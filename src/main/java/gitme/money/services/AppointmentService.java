package gitme.money.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gitme.money.dto.AppointmentDTO;
import gitme.money.repositories.AppointmentRepository;

@Service
public class AppointmentService {
	private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

	@Autowired
	private AppointmentRepository appointmentRepository;

	public void createAppointment(AppointmentDTO appointmentDTO) {
		logger.info("create appointment with {}", appointmentDTO);
		appointmentRepository.createAppointment(appointmentDTO);

	}

}
