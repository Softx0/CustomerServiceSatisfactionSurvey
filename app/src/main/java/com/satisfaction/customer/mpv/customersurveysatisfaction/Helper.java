package com.satisfaction.customer.mpv.customersurveysatisfaction;

import android.widget.TextView;

public class Helper {

    public void encuesta(TextView questionsRandom, String[] cuestionario, int current_question) {
        //  String pregunta = cuestionario.get(ALEATORIO.nextInt(cuestionario.size()));
        //  questionsRandom.setText(pregunta);

        String question = cuestionario[current_question];
        questionsRandom.setText(question);
     /*  for (int i = 0; i < cuestionario.length; i++) {
            questionsRandom.setText(cuestionario[i]);
        }*/

    }

    public String encuesta(String[] cuestionario, int current_question) {
        //  String pregunta = cuestionario.get(ALEATORIO.nextInt(cuestionario.size()));
        //  questionsRandom.setText(pregunta);
        return cuestionario[current_question];
        // questionsRandom.setText(question);
     /*  for (int i = 0; i < cuestionario.length; i++) {
            questionsRandom.setText(cuestionario[i]);
        }*/

    }

}