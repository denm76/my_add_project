package com.example.myadd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewNotes;
    private FloatingActionButton buttonAddNote;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddCreateActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    private void initViews(){
        recyclerViewNotes = findViewById(R.id.recyclerViewAdds);
        buttonAddNote = findViewById(R.id.buttonAddNote);
    }
}