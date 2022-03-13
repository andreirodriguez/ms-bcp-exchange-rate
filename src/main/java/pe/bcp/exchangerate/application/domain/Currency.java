package pe.bcp.exchangerate.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
	private int id;
	private String title;
	private String symbol;
	private Boolean active;
}
