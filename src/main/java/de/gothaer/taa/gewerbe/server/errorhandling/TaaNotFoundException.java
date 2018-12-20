package de.gothaer.taa.gewerbe.server.errorhandling;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class TaaNotFoundException
    implements ExceptionMapper<NotFoundException> {

  public Response toResponse(NotFoundException ex) {
    return Response.status(ex.getResponse()
                             .getStatus())
                   // change TAA constructor
                   .entity(new TaaErrorMessage(ex))
                   .type(MediaType.APPLICATION_JSON) //this has to be set to get the generated JSON
                   .build();
  }

}
