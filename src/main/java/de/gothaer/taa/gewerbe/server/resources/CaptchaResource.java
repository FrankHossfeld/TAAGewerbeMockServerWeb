package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.CheckCaptchaRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.BooleanResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.GetCaptchaResponse;

import javax.ws.rs.*;

@Path("/captcha")
public class CaptchaResource {

  @GET
  @Path("/get/{dummy}")
  @Produces("application/json")
  public GetCaptchaResponse getCaptcha(@PathParam("dummy") String pDummy) {

    GetCaptchaResponse response = new GetCaptchaResponse();
    response.setId("1234");
    response.setImageUrl("http://localhost:8080/app/TAAGewerbe/captcha_test.jpeg");

    Status status = new Status();
    response.setStatus(status);
    return response;
  }

  @POST
  @Path("/check")
  @Produces("application/json")
  public BooleanResponse checkCaptcha(CheckCaptchaRequest request) {

    BooleanResponse response = new BooleanResponse();

    if ("test1".equals(request.getText())) {
      response.setResult(Boolean.TRUE);
    } else {
      response.setResult(Boolean.FALSE);
    }

    Status status = new Status();
    response.setStatus(status);
    return response;
  }

}
