package p11.dto;

import java.util.List;

import p11.enums.Speciality;

public class HospitalDTO {
	private String name;
	private List<Speciality> specialities;
	private Double longitude;
	private Double latitude;
	private String distance;
	private String time;
	private int numberOfAvailableBed;
	private boolean selected;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Speciality> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(List<Speciality> specialities) {
		this.specialities = specialities;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getNumberOfAvailableBed() {
		return numberOfAvailableBed;
	}

	public void setNumberOfAvailableBed(int numberOfAvailableBed) {
		this.numberOfAvailableBed = numberOfAvailableBed;
	}

	@Override
	public String toString() {
		return "HospitalDTO [name=" + name + ", specialities=" + specialities + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", distance=" + distance + ", time=" + time + ", numberOfAvailableBed="
				+ numberOfAvailableBed + ", selected=" + selected + "]";
	}

}
