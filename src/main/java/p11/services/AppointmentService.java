package p11.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import p11.dto.AppointmentDTO;
import p11.repositories.AppointmentRepository;

@Service
public class AppointmentService {
	private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

	@Autowired
	private AppointmentRepository appointmentRepository;

	public void createAppointment(AppointmentDTO appointmentDTO) {
		logger.info("create appointment with {}", appointmentDTO);
		appointmentRepository.createAppointment(appointmentDTO);

	}

	public List<AppointmentDTO> getAppointments() {
		logger.info("Get appointments");
		return appointmentRepository.getAppointments();
	}

}
