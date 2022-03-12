package pe.bcp.exchangerate.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
	
	public Pagination(String sort)
	{
		this.sort = (sort == null) ? "" : sort;
	}
	
    public Pagination(int pageIndex, int pageSize, String sort)
    {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.sort = (sort == null) ? "" : sort;
    }	
	
	private int pageIndex;
	private int pageSize;
	private int pageCount;
	private String sort;
	private int total = 0;
	
	public void setTotal(int total)
	{
        if (this.pageSize > 0)
            this.pageCount = (total / this.pageSize + ((total % this.pageSize == 0) ? 0 : 1));
        else
            this.pageCount = 1;
		
		this.total = total;
	}
}
