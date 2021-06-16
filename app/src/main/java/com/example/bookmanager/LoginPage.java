package com.example.bookmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
  Button loginBtn;
  EditText username, password;
  SqliteDB DB;
  TextView resetPass, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.usernameId);
        password = (EditText)findViewById(R.id.passwordId) ;
        loginBtn = (Button) findViewById(R.id.loginId);
        DB = new SqliteDB(this);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();



                if (user.equals(" ") || pass.equals(""))
                    Toast.makeText(LoginPage.this, "Please complete all the fields! ", Toast.LENGTH_SHORT).show();
                else {
                boolean checkuserpass = DB.checkusernamepassword(user, pass);
                if(checkuserpass==true){
                    Toast.makeText(LoginPage.this, "You are logged now. ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomePage.class);
                    intent.putExtra("Username", user);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginPage.this, "Invalid password! ", Toast.LENGTH_SHORT).show();

                    resetPass=findViewById(R.id.clicktoresetpass);
                    SpannableString clicktoreset= new SpannableString("Forgot your password? Please click here to reset a new password.");
                    ClickableSpan clickable = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View widget) {
                            String user = username.getText().toString();
                            Intent i= new Intent(LoginPage.this, ResetPassword.class);
                            i.putExtra("Username", user);
                            startActivity(i);


                        }

                        @Override
                        public void updateDrawState(@NonNull TextPaint ds) {
                            super.updateDrawState(ds);
                            ds.setUnderlineText(true);
                        }
                    };
                    clicktoreset.setSpan(clickable, 29, 39, Spanned.SPAN_INCLUSIVE_INCLUSIVE );
                    ForegroundColorSpan foregroundColorSpan= new ForegroundColorSpan(Color.MAGENTA);
                    clicktoreset.setSpan(foregroundColorSpan, 29, 39, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    resetPass.setText(clicktoreset);
                    resetPass.setMovementMethod(LinkMovementMethod.getInstance());}
                }
            }
        });




        register= findViewById(R.id.gotoregisterID);
        SpannableString clicktoregister= new SpannableString("Not registered? Create an account here");
        ClickableSpan clickable1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(LoginPage.this, RegisterPage.class));
            }

            @Override
            public void updateDrawState(@NonNull TextPaint dss) {
                super.updateDrawState(dss);
                dss.setUnderlineText(true);
            }
        };
        clicktoregister.setSpan(clickable1, 34, 38, Spanned.SPAN_INCLUSIVE_INCLUSIVE );
        ForegroundColorSpan foregroundColorSpan= new ForegroundColorSpan(Color.MAGENTA);
        clicktoregister.setSpan(foregroundColorSpan, 34, 38, Spanned.SPAN_INCLUSIVE_INCLUSIVE );
        register.setText(clicktoregister);
        register.setMovementMethod(LinkMovementMethod.getInstance());
    }



}