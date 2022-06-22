package com.example.delivery1912;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class DetailedMenu extends AppCompatActivity {

    private TextView Nama, Desk;
    private EditText Jumlah;
    private Button addCart;
    private String id ="";
    private String Harga;
    private Integer hasilJumlah;
    private Integer i = 1;
    private ImageButton Inc,Dec;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_menu);

        Nama = findViewById(R.id.detNama);
        Desk = findViewById(R.id.detDesk);
        Jumlah = findViewById(R.id.jumlah);
        Inc = findViewById(R.id.increaseJumlah);
        Dec = findViewById(R.id.decreaseJumlah);

        progressDialog = new ProgressDialog(DetailedMenu.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menyimpan ... ");


        Intent intent = getIntent();
        if(intent!=null){
            id = intent.getStringExtra("id");
            Nama.setText(intent.getStringExtra("name"));
            Harga = intent.getStringExtra("harga");
            Desk.setText(intent.getStringExtra("desk"));
        }

        addCart = findViewById(R.id.cartButton);
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ketika tombol Tambah Keranjang di Klik akan terjadi beberapa hal :
                // - Harga dan Jumlah akan dijadikan Integer, dan di kalikan, lalu valuenya masuk ke HasilJumlah
                // - Nama Produk, Hasil Jumlah, Jumlah, Desk

                Integer jumlah = Integer.parseInt(Jumlah.getText().toString());
                Integer harga = Integer.parseInt(Harga);
                hasilJumlah = harga * jumlah;
                //
                String sHasilJumlah = hasilJumlah.toString();
                String sNama = Nama.getText().toString();
                String sJumlah = jumlah.toString();
                String sDesk = Desk.getText().toString();

                Intent intent = new Intent(getApplicationContext(), LokasiAntar.class);

                intent.putExtra("nama",sNama);
                intent.putExtra("hasilJumlah",sHasilJumlah);
                intent.putExtra("jumlah",sJumlah);
                intent.putExtra("desk",sDesk);
                startActivity(intent);
                finish();

            }
        });

        Inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _stringVal;

                i = i + 1;
                _stringVal = String.valueOf(i);
                Jumlah.setText(_stringVal);
            }
        });

        Dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _stringVal;

                if (i > 0) {
                    i = i - 1;
                    _stringVal = String.valueOf(i);
                    Jumlah.setText(_stringVal);
                } else {

                }
            }
        });

    }
}