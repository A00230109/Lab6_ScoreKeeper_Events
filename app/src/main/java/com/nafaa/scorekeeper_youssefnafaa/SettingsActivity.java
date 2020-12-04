package com.nafaa.scorekeeper_youssefnafaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT_NAME =
            "com.nafaa.scorekeeper_youssefnafaa.EXTRA_TEXT_NAME";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        Button btnApplySettings = findViewById(R.id.btn_apply_settings);
        final EditText editTextName = findViewById(R.id.edit_text_name);

        btnApplySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textName = editTextName.getText().toString();
                if(textName.trim().equals("")){
                    Toast.makeText(SettingsActivity.this, "Name is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                intent.putExtra(EXTRA_TEXT_NAME, textName);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
}


