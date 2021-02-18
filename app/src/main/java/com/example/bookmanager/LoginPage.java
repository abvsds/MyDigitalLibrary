package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
  Button loginBtn, goTo_register;
  EditText username, password;
  SqliteDB DB;

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
                }
                }
            }
        });




goTo_register = (Button) findViewById(R.id.gotoregisterID);
goTo_register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        openRegisterPage();
    }
});

    }
public void openRegisterPage(){
        Intent intent= new Intent (this, RegisterPage.class);
        startActivity(intent);
}


}