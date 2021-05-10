import java.util.ArrayList;

public class FIFO extends AlgZasStron {
    @Override
    public void zamienStrone() {
        ArrayList<JednDanych> listaDanych = pamiecFizyczna.getRamkaArray();

        if(listaDanych.isEmpty()){
            return;
        }
        //znajdz tą ramke ktora ma najmniejszy czas dodania
        JednDanych doUsuniecia = listaDanych.get(0);
        for(int i=1; i<listaDanych.size();i++){
            if(listaDanych.get(i).czasDodaniaWPamieci < doUsuniecia.czasDodaniaWPamieci)
            {
                doUsuniecia = listaDanych.get(i);
            }
        }
//        System.out.println("Usuwam strone = "+doUsuniecia.adres);
        pamiecFizyczna.usunRamke(doUsuniecia.adres);
    }
}
