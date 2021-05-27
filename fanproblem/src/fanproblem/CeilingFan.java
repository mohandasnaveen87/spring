package fanproblem;

import java.util.Scanner;

public class CeilingFan  implements Fan{

	private static int speed=0;
	private static String direction="forward";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         Fan fan=new CeilingFan();
         System.out.println("The fan is at speed "+speed+" and"+ (speed==0?" stopped":"moving in "+direction));
         System.out.println("Enter 1 to increase and 2 to change direction");
         Scanner sc=new Scanner(System.in);
          while(sc.hasNextInt()) {
        	  
        	  switch(sc.nextInt()) {
        	  case 1: 
        		  	  fan.increase();
        	          break;
        	  case 2:
        		      if(speed!=0)
        		  	  fan.reverse();
        	          break;
        	  default:
        		      System.out.println("Invalid Operation.");
        	  }
              System.out.println("The fan is at speed "+speed+" and "+ (speed==0?" stopped":"moving in "+direction));
              System.out.println("Enter 1 to increase and 2 to change direction");
        	  
          }
         
         sc.close();
	      
	}

	@Override
	public void increase() {
		// TODO Auto-generated method stub
		if(speed<3)
		speed++;
		else
		speed=0;
		System.out.println("Speed is "+ speed);
	}

	@Override
	public void reverse() {
		// TODO Auto-generated method stub
		if(direction.equals("forward")) {
			direction="reverse";
		}
		else {
			direction="forward";
		}
	}

}
