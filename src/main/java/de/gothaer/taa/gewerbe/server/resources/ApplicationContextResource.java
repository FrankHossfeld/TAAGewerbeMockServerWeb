package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.taa.gewerbe.ui.shared.s2.model.staticEnums.ApplicationContext;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaApplicationContextResponse;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Singleton
@Path("/applicationContext")
public class ApplicationContextResource {

  //   private ApplicationContext applicationContext = ApplicationContext.TAA;
  public ApplicationContext applicationContext         = ApplicationContext.TR;
  public boolean            testMode                   = true;
  public boolean            showCheckboxEuUnterschrift = true;

  @GET
  @Path("/getApplicationContext")
  @Produces("application/json")
  public TaaApplicationContextResponse getApplicationContext() {
    System.out.println("******************************");
    System.out.println("* Call getApplicationContext *");
    if (ApplicationContext.TR.equals(applicationContext)) {
      System.out.println("*    -> TR <-                *");
    } else {
      System.out.println("*    -> TAA <-               *");
    }
    System.out.println("******************************");
    TaaApplicationContextResponse response = new TaaApplicationContextResponse();
    response.setApplicationContext(applicationContext);
    response.setTestMode(testMode);
    response.setShowCheckboxEuUnterschrift(showCheckboxEuUnterschrift);

    Status status = new Status();
    status.setReturncode(ReturnCode.OK);
    response.setStatus(status);

    return response;
  }

  @GET
  @Path("/setApplicationContextTAA")
  @Produces("application/json")
  public String setApplicationContextTAA() {
    System.out.println("Call setApplicationContextTAA");
    this.applicationContext = ApplicationContext.TAA;
    return "OK";
  }

  @GET
  @Path("/setApplicationContextTR")
  @Produces("application/json")
  public String setApplicationContextTR() {
    System.out.println("Call setApplicationContextTR");
    this.applicationContext = ApplicationContext.TR;
    return "OK";
  }

}
