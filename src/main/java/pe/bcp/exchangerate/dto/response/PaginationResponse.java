package pe.bcp.exchangerate.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.bcp.exchangerate.application.domain.Pagination;

@Getter
@Setter
@NoArgsConstructor
public class PaginationResponse<T> 
{
    public PaginationResponse(Pagination pagination, List<T> items)
    {
        this.pagination = pagination;
        this.items = items;
    }

    private Pagination pagination;
    private List<T> items;
}
