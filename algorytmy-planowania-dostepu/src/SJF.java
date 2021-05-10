import java.util.ArrayList;

public class SJF extends Algorytm{


    public SJF(ArrayList<Proces> list) {
        super(list);
    }

    @Override
    public Proces aktywnyProces() {
        int shortest = 0;
        if(currentproces.getId()!=-1){
            if(!currentproces.isDone()){ // bez wywlaszczenia
                return currentproces;
            }
            else
            {
                currentproces = new Proces();
            }
        }
        for(int i=1; i<list.size();i++){
            if(list.get(i).getCzasPozostały()<list.get(shortest).getCzasPozostały()){
                shortest=i;
            }
        }
        return list.get(shortest);
    }

    @Override
    public void updateList(ArrayList<Proces> list) {
        this.list = list;
    }
}
