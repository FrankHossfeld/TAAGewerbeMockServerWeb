package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsMessage;
import de.gothaer.gwt.gxt.config.shared.model.GsMessage.Source;
import de.gothaer.taa.gewerbe.ui.shared.s2.errorMessage.ErrorMessageIDs;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaSepaRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaSepaResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.widgetconfig.WidgetAnnotationConstants;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/sepa")
public class SepaResource {

  @POST
  @Path("/validiban")
  @Produces("application/json")
  public TaaSepaResponse validiban(TaaSepaRequest pRequest) {
    TaaSepaResponse tResponse = new TaaSepaResponse();

    Status tStatus = new Status();
    if ("DE123456789".equals(pRequest.getIban())) {
      tStatus.setReturncode(ReturnCode.FACHLICHE_MELDUNGEN);
      GsMessage tMessage = new GsMessage();
      tMessage.setMessageId(ErrorMessageIDs.PD_UNGUELTIGE_IBAN);
      tMessage.getMessageWidgetIds()
              .add(WidgetAnnotationConstants.PERSON_PARTNER_IBAN);
      tMessage.setSource(Source.SERVER);
      tMessage.setText("fehlerhafte Iban");
      tStatus.add(tMessage);
    } else {
      tResponse.setIban("DE12500105170648489890");
      tResponse.setBic("INGDDEFFXXX");
    }
    tResponse.setStatus(tStatus);

    return tResponse;
  }

}
