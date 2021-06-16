package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class ResetPassword extends AppCompatActivity {
TextView tv_username;
EditText pass_r, repass_r;
SqliteDB db;
Button resetPass;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[!@#&()–[{}]:;',?/*~$^+=<>])" +
                    "(?=.*[0-9])"+
                    "(?=.*[a-z])"+
                    "(?=.*[A-Z])"+
                    ".{10,}" +
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        tv_username=findViewById(R.id.rr_usernameId);
        String value_name = getIntent().getStringExtra("Username");
        tv_username.setText("Username: " + value_name);

        pass_r=findViewById(R.id.rr_passwordId);
        repass_r=findViewById(R.id.rrepasswordId);
        resetPass=findViewById(R.id.rr_buttonId);
        db = new SqliteDB(this);

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pass = pass_r.getText().toString();
                String repass = repass_r.getText().toString();

                if ( pass.equals("") || repass.equals(""))
                    Toast.makeText(ResetPassword.this, "Please complete the both fields! ", Toast.LENGTH_SHORT).show();
                else {
                    if ( pass.equals(repass)) {

                       if(PASSWORD_PATTERN.matcher(pass).matches()){

                            boolean update = db.updatePass(value_name, pass);
                            if (update) {
                                Toast.makeText(ResetPassword.this, "Password was modified successfully", Toast.LENGTH_SHORT).show();
                                pass_r.setText("");
                                repass_r.setText("");
                                Runnable r = new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i = new Intent (ResetPassword.this, LoginPage.class);
                                        startActivity(i);
                                    }
                                };
                                Handler handler = new Handler();
                                handler.postDelayed(r, 1000);

                            } else {
                                Toast.makeText(ResetPassword.this, "Password cannot be changed", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                           Toast.makeText(ResetPassword.this, "Password should have minimum 10 characters and should contain at least one uppercase letter, at least one lowercase letter, at least one digit and at least one special character !", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ResetPassword.this, "Passwords are not matching! ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}