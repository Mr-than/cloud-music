package com.example.mvvmdemo;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class InternetTool {

    //基本服务器地址
    private String baseUrl;
    //接口地址
    private String portPath;
    //需要拼接的请求数据
    private String requestData;
    //接口总地址
    private String url;
    //请求类型

    //get请求
    public static final String GET="GET";
    //保存请求类型
    private String type;
    //请求头
    private String headerKey;
    private String headerContent;

    public InternetTool(){

        baseUrl=null;
        portPath=null;
        requestData=null;
        url=null;
        headerKey=null;
        headerContent=null;
    }


    public InternetTool setBaseUrl(String baseUrl){
        this.baseUrl=baseUrl;
        url=baseUrl;
        return this;
    }
    public InternetTool setRequestType(String type){
        this.type=type;
        return this;
    }
    public InternetTool setPortPath(String path){
        this.portPath=path;
        url=baseUrl+portPath;
        return this;
    }

    public InternetTool setRequestData(String key,String value){
        if(requestData==null||requestData.equals("")){
            requestData=key+"="+value;
            url=this.baseUrl+portPath+"?"+requestData;
        }else{
            requestData=null;
            requestData=key+"="+value;
            url=url+"&"+requestData;
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
                    URL url=new URL(InternetTool.this.url);
                    HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    if(InternetTool.this.headerKey!=null&&InternetTool.this.headerContent!=null){
                        connection.setRequestProperty(headerContent,headerKey);
                    }
                    connection.setDoInput(true);




                    StringBuilder stringBuilder=new StringBuilder();

                    InputStream in=connection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
                    String line=null;

                    while((line=bufferedReader.readLine())!=null){
                        stringBuilder.append(line).append("\n");
                    }

                    onFinish(stringBuilder,back);
                    //Toast.makeText(, "", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void onFinish(StringBuilder stringBuilder,Back back){
        back.onFinish(stringBuilder.toString());
    }


    public interface Back{
        public void onFinish(String data);
    }
}

