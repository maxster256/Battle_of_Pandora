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
	double defense_bonus;
	boolean can_far_attack;
	
	Random random = new Random();
	
	@Override
	public void move(Map mapa)
	{
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
		if(type==0) {((Unit)enemy).health=0;} //jesli atakujacy to buldozer to zabija przeciwnika
		else if(random.nextFloat(1)>((Unit)enemy).defense_bonus)	//losowanie szansy na obrone ataku
		{
		if(mapa.FieldContent(pos_x,pos_y)=='_') {((Unit)enemy).health-=strength;}	//atak z pustego pola
		else {((Unit)enemy).health-=strength*strength_bonus;}						//atak z krzakow
		}
	}
	@Override
	public void far_attack(Interface enemy, Map mapa)
	{
		int i;	//zmienna do okreslenia kierunku ataku
		boolean attack_blocked=false;	//flaga do okreslenia czy atak zostal zablokowany przez drzewo
		if(((Unit)enemy).pos_x-pos_x==0)
		{
			if(((Unit)enemy).pos_y-pos_y>0) {i=1;}
			else 							{i=-1;}
			if(mapa.FieldContent(pos_x,pos_y+i)=='T'||mapa.FieldContent(pos_x,pos_y+2*i)=='T') {attack_blocked=true;}
			else if ((type==3||type==4) && pos_y+4*i>0 && pos_y+4*i<Main.getY())
			{if(mapa.FieldContent(pos_x,pos_y+3*i)=='T'||mapa.FieldContent(pos_x,pos_y+4*i)=='T') {attack_blocked=true;}}
		}
		else if (((Unit)enemy).pos_y-pos_y==0)
		{
			if(((Unit)enemy).pos_x-pos_x>0) {i=1;}
			else 							{i=-1;}
			if(mapa.FieldContent(pos_x+i,pos_y)=='T'||mapa.FieldContent(pos_x+2*i,pos_y)=='T') {attack_blocked=true;}
			else if ((type==3||type==4) && pos_x+4*i>0 && pos_x+4*i<Main.getX())
			{if(mapa.FieldContent(pos_x+3*i,pos_y)=='T'||mapa.FieldContent(pos_x+4*i,pos_y)=='T') {attack_blocked=true;}}
		}
		if(attack_blocked==false)	//jesli atak nie zostal zablokowany wykonac atak
		{
			double defense;
			if(((Unit)enemy).type==3||((Unit)enemy).type==4) 	{defense = ((Unit)enemy).defense_bonus;}
			else 												{defense = ((Unit)enemy).defense_bonus*((Unit)enemy).defense_bonus;}
																//przy dalekim ataku ze strony navi jednostki kolonizatorow maja zmniejszona szanse na obrone
			if(random.nextFloat(1)>defense)		//losowanie szansy na obrone ataku
			{
				if(mapa.FieldContent(pos_x,pos_y)=='_') {((Unit)enemy).health-=strength;}	//atak z pustego pola
				else {((Unit)enemy).health-=strength*strength_bonus*strength_bonus;}		//atak z krzakow
			}
		}
   	}
	
	public Unit(int type,int health, double speed, int pos_x, int pos_y,int strength,double strength_bonus,double defense_bonus,boolean can_far_attack)
	{
		this.type=type;	
		this.health=health;	
		this.speed=speed;	
		this.pos_x=pos_x;
		this.pos_y=pos_y;	
		this.strength=strength;	
		this.strength_bonus=strength_bonus;
		this.defense_bonus=defense_bonus;
		this.can_far_attack=can_far_attack;
	}
}
