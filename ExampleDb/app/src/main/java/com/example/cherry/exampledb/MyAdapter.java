package com.example.cherry.exampledb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cherry.exampledb.Rdatabase.Rtable;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context ct;
    List<Rtable> list;

    public MyAdapter(Context ct, List<Rtable> list) {
        this.ct = ct;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ct).inflate(R.layout.row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Rtable rtable = list.get(position);
        holder.tv.setText(list.get(position).getName());
        holder.tv1.setText(list.get(position).getRoll());
        holder.tv2.setText(list.get(position).getNumber());
        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.viewModel.delete(rtable);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv,tv1,tv2;
        ImageButton b,edit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            b = itemView.findViewById(R.id.iv);
            edit = itemView.findViewById(R.id.edit);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ViewGroup viewGroup = view.findViewById(R.id.content);
                    View v = LayoutInflater.from(ct).inflate(R.layout.update,viewGroup,false);
                    final EditText name = v.findViewById(R.id.name);
                    final EditText roll = v.findViewById(R.id.roll);
                    final EditText number = v.findViewById(R.id.number);
                    Button update = v.findViewById(R.id.update);
                    Button cancel = v.findViewById(R.id.cancel);
                    final BottomSheetDialog dialog = new BottomSheetDialog(ct);
                    dialog.setContentView(v);
                    dialog.setCancelable(false);

                    name.setText(tv.getText().toString());
                    roll.setText(tv1.getText().toString());
                    roll.setEnabled(false);
                    number.setText(tv2.getText().toString());

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Rtable rtable = new Rtable();
                            rtable.setName(name.getText().toString());
                            rtable.setRoll(roll.getText().toString());
                            rtable.setNumber(number.getText().toString());
                            MainActivity.viewModel.update(rtable);
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
        }
    }
}
