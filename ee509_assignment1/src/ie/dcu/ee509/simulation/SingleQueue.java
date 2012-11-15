/**
 * 
 */
package ie.dcu.ee509.simulation;

import ie.dcu.ee509.manager.EventListManager;
import ie.dcu.ee509.model.Event;
import ie.dcu.ee509.utils.EventGenerator;
import ie.dcu.ee509.utils.RandomNumberGenerator;

import java.util.ArrayList;

/**
 * <p>
 * Title: SingleQueue.java
 * </p>
 * 
 * <p>
 * Dublin City University
 * </p>
 * 
 * @author Di Wu
 * 
 * @date 2012-11-12
 * 
 * @version 1.0
 */
public class SingleQueue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// getBatchOfPacketDelay(5000);
		// getMeanOfAllBatches(200000, 5000);
	}

	public static double getMeanBatchOfPacketDelay(int sizeOfBatch) {
		EventListManager.getInstance().getEventLinkedList().clear();
		// generate 10 arrival event
		ArrayList<Event> list = EventGenerator
				.generateArrEventList(sizeOfBatch);
		// to store delay time.
		double alldelay = 0;
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
				// System.out.println(event.getEventClock()+"==="+lastDepEvent.getEventClock()+"==="+(lastDepEvent.getEventClock()-event.getEventClock()));
				alldelay += (lastDepEvent.getEventClock() - event
						.getEventClock());
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
				// System.out.println(event.getEventClock()+"==="+lastDepEvent.getEventClock()+"==="+(lastDepEvent.getEventClock()-event.getEventClock()));
				alldelay += (lastDepEvent.getEventClock() - event
						.getEventClock());
			}
		}
		// System.out.println(alldelay/sizeOfBatch);
		return alldelay / sizeOfBatch;
	}

	public static double[] getMeansOfEveryBatch(int arrivals, int sizeOfBatch) {
		int numOfBatches = arrivals / sizeOfBatch;
		double[] meansOfEveryBatch = new double[numOfBatches];
		for (int i = 0; i < meansOfEveryBatch.length - 1; i++) {
			meansOfEveryBatch[i] = getMeanBatchOfPacketDelay(sizeOfBatch);
		}
		meansOfEveryBatch[numOfBatches - 1] = getMeanBatchOfPacketDelay(sizeOfBatch * 80 / 100);
		return meansOfEveryBatch;
	}

	public static double getMeanOfAllBatches(int arrivals, int sizeOfBatch) {
		double[] meansOfEveryBatch = getMeansOfEveryBatch(arrivals, sizeOfBatch);
		double meanAllBatches = 0;
		for (int i = 0; i < meansOfEveryBatch.length; i++) {
			System.out.println(i + " batch mean -> " + meansOfEveryBatch[i]);
			meanAllBatches += meansOfEveryBatch[i];
		}
		meanAllBatches = meanAllBatches / 40;
		System.out.println("Mean = " + meanAllBatches);
		return meanAllBatches;
	}
}
