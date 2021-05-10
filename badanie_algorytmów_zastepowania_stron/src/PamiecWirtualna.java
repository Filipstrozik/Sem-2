import java.lang.reflect.Array;
import java.util.ArrayList;


public class PamiecWirtualna {
    private ArrayList<Strona> stronaArray;
    public PamiecFizyczna pamiecFizyczna;
    private AlgZasStron algZasStron;
    private int zmaianaStronCounter = 0;

    //statystyka strona ktora najczesciej powoduje blad.

    public PamiecWirtualna(AlgZasStron algZasStron, int iloscRamek, int iloscStron) {
        this.algZasStron = algZasStron;
        this.pamiecFizyczna = new PamiecFizyczna(iloscRamek);
        stronaArray = new ArrayList<Strona>();

        for (int i = 0; i < iloscStron; i++) {
            stronaArray.add(new Strona(i + 1));
        }
        this.algZasStron.setPamiecFizyczna(pamiecFizyczna);
    }

    public boolean zaladujStroneDoPamieciFizycznej(int addr) {
        Timer.getInstance().increaseTime(1);


        if (addr > stronaArray.size() || addr < 0) {
            System.out.println("Adres zpoza przedziau! Adres = " + addr);
            return false;
        }

        JednDanych doDodania = stronaArray.get(addr - 1);
//        System.out.println(doDodania.adres);
        if (pamiecFizyczna.dodajRamkeDoPamieci(doDodania) == false) {
//            System.out.println("Nie ma "+ addr+" w ramkach");
            zmaianaStronCounter++;

            //tutaj dodwanaie counta w mapie stron
//            System.out.println("zamieniam strone algorytmem.");
            algZasStron.zamienStrone();
            pamiecFizyczna.dodajRamkeDoPamieci(doDodania);
        }

        return true;
    }

    public boolean zaladujStroneDoPamieciFizycznejOPT(int addr, ArrayList<Integer> oczekujaceOdwolania) {
        Timer.getInstance().increaseTime(1);

        if (addr > stronaArray.size() || addr < 0) {
            System.out.println("Adres zpoza przedziau! Adres = " + addr);
            return false;
        }

        JednDanych doDodania = stronaArray.get(addr - 1);
//        System.out.println(doDodania.adres);
        if (pamiecFizyczna.dodajRamkeDoPamieci(doDodania) == false) {
//            System.out.println("Nie ma "+ addr+" w ramkach");
            zmaianaStronCounter++;
            //tutaj dodawanie counta w mapie stron
//            System.out.println("zamieniam strone algorytmem.");
            ((OPT) algZasStron).zamienStroneOPT(oczekujaceOdwolania, stronaArray.size());
            pamiecFizyczna.dodajRamkeDoPamieci(doDodania);
        }

        return true;
    }

    public int getZmaianaStronCounter() {
        return zmaianaStronCounter;
    }


}
