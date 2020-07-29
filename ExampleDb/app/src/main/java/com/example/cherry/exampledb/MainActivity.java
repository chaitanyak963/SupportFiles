package com.example.cherry.exampledb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cherry.exampledb.Rdatabase.MyViewModel;
import com.example.cherry.exampledb.Rdatabase.RDatabase;
import com.example.cherry.exampledb.Rdatabase.Rtable;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText name,roll,number;
    RDatabase database;
    RecyclerView rv;
    static MyViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        rv = findViewById(R.id.rv);
        roll = findViewById(R.id.roll);
        number = findViewById(R.id.number);
        /*database = Room.databaseBuilder(MainActivity.this,RDatabase.class,"MYROOM").
                fallbackToDestructiveMigration().allowMainThreadQueries().build();
        List<Rtable> list = database.rdao().getall();*/
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.readData().observe(this, new Observer<List<Rtable>>() {
            @Override
            public void onChanged(List<Rtable> rtables) {
                MyAdapter adapter = new MyAdapter(MainActivity.this,rtables);
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
        });

    }

    public void submit(View view) {
        Rtable rtable = new Rtable();
        rtable.setName(name.getText().toString());
        rtable.setNumber(number.getText().toString());
        rtable.setRoll(roll.getText().toString());
        viewModel.insert(rtable);
    }
}