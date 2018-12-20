package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.ZuersRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.ZuersResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/zuers")
public class ZuersResource {

  @POST
  @Path("/doZuers")
  @Produces("application/json")
  public ZuersResponse doZuers(ZuersRequest request) {
    ZuersResponse response = new ZuersResponse();
    Status status = new Status();
    status.setReturncode(ReturnCode.OK);
    response.setStatus(status);

    response.setArtBachlage("");
    response.setArtInselLage("");
    response.setBachlage(false);
    response.setInselLage(false);

    if (request.getRisikoOrt()
               .getStrasse()
               .equals("Gothaer Allee")) {
      response.setZuersZoneErdbeben("2");
      response.setZuersZoneWasser("2");
    } else if (request.getRisikoOrt()
                      .getStrasse()
                      .equals("Gothaerstr")) {
      response.setZuersZoneErdbeben("4");
      response.setZuersZoneWasser("4");
    } else if (request.getRisikoOrt()
                      .getStrasse()
                      .contains("Pohlig")) {
      response.setZuersZoneErdbeben("1");
      response.setZuersZoneWasser("1");
    } else if (request.getRisikoOrt()
                      .getStrasse()
                      .contains("Beethoven")) {
      response.setZuersZoneErdbeben("1");
      response.setZuersZoneWasser("1");
    } else if (request.getRisikoOrt()
                      .getStrasse()
                      .contains("Messering")) {
      if (request.getRisikoOrt()
                 .getHausnummer()
                 .equals("1")) {
        response.setZuersZoneErdbeben("3");
        response.setZuersZoneWasser("3");
      } else {
        response.setZuersZoneErdbeben("4");
        response.setZuersZoneWasser("4");
      }

    } else if (request.getRisikoOrt()
                      .getStrasse()
                      .contains("Mozart")) {
      response.setZuersZoneErdbeben("N");
      response.setZuersZoneWasser("N");

    } else if (request.getRisikoOrt()
                      .getStrasse()
                      .contains("Olgast")) {
      response.setZuersZoneErdbeben("N");
      response.setZuersZoneWasser("N");

    } else if (request.getRisikoOrt()
                      .getStrasse()
                      .contains("Tannen")) {
      response.setZuersZoneErdbeben("4i");
      response.setZuersZoneWasser("4i");
      response.setInselLage(true);
    } else if (request.getRisikoOrt()
                      .getStrasse()
                      .contains("Sonnenblume")) {
      response.setZuersZoneErdbeben("0");
      response.setZuersZoneWasser("0");
      response.setInselLage(true);
    } else {
      response.setZuersZoneErdbeben("1");
      response.setZuersZoneWasser("1");
    }

    return response;
  }

}
