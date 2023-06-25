package com.example.myadd;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AddCreateViewModel extends AndroidViewModel {
    private AddDao addDao;
    private MutableLiveData<Boolean> shouldCloseScreen = new MutableLiveData<>();
    public LiveData<Boolean> getShouldCloseScreen() {return shouldCloseScreen;}

    public AddCreateViewModel(@NonNull Application application) {

        super(application);
        addDao = AddDatabase.getInstance(application).addsDao();
    }

    public void saveAdd(Add add){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                addDao.add(add);
                shouldCloseScreen.postValue(true);
            }
        });
        thread.start();
    }

}
