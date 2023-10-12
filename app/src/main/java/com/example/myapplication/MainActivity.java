package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private RecyclerView recyclerView;
    private VeriAdapter veriAdapter;
    private ArrayList<String> kayitlarList = new ArrayList<>();
    private static final int MAX_KAYIT_SAYISI = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        veriAdapter = new VeriAdapter(this, kayitlarList);
        recyclerView.setAdapter(veriAdapter);

        Button playButton = findViewById(R.id.play);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        final EditText titleEditText = findViewById(R.id.titleEditText);
        Button kaydetButton = findViewById(R.id.kayitbuton);

        kaydetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String metin = titleEditText.getText().toString().trim();

                if (metin.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Bu alan boş bırakılamaz", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (kayitSayisi() < MAX_KAYIT_SAYISI) {
                    dbHelper.veriEkle(metin);
                    titleEditText.setText("");
                    guncelleKayitlarList();
                } else {
                    Toast.makeText(MainActivity.this, "Maksimum " + MAX_KAYIT_SAYISI + " kayıt ekleyebilirsiniz", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int kayitSayisi() {
        return kayitlarList.size();
    }

    private void guncelleKayitlarList() {
        kayitlarList.clear();
        kayitlarList.addAll(dbHelper.getTumKayitlar());
        veriAdapter.notifyDataSetChanged();
    }
}
