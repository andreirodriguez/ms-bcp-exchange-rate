package pe.bcp.exchangerate.repository.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import pe.bcp.exchangerate.application.domain.Pagination;
import pe.bcp.exchangerate.repository.interfaces.SQLHelper;
import pe.bcp.exchangerate.application.domain.CurrencyExchange;
import pe.bcp.exchangerate.repository.interfaces.CurrencyExchangeRepository;

@Repository
public class CurrencyExchangeRepositoryImp implements CurrencyExchangeRepository {

	@Autowired
	private SQLHelper sqlHelper;

	@Override
	public CurrencyExchange getSearch(Map<String,Object> parametersJson) {
		CurrencyExchange o = sqlHelper.getSearch("dbo.currency_exchange_search", parametersJson, new CurrencyExchangeSearchRowMapper());

		return o;
	}

	@Override
	public List<CurrencyExchange> getSearch(Map<String,Object> parametersJson, Pagination pagination) {
		List<CurrencyExchange> l = sqlHelper.getSearch("dbo.currency_exchange_search", parametersJson, pagination, new CurrencyExchangeSearchRowMapper());

		return l;
	}

	static class CurrencyExchangeSearchRowMapper implements RowMapper<CurrencyExchange> {
		@Override
		public CurrencyExchange mapRow(ResultSet r, int row) throws SQLException {
			CurrencyExchange o = new CurrencyExchange();

			o.setId(r.getInt("id"));
			o.setCurrencyOriginId(r.getInt("currency_origin_id"));
			o.setCurrencyOriginTitle(r.getString("currency_origin_title"));
			o.setCurrencyOriginSymbol(r.getString("currency_origin_symbol"));			
			o.setCurrencyExchangeId(r.getInt("currency_exchange_id"));
			o.setCurrencyExchangeTitle(r.getString("currency_exchange_title"));
			o.setCurrencyExchangeSymbol(r.getString("currency_exchange_symbol"));			
			o.setMathematicalOperator(r.getString("mathematical_operator"));
			o.setRateExchange(r.getDouble("rate_exchange"));
			o.setActive(r.getBoolean("active"));

			return o;
		}
	}

	@Override
	public List<CurrencyExchange> getFindAll(Map<String,Object> parametersJson, Pagination pagination) {
		List<CurrencyExchange> l = sqlHelper.getFindAll("dbo.currency_exchange_find_all", parametersJson, pagination, new CurrencyExchangeFindAllRowMapper());

		return l;
	}

	static class CurrencyExchangeFindAllRowMapper implements RowMapper<CurrencyExchange> {
		@Override
		public CurrencyExchange mapRow(ResultSet r, int row) throws SQLException {
			CurrencyExchange o = new CurrencyExchange();

			o.setId(r.getInt("id"));
			o.setCurrencyOriginId(r.getInt("currency_origin_id"));
			o.setCurrencyExchangeId(r.getInt("currency_exchange_id"));
			o.setMathematicalOperator(r.getString("mathematical_operator"));
			o.setRateExchange(r.getDouble("rate_exchange"));
			o.setActive(r.getBoolean("active"));

			return o;
		}
	}

	@Override
	public void setInsertUpdate(CurrencyExchange o) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("id", o.getId());
		parameters.put("currency_origin_id", o.getCurrencyOriginId());
		parameters.put("currency_exchange_id", o.getCurrencyExchangeId());
		parameters.put("mathematical_operator", o.getMathematicalOperator());
		parameters.put("rate_exchange", o.getRateExchange());
		parameters.put("active", o.getActive());

		int id = sqlHelper.setInsertUpdate("dbo.currency_exchange_insert_update", parameters);

		o.setId(id);
	}

}