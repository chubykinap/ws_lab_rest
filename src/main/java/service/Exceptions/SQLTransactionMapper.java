package service.Exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SQLTransactionMapper implements ExceptionMapper<SQLTransactionException> {
    @Override
    public Response toResponse(SQLTransactionException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
