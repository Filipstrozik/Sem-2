import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FCFS extends Algorytm {

    Queue<Proces> kolejka;

    public FCFS(ArrayList<Proces> list) {
        super(list);
        kolejka = new LinkedList<Proces>(list);
    }

    @Override
    public Proces aktywnyProces() {
        return kolejka.peek();
    }

    @Override
    public void updateList(ArrayList<Proces> list) {
        kolejka = new LinkedList<Proces>(list);
    }
}
