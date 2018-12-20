package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsKeyValue;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaRechtsformRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaUnternehmensArtenRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaRechtsformResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaUnternehmensArtenResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/rechtsform")
public class RechtsformResource {

  @POST
  @Path("/getRechtsformenForTaetigkeit")
  @Produces("application/json")
  public TaaRechtsformResponse getRechtsformenForTaetigkeit(TaaRechtsformRequest request) {
    TaaRechtsformResponse response = new TaaRechtsformResponse();
    Status status = new Status();

    response.getRechtsformen()
            .add(new GsKeyValue("Einzelunternehmung",
                                "Einzelunternehmung"));
    response.getRechtsformen()
            .add(new GsKeyValue("GmbH",
                                "GmbH"));

    response.setStatus(status);

    return response;
  }

  @POST
  @Path("/getUnternehmensArten")
  @Produces("application/json")
  public TaaUnternehmensArtenResponse get(TaaUnternehmensArtenRequest request) {
    TaaUnternehmensArtenResponse response = new TaaUnternehmensArtenResponse();

    Status status = new Status();
    status.setReturncode(ReturnCode.OK);
    response.setStatus(status);

    response.getUnternehmensArten()
            .add(new GsKeyValue("1",
                                "Unternehmens"));
    response.getUnternehmensArten()
            .add(new GsKeyValue("2",
                                "Kindergartens"));

    return response;
  }

}
