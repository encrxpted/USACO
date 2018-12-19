package usaco2018.jan;
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

/*
 * Jan 2018 Bronze Problem
 * Score: ***** *****
 */

public class Lifeguard {
	private static File fileIn;
	private static File fileOut;
	private static FileWriter fw;
	private static FileReader fr;
	private static BufferedWriter bw;
	private static BufferedReader br;
	private static String input = "Lifeguards/10.in"; // Input file path
	private static String output = "lifeguard.txt"; // Output file path
	
	private static String input1;
	private static String nextLine;
	private static int output1;
	
	private static String testInput1 = "3";
	private static List<Shift> shifts = new ArrayList<>();
	private static HashMap<Integer, Integer> coveredTime = new HashMap<>(); // in time units
	// between t =0 and t= 1 is "1st time interval"
//	private static String testInput2 = "";
//	private static String testInput3 = "";
	
	private static boolean test = false; // TODO make sure this is false before submission

	public static void main(String args[]) throws IOException {
		long startTime = System.nanoTime();

		if(!test) {
			setUp();
			input1 = readLine();
			nextLine = readLine();
			
			while(nextLine != null && !nextLine.equals("")) {
				String[] line = nextLine.split(" ");
				int start = Integer.parseInt(line[0]);
				int end = Integer.parseInt(line[1]);
				Shift shift = new Shift(start, end);
				
				int length = shift.length;
				int firstInterval = start + 1;
				int interval = firstInterval;
				for(int i = 0; i < length; i++) {
					if(coveredTime.containsKey(interval)) 
						coveredTime.put(interval, coveredTime.get(interval)+1);
					else
						coveredTime.put(interval, 1);
					interval++;
				}
				
				shifts.add(shift);	
				
				nextLine = readLine();
			}
			
		}
		else {
			input1 = testInput1;
			shifts.add(new Shift(5,9));
			shifts.add(new Shift(1,4));
			shifts.add(new Shift(3,7));
			
			for(Shift shift : shifts) {
				int length = shift.length;
				int firstInterval = shift.start + 1;
				int interval = firstInterval;
				
				for(int i = 0; i < length; i++) {
					if(coveredTime.containsKey(interval)) 
						coveredTime.put(interval, coveredTime.get(interval)+1);
					else
						coveredTime.put(interval, 1);
					interval++;
				}
				
			}
		}
		
		int minTimeAlone = 1000;
		for(Shift shift : shifts) {
			int interval = shift.start + 1;
			int timeAlone = 0;
			
			for(int i = 0; i < shift.length; i++) {
				if(coveredTime.containsKey(interval)) {
					if(coveredTime.get(interval) == 1) {
						timeAlone++;
					}
				}
				
				interval++;
			}
			
			if(timeAlone < minTimeAlone) minTimeAlone = timeAlone;
			
		}
		
		output1 = coveredTime.size() - minTimeAlone;
		
		if(!test) writeLine(Integer.toString(output1));
		else System.out.println(output1);
		System.out.println(output1);

		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("Seconds: " + totalTime/Math.pow(10, 9));
		
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

class Shift {
	int start;
	int end;
	int length;
	public Shift(int start, int end) {
		this.start = start;
		this.end = end;
		length = end - start;
	}
}
