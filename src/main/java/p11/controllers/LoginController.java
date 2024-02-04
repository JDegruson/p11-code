package p11.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import p11.authentication.JwtUtil;
import p11.dto.AuthCredentialsRequest;
import p11.services.LoginService;

/**
 * LoginController is a Spring MVC RestController responsible for handling
 * authentication-related HTTP requests. It defines an endpoint for user login
 * using authentication credentials.
 * 
 * <p>
 * This controller uses the {@link LoginService} for user authentication and
 * issues JSON Web Tokens (JWTs) using the {@link JwtUtil}.
 * </p>
 * 
 * <p>
 * The endpoint provided by this controller is:
 * </p>
 * <ul>
 * <li><strong>POST /login:</strong> Handles user login using the provided
 * authentication credentials. The request body should contain an
 * {@link AuthCredentialsRequest} representing the user credentials.</li>
 * </ul>
 * 
 */
@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private JwtUtil jwtUtil;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * Handles the HTTP POST request for user login using the provided
	 * authentication credentials.
	 * 
	 * @param request the authentication credentials provided in the request body
	 * @return a ResponseEntity containing the JWT in the Authorization header and
	 *         the UserDetails in the response body on successful login. Returns
	 *         UNAUTHORIZED status on failed login.
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthCredentialsRequest request) {
		logger.info("logging");
		try {
			UserDetails user = loginService.getUserFromRequest(request);
			return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtUtil.generateToken(user.getUsername()))
					.body(user);
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}