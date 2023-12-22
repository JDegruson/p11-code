package p11.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
	public String createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
		return "OK";
	}

	@GetMapping(path = "/findHospital")
	@ResponseStatus(HttpStatus.OK)
	public HospitalDTO findHospital(@RequestBody AppointmentDTO appointmentDTO) {

//		StringBuilder apiUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json");
//		apiUrl.append("?location=-33.8670522,151.1957362");
//		apiUrl.append("&radius=1500");
//		apiUrl.append("&type=hospital");
//		apiUrl.append("&key=AIzaSyCdawcAYPOV_peGZ3QYh7AFgo7jj5TakP0");
//
//		RestTemplate restTemplate = new RestTemplate();
//		String forObject = restTemplate.getForObject(apiUrl.toString(), String.class);
//		logger.info(forObject);
		return appointmentService.findHospital(appointmentDTO);
		
	}
}
