package pandora;

import java.util.Random;

public class Archer extends Unit_Strength implements Interface{
	
	Map mapa = new Map();
	
	@Override
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
		direction = random.nextInt(4); // GGeneruj losową liczbę między 0-3
		
		// Poruszaj się w górę
		if (direction == 0) {
        		if (pos_y - 1 >= 0) { // Sprawdź, czy nowa pozycja mieści się w granicach siatki
           			if (mapa.isFieldaTree(pos_x, pos_y - 1)==false) {pos_y--;} // Sprawdz czy pole jest drzewem. Jesli nie, przejdź na nową pozycję
           	}}
		// Poruszaj się w dół
		else if (direction == 1) { 
       			if (pos_y + 1 < Main.y) {
            			if (mapa.isFieldaTree(pos_x, pos_y + 1)==false) {pos_y++;}
            	}}
		// Poruszaj się w lewo
		else if (direction == 2) {
       			if (pos_x - 1 >= 0) {
          			if (mapa.isFieldaTree(pos_x - 1, pos_y)==false) {pos_x--;}
        	}}
		// Poruszaj się w prawo
		else if (direction == 3) {
       			if (pos_x + 1 < Main.x) {
           			if (mapa.isFieldaTree(pos_x + 1, pos_y)==false) {pos_x++;}
            }}
		moves--;
		}
	}
	@Override
	public void attack()
	{
		
	}
	public Archer(int health, int speed, int pos_x, int pos_y,int strength, int index)
	{
		super(health,speed,pos_x,pos_y,strength,index);
	}
}
