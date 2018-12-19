package usaco2018.USOpen;

import java.text.DecimalFormat;
import java.util.Random;

public class Test {
	
	enum Days {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
	}
	
	Days days = Days.SUNDAY;
	
	

	public static void main(String[] args) {
		DecimalFormat fmt = new DecimalFormat("0.###");
		System.out.print(fmt.format(8));
		Random rand= new Random();
		rand.nextInt(11);
		//System.out.println( 2 +4+"uwu");

	}

}
