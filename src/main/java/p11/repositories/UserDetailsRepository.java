package p11.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import p11.dto.UserRowMapper;

@Repository
public class UserDetailsRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public UserDetailsRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<UserDetails> allUsers() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM users");
		return jdbcTemplate.query(sql.toString(), new UserRowMapper());
	}

	public UserDetails getUserByName(String username) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM users where username = :username");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("username", username);
		return jdbcTemplate.queryForObject(sql.toString(), paramMap, new UserRowMapper());
	}
}