import java.util.Scanner;
/*
 * Total Marks Main Class: 
 * Compilation & Correct Execution Marks: 
 */
class Transaction{
	int quantity;
	int unitPrice;
	
	Transaction(int q, int p) {
		quantity = q;
		unitPrice = p;
	}
	/*
	 * Add getters and setters
	 *
	 */
	public int getQuant() {
		return quantity;
	}
	public int getPrice() {
		return unitPrice;
	}
	public void setQuant(int q) {
		quantity = q;
	}
	public void setPrice(int p) {
		unitPrice = p;
	}
}

public class Main {
	static LinkedQueue<Transaction> buyQueue = new LinkedQueue<Transaction>();
	static LinkedQueue<Transaction> sellQueue = new LinkedQueue<Transaction>();
	
	/*
	 * 
	 * 15 marks
	 */
	public static void processTransactions(LinkedQueue<String> transactions) {
		
		for(int i = 0; i < transactions.size();i++) {
			String trans = transactions.dequeue();
			String [] tokens = trans.split(" ");
			
			
			switch(tokens[0].toUpperCase()) {
			case "BUY":
			{
				int Quant = Integer.parseInt(tokens[1]);
				int Price = Integer.parseInt(tokens[2]);
				buyQueue.enqueue(new Transaction(Quant,Price));
			}
			case "SELL":{
				System.out.println("Hello world");
				int Quant = Integer.parseInt(tokens[1]);
				int Price = Integer.parseInt(tokens[2]);
				sellQueue.enqueue(new Transaction(Quant,Price));
			}
			}
		/*	if(tokens[0].equalsIgnoreCase("BUY")) {
				Quant = Integer.parseInt(tokens[1]);
				Price = Integer.parseInt(tokens[2]);
				buyQueue.enqueue(new Transaction(Quant,Price));
				
				
			}else if(tokens[0].equalsIgnoreCase("SELL")) {
				System.out.println("Hello world");
				Quant = Integer.parseInt(tokens[1]);
				Price = Integer.parseInt(tokens[2]);
				sellQueue.enqueue(new Transaction(Quant,Price));
			} */
		}
	
		System.out.println(buyQueue.size());
		System.out.println(sellQueue.size());
		
	}
	
	/*
	 * 
	 * 20 marks
	 */
	public static Integer calculateCapitalGainLoss() {
		//COMPLETE CODE HERE
		int total =0;
		for(int i =0; i < sellQueue.size();i++) {
			if(sellQueue.first().getQuant() > buyQueue.first().getQuant()) {
				
				int quant = sellQueue.first().getQuant() - buyQueue.first().getQuant();
				total += quant * (sellQueue.first().getPrice() - buyQueue.first().getPrice()) ;
				sellQueue.dequeue();
			}else if(sellQueue.first().getQuant() < buyQueue.first().getQuant()) {
		
				int quant = buyQueue.first().getQuant() - sellQueue.first().getQuant();
				total += quant * (sellQueue.first().getPrice() - buyQueue.first().getPrice());
				buyQueue.dequeue();
			}else if(sellQueue.first().getQuant() == buyQueue.first().getQuant()) {
				int quant = sellQueue.first().getQuant();
				System.out.println(quant);
				total += quant * (sellQueue.first().getPrice() - buyQueue.first().getPrice());
				buyQueue.dequeue();
				sellQueue.dequeue();
			}
		}
		
		return total;
	}
	
	public static void main(String[] args) {
		String response = "";
		Scanner s = new Scanner(System.in);
		LinkedQueue<String> transactionQueue = new LinkedQueue<String>();
		
		while (!response.toLowerCase().equals("quit")){
			System.out.println("Select option: ");
			System.out.println("1) Enter new transaction");
			System.out.println("2) Calculate capital gain or loss");
			System.out.println("or \"quit\" to quit.");
			response = s.nextLine();
			
			switch(response.toLowerCase()){
				case "1": {
					System.out.println("Enter transaction:");
					transactionQueue.enqueue(s.nextLine());
				}
					break;
				case "2": {
					processTransactions(transactionQueue);
					System.out.println("Capital Gain/Loss: "+calculateCapitalGainLoss());
				}
					break;
				default: System.out.println("Incorrect option selected. Please try again.");
			}			
		}
	}
}