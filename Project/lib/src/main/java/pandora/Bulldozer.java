package pandora;

public class Bulldozer extends Unit{
	
	public void move()
	{
		Random random = new Random();
		int direction = random.nextInt(4); // GGeneruj losową liczbę między 0-3
		
		if (direction == 0) { // Poruszaj się w górę
        		if (pos_y - 1 >= 0) { // Sprawdź, czy nowa pozycja mieści się w granicach siatki
           			if (isFieldEmpty(pos_x, pos_y - 1)) { // Sprawdź, czy pole jest puste
               				 pos_y--; // Przejdź na nową pozycję
            }
		else {
                	destroy(); // Zniszcz pole, jeśli nie jest puste
           	 }}}
		else if (direction == 1) { // Poruszaj się w dół
       			if (pos_y + 1 < GRID_SIZE) {
            			if (isFieldEmpty(pos_x, pos_y + 1)) {
              				pos_y++;
            } 
		else {
                destroy();
            }}}
		else if (direction == 2) { // Poruszaj się w lewo
       			if (pos_x - 1 >= 0) {
          			if (isFieldEmpty(pos_x - 1, pos_y)) {
             				pos_x--;
            }
		else {
                destroy();
            }}}
		else if (direction == 3) { // Poruszaj się w prawo
       			if (pos_x + 1 < GRID_SIZE) {
           			if (isFieldEmpty(pos_x + 1, pos_y)) {
              				pos_x++;
            } 
		else {
                destroy();
            }}}
		
	}
	public void destroy()
	{
		Map[pos_x][pos_y] = '_'; // Zamień zawartość pola na '_'
	}
	public Bulldozer(int health, int speed, int pos_x, int pos_y)
	{
		super(health,speed,pos_x,pos_y);
	}
}
