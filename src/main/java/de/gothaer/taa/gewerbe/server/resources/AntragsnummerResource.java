package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.AntragsnummernRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.AntragsnummernResponse;

import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.text.SimpleDateFormat;
import java.util.Date;

@Singleton
@Path("/antragsnummer")
public class AntragsnummerResource {

  private static int laufendeNummer = 0;

  @POST
  @Path("/getNewAntragsnummer")
  @Produces("application/json")
  public AntragsnummernResponse getAntragsNummer(AntragsnummernRequest request) {
    System.out.println("Call StichwortService");
    String pattern = "yyyyMMdd";
    SimpleDateFormat tFormat = new SimpleDateFormat(pattern);
    String ret = tFormat.format(new Date()) +
                 "EL1" +
                 String.format("%06d",
                               ++laufendeNummer);
    AntragsnummernResponse response = new AntragsnummernResponse();
    Status stat = new Status();
    stat.setReturncode(ReturnCode.OK);
    response.setAntragsnummer(ret);
    response.setStatus(stat);
    return response;
  }

}
