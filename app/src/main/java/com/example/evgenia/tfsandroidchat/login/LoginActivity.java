package com.example.evgenia.tfsandroidchat.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.evgenia.tfsandroidchat.NavigationActivity;
import com.example.evgenia.tfsandroidchat.R;
import com.example.evgenia.tfsandroidchat.base_contract.IMainContract;
import com.example.evgenia.tfsandroidchat.base_contract.PresenterCache;


/**
 * Created by Evgenia on 28.03.2017.
 */

public class LoginActivity extends AppCompatActivity
        implements ILoginContract.ILoginView{

    public static final String KEY_LOGIN = "key_login";
    public static final String KEY_PASSWORD = "key_password";
    private static final String RUN_PROSES = "run_proses";
    private static final String TAG = "LoginActivity";

    private EditText editTextLogin;
    private EditText editTextPassword;
    private ProgressBar progressBar;
    private Button button;

    private ILoginContract.ILoginPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        if(savedInstanceState != null){
            if(savedInstanceState.getBoolean(RUN_PROSES)){
                editTextLogin.setVisibility(View.GONE);
                editTextPassword.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);
            }else {
                editTextLogin.setVisibility(View.VISIBLE);
                editTextPassword.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                button.setVisibility(View.VISIBLE);
            }
        }

        initPresenter();


    }

    private void initViews(){
        editTextLogin = (EditText)findViewById(R.id.et_login);
        editTextPassword = (EditText)findViewById(R.id.et_password);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        button = (Button)findViewById(R.id.btn_login);
        button.setOnClickListener(view -> {
            confirmUser();
        });
    }

    private void initPresenter(){
        presenter = (ILoginContract.ILoginPresenter) PresenterCache.getInstance().getPresenter(LoginPresenter.key);
        if(presenter != null){
            presenter.attach(this);
            ((IMainContract.ICachedPresenter)presenter).restoreState();
        }else {
            presenter = new LoginPresenter();
            PresenterCache.getInstance().putPresenter((IMainContract.ICachedPresenter) presenter);
            presenter.attach(this);
        }
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        clearAlViews();
        super.onDestroy();
    }

    private void clearAlViews(){
        editTextLogin = null;
        editTextPassword = null;
        progressBar = null;
        button = null;
    }

    private void confirmUser(){
        reverseVisibility();
        presenter.confirmUser(editTextLogin.getText().toString(), editTextPassword.getText().toString());
    }

    @Override
    public void userConfirmed(boolean isCorrect, String login, String password) {

        if(isCorrect){
            startActivity(login);
        }
    }

    private void startActivity(String login){
        Intent intent =new Intent(this, NavigationActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(KEY_LOGIN, login);

        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }


    private void reverseVisibility(){
        if(editTextLogin.getVisibility() == View.GONE || editTextLogin.getVisibility() == View.INVISIBLE){
            editTextLogin.setVisibility(View.VISIBLE);
        }else {
            editTextLogin.setVisibility(View.GONE);
        }

        if(editTextPassword.getVisibility() == View.GONE || editTextPassword.getVisibility() == View.INVISIBLE){
            editTextPassword.setVisibility(View.VISIBLE);
        }else {
            editTextPassword.setVisibility(View.GONE);
        }

        if(button.getVisibility() == View.GONE || button.getVisibility() == View.INVISIBLE){
            button.setVisibility(View.VISIBLE);
        }else {
            button.setVisibility(View.GONE);
        }

        if(progressBar.getVisibility() == View.GONE || progressBar.getVisibility() == View.INVISIBLE){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(RUN_PROSES, progressBar.getVisibility() == View.VISIBLE);
        super.onSaveInstanceState(outState);

    }
}
