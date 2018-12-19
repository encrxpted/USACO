package usaco2018.USOpen;
// TODO REMEMBER TO ERASE PACKAGE LINE BEFORE SUBMISSION!

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MilkOrder {
	private static File fileIn;
	private static File fileOut;
	private static FileWriter fw;
	private static FileReader fr;
	private static BufferedWriter bw;
	private static BufferedReader br;
	private static String input = ""; // Input file path
	private static String output = ""; // Output file path
	
	static String input1 = "6 3 2";
	static String input2 = "4 5 6";
	static String input3 = "5 3\n" + "3 1";

	boolean testing = true;
	public static void main(String args[]) throws IOException {
		//setUp(); TODO UNCOMMENT
		
		List<Integer> orderedCowsList = new ArrayList<>();
		HashMap<Integer, Integer> positionCowsList = new HashMap<>();
		HashMap<Integer, Integer> milkingOrder = new HashMap<>();
		
		
//		String[] line1 = readLine().split(" ");
//		int totalCows = Integer.parseInt(line1[0]);
//		int orderedCows = Integer.parseInt(line1[1]);
//		int positionedCows = Integer.parseInt(line1[2]);
		String[] line1 = input1.split(" ");
		int totalCows = Integer.parseInt(line1[0]);
		int orderedCows = Integer.parseInt(line1[1]);
		int positionedCows = Integer.parseInt(line1[2]);

		
		if(positionedCows == 0 || totalCows == 0) {
			writeLine("1");
			return;
		}
		
		String[] line2 = input2.split(" ");
		for(int i = 0; i < line2.length; i++) {
			orderedCowsList.add(Integer.parseInt(line2[i]));
		}
		
		//positionCowsList = getPositionedCows();
		positionCowsList.put(5, 3);
		positionCowsList.put(3, 1);
		
		for(int cow : positionCowsList.keySet()) {
			 int position = positionCowsList.get(cow);
			 milkingOrder.put(cow, position);
			 if(cow == 1) {
				 writeLine(Integer.toString(position));
				 return;
			 }
		}
		
		int relOrder = 0;
		int lastCowOnPosList = 0;
		for(int cow: orderedCowsList) {
			System.out.println(cow);
			if(milkingOrder.containsKey(cow)) {
				int absPosition = milkingOrder.get(cow);
				if(lastCowOnPosList != 0) {
					int lastPosTaken = milkingOrder.get(lastCowOnPosList);
					int slotsBetween = Math.abs(lastPosTaken - absPosition);
					if(slotsBetween-relOrder != 0) {
						writeLine(Integer.toString(lastPosTaken+1));
						return;
					}
					relOrder = 0;
				} 
				else {
					int slotsBetween = absPosition+1;
					if(slotsBetween-relOrder != 0) {
						
						System.out.println("hi " + slotsBetween + " " + relOrder);
						writeLine("1");
						return;
					}
				}
				lastCowOnPosList = cow;
			}
			else relOrder++;
		}
		
	}
	
	public static HashMap<Integer, Integer> getPositionedCows() {
		HashMap<Integer, Integer> positionCowsList = new HashMap<>();
		while(true) {
			String line = readLine();
			if(line != null) {
				String[] lineArray = line.split(" ");
				int cow = Integer.parseInt(lineArray[0]);
				int position = Integer.parseInt(lineArray[1]);
				positionCowsList.put(cow, position);
			}
			else return positionCowsList;
		}
		
	}
	
	// Instantiates the files and file readers/writers. Called once at the beginning.
	public static void setUp() {
		fileIn = new File(input);
		fileOut = new File(output);

		try {
			fr = new FileReader(fileIn);
			if(fr != null)
				br = new BufferedReader(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fw = new FileWriter(fileOut);
			if(fw != null)
				bw = new BufferedWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
 
	// Writes a single line to the output file and moves to the beginning of the next line
	public static void writeLine(String line) {
		System.out.print(line);
		try {
			if(bw != null) {
				bw.write(line);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	// Reads a single line of the input file then moves to the beginning of the next line
	public static String readLine() {
		String line = "";
		try {
			if(br != null) line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}	
	

}
