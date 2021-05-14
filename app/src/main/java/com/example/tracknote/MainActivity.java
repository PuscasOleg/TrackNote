package com.example.tracknote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        auth=FirebaseAuth.getInstance();
//        db=FirebaseDatabase.getInstance();
//        users=db.getReference("Users");



        //Регистрация пользователя




//        Button SignUpBtn=findViewById(R.id.bntSignUp);
//        SignUpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent OpenSignUp=new Intent(MainActivity.this,SignUpActivity.class);
//                startActivity(OpenSignUp);
//            }
//        });
//
//        Button SignInBtn=findViewById(R.id.btnSignIn);
//        SignInBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent OpenSingIn=new Intent(MainActivity.this,SignInActivity.class);
//                startActivity(OpenSingIn);
//            }
//        });
    }

    public  void OpenSignUp(View view){
        Intent SingUp=new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(SingUp);
    }

    public  void OpenSignIn(View view){
        Intent SingIn=new Intent(MainActivity.this,SignInActivity.class);
        startActivity(SingIn);
    }




}