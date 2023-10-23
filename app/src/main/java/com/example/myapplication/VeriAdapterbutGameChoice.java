package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class VeriAdapterbutGameChoice extends RecyclerView.Adapter<VeriAdapterbutGameChoice.ViewHolder> {

    private List<gameget> veriler;
    private DatabaseHelper dbHelper;
    private VeriAdapter veriAdapter;
    private List<String> kayitlarList = new ArrayList<>();
    private Context context;
    private int selectedPosition = -1;



    public VeriAdapterbutGameChoice(Context context, List<gameget> veriler) {
        this.context = context;
        this.veriler = veriler;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gamechoice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        gameget veri = veriler.get(position);
        holder.Title.setText(veri.getBaslik());
        holder.aciklama.setText(veri.getGametext());


    }

    @Override
    public int getItemCount() {
        return veriler.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foto; // ImageView olarak güncellendi
        TextView Title;
        TextView aciklama;

        public ViewHolder(View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.gamepicture);
            Title = itemView.findViewById(R.id.baslik);
            aciklama = itemView.findViewById(R.id.gametext);
        }
    }

}



