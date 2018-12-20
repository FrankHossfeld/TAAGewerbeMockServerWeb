package de.gothaer.taa.gewerbe.server.filters;

import org.glassfish.jersey.message.internal.ReaderWriter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomLoggingFilter
    implements ContainerRequestFilter,
               ContainerResponseFilter {
  @Override
  public void filter(ContainerRequestContext requestContext)
      throws IOException {
    System.out.println("==> HTTP REQUEST Start: ");
    System.out.println("User: " +
                       (requestContext.getSecurityContext()
                                      .getUserPrincipal() == null ? "unknown" : requestContext.getSecurityContext()
                                                                                              .getUserPrincipal()));
    System.out.println("Path: " +
                       requestContext.getUriInfo()
                                     .getPath());
    System.out.println("Header: " + requestContext.getHeaders());
    System.out.println("Entity: " + getEntityBody(requestContext));
    System.out.println("==> HTTP REQUEST End");
  }

  private String getEntityBody(ContainerRequestContext requestContext) {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    InputStream in = requestContext.getEntityStream();

    final StringBuilder b = new StringBuilder();
    try {
      ReaderWriter.writeTo(in,
                           out);

      byte[] requestEntity = out.toByteArray();
      if (requestEntity.length == 0) {
        b.append("")
         .append("\n");
      } else {
        b.append(new String(requestEntity))
         .append("\n");
      }
      requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));

    } catch (IOException ex) {
      //Handle logging error
    }
    return b.toString();
  }

  @Override
  public void filter(ContainerRequestContext requestContext,
                     ContainerResponseContext responseContext)
      throws IOException {
    System.out.println("==> HTTP RESPONSE Start: ");
    System.out.println("Header: " + responseContext.getHeaders());
    //    System.out.println("Entity: " + getEntityBody(responseContext));
    System.out.println("==> HTTP RESPONSE End");
  }

}