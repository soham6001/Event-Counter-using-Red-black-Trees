


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class bbst {
	public static void main(String[] args) throws IOException {
		
		//*******************************************************************************************************************************************************
		
		//**** The input filename is given to the program as an argument and the file is scanned ****
		
		//*******************************************************************************************************************************************************
		
		
		Scanner sc = new Scanner(new File(args[0]));
		int size = sc.nextInt();
		int[][] a = new int[size][2];

		int count = 0;

		while (sc.hasNext()) {

			//reads int tokens from file and assigns them to ID and count in the sorted array
			a[count][0] = sc.nextInt();
			a[count][1] = sc.nextInt();

			count++;

		}
        
		sc.close();
		sc = new Scanner(System.in);
        
		//**** The creation of the red black tree ****
		RedBlackTree tree = new RedBlackTree();

		//**** RB Tree Initialization ****
		tree.initializeRBT(a, 0, size - 1, size);

		
		int n = 0;
		//the command
		while (sc.hasNext()) {

			switch (sc.next()) {
			case "increase":

				tree.increase(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()));
				break;
			case "reduce":

				tree.reduce(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()));
				break;
			case "count":

				tree.count(Integer.parseInt(sc.next()));
				break;
			case "inrange":

				tree.inRange(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()));
				break;
			case "next":

				tree.next(Integer.parseInt(sc.next()));
				break;

			case "previous":

				tree.previous(Integer.parseInt(sc.next()));
				break;
            //terminating condition
			case "quit":
				n = 100;
				break;

			default:
				System.out.println("Incorrect command");
				break;

			}
			if (n == 100)
				break;

		}

		sc.close();

	}

}

