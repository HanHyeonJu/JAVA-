package com.example.todo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogoActivity extends AppCompatActivity {

    public static final String TAG = "LOGO";

    private TextView tvProgress;
    private ProgressBar progressBar;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        // 액션바 숨기기
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide(); // 숨기기

        tvProgress = findViewById(R.id.tvProgress);
        progressBar = findViewById(R.id.progressBar);

        // 안드로이드에서는 기존 UI스레드는 Thread.sleep() 호출을 허용하지 않으므로
        // 별도의 작업스레드 생성하여 실행함.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 프로그레스바 보이기 한 후 일정시간 주기로 채우기
                processProgressBar();

                // rememberMe 로그인 상태유지 값에 따라
                // 로그인 화면 또는 메인 화면 띄우기
                processRememberMe();
            }
        });
        thread.start();

    } // onCreate


    private void processProgressBar() {
        // 2초(1초=1000밀리초) 후에 프로그레스바가 화면에 나타나서
        // 100까지 채운 뒤에 로그인 화면으로 전환시키기

        try {
            Thread.sleep(2000); // 2초 쉬기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 안드로이드는 UI 스레드에서만 뷰 객체 접근을 허용함.
        // 작업스레드에서는 뷰 객체 접근이 허용안됨에 주의.
        //  -> 작업스레드에서 뷰 객체에 접근하려면
        // 	   runOnUiThread() 메소드를 통해 UI 스레드로 실행흐름을 옮겨야 함.
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvProgress.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
//        runOnUiThread(() -> {
//            tvProgress.setVisibility(View.VISIBLE);
//            progressBar.setVisibility(View.VISIBLE);
//        });


        for (progress=0; progress<100; progress++) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvProgress.setText(progress + "%");
                    progressBar.setProgress(progress);
                }
            });

            try {
                Thread.sleep(10); // 0.01초 쉬기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } // for
    } // processProgressBar


    private void processRememberMe() {
        // 기존에 저장되어 있던 로그인 상태유지 값을 SharedPreferences로부터 가져오기
        SharedPreferences pref = getSharedPreferences("todo", Activity.MODE_PRIVATE);

        Intent intent = null;
        if (pref == null || !pref.contains("rememberMe")) {
            // 로그인 액티비티 설정
            intent = new Intent(this, LoginActivity.class);

        } else { // (pref != null && pref.contains("rememberMe"))
            // rememberMe는 pref에 값이 있는지만 체크만 하면 됨.
            //boolean isRememberMe = pref.getBoolean("rememberMe", false);

            String id = pref.getString("loginId", "");
            String startDate = pref.getString("rememberMeDate", ""); // "2022-03-10 09:49:10"
            String firstName = pref.getString("firstName", "");
            String lastName = pref.getString("lastName", "");

            // 로그인 상태유지 기간 확인 (2분 동안만 로그인 상태 유지함)
            boolean isInPeriod = checkTimeInPeriod(startDate);

            if (!isInPeriod) { // isInPeriod == false
                // SharedPreferences 파일 데이터 모두 삭제하기
                clearRememberMe();

                intent = new Intent(this, LoginActivity.class);

            } else {
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("loginId", id);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
            }
        }

        startActivity(intent);

        // 현재 액티비티 닫기
        finish();
    } // processRememberMe


    private boolean checkTimeInPeriod(String strStartDate) {
        Date nowDate = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = null;
        try {
            // parse 메소드: String -> Date 변환하기
            startDate = sdf.parse(strStartDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = nowDate.getTime() - startDate.getTime();
        Log.d(TAG, "diff : " + diff + " 밀리초");

        // 1초 = 1000밀리초
        long diffMinute = diff / (1000*60); //  밀리초 -> 분 단위 값으로 변환
        Log.d(TAG, "diffMinute : " + diffMinute + " 분"); // 분단위 차이값 로그 출력

        if (diffMinute <= 2) { // 날짜 차이값이 2분 이내인지 확인
            return true;
        } else {
            return false;
        }
    } // checkTimeInPeriod


    private void clearRememberMe() {
        SharedPreferences pref = getSharedPreferences("todo", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        //editor.remove("rememberMe");
        //editor.remove("rememberMeDate");

        editor.clear();
        editor.commit();
    } // clearRememberMe

}