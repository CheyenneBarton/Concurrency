package HW;

import java.util.Random;

public class Concurrency_HW {

	static int sum = 0;
	static long totalTime1;
	static long totalTime2;
	
	public static void main(String[] args) {
		
		//start the timer
		long startTime1 = System.nanoTime();
		
	Thread t1 = new Thread (new Runnable() {
		public void run() {
			//create an array of first 100,000,000 random integers
					int[] arr = new int[100000000];
					
					//create a random object
					Random r = new Random();

					for (int i = 0; i < arr.length; i++) {
						
						//put the random numbers (ranging from 1 to 10) into an array
						arr[i] = r.nextInt(10) + 1;
						sum += arr[i]; //sum = sum + arr[i]
						
						//end the timer
						long endTime1 = System.nanoTime();
						
						totalTime1 = (endTime1 - startTime1);
						
					//seeing what the random numbers are in the array	
//					System.out.println(arr[i]);
					
					//Find which thread is doing what calculation
//					long threadId = Thread.currentThread().getId();
//					System.out.println("Thread t1: " + threadId);
				}
		}
	});
	
	//start the timer
	long startTime2 = System.nanoTime();
	
	Thread t2 = new Thread ( new Runnable() {
		public void run() {
			//create an array of the second 100,000,000 random integers
					int[] arr = new int[100000000];
					
					//create a random object
					Random r = new Random();

					for (int i = 0; i < arr.length; i++) {
						
						//put the random numbers (ranging from 1 to 10) into an array
						arr[i] = r.nextInt(10) + 1;
						synchronized (this) {
							sum += arr[i]; //sum = sum + arr[i]
							}
						
						//end the timer
						long endTime2 = System.nanoTime();
						
						totalTime2 = (endTime2 - startTime2);
						
						//seeing what the random numbers are in the array	
//						System.out.println(arr[i]);
						
						//Find which thread is doing what calculation
//						long threadId = Thread.currentThread().getId();
//						System.out.println("Thread t2: " + threadId);
				}
		}
	});
	
	//start the timer
	long startTime = System.nanoTime();
	
	t1.start();
	t2.start();
	
	try {
		t1.join();
		t2.join();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
	//end the timer
	long endTime = System.nanoTime();
	
	System.out.println("Total sum: " + sum + " in " + (endTime - startTime) + " nanoseconds."
	+ " Single thread time of t1: " + totalTime1 + " nanoseconds. "
			+ "t2 thread time " + totalTime2 + " nanoseconds.");
	
	}

}
