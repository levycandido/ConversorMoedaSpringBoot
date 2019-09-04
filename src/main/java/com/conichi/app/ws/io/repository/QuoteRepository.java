package com.conichi.app.ws.io.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.conichi.app.ws.io.entity.QuoteEntity;

@Component
public interface QuoteRepository extends PagingAndSortingRepository	<QuoteEntity, Long> {
	QuoteEntity findByCurrency(String currency);


}
	