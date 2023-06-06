package pandora;

public class Robot extends Unit{
	
	public Robot(int type,int health, double speed, int pos_x, int pos_y, int strength,double strength_bonus,double defense_bonus, boolean can_far_attack)
	{
		super(type,health,speed,pos_x,pos_y,strength,strength_bonus,defense_bonus,can_far_attack);
	}
}
