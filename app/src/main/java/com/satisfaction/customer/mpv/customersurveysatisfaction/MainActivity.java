package com.satisfaction.customer.mpv.customersurveysatisfaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Initialize views
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.questionsRandom)
    TextView questionsRandom;

    @BindView(R.id.ic_excellent)
    Button ic_excellentIb;
    @BindView(R.id.ic_good)
    Button ic_goodIb;
    @BindView(R.id.ic_normal)
    Button ic_normalIb;
    @BindView(R.id.ic_bad)
    Button ic_badIb;
    @BindView(R.id.ic_very_bad)
    Button ic_very_badIb;

    @BindView(R.id.excelente)
    TextView excellentTv;
    @BindView(R.id.good)
    TextView goodTv;
    @BindView(R.id.normal)
    TextView normalTv;
    @BindView(R.id.bad)
    TextView badTv;
    @BindView(R.id.very_bad)
    TextView very_badTv;

    private String[] cuestionario = {
            "¿Que le parece el servicio que le damos en Merca Plaza?",
            "¿Como son los precios de Merca Plaza?",
            "¿Como encuentra la  calidad de los productos?",
            "¿Que piensa sobre el personal de Merca Plaza?"
    };


    Helper helper = new Helper();
    Context context = this;
    SharedPreferences sharedPreferences;

    private int current_question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences("EncuestaMPV", MODE_PRIVATE);

        //listener buttons
        ic_excellentIb.setOnClickListener(this);
        ic_goodIb.setOnClickListener(this);
        ic_normalIb.setOnClickListener(this);
        ic_badIb.setOnClickListener(this);
        ic_very_badIb.setOnClickListener(this);

        current_question = 0;
        helper.encuesta(questionsRandom, cuestionario, current_question);
    }

    public String fileName = "prueba11.txt";

    public void guardarRespuesta(TextView respuestaTv) {

        OutputStreamWriter escritor = null;
        try {
            escritor = new OutputStreamWriter(openFileOutput(fileName, Context.MODE_APPEND));

            escritor.write(questionsRandom.getText().toString() + respuestaTv.getText().toString());

        } catch (Exception ex) {
            Log.e("emmanuel", "Error al escribir fichero a memoria interna");
        } finally {
            try {
                if (escritor != null)
                    escritor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void leerFicheroMemoriaInterna() {
        InputStreamReader flujo = null;
        BufferedReader lector;
        try {
            flujo = new InputStreamReader(openFileInput(fileName));
            lector = new BufferedReader(flujo);
            String texto = lector.readLine();
            while (texto != null) {
                // Toast.makeText(getApplicationContext(), "--: " + texto + "\n", Toast.LENGTH_LONG).show();


                //TODO: NUNCA HACER ESTO
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(context);
                }
                builder.setTitle("Respuestas")
                        .setMessage(texto)
                        .show();
                //ASI NO....

                texto = lector.readLine();
            }
        } catch (Exception ex) {
            Log.e("emmanuel", "Error al leer fichero desde memoria interna");
        } finally {
            try {
                if (flujo != null)
                    flujo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void guardandoDatos(TextView respuestaTv) {
        String dato = respuestaTv.getText().toString();
        @SuppressLint("WrongConstant") SharedPreferences preferences = getPreferences(Context.MODE_APPEND);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("datos", helper.encuesta(cuestionario, current_question) + dato + "\n");
        editor.apply();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_excellent:
            case R.id.excelente:

                showQuestion();
                guardarRespuesta(excellentTv);
                //guardandoDatos(excellentTv);
                //  showData();
                leerFicheroMemoriaInterna();
                break;

            case R.id.ic_good:
            case R.id.good:

                showQuestion();
                guardarRespuesta(goodTv);
                //guardandoDatos(goodTv);
                //  showData();
                leerFicheroMemoriaInterna();
                break;

            case R.id.ic_normal:
            case R.id.normal:

                showQuestion();
                guardarRespuesta(normalTv);
                // guardandoDatos(normalTv);
                //  showData();
                leerFicheroMemoriaInterna();
                break;

            case R.id.ic_bad:
            case R.id.bad:

                showQuestion();
                guardarRespuesta(badTv);
                // guardandoDatos(badTv);
                //  showData();
                leerFicheroMemoriaInterna();
                break;

            case R.id.ic_very_bad:
            case R.id.very_bad:

                showQuestion();
                guardarRespuesta(very_badTv);
                //guardandoDatos(very_badTv);
                //  showData();
                leerFicheroMemoriaInterna();
                break;

        }
    }

    //TODO: Mover metodo al Helper
    private void showQuestion() {
        if (current_question < cuestionario.length - 1) {
            current_question++;
            helper.encuesta(questionsRandom, cuestionario, current_question);
        }
    }

    private String question() {
        if (current_question < cuestionario.length - 1)
            current_question++;
        return helper.encuesta(cuestionario, current_question);
    }

    private void showData() {
        if (current_question == cuestionario.length - 1) {
            @SuppressLint("WrongConstant") SharedPreferences preferences = getPreferences(Context.MODE_APPEND);
            String valores = preferences.getString("datos", "No hay nada guardado");
            Toast.makeText(getApplicationContext(), "Datos: " + valores, Toast.LENGTH_LONG).show();
        }
    }
}