package pl.lodz.p.white.whitetestapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestRestAPIs {
	
	@GetMapping("/api/test/moderator")
	@PreAuthorize("hasRole('MODERATOR')")
	public String userAccess() {
		return ">>> User Contents!";
	}

	@GetMapping("/api/test/candidate")
	@PreAuthorize("hasRole('CANDIDATE') or hasRole('MODERATOR')")
	public String projectManagementAccess() {
		return ">>> Candidate Board";
	}

}
