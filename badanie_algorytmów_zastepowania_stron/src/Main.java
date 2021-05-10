import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Generator gen = new Generator();
        ArrayList<Integer> list = new ArrayList<>();
        int fifoCounter = 0, optCounter = 0, lruCounter = 0, alruCounter = 0, randCounter = 0;
        int n=10;
        Scanner sc = new Scanner(System.in);

        System.out.println("Symulacja algorytmów zastępowania stron.");

        System.out.println("jak dlugi ciag?");
        int dlugoscCiagu = sc.nextInt();

        System.out.println("jak duzo stron?");
        int iloscStron = sc.nextInt();
        Generator.maxAdres = iloscStron;

        System.out.println("jak duzo ramek?");
        int iloscRamek = sc.nextInt();

        System.out.println("jakie prawdopodobienstwo zasady lokalnosci - jak czesto sie pojawia?");
        Generator.prawdopodobienstwo = sc.nextInt();

        System.out.println("Na jak maksymalnie dlugo ma dzialac zasada lokalnosci?");
        Generator.maxCzasZasadyLokalnosci = sc.nextInt();

        System.out.println("Jak duży podzbior ramek zasady lokalnosci?");
        Generator.ileAdresowZasadyLokalnosci = sc.nextInt();

        //poczatek loopa
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < dlugoscCiagu; i++) {
                int val = gen.next();
//            System.out.println(val);
                list.add(val);
            }


            //zrobić średnią wartość błędów na ilosc symulacji

//            System.out.println("wykonanie");
            fifoCounter += symulacja(new FIFO(), iloscRamek, iloscStron, list, dlugoscCiagu);

            optCounter += symulacjaOPT(new OPT(), iloscRamek, iloscStron, list, dlugoscCiagu);

            lruCounter += symulacja(new LRU(), iloscRamek, iloscStron, list, dlugoscCiagu);

            alruCounter += symulacja(new ALRU(), iloscRamek, iloscStron, list, dlugoscCiagu);

            randCounter += symulacja(new RAND(), iloscRamek, iloscStron, list, dlugoscCiagu);

            list.clear();
        }

        fifoCounter = fifoCounter/n;
        optCounter = optCounter/n;
        lruCounter = lruCounter/n;
        alruCounter = alruCounter/n;
        randCounter = randCounter/n;


        //koniec loopa
        System.out.println("FIFO: "+fifoCounter);
        System.out.println("OPT: "+optCounter);
        System.out.println("LRU: "+lruCounter);
        System.out.println("ALRU: "+alruCounter);
        System.out.println("RAND: "+randCounter);
    }


    static int symulacja(AlgZasStron algZasStron, int ramki, int strony, ArrayList<Integer> addrList, int dlugoscCiagu) {
        Timer.getInstance().resetTime();
        PamiecWirtualna pamiecWirtualna = new PamiecWirtualna(algZasStron, ramki, strony);
        for (int i = 0; i < dlugoscCiagu; i++) {
//            System.out.print("RamkaArr: " + pamiecWirtualna.pamiecFizyczna.getRamkaArray() + "  ");
//            if(algZasStron instanceof ALRU){
//                System.out.print(pamiecWirtualna.pamiecFizyczna.kolejka);
//            }
            pamiecWirtualna.zaladujStroneDoPamieciFizycznej(addrList.get(i));
        }

        if (ramki >= strony) {
            return ramki;
        } else {
            return (pamiecWirtualna.getZmaianaStronCounter() + ramki);
        }
    }


    static int symulacjaOPT(AlgZasStron algZasStron, int ramki, int strony, ArrayList<Integer> addrList, int dlugoscCiagu) {
        Timer.getInstance().resetTime();
        PamiecWirtualna pamiecWirtualna = new PamiecWirtualna(algZasStron, ramki, strony);
        for (int i = 0; i < dlugoscCiagu; i++) {
            ArrayList<Integer> oczekujace = new ArrayList<Integer>();
            for (int j = i; j < dlugoscCiagu; j++) {
                oczekujace.add(addrList.get(j));
            }
//            System.out.println("RamkaArr: " + pamiecWirtualna.pamiecFizyczna.getRamkaArray() + "  ");
            pamiecWirtualna.zaladujStroneDoPamieciFizycznejOPT(addrList.get(i), oczekujace);
        }

        if (ramki >= strony) {
            return ramki;
        } else {
            return (pamiecWirtualna.getZmaianaStronCounter() + ramki);
        }
    }

}
