package com.example.todo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todo.domain.User;
import com.example.todo.domain.UserOneResult;
import com.example.todo.domain.UserResult;
import com.example.todo.retrofit.RetrofitClient;
import com.example.todo.retrofit.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JoinActivity extends AppCompatActivity {

    public static final String TAG = "JOIN";

    private boolean isIdDuplicated; // 아이디 중복여부
    private TextView tvJoinId;
    private EditText txtJoinId, txtJoinPwd, txtJoinFirstName, txtJoinLastName;
    private RadioButton rdoMale, rdoFemale;
    private RadioGroup radioGroup;
    private String gender; // 성별 정보

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide(); // 액션바 숨기기

        tvJoinId = findViewById(R.id.tvJoinId);
        txtJoinId = findViewById(R.id.txtJoinId);
        txtJoinPwd = findViewById(R.id.txtJoinPwd);
        txtJoinFirstName = findViewById(R.id.txtJoinFirstName);
        txtJoinLastName = findViewById(R.id.txtJoinLastName);
        rdoMale = findViewById(R.id.rdoMale);
        rdoFemale = findViewById(R.id.rdoFemale);
        radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int resId) {

                if (resId == R.id.rdoMale) {
                    gender = "남";
                } else if (resId == R.id.rdoFemale) {
                    gender = "여";
                }
                Log.d(TAG, "onCheckedChanged() 호출됨 - gender : " + gender);
            }
        });


        Button btnJoinComplete = findViewById(R.id.btnJoinComplete);
        btnJoinComplete.setOnClickListener(view -> processJoin());

        Button btnJoinCancel = findViewById(R.id.btnJoinCancel);
        btnJoinCancel.setOnClickListener(view -> finish());

        txtJoinId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                //EditText editText = (EditText) view;

                if (!hasFocus) { // hasFocus == false
                    String id = txtJoinId.getText().toString().trim();
                    if (id.length() == 0) {
                        return;
                    }

                    checkDuplicateId(id); // 아이디 중복 체크하기
                 }
            } // onFocusChange
        });
    } // onCreate





    private void checkDuplicateId(String id) {

        UserService userService = RetrofitClient.getUserService();

        Call<UserOneResult> call = userService.getUser("one", id);

        call.enqueue(new Callback<UserOneResult>() {
            @Override
            public void onResponse(Call<UserOneResult> call, Response<UserOneResult> response) {
                if (response.isSuccessful()) {
                    UserOneResult userOneResult = response.body();
                    isIdDuplicated = userOneResult.isHasResult(); // 아이디 중복여부 확인

                    processResponseDupId(isIdDuplicated);
                }
            } // onResponse

            @Override
            public void onFailure(Call<UserOneResult> call, Throwable t) {
            }
        });

    } // checkDuplicateId


    private void processResponseDupId(boolean isIdDuplicated) {
        if (isIdDuplicated) {
            tvJoinId.setText("이미 사용중인 아이디 입니다.");
            tvJoinId.setTextColor(Color.RED);
        } else {
            tvJoinId.setText("사용가능한 아이디 입니다.");
            tvJoinId.setTextColor(Color.GREEN);
        }
    } // processResponseDupId



    private void processJoin() {
        String id = txtJoinId.getText().toString().trim(); // 아이디(userName) 가져오기
        String pwd = txtJoinPwd.getText().toString().trim();
        String firstName = txtJoinFirstName.getText().toString().trim();
        String lastName = txtJoinLastName.getText().toString().trim();

        // 라디오버튼 선택값으로 성별정보 구하기
        String gender = "";
        if (rdoMale.isChecked()) {
            gender = "남";
        } else if (rdoFemale.isChecked()) {
            gender = "여";
        }

        // 사용자 입력값 검증하기
        boolean isSafe = validateUserInput(id, pwd, firstName, lastName);
        if (!isSafe) { // isSafe == false
            return;
        }

        // 사용자 입력값을 User 객체로 변환하기
        User user = new User();
        user.setUserName(id);
        user.setPassword(pwd);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        // User 객체를 서버에 전송하기
        insertUser(user);
    } // processJoin


    private boolean validateUserInput(String id, String pwd, String firstName, String lastName) {
        // 입력값이 한개라도 없으면 메시지로 알리고 회원가입 취소하기
        if (id.length() == 0 || pwd.length() == 0 || firstName.length() == 0 || lastName.length() == 0) {
            Toast.makeText(this, "입력값이 없는 글상자가 있습니다.", Toast.LENGTH_LONG).show();
            return false;
        }

        // 아이디 중복여부 확인
        if (isIdDuplicated) { // isIdDuplicated == true
            Toast.makeText(this, "이미 사용중인 아이디 입니다.", Toast.LENGTH_LONG).show();
            txtJoinId.requestFocus();
            return false;
        }
        return true;
    } // validateUserInput


    private void insertUser(User user) {

        UserService userService = RetrofitClient.getUserService();

        Call<UserResult> call = userService.createUser(user);

        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse : 실패");
                    return;
                }

                UserResult userResult = response.body();
                Log.d(TAG, "onResponse : 성공, 응답결과 : " + userResult.toString());

                if (userResult.isSuccess()) {
                    showJoinCompleteMessage();
                }
            } // onResponse

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                Log.d(TAG, "onFailure : " + t.getMessage());
            }
        });

    } // insertUser


    private void showJoinCompleteMessage() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("회원가입 성공")
                .setMessage("회원가입이 완료되었습니다.")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("확인", (dialogInterface, i) -> finish())
                .create(); // 대화상자 빌더로 설정 및 객체생성

        alertDialog.show(); // 대화상자 띄우기
    } // showJoinCompleteMessage

}