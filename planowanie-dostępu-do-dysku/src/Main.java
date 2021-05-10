import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    CPU cpu = new CPU(new Menadzer(new Generator()));
	    cpu.aktywny= true;
        Scanner sk = new Scanner(System.in);
        String in = sk.next();
        while (!in.equals("quit")){
            if(in.equals("start"))
            {
                cpu.start();

                cpu.aktywny=true;

                System.out.println("start Symulacji...");
                System.out.println("glowica: "+ cpu.menadzer.poz);
                System.out.println("dysk: "+ cpu.menadzer.size);
                System.out.println("generator max czas next "+Generator.maxczasNextZad);
            }
            else if(in.equals("wyniki"))
            {

                System.out.println("CZAS: "+ cpu.menadzer.czasDzialania);
                int avgFCFS = 0;
                int maxFCFS = 0;
                int avgSSTF = 0;
                int maxSSTF = 0;
                int avgSCAN = 0;
                int maxSCAN = 0;
                int avgCSCAN =0;
                int maxCSCAN =0;
                int avgEDF = 0;
                int maxEDF = 0;
                int avgFDSCAN=0;
                int maxFDSCAN=0;

                for(Zadanie z : cpu.menadzer.statListFCFS)
                {
                    if(maxFCFS<z.czasOczekiwania)
                        maxFCFS= z.czasOczekiwania;
                    avgFCFS+=z.czasOczekiwania;
                }
                if(!cpu.menadzer.statListFCFS.isEmpty())
                    avgFCFS = avgFCFS/cpu.menadzer.statListFCFS.size();

                System.out.println("FCFS");
                System.out.println("Przebyta droga: " +cpu.menadzer.AlgorytmFCFS.dysk.zmiany);
                System.out.println("maksymalny czas oczekiwania: " +maxFCFS);
                System.out.println("sredni czas oczekiwania: "+avgFCFS);
                System.out.println("ilosc zadan obsluzonych: "+ cpu.menadzer.statListFCFS.size());
                System.out.println("ilosc zadan czakającyh powyzej 2000s: "+ cpu.menadzer.zaglodzoneFCFS.size());
                System.out.println("WSPOLCZYNNIK: "+ (cpu.menadzer.AlgorytmFCFS.dysk.zmiany/cpu.menadzer.statListFCFS.size()));


                for(Zadanie z : cpu.menadzer.statListSSTF)
                {
                    if(maxSSTF<z.czasOczekiwania)
                        maxSSTF= z.czasOczekiwania;
                    avgSSTF+=z.czasOczekiwania;
                }
                if(!cpu.menadzer.statListSSTF.isEmpty())
                    avgSSTF = avgSSTF/cpu.menadzer.statListSSTF.size();

                System.out.println("SSTF");
                System.out.println("Przebyta droga: " +cpu.menadzer.AlgorytmSSTF.dysk.zmiany);
                System.out.println("maksymalny czas oczekiwania: " +maxSSTF);
                System.out.println("sredni czas oczekiwania: "+avgSSTF);
                System.out.println("ilosc zadan obsluzonych: "+ cpu.menadzer.statListSSTF.size());
                System.out.println("ilosc zadan czakającyh powyzej 2000s: "+ cpu.menadzer.zaglodzoneSSTF.size());
                System.out.println("WSPOLCZYNNIK: "+ (cpu.menadzer.AlgorytmSSTF.dysk.zmiany/cpu.menadzer.statListSSTF.size()));

                for(Zadanie z : cpu.menadzer.statListSCAN)
                {
                    if(maxSCAN<z.czasOczekiwania)
                        maxSCAN= z.czasOczekiwania;
                    avgSCAN+=z.czasOczekiwania;
                }
                if(!cpu.menadzer.statListSCAN.isEmpty())
                    avgSCAN = avgSCAN/cpu.menadzer.statListSCAN.size();

                System.out.println("SCAN");
                System.out.println("Przebyta droga: " +cpu.menadzer.AlgorytmSCAN.dysk.zmiany);
                System.out.println("maksymalny czas oczekiwania: " +maxSCAN);
                System.out.println("sredni czas oczekiwania: "+avgSCAN);
                System.out.println("ilosc zadan obsluzonych: "+ cpu.menadzer.statListSCAN.size());
                System.out.println("ilosc zadan czakającyh powyzej 2000s: "+ cpu.menadzer.zaglodzoneSCAN.size());
                System.out.println("WSPOLCZYNNIK: "+ (cpu.menadzer.AlgorytmSCAN.dysk.zmiany/cpu.menadzer.statListSCAN.size()));

                for(Zadanie z : cpu.menadzer.statListCSCAN)
                {
                    if(maxCSCAN<z.czasOczekiwania)
                        maxCSCAN= z.czasOczekiwania;
                    avgCSCAN+=z.czasOczekiwania;
                }
                if(!cpu.menadzer.statListCSCAN.isEmpty())
                    avgCSCAN = avgCSCAN/cpu.menadzer.statListCSCAN.size();

                System.out.println("CSCAN");
                System.out.println("Przebyta droga: " +cpu.menadzer.AlgorytmCSCAN.dysk.zmiany);
                System.out.println("maksymalny czas oczekiwania: " +maxCSCAN);
                System.out.println("sredni czas oczekiwania: "+avgCSCAN);
                System.out.println("ilosc zadan obsluzonych: "+ cpu.menadzer.statListCSCAN.size());
                System.out.println("ilosc zadan czakającyh powyzej 2000s: "+ cpu.menadzer.zaglodzoneCSCAN.size());
                System.out.println("WSPOLCZYNNIK: "+ (cpu.menadzer.AlgorytmCSCAN.dysk.zmiany/cpu.menadzer.statListCSCAN.size()));


                for(Zadanie z : cpu.menadzer.statListEDF)
                {
                    if(maxEDF<z.czasOczekiwania)
                        maxEDF= z.czasOczekiwania;
                    avgEDF+=z.czasOczekiwania;
                }
                if(!cpu.menadzer.statListEDF.isEmpty())
                    avgEDF = avgEDF/cpu.menadzer.statListEDF.size();

                System.out.println("EDF");
                System.out.println("Przebyta droga: " +cpu.menadzer.StrategiaEDF.dysk.zmiany);
                System.out.println("maksymalny czas oczekiwania: " +maxEDF);
                System.out.println("sredni czas oczekiwania: "+avgEDF);
                System.out.println("ilosc zadan obsluzonych: "+ cpu.menadzer.statListEDF.size());
                System.out.println("ilosc zadan RT odrzuconych: "+ cpu.menadzer.zaglodzoneEDF.size());
                System.out.println("WSPOLCZYNNIK: "+ (cpu.menadzer.StrategiaEDF.dysk.zmiany/cpu.menadzer.statListEDF.size()));

                for(Zadanie z : cpu.menadzer.statListFDSCAN)
                {
                    if(maxFDSCAN<z.czasOczekiwania)
                        maxFDSCAN= z.czasOczekiwania;
                    avgFDSCAN+=z.czasOczekiwania;
                }
                if(!cpu.menadzer.statListFDSCAN.isEmpty())
                    avgFDSCAN = avgFDSCAN/cpu.menadzer.statListFDSCAN.size();

                System.out.println("FDSCAN");
                System.out.println("Przebyta droga: " +cpu.menadzer.StrategiaFDSCAN.dysk.zmiany);
                System.out.println("maksymalny czas oczekiwania: " +maxFDSCAN);
                System.out.println("sredni czas oczekiwania: "+avgFDSCAN);
                System.out.println("ilosc zadan obsluzonych: "+ cpu.menadzer.statListFDSCAN.size());
                System.out.println("ilosc zadan RT odrzuconych: "+ cpu.menadzer.zaglodzoneFDSCAN.size());
                System.out.println("WSPOLCZYNNIK: "+ (cpu.menadzer.StrategiaFDSCAN.dysk.zmiany/cpu.menadzer.statListFDSCAN.size()));
            }
            else if(in.equals("wyswietl")) // wyświetla listę procesów
            {
                System.out.println("Aktywne: FCFS");
                for (Zadanie z : cpu.menadzer.zadanieListFCFS) {
                    System.out.println(z);
                }
                System.out.println("Ukończone: FCFS");
                for (Zadanie z : cpu.menadzer.statListFCFS) {
                    System.out.println(z);
                }

                System.out.println("Aktywne: SSTF");
                for (Zadanie z : cpu.menadzer.zadanieListSSTF) {
                    System.out.println(z);
                }
                System.out.println("Ukończone: SSTF");
                for (Zadanie z : cpu.menadzer.statListSSTF) {
                    System.out.println(z);
                }

                System.out.println("Aktywne: SCAN");
                for (Zadanie z : cpu.menadzer.zadanieListSCAN) {
                    System.out.println(z);
                }
                System.out.println("Ukończone: SCAN");
                for (Zadanie z : cpu.menadzer.statListSCAN) {
                    System.out.println(z);
                }

                System.out.println("Aktywne: CSCAN ");
                for (Zadanie z : cpu.menadzer.zadanieListCSCAN) {
                    System.out.println(z);
                }
                System.out.println("Ukończone: CSCAN");
                for (Zadanie z : cpu.menadzer.statListCSCAN) {
                    System.out.println(z);
                }

                System.out.println("Aktywne: EDF  ");
                for (Zadanie z : cpu.menadzer.zadanieListEDF) {
                    System.out.println(z);
                }
                System.out.println("Aktywne: EDF RT");
                for (Zadanie z : cpu.menadzer.zadanieListRTEDF) {
                    System.out.println(z);
                }
                System.out.println("Ukończone: EDF");
                for (Zadanie z : cpu.menadzer.statListEDF) {
                    System.out.println(z);
                }
                System.out.println("Zagłodzone: EDF");
                for (Zadanie z : cpu.menadzer.zaglodzoneEDF) {
                    System.out.println(z);
                }


                System.out.println("Aktywne: FDSCAN  ");
                for (Zadanie z : cpu.menadzer.zadanieListEDF) {
                    System.out.println(z);
                }
                System.out.println("Aktywne: FDSCAN RT");
                for (Zadanie z : cpu.menadzer.zadanieListRTEDF) {
                    System.out.println(z);
                }
                System.out.println("Ukończone: FDSCAN");
                for (Zadanie z : cpu.menadzer.statListFDSCAN) {
                    System.out.println(z);
                }
                System.out.println("Zagłodzone: FDSCAN");
                for (Zadanie z : cpu.menadzer.zaglodzoneFDSCAN) {
                    System.out.println(z);
                }
            }
            else if(in.equals("glowica")){
                System.out.println("Zmiana głowicy, podaj poz i size:");
                cpu.menadzer.poz = sk.nextInt();
                cpu.menadzer.size= sk.nextInt();
            }
            else if(in.equals("gen")){
                System.out.println("Zmiana generatora, podaj czas");
                Generator.maxczasNextZad=sk.nextInt();
            }
            else if(in.equals("syt")){
                System.out.println("Zmiana sektora, 0 - wszytskie sektory, 1 - tylko 4 sektor, 2 - sektor 1 i 4");
                Generator.sytaucja=sk.nextInt();
            }
            in = sk.next();
        }
        cpu.aktywny=false;
        sk.close();
    }
}
