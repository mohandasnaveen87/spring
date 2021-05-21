package coll;

import java.util.Scanner;

public class CollectionQueue {
	
	static Node first=null;
	static Node last=null;
	static int qSize=0;
	public static void main(String[] args) {
		
		int enqueueFlag=1;
		int dequeueFlag=0;
		 
		while(enqueueFlag==1) {
		 // Create a Scanner object
			Scanner myObj = new Scanner(System.in);
	    System.out.println("Enter roll no");

	    String rollNo = myObj.nextLine();
	    
	    enqueue(rollNo);
	    System.out.println("Do you want to continue");
	    enqueueFlag=myObj.nextInt();
		}
		traverse();
		System.out.println("Do you want to remove from queue");
		Scanner myObj1 = new Scanner(System.in);
		dequeueFlag=myObj1.nextInt();
		while(dequeueFlag==1) {
			dequeue();
			System.out.println("Do you want to remove from queue");
			dequeueFlag=myObj1.nextInt();
		}    
		traverse();
	}
	private static void enqueue(String rollNo) {
		// TODO Auto-generated method stub
		if(qSize==0)
		{
			first=new Node(rollNo);
			first.next=null;
			last=first;
			qSize++;
		}
		else if (qSize==1) {
			
			last.next=new Node(rollNo);
			last=last.next;
			first.next=last;
			qSize++;
		}
		else {
			last.next=new Node(rollNo);
			last=last.next;
		}
		
}
	private static void dequeue() {
		// TODO Auto-generated method stub
		first=first.next;
		qSize--;

	}
	private static void traverse() {
		for(Node x=first;x!=null;x=x.next) {
	    	System.out.println(x.rollno);
	    }
		
	}
}
