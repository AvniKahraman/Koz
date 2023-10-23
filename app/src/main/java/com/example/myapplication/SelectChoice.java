package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SelectChoice extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private Context context;
    private RecyclerView recyclerView;
    private VeriAdapterbutGameChoice veriAdapter;
    private List<gameget> tamam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectchoice);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        dbHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tamam = new ArrayList<>();

        String json = "[\n" +
                "  {\n" +
                "    \"id\": \"1\",\n" +
                "    \"gamepicture\": \"https://i.ytimg.com/vi/X8bod1bqOHg/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLDlyPC1x1sZQuMfaCQxjQRETUiF4w\",\n" +
                "    \"gametext\": \"18 Bin Kilometrede 1998 Model Fiat Tempra\",\n" +
                "    \"baslik\": \"jaho\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"2\",\n" +
                "    \"gamepicture\": \"https://i.ytimg.com/vi/JlvY3-3Gork/hqdefault.jpg?sqp=-oaymwE2CNACELwBSFXyq4qpAygIARUAAIhCGAFwAcABBvABAfgB_gmAAtAFigIMCAAQARhlIE8oRzAP&rs=AOn4CLBqpNSiWgcGapGKq8njI6ScVKsiZA\",\n" +
                "    \"baslik\": \"#ZaferMeclise mülteciler evine! | Prof. Dr. Ümit Özdağ | Zafer Partisi\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"3\",\n" +
                "    \"gamepicture\": \"https://i.ytimg.com/vi/l-IAz-s0rno/hqdefault.jpg?sqp=-oaymwEcCNACELwBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLC9Jnb3mjw_0mwllgZHBbZwiaXOjQ\",\n" +
                "    \"baslik\": \"DÜNYAYI ŞAŞIRTAN TÜRK PİLOT! \uD83D\uDE31- ARMA 3 w/@CaglarArtsLtd @Burhi\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"4\",\n" +
                "    \"gamepicture\": \"https://i.ytimg.com/vi/xNbge2jXUBk/hq720.jpg?sqp=-oaymwEcCOgCEMoBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLD38Z4trpg7yN_9dIhXJhNLQOd4oA\",\n" +
                "    \"baslik\": \"EXTRACTION 2 - ELEŞTİREL PARODİ\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"5\",\n" +
                "    \"gamepicture\": \"https://i.ytimg.com/vi/Z8eXaXoUJRQ/hqdefault.jpg?sqp=-oaymwEcCNACELwBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAG22yKPLpArsQvfHXlqaoS5FXy_A\",\n" +
                "    \"video_title\": \"Selena Gomez - Slow Down (Official)\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"6\",\n" +
                "    \"gamepicture\": \"https://i.ytimg.com/vi/fwfFSYH1330/hqdefault.jpg?sqp=-oaymwEcCNACELwBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLD53TBPRKIjOBlfQinUr_I37gtAXQ\",\n" +
                "    \"baslik\": \"NİNJANIN HÜNERLERİ! | Goose Goose Duck [YOUTUBE ÖZEL]\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"7\",\n" +
                "    \"gamepicture\": \"https://i.ytimg.com/vi/2nCs6ve4zw4/hqdefault.jpg?sqp=-oaymwEcCNACELwBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAE8UnIUC5LfoF7BjZDQsMsBk-lgw\",\n" +
                "    \"baslik\": \"SAVUNMASIZ ASTRAL! | Goose Goose Duck\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"8\",\n" +
                "    \"gamepicture\": \"https://i.ytimg.com/vi/942WjgyhF1s/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLBrV_OEHYObSayOv2G0K9mNQr5JPg\",\n" +
                "    \"baslik\": \"Leyla ile Mecnun 5. Bölüm\"\n" +
                "  }\n" +
                "]";

        tamam = parseChannel(json);

        veriAdapter = new VeriAdapterbutGameChoice(this, tamam);
        recyclerView.setAdapter(veriAdapter);

        ImageView backbutton = findViewById(R.id.backarrow);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private List<gameget> parseChannel(String json3) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<gameget>>() {}.getType();
        return gson.fromJson(json3, listType);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
