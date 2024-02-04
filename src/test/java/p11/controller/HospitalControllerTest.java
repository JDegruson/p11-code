package p11.controller;

import static org.mockito.Mockito.verify;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import p11.controllers.HospitalController;
import p11.dto.AppointmentDTO;
import p11.services.HospitalService;

public class HospitalControllerTest {

	@InjectMocks
	private HospitalController hospitalController;

	@Mock
	private HospitalService hospitalService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void createAppointmentTest() {
		ArgumentCaptor<AppointmentDTO> captor = ArgumentCaptor.forClass(AppointmentDTO.class);

		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setHospitalName("hospital");

		hospitalController.findHospital(appointmentDTO);

		verify(hospitalService).findHospital(captor.capture());

		AppointmentDTO value = captor.getValue();

		MatcherAssert.assertThat(value.getHospitalName(), Matchers.equalTo("hospital"));
	}
}
