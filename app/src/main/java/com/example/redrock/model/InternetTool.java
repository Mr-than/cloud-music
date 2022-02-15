package com.example.redrock.model;

import android.widget.Toast;

import com.example.redrock.activity.PlaylistSongActivity;
import com.example.redrock.base.APP;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *   description:网络请求工具
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/1/24
 */

public class InternetTool {

    //基本服务器地址
    private StringBuilder baseUrl;
    //接口地址
    private String portPath;
    //需要拼接的请求数据
    private StringBuilder requestData;
    //接口总地址
    private StringBuilder url;
    //请求类型

    //由于get请求完全能满足要求，所以并没有封装post及其他请求类型

    //get请求
    public static final String GET="GET";
    //保存请求类型
    private String type;

    //请求头虽然一般情况下用不到，但是我必须要有一个，即便没怎么好好封装这玩意儿 /doge
    private String headerKey;
    private String headerContent;

    public InternetTool(){

        baseUrl=new StringBuilder();
        portPath=null;
        requestData=new StringBuilder();
        url=new StringBuilder();
        headerKey=null;
        headerContent=null;
    }


    public InternetTool setBaseUrl(String baseUrl){
        this.baseUrl=new StringBuilder();
        this.baseUrl.append(baseUrl);
        url=new StringBuilder();
        url.append(baseUrl);
        return this;
    }
    public InternetTool setRequestType(String type){
        this.type=type;
        return this;
    }
    public InternetTool setPortPath(String path){
        this.portPath=path;
        baseUrl.append(portPath);
        url=baseUrl;
        return this;
    }

    public synchronized InternetTool setRequestData(String key,String value){
        if(requestData==null||requestData.toString().equals("")){
            requestData=new StringBuilder();
            //requestData=key+"="+value;
            requestData.append(key+"="+value);
            url=baseUrl.append("?").append(requestData);
            //url=this.baseUrl+portPath+"?"+requestData;
        }else{
            requestData=new StringBuilder();
            //requestData=key+"="+value;
            requestData.append(key+"="+value);
            //url=url+"&"+requestData;
            url.append("&").append(requestData);
        }
        return this;
    }

    public InternetTool setHeader(String headerKey,String headerContent){
        this.headerKey=headerKey;
        this.headerContent=headerContent;
        return this;
    }


    public void startRequest(Back back){

        if(type.equals(GET)){
            get(back);
        }else {
            throw new RuntimeException("没有或者暂时没有封装这种请求类型");
        }
    }

    private void get(Back back){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url=new URL(InternetTool.this.url.toString());
                    HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(8000);
                    connection.setRequestMethod("GET");
                    if(InternetTool.this.headerKey!=null&&InternetTool.this.headerContent!=null){
                        connection.setRequestProperty(headerKey,headerContent);
                    }


                    connection.setDoInput(true);
                    InputStream in=null;
                    try {
                        in = connection.getInputStream();
                    }catch (Exception e){
                        back.onError();
                        e.printStackTrace();
                    }
                    StringBuilder stringBuilder=new StringBuilder();

                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
                    String line=null;

                    while((line=bufferedReader.readLine())!=null){
                        stringBuilder.append(line).append("\n");
                    }

                    onFinish(stringBuilder,back);

                }catch (Exception e){
                    back.onError();
                   // Toast.makeText(PlaylistSongActivity.PLAYLIST_ACTIVITY, "网络异常，加载歌单失败", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


                baseUrl=new StringBuilder();
                portPath=null;
                requestData=new StringBuilder();
                url=new StringBuilder();
                headerKey=null;
                headerContent=null;

            }
        }).start();
    }

    private void onFinish(StringBuilder stringBuilder,Back back){
        back.onFinish(stringBuilder.toString());
    }


    public interface Back{
        public void onError();
        public void onFinish(String data);
    }
}