package de.gothaer.taa.gewerbe.server.errorhandling;

import de.gothaer.taa.gewerbe.server.filters.TaaApplicationConstants;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.io.PrintWriter;
import java.io.StringWriter;

public class TaaGenericExceptionMapper
    implements ExceptionMapper<Throwable> {

  public Response toResponse(Throwable ex) {
    TaaErrorMessage errorMessage = new TaaErrorMessage();
    setHttpStatus(ex,
                  errorMessage);
    errorMessage.setCode(TaaApplicationConstants.GENERIC_APP_ERROR_CODE);
    errorMessage.setMessage(ex.getMessage());
    StringWriter errorStackTrace = new StringWriter();
    ex.printStackTrace(new PrintWriter(errorStackTrace));
    errorMessage.setDeveloperMessage(errorStackTrace.toString());
    errorMessage.setLink(TaaApplicationConstants.BLOG_POST_URL);

    return Response.status(errorMessage.getStatus())
                   .entity(errorMessage)
                   .type(MediaType.APPLICATION_JSON)
                   .build();
  }

  private void setHttpStatus(Throwable ex,
                             TaaErrorMessage errorMessage) {
    if (ex instanceof WebApplicationException) { //NICE way to combine both of methods, say it in the blog
      errorMessage.setStatus(((WebApplicationException) ex).getResponse()
                                                           .getStatus());
    } else {
      errorMessage.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()); //defaults to internal server error 500
    }
  }

}