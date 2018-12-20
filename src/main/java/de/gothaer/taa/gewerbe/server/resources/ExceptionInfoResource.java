package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ExceptionInfo;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Singleton
@Path("/exceptionInfo")
public class ExceptionInfoResource {

  /**
   * @param exceptionInfo
   */
  @POST
  @Path("/log")
  @Consumes("application/json")
  public void save(ExceptionInfo exceptionInfo) {

    System.out.println("===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + =====");
    System.out.println(exceptionInfo.toString());
    System.out.println("===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + ===== + =====");

  }

}
