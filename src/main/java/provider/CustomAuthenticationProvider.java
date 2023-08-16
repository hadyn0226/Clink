package provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import service.login.LoginService;
import vo.loginLogVO.LoginLogVO;
import vo.userVO.UserVO;

public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private final LoginService loginService;
	
	
	private PasswordEncoder passwordEncoder;
	
	public CustomAuthenticationProvider(LoginService loginService, PasswordEncoder passwordEncoder) {
		this.loginService = loginService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails;
        try {
            userDetails = loginService.loadUserByUsername(username);
            
            UserVO vo = (UserVO) userDetails;
            LoginLogVO loginLogVO = loginService.getLog(vo.getUserId());
            if(loginLogVO.getFailCnt() >= 5) {
            	throw new BadCredentialsException("-2");
            }
            if(vo.getUserStatus() == 2) {
            	throw new BadCredentialsException("3");
            }
            
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("-1");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
        	loginService.increFail(username);
            throw new BadCredentialsException("-1");
        }
        
        
        
        
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationType);
	}

}
