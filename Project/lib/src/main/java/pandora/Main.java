package pandora;

import java.util.Scanner;

public class Main {
	
	static int x,y;
	static int rider, archer, robot, soldier;
	static int density;
	static int iterations;

	public static void main(String[] args)
	{ 
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
		Main.simulation(x,y,rider,archer,robot,soldier,iterations,density);
		scan.close();
	}
	
	public static void simulation(int x, int y, int rider, int archer, int robot, int soldier, int iterations, int density) //metoda z uwzglednieniem parametrow wejsciowych np ze skryptow
	{
		Map mapa = new Map(x,y,density);
		mapa.generate();		//utworzenie mapy
		mapa.display();
		for(int i=0;i<iterations;i++)
		{
			//wykonanie symulacji
		}
	}

}
