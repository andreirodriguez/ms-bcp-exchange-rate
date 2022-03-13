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
import pe.bcp.exchangerate.application.domain.ExchangeRate;
import pe.bcp.exchangerate.repository.interfaces.ExchangeRateRepository;

@Repository
public class ExchangeRateRepositoryImp implements ExchangeRateRepository {

	@Autowired
	private SQLHelper sqlHelper;

	@Override
	public ExchangeRate getSearch(Map<String,Object> parametersJson) {
		ExchangeRate o = sqlHelper.getSearch("dbo.exchange_rate_search", parametersJson, new ExchangeRateSearchRowMapper());

		return o;
	}

	@Override
	public List<ExchangeRate> getSearch(Map<String,Object> parametersJson, Pagination pagination) {
		List<ExchangeRate> l = sqlHelper.getSearch("dbo.exchange_rate_search", parametersJson, pagination, new ExchangeRateSearchRowMapper());

		return l;
	}

	static class ExchangeRateSearchRowMapper implements RowMapper<ExchangeRate> {
		@Override
		public ExchangeRate mapRow(ResultSet r, int row) throws SQLException {
			ExchangeRate o = new ExchangeRate();

			o.setId(r.getInt("id"));
			o.setAmountOrigin(r.getDouble("amount_origin"));
			o.setAmountExchange(r.getDouble("amount_exchange"));
			o.setRateExchange(r.getDouble("rate_exchange"));
			o.setCurrencyOriginId(r.getInt("currency_origin_id"));
			o.setCurrencyOriginTitle(r.getString("currency_origin_title"));
			o.setCurrencyOriginSymbol(r.getString("currency_origin_symbol"));			
			o.setCurrencyExchangeId(r.getInt("currency_exchange_id"));
			o.setCurrencyExchangeTitle(r.getString("currency_exchange_title"));
			o.setCurrencyExchangeSymbol(r.getString("currency_exchange_symbol"));
			o.setRegisterUserId(r.getInt("register_user_id"));
			o.setRegisterUserFullname(r.getString("register_user_fullname"));
			o.setRegisterDatetime(r.getTimestamp("register_datetime"));
			o.setActive(r.getBoolean("active"));

			return o;
		}
	}

	@Override
	public List<ExchangeRate> getFindAll(Map<String,Object> parametersJson, Pagination pagination) {
		List<ExchangeRate> l = sqlHelper.getFindAll("dbo.exchange_rate_find_all", parametersJson, pagination, new ExchangeRateFindAllRowMapper());

		return l;
	}

	static class ExchangeRateFindAllRowMapper implements RowMapper<ExchangeRate> {
		@Override
		public ExchangeRate mapRow(ResultSet r, int row) throws SQLException {
			ExchangeRate o = new ExchangeRate();

			o.setId(r.getInt("id"));
			o.setAmountOrigin(r.getDouble("amount_origin"));
			o.setAmountExchange(r.getDouble("amount_exchange"));
			o.setRateExchange(r.getDouble("rate_exchange"));
			o.setCurrencyOriginId(r.getInt("currency_origin_id"));
			o.setCurrencyOriginTitle(r.getString("currency_origin_title"));
			o.setCurrencyOriginSymbol(r.getString("currency_origin_symbol"));			
			o.setCurrencyExchangeId(r.getInt("currency_exchange_id"));
			o.setCurrencyExchangeTitle(r.getString("currency_exchange_title"));
			o.setCurrencyExchangeSymbol(r.getString("currency_exchange_symbol"));
			o.setRegisterUserId(r.getInt("register_user_id"));
			o.setRegisterUserFullname(r.getString("register_user_fullname"));
			o.setRegisterDatetime(r.getTimestamp("register_datetime"));
			o.setActive(r.getBoolean("active"));

			return o;
		}
	}

	@Override
	public void setInsertUpdate(ExchangeRate o) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("id", o.getId());
		parameters.put("amount_origin", o.getAmountOrigin());
		parameters.put("amount_exchange", o.getAmountExchange());
		parameters.put("rate_exchange", o.getRateExchange());
		parameters.put("currency_origin_id", o.getCurrencyOriginId());
		parameters.put("currency_exchange_id", o.getCurrencyExchangeId());
		parameters.put("register_user_id", o.getRegisterUserId());
		parameters.put("register_user_fullname", o.getRegisterUserFullname());
		parameters.put("register_datetime", o.getRegisterDatetime());
		parameters.put("active", o.getActive());

		int id = sqlHelper.setInsertUpdate("dbo.exchange_rate_insert_update", parameters);

		o.setId(id);
	}

}