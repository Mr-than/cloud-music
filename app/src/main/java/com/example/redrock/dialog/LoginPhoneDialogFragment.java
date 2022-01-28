package com.example.redrock.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redrock.R;
import com.example.redrock.adapter.CountryChooseAdapter;
import com.example.redrock.bean.CountryAndRegion;
import com.example.redrock.fragment.PhoneLoginFragment;
import com.example.redrock.model.CountryChooseTitle;
import com.example.redrock.viewModel.LoginPhoneViewModel;

import java.util.ArrayList;
import java.util.List;

public class LoginPhoneDialogFragment extends DialogFragment {


    private List<CountryAndRegion> list;
    private RecyclerView county;
    private LoginPhoneViewModel viewModel;


    public LoginPhoneDialogFragment(LoginPhoneViewModel viewModel){
        this.viewModel=viewModel;
    }


    ImageView cancelWindow;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    Dialog dialog=new Dialog(getActivity(), R.style.Phone_dialog);
    dialog.setContentView(R.layout.dialogfragment_loginphone);
    //外部点击可以关闭弹窗
    dialog.setCanceledOnTouchOutside(true);

    Window window=dialog.getWindow();
    //弹窗背景设置为透明颜色显示圆角
    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    WindowManager.LayoutParams lp=window.getAttributes();
    lp.windowAnimations=R.style.Phone_dialog;
    //设置弹窗位置和高度、宽度
    lp.gravity= Gravity.BOTTOM;
    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
    lp.height=2180;
    window.setAttributes(lp);

    init();
    county=dialog.findViewById(R.id.countryRegion);

    cancelWindow=dialog.findViewById(R.id.cancel_window);
    cancelWindow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dismiss();
        }
    });


        CountryChooseAdapter countryChooseAdapter=new CountryChooseAdapter(list,this,viewModel);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        county.setLayoutManager(manager);
        county.addItemDecoration(new CountryChooseTitle());
        county.setAdapter(countryChooseAdapter);
    return dialog;
    }

    private void init(){
        list=new ArrayList<>();
        //只搞了中国地区的几个国家码
        list.add(new CountryAndRegion("中国","+86"));
        list.add(new CountryAndRegion("中国香港","+852"));
        list.add(new CountryAndRegion("中国澳门","+853"));
        //台湾永远是中国永远不可或缺的一部分！！！
        list.add(new CountryAndRegion("中国台湾","+886"));
    }

}
