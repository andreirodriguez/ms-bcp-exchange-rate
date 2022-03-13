package pe.bcp.exchangerate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyResponse {
	private int id;
	private String title;
	private String symbol;
	private Boolean active;
}
