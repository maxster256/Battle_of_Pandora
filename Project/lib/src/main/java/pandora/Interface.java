package pandora;

public interface Interface {
	public void move(Map mapa);
	public void attack(Interface enemy, Map mapa);
	public void far_attack(Interface enemy, Map mapa);
	public void find_enemy(Interface[] enemy, Map mapa);
}
