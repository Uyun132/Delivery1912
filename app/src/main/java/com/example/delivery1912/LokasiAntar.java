package com.example.delivery1912;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LokasiAntar extends AppCompatActivity {
    private Spinner gedung,lantai;
    private Button btPesan;
    private EditText txRuangan;

    private String Nama,HasilJumlah,Jumlah,Desk;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi_antar);

        progressDialog = new ProgressDialog(LokasiAntar.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menyimpan ... ");

        txRuangan = findViewById(R.id.edRuangan);

        //ISI SPINNER{
        gedung = findViewById(R.id.spnGedung);
        String[] itemsGedung = new String[]{"A","B","C","D","E","F","G"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsGedung);
        gedung.setAdapter(adapter);
        //ambil value dari spinner
        String sGedung = gedung.getSelectedItem().toString();
        lantai = findViewById(R.id.spnLantai);
        String[] itemsLantai = new String[]{"1","2","3","4","5"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsLantai);
        lantai.setAdapter(adapter1);
        String sLantai = lantai.getSelectedItem().toString();


        Intent intent = getIntent();
        if(intent!=null){
            Nama = intent.getStringExtra("nama");
            HasilJumlah = intent.getStringExtra("hasilJumlah");
            Jumlah = intent.getStringExtra("jumlah");
            Desk = intent.getStringExtra("desk");
        }

        btPesan = findViewById(R.id.btnPesan);
        btPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ketika tombol pesan ditekan akan terjadi beberapa hal :
                // - Mengambil data User untuk diambil data nomor teleponya
                // - Data yg masuk database KERANJANG : Nama Pesanan, Harga Total, Jumlah, Nama Customer, Nomor Telepon Customer, Gedung, Lantai, Ruangan
                // itu semua nanti masuk database KERANJANG dan ditampilkan di Recipt pada applikasi Kasir

                saveData(Nama,HasilJumlah,Jumlah,sGedung,sLantai,txRuangan.getText().toString());



            }
        });


    }

    private void saveData(String pName, String hasilJumlah,String pJumlah, String gedung, String lantai, String ruangan) {
        Map<String,Object> user = new HashMap<>();
        user.put("namaProduk",pName);
        user.put("hasilJumlah",hasilJumlah);
        user.put("jumlah",pJumlah);
        user.put("gedung",gedung);
        user.put("lantai",lantai);
        user.put("ruangan",ruangan);
                //koding untuk menambahkan data dengn .add
        db.collection("keranjang")
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