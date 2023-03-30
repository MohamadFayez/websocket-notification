package com.mdi.websocket.util;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class ValidationUtils {

	public boolean doesStringContainsAny(String str, List<String> values) {

		Optional<String> contained =
				values.parallelStream().filter(e -> str.contains(e)).findAny();

		if(contained.isPresent())
			return true;

		return false;
	}
}
