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
            // ���� ���� ���� ���� ó�� ������ �߰��ϼ���.
            // ���� ���, �ߺ� �α��� ���� ó���� ����ڿ��� �ȳ� �޽����� �����ִ� ���� �۾��� ������ �� �ֽ��ϴ�.
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
