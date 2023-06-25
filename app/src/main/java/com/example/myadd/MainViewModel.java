package com.example.myadd;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private AddDatabase addDatabase;

    public MainViewModel(@NonNull Application application) {
        super(application);
        addDatabase = AddDatabase.getInstance(application);
    }

    public LiveData<List<Add>> getAdds() {
        return addDatabase.addsDao().getAdds();
    }

    public void remove(Add add){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                addDatabase.addsDao().remove(add.getId());
            }
        });
        thread.start();
    }
}
