package pandora;

import java.util.Random;

public class Bulldozer extends Unit{
	
	public void moveBulldozer(Map mapa)
	{
		Random random = new Random();
		int direction;
		moves = speed;
		while(moves>0)	//petla wykonuje przewidziana liczbe ruchow dla jednostki np: dla speed=2 petla wykona się 2 razy
		{
			if(moves<1){	// gdy liczba ruchow wyniesie wiecej niz 0 ale mniej niz 1 instrukcja wykona ponizszy krok.
				if(random.nextFloat(1)<moves) {break;} //jesli predkosc jest wyrazona jako liczba niecalkowita to instrukcja if wylosuje czy ma wykonac dodatkowy ruch gdy liczba ruchow spadnie ponizej 1
			}
		direction = random.nextInt(4); // GGeneruj losową liczbę między 0-3
		switch(direction)
		{
		case 0: // Poruszaj się w gore
			if (pos_y - 1 >= 0) { // Sprawdź, czy nowa pozycja mieści się w granicach siatki
    			pos_y--; // Przejdź na nową pozycję
       			if (mapa.FieldContent(pos_x, pos_y)!='_') {destroy(mapa);} // Sprawdź, czy pole jest puste i zniszcz pole, jeśli nie jest puste
			}break;
		case 1: // Poruszaj się w dol
			if (pos_y + 1 < Main.getY()) {
   				pos_y++;
        		if (mapa.FieldContent(pos_x, pos_y)!='_') {destroy(mapa);}
        	}break;
		case 2: // Poruszaj się w lewo
			if (pos_x - 1 >= 0) {
   				pos_x--;
      			if (mapa.FieldContent(pos_x, pos_y)!='_') {destroy(mapa);}
			}break;
		case 3: // Poruszaj się w prawo
			if (pos_x + 1 < Main.getX()) {
   				pos_x++;
       			if (mapa.FieldContent(pos_x, pos_y)!='_') {destroy(mapa);}
			}break;
		}
		moves--;
		}
	}
	public void destroy(Map mapa)
	{
		mapa.change_map(pos_x,pos_y); // wywolanie metody z klasy Map odpowiedzialnej za zmiane zawartosci danego pola mapy
	}
	public Bulldozer(int type,char team,int health, double speed, int pos_x, int pos_y, int strength,double strength_bonus)
	{
		super(type,team,health,speed,pos_x,pos_y,strength,strength_bonus);
	}
}
