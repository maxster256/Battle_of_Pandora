package pandora;

import java.util.Scanner;

public class Main {

	public static void main(String[] args)	//metoda na parametry wejsciowe
	{
		int x,y;
		int rider, archer, robot, soldier;
		int density;
		int iterations;
		Scanner scan = new Scanner(System.in);
		System.out.println("Podaj rozmiar mapy:");
		x = scan.nextInt();
		y = scan.nextInt();
		System.out.println("Podaj liczebnosc jezdzcow:");
		rider=scan.nextInt();
		System.out.println("Podaj liczebnosc lucznikow:");
		archer=scan.nextInt();
		System.out.println("Podaj liczebnosc robotow:");
		robot=scan.nextInt();
		System.out.println("Podaj liczebnosc zolnierzy:");
		soldier=scan.nextInt();
		System.out.println("Podaj zageszczenie lasu:");
		density=scan.nextInt();
		System.out.println("Podaj liczbe iteracji:");
		iterations=scan.nextInt();
		//...
		Main.simulation(x,y,rider,archer,robot,soldier,iterations,density);
		scan.close();
	}
	
	public static void simulation(int x, int y, int rider, int archer, int robot, int soldier, int iterations, int density) //metoda na wypadek skryptow z podanymi parametrami
	{
		Map mapa = new Map(x,y,density);
		mapa.generate();		//stworzenie mapy
		for(int i=0;i<iterations;i++)
		{
			//wykonanie symulacji	
		}
	}

}
