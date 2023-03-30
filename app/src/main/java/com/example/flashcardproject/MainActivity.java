package com.example.flashcardproject;

import static com.example.flashcardproject.Add_cardActivity.CleFlashcard;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    List<Flashcard> flashcard=new ArrayList<>();
    private int Id;
    private AccesBase accesBase=new AccesBase(this);
    private String Vraie_reponse="";
    private boolean premierefois=true;
    public static final String Cle1Flashcard="FlashCardProject1_Flashcard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (premierefois){
            Id=0;
            recupererdonneesBd();
            afficheflashcard();
            premierefois=false;
        }else {


        }

        ecouteReponse1();
        ecouteReponse2();
        ecouteReponse3();
        ecouteQuestionReponse();
        ecoute_add_Button();
        ecouteVisibleButton();
        ecoute_edit_Button();
        ecoutearrowleft();
        ecoutearrowright();
        ecoute_delete_Button();
        recupererdonnees();
    }


    private void recupererdonnees(){

        Bundle bundle=getIntent().getExtras();

        if (bundle!=null&&!bundle.isEmpty()){
            Flashcard flashcard1=(Flashcard) bundle.getSerializable(CleFlashcard);
           if (flashcard1.getId()==-1){
               recupererdonneesBd();
               Id=flashcard.size()-1;
               afficheflashcard();
           }else {
               recupererdonneesBd();
               afficheflashcard();
           }

        }
    }


    private void ecoute_delete_Button(){
        ImageView buttondelete=findViewById(R.id.delete_button);
        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("voulez-vous vraiment supprimer ce flashcard?");
                builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        accesBase.delete(flashcard.get(Id).getId());
                        Toast.makeText(MainActivity.this, "Flashcard supprimer", Toast.LENGTH_SHORT).show();
                        if (flashcard.size()==1){
                            Id=0;
                        } else if (Id==flashcard.size()-1) {
                            Id=Id-1;
                        }else {
                            Id=Id+1;
                        }
                        recupererdonneesBd();
                        afficheflashcard();
                    }
                });
                builder.setNegativeButton("Non",null);
                builder.show();

            }
        });
    }

    /**
     *
     */

    private void ecoute_edit_Button(){
        ImageView buttonedit=findViewById(R.id.edit_button);
        buttonedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flashcard.size()!=0) {
                    Intent intent = new Intent(MainActivity.this, Add_cardActivity.class);
                    Flashcard flashcard1 = null;
                    flashcard1 = flashcard.get(Id);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable(Cle1Flashcard,flashcard1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

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
                overridePendingTransition(R.anim.left, R.anim.left);
            }
        });
    }

    /**
     *
     */
    private void ecouteQuestionReponse(){
        TextView Question=findViewById(R.id.IdQuestion);
        TextView Reponse=findViewById(R.id.IdReponse);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.flip);

        Reponse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Reponse.setVisibility(View.INVISIBLE);
                Question.setVisibility(View.VISIBLE);
                Reponse.startAnimation(animation1);
                animation1.setDuration(6000);
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

    private void ecoutearrowleft(){
        ImageView buttonarrowleft=findViewById(R.id.arrowleft_button);
        buttonarrowleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recupererdonneesBd();
                if (Id>0){
                    Id=Id-1;
                afficheflashcard();
                }
            }
        });
    }

    private void ecoutearrowright(){
        ImageView buttonarrowright=findViewById(R.id.arrowright_button);
        buttonarrowright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recupererdonneesBd();
                if (Id<flashcard.size()-1){
                    Id=Id+1;
                afficheflashcard();
                }
            }
        });
    }


    private  void recupererdonneesBd(){
        flashcard=accesBase.recuperer();
    }

    private  void afficheflashcard(){
        Random random=new Random();
        int nombrerandom=random.nextInt(3)+1;
        TextView Question=findViewById(R.id.IdQuestion);
        TextView Reponse=findViewById(R.id.IdReponse);
        TextView Reponse1=findViewById(R.id.IdReponse1);
        TextView Reponse2=findViewById(R.id.IdReponse2);
        TextView Reponse3=findViewById(R.id.IdReponse3);
        ImageView arrowleft=findViewById(R.id.arrowleft_button);
        ImageView arrowright=findViewById(R.id.arrowright_button);
        ImageView delete=findViewById(R.id.delete_button);
        ImageView ButtonVisible = findViewById(R.id.visible_button);
        overridePendingTransition(R.anim.left, R.anim.left);
        if (flashcard.size()!=0) {
            delete.setVisibility(View.VISIBLE);

            if (flashcard.size()==1){
                arrowleft.setVisibility(View.INVISIBLE);
                arrowright.setVisibility(View.INVISIBLE);
            } else if (Id==0){
                arrowleft.setVisibility(View.INVISIBLE);
                arrowright.setVisibility(View.VISIBLE);
            } else if (Id==flashcard.size()-1) {
                arrowleft.setVisibility(View.VISIBLE);
                arrowright.setVisibility(View.INVISIBLE);
            }else {
                arrowleft.setVisibility(View.VISIBLE);
                arrowright.setVisibility(View.VISIBLE);
            }
            Question.setText(flashcard.get(Id).getQuestion());
            Reponse.setText(flashcard.get(Id).getAnswer());
            Vraie_reponse=flashcard.get(Id).getAnswer();
            if (!flashcard.get(Id).getWronganswer1().equalsIgnoreCase("")&& !flashcard.get(Id).getWronganswer2().equalsIgnoreCase("")){
                switch (nombrerandom){
                    case 1:
                        Reponse1.setText(flashcard.get(Id).getAnswer());
                        Reponse2.setText(flashcard.get(Id).getWronganswer1());
                        Reponse3.setText(flashcard.get(Id).getWronganswer2());
                        break;
                    case 2:
                        Reponse1.setText(flashcard.get(Id).getWronganswer1());
                        Reponse2.setText(flashcard.get(Id).getAnswer());
                        Reponse3.setText(flashcard.get(Id).getWronganswer2());
                        break;
                    case 3:
                        Reponse1.setText(flashcard.get(Id).getWronganswer2());
                        Reponse2.setText(flashcard.get(Id).getWronganswer1());
                        Reponse3.setText(flashcard.get(Id).getAnswer());
                        break;
                }
            }else{
                Reponse1.setText("");
                Reponse2.setText("");
                Reponse3.setText("");
                Reponse1.setVisibility(View.INVISIBLE);
                Reponse2.setVisibility(View.INVISIBLE);
                Reponse3.setVisibility(View.INVISIBLE);
                ButtonVisible.setImageResource(R.drawable.icon_visible);
            }

        }else {
            Question.setText("");
            Reponse.setText("");
            Reponse1.setText("");
            Reponse2.setText("");
            Reponse3.setText("");
            Reponse1.setVisibility(View.INVISIBLE);
            Reponse2.setVisibility(View.INVISIBLE);
            Reponse3.setVisibility(View.INVISIBLE);
            arrowleft.setVisibility(View.INVISIBLE);
            arrowright.setVisibility(View.INVISIBLE);
            delete.setVisibility(View.INVISIBLE);
            ButtonVisible.setImageResource(R.drawable.icon_visible);
        }
    }

    /**
     *
     */

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