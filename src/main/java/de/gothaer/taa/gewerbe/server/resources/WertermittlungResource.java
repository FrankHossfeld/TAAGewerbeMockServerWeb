package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.taa.gewerbe.ui.shared.s2.model.Wertermittlung;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.WertermittlungBerechnenRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.WertermittlungResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.math.BigDecimal;

/**
 * Mockimplementierung Wertermittlung
 *
 * @author tnk
 */
@Path("/wertermittlung")
public class WertermittlungResource {

  @POST
  @Path("/calculateVersicherungssummen")
  @Produces("application/json")
  public WertermittlungResponse calculateVersicherungssummen(WertermittlungBerechnenRequest request) {

    //    BigDecimal x = new BigDecimal(0);
    //    if (request.getWertermittlung() != null) {
    //      if (request.getWertermittlung().getBreite() != null &&
    //          request.getWertermittlung().getHoehe() != null &&
    //          request.getWertermittlung().getManuelleEingabe_m2() != null) {
    //        x = (request.getWertermittlung().getLaenge()
    //                               .add(request.getWertermittlung().getBreite())
    //                               .add(request.getWertermittlung().getHoehe())
    //                               .add(request.getWertermittlung().getManuelleEingabe_m2())).divide(BigDecimal.valueOf(4));
    //      }
    //    }

    final WertermittlungResponse tResponse = new WertermittlungResponse();

    tResponse.getStatus()
             .setReturncode(ReturnCode.OK);
    final Wertermittlung tWertermittlung = request.getAntrag()
                                                  .getRisikoort(request.getRisikoortId())
                                                  .getWertermittlung();
    if (tWertermittlung != null) {
      // setzen hier die 2 wichtigen Attribute für den Client auf Fake-Werte
      final BigDecimal tVsuNeuwert = new BigDecimal("555000");
      tWertermittlung.getErgebnis()
                     .setNeubauWertGesamt(tVsuNeuwert);
      tWertermittlung.getErgebnis()
                     .setBetragWert1914(tVsuNeuwert.divide(BigDecimal.valueOf(17),
                                                           2,
                                                           BigDecimal.ROUND_HALF_UP));
    }
    tResponse.setWertermittlung(tWertermittlung);

    return tResponse;
  }

}