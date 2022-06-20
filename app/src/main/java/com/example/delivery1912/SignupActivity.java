package com.example.delivery1912;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {


    private EditText nameUs,emailUs,teleponUs,passwordUs;
    private Button btnSignup;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth fAuth;
    private ProgressDialog progressDialog;
    private String id ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameUs = findViewById(R.id.username);
        emailUs = findViewById(R.id.email);
        passwordUs = findViewById(R.id.password);
        teleponUs = findViewById(R.id.telepon);
        btnSignup = findViewById(R.id.btSignup);

        fAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menyimpan ... ");

        if(fAuth.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        btnSignup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String Email = emailUs.getText().toString().trim();
                 String Pass = passwordUs.getText().toString().trim();

                 if(TextUtils.isEmpty(Email)){
                     emailUs.setError("Email harus diisi");
                     return;
                 }if(TextUtils.isEmpty(Pass)){
                     passwordUs.setError("Password harus diisi");
                     return;
                 }
                 progressDialog.show();
                 saveData(nameUs.getText().toString(),Email,Pass,teleponUs.getText().toString());
                 fAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){
                             Toast.makeText(getApplicationContext(),"Berhasil menyimpan",Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                             startActivity(intent);
                         }else {
                             Toast.makeText(getApplicationContext(),"Gagal",Toast.LENGTH_SHORT).show();
                             progressDialog.dismiss();
                         }
                     }
                 });
             }
         });
    }
    private void saveData(String name, String email,String password, String telepon) {
        Map<String,Object> user = new HashMap<>();
        user.put("name",name);
        user.put("email",email);
        user.put("password",password);
        user.put("telepon",telepon);
        //jika id kosong maka akan edit data
//        if(id!=null){
//            //kodingan untuk edit data firestore dengan mengambil id
//            db.collection("users").document(id)
//                    .set(user)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful()){
//                                Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_SHORT).show();
//                                finish();
//                            }else {
//                                Toast.makeText(getApplicationContext(),"Gagal",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//        }

        //koding untuk menambahkan data dengn .add
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
    }
}