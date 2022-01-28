package com.example.redrock.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginPhoneViewModel extends ViewModel {
    private MutableLiveData<String> loginPhoneLiveData=new MutableLiveData<>();

    public MutableLiveData<String> getLoginPhoneLiveData() {
        return loginPhoneLiveData;
    }

    public void setCountryCode(String code){
        loginPhoneLiveData.setValue(code);
    }

}
