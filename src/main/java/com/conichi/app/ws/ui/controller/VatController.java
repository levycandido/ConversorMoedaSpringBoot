package com.conichi.app.ws.ui.controller;

import java.util.logging.Logger;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudmersive.client.VatApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import com.cloudmersive.client.model.VatLookupRequest;
import com.cloudmersive.client.model.VatLookupResponse;
import com.conichi.app.ws.security.VatConstants;
import com.conichi.app.ws.service.impl.QuoteServiceImpl;
import com.conichi.app.ws.ui.model.response.RestVat;

@RestController
@RequestMapping("/api/vat")
public class VatController {

	@GetMapping(path = "/validator", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE,
			"application/hal+json" })
	public RestVat validator(@RequestParam String vatCod) {
		RestVat returnValue = null;
		ApiClient defaultClient = Configuration.getDefaultApiClient();

		// Configure API key authorization: Apikey
		ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication(VatConstants.AUTHENTICATION);
		Apikey.setApiKey(VatConstants.APIKEY);
	
		VatApi apiInstance = new VatApi();
		VatLookupRequest input = new VatLookupRequest();
		input.setVatCode(vatCod);
		try {
			VatLookupResponse result = apiInstance.vatVatLookup(input);
			returnValue = new RestVat(result.getCountryCode(), result.getVatNumber(), result.isIsValid());
			System.out.println(result);
		} catch (ApiException e) {
			System.err.println("Exception when calling VatApi#vatVatLookup");
			e.printStackTrace();
		}

		return returnValue;
	}

}
