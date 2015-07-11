package eventprocessor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/*
 "IDLE IDLE A IDLE IDLE B   " outputs "A"
 "IDLE IDLE A IDLE B    IDLE" outputs ""
 "B    IDLE A IDLE IDLE IDLE" outputs ""
 "A    A    A A    A    A   " outputs "AAAAAA"
 */

public class Processor {
	public static final int QUEUE_SIZE = 5;
	private static List<String> eventQueue = new LinkedList<String>();

	public static void main(String[] args) {		
		for (int i = 0; i < QUEUE_SIZE; i++) {
			eventQueue.add("IDLE");
		}
		while (true) {
			System.out.println("Enter event");
			try {
				String event = new BufferedReader(new InputStreamReader(
						System.in)).readLine();
				if (event.toUpperCase().equals("A")
						|| event.toUpperCase().equals("B")
						|| event.toUpperCase().equals("IDLE")) {
					processQueue(event);					
				} else {
					System.out.println("Input completed");
					processQueue("IDLE");
					processQueue("IDLE");
					break;
				}
			} catch (Exception e) {
				System.err.println("Exception: " + e.getMessage());
			}
		}
	}
	
	private static void processQueue(String event) {
		eventQueue.remove(0);
		eventQueue.add(event);
		
		if (eventQueue.get(2).toUpperCase().equals("A")
				&& !eventQueue.get(0).toUpperCase().equals("B")
				&& !eventQueue.get(1).toUpperCase().equals("B")
				&& !eventQueue.get(3).toUpperCase().equals("B")
				&& !eventQueue.get(4).toUpperCase().equals("B")) {

			System.out.println("A");
		}
	}
}