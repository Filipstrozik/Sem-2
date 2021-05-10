import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FCFS extends Algorytm {
    Dysk dysk;
    Queue<Zadanie> kolejka;

    public FCFS(ArrayList<Zadanie> list,int poz, int size) {
        super(list);
        dysk = new Dysk(poz, size);
        kolejka = new LinkedList<Zadanie>(list);
    }

    @Override
    public Zadanie aktywneZadanie() {
        Zadanie z = kolejka.peek();//
        if(z.pozycja == dysk.poz){
            return z;
        } else if(z.pozycja > dysk.poz){
            dysk.poz++;
            dysk.zmiany++;
        } else {
            dysk.poz--;
            dysk.zmiany++;
        }
        return null;
    }

    @Override
    public void updateList(ArrayList<Zadanie> list) {
        kolejka = new LinkedList<Zadanie>(list);
    }

}