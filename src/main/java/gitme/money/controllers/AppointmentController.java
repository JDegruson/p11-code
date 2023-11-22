package gitme.money.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gitme.money.dto.AppointmentDTO;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */
	@PostMapping(path = "/create")
	@ResponseStatus(HttpStatus.OK)
	public String appointment(@RequestParam AppointmentDTO appointmentDTO) {
		return "OK";
	}
}
