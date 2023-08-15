package sessionRegistry;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomSessionRegistry extends SessionRegistryImpl {

	private static final Logger logger = LoggerFactory.getLogger(CustomSessionRegistry.class);

	@Override
	public void registerNewSession(String sessionId, Object principal) {
		System.out.println("principal = " + principal);
		System.out.println("existingSession = " + getAllSessions(principal, false));
		System.out.println(getAllPrincipals());
		for (SessionInformation existingSession : getAllSessions(principal, false)) {
			if (existingSession.getPrincipal().equals(principal)) {
				UserDetails userDetails = (UserDetails) existingSession.getPrincipal();
				logger.info("Logging out user: " + userDetails.getUsername() + " due to duplicate login.");
				existingSession.expireNow();
				throw new IllegalArgumentException("User is already logged in");
			}
		}
		super.registerNewSession(sessionId, principal);
	}
}