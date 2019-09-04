package com.conichi.app.ws.ui.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.conichi.app.ws.security.LiveConstants;
import com.conichi.app.ws.service.QuoteService;
import com.conichi.app.ws.shared.dto.QuoteDto;
import com.conichi.app.ws.ui.model.response.RestLive;

@Component
public class DataBaseLoader implements ApplicationRunner {
	
	@Autowired
	private QuoteService quoteService;
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		getAllCurrency();
	}

	public void getAllCurrency() {

		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = LiveConstants.BASE_URL + LiveConstants.AllCURRENCY + "?access_key=" + LiveConstants.ACCESS_KEY;
		ResponseEntity<RestLive> response = restTemplate.getForEntity(fooResourceUrl, RestLive.class);
		HashMap<String, Double> quotes = response.getBody().getQuotes();

		List<QuoteDto> listQuota = quotes.entrySet().stream().sorted(Comparator.comparing(e -> e.getKey()))
				.map(e -> new QuoteDto(e.getKey(), e.getValue())).collect(Collectors.toList());

		List<QuoteDto> returnValue = quoteService.importQuotes(listQuota);
		
	}
}
