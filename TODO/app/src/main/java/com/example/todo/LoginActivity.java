package com.example.todo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todo.domain.User;
import com.example.todo.domain.UserOneResult;
import com.example.todo.retrofit.RetrofitClient;
import com.example.todo.retrofit.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private String id, pwd;
    private EditText txtId, txtPwd;
    private CheckBox chkRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide(); // 액션바 숨기기

        // 뷰 참조 가져오기
        txtId = findViewById(R.id.txtId);
        txtPwd = findViewById(R.id.txtPwd);
        chkRememberMe = findViewById(R.id.chkRememberMe);
        Button btnQuit = findViewById(R.id.btnQuit);
        Button btnJoin = findViewById(R.id.btnJoin);
        Button btnLogin = findViewById(R.id.btnLogin);

        // 종료버튼 클릭 이벤트 연결하기
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showQuitMessage();
            }
        });

        // 람다 표현식 : 추상메소드를 한개만 가진 인터페이스 구현 객체를 만들때 사용가능
        //btnQuit.setOnClickListener(view -> showQuitMessage());

        // 회원가입 버튼 클릭시
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

        // 로그인 버튼 클릭 이벤트 연결하기
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processLogin();
            }
        });

    } // onCreate


    private void processLogin() {
        // 사용자 입력값 가져오기
        id = txtId.getText().toString().trim();
        pwd = txtPwd.getText().toString().trim();

        // 사용자 입력값 검증하기

        // 사용자 입력값과 DB 정보와 비교하기
        requestGetUser(id);
    } // processLogin


    private void requestGetUser(String id) {

        UserService userService = RetrofitClient.getUserService();

        Call<UserOneResult> call = userService.getUser("one", id);

        call.enqueue(new Callback<UserOneResult>() {
            @Override
            public void onResponse(Call<UserOneResult> call, Response<UserOneResult> response) {
                if (!response.isSuccessful()) return;

                UserOneResult userOneResult = response.body();
                User user = userOneResult.getUser();

                boolean isSame = compareLoginIdPwd(user);
                if (!isSame) { // isSame == false   로그인 실패
                    Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 다릅니다.", Toast.LENGTH_LONG).show();
                    txtId.requestFocus();
                    return;
                }

                // 로그인 성공 처리(로그인 상태유지 처리, 메인 액티비티 화면으로 이동)
                processLoginSuccess(user);
            } // onResponse

            @Override
            public void onFailure(Call<UserOneResult> call, Throwable t) {
            }
        });

    } // sendRequestGetUser


    // 로그인 성공 처리 메소드(로그인 상태유지 처리, 메인 액티비티 화면으로 이동)
    private void processLoginSuccess(User user) {
        // 로그인 상태유지 체크되어있으면 SharedPreferences로 파일에 저장
        if (chkRememberMe.isChecked()) {
            saveRememberMe(user);
        }

        Intent intent = new Intent(this, MainActivity.class);

        // 1)
        intent.putExtra("loginId", id);
        intent.putExtra("firstName", user.getFirstName());
        intent.putExtra("lastName", user.getLastName());

        // 2)
//        Bundle bundle = new Bundle();
//        bundle.putString("loginId", id);
//        intent.putExtras(bundle);

        // 메인 액티비티 화면 띄우기
        startActivity(intent);

        finish(); // 현재 화면 액티비티 닫기(종료)
    } // processLoginSuccess


    private void saveRememberMe(User user) {
        Date date = new Date(); // 현재(로그인)시점의 날짜시간 정보를 가진 Date 객체 생성
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // format 메소드: Date -> String 변환하기
        String strDate = sdf.format(date); // "2022-03-10 09:49:10"

        SharedPreferences pref = getSharedPreferences("todo", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("rememberMe", true);
        editor.putString("rememberMeDate", strDate);
        editor.putString("loginId", id);
        editor.putString("firstName", user.getFirstName());
        editor.putString("lastName", user.getLastName());
        editor.commit();
    } // saveRememberMe


    private boolean compareLoginIdPwd(User user) {
        boolean isSame = false;

        if (user != null) { // 아이디 일치
            if (pwd.equals(user.getPassword())) { // 비밀번호 일치
                isSame = true;
            } else { // 비밀번호 불일치
                isSame = false;
            }
        } else { // user == null  아이디 불일치
            isSame = false;
        }
        return isSame;
    } // compareLoginIdPwd


    private void showQuitMessage() {
       AlertDialog alertDialog = new AlertDialog.Builder(this)
               .setTitle("종료")
               .setMessage("정말 종료하시겠습니까?")
               .setIcon(android.R.drawable.ic_dialog_alert)

               .setPositiveButton("예", (dialogInterface, i) -> finish())

               .setNeutralButton("취소", null)

               .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                        // 실행문;
                   }
               })
               .create(); // 대화상자 빌더로 설정 및 객체생성

        alertDialog.show(); // 대화상자 띄우기
    } // showQuitMessage



}




