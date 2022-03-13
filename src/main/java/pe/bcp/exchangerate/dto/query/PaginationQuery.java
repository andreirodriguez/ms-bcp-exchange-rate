package pe.bcp.exchangerate.dto.query;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.bcp.exchangerate.application.domain.Pagination;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationQuery {
	private int pageIndex;
	private int pageSize;
	private String sort;	
	private Pagination pagination;
	
	public Pagination getPagination()
	{
        if (this.pagination == null)
        {
            if (this.pageIndex > 0 && this.pageSize > 0)
                this.pagination = new Pagination(this.pageIndex, this.pageSize, this.sort);
            else
                this.pagination = new Pagination(this.sort);
        }

        return this.pagination;
	}
	
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }		
}
