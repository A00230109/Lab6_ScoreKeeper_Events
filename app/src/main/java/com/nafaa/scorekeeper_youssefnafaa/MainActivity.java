package com.nafaa.scorekeeper_youssefnafaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT_SCORE1  = "textScore1";
    public static final String TEXT_SCORE2  = "textScore2";
    public static final String RADIO_BUTTON1 = "radidButton1";
    public static final String RADIO_BUTTON2 = "radidButton2";
    public static final String RADIO_BUTTON3 = "radidButton3";
    public static final String RADIO_BUTTON4 = "radidButton4";
    public static final String TEXT_NAME = "textName";

    private int score1Saved;
    private int score2Saved;
    private boolean radioButton1Saved;
    private boolean radioButton2Saved;
    private boolean radioButton3Saved;
    private boolean radioButton4Saved;
    private String textName, textNameFromSettings;


    Toolbar toolbar;
    Button btnLightT1, btnLightT2, btnPlus, btnMinus;
    int tagImageLight1, tagImageLight2, addToScore;
    Boolean lightT1On, lightT2On;
    RadioGroup rdGroup;
    RadioButton rdBtn1, rdBtn2, rdBtn3, rdBtn4;
    TextView score1, score2;
    TextView textGreetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textGreetings = findViewById(R.id.greetings);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnLightT1 = findViewById(R.id.btnLightT1);
        btnLightT2 = findViewById(R.id.btnLightT2);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        score1 = findViewById(R.id.score1);
        score2 = findViewById(R.id.score2);
        tagImageLight1 = R.drawable.lighton;
        tagImageLight2 = R.drawable.lightoff;

        btnLightT1.setTag(tagImageLight1);
        lightT1On = true;
        btnLightT2.setTag(tagImageLight2);
        lightT2On = false;
        rdGroup = findViewById(R.id.rdGroup);
        rdBtn1 = findViewById(R.id.rdBtn1);
        rdBtn2 = findViewById(R.id.rdBtn2);
        rdBtn3 = findViewById(R.id.rdBtn3);
        rdBtn4 = findViewById(R.id.rdBtn4);
        btnLightT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tagImageLight1 == R.drawable.lighton) {
                    btnLightT1.setBackgroundResource(R.drawable.lightoff);
                    lightT1On = false;
                    btnLightT2.setBackgroundResource(R.drawable.lighton);
                    lightT2On = true;
                    tagImageLight1 = R.drawable.lightoff;
                } else {
                    btnLightT1.setBackgroundResource(R.drawable.lighton);
                    lightT1On = true;
                    btnLightT2.setBackgroundResource(R.drawable.lightoff);
                    lightT2On = false;
                    tagImageLight1 = R.drawable.lighton;
                }
            }
        });

        btnLightT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tagImageLight2 == R.drawable.lightoff) {
                    btnLightT1.setBackgroundResource(R.drawable.lighton);
                    lightT1On = true;
                    btnLightT2.setBackgroundResource(R.drawable.lightoff);
                    lightT2On = false;
                    tagImageLight2 = R.drawable.lighton;
                } else {
                    btnLightT1.setBackgroundResource(R.drawable.lightoff);
                    lightT1On = false;
                    btnLightT2.setBackgroundResource(R.drawable.lighton);
                    lightT2On = true;
                    tagImageLight2 = R.drawable.lightoff;
                }
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int valFromScore1 = (Integer.parseInt(score1.getText().toString()));
                int valFromScore2 = (Integer.parseInt(score2.getText().toString()));
                if (lightT1On) {
                    addToScore = returnValCheckedRadioButton();
                    valFromScore1 = valFromScore1 + addToScore;
                    score1.setText(valFromScore1 + "");
                }
                if (lightT2On) {
                    addToScore = returnValCheckedRadioButton();
                    valFromScore2 = valFromScore2 + addToScore;
                    score2.setText(valFromScore2 + "");
                }
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                int valFromScore1 = (Integer.parseInt(score1.getText().toString()));
                int valFromScore2 = (Integer.parseInt(score2.getText().toString()));
                if (lightT1On) {
                    addToScore = returnValCheckedRadioButton();
                    valFromScore1 = valFromScore1 - addToScore;
                    if (valFromScore1 < 0) {
                        valFromScore1 = 0;
                    }
                    score1.setText(valFromScore1 + "");
                }
                if (lightT2On) {
                    addToScore = returnValCheckedRadioButton();
                    valFromScore2 = valFromScore2 - addToScore;
                    if (valFromScore2 < 0) {
                        valFromScore2 = 0;
                    }
                    score2.setText(valFromScore2 + "");
                }
            }
        });

        loadPreferences();
        updateViews();
        Intent intent = getIntent();
        textNameFromSettings = intent.getStringExtra(SettingsActivity.EXTRA_TEXT_NAME);
        if(textNameFromSettings != null)
            textGreetings.setText(textNameFromSettings);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_info: {
                openDialog();
                return true;
            }
            case R.id.item_settings: {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void openDialog(){
        NoteInfoDialog noteInfoDialog = new NoteInfoDialog();
        noteInfoDialog.show(getSupportFragmentManager(), "Info Dialog");
    }

    private int returnValCheckedRadioButton() {
        if (rdBtn1.isChecked()) {
            return 1;
        } else if (rdBtn2.isChecked()) {
            return 2;
        } else if (rdBtn3.isChecked()) {
            return 3;
        } else {
            return 6;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        savePreferences();
    }

    @Override
    protected void onResume() {
        super.onResume();
        savePreferences();
    }

    @Override
    protected void onDestroy() {
        savePreferences();
        super.onDestroy();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    public void savePreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(TEXT_SCORE1, Integer.parseInt(score1.getText().toString()));
        editor.putInt(TEXT_SCORE2, Integer.parseInt(score2.getText().toString()));
        editor.putString(TEXT_NAME, textGreetings.getText().toString());
        editor.putBoolean(RADIO_BUTTON1, rdBtn1.isChecked());
        editor.putBoolean(RADIO_BUTTON2, rdBtn2.isChecked());
        editor.putBoolean(RADIO_BUTTON3, rdBtn3.isChecked());
        editor.putBoolean(RADIO_BUTTON4, rdBtn4.isChecked());
        editor.apply();
    }

    public void loadPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        score1Saved = sharedPreferences.getInt(TEXT_SCORE1, 0);
        score2Saved = sharedPreferences.getInt(TEXT_SCORE2, 0);
        radioButton1Saved = sharedPreferences.getBoolean(RADIO_BUTTON1, true);
        radioButton2Saved = sharedPreferences.getBoolean(RADIO_BUTTON2, false);
        radioButton3Saved = sharedPreferences.getBoolean(RADIO_BUTTON3, false);
        radioButton4Saved = sharedPreferences.getBoolean(RADIO_BUTTON4, false);
        textName = sharedPreferences.getString(TEXT_NAME, "User");
    }

    public void updateViews(){
        score1.setText(score1Saved+"");
        score2.setText(score2Saved+"");
        rdBtn1.setChecked(radioButton1Saved);
        rdBtn2.setChecked(radioButton2Saved);
        rdBtn3.setChecked(radioButton3Saved);
        rdBtn4.setChecked(radioButton4Saved);
        textGreetings.setText(textName);

    }

}