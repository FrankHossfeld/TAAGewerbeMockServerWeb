package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.taa.gewerbe.ui.shared.s2.model.Vermittler;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaDecodeAndCheckTRParameterRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaDecodeTokenResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Mockimplementierung für den DecodeToken-Service. Gibt einfach immer die VDAgt 01010 zurück
 *
 * @author tnk
 */
@Path("/token")
public class DecodeTokenResource {

  @POST
  @Path("/decode")
  @Produces("application/json")
  public TaaDecodeTokenResponse decodeToken(TaaDecodeAndCheckTRParameterRequest request) {
    TaaDecodeTokenResponse tResponse = new TaaDecodeTokenResponse();
    Vermittler tVermittler = new Vermittler("0000071",
                                            "01010");
    tResponse.setVermittler(tVermittler);
    tResponse.setCaller("");

    System.out.println("===== decode");
    if (request.getToken() == null ||
        request.getToken()
               .isEmpty()) {
      System.out.println("KEIN Token gefunden!");
    } else {
      System.out.println("Token gefunden: " + request.getToken());
    }
    System.out.println("=====");

    return tResponse;
  }

}
