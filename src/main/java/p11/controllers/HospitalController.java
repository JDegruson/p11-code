package p11.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p11.dto.AppointmentDTO;
import p11.dto.HospitalDTO;
import p11.services.HospitalService;

/**
 * HospitalController is a Spring MVC RestController responsible for handling
 * HTTP requests related to hospitals. It defines an endpoint for finding the
 * nearest hospital based on appointment details.
 * 
 * <p>
 * This controller uses the {@link HospitalService} to perform business logic
 * related to hospitals.
 * </p>
 * 
 * <p>
 * The endpoint provided by this controller is:
 * </p>
 * <ul>
 * <li><strong>POST /hospital/hospital:</strong> Find the nearest hospital based
 * on the provided {@link AppointmentDTO}. The request body should contain an
 * {@link AppointmentDTO} representing the appointment details.</li>
 * </ul>
 * 
 */
@RestController
@RequestMapping("/hospital")
public class HospitalController {

	private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);

	@Autowired
	private HospitalService hospitalService;

	/**
	 * Handles the HTTP POST request to find the nearest hospital based on the
	 * provided appointment details.
	 * 
	 * @param appointmentDTO the appointment details provided in the request body
	 * @return a ResponseEntity containing the nearest hospital
	 */
	@PostMapping(path = "/hospital")
	public ResponseEntity<HospitalDTO> findHospital(@RequestBody AppointmentDTO appointmentDTO) {
		logger.info("Find nearest hospital with this appointment : {}", appointmentDTO);
		return ResponseEntity.ok(hospitalService.findHospital(appointmentDTO));

	}
}
