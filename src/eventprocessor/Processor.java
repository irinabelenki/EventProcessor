package eventprocessor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
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

	public enum EVENT {
		A, B, IDLE, ILLEGAL
	};

	private static List<EVENT> eventQueue = new LinkedList<EVENT>();

	public static void main(String[] args) {
		for (int i = 0; i < QUEUE_SIZE; i++) {
			eventQueue.add(EVENT.IDLE);
		}
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));

		while (true) {
			System.out.println("Enter event");

			try {
				String event = bufferedReader.readLine();
				EVENT enumEvent = eventToEnum(event);
				if (!enumEvent.equals(EVENT.ILLEGAL)) {
					processQueue(enumEvent);
				} else {
					System.out.println("Input completed");
					processQueue(EVENT.IDLE);
					processQueue(EVENT.IDLE);
					break;
				}
			} catch (Exception e) {
				System.err.println("Exception: " + e.getMessage());
			}
		}
	}

	private static EVENT eventToEnum(String event) {
		if (event.toUpperCase().equals("A")) {
			return EVENT.A;
		} else if (event.toUpperCase().equals("B")) {
			return EVENT.B;
		} else if (event.toUpperCase().equals("IDLE")) {
			return EVENT.IDLE;
		} else
			return EVENT.ILLEGAL;
	}

	private static void processQueue(EVENT event) {
		eventQueue.remove(0);
		eventQueue.add(event);
		Iterator<EVENT> queueIt = eventQueue.iterator();

		if (!queueIt.next().equals(EVENT.B) 
				&& !queueIt.next().equals(EVENT.B)
				&& queueIt.next().equals(EVENT.A)
				&& !queueIt.next().equals(EVENT.B)
				&& !queueIt.next().equals(EVENT.B)) {

			System.out.println("A");
		}
	}
}