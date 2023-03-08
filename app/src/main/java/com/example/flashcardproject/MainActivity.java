package com.example.flashcardproject;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private String Vraie_reponse="Brasilia";

    public static final String Cle1TextQuestion="FlashCardProject1_Question";
    public static final String Cle1TextReponse="FlashCardProject1_Reponse";
    public static final String Cle1TextReponse2="FlashCardProject1_Reponse2";
    public static final String Cle1TextReponse3="FlashCardProject1_Reponse3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ecouteReponse1();
        ecouteReponse2();
        ecouteReponse3();
        ecouteQuestionReponse();
        ecoute_add_Button();
        recupererdonnees();
        ecouteVisibleButton();
        ecoute_edit_Button();

    }

    /**
     *
     */

    private void ecoute_edit_Button(){
        ImageView buttonedit=findViewById(R.id.edit_button);
        buttonedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView Question=findViewById(R.id.IdQuestion);
                TextView Reponse=findViewById(R.id.IdReponse);
                TextView Reponse1=findViewById(R.id.IdReponse1);
                TextView Reponse2=findViewById(R.id.IdReponse2);
                TextView Reponse3=findViewById(R.id.IdReponse3);
                Intent intent=new Intent(MainActivity.this,Add_cardActivity.class);
                Bundle bundle=new Bundle();
                String TextQuestion=Question.getText().toString();
                String TextReponse=Reponse.getText().toString();
                String TextReponse1=Reponse1.getText().toString();
                String TextReponse2=Reponse2.getText().toString();
                String TextReponse3=Reponse3.getText().toString();
                bundle.putString(Cle1TextQuestion,TextQuestion);
                bundle.putString(Cle1TextReponse,TextReponse);
                if (TextReponse1.isEmpty()){
                    bundle.putString(Cle1TextReponse2,TextReponse2);
                    bundle.putString(Cle1TextReponse3,TextReponse3);
                }else {
                    if (TextReponse1.equalsIgnoreCase(TextReponse)){
                        bundle.putString(Cle1TextReponse2,TextReponse2);
                        bundle.putString(Cle1TextReponse3,TextReponse3);
                    } else if (TextReponse2.equalsIgnoreCase(TextReponse)) {
                        bundle.putString(Cle1TextReponse2,TextReponse1);
                        bundle.putString(Cle1TextReponse3,TextReponse3);
                    }else {
                        bundle.putString(Cle1TextReponse2,TextReponse2);
                        bundle.putString(Cle1TextReponse3,TextReponse1);
                    }


                }

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

    /**
     *
     */
    private void ecoute_add_Button(){
        ImageView AddButton=findViewById(R.id.add_button);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Add_cardActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     *
     */
    private void ecouteQuestionReponse(){
        TextView Question=findViewById(R.id.IdQuestion);
        TextView Reponse=findViewById(R.id.IdReponse);
        Reponse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Reponse.setVisibility(View.INVISIBLE);
                Question.setVisibility(View.VISIBLE);
            }
        });

        Question.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Question.setVisibility(View.INVISIBLE);
                Reponse.setVisibility(View.VISIBLE);

            }
        });
    }

    /**
     *
     */
    private void recupererdonnees(){
        Random random=new Random();
        int nombrerandom=random.nextInt(3)+1;
        TextView Question=findViewById(R.id.IdQuestion);
        TextView Reponse=findViewById(R.id.IdReponse);
        TextView Reponse1=findViewById(R.id.IdReponse1);
        TextView Reponse2=findViewById(R.id.IdReponse2);
        TextView Reponse3=findViewById(R.id.IdReponse3);
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null&&!bundle.isEmpty()) {
            String TextQuestion = bundle.getString(Add_cardActivity.CleTextQuestion);
            String TextReponse = bundle.getString(Add_cardActivity.CleTextReponse);
            String TextReponse2 = bundle.getString(Add_cardActivity.CleTextReponse2);
            String TextReponse3 = bundle.getString(Add_cardActivity.CleTextReponse3);
            if ((TextQuestion!=null && !TextQuestion.trim().isEmpty())&&TextReponse!=null && !TextReponse.trim().isEmpty()) {

                Question.setText(TextQuestion);
                Reponse.setText(TextReponse);
                Vraie_reponse=TextReponse;
                if (TextReponse2!=null && !TextReponse2.trim().isEmpty()&&TextReponse3!=null && !TextReponse3.trim().isEmpty()){
                    switch (nombrerandom){
                        case 1:
                            Reponse1.setText(TextReponse);
                            Reponse2.setText(TextReponse2);
                            Reponse3.setText(TextReponse3);
                            break;
                        case 2:
                            Reponse1.setText(TextReponse2);
                            Reponse2.setText(TextReponse);
                            Reponse3.setText(TextReponse3);
                            break;
                        case 3:
                            Reponse1.setText(TextReponse3);
                            Reponse2.setText(TextReponse2);
                            Reponse3.setText(TextReponse);
                            break;
                    }
                }else{
                    Reponse1.setText("");
                    Reponse2.setText("");
                    Reponse3.setText("");
                }





            }
        }

    }

    /**
     * cette methode ecoute le textview1
     */
    private void ecouteReponse1(){
        TextView Reponse1=findViewById(R.id.IdReponse1);
        TextView Question=findViewById(R.id.IdQuestion);
        TextView Reponse=findViewById(R.id.IdReponse);
        Reponse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Reponse1.getText().toString().equalsIgnoreCase(Vraie_reponse)){
                    Question.setVisibility(View.INVISIBLE);
                    Reponse.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "BONNE REPONSE, FELICITATIONS!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "MAUVAISE REPONSE!", Toast.LENGTH_SHORT).show();
                    Reponse1.setVisibility(View.INVISIBLE);
                }

            }
        });
    }

    /**
     * cette methode ecoute le textview2
     */
    private void ecouteReponse2(){
        TextView Reponse2=findViewById(R.id.IdReponse2);
        TextView Question=findViewById(R.id.IdQuestion);
        TextView Reponse=findViewById(R.id.IdReponse);
        Reponse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Reponse2.getText().toString().equalsIgnoreCase(Vraie_reponse)){
                    Question.setVisibility(View.INVISIBLE);
                    Reponse.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "BONNE REPONSE, FELICITATIONS!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "MAUVAISE REPONSE!", Toast.LENGTH_SHORT).show();
                    Reponse2.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    /**
     * cette methode ecoute le textview 3
     */
    private void ecouteReponse3(){
        TextView Reponse3=findViewById(R.id.IdReponse3);
        TextView Question=findViewById(R.id.IdQuestion);
        TextView Reponse=findViewById(R.id.IdReponse);
        Reponse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Reponse3.getText().toString().equalsIgnoreCase(Vraie_reponse)){
                    Question.setVisibility(View.INVISIBLE);
                    Reponse.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "BONNE REPONSE, FELICITATIONS!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "MAUVAISE REPONSE!", Toast.LENGTH_SHORT).show();
                    Reponse3.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    /**
     * cette methode ecoute le bouton de vue et fait apparaitre ou dispaeaitre les reponse
     */
   private void ecouteVisibleButton(){

        TextView REPONSE1=findViewById(R.id.IdReponse1);
        TextView REPONSE2=findViewById(R.id.IdReponse2);
        TextView REPONSE3=findViewById(R.id.IdReponse3);

        ImageView ButtonVisible = findViewById(R.id.visible_button);
        ButtonVisible.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(REPONSE1.getVisibility()==View.VISIBLE&&REPONSE2.getVisibility()==View.VISIBLE&&REPONSE2.getVisibility()==View.VISIBLE){
                    REPONSE1.setVisibility(View.INVISIBLE);
                    REPONSE2.setVisibility(View.INVISIBLE);
                    REPONSE3.setVisibility(View.INVISIBLE);
                    ButtonVisible.setImageResource(R.drawable.icon_visible);
                }else {
                    if (REPONSE1.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this, "IL N'Y A PAS DE QUESTION A CHOIX MULTIPLE", Toast.LENGTH_SHORT).show();
                    }else {
                        REPONSE1.setVisibility(View.VISIBLE);
                        REPONSE2.setVisibility(View.VISIBLE);
                        REPONSE3.setVisibility(View.VISIBLE);
                        ButtonVisible.setImageResource(R.drawable.icon_invisible);
                    }

                }


            }
        });

    }
}