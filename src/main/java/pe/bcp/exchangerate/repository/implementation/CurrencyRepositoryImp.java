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
import pe.bcp.exchangerate.application.domain.Currency;
import pe.bcp.exchangerate.repository.interfaces.CurrencyRepository;

@Repository
public class CurrencyRepositoryImp implements CurrencyRepository {

	@Autowired
	private SQLHelper sqlHelper;

	@Override
	public Currency getSearch(Map<String,Object> parametersJson) {
		Currency o = sqlHelper.getSearch("dbo.currency_search", parametersJson, new CurrencySearchRowMapper());

		return o;
	}

	@Override
	public List<Currency> getSearch(Map<String,Object> parametersJson, Pagination pagination) {
		List<Currency> l = sqlHelper.getSearch("dbo.currency_search", parametersJson, pagination, new CurrencySearchRowMapper());

		return l;
	}

	static class CurrencySearchRowMapper implements RowMapper<Currency> {
		@Override
		public Currency mapRow(ResultSet r, int row) throws SQLException {
			Currency o = new Currency();

			o.setId(r.getInt("id"));
			o.setTitle(r.getString("title"));
			o.setSymbol(r.getString("symbol"));
			o.setActive(r.getBoolean("active"));

			return o;
		}
	}

	@Override
	public List<Currency> getFindAll(Map<String,Object> parametersJson, Pagination pagination) {
		List<Currency> l = sqlHelper.getFindAll("dbo.currency_find_all", parametersJson, pagination, new CurrencyFindAllRowMapper());

		return l;
	}

	static class CurrencyFindAllRowMapper implements RowMapper<Currency> {
		@Override
		public Currency mapRow(ResultSet r, int row) throws SQLException {
			Currency o = new Currency();

			o.setId(r.getInt("id"));
			o.setTitle(r.getString("title"));
			o.setSymbol(r.getString("symbol"));
			o.setActive(r.getBoolean("active"));

			return o;
		}
	}

	@Override
	public void setInsertUpdate(Currency o) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("id", o.getId());
		parameters.put("title", o.getTitle());
		parameters.put("symbol", o.getSymbol());
		parameters.put("active", o.getActive());

		int id = sqlHelper.setInsertUpdate("dbo.currency_insert_update", parameters);

		o.setId(id);
	}

}