package com.conichi.app.ws.service;


import java.util.List;

import com.conichi.app.ws.shared.dto.QuoteDto;
import com.conichi.app.ws.ui.model.response.RestQuote;



public interface QuoteService  {
	List<QuoteDto> importQuotes(List<QuoteDto> listQuota);
	RestQuote convert(String origin, String destination, Double amount);
    
}
