package de.gothaer.taa.gewerbe.server.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

public class LoggingResponseFilter
    implements ContainerResponseFilter {

  public void filter(ContainerRequestContext requestContext,
                     ContainerResponseContext responseContext)
      throws IOException {
    String method = requestContext.getMethod();

    System.out.println("=====================================================================================================================");
    System.out.println("TaaApplication: Requesting " +
                       method +
                       " for path " +
                       requestContext.getUriInfo()
                                     .getPath());
    Object entity = responseContext.getEntity();
    //    if (entity != null) {
    //      System.out.println("TaaApplication: Response " + new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(entity));
    //    }
    System.out.println("=====================================================================================================================");
  }

}
