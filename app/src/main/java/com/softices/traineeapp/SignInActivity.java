package com.softices.traineeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {
    public Button btnLogin;
    TextView txtLogin;
    EditText edtEmail, edtPassword;
//    Toast toast = new Toast(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        btnLogin = (Button) findViewById(R.id.btn_login);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(i);
                final String email = edtEmail.getText().toString();
                final String pass = edtPassword.getText().toString();
                if (!isValidEmail(email)) {
                    edtEmail.setError("Please enter valid email address.");
                } else if (!isValidPassword(pass)) {
                    edtPassword.setError("Password must be more than 6 characters.");
                }
            }
        });
        txtLogin = (TextView) findViewById(R.id.txt_login);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        if (password != null && password.length() > 6) {
            return true;
        }
        return false;
    }

    private void savePreferences(boolean value) {
        android.content.SharedPreferences sharedPreferences = android.preference.PreferenceManager
                .getDefaultSharedPreferences(this);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("is_user_login", value);

        editor.commit();
    }

}
