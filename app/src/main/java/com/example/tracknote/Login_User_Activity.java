package com.example.tracknote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.longrunning.WaitOperationRequest;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login_User_Activity extends AppCompatActivity {


    Button ADD;
    List<String> List1 = new ArrayList<>();
    List<String> List2 = new ArrayList<>();

    EditText StartLocation,FinalLocation,StartDate;

    FirebaseFirestore db=FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);


        ADD=findViewById(R.id.ADDbutton);
        StartLocation=findViewById(R.id.startLocationText);
        FinalLocation=findViewById(R.id.finalLocationText);
        StartDate=findViewById(R.id.StartDate);


        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String startLoc=StartLocation.getText().toString();
                String finalLoc=FinalLocation.getText().toString();
                String startDate=StartDate.getText().toString();

                Map<String,Object> data=new HashMap<>();
                data.put("Start Location",startLoc);
                data.put("Final Location",finalLoc);
                data.put("Start Date",startDate);

                db.collection("First Trip").document("User").set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();

                            }
                        });

            }
        });


        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


    }


//    public void sendMessage(View view) {
//
//        EditText StartMessage = (EditText) findViewById(R.id.startLocationText);
//        EditText FinalMessage = (EditText) findViewById(R.id.finalLocationText);
//        String StartOutput = StartMessage.getText().toString();
//        String FinalOutPut = FinalMessage.getText().toString();
//
//        List1.add(StartOutput);
//        List2.add(FinalOutPut);
//
//        TextView pole = (TextView) findViewById(R.id.StartOutputText);
//        TextView pole2 = (TextView) findViewById(R.id.FinalOutputText);
//
//        for (String str : List1) {
//            pole.append(str+"\n");
//
//        }
//        for (String str2 : List2) {
//            pole2.append(str2+"\n");
//
//        }
//
//
//    }


}