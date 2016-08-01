package lightSwitches;


import java.util.Scanner;

public class Test {
	
	private static float count = 0f;
	private static int numCase = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while(scan.hasNext()){
			float numBulb = scan.nextFloat();
			float time = scan.nextFloat();
			float theBulb = scan.nextFloat();
			
			time = time%numBulb;
			
			numCase = numCase + 1;
			count = 0;
			
			sim(numBulb, time, theBulb);
			
			if (count % 2 != 0) {
				System.out.println("Case " + numCase + ": On");
			} else {
				System.out.println("Case " + numCase + ": Off");
			}
		}
		
		
		scan.close();
	}
	
	private static void sim(float numBulb, float time, float theBulb){
		float simTime = 1f;
		while(simTime <= time){
			if(isExactDiv(theBulb, simTime)){
				count = count + 1;
			}
			simTime = simTime + 1f;
		}
		
	}
	
	private static boolean isExactDiv(float theBulb, float simTime){
		float remainder = theBulb % simTime;
		System.out.println(remainder);
		if(remainder == 0){
			return true;
		}else{
			return false;
		}
	}

}
