package com.example.myadd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewNotes;
    private FloatingActionButton buttonAddNote;
    private AddsAdapter addsAdapter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Для сохранения данных модели создаём объект класса через ViewModelProvider
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initViews();

        addsAdapter = new AddsAdapter();
        recyclerViewNotes.setAdapter(addsAdapter);

        viewModel.getAdds().observe(this, new Observer<List<Add>>() {
            @Override
            public void onChanged(List<Add> adds) {
                addsAdapter.setAdds(adds);
            }
        });

        //Удаление объявления свайпом
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT
        ) {
            @Override
            public boolean onMove(
                    @NonNull RecyclerView recyclerView,
                    @NonNull RecyclerView.ViewHolder viewHolder,
                    @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Add add = addsAdapter.getAdds().get(position);
                viewModel.remove(add);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddCreateActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        recyclerViewNotes = findViewById(R.id.recyclerViewAdds);
        buttonAddNote = findViewById(R.id.buttonAddNote);
    }
}