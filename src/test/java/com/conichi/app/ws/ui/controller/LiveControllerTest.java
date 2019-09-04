package com.conichi.app.ws.ui.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.conichi.app.ws.io.entity.QuoteEntity;
import com.conichi.app.ws.service.impl.QuoteServiceImpl;
import com.conichi.app.ws.ui.model.response.RestQuote;

class LiveControllerTest {
	
	@InjectMocks
	QuoteController quoteController;
	
	@Mock
	QuoteServiceImpl QuoteService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testConvert() {
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

		Double amountDest = amount * (quoteEntityDest.getValue() / quoteEntityOrigin.getValue());

		RestQuote returnValue = new RestQuote();
		returnValue.setCurrency(basecurrency + destination);
		returnValue.setValue(amountDest);
				
		when(QuoteService.findCurrency(basecurrency + origin)).thenReturn(quoteEntityOrigin);
		when(QuoteService.findCurrency(basecurrency + destination)).thenReturn(quoteEntityDest);
		when(QuoteService.convert(anyString(), anyString(), anyDouble())).thenReturn(returnValue);
		
		returnValue = QuoteService.convert(origin, destination, amount);

		assertNotNull(returnValue);
		assertTrue(returnValue.getValue().equals(amountDest));
		assertEquals(quoteEntityDest.getCurrency(), returnValue.getCurrency());

	}

}
