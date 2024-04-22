package Graph;

public class Edge implements Comparable<Edge> {

	private Vertex distination;
	private double cost = 0;

	public Edge(Vertex source, Vertex distination) {
		setDistination(distination);
		calculateCost(source);
	}

	private void calculateCost(Vertex source) {
		// Radius of the Earth in kilometers
		final double earthRadius = 6371.0;

		// Convert latitude and longitude from degrees to radians
		double radLat1 = Math.toRadians(source.getLatitude());
		double radLon1 = Math.toRadians(source.getLongitude());
		double radLat2 = Math.toRadians(distination.getLatitude());
		double radLon2 = Math.toRadians(distination.getLongitude());

		// Calculate the differences in coordinates
		double deltaLat = radLat2 - radLat1;
		double deltaLon = radLon2 - radLon1;

		// Haversine formula
		double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
				+ Math.cos(radLat1) * Math.cos(radLat2) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		// Calculate the distance
		double distance = earthRadius * c;

		cost =  distance;

	}

	public Vertex getDistination() {
		return distination;
	}

	private void setDistination(Vertex distination) {
		this.distination = distination;
	}

	public double getCost() {
		return cost;
	}

	private void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		if (this.cost > o.cost)
			return 1;
		else if (this.cost < o.cost)
			return -1;
		return 0;
	}

}
