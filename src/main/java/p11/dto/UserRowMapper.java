package p11.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserRowMapper implements RowMapper<UserDetails> {

	@Override
	public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		return User.withUsername(rs.getString("username")).password(rs.getString("password")).roles("USER")
				.disabled(!rs.getBoolean("enabled")).build();
	}

}
