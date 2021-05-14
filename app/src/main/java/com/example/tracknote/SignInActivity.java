package com.example.tracknote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class SignInActivity extends AppCompatActivity {

    private  Button signIn;
    private EditText email,password;
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signIn = findViewById(R.id.SignInButton);
        email = findViewById(R.id.Emailtext);
        password = findViewById(R.id.PasswordText);
        auth = FirebaseAuth.getInstance();


      signIn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              String userPassword = password.getText().toString().trim();
              String userEmail = email.getText().toString().trim();

              if(userEmail.isEmpty()){
                  email.setError("Email is required");
                  email.requestFocus();
                  return;
              }
              if(userPassword.isEmpty()){
                  password.setError("Password is required");
                  password.requestFocus();
                  return;

              }
              auth.signInWithEmailAndPassword(userEmail,userPassword)
                      .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                              if(task.isSuccessful()){

                                  FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                                  if(user.isEmailVerified()) {
                                      //Переход на  профиль пользователья
                                      Toast.makeText(SignInActivity.this, "Welcome User", Toast.LENGTH_LONG).show();
                                      OpenUserActivity();

                                  }else{
                                      user.sendEmailVerification();
                                      Toast.makeText(SignInActivity.this,"Check your email verify account",Toast.LENGTH_LONG).show();
                                  }

                              }else {
                                  Toast.makeText(SignInActivity.this,"Login Error!Please check your credentials",Toast.LENGTH_LONG).show();
                              }
                          }
                      });


//              auth.signInWithEmailAndPassword(userEmail,userPassword)
//                      .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                          @Override
//                          public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
//                              Toast.makeText(SignInActivity.this,"Welcome",Toast.LENGTH_LONG).show();
//                          }
//                      }).addOnFailureListener(new OnFailureListener() {
//                  @Override
//                  public void onFailure(@NonNull @NotNull Exception e) {
//                      Toast.makeText(SignInActivity.this,"Failure",Toast.LENGTH_LONG).show();
//                  }
//              });


          }
      });
    }


    private void OpenUserActivity() {
        Intent LogIn=new Intent(SignInActivity.this,Login_User_Activity.class);
        startActivity(LogIn);
    }

    public  void OpenSignUp(View view){
        Intent SingUp=new Intent(SignInActivity.this,SignUpActivity.class);
        startActivity(SingUp);
    }



    public  void OpenForgotPassword(View view){
        Intent ForgotPassword=new Intent(SignInActivity.this,ForgotPasswordActivity.class);
        startActivity(ForgotPassword);
    }



};

