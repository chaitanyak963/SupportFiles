package com.example.cherry.exampledb.Rdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    MyRepository repository;
    LiveData<List<Rtable>> getAllData;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new MyRepository(application);
        getAllData = repository.readAllData();

    }

    /*This is for Insert Method*/
    public void insert(Rtable Rtable){
        repository.insert(Rtable);
    }

    /*This is for Update Method*/
    public void update(Rtable Rtable){
        repository.update(Rtable);
    }

    /*This is for delete method*/
    public void delete(Rtable Rtable){
        repository.delete(Rtable);
    }

    /*This is for read Data method*/
    public LiveData<List<Rtable>> readData(){
        return getAllData;
    }

}

