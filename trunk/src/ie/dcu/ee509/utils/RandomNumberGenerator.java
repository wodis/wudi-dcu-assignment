/**
 * 
 */
package ie.dcu.ee509.utils;

/**
 * <p>
 * Title: RandomNumberGenerator.java
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
public class RandomNumberGenerator {
	public static double getServiceTime(double mu) {
		double serviceTime = -(1 / mu) * Math.log(1 - Math.random());
		return serviceTime;
	}

	public static double getArrivalTime(double lambda) {
		double arrivalTime = -(1 / lambda) * Math.log(1 - Math.random());
		return arrivalTime;
	}

	public static void main(String args[]) {
		double departureTime;
		double arrivalTime;
		double serviceTime;
		double mu = 1.25 * Math.pow(10, 7);
		double lambda = 1 * Math.pow(10, 7);
		
		arrivalTime = RandomNumberGenerator.getArrivalTime(lambda);
		serviceTime = RandomNumberGenerator.getServiceTime(mu);
		departureTime = arrivalTime + serviceTime;
		
		System.out.println(mu);
		System.out.println(lambda);
		System.out.println(departureTime);
	}
}
