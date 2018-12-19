package usaco2018.USOpen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TeamTicTacToe {
	private static File fileIn;
	private static File fileOut;
	private static FileWriter fw;
	private static FileReader fr;
	private static BufferedWriter bw;
	private static BufferedReader br;
	private static String input = "tttt.in"; // Input file path
	private static String output = "tttt.out"; // Output file path
	
	static int oneCowWin = 0;
	static int twoCowsWin = 0;
	
//	static String firstRow = "EEE";
//	static String secondRow = "REX";
//	static String thirdRow = "GRE";
	
	private static List<Character> cowsThatCanWin = new ArrayList<>();
	private static List<char[]> cowTeamsThatCanWin = new ArrayList<>();

	public static void main(String args[]) throws IOException {
		setUp();
		char[] row1 = readLine().toCharArray();
		char[] row2 = readLine().toCharArray();
		char[] row3 = readLine().toCharArray();
//		char[] row1 = firstRow.toCharArray();
//		char[] row2 = secondRow.toCharArray();
//		char[] row3 = thirdRow.toCharArray();
		oneCowWin = 0;
		twoCowsWin = 0;
		char[] col1 = new char[3];
		char[] col2 = new char[3];
		char[] col3 = new char[3];
		char[] diagL = new char[3];
		char[] diagR = new char[3];
		col1[0] = row1[0];
		col1[1] = row2[0];
		col1[2] = row3[0];
		col2[0] = row1[1];
		col2[1] = row2[1];
		col2[2] = row3[1];
		col3[0] = row1[2];
		col3[1] = row2[2];
		col3[2] = row3[2];
		diagL[0] = row1[0];		
		diagL[1] = row2[1];
		diagL[2] = row3[2];
		diagR[0] = row1[2];
		diagR[1] = row2[1];
		diagR[2] = row3[0];
		
		canOneCowWinRow(row1);		
		canOneCowWinRow(row2);
		canOneCowWinRow(row3);
		canOneCowWinRow(col1);
		canOneCowWinRow(col2);
		canOneCowWinRow(col3);
		canOneCowWinRow(diagL);
		canOneCowWinRow(diagR);
		
		canTwoCowsWinRow(row1);
		canTwoCowsWinRow(row2);
		canTwoCowsWinRow(row3);
		canTwoCowsWinRow(col1);
		canTwoCowsWinRow(col2);
		canTwoCowsWinRow(col3);
		canTwoCowsWinRow(diagL);
		canTwoCowsWinRow(diagR);
		
		writeLine(oneCowWin + "\n" + twoCowsWin);
		//System.out.println(oneCowWin + "\n" + twoCowsWin);
	}
	
	public static boolean canTwoCowsWinRow(char[] rowArray) {
		if(!canOneCowWinRow(rowArray) && (rowArray[0] == rowArray[1] || rowArray[1] == rowArray[2] || rowArray[0] == rowArray[2])) {
			char[] teams = new char[2];
			if(rowArray[0]==rowArray[1]) {
				teams[0] = rowArray[0];
				teams[1] = rowArray[2];
				if (!cowTeamsThatCanWin.isEmpty()) {
					for (int i = 0; i < cowTeamsThatCanWin.size(); i++) {
						char[] teamFromList = cowTeamsThatCanWin.get(i);
						if (teams.equals(cowTeamsThatCanWin.get(i))
								|| (teams[0] == teamFromList[1] && teams[1] == teamFromList[0]))
							return true;
						if (i + 1 == cowTeamsThatCanWin.size()) {
							twoCowsWin++;
							cowTeamsThatCanWin.add(teams);
							return true;
						}
					}
				} 
				else {
					twoCowsWin++;
					cowTeamsThatCanWin.add(teams);
					return true;
				}
			}
			else if(rowArray[1]==rowArray[2]) {
				teams[0] = rowArray[1];
				teams[1] = rowArray[0];
				if (!cowTeamsThatCanWin.isEmpty()) {
					for (int i = 0; i < cowTeamsThatCanWin.size(); i++) {
						char[] teamFromList = cowTeamsThatCanWin.get(i);
						if (teams.equals(cowTeamsThatCanWin.get(i))
								|| (teams[0] == teamFromList[1] && teams[1] == teamFromList[0]))
							return true;
						if (i + 1 == cowTeamsThatCanWin.size()) {
							twoCowsWin++;
							cowTeamsThatCanWin.add(teams);
							return true;
						}
					}
				} 
				else {
					twoCowsWin++;
					cowTeamsThatCanWin.add(teams);
					return true;
				}
			}
			else if(rowArray[2]==rowArray[0]) {
				teams[0] = rowArray[2];
				teams[1] = rowArray[1];
				if (!cowTeamsThatCanWin.isEmpty()) {
					for (int i = 0; i < cowTeamsThatCanWin.size(); i++) {
						char[] teamFromList = cowTeamsThatCanWin.get(i);
						if (teams.equals(cowTeamsThatCanWin.get(i))
								|| (teams[0] == teamFromList[1] && teams[1] == teamFromList[0]))
							return true;
						if (i + 1 == cowTeamsThatCanWin.size()) {
							twoCowsWin++;
							cowTeamsThatCanWin.add(teams);
							return true;
						}
					}
				} 
				else {
					twoCowsWin++;
					cowTeamsThatCanWin.add(teams);
					return true;
				}
			}
			return true;
		}
		else return false;
	}
	
	public static boolean canOneCowWinRow(char[] rowArray) {
		if(rowArray[0] == rowArray[1] && rowArray[1] == rowArray[2]) {
			if(!cowsThatCanWin.isEmpty()) {
				for (int i = 0; i < cowsThatCanWin.size(); i++) {
					if (cowsThatCanWin.get(i).equals(rowArray[0])) {
						return true;
					}
					if (i + 1 == cowsThatCanWin.size()) {
						oneCowWin++;
						cowsThatCanWin.add(rowArray[0]);
					}
				}
			}
			else {
				oneCowWin++;
				cowsThatCanWin.add(rowArray[0]);
			}
			return true;
		}
		else return false;	
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
			if(br != null)
				line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}	
}
