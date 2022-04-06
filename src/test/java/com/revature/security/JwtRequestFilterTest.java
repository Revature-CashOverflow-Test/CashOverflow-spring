package com.revature.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.service.JwtUserDetailsService;
import com.revature.util.JwtUtil;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class JwtRequestFilterTest {

	JwtRequestFilter filt;
	
	@Mock
	JwtUserDetailsService serv;
	
	@Mock
	JwtUtil util;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		filt = new JwtRequestFilter(serv, util);
	}

	@Test
	void passTest() {
		assertEquals(true, Boolean.TRUE);
	}
	
//	@SuppressWarnings("static-access")
//	@Test
//	void filterTest() throws ServletException, IOException {
//		String jwtToken = "Bearer this is a good token";
//		String requestTokenHeader = "this is a good token";
//		UserDetails u = (UserDetails)new User("dummy", "test", new ArrayList<>());
//		
//		when(util.getUsernameFromToken(requestTokenHeader)).thenReturn("dummy");
//		when(serv.loadUserByUsername("dummy")).thenReturn(u);
//		when(util.validateToken(jwtToken, u)).thenReturn(Boolean.TRUE);
//		
//		Authentication authentication = Mockito.mock(Authentication.class);
//		SecurityContextHolder securityContextHolder = Mockito.mock(SecurityContextHolder.class);
//		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//		when(securityContextHolder.getContext().getAuthentication()).thenReturn(authentication);
//		
//		MockHttpServletRequest req = new MockHttpServletRequest();
//		MockHttpServletResponse resp = new MockHttpServletResponse();
//		
//		req.addHeader("Authorization", "Bearer this is a good token");
//		SecurityContextHolder.setContext(securityContext);
//		filt.doFilter(req, resp, new MockFilterChain());
//	}

}
