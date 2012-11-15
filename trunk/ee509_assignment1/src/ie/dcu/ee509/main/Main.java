/**
 * 
 */
package ie.dcu.ee509.main;

import java.util.ArrayList;

import ie.dcu.ee509.manager.EventListManager;
import ie.dcu.ee509.model.Event;
import ie.dcu.ee509.utils.EventGenerator;

/**
 * <p>
 * Title: Main.java
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
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		eventProcessLoop();
	}

	public static void eventProcessLoop() {
		// generate 10 arrival event
		ArrayList<Event> list = EventGenerator.generateArrEventList(500);
		// record the last departure event
		Event lastDepEvent = null;
		// iterate this whole event list
		for (Event event : list) {
			EventListManager manger = EventListManager.getInstance();
			// if event list of EventListManager is empty, add arrival event and
			// departure event immediately.
			if (manger.isEmpty()) {
				manger.insertEvent(event);
				Event depEvent = EventGenerator.generateDepEvent(event);
				manger.insertEvent(depEvent);
				lastDepEvent = depEvent;
				System.out.println(event.getEventClock()+"==="+lastDepEvent.getEventClock()+"==="+(lastDepEvent.getEventClock()-event.getEventClock()));
			} else {
				manger.insertEvent(event);
				if (event.getEventClock() <= lastDepEvent.getEventClock()) {
					Event depEvent = EventGenerator
							.generateDepEvent(lastDepEvent);
					manger.insertEvent(depEvent);
					lastDepEvent = depEvent;
				} else {
					Event depEvent = EventGenerator.generateDepEvent(event);
					manger.insertEvent(depEvent);
					lastDepEvent = depEvent;
				}
				System.out.println(event.getEventClock()+"==="+lastDepEvent.getEventClock()+"==="+(lastDepEvent.getEventClock()-event.getEventClock()));
			}
		}
	}
}
