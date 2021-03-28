import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> lista = new ArrayList<>(Arrays.asList(2,36,5,21,8,13,11,20,5,4,1));
	    Algorytm a = new Algorytm(lista,3);
        System.out.println(a.run());
    }
}
