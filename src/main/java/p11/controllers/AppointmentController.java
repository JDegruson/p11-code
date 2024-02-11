package p11.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p11.dto.AppointmentDTO;
import p11.services.AppointmentService;

/**
 * AppointmentController is a Spring MVC RestController responsible for handling
 * HTTP requests related to appointments. It defines endpoints for creating and
 * retrieving appointments.
 * 
 * <p>
 * This controller uses the {@link AppointmentService} to perform business logic
 * related to appointments.
 * </p>
 * 
 * <p>
 * The endpoints provided by this controller are:
 * </p>
 * <ul>
 * <li><strong>POST /appointment/appointment:</strong> Create a new appointment.
 * The request body should contain an {@link AppointmentDTO} representing the
 * appointment details.</li>
 * <li><strong>GET /appointment/appointments:</strong> Retrieve a list of
 * appointments.</li>
 * </ul>
 * 
 */
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

	@Autowired
	private AppointmentService appointmentService;

	/**
	 * Handles the HTTP POST request to create a new appointment.
	 * 
	 * @param appointmentDTO the appointment details provided in the request body
	 * @return a ResponseEntity indicating the success of the operation
	 */
	@PostMapping(path = "/appointment")
	public ResponseEntity<String> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
		logger.info("create appointment with {} ", appointmentDTO);
		appointmentService.createAppointment(appointmentDTO);
		return ResponseEntity.ok().build();
	}

	/**
	 * Handles the HTTP GET request to retrieve a list of appointments.
	 * 
	 * @return a ResponseEntity containing the list of appointments
	 */
	@GetMapping(path = "/appointments")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<AppointmentDTO>> getAppointments() {
		logger.info("Get appointments");
		List<AppointmentDTO> appointments = appointmentService.getAppointments();
		return ResponseEntity.ok(appointments);
	}
}
