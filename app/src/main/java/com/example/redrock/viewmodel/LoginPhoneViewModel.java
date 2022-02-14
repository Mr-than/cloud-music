package com.example.redrock.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 *   description:手机国家码的viewModel
 *   author:冉跃
 *   email:2058109198@qq.com
 *   date:2022/1/27
 */

public class LoginPhoneViewModel extends ViewModel {
    private MutableLiveData<String> loginPhoneLiveData=new MutableLiveData<>();

    public MutableLiveData<String> getLoginPhoneLiveData() {
        return loginPhoneLiveData;
    }

    public void setCountryCode(String code){
        loginPhoneLiveData.setValue(code);
    }

}