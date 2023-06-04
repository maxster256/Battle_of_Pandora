package pandora;

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
		//mapa.display();
		int spawn_x=x-1;		//umiejscowienie spawnu kolonizatorow w dolnym prawym rogu mapy
		int spawn_y=y-1;		
		Interface[] tab = new Interface[soldier+robot+rider+archer+1];	
		//ponizsze pętle for tworza podana wczesniej liczbe jednostek poszczegolnych klas - podana liczbe razy tworza jednostke i dodaja ja do tablicy z polimorfizmu
		//parametry konstruktorow danych klas uszeregowane sa w nastepujacy sposob: (type,team,health,speed,pos_x,pos_y,strength,strength_bonus)
		
		//dla kolonizatorow i navi jednostki spawnuja sie tak aby zadne jednostki nie dzielily ze soba pola
		//dla kolonizatorow spawn zaczyna sie od prawej krawedzi mapy
		//dla navi spawn zaczyna sie od lewej krawedzi mapy
		//w przypadku wypelnienia ktores z krawedzi, do spawnu przydzielana jest nastepna kolumna w kierunku centrum mapy 
		for(int i=0;i<soldier;i++)	//petla for dla zolnierzy
		{
			if(spawn_y<0) {spawn_y=y-1; spawn_x--;}	// komentarz 49
			while(mapa.FieldContent(spawn_x,spawn_y)=='T'){spawn_y--;}	//jesli pole jest drzewem to jednostka nie moze na nim sie pojawic (pojawi sie na pierwszym polu B lub _)
			tab[i+1] = new Soldier(1,'C',100,1,spawn_x,spawn_y,40,0.5);
			spawn_y--;	//przesuniecie spawnu tak aby kazda jednostka miala wlasne miejsce spawnu
		}
		for(int i=0;i<robot;i++)	//petla for dla robotow
		{
			if(spawn_y<0) {spawn_y=y-1; spawn_x--;} // komentarz 49
			while(mapa.FieldContent(spawn_x,spawn_y)=='T'){spawn_y--;}
			tab[soldier+i+1] = new Robot(2,'C',200,2,spawn_x,spawn_y,60,0.5);
			spawn_y--;
		}
		spawn_x=0;			//umiejscowienie spawnu navi w gornym lewym rogu mapy
		spawn_y=0;
		for(int i=0;i<rider;i++)	//petla for dla jezdzcow
		{
			if(spawn_y>=y) {spawn_y=0; spawn_x++;} // komentarz 49
			while(mapa.FieldContent(spawn_x,spawn_y)=='T'){spawn_y++;}
			tab[robot+soldier+i+1] = new Rider(3,'N',100,2,spawn_x,spawn_y,40,2);
			spawn_y++;
		}
		for(int i=0;i<archer;i++)	//petla for dla lucznikow
		{
			if(spawn_y>=y) {spawn_y=0; spawn_x++;} // komentarz 49
			while(mapa.FieldContent(spawn_x,spawn_y)=='T'){spawn_y++;}
			tab[rider+robot+soldier+i+1] = new Archer(4,'N',100,1,spawn_x,spawn_y,40,2);
			spawn_y++;
		}
		Bulldozer bull = new Bulldozer(0,'C',500,0.25,x/2-1,y/2-1,500,1);
		tab[0] = bull;	//utworzenie buldozera na ostatniej pozycji tablicy w polimorfizmie
		mapa.images();	//zaladowanie plikow .png
		mapa.Frame(tab);	//wyswietlenie mapy przed rozpoczeciem symulacji
		try {Thread.sleep(10000);}			//odczekanie 10 sekund aby uzytkownik mogl otworzyc mape na czas
		catch (InterruptedException e) {e.printStackTrace();}
		
		for(int i=0;i<iterations;i++)	//petla for do wykonania symulacji
		{
			mapa.Frame(tab);					//wizualizacja mapy podczas danej tury
			try {Thread.sleep(500);}		//odczekanie 0.5 sekundy w celu wyswietlenia symulacji
			catch (InterruptedException e) {e.printStackTrace();}
			//powyzsze kroki sa do pominiecia jesli sama wizualizacja symulacji nie jest brana pod uwage (np przy zbieraniu danych symulacji)
			bull.moveBulldozer(mapa);					//wykonanie metody moveBulldozer dla buldozera
			for(int index=1;index<tab.length;index++)
			{
				tab[index].move(mapa);					//wykonania metody move dla pozostalych jednostek
			}
		}
	}
	public static int getX() {return x;}
	public static int getY() {return y;}
}
