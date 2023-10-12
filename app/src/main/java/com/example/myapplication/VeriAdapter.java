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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class VeriAdapter extends RecyclerView.Adapter<VeriAdapter.ViewHolder> {
    private ArrayList<String> veriler;
    private DatabaseHelper dbHelper;
    private VeriAdapter veriAdapter;
    private ArrayList<String> kayitlarList = new ArrayList<>();
    private Context context;
    private int selectedPosition = -1;

    public VeriAdapter(Context context, ArrayList<String> veriler) {
        this.context = context;
        this.veriler = veriler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String veri = veriler.get(position);
        holder.textView.setText(veri);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    selectedPosition = clickedPosition;
                    notifyDataSetChanged();
                    kayitSil();
                }
            }
        });

        // Highlight the selected item (for example, change its background color)
        if (position == selectedPosition) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
        } else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        }
    }

    @Override
    public int getItemCount() {
        return veriler.size();
    }

    public void kayitSil() {
        if (selectedPosition != -1) {
            String silinecekVeri = veriler.get(selectedPosition);
            dbHelper = new DatabaseHelper(context);
            dbHelper.kayitSil(silinecekVeri);

            veriler.remove(selectedPosition);
            selectedPosition = -1;
            notifyDataSetChanged();
            Toast.makeText(context, "Kayit silindi", Toast.LENGTH_SHORT).show();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.kayitEditText);
            button = itemView.findViewById(R.id.kayitsil);
        }
    }
}
