package pandora;

import java.util.ArrayList;
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
		
		ArrayList<Object> Soldiers = new ArrayList<Object>();	//lista zolnierzy
		ArrayList<Object> Robots = new ArrayList<Object>();	//lista robotow
		ArrayList<Object> Bulldozers = new ArrayList<Object>();	//lista buldozerow - domyslne jest tylko jeden
		ArrayList<Object> Archers = new ArrayList<Object>();	//lista lucznikow
		ArrayList<Object> Riders = new ArrayList<Object>();	//lista jezdzcow
		
		ArrayList<ArrayList<Object>> Navi = new ArrayList<ArrayList<Object>>();		//lista zagniezdzona strony navi - lucznikow i jezdzcow
		ArrayList<ArrayList<Object>> Colonizators = new ArrayList<ArrayList<Object>>();	//lista zagniezdzona strony kolonizatorow - zolnierzy, robotow i buldozera
		
		Bulldozer Bulldozer = new Bulldozer(500,0.25,x-1,y-1,0);	//utworzenie buldozera
		Bulldozers.add(Bulldozer);					//dodanie buldozera do listy
		//ponizsze pętle for tworza podana wczesniej liczbe jednostek poszczegolnych klas - podana liczbe razy tworza jednostke i dodaja ja do odpowiedniej listy
		//parametry konstruktorow danych klas uszeregowane sa w nastepujacy sposob: (health,speed,pos_x,pos_y,index,strength)
		for(int i=0;i<soldier;i++)	//petla for dla zolnierzy
		{
			Soldier Soldier = new Soldier(100,1,0,0,i+1,40);
			Soldiers.add(Soldier);
		}
		for(int i=0;i<robot;i++)	//petla for dla robotow
		{
			Robot Robot = new Robot(200,2,0,0,i+1+soldier,60);
			Robots.add(Robot);
		}
		for(int i=0;i<rider;i++)	//petla for dla jezdzcow
		{
			Rider Rider = new Rider(100,2,0,0,i+1,40);
			Riders.add(Rider);
		}
		for(int i=0;i<archer;i++)	//petla for dla lucznikow
		{
			Archer Archer = new Archer(100,1,0,0,i+1+rider,40);
			Archers.add(Archer);
		}
		
		Navi.add(Riders);		//dodanie listy jezdzcow do listy navi
		Navi.add(Archers);		//dodanie listy lucznikow do listy navi
		Colonizators.add(Bulldozers);	//dodanie listy buldozera do listy kolonizatorow
		Colonizators.add(Soldiers);	//dodanie listy zolnierzy do listy kolonizatorow
		Colonizators.add(Robots);	//dodanie listy robotow do listy kolonizatorow
		
		for(int i=0;i<iterations;i++)	//petla for do wykonania symulacji
		{
			//wykonanie symulacji
		}
	}
	public static int getX() {return x;}	//getter dlugosci mapy
	public static int getY() {return y;}	//getter szerokosci mapy

}
