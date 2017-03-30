package com.example.evgenia.tfsandroidchat.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evgenia.tfsandroidchat.NavigationActivity;
import com.example.evgenia.tfsandroidchat.R;



/**
 * Created by Evgenia on 28.03.2017.
 */

public class LoginActivity extends AppCompatActivity {

    public static final String KEY_LOGIN = "key_login";
    private static final String KEY_PASSWORD = "key_password";
    private static final String KEY_ISLOGIN = "key_islogin";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = (Button)findViewById(R.id.btn_login);
        final EditText editTextLogin = (EditText)findViewById(R.id.et_login);
        final EditText editTextPassword = (EditText)findViewById(R.id.et_password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextLogin.getText().length() > 0 && editTextPassword.getText().length() > 0){
                    startActivity(editTextLogin.getText().toString());
                }else {
                    Toast.makeText(getApplicationContext(), "неверный пароль или логин", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void startActivity(String login){
        Intent intent =new Intent(getApplicationContext(), NavigationActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(KEY_LOGIN, login);

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
