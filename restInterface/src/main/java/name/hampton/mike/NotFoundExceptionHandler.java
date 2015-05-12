package name.hampton.mike;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<ImplementationNotFoundException>{

    @Context
    private HttpHeaders headers;

    public Response toResponse(ImplementationNotFoundException ex){
        return Response.status(404).entity(ex.getLocalizedMessage()).type( getAcceptType()).build();
    }

    private MediaType getAcceptType(){
         List<MediaType> accepts = headers.getAcceptableMediaTypes();
         if (accepts!=null && accepts.size() > 0) {
             //choose one
        	 return accepts.get(0);
         }else {
             //return a default one like Application/json
        	 return MediaType.APPLICATION_JSON_TYPE;
         }
    }
}
