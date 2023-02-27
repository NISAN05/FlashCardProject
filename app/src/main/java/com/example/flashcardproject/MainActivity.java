package com.example.flashcardproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ecouteVueButton();
        ecouteReponse1();
        ecouteReponse2();
        ecouteReponse3();

    }

    /**
     * cette methode ecoute le textview1
     */
    private void ecouteReponse1(){
        TextView Reponse1=findViewById(R.id.IdReponse1);
        Reponse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "MAUVAISE REPONSE!", Toast.LENGTH_SHORT).show();
                Reponse1.setBackgroundColor(Color.RED);
            }
        });
    }

    /**
     * cette methode ecoute le textview2
     */
    private void ecouteReponse2(){
        TextView Reponse2=findViewById(R.id.IdReponse2);
        Reponse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TextView)findViewById(R.id.IdQuestion)).setText("Bonne reponse, Felicitations!");
                Reponse2.setBackgroundColor(Color.GREEN);
            }
        });
    }

    /**
     * cette methode ecoute le textview 3
     */
    private void ecouteReponse3(){
        TextView Reponse3=findViewById(R.id.IdReponse3);
        Reponse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "MAUVAISE REPONSE!", Toast.LENGTH_SHORT).show();
                Reponse3.setBackgroundColor(Color.RED);
            }
        });
    }

    /**
     * cette methode ecoute le bouton de vue et fait apparaitre ou dispaeaitre les reponse
     */
    private void ecouteVueButton(){

        TextView REPONSE1=findViewById(R.id.IdReponse1);
        TextView REPONSE2=findViewById(R.id.IdReponse2);
        TextView REPONSE3=findViewById(R.id.IdReponse3);

        ImageButton ButtonVue = findViewById(R.id.IdVueButton);
        ButtonVue.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(REPONSE1.getVisibility()==View.VISIBLE){
                    REPONSE1.setVisibility(View.GONE);
                    REPONSE2.setVisibility(View.GONE);
                    REPONSE3.setVisibility(View.GONE);
                }else {
                    REPONSE1.setVisibility(View.VISIBLE);
                    REPONSE2.setVisibility(View.VISIBLE);
                    REPONSE3.setVisibility(View.VISIBLE);
                }


            }
        });

    }
}