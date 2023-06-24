package com.example.myadd;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AddDao {
    @Query("SELECT * FROM adds")
    LiveData<List<Add>> getAdds();

    @Insert
    void add(Add add);

    @Query("DELETE FROM adds WHERE id = :id")
    void remove(int id);
}
