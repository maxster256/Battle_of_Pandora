package pandora;

public abstract class Unit {
	int health;
	int speed;
	int pos_x;
	int pos_y;
	public Unit(int health, int speed, int pos_x, int pos_y)
	{
		this.health=health;
		this.speed=speed;
		this.pos_x=pos_x;
		this.pos_y=pos_y;
	}
}
