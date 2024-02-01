package p11.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p11.dto.AppointmentDTO;
import p11.dto.HospitalDTO;
import p11.services.HospitalService;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

	private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);

	@Autowired
	private HospitalService hospitalService;

	@PostMapping(path = "/findHospital")
	public HospitalDTO findHospital(@RequestBody AppointmentDTO appointmentDTO) {
		logger.info("Find nearest hospital with this appointment : {}", appointmentDTO);
		return hospitalService.findHospital(appointmentDTO);

	}
}
