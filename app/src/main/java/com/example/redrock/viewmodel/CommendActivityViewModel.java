package com.example.redrock.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.redrock.base.APP;
import com.example.redrock.bean.Commend;
import com.example.redrock.bean.CommendBean;
import com.example.redrock.model.InternetTool;
import com.example.redrock.room.HomePageDataBase;
import com.example.redrock.room.LoginBeanDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *   description:评论活动的ViewModel
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/2/14
 */

public class CommendActivityViewModel extends ViewModel {

    private MutableLiveData<List<CommendBean>> _commendContent=new MutableLiveData<>();
    public LiveData<List<CommendBean>> commendContent=_commendContent;

    private MutableLiveData<String> _songPhoto=new MutableLiveData<>();
    public LiveData<String> songPhoto=_songPhoto;

    private MutableLiveData<String> _songName=new MutableLiveData<>();
    public LiveData<String> songName=_songName;

    private MutableLiveData<String> _code=new MutableLiveData<>();
    public LiveData<String> code =_code;

    private MutableLiveData<String> _songId=new MutableLiveData<>();
    public LiveData<String> songId=_songId;

    private HomePageDataBase dataBase= Room.databaseBuilder(APP.getContext(),HomePageDataBase.class,"HomeFoundWonderful").build();
    private LoginBeanDao loginBeanDao=dataBase.loginBeanDao();

    private InternetTool tool;

    private String Cookie;

    public CommendActivityViewModel(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Cookie=loginBeanDao.getCookie();
            }
        }).start();
    }

    public void getCommend(String id){

        this._songId.postValue(id);

        tool=new InternetTool();
        tool.setBaseUrl("http://redrock.udday.cn:2022")
                .setRequestType(InternetTool.GET)
                .setPortPath("/comment/music")
                .setRequestData("id",id)
                .setRequestData("limit","50")
                .setRequestData("timestamp",String.valueOf(System.currentTimeMillis()))
                .startRequest(new InternetTool.Back() {
                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onFinish(String data) {
                        setData(data);
                    }
                });
    }

    private void setData(String data){
        Gson gson=new Gson();
        Commend bean=gson.fromJson(data,new TypeToken<Commend>(){}.getType());
        List<CommendBean> list=new ArrayList<>();


        if(bean.getHotComments()!=null){
            for (int i = 0; i < bean.getHotComments().size(); i++) {

                if(bean.getHotComments().get(i).getUser()!=null){

                    String like=String.valueOf(bean.getHotComments().get(i).getLikedCount());
                    if(like.length()>=5){
                        double c=bean.getHotComments().get(i).getLikedCount()/10000d;
                        DecimalFormat df = new DecimalFormat("0.0");
                        like= df.format(c)+"万";
                    }

                    list.add(new CommendBean(bean.getHotComments().get(i).getUser().getAvatarUrl(),bean.getHotComments().get(i).getUser().getNickname(),
                            bean.getHotComments().get(i).getTimeStr(),bean.getHotComments().get(i).getContent(),like,bean.getHotComments().get(i).getCommentId()));
                }
            }
        }
        _commendContent.postValue(list);
    }

    public void setSongPhoto(String url){
        _songPhoto.postValue(url);
    }
    public void setSongName(String name){
        _songName.postValue(name);
    }

    public void commend(String content,String t,String id,String commentId){
        tool=new InternetTool();
        tool.setBaseUrl("http://redrock.udday.cn:2022")
                .setRequestType(InternetTool.GET)
                .setPortPath("/comment")
                .setHeader("Cookie",Cookie)
                .setRequestData("t",t)
                .setRequestData("type","0")
                .setRequestData("id",id);
        if(t.equals("2")){
            tool.setRequestData("commentId",commentId);
        }

        tool.setRequestData("content",content)
                .startRequest(new InternetTool.Back() {
                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onFinish(String data){

                        try {
                            JSONObject jsonObject=new JSONObject(data);

                            String code=jsonObject.getString("code");

                            _code.postValue(code);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });



    }



}
