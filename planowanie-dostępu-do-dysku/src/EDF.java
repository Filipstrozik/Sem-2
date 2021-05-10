import java.util.ArrayList;

public class EDF extends Strategia{

    Dysk dysk;
    public EDF(ArrayList<Zadanie> list, int poz, int size){
        super(list);
        dysk = new Dysk(poz, size);
    }
    @Override
    public Zadanie aktywneZadanie() throws CloneNotSupportedException {
        if(!listRT.isEmpty()){
            int shortestRT = 0;
            if(currentRT.pozycja!=-1){
                if(currentRT.pozycja == dysk.poz){
                    Zadanie zad = (Zadanie) currentRT.clone();
                    currentRT = new Zadanie();
                    return zad;
                } else if(currentRT.pozycja > dysk.poz){
                    dysk.poz++;
                    dysk.zmiany++;
                } else {
                    dysk.poz--;
                    dysk.zmiany++;
                }
                return null;
            }
            else {
                for(int i=1; i<listRT.size();i++){
                    if(listRT.get(i).deadline < listRT.get(shortestRT).deadline) {
                        shortestRT = i;
                    }
                }
                Zadanie z = listRT.get(shortestRT);
                currentRT = z;// czy to nie musi być szycbiej?
                if(currentRT.pozycja > dysk.poz){
                    dysk.poz++;
                    dysk.zmiany++;
                } else {
                    dysk.poz--;
                    dysk.zmiany++;
                }
                return null;
            }
        }
        else {
            int shortest = 0;
            if(currentzadanie.pozycja!=-1){
                if(currentzadanie.pozycja == dysk.poz){
                    Zadanie zad = (Zadanie) currentzadanie.clone();
                    currentzadanie = new Zadanie();
                    return zad;
                } else if(currentzadanie.pozycja > dysk.poz){
                    dysk.poz++;
                    dysk.zmiany++;
                } else {
                    dysk.poz--;
                    dysk.zmiany++;
                }
                return null;
            }
            else {
                for(int i=1; i<list.size();i++){
                    if(abs(list.get(shortest).pozycja - dysk.poz)> abs(list.get(i).pozycja - dysk.poz)){
                        shortest = i;
                    }
                }
                Zadanie z = list.get(shortest);
                currentzadanie = z;// czy to nie musi być szycbiej?
                if(z.pozycja > dysk.poz){
                    dysk.poz++;
                    dysk.zmiany++;
                } else {
                    dysk.poz--;
                    dysk.zmiany++;
                }
                return null;
            }
        }
        // Lista RT nie jest pusta wiec.
            //algorytm na szukanie zadania o najkrottszym deadline
            //zapisanie go jako currentRT
            // dazenie do current RT
            // jezeli zdazy do przekazujemy to zadnie -  w menadzeze jest usuwane, nowy currentRT
        //jezeli lista RT pusta to działa jak SSTF normalnie
    }

    @Override
    public void updateList(ArrayList<Zadanie> list){
        this.list = list;
    }

    @Override
    public void updateRealList(ArrayList<Zadanie> list){
        this.listRT = list;
    }
}
