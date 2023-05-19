package pandora;

import java.util.Random;

public class Bulldozer extends Unit{
	
	Map mapa = new Map();
	
	public void move()
	{
		Random random = new Random();
		int direction;
		moves = speed;
		while(moves>0)	//petla wykonuje przewidziana liczbe ruchow dla jednostki np: dla speed=2 petla wykona się 2 razy
		{
			if(moves<1){	// gdy liczba ruchow wyniesie wiecej niz 0 ale mniej niz 1 instrukcja wykona ponizszy krok.
				if(random.nextFloat(1)<moves) {break;} //jesli predkosc jest wyrazona jako liczba niecalkowita to instrukcja if wylosuje czy ma wykonac dodatkowy ruch gdy liczba ruchow spadnie ponizej 1
			}
		direction = random.nextInt(4); // Generuj losową liczbę między 0-3
		
		// Poruszaj się w górę
		if (direction == 0) {
        		if (pos_y - 1 >= 0) { // Sprawdź, czy nowa pozycja mieści się w granicach siatki
           			if (mapa.isFieldEmpty(pos_x, pos_y - 1)) { // Sprawdź, czy pole jest puste
               				 pos_y--; // Przejdź na nową pozycję
           	}
				else {
					destroy(); // Zniszcz pole, jeśli nie jest puste
					pos_y--;
           	}}}
		// Poruszaj się w dół
		else if (direction == 1) { 
       			if (pos_y + 1 < Main.y) {
            			if (mapa.isFieldEmpty(pos_x, pos_y + 1)) {
              				pos_y++;
            	} 
				else {
					destroy();
					pos_y++;
            	}}}
		// Poruszaj się w lewo
		else if (direction == 2) {
       			if (pos_x - 1 >= 0) {
          			if (mapa.isFieldEmpty(pos_x - 1, pos_y)) {
             				pos_x--;
          	}
				else {
					destroy();
					pos_x--;
        	}}}
		// Poruszaj się w prawo
		else if (direction == 3) {
       			if (pos_x + 1 < Main.x) {
           			if (mapa.isFieldEmpty(pos_x + 1, pos_y)) {
              				pos_x++;
           	} 
				else {
					destroy();
					pos_x++;
            }}}	
		moves--;
		}
	}
	public void destroy()
	{
		mapa.change_map(pos_x,pos_y); // wywolanie metody z klasy Map odpowiedzialnej za zmiane zawartosci danego pola mapy
	}
	public Bulldozer(int health, int speed, int pos_x, int pos_y)
	{
		super(health,speed,pos_x,pos_y);
	}
}
