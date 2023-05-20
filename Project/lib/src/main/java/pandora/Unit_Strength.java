package pandora;

public abstract class Unit_Strength extends Unit{
	int strength;
	int index;
	
	public Unit_Strength(int health, double speed, int pos_x, int pos_y,int strength, int index)
	{
		super(health,speed,pos_x,pos_y);
	}
}
