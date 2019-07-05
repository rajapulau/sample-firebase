package com.example.samplefirebase;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText nim, nama, jurusan;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nim = findViewById(R.id.inputNim);
        nama = findViewById(R.id.inputNama);
        jurusan = findViewById(R.id.inputJurusan);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDatabaseFirebase();
            }
        });
    }

    private void saveDatabaseFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference getReference;

        String getNim = nim.getText().toString();
        String getNama = nama.getText().toString();
        String getJurusan = jurusan.getText().toString();

        getReference = database.getReference();

        if (getNim.isEmpty() || getNama.isEmpty() || getJurusan.isEmpty()) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT);
        } else {
            getReference.child("Admin").child("982374923864").child("Mahasiswa").push()
                    .setValue(new Mahasiswa(getNim, getNama, getJurusan))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            nim.setText("");
                            nama.setText("");
                            jurusan.setText("");
                            Toast.makeText(MainActivity.this, "Berhasil disimpan", Toast.LENGTH_SHORT);
                        }
                    });
        }
    }
}
