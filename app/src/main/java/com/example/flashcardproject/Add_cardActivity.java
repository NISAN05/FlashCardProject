package com.example.flashcardproject;

import static com.example.flashcardproject.MainActivity.Cle1Flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Add_cardActivity extends AppCompatActivity {
    private int id=-1;
    private AccesBase accesBase=new AccesBase(this);
    public static final String CleFlashcard="FlashCardProject_Flashcard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        ecoute_close_Button();
        ecoute_download_button();
        ecoute_ajouter_qcm();
        recupererdonnees();
    }

    /**
     *
     */
    private void recupererdonnees(){
        EditText Question=findViewById(R.id.IdtextQuestion);
        EditText Reponse=findViewById(R.id.IdtextReponse);
        EditText Reponse2=findViewById(R.id.IdtextReponse2);
        EditText Reponse3=findViewById(R.id.IdtextReponse3);
        CheckBox QCM=findViewById(R.id.Id_QCM);
        Bundle bundle=getIntent().getExtras();

        if (bundle!=null&&!bundle.isEmpty()){
            Flashcard flashcard=(Flashcard) bundle.getSerializable(Cle1Flashcard);
            QCM.setChecked(true);
            Question.setText(flashcard.getQuestion());
            Reponse.setText(flashcard.getAnswer());
            Reponse2.setText(flashcard.getWronganswer1());
            Reponse3.setText(flashcard.getWronganswer2());
            id=flashcard.getId();
        }
    }

    /**
     *
     */
    private void ecoute_ajouter_qcm(){
        CheckBox ajouterqcm=findViewById(R.id.Id_QCM);
        EditText reponse2=findViewById(R.id.IdtextReponse2);
        EditText reponse3=findViewById(R.id.IdtextReponse3);
        ajouterqcm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    reponse2.setVisibility(View.VISIBLE);
                    reponse3.setVisibility(View.VISIBLE);
                }else{
                    reponse2.setVisibility(View.INVISIBLE);
                    reponse3.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    /**
     *
     */
    private void ecoute_download_button(){
        ImageView DownloadButton=findViewById(R.id.download_button);
        DownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enregistrementDansBd();

            }
        });
    }

    private void transmission(Flashcard flashcard){
        Intent intent = new Intent(Add_cardActivity.this, MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable(CleFlashcard,flashcard);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void enregistrementDansBd(){
        Flashcard flashcard=null;
        CheckBox QCM=findViewById(R.id.Id_QCM);
        EditText Question=findViewById(R.id.IdtextQuestion);
        EditText Reponse=findViewById(R.id.IdtextReponse);
        EditText Reponse2=findViewById(R.id.IdtextReponse2);
        EditText Reponse3=findViewById(R.id.IdtextReponse3);
        String TextQuestion=Question.getText().toString();
        String TextReponse=Reponse.getText().toString();
        String TextReponse2=Reponse2.getText().toString();
        String TextReponse3=Reponse3.getText().toString();

        if (QCM.isChecked()){

            if ((TextQuestion!=null&&!TextQuestion.trim().isEmpty()) &&(TextReponse!=null&&!TextReponse.trim().isEmpty())
                    &&(TextReponse2!=null&&!TextReponse2.trim().isEmpty()) &&(TextReponse3!=null&&!TextReponse3.trim().isEmpty())){
               flashcard=new Flashcard(TextQuestion,TextReponse,TextReponse2,TextReponse3,id);
               if (id==-1) {
                   accesBase.ajout(flashcard);
                   Toast.makeText(Add_cardActivity.this, "Flashcard enregitrer", Toast.LENGTH_SHORT).show();
               }else{
                   accesBase.modifier(flashcard);
                  // id=-1;
                   Toast.makeText(Add_cardActivity.this, "Flashcard modifier", Toast.LENGTH_SHORT).show();
               }
               transmission(flashcard);
            }else {
                Toast.makeText(this, "veuiller remplir tout les champs svp", Toast.LENGTH_SHORT).show();
            }


        }else {
            if ((TextQuestion!=null&&!TextQuestion.trim().isEmpty()) &&(TextReponse!=null&&!TextReponse.trim().isEmpty())){
                flashcard=new Flashcard(TextQuestion,TextReponse,"","",id);
                if (id==-1) {
                    accesBase.ajout(flashcard);
                    Toast.makeText(Add_cardActivity.this, "Flashcard enregitrer", Toast.LENGTH_SHORT).show();
                }else {
                    accesBase.modifier(flashcard);
                    //id=-1;
                    Toast.makeText(Add_cardActivity.this, "Flashcard modifier", Toast.LENGTH_SHORT).show();
                }
                transmission(flashcard);

            }else {
                Toast.makeText(this, "veuiller remplir tout les champs svp", Toast.LENGTH_SHORT).show();
            }
        }


    }

    /**
     *
     */
    private void ecoute_close_Button(){
        ImageView CloseButton=findViewById(R.id.close_button);
        CloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=-1;
                finish();
            }
        });
    }
}