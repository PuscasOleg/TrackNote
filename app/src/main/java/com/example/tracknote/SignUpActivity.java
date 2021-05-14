package com.example.tracknote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tracknote.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class SignUpActivity extends AppCompatActivity {


    private Button signUp, signIn;
    private EditText fullName, password,confirmPassword ,email;
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        signUp = findViewById(R.id.SignUpButton);
        fullName = findViewById(R.id.FullNameText);
        password = findViewById(R.id.PasswordText);
        confirmPassword=findViewById(R.id.ConfPasswordText);
        email = findViewById(R.id.Emailtext);
        auth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Регистрация пользователя
                String userFullName = fullName.getText().toString().trim();
                String userPassword = password.getText().toString().trim();
                String userEmail = email.getText().toString().trim();
                String useConfirmPassword=confirmPassword.getText().toString().trim();


                if (userFullName.isEmpty()) {
                    fullName.setError("Full Name is  required");
                    fullName.requestFocus();
                    return;

                }
                if (userEmail.isEmpty()) {
                    email.setError("Email is required");
                    email.requestFocus();
                    return;
                }
                if(userPassword.isEmpty()){
                    password.setError("Password is required");
                    password.requestFocus();
                    return;

                }
                if(useConfirmPassword.isEmpty()){
                    confirmPassword.setError("Confirm Password is required ");
                    confirmPassword.requestFocus();
                    return;
                }
                if(!userPassword.equals(useConfirmPassword)){
                    confirmPassword.setError("Different Password");
                    confirmPassword.requestFocus();
                    return;
                }
                if(userPassword.length()<6){
                    password.setError("Min password length should be 6 characters! ");
                    password.requestFocus();
                    return;
                }

              //Custom Auth User
                auth.createUserWithEmailAndPassword(userEmail,userPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                 if(task.isSuccessful()){
                                     User user= new  User(userFullName,userEmail,userPassword);

                                     FirebaseDatabase.getInstance().getReference("Users")
                                             .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                             .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                         @Override
                                         public void onComplete(@NonNull @NotNull Task<Void> task) {
                                             if(task.isSuccessful()){
                                                 Toast.makeText(SignUpActivity.this,"User Account Created",Toast.LENGTH_LONG).show();

                                             }else{
                                                 Toast.makeText(SignUpActivity.this,"Failure",Toast.LENGTH_LONG).show();
                                             }
                                         }
                                     });
                                 }else{
                                     Toast.makeText(SignUpActivity.this,"Failure",Toast.LENGTH_LONG).show();
                                 }
                            }
                        });

                  //Auth  User  with password and email only
//                auth.createUserWithEmailAndPassword(userEmail, userPassword)
//                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                            @Override
//                            public void onSuccess(AuthResult authResult) {
//                                Toast.makeText(SignUpActivity.this, "User Account Created", Toast.LENGTH_SHORT).show();
//
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull @NotNull Exception e) {
//                                Toast.makeText(SignUpActivity.this, "Failure" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
            }
        });




    }


    public void OpenSignIn(View view) {
        Intent SingIn = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(SingIn);
    }


}



