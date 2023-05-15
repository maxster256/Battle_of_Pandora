package pandora;

public class Soldier extends Unit_Strength implements Interface{
	
	@Override
	public void move()
	{
		
	}
	@Override
	public void attack()
	{
		
	}
	public Soldier(int health, int speed, int pos_x, int pos_y,int strength, int index)
	{
		super(health,speed,pos_x,pos_y,strength,index);
	}
}
