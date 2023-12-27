package p11.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import p11.dto.AppointmentDTO;
import p11.dto.HospitalDTO;
import p11.services.AppointmentService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

	@Autowired
	private AppointmentService appointmentService;

	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */
	@PostMapping(path = "/create")
	@ResponseStatus(HttpStatus.OK)
	public void createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
		logger.info("create appointment with {} ", appointmentDTO);
		appointmentService.createAppointment(appointmentDTO);
	}

	@PostMapping(path = "/findHospital")
	@ResponseStatus(HttpStatus.OK)
	public HospitalDTO findHospital(@RequestBody AppointmentDTO appointmentDTO) {
		logger.info("Find nearest hospital with this appointment : {}", appointmentDTO);
		return appointmentService.findHospital(appointmentDTO);

	}
}
