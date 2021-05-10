import java.util.ArrayList;
import java.util.Random;

public class RAND extends AlgZasStron {
    @Override
    public void zamienStrone() {
        ArrayList<JednDanych> listaDanych = pamiecFizyczna.getRamkaArray();

        if(listaDanych.isEmpty()){
            return;
        }
        //znajdz tą ramke losowo
        Random rng = new Random();
        int indeksDoUsuniecia = rng.nextInt(listaDanych.size());//to może byc meh...

//        System.out.println("Usuwam strone = " + listaDanych.get(indeksDoUsuniecia).adres);
        pamiecFizyczna.usunRamke(listaDanych.get(indeksDoUsuniecia).adres);
    }
}
