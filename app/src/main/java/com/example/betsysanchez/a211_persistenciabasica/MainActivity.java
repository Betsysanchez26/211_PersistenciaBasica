package com.example.betsysanchez.a211_persistenciabasica;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioButton mal,fem;
    EditText email;
    Spinner sp;
    CheckBox chk1,chk2,chk3;
    Button save,load;
    private final String gEm="email", gene="genero",che1="check1",che2="check2",che3="check3";
    int item;
    int gen,cb1,cb2,cb3;;
    String [] signos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mal=findViewById(R.id.rbMal);
        fem=findViewById(R.id.rbFem);
        email=findViewById(R.id.email);
        sp=findViewById(R.id.spinner);
        chk1=findViewById(R.id.cb1);
        chk2=findViewById(R.id.cb2);
        chk3=findViewById(R.id.cb3);
        save=findViewById(R.id.save);
        load=findViewById(R.id.load);

        gen=0;
        item=0;
        cb1=0;
        cb2=0;
        cb3=0;
        signos= new String[]{"Aries", "Tauro", "Geminis", "Cancer", "Leo", "Virgo", "Libra", "Escorpio", "Sagitario", "Capricornio", "Picis", "Acuario"};

        sp.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,signos));


       fem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gen=1;
            }
        });
        mal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gen=0;
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                editor.putString(gEm, email.getText().toString());
                editor.putInt(gene,gen);
                verificarCheck();
                editor.putInt(che1,cb1);
                editor.putInt(che2,cb2);
                editor.putInt(che3,cb3);
                item=sp.getSelectedItemPosition();
                editor.putInt(null,item);
                editor.commit();
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= getPreferences(MODE_PRIVATE);
                String texto = sharedPreferences.getString(gEm,null);
                email.setText(texto);
                int genero=sharedPreferences.getInt(gene,0);
                if(genero==0){mal.setChecked(true);
                }else{fem.setChecked(true);
                }
                int check=sharedPreferences.getInt(che1,0);
                if(check==1) {chk1.setChecked(true);
                }else{chk1.setChecked(false);
                }
                int check2=sharedPreferences.getInt(che2,0);
                if(check2==1) {chk2.setChecked(true);
                }else{chk2.setChecked(false);
                }
                int check3=sharedPreferences.getInt(che3,0);
                if(check3==1) {chk3.setChecked(true);
                }else{chk3.setChecked(false);
                }
               int index=sharedPreferences.getInt(null,item);
                sp.setSelection(index);
            }
        });
    }
    public void verificarCheck(){
        if(chk1.isChecked()==true){
            cb1=1;}else{cb1=0;}
        if(chk2.isChecked()==true){
            cb2=1;}else{cb2=0;}
        if(chk3.isChecked()==true){
        cb3=1;}else{cb3=0;}
    }
}
