package pe.bcp.exchangerate.repository.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import pe.bcp.exchangerate.application.domain.Pagination;

public interface SQLHelper {
	<T> T getSearch(String procedure, Map<String, Object> parametersJson,RowMapper<?> rowMapper);
	
	<T> List<T> getSearch(String procedure, Map<String, Object> parametersJson, Pagination pagination,RowMapper<?> rowMapper);
	
	<T> List<T> getFindAll(String procedure, Map<String, Object> parametersJson, Pagination pagination,RowMapper<?> rowMapper);
	
	int setInsertUpdate(String procedure, Map<String, Object> parameters);
	
	int setExecute(String procedure, Map<String, Object> parameters);
	
	<T> int setInsertUpdateMassive(String procedure, List<T> list);
}
