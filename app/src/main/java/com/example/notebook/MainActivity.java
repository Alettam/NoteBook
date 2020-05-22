package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mInputNote;
    private Button mSaveBtn;
    private SharedPreferences myNoteSharedPrf;
    private static String NOTE_TEXT = "note_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        getDateFromSharedPrf();
    }

    private void getDateFromSharedPrf() {
        String noteText = myNoteSharedPrf.getString(NOTE_TEXT,"");
        mInputNote.setText(noteText);
    }

    private void initViews() {
        mInputNote = findViewById(R.id.inputNote);
        mSaveBtn = findViewById(R.id.saveBtn);

        myNoteSharedPrf = getSharedPreferences("MyNote", MODE_PRIVATE);

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor myEditor = myNoteSharedPrf.edit();
                String noteText = mInputNote.getText().toString();
                myEditor.putString(NOTE_TEXT,noteText);
                myEditor.apply();

                Toast.makeText(MainActivity.this,"Данные сохранены", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
