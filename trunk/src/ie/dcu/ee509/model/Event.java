package ie.dcu.ee509.model;

/**
 * This class is an implementation of the event object to be inserted in the
 * event list. In the current version it contains just an attribute, the clock
 * value at which the event occurs. It also contains a set of get() and set()
 * methods to retrieve and modify its clock value. The student has to define the
 * constructor of this class and other eventual additional attributes that are
 * necessary to uniquely define the event.
 * 
 * @author Daniele Tafani 10/10/09
 * 
 */
public class Event {

	/**
	 * The clock value of the event.
	 */
	private double eventClock;

	/**
	 * Constructor of the class: to be developed by the student...
	 * 
	 */
	public Event(double eventClock) {
		this.eventClock = eventClock;
	}

	/**
	 * Returns the event clock value.
	 * 
	 * @return (double) eventClock : the even clock value (double).
	 */
	public double getEventClock() {
		return eventClock;
	}

	/**
	 * Modifies the event clock value.
	 * 
	 * @param (double) eventClock : the new event clock value (double).
	 */
	public void setEventClock(double eventClock) {
		this.eventClock = eventClock;
	}

	// Add attributes that are necessary to uniquely define the event object.
	// ....
	private EventType enventType = EventType.ARRIVE;

	private Packet packet = null;

	/**
	 * @param enventType
	 *            the enventType to set
	 */
	public void setEnventType(EventType enventType) {
		this.enventType = enventType;
	}

	/**
	 * @return the enventType
	 */
	public EventType getEnventType() {
		return enventType;
	}

	/**
	 * @param packet
	 *            the packet to set
	 */
	public void setPacket(Packet packet) {
		this.packet = packet;
	}

	/**
	 * @return the packet
	 */
	public Packet getPacket() {
		return packet;
	}

	public Event(double eventClock, EventType et) {
		this.eventClock = eventClock;
		this.enventType = et;
	}
	
	public Event(double eventClock, EventType et, Packet packet) {
		this.eventClock = eventClock;
		this.enventType = et;
		this.setPacket(packet);
	}
}