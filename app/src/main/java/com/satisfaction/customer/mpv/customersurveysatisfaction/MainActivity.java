package com.satisfaction.customer.mpv.customersurveysatisfaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.OutputStreamWriter;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Initialize views
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.ic_excellent)
    Button ic_excellentIb;
    @BindView(R.id.questionsRandom)
    TextView questionsRandom;
    /*  @BindView(R.id.ic_good)
      Button ic_goodIb;
      @BindView(R.id.ic_normal)
      Button ic_normalIb;
      @BindView(R.id.ic_bad)
      Button ic_badIb;
      @BindView(R.id.ic_very_bad)
      Button ic_very_badIb;
*/
      @BindView(R.id.excelente)
      TextView excellentTv;
  /*    @BindView(R.id.good)
      TextView goodTv;
      @BindView(R.id.normal)
      TextView normalTv;
      @BindView(R.id.bad)
      TextView badTv;
      @BindView(R.id.very_bad)
      TextView very_badTv;
  */
    private static final String[] cuestionario = {"¿Que le parece el servicio que le damos en Merca Plaza?",
            "¿Como son los precios de Merca Plaza?",
            "¿Como encuentra la  calidad de los productos?",
            "¿Que piensa sobre el personal de Merca Plaza?"};

    private static final Random ALEATORIO = new Random();

    Context context;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        context = this;
        sharedPreferences = getSharedPreferences("EncuestaMPV", MODE_PRIVATE);

        ic_excellentIb.setOnClickListener(this);
        encuesta();
    }

    @SuppressLint("SetTextI18n")
    public void encuesta() {
          String pregunta = cuestionario[ALEATORIO.nextInt(cuestionario.length)];
          questionsRandom.setText(pregunta);
        /*for (int i = 0; i < cuestionario.length; i++) {
            questionsRandom.setText("" + String.valueOf(i));
        }*/
    }

    public void guardarRespuesta(){
        try{
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("estadisticaEncuesta.txt", MODE_PRIVATE));
            outputStreamWriter.write(excellentTv.getText().toString());
            outputStreamWriter.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_excellent:
            case R.id.excelente:

              /*  SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("encuestaMPV", ic_excellentIb.getText().toString());
                editor.commit();*/
              guardarRespuesta();
                encuesta();
                //code
                break;

        }
    }
}