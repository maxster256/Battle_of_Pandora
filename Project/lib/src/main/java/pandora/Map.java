package pandora;

import java.util.Random;

public class Map {
	
	private int width;
    private int height;
    private int density; // zagęszczenie roślin w procentach
    private int Trees_numb;
    private int Bushes_numb;
    private char[][] map;
	
    public Map()
    {
    	
    }
	public Map(int width, int height, int plantDensity) {
        this.width = width;
        this.height = height;
        this.density = plantDensity;
        this.map = new char[height][width];
        this.Trees_numb = 0;
        this.Bushes_numb = 0;
    }
	// metoda generująca mapę
    public void generate() {
        Random rand = new Random();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                // losowe umieszczenie roślin na mapie
                if (rand.nextInt(100) < density) {
                    // losowy wybór typu rośliny
                    if (rand.nextInt(4) == 0) {
                        map[row][col] = 'T'; // T-drzewo
                        Trees_numb++;
                    } else {
                        map[row][col] = 'B'; // B-krzak
                        Bushes_numb++;
                    }
                } else {
                    map[row][col] = '_'; // pole puste
                }
            }
        }
    }

    // metoda wyświetlająca mapę na ekranie
    public void display() { 	
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                System.out.print(map[row][col] + " ");
            }
            System.out.println();
        }
    }
    
    // metoda sprawdzajaca czy dane pole jest puste (rowne "_")
    public boolean isFieldEmpty(int x, int y)
    {
    	if(map[x][y]=='_') {return true;}
    	return false;
    }

    // metoda zwracająca liczbę T-drzew na mapie
    public int getTrees_numb() {
        return Trees_numb;
    }

    // metoda zwracająca liczbę B-krzaków na mapie
    public int getBushes_numb() {
        return Bushes_numb;
    }
    public void change_map(int x, int y)
    {
    	map[x][y] = '_'; // Zamień zawartość pola na '_'
    }
}
