package com.example.redrock.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.redrock.R;
import com.example.redrock.bean.CountryAndRegion;
import com.example.redrock.dialog.LoginPhoneDialogFragment;
import com.example.redrock.fragment.PhoneLoginFragment;
import com.example.redrock.viewModel.LoginPhoneViewModel;

import java.util.List;

public class CountryChooseAdapter extends RecyclerView.Adapter<CountryChooseAdapter.CountryChooseAdapterViewHolder>{

    private List<CountryAndRegion> list;
    private LoginPhoneDialogFragment loginPhoneDialogFragment;
    private LoginPhoneViewModel viewModel;

    public CountryChooseAdapter(List<CountryAndRegion> list, LoginPhoneDialogFragment loginPhoneDialogFragment, LoginPhoneViewModel viewModel){
        this.list=list;
        this.viewModel=viewModel;
        this.loginPhoneDialogFragment=loginPhoneDialogFragment;
    }

    @NonNull
    @Override
    public CountryChooseAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryChooseAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.countryitem,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryChooseAdapterViewHolder holder, int position) {
        holder.countryName.setText(list.get(position).getCountryName());
        holder.countryCode.setText(list.get(position).getCountryCode());

        String code=list.get(position).getCountryCode();

        holder.countryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setCountryCode(code);
                loginPhoneDialogFragment.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    protected class CountryChooseAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView countryName,countryCode;

        public CountryChooseAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            countryCode=itemView.findViewById(R.id.CountryCode);
            countryName=itemView.findViewById(R.id.CountryName);
        }
    }

}
