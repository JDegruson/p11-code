package p11.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.google.maps.model.RankBy;

import p11.dto.AppointmentDTO;
import p11.dto.HospitalDTO;
import p11.enums.Speciality;
import p11.repositories.AppointmentRepository;

@Service
public class AppointmentService {
	private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Value("${google.api.key}")
	private String apiKey;

	public void createAppointment(AppointmentDTO appointmentDTO) {
		logger.info("create appointment with {}", appointmentDTO);
		appointmentRepository.createAppointment(appointmentDTO);

	}

	public HospitalDTO findHospital(AppointmentDTO appointmentDTO) {
		GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
		LatLng originLocation = new LatLng(appointmentDTO.getLatitude(), appointmentDTO.getLongitude());

		List<HospitalDTO> hospitals = getNearbyHospitals(context, originLocation);

		return getClosestHospital(appointmentDTO, context, originLocation, hospitals);
	}

	private HospitalDTO getClosestHospital(AppointmentDTO appointmentDTO, GeoApiContext context, LatLng originLocation,
			List<HospitalDTO> hospitals) {
		for (HospitalDTO hospitalDTO : hospitals) {
			if (hospitalDTO.getSpecialities().contains(appointmentDTO.getSpeciality())
					&& hospitalDTO.getNumberOfAvailableBed() > 0) {
				hospitalDTO.setSelected(true);
				DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(context).origins(originLocation)
						.destinations(new LatLng(hospitalDTO.getLatitude(), hospitalDTO.getLongitude()))
						.awaitIgnoreError();
				hospitalDTO.setDistance(distanceMatrix.rows[0].elements[0].distance.humanReadable);
				hospitalDTO.setTime(distanceMatrix.rows[0].elements[0].duration.humanReadable);
				logger.info("selected hospital {}", hospitalDTO);
				return hospitalDTO;
			}
		}
		// If no hospital have the right speciality
		return null;
	}

	private List<HospitalDTO> getNearbyHospitals(GeoApiContext context, LatLng originLocation) {
		List<HospitalDTO> hospitals = new ArrayList<HospitalDTO>();
		try {
			PlacesSearchResponse placesSearchResponse = PlacesApi.nearbySearchQuery(context, originLocation)
					.rankby(RankBy.DISTANCE).type(PlaceType.HOSPITAL).await();

			for (PlacesSearchResult placesSearchResult : placesSearchResponse.results) {
				HospitalDTO hospitalDTO = new HospitalDTO();
				hospitalDTO.setLatitude(placesSearchResult.geometry.location.lat);
				hospitalDTO.setLongitude(placesSearchResult.geometry.location.lng);
				hospitalDTO.setName(placesSearchResult.name);
				hospitalDTO.setSpecialities(getRandomSpecialities());
				hospitalDTO.setNumberOfAvailableBed(new Random().nextInt(4));
				hospitals.add(hospitalDTO);
			}
		} catch (ApiException | InterruptedException | IOException e) {
			e.printStackTrace();
		}

		return hospitals;
	}

	public static List<Speciality> getRandomSpecialities() {

		List<Speciality> allElements = new ArrayList<>(EnumSet.allOf(Speciality.class));
		Collections.shuffle(allElements);

		return allElements.subList(0, 20);
	}

	public List<AppointmentDTO> getAppointments() {
		logger.info("Get appointments");
		return appointmentRepository.getAppointments();
	}

}
