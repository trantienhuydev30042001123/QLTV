package com.it1k10.qltv.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.it1k10.qltv.R;
import com.it1k10.qltv.adapter.LoaiSachSpinnerAdapter;
import com.it1k10.qltv.adapter.SachAdapter;
import com.it1k10.qltv.dao.LoaiSachDAO;
import com.it1k10.qltv.dao.SachDAO;
import com.it1k10.qltv.model.LoaiSach;
import com.it1k10.qltv.model.Sach;

import java.util.ArrayList;
import java.util.List;


public class SachFragment extends Fragment {
    ListView lvSach;
    SachDAO sachDAO;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaSach, edTenSach, edGiaThue;
    Spinner spinner;
    Button btnSave, btnCancel;

    SachAdapter adapter;
    Sach item;
    List<Sach> list;

    LoaiSachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<LoaiSach> listLoaiSach;
    LoaiSachDAO loaiSachDAO;
    int maLoaiSach;
    int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sach, container, false);
        lvSach = v.findViewById(R.id.lvSach);
        sachDAO = new SachDAO(getActivity());
        capNhatLv();
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog(getActivity(), 0);
            }
        });
        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item= list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return v;
    }
    void capNhatLv(){
        list = (List<Sach>) sachDAO.getAll();
        adapter = new SachAdapter(getActivity(), this,list);
        lvSach.setAdapter(adapter);
    }
//    public void xoa(final String Id){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle("Delete");
//        builder.setMessage("bạn chắc chắn muốn xóa không?");
//        builder.setCancelable(true);
//
//        builder.setPositiveButton(
//                "Yes",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        sachDAO.delete(Id);
//                        capNhatLv();
//                        dialog.dismiss();
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
        dialog =new Dialog(context);
        dialog.setContentView(R.layout.sach_dialog);
        edMaSach = dialog.findViewById(R.id.edMaSach);
        edTenSach = dialog.findViewById(R.id.edTensach);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        spinner = dialog.findViewById(R.id.spLoaiSach);
        btnCancel = dialog.findViewById(R.id.btnCancelSach);
        btnSave = dialog.findViewById(R.id.btnSaveSach);

        listLoaiSach= new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(context);
        listLoaiSach= (ArrayList<LoaiSach>) loaiSachDAO.getAll();
        sachSpinnerAdapter = new LoaiSachSpinnerAdapter(context, listLoaiSach);
        spinner.setAdapter(sachSpinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parnet, View view, int position, long id) {
                maLoaiSach = Integer.parseInt(listLoaiSach.get(position).getMaLoai());
                Toast.makeText(context, "Chọn"+listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parnet) {

            }
        });
        edMaSach.setEnabled(false);
        if (type !=0){
            edMaSach.setText(String.valueOf(item.getMaSach()));
            edTenSach.setText(item.getTenSach());
            edGiaThue.setText(String.valueOf(item.getGiaThue()));
            for (int i=0; i< listLoaiSach.size(); i++)
                if (item.getMaLoai() == Integer.parseInt((listLoaiSach.get(i).getMaLoai()))){
                    position =i;
                }

            Log.i("demo", "posSach"+position);
            spinner.setSelection(position);

        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Sach();
                item.setTenSach(edTenSach.getText().toString());
                item.setGiaThue(Integer.parseInt(edGiaThue.getText().toString()));
                item.setMaLoai(maLoaiSach);
                if(validate()>0){
                    if (type==0){
                        if(sachDAO.insert(item)>0){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.setMaSach(Integer.parseInt(edMaSach.getText().toString()));
                        if (sachDAO.update(item)>0){
                            Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else {
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
    public  int validate(){
        int check=1;
        if(edTenSach.getText().length()==0|| edGiaThue.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}