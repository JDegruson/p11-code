package p11.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * HospitalService is a Spring service class responsible for finding the nearest
 * hospital based on an appointment's details. It uses the Google Maps API for
 * location-related operations.
 * 
 * <p>
 * This service class provides a method {@code findHospital} that takes an
 * {@link AppointmentDTO} and returns the closest hospital based on its
 * specifications.
 * </p>
 * 
 * <p>
 * The service class is initialized with the Google Maps API key, which is
 * injected using the {@code @Value} annotation.
 * </p>
 * 
 * 
 */
@Service
public class HospitalService {

	private static final Logger logger = LoggerFactory.getLogger(HospitalService.class);

	@Value("${google.api.key}")
	private String apiKey;

	public HospitalService(@Value("${google.api.key}") String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * Finds the nearest hospital based on the details of the provided appointment.
	 * 
	 * @param appointmentDTO the appointment details
	 * @return the closest hospital as an instance of {@link HospitalDTO}
	 */
	public HospitalDTO findHospital(AppointmentDTO appointmentDTO) {
		GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
		LatLng originLocation = new LatLng(appointmentDTO.getLatitude(), appointmentDTO.getLongitude());

		List<HospitalDTO> hospitals = getNearbyHospitals(context, originLocation);

		return getClosestHospital(appointmentDTO, context, originLocation, hospitals);
	}

	/**
	 * Finds the closest hospital based on the details of the provided appointment.
	 * 
	 * @param appointmentDTO the appointment details
	 * @param context        the GeoApiContext for Google Maps API
	 * @param originLocation the origin location as LatLng
	 * @param hospitals      a list of nearby hospitals
	 * @return the closest hospital as an instance of {@link HospitalDTO}, or null
	 *         if no hospital meets the criteria
	 */
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

	/**
	 * Gets a list of nearby hospitals based on the provided GeoApiContext and
	 * origin location.
	 * 
	 * @param context        the GeoApiContext for Google Maps API
	 * @param originLocation the origin location as LatLng
	 * @return a list of nearby hospitals as instances of {@link HospitalDTO}
	 */
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
			logger.info(e.getMessage());
		}

		return hospitals;
	}

	private List<Speciality> getRandomSpecialities() {

		List<Speciality> allElements = new ArrayList<>(EnumSet.allOf(Speciality.class));
		Collections.shuffle(allElements);

		return allElements.subList(0, 20);
	}
}
