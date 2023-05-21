package pandora;

public abstract class Unit {
	int health;
	double speed;
	int pos_x;
	int pos_y;
	double moves;
	int index;
	
	public Unit(int health, double speed, int pos_x, int pos_y,int index)
	{
		this.health=health;
		this.speed=speed;
		this.pos_x=pos_x;
		this.pos_y=pos_y;
		this.index=index;
	}
}
