package Graph;

import List.SingleLinkedList;

public class Vertex implements Comparable<Vertex> {
	private Double latitude;
	private Double Longitude;
	private String Location;
	private SingleLinkedList<Edge> list;
	private boolean isCity;

	public Vertex(Double latitude, Double longitude, String city, boolean isCity) {
		setLatitude(latitude);
		setLongitude(longitude);
		setLocation(city);
		list = new SingleLinkedList<Edge>();
		this.isCity = isCity;
	}

	public Vertex(String location) {
		this.Location = location;
	}

	public boolean isCity() {
		return isCity;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return Longitude;
	}

	public void setLongitude(Double longitude) {
		Longitude = longitude;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public SingleLinkedList<Edge> getList() {
		return list;
	}

	@Override
	public String toString() {
		return Location;
	}

	@Override
	public int hashCode() {
		return Location.hashCode();
	}

	@Override
	public int compareTo(Vertex o) {
		return this.Location.compareTo(o.Location);
	}

}
