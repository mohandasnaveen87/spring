package coll;

import java.util.Scanner;

public class CollectionStack {
	
	static Node first=null;
	static int stackSize=0;
public static void main(String[] args) {
	
		
		/*
		 * Node first=new Node(); first.rollno="11"; Node second=new Node();
		 * second.rollno="12"; first.next=second; second.next=null;
		 * System.out.println(first.next.rollno); //insert Node copyFirst=first;
		 * first=new Node(); first.rollno="10"; first.next=copyFirst;
		 * 
		 * System.out.println(first.rollno+" "+first.next.rollno+" "+first.next.next.
		 * rollno);
		 * 
		 * //remove first=first.next;
		 * System.out.println(first.rollno+" "+first.next.rollno);
		 */
	int pushFlag=1;
	int popFlag=0;
	 
	while(pushFlag==1) {
	 // Create a Scanner object
		Scanner myObj = new Scanner(System.in);
    System.out.println("Enter roll no");

    String rollNo = myObj.nextLine();
    
    push(rollNo);
    System.out.println("Do you want to continue");
    pushFlag=myObj.nextInt();
	}
	traverse();
	
	System.out.println("Do you want to remove from stack");
	Scanner myObj1 = new Scanner(System.in);
	popFlag=myObj1.nextInt();
	while(popFlag==1) {
		pop();
		System.out.println("Do you want to remove from stack");
		popFlag=myObj1.nextInt();
	}    
	traverse();
}

private static void traverse() {
	for(Node x=first;x!=null;x=x.next) {
    	System.out.println(x.rollno);
    }
	
}

private static void pop() {
	// TODO Auto-generated method stub
	first=first.next;
	stackSize--;

}

private static void push(String rollNo) {
	// TODO Auto-generated method stub
	if(stackSize==0)
	{
		first=new Node(rollNo);
		first.next=null;
		stackSize++;
	}
	else
	{
		Node oldFirst=first;
		first=new Node(rollNo);
		first.next=oldFirst;
		stackSize++;
	}
	
}
 boolean isEmpty(){
	return stackSize==0;
	
}
}
