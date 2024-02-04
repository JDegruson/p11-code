package p11.service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import p11.dto.AppointmentDTO;
import p11.dto.HospitalDTO;
import p11.enums.Speciality;
import p11.services.HospitalService;

public class HospitalServiceTest {

	@Test
	public void testFindHospital() throws Exception {

		HospitalService hospitalService = new HospitalService("AIzaSyCdawcAYPOV_peGZ3QYh7AFgo7jj5TakP0");

		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setLatitude(37.7749);
		appointmentDTO.setLongitude(-122.4194);
		appointmentDTO.setSpeciality(Speciality.AUTREALE);

		HospitalDTO findHospital = hospitalService.findHospital(appointmentDTO);

		MatcherAssert.assertThat(findHospital.getSpecialities(), Matchers.hasItem(Speciality.AUTREALE));

	}
}
