package pandora;

import java.util.Scanner;

public class Main {

	public static void main(String[] args)
	{
		int x,y;
		System.out.println("Podaj rozmiar mapy:");
		Scanner scan = new Scanner(System.in);
		x = scan.nextInt();
		y = scan.nextInt();
		scan.close();
	}
}

