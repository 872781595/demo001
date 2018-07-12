package cn.itcast.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
	
	public static void main(String[] args) {
		//$2a$10$KJw92gk0wdDLRjWkSNFGe.0sBsYygsJRHir1DtMCa51PsC2ROPxFK
		//$2a$10$w3nPDBahaRLRjt0CXn/19usccD11c9Pv/X0CR77yvGBpFfNWUd1Oe
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encode = passwordEncoder.encode("123456");
//		System.out.println(encode);
		boolean matches1 = passwordEncoder.matches("123456","$2a$10$KJw92gk0wdDLRjWkSNFGe.0sBsYygsJRHir1DtMCa51PsC2ROPxFK");
		boolean matches2 = passwordEncoder.matches("123456","$2a$10$w3nPDBahaRLRjt0CXn/19usccD11c9Pv/X0CR77yvGBpFfNWUd1Oe");
		System.out.println(matches1);
		System.out.println(matches2);
	}

}
