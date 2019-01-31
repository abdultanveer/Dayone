package com.example.admin.dayone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.admin.firstday.IAdd;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class FbLoginActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    private static final String EMAIL = "email";
    LoginButton loginButton;
    public static String TAG = FbLoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_login);

        callbackManager = CallbackManager.Factory.create();




        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
               AccessToken accessToken =  loginResult.getAccessToken();

                Log.i(TAG, "onSuccess:callbackManager access token "+accessToken.getToken());
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        AccessToken accessToken =  loginResult.getAccessToken();

                        Log.i(TAG, "onSuccess:LoginManager access token "+accessToken.getToken());

                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void handleClick(View view) {

        switch (view.getId()) {
            case R.id.boundServicebutton:
            //step 1
            Intent bindIntent = new Intent(FbLoginActivity.this, MyBoundService.class);
            bindService(bindIntent, serviceConnection, Context.BIND_AUTO_CREATE);
            break;
            case R.id.aidlbutton:
                Intent addIntent = new Intent();
                addIntent.setClassName("com.example.admin.firstday",
                        "com.example.admin.firstday.AddService");
                bindService(addIntent,addServiceConnection,BIND_AUTO_CREATE);
                break;
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder mBinder) {
            //step 3
            MyBoundService.LocalBinder localBinder = (MyBoundService.LocalBinder)mBinder;
            //here i am not creating an instance of the MyBoundService
            //MyBoundService mMyBoundService = new MyBoundService();
            //instead i am getting a running instance of MyBoundService
            MyBoundService myBoundService = localBinder.getService();
             int randomNo =   myBoundService.getRandomNo();
             //step 5
            Toast.makeText(FbLoginActivity.this, "no"+randomNo, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    IAdd remoteAddService = null;
    ServiceConnection addServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder addBinder) {
           remoteAddService = IAdd.Stub.asInterface((IBinder)addBinder);
            try {
               int result = remoteAddService.add(15,20);
                Toast.makeText(FbLoginActivity.this, "result = "+result, Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };



}
