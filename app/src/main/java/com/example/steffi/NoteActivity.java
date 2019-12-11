package com.example.steffi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;

public class NoteActivity extends AppCompatActivity implements OverrideDialog.confirmListener {

    HashMap<String, Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_page);
        final Intent curr_intent = getIntent();
        notes = (HashMap<String, Note>) curr_intent.getSerializableExtra("notes");
        final String note_title = curr_intent.getStringExtra("curr_note");
        final Note curr_note = notes.get(note_title);
        final TextInputEditText title_field = findViewById(R.id.title_field);
        final TextInputEditText note_field = findViewById(R.id.note_field);

        title_field.setText(note_title);



        title_field.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String curr_title = title_field.getText().toString();
                if (!hasFocus) {
                    if (notes.containsKey(curr_title)) {
                        OverrideDialog override_alert = new OverrideDialog();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        override_alert.show(ft, "dialog");
                        Toast.makeText(getApplicationContext(), Integer.toString(notes.size()),
                                Toast.LENGTH_LONG).show();



                    } else {
                        notes.remove(curr_title);
                        saveNote(curr_title, note_field.getText().toString(), new ArrayList<String>());
                        Toast.makeText(getApplicationContext(), Integer.toString(notes.size()),
                                Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
        note_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                saveNote(title_field.getText().toString(), note_field.getText().toString(),new ArrayList<String>());
                Toast.makeText(getApplicationContext(), Integer.toString(notes.size()),
                        Toast.LENGTH_LONG).show();

            }
        });


        FloatingActionButton save_button =  findViewById(R.id.save_button);
        save_button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteActivity.this, MainActivity.class);
                intent.putExtra("notes", notes);
                startActivity(intent);


            }
        }));

    }
    public void onConfirm(Boolean confirm){ ;
        final TextInputEditText title_field = findViewById(R.id.title_field);
        final TextInputEditText note_field = findViewById(R.id.note_field);
        if (confirm) {
            String curr_title = title_field.getText().toString();
            saveNote(curr_title, note_field.getText().toString(), new ArrayList<String>());
        }
    }

    public void saveNote(String title, String jotting, ArrayList<String> tags){
        Note new_note = new Note(title, jotting, tags);
        notes.put(title, new_note);
    }
}
