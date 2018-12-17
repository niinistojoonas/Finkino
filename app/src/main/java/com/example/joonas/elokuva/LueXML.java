package com.example.joonas.elokuva;

import android.os.StrictMode;
import android.view.View;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static java.lang.Integer.parseInt;


public class LueXML {

    String ID;

    ArrayList<String> teatterit = new ArrayList<String>();

    ArrayList<Teatterit> oliolista = new ArrayList<>();

    String aika2;





    public ArrayList<String> teatterilista() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String osoite = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document dokumentti = builder.parse(osoite);
            dokumentti.getDocumentElement().normalize();
            System.out.println("Root element: " + dokumentti.getDocumentElement().getNodeName());

            NodeList nlist = dokumentti.getDocumentElement().getElementsByTagName("TheatreArea");

            for (int i = 0; i < nlist.getLength(); i++) {
                Node node = nlist.item(i);
                Teatterit uusi = new Teatterit();


                if (node.getNodeType() == node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getElementsByTagName("Name").item(0).getTextContent();
                    String Idluku = element.getElementsByTagName("ID").item(0).getTextContent();
                    uusi.aseta(name, Idluku);
                    oliolista.add(uusi);
                    teatterit.add(name);
                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            //System.out.println("#####DONE####");
        }

        return teatterit;


    }




    public ArrayList aikataulut(String paivamaara, String nimi, int alku, int loppu){

        ArrayList tulostettava = new ArrayList();

        oliolista = oliotlistaan();

        System.out.println(oliolista.size());

        for(int i = 1; i < oliolista.size(); i++) {
             if (oliolista.get(i).getName().equals(nimi)){
                 ID = oliolista.get(i).getID();
             }
        }



        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String osoite = "http://www.finnkino.fi/xml/Schedule/?area="+ID+"&dt="+paivamaara;
            System.out.println(osoite);
            Document dokumentti = builder.parse(osoite);
            dokumentti.getDocumentElement().normalize();
            //System.out.println("Root element: "+dokumentti.getDocumentElement().getNodeName());

            NodeList nlist =  dokumentti.getDocumentElement().getElementsByTagName("Show");
            System.out.println(nlist.getLength());

            for (int i=0; i< nlist.getLength(); i++){
                Node node = nlist.item(i);
                Element element = (Element) node;


                if (node.getNodeType() == node.ELEMENT_NODE){
                    String lisattava = element.getElementsByTagName("Title").item(0).getTextContent();
                    String aika = element.getElementsByTagName("dttmShowStart").item(0).getTextContent();
                    String[] parts = aika.split("T");
                    System.out.println(aika);
                    System.out.println(lisattava);
                    aika2 = (parts[1]);
                    System.out.println(aika2);
                    String[] parts2 = aika2.trim().split(":");
                    String aika3 = (parts2[0]);
                    int aika4 = parseInt(aika3);
                    System.out.println(aika4);

                    if (aika4>alku && aika4<loppu) {
                        System.out.println("KAKKA");
                        tulostettava.add(lisattava + " Klo: " + aika2);
                        System.out.println(lisattava + " Klo: " + aika2);
                    }

                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally{
            return tulostettava;
        }


    }


    public ArrayList<Teatterit> oliotlistaan() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String osoite = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document dokumentti = builder.parse(osoite);
            dokumentti.getDocumentElement().normalize();
            //System.out.println("Root element: " + dokumentti.getDocumentElement().getNodeName());

            NodeList nlist = dokumentti.getDocumentElement().getElementsByTagName("TheatreArea");

            for (int i = 0; i < nlist.getLength(); i++) {
                Node node = nlist.item(i);
                Teatterit uusi = new Teatterit();


                if (node.getNodeType() == node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getElementsByTagName("Name").item(0).getTextContent();
                    String Idluku = element.getElementsByTagName("ID").item(0).getTextContent();
                    uusi.aseta(name, Idluku);
                    oliolista.add(uusi);
                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            //System.out.println("#####DONE####");
        }

        return oliolista;


    }




}
