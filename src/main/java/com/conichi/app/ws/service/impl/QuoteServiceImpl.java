package com.conichi.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.conichi.app.ws.exception.CurrencyNotFoundException;
import com.conichi.app.ws.io.entity.QuoteEntity;
import com.conichi.app.ws.io.repository.QuoteRepository;
import com.conichi.app.ws.service.QuoteService;
import com.conichi.app.ws.shared.dto.QuoteDto;
import com.conichi.app.ws.ui.model.response.RestQuote;

@Service
public class QuoteServiceImpl implements QuoteService {

	@Autowired(required = true)
	QuoteRepository quoteRepository;

	@Override
	public List<QuoteDto> importQuotes(List<QuoteDto> listQuota) {
		List<QuoteDto> returnValue = new ArrayList<>();

		// Before import all currencies, delete data base data to avoid duplicat values
		deleteAllCurrency();

		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		for (QuoteDto quoteDto : listQuota) {
			QuoteEntity quoteEntity = modelmapper.map(quoteDto, QuoteEntity.class);
			QuoteEntity storedQuoteDetails = quoteRepository.save(quoteEntity);
			returnValue.add(quoteDto);
		}
		return returnValue;

	}

	private void deleteAllCurrency() {
		quoteRepository.deleteAll();
	}

	@Override
	public RestQuote convert(String origin, String destination, Double amount) {

		RestQuote returnValue = new RestQuote();
		String basecurrency = "USD";

		QuoteEntity quoteOrigin = findCurrency(basecurrency + origin);

		QuoteEntity quoteDestination = findCurrency(basecurrency + destination);
		quoteRepository.findByCurrency(basecurrency + origin);
		Double convertedAmount = convertCurrent(amount, quoteDestination.getValue(), quoteOrigin.getValue());
		returnValue.setCurrency(destination);
		returnValue.setValue(convertedAmount);

		return returnValue;
	}

	public QuoteEntity findCurrency(String currency) {
		QuoteEntity returnValue = quoteRepository.findByCurrency(currency);
		if (returnValue == null) {
			throw new CurrencyNotFoundException("Currency not found");
		}
		return returnValue;
	}

	private Double convertCurrent(Double amount, Double Dest, Double origin) {
		return amount * (Dest / origin);
	}

}
