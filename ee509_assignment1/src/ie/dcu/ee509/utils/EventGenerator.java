/**
 * 
 */
package ie.dcu.ee509.utils;

import java.util.ArrayList;
import java.util.LinkedList;

import ie.dcu.ee509.manager.EventListManager;
import ie.dcu.ee509.model.Event;
import ie.dcu.ee509.model.EventType;

/**
 * <p>
 * Title: EventGenerator.java
 * </p>
 * 
 * <p>
 * Dublin City University
 * </p>
 * 
 * @author Di Wu
 * 
 * @date 2012-11-11
 * 
 * @version 1.0
 */
public class EventGenerator {
	// final static double mu = 1.25 * Math.pow(10, 7);
	// final static double lambda = 1 * Math.pow(10, 7);
	// For the convenience of calculation,change values of mu and lambda.
	final static double mu = 1.25;
	final static double lambda = 1;

	public static Event generateArrEvent() {
		double arrivalTime = RandomNumberGenerator.getArrivalTime(lambda);
		Event arrEvent = new Event(arrivalTime, EventType.ARRIVE);
		return arrEvent;
	}

	public static Event generateNextArrEvent(Event lastEvent) {
		double arrivalTime = RandomNumberGenerator.getArrivalTime(lambda);
		Event arrEvent = new Event(lastEvent.getEventClock() + arrivalTime,
				EventType.ARRIVE);
		return arrEvent;
	}

	public static Event generateDepEvent(Event event) {
		double serviceTime = RandomNumberGenerator.getServiceTime(mu);
		double departureTime = event.getEventClock() + serviceTime;
		Event depEvent = new Event(departureTime, EventType.DEPARTURE);
		return depEvent;
	}

	public static ArrayList<Event> generateArrEventList(int num) {
		ArrayList<Event> list = new ArrayList<Event>();
		list.add(generateArrEvent());
		for (int i = 1; i < num; i++) {
			list.add(generateNextArrEvent(list.get(i - 1)));
		}
		return list;
	}
	
	public static ArrayList<Event> generateSortArrEventList(int num) {
		ArrayList<Event> list = new ArrayList<Event>();
		for (int i = 1; i < num; i++) {
			EventListManager.getInstance().insertEvent(generateArrEvent());
		}
		LinkedList<Event> linkedlist = EventListManager.getInstance().getEventLinkedList();
		for (Event e : linkedlist) {
			list.add(e);
		}
		EventListManager.getInstance().getEventLinkedList().clear();
		return list;
	}
}
