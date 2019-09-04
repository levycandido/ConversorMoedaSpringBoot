package com.conichi.app.ws.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.conichi.app.ws.exception.CurrencyNotFoundException;
import com.conichi.app.ws.io.entity.QuoteEntity;
import com.conichi.app.ws.io.repository.QuoteRepository;
import com.conichi.app.ws.shared.dto.QuoteDto;
import com.conichi.app.ws.ui.model.response.RestQuote;

class QuoteImplTest {

	@InjectMocks
	QuoteServiceImpl quoteService;

	@Mock
	QuoteRepository quoteRepository;

	QuoteEntity quoteEntity;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	void testImporteQuotes() {
		List<QuoteDto> listQuota = new ArrayList<>();
		QuoteDto quoteDtoFirst = new QuoteDto("USDEUR", 0.88964);
		QuoteDto quoteDtoSec = new QuoteDto("USDBRL", 4.08735);
		listQuota.add(quoteDtoFirst);
		listQuota.add(quoteDtoSec);
		List<QuoteDto> returnValue = quoteService.importQuotes(listQuota);
		assertTrue(returnValue.equals(listQuota));
	}

	@Test
	final void testConvert() {

		RestQuote returnValue;
		String origin = "EUR";
		String destination = "BRL";
		Double amount = 10D;
		String basecurrency = "USD";

		QuoteEntity quoteEntityOrigin = new QuoteEntity();
		quoteEntityOrigin.setCurrency("USDEUR");
		quoteEntityOrigin.setValue(0.88964);

		QuoteEntity quoteEntityDest = new QuoteEntity();
		quoteEntityDest.setCurrency("USDBRL");
		quoteEntityDest.setValue(4.08735);

		when(quoteRepository.findByCurrency(basecurrency + origin)).thenReturn(quoteEntityOrigin);
		when(quoteRepository.findByCurrency(basecurrency + destination)).thenReturn(quoteEntityDest);

		Double amountDest = amount * (quoteEntityDest.getValue() / quoteEntityOrigin.getValue());

		returnValue = quoteService.convert(origin, destination, amount);

		assertNotNull(returnValue);
		assertTrue(returnValue.getValue().equals(amountDest));
	}

	@Test
	final void testConvert_CurrencyNotFoundException() {
		when(quoteRepository.findByCurrency(anyString())).thenReturn(null);

		RestQuote returnValue;
		String origin = "EUR";
		String destination = "BRL";
		Double amount = 10D;

		String basecurrency = "USD";

		assertThrows(CurrencyNotFoundException.class,

				() -> {
					quoteService.convert(origin, destination, amount);
				}

		);
	}

}
