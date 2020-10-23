package com.nafaa.scorekeeper_youssefnafaa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnLightT1, btnLightT2, btnPlus, btnMinus;
    int tagImageLight1, tagImageLight2, addToScore;
    Boolean lightT1On, lightT2On;
    RadioGroup rdGroup;
    RadioButton rdBtn1, rdBtn2, rdBtn3, rdBtn4;
    TextView score1, score2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
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
        lightT2On  =false;
        rdGroup = findViewById(R.id.rdGroup);
        rdBtn1 = findViewById(R.id.rdBtn1);
        rdBtn2 = findViewById(R.id.rdBtn2);
        rdBtn3 = findViewById(R.id.rdBtn3);
        rdBtn4 = findViewById(R.id.rdBtn4);
        btnLightT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tagImageLight1 == R.drawable.lighton){
                    btnLightT1.setBackgroundResource(R.drawable.lightoff);
                    lightT1On = false;
                    btnLightT2.setBackgroundResource(R.drawable.lighton);
                    lightT2On = true;
                    tagImageLight1 = R.drawable.lightoff;
                }
                else{
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
                if(tagImageLight2 == R.drawable.lightoff){
                    btnLightT1.setBackgroundResource(R.drawable.lighton);
                    lightT1On = true;
                    btnLightT2.setBackgroundResource(R.drawable.lightoff);
                    lightT2On  =false;
                    tagImageLight2 = R.drawable.lighton;
                }
                else{
                    btnLightT1.setBackgroundResource(R.drawable.lightoff);
                    lightT1On  =false;
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
                int valFromScore1 =  (Integer.parseInt(score1.getText().toString())) ;
                int valFromScore2 =  (Integer.parseInt(score2.getText().toString())) ;
                if (lightT1On){
                    addToScore = returnValCheckedRadioButton();
                    valFromScore1 = valFromScore1 + addToScore;
                    score1.setText(valFromScore1+"");
                }
                if(lightT2On){
                    addToScore = returnValCheckedRadioButton();
                    valFromScore2 = valFromScore2 + addToScore;
                    score2.setText(valFromScore2+"");
                }
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                int valFromScore1 =  (Integer.parseInt(score1.getText().toString())) ;
                int valFromScore2 =  (Integer.parseInt(score2.getText().toString())) ;
                if (lightT1On){
                    addToScore = returnValCheckedRadioButton();
                    valFromScore1 = valFromScore1 - addToScore;
                    if(valFromScore1 <0){
                        valFromScore1 = 0;
                    }
                    score1.setText(valFromScore1+"");
                }
                if(lightT2On){
                    addToScore = returnValCheckedRadioButton();
                    valFromScore2 = valFromScore2 - addToScore;
                    if(valFromScore2<0){
                        valFromScore2 = 0;
                    }
                    score2.setText(valFromScore2+"");
                }
            }
        });

    }
    private int returnValCheckedRadioButton(){
        if(rdBtn1.isChecked()){
            return 1;
        }
        else if(rdBtn2.isChecked()){
            return 2;
        }
        else if(rdBtn3.isChecked()){
            return 3;
        }
        else{
            return 6;
        }
    }
}