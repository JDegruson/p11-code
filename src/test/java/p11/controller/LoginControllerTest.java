package p11.controller;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import p11.authentication.JwtUtil;
import p11.controllers.LoginController;
import p11.dto.AuthCredentialsRequest;
import p11.services.LoginService;

public class LoginControllerTest {

	@Mock
	private LoginService loginService;

	@Mock
	private JwtUtil jwtUtil;

	@InjectMocks
	private LoginController loginController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void loginTest() {
		ArgumentCaptor<AuthCredentialsRequest> captor = ArgumentCaptor.forClass(AuthCredentialsRequest.class);
		UserDetails user = new User("username", "password", asList());
		AuthCredentialsRequest request = new AuthCredentialsRequest();
		request.setUsername("username");
		when(loginService.getUserFromRequest(request)).thenReturn(user);

		loginController.login(request);

		verify(loginService).getUserFromRequest(captor.capture());

		AuthCredentialsRequest value = captor.getValue();

		MatcherAssert.assertThat(value.getUsername(), Matchers.equalTo("username"));
	}

}
