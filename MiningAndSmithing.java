import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Will have a level for each as well as money. Can either buy or mine ore, smelt it, and then sell it for money.
// Should make this have a gui to be more user-friendly.

public class MiningAndSmithing extends JFrame implements ActionListener
{
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel bottomTopLeftPanel;
	private JPanel bottomTopRightPanel;
	private JPanel bottomBottomLeftPanel;
	private JPanel bottomBottomRightPanel;
	//
	private JButton reset; //at top
	private JButton quit;
	//
	private JButton mine; //at bottom of bottom
	private JButton smelt;
	private JButton buy;
	private JButton sell;
	//
	private JLabel miningLevelLabel; //at top of bottom
	private JLabel smeltingLevelLabel;
	private JLabel money;
	private JLabel inventory;
	//
	private static int miningLevel; //variables
	private static int smeltingLevel;
	private static double cash;
	private static HashMap<String, Integer> ores;
	//private static int ctrlVar;
	private static Scanner in;
	private static PrintWriter out;
	
	public MiningAndSmithing()
	{
		super("Mining and Smithing");
		setSize(650,650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,2));
		//
		reset = new JButton("Reset");
		reset.addActionListener(this);
		topPanel.add(reset);
		//
		quit = new JButton("Save & Quit");
		quit.addActionListener(this);
		topPanel.add(quit);
		
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2,2));
		//
		bottomTopLeftPanel = new JPanel();
		bottomTopLeftPanel.setLayout(new GridLayout(1,2));
		bottomPanel.add(bottomTopLeftPanel);
		//
		bottomTopRightPanel = new JPanel();
		bottomTopRightPanel.setLayout(new GridLayout(1,2));
		bottomPanel.add(bottomTopRightPanel);
		//
		bottomBottomLeftPanel = new JPanel();
		bottomBottomLeftPanel.setLayout(new GridLayout(1,2));
		bottomPanel.add(bottomBottomLeftPanel);
		//
		bottomBottomRightPanel = new JPanel();
		bottomBottomRightPanel.setLayout(new GridLayout(1,2));
		bottomPanel.add(bottomBottomRightPanel);
		//
		miningLevelLabel = new JLabel("Mining Level: " + miningLevel);
		bottomTopLeftPanel.add(miningLevelLabel);
		//
		smeltingLevelLabel = new JLabel("Smelting Level: " + smeltingLevel);
		bottomTopLeftPanel.add(smeltingLevelLabel);
		//
		money = new JLabel("Money: $" + cash);
		bottomTopRightPanel.add(money);
		//
		inventory = new JLabel("Ores: " + ores.size());
		bottomTopRightPanel.add(inventory);
		//
		mine = new JButton("Mine");
		mine.addActionListener(this);
		bottomBottomLeftPanel.add(mine);
		//
		smelt = new JButton("Smelt");
		smelt.addActionListener(this);
		bottomBottomLeftPanel.add(smelt);
		//
		buy = new JButton("Buy");
		buy.addActionListener(this);
		bottomBottomRightPanel.add(buy);
		//
		sell = new JButton("Sell");
		sell.addActionListener(this);
		bottomBottomRightPanel.add(sell);
		
		Container contentPane = this.getContentPane();
		contentPane.add(topPanel, BorderLayout.NORTH);
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == reset)
		{
			miningLevel = 1;
			miningLevelLabel.setText("Mining Level: " + miningLevel);
			smeltingLevel = 1;
			smeltingLevelLabel.setText("Smelting Level: " + smeltingLevel);
			cash = 0.00;
			money.setText("Money: $" + cash);
			ores.clear();
		}
		else if(event.getSource() == quit)
		{
			try
			{
				try
				{
					out = new PrintWriter("MiningAndSmithing.txt");
					out.println(miningLevel);
					out.println(smeltingLevel);
					out.println(cash);
					for(int i = 0; i < ores.size(); i++)
					{
						out.println(ores.get(i));
						out.println(ores.get(ores.get(i)));
					}
					this.dispose();
				}
				finally
				{
					out.close();
				}
			}
			catch(FileNotFoundException ex)
			{
				ex.printStackTrace();
			}
			
		}
		else if(event.getSource() == mine)
		{
			miningLevel++;
			miningLevelLabel.setText("Mining Level: " + miningLevel);
		}
		else if(event.getSource() == smelt)
		{
			smeltingLevel++;
			smeltingLevelLabel.setText("Smelting Level: " + smeltingLevel);
		}
		else if(event.getSource() == buy)
		{
			if(cash >= 1)
			{
				cash--;
				money.setText("Money: $" + cash);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "You don't have any money!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(event.getSource() == sell)
		{
			if(ores.size() > 0)
			{
				cash++;
				money.setText("Money: $" + cash);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "You don't have any ores to sell!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
    public static void main(String args[])
    {
    	try
    	{
    		try
    		{
    			in = new Scanner(new File("MiningAndSmithing.txt"));
    			miningLevel = Integer.parseInt(in.nextLine());
				smeltingLevel = Integer.parseInt(in.nextLine());
				cash = Double.parseDouble(in.nextLine());
				ores = new HashMap<>();
				while(in.hasNextLine())
				{
					ores.put(in.nextLine(), Integer.parseInt(in.nextLine()));
				}
	    	}
    		finally
	    	{
	    		in.close();
	    	}
    	}
    	catch(FileNotFoundException ex)
    	{
    		ex.printStackTrace();
    	}
    	
    	MiningAndSmithing gui = new MiningAndSmithing();
    	gui.setVisible(true);
    	
    	/*Scanner scan = new Scanner(System.in);
    	Scanner in = new Scanner(new File("MiningAndSmithing.txt"));
		
		miningLevel = Integer.parseInt(in.nextLine());
		smithingLevel = Integer.parseInt(in.nextLine());
		cash = Double.parseDouble(in.nextLine());
		ores = new HashMap<>();
		while(in.hasNextLine())
		{
			ores.put(in.nextLine(), Integer.parseInt(in.nextLine()));
		}
		
		ctrlVar = 0;
		while(ctrlVar < 1)
		{			
			System.out.print("What would you like to do? (Mine, Smelt, Buy, Sell, Check Levels, Check Money, Reset, or Quit) --> ");
			String thing = scan.nextLine();
			
			if(thing.equals("Quit"))
			{
				PrintWriter out = new PrintWriter("MiningAndSmithing.txt");
				out.println(miningLevel);
				out.println(smithingLevel);
				out.println(cash);
				for(int i = 0; i < ores.size(); i++)
				{
					out.println(ores.get(i));
					out.println(ores.get(ores.get(i)));
				}
				out.close();
				System.exit(0);
			}
			else if(thing.equals("Mine"))
			{
				miningLevel = mine(miningLevel);
			}
			else if(thing.equals("Smelt"))
			{
				smithingLevel = smelt(smithingLevel);
			}
			else if(thing.equals("Buy"))
			{
				buy(cash);
			}
			else if(thing.equals("Sell"))
			{
				sell(cash);
			}
			else if(thing.equals("Reset"))
			{
				miningLevel = 1;
				smithingLevel = 1;
				cash = 0.00;
				ores.clear();
			}
			else if(thing.equals("Check Levels"))
			{
				levelCheck(miningLevel, smithingLevel);
			}
			else if(thing.equals("Check Money"))
			{
				moneyCheck(cash);
			}
			else
			{
				System.out.println("Error! Invalid Input.\n");
				ctrlVar=0;
			}
		}
		in.close();*/
    }
    
    public static int mine(int level)
    {
    	Scanner scan = new Scanner(System.in);
    	
    	System.out.print("What would you like to mine? (Copper, Tin, Iron, Coal, Mithril, Adamantite, or Runite) --> ");
    	String typeOre = scan.nextLine();
    	checkOre(typeOre, level);
    	
    	level++;
    	return level;
    }
	    public static void checkOre(String typeOre, int level)
	    {
	    	int amount;
	    	
	    	if(typeOre.equals("Copper") || typeOre.equals("Tin"))
	    	{
	    		amount = askMineAmount();
	    		add(typeOre, amount);
	    	}
	    	else if(typeOre.equals("Iron"))
	    	{
	    		if(level > 15)
	    		{
	    			
	    		}
	    		else
	    		{
	    			
	    		}
	    	}
	    }
    		public static int askMineAmount()
		    {
		    	Scanner scan = new Scanner(System.in);
		    	
		    	int amount = 0;
		    	do
		    	{
		    		System.out.print("How many would you like to mine? (1-28) --> ");
		    		amount = Integer.parseInt(scan.nextLine());
		    	}
		    	while(amount < 1 || amount > 28);
		    	return amount;
		    }
		    public static void add(String type, int amount)
		    {
		    	// If the ore is already in the hashmap, just add the amount.
		    	// If it is not in the hashmap, add the type and amount.
		    }
    
    public static int smelt(int level)
    {
    	Scanner scan = new Scanner(System.in);
    	
    	System.out.print("What kind of bars would you like to smelt? (Bronze, Iron, Steel, Mithril, Adamant, or Rune) --> ");
    	String typeBar = scan.nextLine();
    	//checkBars(typeOre, typeBar, level);
    	
    	level++;
    	return level;
    }
    	public static void checkBars(String typeOre, String typeBar, int level)
	    {
	    	int amount;
	    	
	    	if(typeBar.equals("Copper") || typeBar.equals("Tin"))
	    	{
	    		amount = askSmeltAmount();
	    		remove(typeOre, amount);
	    		add(typeBar, amount);
	    	}
	    	else if(typeBar.equals("Iron"))
	    	{
	    		if(level > 15)
	    		{
	    			
	    		}
	    		else
	    		{
	    			
	    		}
	    	}
	    }
    		public static int askSmeltAmount()
		    {
		    	Scanner scan = new Scanner(System.in);
		    	
		    	int amount = 0;
		    	do
		    	{
		    		System.out.print("How many would you like to smelt? (One or All) --> ");
		    		amount = Integer.parseInt(scan.nextLine());
		    	}
		    	while(amount < 1 || amount > 28);
		    	return amount;
		    }
		    public static void remove(String typeOre, int amount)
		    {
		    	// Removes type and amount from hashmap
		    }

    public static void buy(double money)
    {
    	
    }
    public static void sell(double money)
    {
    	
    }
    /*
    public static void levelCheck(int mL, int sL)
    {
    	System.out.println("Mining level is " + mL + ".");
    	System.out.println("Smithing level is " + sL + ".\n");
    }
    public static void moneyCheck(double money)
    {
    	System.out.printf("You have $%4.2f.\n", money);
    }
    */
}

class painter extends JFrame
{
	public painter()
	{
		
	}
	
	protected void paintComponent(Graphics g)
	{
		
	}
}