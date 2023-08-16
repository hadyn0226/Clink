package sessionRegistry;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;

import vo.userVO.UserVO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomSessionRegistry extends SessionRegistryImpl {

	private static final Logger logger = LoggerFactory.getLogger(CustomSessionRegistry.class);

	@Override
	public void registerNewSession(String sessionId, Object principal) {
		List<?> principals = getAllPrincipals();
		for(Object prinObject : principals) {
			System.out.println(((UserVO)prinObject).getUserEmail());
			if(((UserVO)prinObject).getUserEmail().equals(((UserVO)principal).getUserEmail())){
				
				List<SessionInformation> sessionInformationList = getAllSessions(prinObject, false);
				for (int i = 0; i < sessionInformationList.size(); i++) {
					sessionInformationList.get(i).expireNow();
					System.out.println(sessionInformationList.get(i).isExpired());
				}
			}
			
		}
		super.registerNewSession(sessionId, principal);
	}
}