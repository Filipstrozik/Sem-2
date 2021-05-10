import java.util.ArrayList;

public class RoundRobin extends Algorytm{
    public static int kwant=1;
    int index;
    int oczekiwanyKwant;

    public RoundRobin(ArrayList<Proces> list) {
        super(list);
        index = 0;
        oczekiwanyKwant = kwant;

    }

    @Override
    public Proces aktywnyProces() {
        if(index >= list.size()) index =0;
        if(list.get(index).getCzasPozostały() == 0 || oczekiwanyKwant == 0)
        {
            oczekiwanyKwant = kwant;
            index++;
        }
        if(index >= list.size()) index = 0;
        oczekiwanyKwant--;
        return list.get(index);
    }

    @Override
    public void updateList(ArrayList<Proces> list) {
        this.list = list;
    }
}
