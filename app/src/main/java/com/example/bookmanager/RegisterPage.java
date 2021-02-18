package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
EditText name, email_registration, username_registration, password_registration, repassword;
Button signup, signin;
//declara un obiect de tip clasa SqliteDB
SqliteDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        name = (EditText)findViewById(R.id.r_nameId);
        email_registration = (EditText)findViewById(R.id.r_emailId);
        username_registration= (EditText)findViewById(R.id.r_usernameId);
        password_registration = (EditText) findViewById(R.id.r_passwordId);
        repassword = (EditText) findViewById(R.id.repasswordId);
        signup = (Button) findViewById(R.id.r_buttonId);
        signin = (Button)findViewById(R.id.backtoLoginId);
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
        boolean checkuser = DB.checkusername(user);
        if(checkuser==false){
            boolean insert = DB.insertData(user, pass);
            if(insert == true){
               Toast.makeText(RegisterPage.this, "User was added successfully",Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(RegisterPage.this, "User cannot be added", Toast.LENGTH_SHORT).show();
            }
            }
            else{
                Toast.makeText(RegisterPage.this, "User already exists",Toast.LENGTH_SHORT).show();
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

    }
    public void backtoLoginPage(){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
}