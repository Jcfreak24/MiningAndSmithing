import java.util.Scanner;


public class Smith{

	public static int smelt(int level) 
	{
		Scanner scan = new Scanner(System.in);

		System.out
				.print("What kind of bars would you like to smelt? (Bronze, Iron, Steel, Mithril, Adamant, or Rune) --> ");
		String typeBar = scan.nextLine();
		// checkBars(typeOre, typeBar, level);

		level++;
		return level;
	}

	public static void checkBars(String typeOre, String typeBar, int level) 
	{
		int amount;

		if (typeBar.equals("Copper") || typeBar.equals("Tin")) {
			amount = askSmeltAmount();
			remove(typeOre, amount);
			add(typeBar, amount);
		} else if (typeBar.equals("Iron")) {
			if (level > 15) {

			} else {

			}
		}
	}

	public static int askSmeltAmount() 
	{
		Scanner scan = new Scanner(System.in);

		int amount = 0;
		do {
			System.out
					.print("How many would you like to smelt? (One or All) --> ");
			amount = Integer.parseInt(scan.nextLine());
		} while (amount < 1 || amount > 28);
		return amount;
	}

	public static void remove(String typeOre, int amount) 
	{
		// Removes type and amount from hashmap
	}
	
	public static void add(String type, int amount) 
	{
		// If the ore is already in the hashmap, just add the amount.
		// If it is not in the hashmap, add the type and amount.
	}
}