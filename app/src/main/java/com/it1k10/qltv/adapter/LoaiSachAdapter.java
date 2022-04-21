package com.it1k10.qltv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.it1k10.qltv.R;
import com.it1k10.qltv.fragment.LoaiSachFragment;
import com.it1k10.qltv.model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    LoaiSachFragment fragment;
    private ArrayList<LoaiSach> lists;
    TextView tvMaLS, tvTenLS;
    ImageView imgDel;


    public LoaiSachAdapter(@NonNull Context context,  LoaiSachFragment fragment, ArrayList<LoaiSach> lists) {
        super(context, 0 , lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item, null);
        }
        final LoaiSach item = lists.get(position);
        if (item != null){
            tvMaLS = v.findViewById(R.id.tvMaLS);
            tvMaLS.setText("Mã loại sách: "+item.getMaLoai());
            tvTenLS = v .findViewById(R.id.tvTenLS);
            tvTenLS.setText("Tên loại sách: "+item.getTenLoai());
//            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
//        imgDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fragment.xoa(String.valueOf(item.getMaLoai()));
//            }
//        });
        return v;
    }


}
