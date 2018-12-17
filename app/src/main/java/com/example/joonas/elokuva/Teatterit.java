package com.example.joonas.elokuva;

import static java.lang.Integer.parseInt;

public class Teatterit {

    String nimi;
    String  Id;


    public void aseta(String x, String y){
        nimi = x;
        Id = y;
    }

    public String getName(){
        return nimi;
    }

    public String getID(){
        return Id;
    }
}


