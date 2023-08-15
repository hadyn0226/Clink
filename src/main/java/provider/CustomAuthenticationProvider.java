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
            System.out.println(vo);
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        
        
        try {
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } catch (SessionAuthenticationException e) {
            // 동시 세션 제어 예외 처리 로직을 추가하세요.
            // 예를 들어, 중복 로그인 방지 처리나 사용자에게 안내 메시지를 보여주는 등의 작업을 수행할 수 있습니다.
            throw new AuthenticationException("Concurrent session control exception") {
                // Custom exception handling logic here
            };
        }
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationType);
	}

}
