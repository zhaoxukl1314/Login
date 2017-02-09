package com.example.zhaoxukl1314.login;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private final static String TAG = "LaunchActivity";

    private State mCurrentState;
    private Button mLoginType;
    private EditText mTenant;
    private EditText mUserName;
    private EditText mPassword;
    private TextView mLoginTypeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoginType = (Button) findViewById(R.id.login_type_button);
        mTenant = (EditText) findViewById(R.id.login_tenant_name);
        mUserName = (EditText) findViewById(R.id.login_username);
        mPassword = (EditText) findViewById(R.id.login_password);
        mLoginTypeText = (TextView) findViewById(R.id.login_type_text);

        String loginType = mLoginType.getText().toString();
        Log.d(TAG,"zhaoxu loginType: " + loginType);
        if (TextUtils.equals(loginType, "用户登陆")) {
            mCurrentState = new UserLoginState();
        } else {
            mCurrentState = new TenantLoginState();
        }
        mLoginType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.equals(mLoginType.getText().toString(), "用户登陆")) {
                    mCurrentState = new UserLoginState();
                } else {
                    mCurrentState = new TenantLoginState();
                }

                mCurrentState.onLayoutChange();
            }
        });
    }

    private interface State {
        void onLayoutChange();
        void login();
    }

    private class UserLoginState implements State {

        @Override
        public void onLayoutChange() {
            mTenant.setVisibility(View.GONE);
            mLoginType.setText("企业租户登陆");
            mLoginTypeText.setText("企业租户登陆");
        }

        @Override
        public void login() {

        }
    }

    private class TenantLoginState implements State {

        @Override
        public void onLayoutChange() {
            mTenant.setVisibility(View.VISIBLE);
            mLoginType.setText("用户登陆");
            mLoginTypeText.setText("用户登陆");
        }

        @Override
        public void login() {

        }
    }
}

