package com.example.flashcardproject;

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
    public static final String CleTextQuestion="FlashCardProject_Question";
    public static final String CleTextReponse="FlashCardProject_Reponse";
    public static final String CleTextReponse2="FlashCardProject_Reponse2";
    public static final String CleTextReponse3="FlashCardProject_Reponse3";

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
        String TextQuestion = bundle.getString(MainActivity.Cle1TextQuestion);
        String TextReponse = bundle.getString(MainActivity.Cle1TextReponse);
        String TextReponse2 = bundle.getString(MainActivity.Cle1TextReponse2);
        String TextReponse3 = bundle.getString(MainActivity.Cle1TextReponse3);
        QCM.setChecked(true);
        Question.setText(TextQuestion);
        Reponse.setText(TextReponse);
        Reponse2.setText(TextReponse2);
        Reponse3.setText(TextReponse3);
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
                charge_donnees();

            }
        });
    }

    /**
     *
     */
    private void charge_donnees(){

        CheckBox QCM=findViewById(R.id.Id_QCM);
        EditText Question=findViewById(R.id.IdtextQuestion);
        EditText Reponse=findViewById(R.id.IdtextReponse);
        EditText Reponse2=findViewById(R.id.IdtextReponse2);
        EditText Reponse3=findViewById(R.id.IdtextReponse3);
        Intent intent=new Intent(Add_cardActivity.this,MainActivity.class);
        Bundle bundle=new Bundle();
        String TextQuestion=Question.getText().toString();
        String TextReponse=Reponse.getText().toString();
        String TextReponse2=Reponse2.getText().toString();
        String TextReponse3=Reponse3.getText().toString();

        if (QCM.isChecked()){

            if ((TextQuestion!=null&&!TextQuestion.trim().isEmpty()) &&(TextReponse!=null&&!TextReponse.trim().isEmpty())
            &&(TextReponse2!=null&&!TextReponse2.trim().isEmpty()) &&(TextReponse3!=null&&!TextReponse3.trim().isEmpty())){
                bundle.putString(CleTextQuestion,TextQuestion);
                bundle.putString(CleTextReponse,TextReponse);
                bundle.putString(CleTextReponse2,TextReponse2);
                bundle.putString(CleTextReponse3,TextReponse3);
                intent.putExtras(bundle);
                startActivity(intent);

            }else {
                Toast.makeText(this, "veuiller remplir tout les champs svp", Toast.LENGTH_SHORT).show();
            }


        }else {
            if ((TextQuestion!=null&&!TextQuestion.trim().isEmpty()) &&(TextReponse!=null&&!TextReponse.trim().isEmpty())){
                bundle.putString(CleTextQuestion,TextQuestion);
                bundle.putString(CleTextReponse,TextReponse);
                intent.putExtras(bundle);
                startActivity(intent);

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
                finish();
            }
        });
    }
}