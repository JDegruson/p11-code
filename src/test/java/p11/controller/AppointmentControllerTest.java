package p11.controller;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import p11.controllers.AppointmentController;
import p11.dto.AppointmentDTO;
import p11.services.AppointmentService;

public class AppointmentControllerTest {

	@InjectMocks
	private AppointmentController appointmentController;

	@Mock
	private AppointmentService appointmentService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void getAppointmentsTest() {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setHospitalName("hospital");

		when(appointmentService.getAppointments()).thenReturn(asList(appointmentDTO));

		ResponseEntity<List<AppointmentDTO>> appointments = appointmentController.getAppointments();

		List<AppointmentDTO> body = appointments.getBody();
		MatcherAssert.assertThat(body, hasSize(1));
		MatcherAssert.assertThat(body.get(0).getHospitalName(), Matchers.equalTo("hospital"));
	}

	@Test
	public void createAppointmentTest() {
		ArgumentCaptor<AppointmentDTO> captor = ArgumentCaptor.forClass(AppointmentDTO.class);

		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setHospitalName("hospital");

		appointmentController.createAppointment(appointmentDTO);

		verify(appointmentService).createAppointment(captor.capture());

		AppointmentDTO value = captor.getValue();

		assertThat(value.getHospitalName(), Matchers.equalTo("hospital"));
	}

}
