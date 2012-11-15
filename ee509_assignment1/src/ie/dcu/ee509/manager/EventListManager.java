package ie.dcu.ee509.manager;

import ie.dcu.ee509.model.Event;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This class implements the functionalities of an event list in the "event
 * advance" simulation design. It provides methods to manage the insertion, the
 * sorting and the removal of an event in the list. Only one instance of this
 * class is admissible (Singleton java pattern). To create an instance of this
 * class use the method getInstance() and not the constructor. For Example :
 * 
 * EventList myEventList = EventList.getInstance();
 * 
 * 
 * @author Daniele Tafani 10/10/09
 * 
 */
public class EventListManager {

	/**
	 * The instance of the EventList class.
	 */
	private static EventListManager instance;

	/**
	 * The event list implemented as a Java LinkedList object.
	 */
	private static LinkedList<Event> eventList;

	/**
	 * Constructor of the class. It creates a LinkedList object.
	 */
	private EventListManager() {
		eventList = new LinkedList<Event>();
	}

	/**
	 * Returns the singleton instance.
	 * 
	 * @return instance : The EventListManager instance.
	 */
	public static synchronized EventListManager getInstance() {
		if (instance == null)
			instance = new EventListManager();
		return instance;
	}

	/**
	 * Insert and sort an event to the list of events according to its
	 * associated clock value.
	 * 
	 * If the list is empty, it adds the event as the first element of the list.
	 * If the list is not empty, it inserts and sorts the event according to its
	 * clock value (for example if the list has 2 events which will occur at
	 * CLOCK_1 = 1.5 and CLOCK_2 = 2.4 and if the event to be inserted has CLOCK
	 * = 1.6, the method will insert the event between the 2 events). If the
	 * event clock is greater than all the event clocks of the list, the method
	 * will insert it as the last element.
	 * 
	 * @param event
	 *            (Event) : the event to be inserted in the list.
	 * 
	 */
	public void insertEvent(Event event) {

		boolean isInserted = false;

		if (isEmpty()) {
			eventList.addFirst(event);
			isInserted = true;
		}

		else {
			ListIterator<Event> iterator = eventList.listIterator();
			while (iterator.hasNext()) {

				Event listEvent = iterator.next();
				if (event.getEventClock() <= listEvent.getEventClock()) {

					iterator.previous();
					iterator.add(event);
					isInserted = true;
					break;
				}
			}
		}

		if (!isInserted)
			eventList.addLast(event);
	}

	/**
	 * Removes and returns the first event from the list.
	 * 
	 * @return firstEvent (Event) : the first event of the list.
	 * 
	 */
	public Event poll() {
		Event firstEvent = eventList.removeFirst();
		return firstEvent;
	}

	/**
	 * Check if the list does not contain events.
	 * 
	 * @return isEmpty (boolean) : "true" if the list is empty, "false"
	 *         viceversa.
	 * 
	 */
	public boolean isEmpty() {
		boolean isEmpty = eventList.isEmpty();
		return isEmpty;
	}
	
	public LinkedList<Event> getEventLinkedList(){
		return eventList;
	}
}