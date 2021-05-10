import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OPT extends AlgZasStron {

    ArrayList<Integer> allAddr;

    @Override
    public void zamienStrone() {

    }

    public void zamienStroneOPT(ArrayList<Integer> oczekujaceOdwolania, int iloscStron) {
        ArrayList<Integer> counts = new ArrayList<>();
        for (int i = 0; i < pamiecFizyczna.getRamkaArray().size(); i++) {
            counts.add(10000);
        }

//        for(int i=0; i<counts.size();i++){
//            System.out.print(" "+(i+1)+" ");
//        }
//        System.out.println();
//        System.out.println(counts);



        ArrayList<JednDanych> ramArr = pamiecFizyczna.getRamkaArray();
//        JednDanych doUsuniecia = ramArr.get(0);
        for (int i = 0; i < ramArr.size(); i++) {
            int count2 =0;
            for(int j=0;j<oczekujaceOdwolania.size();j++){
                if(oczekujaceOdwolania.get(j)==ramArr.get(i).adres){
                    counts.set(i,count2);
                    break;
                }
                count2++;
            }
        }

        int index = 0, maxValue =0;
        for(int i=0;i<counts.size();i++){
            if(counts.get(i)>maxValue){
                maxValue = counts.get(i);
                index = i;
            }
        }

        JednDanych doUsuniecia = ramArr.get(index);


        // znajdz najmniejszy indeks wskzzujecy na max counts po counts 2;
        //zwroc adres tego elementu z ramka arr;


//        System.out.println(doUsuniecia.adres);

//        for (int i = 0; i < oczekujaceOdwolania.size(); i++) {
//            if (counts.get(i) < counts.get(smallestIndex) && counts.get(i) > 0) {
//                smallestIndex = i;
//            }
//        }
//        System.out.println("Usuwam strone = " + pamiecFizyczna.getRamkaArray().get(index).adres);
//        pamiecFizyczna.usunRamke(pamiecFizyczna.getRamkaArray().get(index).adres);

//        System.out.println("Usuwam strone = " + doUsuniecia.adres);
        pamiecFizyczna.usunRamke(doUsuniecia.adres);
    }
}




//    public void zamienStroneOPT(ArrayList<Integer> oczekujaceOdwolania, int iloscStron) {
//        ArrayList<Integer> counts = new ArrayList<>();
//        for (int i = 0; i < iloscStron; i++) {
//            counts.add(0);
//        }
//        for (int i = 0; i < oczekujaceOdwolania.size(); i++) {
//            counts.set(oczekujaceOdwolania.get(i) - 1, counts.get(oczekujaceOdwolania.get(i) - 1) + 1);
//        }
//
////        for(int i=0; i<counts.size();i++){
////            System.out.print(" "+(i+1)+" ");
////        }
////        System.out.println();
////        System.out.println(counts);
//
//
//
//        ArrayList<JednDanych> ramArr = pamiecFizyczna.getRamkaArray();
//        JednDanych doUsuniecia = ramArr.get(0);
//        for (int i = 1; i < ramArr.size(); i++) {
////            System.out.println(doUsuniecia.adres+" = "+counts.get(doUsuniecia.adres-1)+" porwonuje do "+ramArr.get(i).adres+" = "+counts.get(ramArr.get(i).adres-1));
//            if (counts.get(ramArr.get(i).adres-1)<counts.get(doUsuniecia.adres-1)){
//                doUsuniecia = ramArr.get(i);
//            }
//        }
//
//
////        System.out.println(doUsuniecia.adres);
//
////        for (int i = 0; i < oczekujaceOdwolania.size(); i++) {
////            if (counts.get(i) < counts.get(smallestIndex) && counts.get(i) > 0) {
////                smallestIndex = i;
////            }
////        }
////        System.out.println("Usuwam strone = " + pamiecFizyczna.getRamkaArray().get(index).adres);
////        pamiecFizyczna.usunRamke(pamiecFizyczna.getRamkaArray().get(index).adres);
//
////        System.out.println("Usuwam strone = " + doUsuniecia.adres);
//        pamiecFizyczna.usunRamke(doUsuniecia.adres);