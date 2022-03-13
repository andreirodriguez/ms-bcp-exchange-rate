package pe.bcp.exchangerate.dto.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateAuditQuery extends PaginationQuery{
	private int exchangeRateId;
}
