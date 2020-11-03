package security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.UserEntity;


@Service
public class JwtUserDetailsService implements UserDetailsService  {

	private static final String ROLE_PREFIX = "ROLE_";

	public UserDetails loadUserByUsername(String username){

		UserEntity user = new UserEntity();
		user.setUserName("user");
		String username1=user.getUserName();
		if (username1 == null) {
			throw new UsernameNotFoundException("User not found with username: " + username1);
		}
		//auditlogService.fileAuditlogService(new AuditlogDetails(user.getUserId(),null, FileAuditLog.LOGINUSER));
		return new org.springframework.security.core.userdetails.User("user", user.getPassword(),true, true, true, user.getAccountNonLocked(),
				getAuthorities(user.getUserName()));
		
	}


	private Collection<? extends GrantedAuthority> getAuthorities(String username) {
		
		Optional<UserEntity> optional = Optional.empty();//userProfileRepository.findById(userId);
		if (optional.isPresent()) {
			UserEntity userProfileEntity = optional.get();
			return getGrantedAuthoritiesIncludedRole(userProfileEntity.getUserName());
		}
		return new ArrayList<>();
	}


	private Collection<? extends GrantedAuthority> getGrantedAuthoritiesIncludedRole(String userName) {
		// TODO Auto-generated method stub
		return null;
	}


	


}
