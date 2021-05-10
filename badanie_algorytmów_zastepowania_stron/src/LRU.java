import java.util.ArrayList;

public class LRU extends AlgZasStron{
    @Override
    public void zamienStrone() {
        ArrayList<JednDanych> listaDanych = pamiecFizyczna.getRamkaArray();

        if(listaDanych.isEmpty()){
            return;
        }
        //znajdz tą ramke ktora ma najmniejszy czas ostatniego uzycia
        JednDanych doUsuniecia = listaDanych.get(0);
        for(int i=1; i<listaDanych.size();i++){
            if(listaDanych.get(i).czasOstatniegoUzycia < doUsuniecia.czasOstatniegoUzycia)
            {
                doUsuniecia = listaDanych.get(i);
            }
        }
//        System.out.println("Usuwam strone = "+doUsuniecia.adres);
        pamiecFizyczna.usunRamke(doUsuniecia.adres);
    }
}
