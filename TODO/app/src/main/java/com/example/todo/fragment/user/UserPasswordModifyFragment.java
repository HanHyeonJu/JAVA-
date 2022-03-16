package com.example.todo.fragment.user;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.todo.MainActivity;
import com.example.todo.R;
import com.example.todo.domain.User;
import com.example.todo.domain.UserOneResult;
import com.example.todo.domain.UserResult;
import com.example.todo.retrofit.RetrofitClient;
import com.example.todo.retrofit.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 내 정보 비밀번호 수정 부분화면
public class UserPasswordModifyFragment extends Fragment {

    public static final String TAG = "UserPasswordModify";

    private String loginId;

    private EditText txtPwdOld, txtPwdNew, txtPwdNew2;
    private TextView tvPwdOldMsg, tvPwdNew2;
    private Button button;
    private boolean isOldPwdOk, isNewPwdOk;

    public UserPasswordModifyFragment(String loginId) {
        this.loginId = loginId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_password_modify, container, false);

        // 뷰 참조 가져오기
        txtPwdOld = view.findViewById(R.id.txtPwdOld);
        tvPwdOldMsg = view.findViewById(R.id.tvPwdOldMsg);

        txtPwdNew = view.findViewById(R.id.txtPwdNew);
        txtPwdNew2 = view.findViewById(R.id.txtPwdNew2);
        tvPwdNew2 = view.findViewById(R.id.tvPwdNew2);

        button = view.findViewById(R.id.btnUserPwdModify);
        button.setEnabled(false); // 버튼을 비활성화로 초기화

        // 포커스 이벤트 등록하기
        txtPwdOld.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus == true) return;

                requestGetUser(loginId);
            } // onFocusChange
        });


        // 포커스 이벤트 등록하기
        txtPwdNew2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                EditText editText = (EditText) textView;

                if (editText.getId() == R.id.txtPwdNew2) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        String pwdNew = txtPwdNew.getText().toString().trim();
                        String pwdNew2 = txtPwdNew2.getText().toString().trim();

                        if (pwdNew.equals(pwdNew2)) {
                            tvPwdNew2.setText("새 비밀번호가 모두 일치합니다.");
                            tvPwdNew2.setTextColor(Color.GREEN);
                            isNewPwdOk = true;
                        } else {
                            tvPwdNew2.setText("새 비밀번호가 모두 일치하지 않습니다.");
                            tvPwdNew2.setTextColor(Color.RED);
                            isNewPwdOk = false;
                        }

                        showButtonEnable();
                    }
                }
                return false;
            }
        });


        // 버튼에 클릭 이벤트 등록하기
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password = txtPwdNew.getText().toString().trim();

                requestUpdateUserPassword(loginId, password);
            }
        });

        return view;
    } // onCreateView



    private void requestUpdateUserPassword(String loginId, String password) {

        UserService userService = RetrofitClient.getUserService();

        Call<UserResult> call = userService.updateUserPassword("password", loginId, password);

        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                if (!response.isSuccessful()) return;

                UserResult userResult = response.body();
                if (userResult.isSuccess()) {
                    Toast.makeText(getContext(),"비밀번호 수정 완료!", Toast.LENGTH_LONG).show();
                } // if
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                Log.d(TAG, "onFailure 호출됨 : " + t.getMessage());
            }
        });
    } // requestUpdateUser



    private void requestGetUser(String id) {

        UserService userService = RetrofitClient.getUserService();

        Call<UserOneResult> call = userService.getUser("one", id);

        call.enqueue(new Callback<UserOneResult>() {
            @Override
            public void onResponse(Call<UserOneResult> call, Response<UserOneResult> response) {
                if (!response.isSuccessful()) return;

                UserOneResult userOneResult = response.body();
                User user = userOneResult.getUser();
                String password = user.getPassword();

                checkOldPassword(password);
            } // onResponse

            @Override
            public void onFailure(Call<UserOneResult> call, Throwable t) {
                Log.d(TAG, "onFailure 호출됨 : " + t.getMessage());
            }
        });

    } // requestGetUser


    private void checkOldPassword(String dbPassword) {
        String password = txtPwdOld.getText().toString().trim();

        if (password.equals(dbPassword)) {
            tvPwdOldMsg.setText("기존 비밀번호와 일치합니다.");
            tvPwdOldMsg.setTextColor(Color.GREEN);
            isOldPwdOk = true;
        } else {
            tvPwdOldMsg.setText("기존 비밀번호와 일치하지 않습니다.");
            tvPwdOldMsg.setTextColor(Color.RED);
            isOldPwdOk = false;
        }

        showButtonEnable();
    } // checkOldPassword


    private void showButtonEnable() {
        if (isOldPwdOk == true && isNewPwdOk == true) { // isOldPwdOk && isNewPwdOk
            button.setEnabled(true);
        } else {
            button.setEnabled(false);
        }
    } // showButtonEnable

}