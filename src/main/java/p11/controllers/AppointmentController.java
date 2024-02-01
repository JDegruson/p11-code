package p11.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p11.dto.AppointmentDTO;
import p11.services.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping(path = "/create")
	public void createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
		logger.info("create appointment with {} ", appointmentDTO);
		appointmentService.createAppointment(appointmentDTO);
	}

	@GetMapping(path = "/appointments")
	public ResponseEntity<?> getAppointments() {
		logger.info("Get appointments");
		List<AppointmentDTO> appointments = appointmentService.getAppointments();
		return ResponseEntity.ok(appointments);
	}
}
