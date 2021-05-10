import java.util.ArrayList;

public class SCAN extends Algorytm {

    Dysk dysk;
//    int pos = 1;
    int prepos;


    public SCAN (ArrayList<Zadanie> list,int poz, int size){
        super(list);
        dysk = new Dysk(poz,size);
        prepos=dysk.poz-1;
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
                dysk.poz--; //198
                prepos=dysk.size-1; //199
                dysk.zmiany++;
            }
            else if(dysk.poz == 0){
                dysk.poz++; //1
                prepos=0;//0
                dysk.zmiany++;
            }
            else if(prepos<dysk.poz){
                dysk.poz++; //--->
                prepos++;
                dysk.zmiany++;
            }
            else if(prepos>dysk.poz){
                dysk.poz--; // <---
                prepos--;
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




//      dysk.zmiany++;
//              Zadanie z;
////        for(Zadanie zad:list)
////            zad.czasOczekiwania++;
//              if(dysk.poz==200){
//              dysk.poz=199;
//              prepos=200;
//              for(int i=0; i<list.size();i++){
//        if(list.get(i).pozycja==dysk.poz)
//        return list.get(i);
//        }
//        return null;
//        }else if(dysk.poz==1 && prepos==2){
//        dysk.poz++;
//        prepos--;
//        for(int i=0; i<list.size();i++){
//        if(list.get(i).pozycja==dysk.poz)
//        return list.get(i);
//        }
//        return null;
//        }else if(prepos>dysk.poz){
//        dysk.poz--;
//        prepos--;
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
//        z = list.get(i);  //NAPRAW DO ZEBY SCANY DZIALALY
//        return z;
//        }
//        return null;
//        }