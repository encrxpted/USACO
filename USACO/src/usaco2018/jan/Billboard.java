package usaco2018.jan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Jan 2018 Bronze Problem
 * Score: ***** *****
 */

public class Billboard {

	private static File fileIn;
	private static File fileOut;
	private static FileWriter fw;
	private static FileReader fr;
	private static BufferedWriter bw;
	private static BufferedReader br;
	private static String input = "Billboard/10.in"; // Input file path
	private static String output = "billboard.txt"; // Output file path

	private static String input1 = "";
	private static String input2 = "";
	private static int output1 = 0;

	private static String testInput1 = "293 -483 839 384";
	private static String testInput2 = "-384 -740 943 89";

	private static boolean test = false; // TODO make sure this is false before submission

	public static void main(String args[]) throws IOException {
		long startTime = System.nanoTime();

		if (!test) {
			setUp();
			input1 = readLine();
			input2 = readLine();
		}
		else {
			input1 = testInput1;
			input2 = testInput2;
		}

		String[] coords1 = input1.split(" ");
		String[] coords2 = input2.split(" ");

		int lawnX1 = Integer.parseInt(coords1[0]);
		int lawnY1 = Integer.parseInt(coords1[1]);
		int lawnX2 = Integer.parseInt(coords1[2]);
		int lawnY2 = Integer.parseInt(coords1[3]);
		int cowX1 = Integer.parseInt(coords2[0]);
		int cowY1 = Integer.parseInt(coords2[1]);
		int cowX2 = Integer.parseInt(coords2[2]);
		int cowY2 = Integer.parseInt(coords2[3]);

		Point lawnLL = new Point(lawnX1, lawnY1);
		Point lawnTR = new Point(lawnX2, lawnY2);
		Point cowLL = new Point(cowX1, cowY1);
		Point cowTR = new Point(cowX2, cowY2);

		Rectangle lawn = new Rectangle(lawnLL, lawnTR);
		Rectangle cow = new Rectangle(cowLL, cowTR);

		List<Point> lawnPointsInCow = new ArrayList<>();
		lawnPointsInCow = lawn.getPointsIn(cow);

		if (lawnPointsInCow.isEmpty() || lawnPointsInCow.size() == 1) {
			output1 = lawn.getArea();
		} else if (lawnPointsInCow.size() == 4) {
			output1 = 0;
		} else if (lawnPointsInCow.size() == 2) {
			output1 = lawn.getArea() - lawn.getOverlappingArea(cow);
		}

		if (!test)
			writeLine(Integer.toString(output1));
		System.out.println(output1);
		
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("Seconds: " + totalTime/Math.pow(10, 9));
	}

	// Instantiates the files and file readers/writers. Called once at the
	// beginning.
	public static void setUp() {
		fileIn = new File(input);
		fileOut = new File(output);

		try {
			fr = new FileReader(fileIn);
			if (fr != null)
				br = new BufferedReader(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fw = new FileWriter(fileOut);
			if (fw != null)
				bw = new BufferedWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Writes a single line to the output file and moves to the beginning of the
	// next line
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
		} else
			System.out.println(line);
	}

	// Reads a single line of the input file then moves to the beginning of the next
	// line
	public static String readLine() {
		String line = "";
		try {
			if (br != null)
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return line;
		}	

}

class Rectangle {
	Point ll;
	Point tr;
	Point tl;
	Point lr;
	
	public Rectangle(Point lowLeft, Point topRight) {
		ll = lowLeft;
		tr = topRight;
		tl = new Point(lowLeft.x, topRight.y);
		lr = new Point(topRight.x, lowLeft.y);
	}
	
	public Point[] getAllPoints() {
		return new Point[] {ll, tr, tl, lr};
	}
	
	public Point[] getDefiningPoints() {
		return new Point[] {ll, tr};
	}
	
	public int getOverlappingArea(Rectangle rect) {	
		int x1, x2, y1, y2;
		Point[] definingPoints = rect.getDefiningPoints();
		Point rectLL = definingPoints[0];
		Point rectTR = definingPoints[1];
		
		if(rectLL.x > ll.x) {
			x1 = rectLL.x;
		}
		else x1 = ll.x;
		
		if(rectTR.x < tr.x) x2 = rectTR.x;
		else x2 = tr.x;
		
		if(rectLL.y > ll.y) y1 = rectLL.y;
		else y1 = ll.y;
		
		if(rectTR.y < tr.y) y2 = rectTR.y;
		else y2 = tr.y;
		
//		System.out.println((x1) + " " + (x2));
		
		return Math.abs((x1 - x2) * (y1 - y2));
	}
	
	public List<Point> getPointsIn(Rectangle rect) {
		List<Point> pointsList = new ArrayList<Point>();
		
		Point[] definingPoints = rect.getDefiningPoints();
		Point rectLL = definingPoints[0];
		Point rectTR = definingPoints[1];
		
		for(Point thisPoint : getAllPoints()) {
			if(thisPoint.x >= rectLL.x && thisPoint.x <= rectTR.x &&
					thisPoint.y >= rectLL.y && thisPoint.y <= rectTR.y) {
				pointsList.add(thisPoint);
			}
		}
		
		return pointsList;
	}
	
	public int getArea() {
		return (tr.x - ll.x) * (tr.y - ll.y);
	}
}

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
