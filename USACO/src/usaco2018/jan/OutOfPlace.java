package usaco2018.jan;
// TODO REMEMBER TO ERASE PACKAGE LINE BEFORE SUBMISSION!

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * USACO Bronze Jan 2018 solution
 * Score: ****x **x**
 */

public class OutOfPlace {
	private static File fileIn;
	private static File fileOut;
	private static FileWriter fw;
	private static FileReader fr;
	private static BufferedWriter bw;
	private static BufferedReader br;
	private static String inputPath = "OutOfPlace/10.in"; // Input file path
	private static String outputPath = "outofplace.txt"; // Output file path
	
	private static int input1;
	private static String input2;
	static int output1;
	
	private static List<Integer> listOfHeights = new ArrayList<>();
	static int bessieIndex = -1;
	static int bessieHeight = 0;
	
	private static String testInput1 = "2\n" + 
			"4\n" + 
			"7\n" + 
			"7\n" + 
			"9\n" + 
			"3";
	private static int testInput2 = 6;
//	private static String testInput3 = "";
	
	private static boolean test = false; // TODO make sure this is false before submission

	public static void main(String args[]) throws IOException {
		long startTime = System.nanoTime();
		if(!test) {
			setUp();
			input1 = Integer.parseInt(readLine());
			String nextLine = readLine();
			int prevHeight = 0;
			for(int i = 0; i < input1; i++) {
				int height = Integer.parseInt(nextLine);
				listOfHeights.add(height);
				
				nextLine = readLine();
				if(prevHeight > height) {
					bessieIndex = i;
					bessieHeight = height;
				}
				
				prevHeight = height;
			}
		}
		else {
			String[] testInputs = testInput1.split("\n");
			int prevHeight = 0;
			for(int i = 0; i < 6; i++) {
				int height = Integer.parseInt(testInputs[i]);
				listOfHeights.add(height);
				
				if(prevHeight > height) {
					bessieIndex = i;
					bessieHeight = height;
				}
				
				prevHeight = height;
			}
		}
		
		int lastHeight = bessieHeight;
		int swaps = 0;
		//for(int i = bessieIndex-1; i >= 0; i--) {
		int i = bessieIndex-1;
		boolean terminate = false;
		while(i >=0 && !terminate) {
			int height = listOfHeights.get(i);
			
			if(height <= bessieHeight) {
				output1 = swaps;
				terminate = true;
			}
			else if(height == lastHeight) {
				
			}
			else {
				swaps++;
			}
			
			lastHeight = height;
			i--;
		}
		
		if(!test)
			writeLine(Integer.toString(output1));
		else
			System.out.println(output1);
		
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("Seconds: " + totalTime/Math.pow(10, 9));
		
		//String answer = 
		
	}
	
	// Instantiates the files and file readers/writers. Called once at the beginning.
	public static void setUp() {
		fileIn = new File(inputPath);
		fileOut = new File(outputPath);

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
		if (!test) {
			try {
				if (bw != null) {
					bw.write(line);
					bw.newLine();
					bw.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		else System.out.println(line);
	}
	
	// Reads a single line of the input file then moves to the beginning of the next line
	public static String readLine() {
		String line = "";
		try {
			if(br != null)
				line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}	
	

}
