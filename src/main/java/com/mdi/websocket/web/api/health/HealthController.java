package com.mdi.websocket.web.api.health;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
public class HealthController {

	@Operation(hidden = true)
	@Tag(name = "health", description = "This API to check the microservice health.")
	@GetMapping(value = {
			"${app.config.integration.common.health-base-url}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> imUpAndRunning() {

		return ResponseEntity.ok("UP");
	}

}
