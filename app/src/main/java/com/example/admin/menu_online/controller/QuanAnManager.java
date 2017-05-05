package com.example.admin.menu_online.controller;

import android.content.Context;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.R;

import java.util.ArrayList;
import java.util.Random;

import com.example.admin.menu_online.models.MonAn;
import com.example.admin.menu_online.models.QuanAn;

import org.json.JSONArray;

/**
 * Created by Anh on 4/22/2017.
 */

public class QuanAnManager {
    private static QuanAnManager sInstance = null;
    private ArrayList<QuanAn> danhSachQuan, danhSachQuanMoi;
    private int[] imgList;
    private String[] city = {"Hà Nội, Huế, Đà Nẵng, Hồ Chí Minh"};
    private MenuOnlineDatabase db;
    Context context;
    private QuanAnManager(Context context){
        danhSachQuan = new ArrayList<>();
        danhSachQuanMoi = new ArrayList<>();
        db = new MenuOnlineDatabase(context);
        imgList = new int[]{
                R.drawable.quan1,
                R.drawable.quan2,
                R.drawable.quan3,
                R.drawable.quan4,
                R.drawable.quan5,
                R.drawable.quan6,
                R.drawable.quan7,
                R.drawable.quan8,
                R.drawable.quan9,
                R.drawable.quan10
        };
        this.context =context;
    }
    public static QuanAnManager getsInstance(Context context){
        if(sInstance == null)
            sInstance = new QuanAnManager(context);
        return sInstance;
    }
    public void load(){
        danhSachQuan.clear();
        fakeData(context);
    }
    public void loadNew(){
        danhSachQuanMoi.clear();
        fakeDataNew();
    }
    public ArrayList<QuanAn> getDanhSachQuan(){
        if(danhSachQuan.size() == 0)
            load();
        return danhSachQuan;
    }
    public ArrayList<QuanAn> getDanhSachQuanMoi(){
        if(danhSachQuanMoi.size() == 0) {
            loadNew();
        }
        return danhSachQuanMoi;
    }


    private void fakeDataNew(){
        danhSachQuanMoi.add(new QuanAn("Quán 1", "Địa chỉ 1", "Đà Nẵng", R.drawable.quan1));
        danhSachQuanMoi.add(new QuanAn("Quán 2", "Địa chỉ 2", "Hà Nội",R.drawable.quan2));
        danhSachQuanMoi.add(new QuanAn("Quán 3", "Địa chỉ 3", "Huế", R.drawable.quan3));
        danhSachQuanMoi.add(new QuanAn("Quán 4", "Địa chỉ 4", "Hồ Chí Minh", R.drawable.quan4));
        danhSachQuanMoi.add(new QuanAn("Quán 5", "Địa chỉ 5", "Huế", R.drawable.quan5));

//        for(int i=0; i<5; i++){
//            ArrayList<MonAn> list = MonAnManager.getsInstance().getDanhSachMonAn();
//            for(int j=0; j<5; j++){
//                Random random = new Random();
//                int ran = random.nextInt(list.size());
//                //random de chon 1 mon trong ds mon man cua MonAnManager
//                danhSachQuanMoi.get(i).getMonAnList().add(list.get(ran));
//                //Phía dưới là lọc random theo id của món, còn trên là lọc random theo stt trong arraylist
////                for(int k=0; k<list.size(); k++){
////                    if(list.get(k).getMaMonAn() == ran) {
////                        danhSachQuanMoi.get(i).getMonAnList().add(list.get(k));
////                    }
////                }
//            }
//        }
    }
    private void fakeData(Context context){
//        db.insertQuanAn("Quán 1", "Địa chỉ 1", "Đà Nẵng", R.drawable.quan1));
//        danhSachQuan.add(new QuanAn("Quán 2", "Địa chỉ 2", "Hà Nội",R.drawable.quan2));
//        danhSachQuan.add(new QuanAn("Quán 3", "Địa chỉ 3", "Huế", R.drawable.quan3));
//        danhSachQuan.add(new QuanAn("Quán 4", "Địa chỉ 4", "Hồ Chí Minh", R.drawable.quan4));
//        danhSachQuan.add(new QuanAn("Quán 5", "Địa chỉ 5", "Huế", R.drawable.quan5));
//        danhSachQuan.add(new QuanAn("Quán 6", "Địa chỉ 6", "Hà Nội", R.drawable.quan6));
//        danhSachQuan.add(new QuanAn("Quán 7", "Địa chỉ 7", "Đà Nẵng", R.drawable.quan7));
//        danhSachQuan.add(new QuanAn("Quán 8", "Địa chỉ 8", "Hà Nội", R.drawable.quan8));
//        danhSachQuan.add(new QuanAn("Quán 9", "Địa chỉ 9", "Hồ Chí Minh", R.drawable.quan9));
//        danhSachQuan.add(new QuanAn("Quán 10", "Địa chỉ 10", "Đà Nẵng", R.drawable.quan10));
//        danhSachQuan.add(new QuanAn("Quán 11", "Địa chỉ 11", "Hồ Chí Minh", R.drawable.quan1));
//        danhSachQuan.add(new QuanAn("Quán 12", "Địa chỉ 12", "Hà Nội", R.drawable.quan2));
//        danhSachQuan.add(new QuanAn("Quán 13", "Địa chỉ 13", "Hồ Chí Minh", R.drawable.quan3));
//        danhSachQuan.add(new QuanAn("Quán 14", "Địa chỉ 14", "Huế", R.drawable.quan4));
//        danhSachQuan.add(new QuanAn("Quán 15", "Địa chỉ 15", "Huế", R.drawable.quan7));
//        danhSachQuan.add(new QuanAn("Quán 16", "Địa chỉ 16", "Đà Nẵng", R.drawable.quan5));
//        danhSachQuan.add(new QuanAn("Quán 17", "Địa chỉ 17", "Hà Nội", R.drawable.quan1));
//        for(QuanAn data : danhSachQuan){
//            ArrayList<MonAn> list = MonAnManager.getsInstance().getDanhSachMonAn();
//            for(int j=0; j<5; j++){
//                Random random = new Random();
//                int ran = random.nextInt(list.size());
//                //random de chon 1 mon trong ds mon man cua MonAnManager
//                data.getMonAnList().add(list.get(ran));
//            }
//        }
        //giai phong du lieu truoc khi nao lai
        db.releaseDataQuanAn();
        for(int i=0; i<17; i++){
            ArrayList<MonAn> list = MonAnManager.getsInstance(context).getDanhSachMonAn();
//            ArrayList<MonAn> res = new ArrayList<>();
            String idStr ="";
            for(int j=0; j<5; j++){
                Random random = new Random();
                int ran = random.nextInt(list.size());
                //random de chon 1 mon trong ds mon man cua MonAnManager
//                res.add(list.get(ran));
                if(j!=4) idStr += ran + " ";
                else idStr += ran;
            }
//            JSONArray jsonArray = new JSONArray(res);
            String choose = city[new Random().nextInt(city.length)];
            int img = imgList[new Random().nextInt(imgList.length)];
            db.insertQuanAn("Quán "+(i+1), "Địa chỉ "+(i+1),choose, img, idStr);
        }
        danhSachQuan = db.getAllQuanAn();
    }
}
