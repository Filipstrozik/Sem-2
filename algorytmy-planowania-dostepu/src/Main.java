import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

	    CPU cpu = new CPU(new Menadzer(new Generator()));
	    cpu.aktywny=true;
	    Scanner sk = new Scanner(System.in);
	    String in = sk.next();
	    while (!in.equals("quit")){
	    	if(in.equals("start")){
	    		cpu.start();

				cpu.aktywny=true;

				System.out.println("start cpu...");
			}
	    	else if(in.equals("wyniki"))
			{
				int avgFCFS = 0;
				int maxFCFS = 0;
				int avgSJF = 0;
				int maxSJF = 0;
				int avgRR = 0;
				int maxRR = 0;

				System.out.println("Wykonanych cykli: " + cpu.menadzer.czasDzialania);
				System.out.println("maksymalny losowy czas pojwanienia sie procesu: " + Generator.maxczasNextproc);
				System.out.println("maksymalny losowy czas trwania procesu: " + Generator.maxCzasProcesu);
				System.out.println("kwant czasu RR: "+RoundRobin.kwant);


				for(Proces p : cpu.menadzer.statListFCFS)
				{
					if(maxFCFS<p.czasOczekiwania)
						maxFCFS= p.czasOczekiwania;
					avgFCFS+=p.czasOczekiwania;
				}
				if(!cpu.menadzer.statListFCFS.isEmpty())
					avgFCFS = avgFCFS/cpu.menadzer.statListFCFS.size();

				System.out.println("FCFS");
				System.out.println("maksymalny czas oczekiwania: " +maxFCFS);
				System.out.println("sredni czas oczekiwania: "+avgFCFS);
				System.out.println("ilosc procesow wykonanych: "+ cpu.menadzer.statListFCFS.size());
				System.out.println("Wykonanych przełączen: " + cpu.menadzer.AlgorytmFCFS.liczbaPrzelaczen);
				System.out.println("Ilosc procesow zaglodzonych: " + cpu.menadzer.zaglodzoneFCFS.size());


				for(Proces p : cpu.menadzer.statListSJF)
				{
					if(maxSJF<p.czasOczekiwania)
						maxSJF= p.czasOczekiwania;
					avgSJF+=p.czasOczekiwania;
				}
				if(!cpu.menadzer.statListSJF.isEmpty())
					avgSJF = avgSJF/cpu.menadzer.statListSJF.size();
				System.out.println("SJF");
				System.out.println("maksymalny czas oczekiwania: " +maxSJF);
				System.out.println("sredni czas oczekiwania: "+avgSJF);
				System.out.println("ilosc procesow wykonanych: "+ cpu.menadzer.statListSJF.size());
				System.out.println("Wykonanych przełączen: " + cpu.menadzer.AlgorytmSJF.liczbaPrzelaczen);
				System.out.println("Ilosc procesow zaglodzonych: " + cpu.menadzer.zaglodzoneSJF.size());

				for(Proces p : cpu.menadzer.statListRR)
				{
					if(maxRR<p.czasOczekiwania)
						maxRR= p.czasOczekiwania;
					avgRR+=p.czasOczekiwania;
				}
				if(!cpu.menadzer.statListRR.isEmpty())
					avgRR = avgRR/cpu.menadzer.statListRR.size();
				System.out.println("RR");
				System.out.println("maksymalny czas oczekiwania: " +maxRR);
				System.out.println("sredni czas oczekiwania: "+avgRR);
				System.out.println("ilosc procesow wykonanych: "+ cpu.menadzer.statListRR.size());
				System.out.println("Wykonanych przełączen: " + cpu.menadzer.AlgorytmRR.liczbaPrzelaczen);
				System.out.println("Ilosc procesow zaglodzonych: " + cpu.menadzer.zaglodzoneRR.size());

			}
			else if(in.equals("wyswietl")) // wyświetla listę procesów
			{
				System.out.println("Aktywne: ");
				for(Proces p : cpu.menadzer.procesListFCFS)
				{
					System.out.println(p);
				}
				System.out.println("Ukończone: ");
				for(Proces p : cpu.menadzer.statListFCFS)
				{
					System.out.println(p);
				}
				System.out.println("Aktywne: ");
				for(Proces p : cpu.menadzer.procesListSJF)
				{
					System.out.println(p);
				}
				System.out.println("Ukończone: ");
				for(Proces p : cpu.menadzer.statListSJF)
				{
					System.out.println(p);
				}
				System.out.println("Aktywne: ");
				for(Proces p : cpu.menadzer.procesListRR)
				{
					System.out.println(p);
				}
				System.out.println("Ukończone: ");
				for(Proces p : cpu.menadzer.statListRR)
				{
					System.out.println(p);
				}
			}
			else if(in.equals("genzmien"))
			{
				System.out.println("podaj max czas trwania procesu");
				Generator.maxCzasProcesu = sk.nextInt();
				System.out.println("podaj max czas pojawienia sie procesu");
				Generator.maxczasNextproc = sk.nextInt();
			}
			else if(in.equals("rrzmien"))
			{
				System.out.println("podaj kwant czasu");
				RoundRobin.kwant = sk.nextInt();
			}
			in = sk.next();

		}
	    cpu.aktywny=false;
	    sk.close();
    }
}
