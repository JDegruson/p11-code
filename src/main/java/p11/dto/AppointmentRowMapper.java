package p11.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import p11.enums.Speciality;

public class AppointmentRowMapper implements RowMapper<AppointmentDTO> {

	@Override
	public AppointmentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setId(rs.getLong("id"));
		appointmentDTO.setLongitude(rs.getDouble("longitude"));
		appointmentDTO.setLatitude(rs.getDouble("latitude"));
		appointmentDTO.setPatientName(rs.getString("patient_name"));
		appointmentDTO.setHospitalName(rs.getString("hospital_name"));
		appointmentDTO.setSpeciality(Speciality.valueOf(rs.getString("speciality").toUpperCase()));
		appointmentDTO.setTime(rs.getTimestamp("time").toLocalDateTime());
		return appointmentDTO;
	}

}
