package p11.authentication;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import p11.repositories.UserDetailsRepository;

/**
 * JwtFilter is a component that performs JWT (JSON Web Token) authentication by
 * intercepting and processing incoming HTTP requests. It extends
 * OncePerRequestFilter to ensure that it is invoked exactly once per request.
 * 
 * <p>
 * This filter checks the Authorization header of the incoming request for a
 * valid JWT. If a valid JWT is present, it extracts the user details from the
 * token, validates it, and sets up the Spring Security context for the
 * authenticated user.
 * </p>
 * 
 * 
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private JwtUtil jwtUtil;

	/**
	 * Performs the actual JWT authentication for each incoming HTTP request.
	 * 
	 * @param request     the incoming HTTP request
	 * @param response    the HTTP response
	 * @param filterChain the filter chain for further processing
	 * @throws ServletException if an exception occurs during processing
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (!StringUtils.hasText(header) || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		final String token = header.split(" ")[1].trim();
		UserDetails userDetails = userDetailsRepository.getUserByName(jwtUtil.extractUsername(token));

		if (!jwtUtil.validateToken(token, userDetails)) {
			filterChain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());

		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);

	}

}
