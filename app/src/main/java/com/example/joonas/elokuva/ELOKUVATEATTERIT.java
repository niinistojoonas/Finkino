package com.example.joonas.elokuva;

import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;



public class ELOKUVATEATTERIT extends AppCompatActivity {

    private Spinner spinner;
    private Spinner spinner2;
    private Spinner alku;
    private Spinner loppu;


    ArrayList<String> tulostettava = new ArrayList<String>();
    ArrayList<String> paikkakunnat = new ArrayList<String>();
    ArrayList<String> aikaikkuna = new ArrayList<String>();

    String item;

    String paikka;

    ScrollView ikkuna;

    TextView tekstiikkuna;

    String paivamaara;

    int alkuaika;

    int loppuaika;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elokuvateatterit);

        aikaikkuna.add("Aika");


        for(int i = 0; i < 23; i++){
            aikaikkuna.add(i+":00");
        }








        LueXML lue = new LueXML();
        paikkakunnat = lue.teatterilista();


        String s = "2018-12-01";
        String e = "2018-12-10";
        LocalDate start = LocalDate.parse(s);
        LocalDate end = LocalDate.parse(e);
        List<String> totalDates = new ArrayList<>();
        totalDates.add("Päivämäärä");
        while (!start.isAfter(end)) {
            String lol = start.toString();
            String[] parts = lol.split("-");
            String lol2 = parts[2]+"."+parts[1]+"."+parts[0];
            totalDates.add(lol2);
            start = start.plusDays(1);
        }



        spinner = findViewById(R.id.spinner);

        spinner2 = findViewById(R.id.spinner2);

        alku =findViewById(R.id.spinner4);

        loppu =findViewById(R.id.spinner3);

        ikkuna = findViewById(R.id.scrollView2);

        tekstiikkuna = findViewById(R.id.textView);




        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        ArrayAdapter<String> dataAdapter;
        dataAdapter  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, paikkakunnat);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (parent.getItemAtPosition(position).equals("Valitse alue/teatteri")){

                }
                else{

                    item = parent.getItemAtPosition(position).toString();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter<String> dataAdapter2;
        dataAdapter2  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, totalDates);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Päivämäärä")){

                }
                else{

                    paivamaara = parent.getItemAtPosition(position).toString();


                        }
                    }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        ArrayAdapter<String> dataAdapter3;
        dataAdapter3  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, aikaikkuna);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        alku.setAdapter(dataAdapter3);
        alku.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Aika")){

                }
                else{
                    String aika = parent.getItemAtPosition(position).toString();
                    String osat[] = aika.split(":");
                    alkuaika = Integer.parseInt(osat[0]);


                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<String> dataAdapter4;
        dataAdapter4  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, aikaikkuna);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loppu.setAdapter(dataAdapter4);
        loppu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Aika")){

                }
                else{

                    String aika = parent.getItemAtPosition(position).toString();
                    String osat[] = aika.split(":");
                    loppuaika = Integer.parseInt(osat[0]);




                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });








    }




    public void showMovies(View v){
        tekstiikkuna.setText("");
        LueXML lue = new LueXML();
        tulostettava = lue.aikataulut(paivamaara,item, alkuaika, loppuaika);
        for(int i = 0; i < tulostettava.size(); i++) {
            tekstiikkuna.append((i+1)+". "+tulostettava.get(i));
            tekstiikkuna.append(System.getProperty("line.separator"));
        }
    }




}
