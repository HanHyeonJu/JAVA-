package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.todo.fragment.user.UserInfoFragment;
import com.example.todo.fragment.user.UserModifyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MAIN";

    public static final int USER_INFO = 0;
    public static final int USER_MODIFY = 1;

    UserInfoFragment userInfoFragment;
    UserModifyFragment userModifyFragment;

    DrawerLayout drawerLayout;
    Toolbar toolbar;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userInfoFragment = new UserInfoFragment();
        userModifyFragment = new UserModifyFragment();



        // 인텐트 객체 참조 가져오기
        Intent intent = getIntent();

        // 1)
        String loginId = intent.getStringExtra("loginId");
        Log.d(TAG, "로그인 아이디: " + loginId);

        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");


        // 2)
//        Bundle bundle = intent.getExtras();
//        loginId = bundle.getString("loginId");

        //textView = findViewById(R.id.tvProfileName);
        //textView.append("\n로그인 아이디: " + loginId);

        // 툴바 참조 가져오기
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // 툴바를 액션바로 설정하기

        // 액션바 참조 가져오기
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 액션바 제목 숨기기

        // 드로어 레이아웃에 액션바 토글버튼으로 내비게이션뷰 띄우기 설정하기
        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle
                = new ActionBarDrawerToggle(
                        this, drawerLayout, toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        // 내비게이션뷰에 메뉴아이템 선택 이벤트 연결하기
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_user_logout) {
                    Log.d(TAG, "로그아웃 메뉴 선택됨");
                    showDialogLogout();

                } else if (itemId == R.id.menu_user_info) {
                    Log.d(TAG, "내 정보 조회 메뉴 선택됨");
                    onFragmentChanged(USER_INFO);

                } else if (itemId == R.id.menu_user_modify) {
                    Log.d(TAG, "내 정보 수정 메뉴 선택됨");
                    onFragmentChanged(USER_MODIFY);

                } else if (itemId == R.id.menu_user_remove) {
                    Log.d(TAG, "회원탈퇴 메뉴 선택됨");
                }
                return true;
            } // onNavigationItemSelected
        });
        
        // 내비게이션뷰의 헤더레이아웃에 로그인 사용자정보 설정하기
        View headerView = navigationView.getHeaderView(0);
        TextView tvProfileName = headerView.findViewById(R.id.tvProfileName);
        TextView tvProfileIntro = headerView.findViewById(R.id.tvProfileIntro);
        tvProfileName.setText(lastName + " " + firstName);
        tvProfileIntro.setText(loginId);


        // Bottom 내비게이션뷰에 메뉴아이템 선택 이벤트 연결하기
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_menu1:
                        Log.d(TAG, "내 할일 메뉴 선택됨");
                        break;
                    case R.id.bottom_menu2:
                        Log.d(TAG, "전체 할일 메뉴 선택됨");
                        break;
                    case R.id.bottom_menu3:
                        Log.d(TAG, "차트 메뉴 선택됨");
                        break;
                }
                return true;
            } // onNavigationItemSelected
        });

    } // onCreate


    private void showDialogLogout() {
        // 대화상자 준비하기
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("로그아웃")
                .setMessage("로그아웃 하시겠습니까?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 파일에 저장한 로그인 상태유지 관련 정보 모두 삭제하기
                        SharedPreferences pref = getSharedPreferences("todo", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.clear();
                        editor.commit();
                        
                        // 로그인 액티비티 화면 띄우기
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);

                        // 현재 메인액티비티 닫기(종료)
                        finish();
                    }
                })
                .setNeutralButton("취소", null)
                .create();
        
        alertDialog.show(); // 대화상자 띄우기
    } // processLogout


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    } // onBackPressed



    public void onFragmentChanged(int index) {

        Fragment fragment = null;
        if (index == USER_INFO) {
            fragment = userInfoFragment;
        } else if (index == USER_MODIFY) {
            fragment = userModifyFragment;
        }

        if (fragment == null) {
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

        // 내비게이션뷰 닫기
        drawerLayout.closeDrawer(GravityCompat.START);
    } // onFragmentChanged





}