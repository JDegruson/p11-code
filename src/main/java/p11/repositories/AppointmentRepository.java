package p11.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import p11.dto.AppointmentDTO;
import p11.dto.AppointmentRowMapper;

@Repository
public class AppointmentRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public AppointmentRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void createAppointment(AppointmentDTO appointmentDTO) {
		String sql = "INSERT INTO appointment (longitude, latitude, patient_name, hospital_name, speciality, time) VALUES (:longitude,:latitude,:patient_name,:hospital_name,:speciality,:time);";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("longitude", appointmentDTO.getLongitude());
		paramMap.put("latitude", appointmentDTO.getLatitude());
		paramMap.put("patient_name", appointmentDTO.getPatientName());
		paramMap.put("hospital_name", appointmentDTO.getHospitalName());
		if (appointmentDTO.getSpeciality() != null) {
			paramMap.put("speciality", appointmentDTO.getSpeciality().name());
		}
		paramMap.put("time", appointmentDTO.getTime());
		jdbcTemplate.update(sql, paramMap);

	}

	public List<AppointmentDTO> getAppointments() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM appointment");
		return jdbcTemplate.query(sql.toString(), paramMap, new AppointmentRowMapper());
	}

}
