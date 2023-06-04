package pandora;

import java.util.Random;

public class Unit implements Interface{
	
	int type;
	char team;
	int health;
	double speed;
	int pos_x;
	int pos_y;
	double moves;
	int strength;
	double strength_bonus;
	
	@Override
	public void move(Map mapa)
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
		case 0: //Poruszaj się w górę
			if (pos_y - 1 >= 0) { // Sprawdź, czy nowa pozycja mieści się w granicach siatki
       			if (mapa.FieldContent(pos_x, pos_y - 1)!='T') {pos_y--;} // Sprawdz czy pole jest drzewem. Jesli nie, przejdź na nową pozycję
			}break;
		case 1: // Poruszaj się w dół
			if (pos_y + 1 < Main.getY()) {
    			if (mapa.FieldContent(pos_x, pos_y + 1)!='T') {pos_y++;}
			}break;
		case 2: // Poruszaj się w lewo
			if (pos_x - 1 >= 0) {
      			if (mapa.FieldContent(pos_x - 1, pos_y)!='T') {pos_x--;}
			}break;
		case 3: // Poruszaj się w prawo
			if (pos_x + 1 < Main.getX()) {
       			if (mapa.FieldContent(pos_x + 1, pos_y)!='T') {pos_x++;}
			}break;
		}
		moves--;
		}
	}
	@Override
	public void attack(Interface enemy, Map mapa)
	{
		((Unit)enemy).health-=strength;
	}
	public void far_attack(Interface enemy, Map mapa)
	{
		if(mapa.FieldContent(pos_x,pos_y)=='_') {((Unit)enemy).health-=strength;}
		else {((Unit)enemy).health-=strength*strength_bonus;}
   	}
	
	public Unit(int type,char team,int health, double speed, int pos_x, int pos_y,int strength,double strength_bonus)
	{
		this.type=type;						//pole type do okreslenia jakim typem jednostki jest dana jednostka
		this.team=team;						//pole team do okreslenia w jakiej druzynie znajduje sie jednostka
		this.health=health;					//pole health - poziom zycia jednostki
		this.speed=speed;					//pole speed - o ile srednio pol moze sie poruszyc w ciagu 1 tury
		this.pos_x=pos_x;					//pole pos_x - pozycja pozioma jednostki
		this.pos_y=pos_y;					//pole pos_y - pozycja pionowa jednostki
		this.strength=strength;				//pole strength - ile moze odebrac zycia przeciwnikowi
		this.strength_bonus=strength_bonus;	//pole strength_bonus - mnoznik do strength w przypadku gdy jednostka sie znajdzie w krzaku
	}
}
