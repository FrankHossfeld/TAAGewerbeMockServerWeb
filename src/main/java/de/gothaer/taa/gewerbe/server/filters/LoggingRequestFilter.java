package de.gothaer.taa.gewerbe.server.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

public class LoggingRequestFilter
    implements ContainerRequestFilter {

  @Override
  public void filter(ContainerRequestContext containerRequestContext)
      throws IOException {
    String method = containerRequestContext.getMethod();

    System.out.println("=====================================================================================================================");
    System.out.println("TaaApplication: Requesting " +
                       method +
                       " for path " +
                       containerRequestContext.getUriInfo()
                                              .getPath());
    //    Object entity = containerRequestContext.getEntityStream();
    //    if (entity != null) {
    //      System.out.println("Request " + new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(entity));
    //    }
    System.out.println("=====================================================================================================================");
  }

}
