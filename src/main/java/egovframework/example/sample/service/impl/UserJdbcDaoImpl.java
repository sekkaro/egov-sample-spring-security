package egovframework.example.sample.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import egovframework.example.sample.service.UserVO;

public class UserJdbcDaoImpl extends JdbcDaoImpl {

	@Override
	protected List<UserDetails> loadUsersByUsername(String username) {
		return getJdbcTemplate().query(getUsersByUsernameQuery(), new String[] { username },
				new RowMapper<UserDetails>() {

					@Override
					public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
						String userId = rs.getString(1);
						String password = rs.getString(2);
						String useYn = rs.getString(3);

						System.out.println("userId: " + userId + ", password: " + password + ", useYn: " + useYn);

						return new UserVO(username, password, useYn, true, true, true, AuthorityUtils.NO_AUTHORITIES);
					}

				});
	}

	@Override
	protected List<GrantedAuthority> loadUserAuthorities(String username) {
		return getJdbcTemplate().query(getAuthoritiesByUsernameQuery(), new String[] { username },
				new RowMapper<GrantedAuthority>() {

					@Override
					public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
						String roleName = getRolePrefix() + rs.getString(2);
						System.out.println("roleName: " + roleName);

						return new SimpleGrantedAuthority(roleName);
					}

				});
	}

}
