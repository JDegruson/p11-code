package p11.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import p11.dto.AppointmentDTO;
import p11.enums.Speciality;
import p11.repositories.AppointmentRepository;

public class AppointmentRepositoryTest {

	@Test
	public void testCreateAppointment() {
		NamedParameterJdbcTemplate jdbcTemplate = mock(NamedParameterJdbcTemplate.class);

		AppointmentRepository appointmentRepository = new AppointmentRepository(jdbcTemplate);

		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setLongitude(1.0);
		appointmentDTO.setLatitude(2.0);
		appointmentDTO.setPatientName("John Doe");
		appointmentDTO.setHospitalName("Sample Hospital");

		int queryResult = 1;

		when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(queryResult);

		appointmentRepository.createAppointment(appointmentDTO);

		verify(jdbcTemplate).update(anyString(), eq(getExpectedParamMap(appointmentDTO)));
	}

	@Test
	public void testgetAppointments() {

		DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:schema.sql").addScript("classpath:data.sql").build();

		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

		AppointmentRepository appointmentRepository = new AppointmentRepository(jdbcTemplate);

		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setId(1L);
		appointmentDTO.setLongitude(1.0);
		appointmentDTO.setLatitude(2.0);
		appointmentDTO.setPatientName("John Doe");
		appointmentDTO.setHospitalName("Sample Hospital");
		appointmentDTO.setSpeciality(Speciality.SOINS_INTENSIFS);
		appointmentDTO.setTime(LocalDateTime.parse("2023-01-01T10:00:00"));

		List<AppointmentDTO> appointments = appointmentRepository.getAppointments();

		assertThat(appointments, hasItem(hasSameAttributes(appointmentDTO)));
	}

	private Map<String, Object> getExpectedParamMap(AppointmentDTO appointmentDTO) {
		Map<String, Object> expectedParamMap = new HashMap<>();
		expectedParamMap.put("longitude", appointmentDTO.getLongitude());
		expectedParamMap.put("latitude", appointmentDTO.getLatitude());
		expectedParamMap.put("patient_name", appointmentDTO.getPatientName());
		expectedParamMap.put("hospital_name", appointmentDTO.getHospitalName());
		if (appointmentDTO.getSpeciality() != null) {
			expectedParamMap.put("speciality", appointmentDTO.getSpeciality().name());
		}
		expectedParamMap.put("time", appointmentDTO.getTime());
		return expectedParamMap;
	}

	private static Matcher<AppointmentDTO> hasSameAttributes(AppointmentDTO expected) {
		return allOf(hasProperty("id", equalTo(expected.getId())),
				hasProperty("latitude", equalTo(expected.getLatitude())),
				hasProperty("longitude", equalTo(expected.getLongitude())),
				hasProperty("speciality", equalTo(expected.getSpeciality())),
				hasProperty("patientName", equalTo(expected.getPatientName())),
				hasProperty("hospitalName", equalTo(expected.getHospitalName())),
				hasProperty("time", equalTo(expected.getTime())));
	}
}
