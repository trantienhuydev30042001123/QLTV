package com.it1k10.qltv.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.it1k10.qltv.R;
import com.it1k10.qltv.adapter.LoaiSachAdapter;
import com.it1k10.qltv.dao.LoaiSachDAO;
import com.it1k10.qltv.model.LoaiSach;
import com.it1k10.qltv.model.ThanhVien;

import java.util.ArrayList;


public class LoaiSachFragment extends Fragment {
    ListView lvLoaiSach;
    ArrayList<LoaiSach> list;
    static LoaiSachDAO dao;
    LoaiSachAdapter adapter;
    LoaiSach item;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLS, edTenLS;
    Button btnSave, btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        lvLoaiSach = v.findViewById(R.id.lvLoaiSach) ;
        dao = new LoaiSachDAO(getActivity());
        fab = v.findViewById(R.id.fab);
        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvLoaiSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return v;
    }
    void capNhatLv(){
        list = (ArrayList<LoaiSach>) dao.getAll();
        adapter =new LoaiSachAdapter(getActivity(), this, list);
        lvLoaiSach.setAdapter(adapter);
    }
//    public void xoa(final String Id){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle("Deltele");
//        builder.setMessage("Bạn có chắc chắn muốn xóa không?");
//        builder.setCancelable(true);
//
//        builder.setPositiveButton(
//                "yes",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        dao.delete(Id);
//                        capNhatLv();
//                        dialog.cancel();
//                    }
//                }
//        );
//        builder.setNegativeButton(
//                "No",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                }
//        );
//        AlertDialog alert = builder.create();
//        builder.show();
//    }
    protected void openDialog(final Context context, final int type){
        dialog= new Dialog(context);
        dialog.setContentView(R.layout.loai_sach_dialog);
        edMaLS = dialog.findViewById(R.id.edMaLS);
        edTenLS = dialog.findViewById(R.id.edTenLS);
        btnCancel = dialog.findViewById(R.id.btnCancelLS);
        btnSave = dialog.findViewById(R.id.btnSaveLS);

        edMaLS.setEnabled(false);
        if (type !=0) {
            edMaLS.setText(String.valueOf(item.getMaLoai()));
            edTenLS.setText(item.getTenLoai());
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new LoaiSach();
                item.setTenLoai(edTenLS.getText().toString());
                if(validate()>0){
                    if(type==0){
                        if(dao.insert(item)>0){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.setMaLoai(edMaLS.getText().toString());
                        if(dao.update(item)>0){
                            Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public int validate() {
        int check = 1;
        if (edTenLS.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}