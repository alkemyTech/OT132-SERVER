
package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.OrganizationDTO;
import com.alkemy.ong.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("organization")
public class OrganizationController {

	@Autowired
	OrganizationService service;
	
	@GetMapping("/public")
	public ResponseEntity<OrganizationDTO> publicData() {
		OrganizationDTO dto = service.find(0);
		return ResponseEntity.ok().body(dto);
	}
}
