import java.util.ArrayList;
import java.util.Scanner;

public class App {

	Validation validate = new Validation();
	Scanner scan = new Scanner(System.in);
	ArrayList<Runner> runners = new ArrayList<>();
	int turtleScore = 0;
	int hareScore = 0;
	
	void addRunner() {
		String name, type;
		
		do {
			System.out.println("Name [5-15 Characters]: ");
			name = scan.nextLine();
		}while(!validate.isValidName(name));
		
		do {
			System.out.println("Type [hare|turtle]: ");
			type = scan.nextLine();
		}while(!validate.isValidType(type));
		
		if(type.contentEquals("hare")) {
			runners.add(new Hare(name, type));
		} else {
			runners.add(new Turtle(name, type));
		}
		System.out.println("Another runner has joined the race!");
	}
	
	void joinRun() {
		Leaderboard board = new Leaderboard(runners);
		
		while(board.isRunning) {
			board.updateBoard();
			board.showBoard();
		}
		board.resetBoard();
	
		if(board.winner instanceof Hare) {
			hareScore++;
		}else {
			turtleScore++;
		}
	}
	
	
	void viewStanding() {
		if(hareScore == turtleScore) {
			System.out.println("Draw");
		} else if (hareScore > turtleScore) {
			System.out.println("Hare Wins!");
		} else if(turtleScore > hareScore){
			System.out.println("Turtle Wins!");
		}
		
		System.out.println("Hare: " + hareScore);
		System.out.println("Turtle: " + turtleScore);
	}
	
	boolean onApp = true;
	public App(boolean onApp) {
		while(onApp) {
			System.out.println("1. Add Runner");
			System.out.println("2. Join Run");
			System.out.println("3. View Standing");
			System.out.println("4. Close Program");
			System.out.println("Input choice");
		
			int choice;
			
			do {
				System.out.println(">> ");
				choice = scan.nextInt(); scan.nextLine();
			}while(!validate.isValidChoice(choice));
			
			switch(choice) {
			case 1:
				addRunner();
				break;
			case 2:
				joinRun();
				break;
			case 3:
				viewStanding();
				break;
			case 4:
				onApp = false;
				break;
			}
		}
	}

	public static void main(String[] args) {
		new App(true);
	}

}
