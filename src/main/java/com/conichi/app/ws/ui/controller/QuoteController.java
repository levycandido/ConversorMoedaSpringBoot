package com.conichi.app.ws.ui.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.Configuration;
import com.conichi.app.ws.security.LiveConstants;
import com.conichi.app.ws.service.QuoteService;
import com.conichi.app.ws.shared.dto.QuoteDto;
import com.conichi.app.ws.ui.model.response.RestLive;
import com.conichi.app.ws.ui.model.response.RestQuote;

@RestController
@RequestMapping("/api/currency")
public class QuoteController {
	
	@Autowired
	private QuoteService quoteService;

	@GetMapping(path = "/convet", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE,
			"application/hal+json" })
	public RestQuote convert(@RequestParam String origin, @RequestParam String destination,
			@RequestParam Double amount) {

		RestTemplate restTemplate = new RestTemplate();
		RestQuote returnValue = quoteService.convert(origin, destination, amount);

		ApiClient defaultClient = Configuration.getDefaultApiClient();

		return returnValue;
	}

}
