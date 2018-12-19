package template;
// TODO REMEMBER TO ERASE PACKAGE LINE BEFORE SUBMISSION!

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Template to read and write to files for USACO
 */

public class Template {
	private static File fileIn;
	private static File fileOut;
	private static FileWriter fw;
	private static FileReader fr;
	private static BufferedWriter bw;
	private static BufferedReader br;
	
	private static String inputPath = ""; // Input file path
	private static String outputPath = ""; // Output file path
	
	private static boolean test = false; // TODO make sure this is false before submission

	public static void main(String args[]) throws IOException {
		if(!test) {
			setUp();
		}
		// Code goes here 
		
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
