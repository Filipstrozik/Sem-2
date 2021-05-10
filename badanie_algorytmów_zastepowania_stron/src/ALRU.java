import javax.swing.*;
import java.util.ArrayList;
import java.util.Queue;

public class ALRU extends AlgZasStron {

    @Override
    public void zamienStrone() {
        ArrayList<JednDanych> listaDanych = pamiecFizyczna.getRamkaArray();
        Queue<JednDanych> kolejka = pamiecFizyczna.kolejka;

//        for (JednDanych d : listaDanych) {
//            System.out.print(d.szansa + " ");
//        }

        if (kolejka.isEmpty()) {
            return;
        }

        boolean znalezionaFlaga = false;
        int indexDoUsuniecia = 0;
        while (!znalezionaFlaga) {
            if(kolejka.peek().szansa==1){
                kolejka.peek().szansa=0;
                kolejka.add(kolejka.poll());
            } else if(kolejka.peek().szansa==0){
                znalezionaFlaga = true;
                indexDoUsuniecia = kolejka.poll().adres;
            }

//            else if(listaDanych.get(indexDoUsuniecia).szansa == 1){
//                listaDanych.get(indexDoUsuniecia).szansa=0;
//                listaDanych.get(indexDoUsuniecia).czasOstatniegoUzycia = Timer.getInstance().getTime();
//            }
        }
//        System.out.println("Usuwam strone = " + indexDoUsuniecia);
        pamiecFizyczna.usunRamke(indexDoUsuniecia);
    }
}


//    boolean znalezionaFlaga = false;
//    int indexDoUsuniecia = 0;
//        while (!znalezionaFlaga) {
//                indexDoUsuniecia=0;
//                for (int i = 1; i < listaDanych.size(); i++) { //trzeba isc po kolejce
//        if (listaDanych.get(i).czasOstatniegoUzycia < listaDanych.get(indexDoUsuniecia).czasOstatniegoUzycia) {
//        indexDoUsuniecia = i;
//        }
//        }
//        System.out.println(indexDoUsuniecia);
//        if (listaDanych.get(indexDoUsuniecia).szansa == 0) {
//        znalezionaFlaga = true;
//        }
//        else if(listaDanych.get(indexDoUsuniecia).szansa == 1){
//        listaDanych.get(indexDoUsuniecia).szansa=0;
//        listaDanych.get(indexDoUsuniecia).czasOstatniegoUzycia = Timer.getInstance().getTime();
//        }
//        }
//        System.out.println("Usuwam strone = " + listaDanych.get(indexDoUsuniecia).adres);
//        pamiecFizyczna.usunRamke(listaDanych.get(indexDoUsuniecia).adres);
//        }