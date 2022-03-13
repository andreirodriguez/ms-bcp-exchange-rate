package pe.bcp.exchangerate.repository.implementation;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import pe.bcp.exchangerate.application.domain.Pagination;
import pe.bcp.exchangerate.repository.interfaces.SQLHelper;

@Repository
public class SQLHelperImp implements SQLHelper {

	@Autowired
	private JdbcTemplate jdbcTemplate;	
	
	@Override
	public <T> T getSearch(String procedure, Map<String, Object> parametersJson, RowMapper<?> rowMapper) {
		Pagination pagination = new Pagination("");
		
		List<T> l = this.getSearch(procedure, parametersJson, pagination, rowMapper);
		
		if(l.size()>0) return l.get(0);
		
		return null;
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getSearch(String procedure, Map<String, Object> parametersJson, Pagination pagination,RowMapper<?> rowMapper) {
		SimpleJdbcCall jdbc = this.getSimpleJdbc(procedure, rowMapper);
		
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("parameters_json", this.getParametersToJson(parametersJson));
		parameters.put("order_by", this.getOrderByWhithoutInjection(pagination.getSort()));
		
		return ((List<T>)jdbc.execute(parameters).get("items"));	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getFindAll(String procedure, Map<String, Object> parametersJson,Pagination pagination, RowMapper<?> rowMapper) {
		SimpleJdbcCall jdbc = this.getSimpleJdbc(procedure, rowMapper);
		
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("parameters_json", this.getParametersToJson(parametersJson));
		parameters.put("page_active", pagination.getPageIndex());
		parameters.put("show_quantity", pagination.getPageSize());
		parameters.put("order_by", this.getOrderByWhithoutInjection(pagination.getSort()));
		
		Map<String, Object> result = jdbc.execute(parameters);
		
		pagination.setTotal((int)result.get("total_registers"));
		
		return ((List<T>)result.get("items"));	
	}	
	
	@Override
	public int setInsertUpdate(String procedure, Map<String, Object> parameters) {
		SimpleJdbcCall jdbc = this.getSimpleJdbc(procedure);
		
		Map<String, Object> result = jdbc.execute(parameters);
		
		return (int)result.get("id");
	}	
	
	@Override
	public int setExecute(String procedure, Map<String, Object> parameters) {
		SimpleJdbcCall jdbc = this.getSimpleJdbc(procedure);
		
		Map<String, Object> result = jdbc.execute(parameters);
		
		return (int)result.get("#update-count-1");
	}		
	
	@Override
	public <T> int setInsertUpdateMassive(String procedure, List<T> list) {
		SimpleJdbcCall jdbc = this.getSimpleJdbc(procedure);
		
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("json_data", this.getListToJson(list));
		
		Map<String, Object> result = jdbc.execute(parameters);
		
		return (int)result.get("#update-count-1");
	}	
	
	private SimpleJdbcCall getSimpleJdbc(String procedure)
	{		
		String[] schemaMethod = procedure.split("\\.");
		String schema = schemaMethod[0];
		String methodSQL = schemaMethod[1]; 
		
		SimpleJdbcCall jdbc = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withSchemaName(schema).withProcedureName(methodSQL);
		
		return jdbc;
	}	
	
	private SimpleJdbcCall getSimpleJdbc(String procedure, RowMapper<?> rowMapper)
	{		
		String[] schemaMethod = procedure.split("\\.");
		String schema = schemaMethod[0];
		String methodSQL = schemaMethod[1]; 
		
		SimpleJdbcCall jdbc = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withSchemaName(schema).withProcedureName(methodSQL).returningResultSet("items", rowMapper);
		
		return jdbc;
	}
	
	private String getParametersToJson(Map<String, Object> parameters)
	{
		Gson gson = new Gson();
	    Type gsonType = new TypeToken<HashMap<String,Object>>(){}.getType();
	    
		String json = gson.toJson(parameters,gsonType);
		
		return json;
	}
	
	private <T> String getListToJson(List<T> list)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String json = gson.toJson(list); 		
		
		return json;
	}	

	private String getOrderByWhithoutInjection(String orderBy)
	{
		if(orderBy.isEmpty()) return "";
		
		orderBy = orderBy.trim();
		
		String[] arraytext = orderBy.split(",");
		String [] keyOrderBy = null;
		orderBy = "";
		
		for(String text:arraytext)
		{
			keyOrderBy = text.split(" ");
			
			orderBy += ",[" + keyOrderBy[0] + "] " + keyOrderBy[1];  
		}
		
		return orderBy.substring(1);
	}






}
