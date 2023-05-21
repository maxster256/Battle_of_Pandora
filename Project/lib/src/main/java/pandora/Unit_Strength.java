package pandora;

public abstract class Unit_Strength extends Unit{
	int strength;
	
	public Unit_Strength(int health, double speed, int pos_x, int pos_y,int index,int strength)
	{
		super(health,speed,pos_x,pos_y,index);
		this.strength=strength;
	}
}
