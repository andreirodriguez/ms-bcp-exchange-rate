package pe.bcp.exchangerate.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.bcp.exchangerate.application.domain.Currency;
import pe.bcp.exchangerate.application.mapper.CurrencyMapper;
import pe.bcp.exchangerate.dto.response.CurrencyResponse;
import pe.bcp.exchangerate.service.interfaces.CurrencyService;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
	@Autowired
	private CurrencyService currencyService;

	@GetMapping("/{id}")
	public ResponseEntity<CurrencyResponse> getSelect(@PathVariable int id) {
		Currency o = currencyService.getSelect(id);

		if(o==null) return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

		return new ResponseEntity<CurrencyResponse>(CurrencyMapper.ToCurrencyResponse(o),HttpStatus.OK);
	}
}
