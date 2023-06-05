package pandora;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
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
		Interface[] tab = new Interface[soldier+robot+rider+archer+1];
		
		//ponizsze pętle for tworza podana wczesniej liczbe jednostek poszczegolnych klas - podana liczbe razy tworza jednostke i dodaja ja do tablicy z polimorfizmu
		//parametry konstruktorow danych klas uszeregowane sa w nastepujacy sposob: (team,health,speed,pos_x,pos_y,strength,strength_bonus)
		for(int i=0;i<soldier;i++)	//petla for dla zolnierzy
		{
			tab[i] = new Soldier('C',100,1,0,0,40,0.5);
		}
		for(int i=0;i<robot;i++)	//petla for dla robotow
		{
			tab[soldier+i] = new Robot('C',200,2,0,0,60,0.5);
		}
		for(int i=0;i<rider;i++)	//petla for dla jezdzcow
		{
			tab[robot+soldier+i] = new Rider('N',100,2,0,0,40,2);
		}
		for(int i=0;i<archer;i++)	//petla for dla lucznikow
		{
			tab[rider+robot+soldier+i] = new Archer('N',100,1,0,0,40,2);
		}
		tab[soldier+robot+rider+archer] = new Bulldozer('C',500,0.25,x-1,y-1,500,1);	//utworzenie buldozera na ostatniej pozycji tablicy w polimorfizmie
		
		for(Interface i: tab)	//petla for do wykonania symulacji
		{
			//Czy mozliwy atak
			//ewentualny atak

			for(Interface j: tab){
				if(i!=j && j.getTeam()!=i.getTeam()) i.attack(j,mapa);
			
			//ponizej przyklady dzialania metody atak z uzyciem obiektow oraz przykladowa petla (tylko) sprawdzajaca mozliwosc wykonania ataku bezposredniego
			/*
			System.out.println("Health of soldier: "+((Unit)tab[0]).health); //soldier
			System.out.println("Health of rider: "+((Unit)tab[2]).health); //rider
			tab[0].attack(tab[2],mapa);
			System.out.println("Health of soldier: "+((Unit)tab[0]).health); //soldier
			System.out.println("Health of rider: "+((Unit)tab[2]).health); //rider	
			for(int j=0;j<tab.length;j++)
			{
				for(int k=0;k<tab.length;k++)
				{
					if(((Unit)tab[j]).team!=((Unit)tab[k]).team && Math.abs(((Unit)tab[j]).pos_x-((Unit)tab[k]).pos_x)<=1 && Math.abs(((Unit)tab[j]).pos_y-((Unit)tab[k]).pos_y)<=1)
					{
						tab[j].attack(tab[k],mapa);
					}
				}
			}
			*/
		}
	}
	public static int getX() {return x;}
	public static int getY() {return y;}
}
