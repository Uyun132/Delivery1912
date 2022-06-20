package com.example.delivery1912;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.example.delivery1912.model.User;
import com.example.delivery1912.adapter.MenuAdapter;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText passwordLog , emailLog;
    private Button btnLogin;
    private FirebaseAuth fAuth;
    private ProgressDialog progressDialog;
    private String id = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passwordLog = findViewById(R.id.passwordLogin);
        emailLog = findViewById(R.id.emailLogin);
        btnLogin = findViewById(R.id.btLogin);
        fAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menyambungkan ... ");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sPass = passwordLog.getText().toString().trim();
                String sEmail = emailLog.getText().toString().trim();

                if(TextUtils.isEmpty(sPass)){
                    passwordLog.setError("Password kosong");
                    return;
                } else if(TextUtils.isEmpty(sEmail)){
                    emailLog.setError("Email kosong");
                    return;
                } else {
                    fAuth.signInWithEmailAndPassword(sEmail,sPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Berhasil Login",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(),"Gagal Login",Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });
                }
            }
            });
        }

}