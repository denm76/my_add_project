package com.example.myadd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddCreateActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextPrice;

    private RadioButton radioButtonLowPriority;
    private RadioButton radioButtonMediumPriority;
    private RadioButton radioButtonHighPriority;

    private Button buttonSaveAdd;

    private AddCreateViewModel viewModel;

    public void initViews() {
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPrice = findViewById(R.id.editTextPrice);
        radioButtonLowPriority = findViewById(R.id.radioButtonLowPriority);
        radioButtonMediumPriority = findViewById(R.id.radioButtonMediumPriority);
        radioButtonHighPriority = findViewById(R.id.radioButtonHighPriority);
        buttonSaveAdd = findViewById(R.id.buttonSaveAdd);
    }

    private int getPriority() {
        int priority;
        if (radioButtonLowPriority.isChecked()) {
            priority = 0;
        } else if (radioButtonMediumPriority.isChecked()) {
            priority = 1;
        } else {
            priority = 2;
        }
        return priority;
    }

    private void saveAdd() {
        String textTitle = editTextTitle.getText().toString().trim();
        String textDescription = editTextDescription.getText().toString().trim();
        String textPrice = editTextPrice.getText().toString().trim();

        if (textTitle.isEmpty() || textDescription.isEmpty() || textPrice.isEmpty()) {
            Toast.makeText(
                    AddCreateActivity.this,
                    "Добавьте текст  заметки!",
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            int priority = getPriority();
            Add add = new Add(textTitle, textDescription, textPrice, priority);
            viewModel.saveAdd(add);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_create);

        initViews();

        viewModel = new ViewModelProvider(this).get(AddCreateViewModel.class);
        viewModel.getShouldCloseScreen().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldCloseScreen) {
                if (shouldCloseScreen) {
                    finish();
                }
            }
        });

        buttonSaveAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAdd();
            }
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AddCreateActivity.class);
    }
}