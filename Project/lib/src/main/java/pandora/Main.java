package pandora;

import java.util.Scanner;

public class Main {
	//parametry prywatne pozyskiwane getterami na samym dole klasy Main
	private static int x,y;
	private static int rider, archer, robot, soldier;
	private static int density;
	private static int iterations;

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
	public static int getX() {return x;}	//getter dlugosci mapy
	public static int getY() {return y;}	//getter szerokosci mapy

}
