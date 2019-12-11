package com.example.steffi;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OverrideDialog.confirmListener {

    public HashMap<String, Note> notes = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent curr_intent = getIntent();
        if (curr_intent.getExtras() != null) {
            notes = (HashMap<String, Note>) curr_intent.getSerializableExtra("notes");
            Toast.makeText(this, Integer.toString(notes.size()),
                    Toast.LENGTH_LONG).show();

        }

        FloatingActionButton new_note_button = findViewById(R.id.new_note);
        new_note_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notes.containsKey("Untitled")) {
                    OverrideDialog override_alert = new OverrideDialog();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    override_alert.show(ft, "dialog");
                }
                else {
                    Note new_note = new Note("Untitled", "", new ArrayList<String>());
                    Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                    intent.putExtra("notes", notes);
                    intent.putExtra("curr_note", new_note.getTitle());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
