package de.gothaer.taa.gewerbe.server.errorhandling;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TaaApplicationExceptionMapper
    implements ExceptionMapper<TaaApplicationException> {

  public Response toResponse(TaaApplicationException ex) {
    return Response.status(ex.getStatus())
                   .entity(new TaaErrorMessage(ex))
                   .type(MediaType.APPLICATION_JSON)
                   .build();
  }

}
