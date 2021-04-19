package com.example.bookmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterPage extends AppCompatActivity {
EditText name, email_registration, username_registration, password_registration, repassword;
Button signup;
TextView signin, backtologin;
//declara un obiect de tip clasa SqliteDB
SqliteDB DB;

    private static final Pattern PASSWORD_PATTERN1 =
            Pattern.compile("^" +
                    "(?=.*[!@#&()–[{}]:;',?/*~$^+=<>])" +     // at least 1 special character
                    "(?=.*[0-9])"+
                    "(?=.*[a-z])"+       // at least one lowercase letter
                    "(?=.*[A-Z])"+        // // at least one uppercase letter
                    ".{10,}" +                // at least 10 characters
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        name = (EditText)findViewById(R.id.r_nameId);
       // email_registration = (EditText)findViewById(R.id.r_emailId);
        username_registration= (EditText)findViewById(R.id.r_usernameId);
        password_registration = (EditText) findViewById(R.id.r_passwordId);
        repassword = (EditText) findViewById(R.id.repasswordId);
        signup = (Button) findViewById(R.id.r_buttonId);
        signin = findViewById(R.id.backtoLoginId);
        //initializare baza de date
        DB =  new SqliteDB(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
String user = username_registration.getText().toString();
String pass = password_registration.getText().toString();
String repass = repassword.getText().toString();

if(user.equals(" ")||pass.equals("")||repass.equals(""))
    Toast.makeText( RegisterPage.this, "Please complete all fields! ", Toast.LENGTH_SHORT).show();
else {
    if(pass.equals(repass)){

        if(PASSWORD_PATTERN1.matcher(pass).matches()){
        boolean checkuser = DB.checkusername(user);
        if(checkuser==false){
            boolean insert = DB.insertData(user, pass);
            if(insert == true){
               Toast.makeText(RegisterPage.this, "User was added successfully",Toast.LENGTH_SHORT).show();
                name.setText("");
                username_registration.setText("");
                password_registration.setText("");
                repassword.setText("");
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent (RegisterPage.this, LoginPage.class);
                        startActivity(i);
                    }
                };
                Handler handler = new Handler();
                handler.postDelayed(r, 1000);
            }
            else {
                Toast.makeText(RegisterPage.this, "User cannot be added", Toast.LENGTH_SHORT).show();
            }
            }
            else{
                Toast.makeText(RegisterPage.this, "User already exists",Toast.LENGTH_SHORT).show();
            }
        } else{
        Toast.makeText(RegisterPage.this, "Password should have minimum 10 characters and should contain at least one uppercase letter, at least one lowercase letter, at least one digit and at least one special character !",Toast.LENGTH_SHORT).show();
    }
    } else{
        Toast.makeText(RegisterPage.this, "Password is not matching!",Toast.LENGTH_SHORT).show();
    }
    }
}

        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backtoLoginPage();
            }
        });
backtologin=findViewById(R.id.backtoLoginId);
        SpannableString backLogin= new SpannableString("If you already have an account, please ga to login page.");
        ClickableSpan clickable2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(RegisterPage.this, LoginPage.class));
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        backLogin.setSpan(clickable2, 45, 55, Spanned.SPAN_INCLUSIVE_INCLUSIVE );
        backtologin.setText(backLogin);
        backtologin.setMovementMethod(LinkMovementMethod.getInstance());
    }
    public void backtoLoginPage(){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
}