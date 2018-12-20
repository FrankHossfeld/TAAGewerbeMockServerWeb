package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.taa.gewerbe.ui.shared.s2.transport.LoggingInfo;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Singleton
@Path("/loggingInfo")
public class LoggingInfoResource {

  /**
   * @param pLoggingInfo
   */
  @POST
  @Path("/log")
  @Consumes("application/json")
  public void save(LoggingInfo pLoggingInfo) {

    System.out.println("=====");
    System.out.println(pLoggingInfo.toString());
    System.out.println("=====");

  }

}
