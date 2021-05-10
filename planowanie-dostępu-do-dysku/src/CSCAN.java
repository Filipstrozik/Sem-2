import java.util.ArrayList;

public class CSCAN extends Algorytm {

    Dysk dysk;
//    int pos = 1;
    int prepos;


    public CSCAN (ArrayList<Zadanie> list, int poz, int size){
        super(list);
        dysk = new Dysk(poz, size);
        prepos= dysk.poz-1;
    }

    @Override
    public Zadanie aktywneZadanie() throws InterruptedException {
        Zadanie z = null;
        for(int i=0; i<list.size();i++){
            if(list.get(i).pozycja==dysk.poz){
                z = list.get(i);
            }
        }
        if(z!=null){
            return z;
        }
        else{
            if(dysk.poz==dysk.size-1){ //hit right bound zmiana kierunku
                dysk.poz=0; //0
                prepos=-1; //-1
                dysk.zmiany++;
            }
            else if(prepos<dysk.poz){
                dysk.poz++; //--->
                prepos++;
                dysk.zmiany++;
            }
            return null;
        }
    }

    @Override
    public void updateList(ArrayList<Zadanie> list) {
        this.list=list;
    }
}






//        dysk.zmiany++;
////        for(Zadanie zad:list)
////            zad.czasOczekiwania++;
//        if(dysk.poz==200){
//        dysk.poz=1;
//        prepos=0;
//        for(int i=0; i<list.size();i++){
//        if(list.get(i).pozycja==dysk.poz)
//        return list.get(i);
//        }
//        return null;
//        }else {
//        dysk.poz++;
//        prepos++;
//        for(int i=0; i<list.size();i++){
//        if(list.get(i).pozycja==dysk.poz)
//        return list.get(i);
//        }
//        return null;
//        }