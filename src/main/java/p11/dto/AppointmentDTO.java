package p11.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import p11.enums.Speciality;

public class AppointmentDTO {

	private Long id;
	private Double longitude;
	private Double latitude;
	private Speciality speciality;
	private String patientName;
	private String hospitalName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "AppointmentDTO [id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + ", speciality="
				+ speciality + ", patientName=" + patientName + ", hospitalName=" + hospitalName + ", time=" + time
				+ "]";
	}

}
