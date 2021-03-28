import java.util.ArrayList;

public class Algorytm {
    boolean flag;
    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    int K; //k-ty najmniejszy element
    int Vid; //id v wylosowanego rand
    ArrayList<Integer> SL = new ArrayList<Integer>();
    ArrayList<Integer> SV = new ArrayList<Integer>();
    ArrayList<Integer> SR = new ArrayList<Integer>();
    public Algorytm(ArrayList<Integer> list, int k){
        this.arrayList = list;
        this.K=k;
    }

    public int run(){
        while(arrayList.size()!=0) {
            System.out.println(arrayList);
            Vid = (int) (Math.random() * arrayList.size() - 1);
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i) < arrayList.get(Vid)) {
                    SL.add(arrayList.get(i));
                } else if (arrayList.get(i) == arrayList.get(Vid)) {
                    SV.add(arrayList.get(i));
                } else if (arrayList.get(i) > arrayList.get(Vid)) {
                    SR.add(arrayList.get(i));
                }
            }
            System.out.println(SL + "  " + SV + "  " + SR);
            if (K <= SL.size()) {
                arrayList = new ArrayList<>(SL);
                SR.clear();
                SV.clear();
                SL.clear();
            } else if (K <= SL.size() + SV.size()) {
                return arrayList.get(Vid);
            } else if (K > SL.size() + SV.size()) {
                arrayList = new ArrayList<>(SR);
                K = K - (SL.size() + SV.size());
                SL.clear();
                SV.clear();
                SR.clear();
            }
        }
        System.out.println(arrayList);
        System.out.println(SL + "  " + SV + "  " + SR);
        return -1;
    }
}
