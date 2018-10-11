package com.softices.traineeapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    public Button btnSignup;
    TextView txtSignin, txtAnyAccount;
    EditText edtFirstName, edtMiddleName, edtLastName, edtSignUpEmail, edtSignUpMobile, edtPassword, edtConfirmPassword;

    private BroadcastReceiver mBtrInfRcv = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 1);
            txtAnyAccount.setText("Battery Level:" + Integer.toString(level) + "%");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();

        registerReceiver(mBtrInfRcv, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Success .....", Toast.LENGTH_SHORT).show();
                final String first = edtFirstName.getText().toString();
                final String middle = edtMiddleName.getText().toString();
                final String last = edtLastName.getText().toString();
                final String mobile = edtSignUpMobile.getText().toString();
                final String email = edtSignUpEmail.getText().toString();
                final String pass = edtPassword.getText().toString();
                final String confirm = edtConfirmPassword.getText().toString();
                if (!isValidfirstname(first)) {
                    edtFirstName.setError("Please enter your first name.");
                } else if (!isValidmiddlename(middle)) {
                    edtMiddleName.setError("Please enter your middle name.");
                } else if (!isValidlastname(last)) {
                    edtLastName.setError("Please enter your last name.");
                } else if (!isValidsignupmobile(mobile)) {
                    edtSignUpMobile.setError("Please enter your correct mobile number.");
                } else if (!isValidEmail(email)) {
                    edtSignUpEmail.setError("Please enter your Email");
                } else if (!isValidPassword(pass)) {
                    edtPassword.setError("Enter your password in more than 6 character.");
                } else if (!isValidConfirmPassword(confirm)) {
                    edtConfirmPassword.setError("Again enter the same password to confirm.");
                } else if (edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString())) {
                } else {
                    Toast.makeText(getApplicationContext(), "password not same", Toast.LENGTH_LONG).show();
                }
            }
        });
        edtFirstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b)
                    Toast.makeText(getApplicationContext(), "Success .....", Toast.LENGTH_SHORT).show();
            }
        });
        txtSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });
    }

    private boolean isValidfirstname(String first) {
        if (first != null && first.length() > 1) {
            return true;
        }
        return false;
    }

    private boolean isValidmiddlename(String middle) {
        if (middle != null && middle.length() > 1) {
            return true;
        }
        return false;
    }

    private boolean isValidlastname(String last) {
        if (last != null && last.length() > 1) {
            return true;
        }
        return false;
    }

    private boolean isValidsignupmobile(String mobile) {
        if (mobile != null && mobile.length() == 11) {
            return true;
        }
     return false;
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        if (password != null && password.length() < 10) {
            return true;
        }
        return false;
    }

    private boolean isValidConfirmPassword(String confirm) {
        if (confirm != null && confirm.length() < 10) {
            return true;
        }
        return false;
    }

    private void init() {
        txtSignin = (TextView) findViewById(R.id.txt_signin);
        txtAnyAccount = (TextView) findViewById(R.id.txt_any_account);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        edtFirstName = (EditText) findViewById(R.id.edt_first_name);
        edtMiddleName = (EditText) findViewById(R.id.edt_middle_name);
        edtLastName = (EditText) findViewById(R.id.edt_last_name);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        edtConfirmPassword = (EditText) findViewById(R.id.edt_confirm_password);
        edtSignUpMobile = (EditText) findViewById(R.id.edt_signup_mobile);
        edtSignUpEmail = (EditText) findViewById(R.id.edt_signup_email);
    }
}


