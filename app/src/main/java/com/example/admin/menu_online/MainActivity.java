package com.example.admin.menu_online;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.adapters.LoaiMonAnAdapter;
import com.example.admin.menu_online.adapters.MyAdapter;
import com.example.admin.menu_online.adapters.QuanAnAdapter;
import com.example.admin.menu_online.controller.LoadDatabaseControl;
import com.example.admin.menu_online.controller.MonAnManager;
import com.example.admin.menu_online.controller.QuanAnManager;
import com.example.admin.menu_online.models.MonAn;
import com.example.admin.menu_online.models.QuanAn;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MenuOnlineDatabase menuOnlineDatabase;

    ArrayList<MonAn> monAnNoiBat, locMonAn;
    private String[] cityList, loaimonanList;
    private int[] arrImgLoaiMonAn;
    private MyAdapter myAdapter;
    private QuanAnAdapter quanAnAdapter;
    private LoaiMonAnAdapter loaiMonAnAdapter;
    private ArrayAdapter<String> adapterCity;
    private ListView lvHienThiMonAn, lvCity, lvLoaiMonAn;
    private TextView txtTitle;
    private EditText txtSearch;
    private Button btnNewMonAn, btnNewQuanAn, btnMenu, btnSearch;
    private ImageView imgApp;

    TabHost tabHost;
    String loaiMonAn="", thanhPho="";

    Boolean siteCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setControl();
        setEvent();
    }


    private void setEvent() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, btnMenu);
                popupMenu.getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if(id == R.id.monAnList){
                            Intent intent = new Intent(MainActivity.this, MenuMonAn.class);
                            startActivity(intent);
                        }
                        else if(id == R.id.quanAnList){
                            startActivity(new Intent(MainActivity.this, MenuQuanAn.class));
                        }
                        else if(id == R.id.ranking){
                            Toast.makeText(getApplicationContext(), "Hiện chưa có chức năng này", Toast.LENGTH_SHORT).show();
                        }
                        else if(id == R.id.info){
                            Toast.makeText(getApplicationContext(), "Version 1.1.3.1415926535897932", Toast.LENGTH_SHORT).show();
                        }
                        else if(id == R.id.user){
                            startActivity(new Intent(MainActivity.this, UserLogin.class));
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        //nut search
        lvHienThiMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(siteCheck == true){
                    Intent intent = new Intent(MainActivity.this, ChiTietMonAn.class);
                    Bundle bundle = new Bundle();
                    if(loaiMonAn == "" && thanhPho == "")
                        bundle.putSerializable("detail",MonAnManager.getsInstance(MainActivity.this).getDanhSachMonMoi().get(position));
                    else
                        bundle.putSerializable("detail",locMonAn.get(position));
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, ChiTietQuanAn.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("detail", QuanAnManager.getsInstance(MainActivity.this).getDanhSachQuanMoi().get(position));
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
            }
        });
        lvHienThiMonAn.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
                if(sharedPreferences.getInt("USERID", 0)!=0) {
                    if(thanhPho == "" && loaiMonAn == "") {
                        if (menuOnlineDatabase.checkDatHang(monAnNoiBat.get(position).getMaMonAn())) {
                            int maMonAn = monAnNoiBat.get(position).getMaMonAn();
                            String tenMon = monAnNoiBat.get(position).getTenMonAn();
                            int img = monAnNoiBat.get(position).getImage();
                            int soLuong = monAnNoiBat.get(position).getSoLuong();
                            String viTri = monAnNoiBat.get(position).getViTri();
                            String loaiMonAn = monAnNoiBat.get(position).getLoaiMonAn();
                            float giaTien = monAnNoiBat.get(position).getGiaTien();
                            menuOnlineDatabase.insertDatHang(maMonAn, tenMon, img, 1, viTri, loaiMonAn, giaTien);
                            Toast.makeText(MainActivity.this, "Da them mon thanh cong", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(MainActivity.this, "Da co san mon an nay", Toast.LENGTH_SHORT).show();
                    } else {
                        if (menuOnlineDatabase.checkDatHang(locMonAn.get(position).getMaMonAn())) {
                            int maMonAn = locMonAn.get(position).getMaMonAn();
                            String tenMon = locMonAn.get(position).getTenMonAn();
                            int img = locMonAn.get(position).getImage();
                            int soLuong = locMonAn.get(position).getSoLuong();
                            String viTri = locMonAn.get(position).getViTri();
                            String loaiMonAn = locMonAn.get(position).getLoaiMonAn();
                            float giaTien = locMonAn.get(position).getGiaTien();
                            menuOnlineDatabase.insertDatHang(maMonAn, tenMon, img, 1, viTri, loaiMonAn, giaTien);
                            Toast.makeText(MainActivity.this, "Da them mon thanh cong", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(MainActivity.this, "Da co san mon an nay", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Ban can dang nhap de thuc hien chuc nang nay", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, UserLogin.class));
                }
                return false;
            }
        });
        //Button lọc món theo thành phố
        lvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                monAnNoiBat.clear();
//                myAdapter.notifyDataSetChanged();
                locMonAn = new ArrayList<MonAn>();
                thanhPho = cityList[position];
                siteCheck = true;
                for(int i=0; i<MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().size(); i++){
                    if(loaiMonAn == "") {
                        if (MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i).getViTri().equals(thanhPho)) {
                            locMonAn.add(MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i));
                            txtTitle.setText("Món ăn tại "+thanhPho);
                        }
                    }
                    else{
                        if (MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i).getViTri().equals(thanhPho) && MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i).getLoaiMonAn().equals(loaiMonAn)) {
                            locMonAn.add(MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i));
                            txtTitle.setText("Món ăn "+loaiMonAn+" tại "+thanhPho);
                        }
                    }
                }
                if(locMonAn.size() == 0) txtTitle.setText("Khong co du lieu");
                myAdapter = new MyAdapter(MainActivity.this, R.layout.item_monan, locMonAn);
                lvHienThiMonAn.setAdapter(myAdapter);
                tabHost.setCurrentTab(0);
            }
        });
        //Button lọc món theo phân loại
        lvLoaiMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                monAnNoiBat.clear();
//                myAdapter.notifyDataSetChanged();
                locMonAn = new ArrayList<MonAn>();
                loaiMonAn = loaimonanList[position];
                siteCheck = true;
                for(int i=0; i<MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().size(); i++){
                    if(thanhPho == "") {
                        if (MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i).getLoaiMonAn().equals(loaiMonAn)) {
                            locMonAn.add(MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i));
                            txtTitle.setText("Món ăn "+loaiMonAn);
                        }
                    }
                    else{
                        if (MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i).getViTri().equals(thanhPho) && MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i).getLoaiMonAn().equals(loaiMonAn)) {
                            locMonAn.add(MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i));
                            txtTitle.setText("Món ăn "+loaiMonAn+" tại "+thanhPho);
                        }
                    }
                }
                if(locMonAn.size() == 0) txtTitle.setText("Khong co du lieu");
                myAdapter = new MyAdapter(MainActivity.this, R.layout.item_monan, locMonAn);
                lvHienThiMonAn.setAdapter(myAdapter);
                tabHost.setCurrentTab(0);
            }
        });
        //button khi nhan vào sẽ reset trạng thái về những món mới
        btnNewMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                monAnNoiBat = MonAnManager.getsInstance().getDanhSachMonMoi();
                myAdapter = new MyAdapter(MainActivity.this, R.layout.item_monan, monAnNoiBat);
                lvHienThiMonAn.setAdapter(myAdapter);
                thanhPho = "";
                loaiMonAn = "";
                txtTitle.setText("Món ăn nổi bật");
                siteCheck = true;
            }
        });
        btnNewQuanAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ArrayList<QuanAn> quanAnNoiBat = QuanAnManager.getsInstance(MainActivity.this).getDanhSachQuanMoi();
                quanAnAdapter = new QuanAnAdapter(MainActivity.this, R.layout.item_quanan, quanAnNoiBat);
                lvHienThiMonAn.setAdapter(quanAnAdapter);
                txtTitle.setText("Quán ăn mới");
                siteCheck = false;
            }
        });
    }

    private void setControl() {
        //Dem so lan start up app
        LoadDatabaseControl.getsInstance(this).increaseCountStartUpApp();
        menuOnlineDatabase = new MenuOnlineDatabase(this);
        //Nap du lieu cho lan run app dau tien
        MonAnManager.getsInstance(this).LoadData();
        QuanAnManager.getsInstance(this).load();
        //Khoi tao tabhost chứa các tab con
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        //tao cac tab con
        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setIndicator("Home");
        tab1.setContent(R.id.tab1);
        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setIndicator("Phân loại");
        tab2.setContent(R.id.tab2);
        TabHost.TabSpec tab3 = tabHost.newTabSpec("t3");
        tab3.setIndicator("Thành phố");
        tab3.setContent(R.id.tab3);
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        btnNewMonAn = (Button) findViewById(R.id.btnNewMonAn);
        btnNewQuanAn = (Button) findViewById(R.id.btnNewQuanAn);
        btnMenu = (Button) findViewById(R.id.btnMenu);
        monAnNoiBat = MonAnManager.getsInstance(MainActivity.this).getDanhSachMonMoi();


        myAdapter = new MyAdapter(this, R.layout.item_monan, monAnNoiBat);
        lvHienThiMonAn = (ListView) findViewById(R.id.lvHienThiMonAn);
        lvHienThiMonAn.setAdapter(myAdapter);

        //setup city list
        cityList = getResources().getStringArray(R.array.city);
        adapterCity = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityList);
        lvCity = (ListView) findViewById(R.id.lvCity);
        lvCity.setAdapter(adapterCity);

        //tao arr img loai mon an
        arrImgLoaiMonAn = new int[]{
                R.drawable.loaimonan_vietnam,
                R.drawable.loaimonan_chaumy,
                R.drawable.loaimonan_my,
                R.drawable.loaimonan_chauau,
                R.drawable.loaimonan_y,
                R.drawable.loaimonan_phap,
                R.drawable.loaimonan_duc,
                R.drawable.loaimonan_taybannha,
                R.drawable.loaimonan_china,
                R.drawable.loaimonan_ando,
                R.drawable.loaimonan_thai,
                R.drawable.loaimonan_nhatban,
                R.drawable.loaimonan_hanquoc,
                R.drawable.loaimonan_banhmy,
                R.drawable.loaimonan_quananle,
                R.drawable.loaimonan_cafe,
                R.drawable.loaimonan_douong
        };
//        addArrImgLoaiMonAn(arrImgLoaiMonAn);

        //setup loaimonan list
        loaimonanList = getResources().getStringArray(R.array.loaiMonAn);
        loaiMonAnAdapter = new LoaiMonAnAdapter(this, R.layout.item_loaimonan, loaimonanList, arrImgLoaiMonAn);
        lvLoaiMonAn = (ListView) findViewById(R.id.lvLoaiMonAn);
        lvLoaiMonAn.setAdapter(loaiMonAnAdapter);
    }
}
