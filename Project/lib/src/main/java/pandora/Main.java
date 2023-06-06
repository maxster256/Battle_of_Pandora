package pandora;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Main {
	
	private static int x,y;
	private static int rider, archer, robot, soldier;
	private static int density;
	private static int iterations;
	private static int repeats;
	private static int show_visualization;
	private static int use_file;

	public static void main(String[] args) throws IOException
	{ 
		Scanner scan = new Scanner(System.in);
		File file = new File("sample.txt");										//plik do odczytu gotowych parametrow
		PrintWriter out = new PrintWriter("simulation.txt");					//plik do zapisu danych symulacji
		System.out.println("Odczytac parametry wejsciowe z pliku? (1 -> tak):");
		//use_file=scan.nextInt();	//przypisanie wartosci za pomoca Scannera
		use_file=1;					//przypisanie wartosci w celu zadzialania taska run
		if(use_file==1) {
			try {										//odczytanie zawartosci pliku sample.txt o ile istnieje
				FileReader reader = new FileReader(file);
				BufferedReader in = new BufferedReader(reader);
			    String sample;
			    int[] T= new int[9]; int i=0;
			    while((sample=in.readLine())!=null)
			    {
			    	T[i]=Integer.parseInt(sample); i++;	//zamiana sample z typu String na int i przypisanie do tablicy
			    }
			    x=T[0]; y=T[1]; rider=T[2]; archer=T[3]; robot=T[4]; soldier=T[5]; iterations=T[6]; density=T[7]; show_visualization=T[8];
			    Main.create_teams_and_map(x,y,rider,archer,robot,soldier,iterations,density,show_visualization,out);
			    in.close();
			} catch (IOException e) {
				System.out.println("Błąd odczytu pliku!");
			} 
		}
		else {	//wywolane jesli dane nie sa odczytywane z gotowego pliku
		System.out.println("Podaj rozmiary mapy:");
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
		System.out.println("Przedstawic wizualizacje symulacji? (1 -> tak):");
		show_visualization=scan.nextInt();
		System.out.println("Podaj liczbe powtorzen symulacji (dla wlaczonej wizualizacji zalecane jest 1):");
		repeats=scan.nextInt();
									//przekazanie niezbednych parametrow do wykonania symulacji
		for(int i=0;i<repeats;i++)	{Main.create_teams_and_map(x,y,rider,archer,robot,soldier,iterations,density,show_visualization,out);}
		
		}
		out.close();
		scan.close();
	}
	
	public static void create_teams_and_map(int x, int y, int rider, int archer, int robot, int soldier, int iterations, int density,int show_visualization,PrintWriter out) throws FileNotFoundException //metoda z uwzglednieniem parametrow wejsciowych np ze skryptow
	{
		Map mapa = new Map(x,y,density);	//przypisanie mapie podtsawowych wartosci: dlugosc, szerokosc, zageszczenie lasu
		mapa.generate();					//utworzenie mapy
		int spawn_x=x-1;					//umiejscowienie spawnu kolonizatorow w dolnym prawym rogu mapy
		int spawn_y=y-1;		
		
		Interface[] navi = new Interface[archer+rider];		//tablica typu Interface dla Navi
		Interface[] col = new Interface[robot+soldier+1];	//tablica typu Interface dla kolonizatorow
		//ponizsze pętle for tworza podana wczesniej liczbe jednostek poszczegolnych klas - podana liczbe razy tworza jednostke i dodaja ja do tablicy typu Interface
		//parametry konstruktorow danych klas uszeregowane sa w nastepujacy sposob:
		//(type,health,speed,pos_x,pos_y,strength,strength_bonus,defense_bonus,can_far_attack)
		
		//dla kolonizatorow i navi jednostki spawnuja sie tak aby zadne jednostki nie dzielily ze soba pola
		//dla kolonizatorow spawn zaczyna sie od prawej krawedzi mapy
		//dla navi spawn zaczyna sie od lewej krawedzi mapy
		//w przypadku wypelnienia ktores z krawedzi, do spawnu przydzielana jest nastepna kolumna w kierunku centrum mapy 
		for(int i=0;i<soldier;i++)	//petla for dla zolnierzy
		{
			if(spawn_y<0) {spawn_y=y-1; spawn_x--;}	// komentarz 92
			while(mapa.FieldContent(spawn_x,spawn_y)=='T'){spawn_y--; if(spawn_y<0) {spawn_y=y-1; spawn_x--;}}	//jesli pole jest drzewem to jednostka nie moze na nim sie pojawic (pojawi sie na pierwszym dostepnym polu 'B' lub '_')
			col[i+1] = new Soldier(1,100,1,spawn_x,spawn_y,40,0.5,0.2,true);
			spawn_y--;	//przesuniecie spawnu tak aby kazda jednostka miala wlasne miejsce spawnu
		}
		for(int i=0;i<robot;i++)	//petla for dla robotow
		{
			if(spawn_y<0) {spawn_y=y-1; spawn_x--;} // komentarz 92
			while(mapa.FieldContent(spawn_x,spawn_y)=='T'){spawn_y--; if(spawn_y<0) {spawn_y=y-1; spawn_x--;}}
			col[soldier+i+1] = new Robot(2,200,2,spawn_x,spawn_y,60,0.6,0.5,true);
			spawn_y--;
		}
		spawn_x=0;			//umiejscowienie spawnu navi w gornym lewym rogu mapy
		spawn_y=0;
		for(int i=0;i<rider;i++)	//petla for dla jezdzcow
		{
			if(spawn_y>=y) {spawn_y=0; spawn_x++;} // komentarz 92
			while(mapa.FieldContent(spawn_x,spawn_y)=='T'){spawn_y++; if(spawn_y>=y) {spawn_y=0; spawn_x++;}}
			navi[i] = new Rider(3,100,2,spawn_x,spawn_y,60,2,0.3,false);
			spawn_y++;
		}
		for(int i=0;i<archer;i++)	//petla for dla lucznikow
		{
			if(spawn_y>=y) {spawn_y=0; spawn_x++;} // komentarz 92
			while(mapa.FieldContent(spawn_x,spawn_y)=='T'){spawn_y++; if(spawn_y>=y) {spawn_y=0; spawn_x++;}}
			navi[rider+i] = new Archer(4,100,1,spawn_x,spawn_y,40,1.7,0.25,true);
			spawn_y++;
		}
		Bulldozer bulldozer = new Bulldozer(0,2000,0.25,x/2-1,y/2-1,1000,1,0.85,false);
		col[0] = bulldozer;	//(123-124)utworzenie buldozera i dodanie go do tablicy kolonizatorow na pierwsza pozycje
		
		if(show_visualization==1){
		mapa.images();	//zaladowanie plikow .png
		mapa.Frame(navi,col); //wyswietlenie mapy przed rozpoczeciem symulacji
		}	
		
		switch(Main.simulation(mapa,navi,col,show_visualization,out))	//wywolanie symulacji
		{
			case 0: System.out.println("Symulacja zakonczyla sie bez rozstrzygniecia"); break;
			case 1: System.out.println("Symulacja zakonczyla sie smiercia Navi"); break;
			case 2: System.out.println("Symulacja zakonczyla sie smiercia kolonizatorow"); break; 
		}
		if(show_visualization==1) {mapa.Frame(navi,col);} //wyswietlenie zawartosci mapy po zakonczeniu symulacji
	}
	
	public static int simulation(Map mapa, Interface[] navi, Interface[] col,int show_visualization,PrintWriter out) throws FileNotFoundException
	{
		out.println("iteration;navi count;colonizators count;trees;bushes;all map squares");//pierwszy wiersz pliku z danymi zebranymi z symulacji
		
		int navi_counter=navi.length;			//zapisanie liczby powstalych jednostek navi
		int colonizators_counter=col.length;	//zapisanie liczby powstalych jednostek kolonizatorow
		
		if(show_visualization==1) {
		try {Thread.sleep(3000);}			//odczekanie 3 sekund aby uzytkownik mogl otworzyc mape na czas
		catch (InterruptedException e) {e.printStackTrace();}
		}
		
		Bulldozer bulldozer = (Bulldozer)col[0];
		boolean attack_flag=false;	//flaga informujaca czy jednostka wykonala juz atak. Jesli tak to nie wykona ruchu w danej iteracji
		
		int Xn,Yn;		//lokalizacja danej jednostki navi
		int Xc,Yc;		//lokazlizacja danej jednostki kolonizatorow
		
		for(int i=0;i<iterations;i++)	//petla for do wykonania symulacji
		{
			if(show_visualization==1) {
			mapa.Frame(navi,col);					//wizualizacja mapy podczas danej tury
			try {Thread.sleep(500);}				//odczekanie 0.5 sekundy w celu wyswietlenia pojedynczej iteracji symulacji
			catch (InterruptedException e) {e.printStackTrace();}
			}
			
			if(bulldozer.health>0) {bulldozer.moveBulldozer(mapa);}					//wykonanie metody moveBulldozer dla buldozera
			for(Interface NAVI : navi)	//petla foreach dla Navi
			{
				if(((Unit)NAVI).health>0) {	//uniemozliwienie wykonania czegokolwiek jesli jednostka jest martwa
				attack_flag=false;
				Xn = ((Unit)NAVI).pos_x;
				Yn = ((Unit)NAVI).pos_y;
				if(bulldozer.pos_x==Xn && bulldozer.pos_y==Yn && bulldozer.health>0) {bulldozer.attack(NAVI,mapa); navi_counter--;}	//jesli buldozer najechal na dana jednostke Navi to ja zaatakuje (zabije)
				else {
				for(Interface COL : col)	//petla foreach do znalezienia przeciwnika do zaatakowania
				{
					if(((Unit)COL).health>0) { //uniemozliwienie zaatakowania jednostki juz martwej
						Xc=((Unit)COL).pos_x;
						Yc=((Unit)COL).pos_y;
						if(Math.abs(Xc-Xn)+Math.abs(Yc-Yn)<=1) {NAVI.attack(COL,mapa); attack_flag=true;}	//wykonanie ataku jesli wroga jednostka jest w bezposrednim sasiedztwie (do 1 kratki odleglosci)
						else if(Math.abs(Xc-Xn)+Math.abs(Yc-Yn)<=5 && ((Unit)NAVI).can_far_attack) {NAVI.far_attack(COL,mapa); attack_flag=true;} //wykonanie ataku jesli wroga jednostka jest w odleglosci do 5 kratek
						if(((Unit)COL).health<=0) {colonizators_counter--;}
					break;} //jesli atak zostal wykonany to zakonczyc szukanie przeciwnika
				}
				if(attack_flag==false) {NAVI.move(mapa);}}} //wykonanie metody move dla pozostalych jednostek (pod warunkiem ze nie wykonaly ataku)						
			}
			for(Interface COL : col)	//petla foreach dla kolonizatorow
			{
				if(((Unit)COL).health>0 && ((Unit)COL).type!=0) { //uniemozliwienie wykonania czegokolwiek jesli jednostka jest martwa lub jest buldozerem (we wlasny sposob wykonuje mozliwe ruchy)
				attack_flag=false;
				Xc=((Unit)COL).pos_x;
				Yc=((Unit)COL).pos_y;
				for(Interface NAVI : navi) //petla foreach do znalezienia przeciwnika do zaatakowania
				{
					if(((Unit)NAVI).health>0) {	//uniemozliwienie zaatakowania jednostki juz martwej
						Xn=((Unit)NAVI).pos_x;
						Yn=((Unit)NAVI).pos_y;
						if(Math.abs(Xc-Xn)+Math.abs(Yc-Yn)<=1) {COL.attack(NAVI,mapa); attack_flag=true;} //wykonanie ataku jesli wroga jednostka jest w bezposrednim sasiedztwie (do 1 kratki odleglosci)
						else if(Math.abs(Xc-Xn)+Math.abs(Yc-Yn)<=3) {COL.far_attack(NAVI,mapa); attack_flag=true;} //wykonanie ataku jesli wroga jednostka jest w odleglosci do 3 kratek
						if(((Unit)NAVI).health<=0) {navi_counter--;}
					break;} //jesli atak zostal wykonany to zakonczyc szukanie przeciwnika
				}
				if(attack_flag==false) {COL.move(mapa);}} //wykonanie metody move dla pozostalych jednostek (pod warunkiem ze nie wykonaly ataku)
			}
			if(i%500==0) {out.println(i+";"+navi_counter+";"+colonizators_counter+";"+mapa.getTrees_numb()+";"+mapa.getBushes_numb());}
			//zapisanie pozadanych danych
		}
		if(navi_counter==0) {return 1;}
		if(colonizators_counter==0) {return 2;}
		return 0;
	}
	public static int getX() {return x;}
	public static int getY() {return y;}
}
