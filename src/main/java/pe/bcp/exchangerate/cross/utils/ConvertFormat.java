package pe.bcp.exchangerate.cross.utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pe.bcp.exchangerate.cross.variables.Formats;

public class ConvertFormat 
{
    public static Boolean IsNullOrEmpty(Object item)
    {   
        if(item == null) return true;
        
        String o = item.toString().trim();
        
        if(o.isEmpty()) return true;
        
        return false;
    }
    
    public static Object IntegerToNull(int item)
    {   
    	if(ConvertFormat.IsNullOrEmpty(item)) return null;
    	
    	if(item <= 0) return null;
    	
        return item;
    }    
    
	public static String DoubleToString(Double value) 
    {
		return ConvertFormat.DoubleToString(value,Formats.Double_Separator_2Decimals);
    }   	
    
	public static String DoubleToString(Double value,String format) 
    {
		if(ConvertFormat.IsNullOrEmpty(value)) return "";
		
		return String.format(format, value);
    }  
	
	public static Boolean StringToBoolean(String value) 
    {
		if(ConvertFormat.IsNullOrEmpty(value)) return false;
		
		return (value.equals("1"));
    }  	
    
	public static String getLowerCamelCase(String text) 
    {
    	text = text.trim();
    	
		if(text=="") return "";
		
		text = text.substring(0,1).toLowerCase() + text.substring(1);
		
		return text;
    }   	
	
	public static <T> String ListToJson(List<T> list)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String json = gson.toJson(list); 		
		
		return json;
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) 
	{		  
	    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
	    
	    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
	}	
	
     
}
