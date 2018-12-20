package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsMessage;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.Vermittler;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaVermittlerRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaVermittlerdatenRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaVermittlerdatenResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.widgetconfig.WidgetAnnotationConstants;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/vermittler")
public class VermittlerResource {

  public VermittlerResource() {
  }

  @POST
  @Path("/getAllisnummer")
  @Produces("application/json")
  public TaaVermittlerdatenResponse getAllisnummer(TaaVermittlerRequest pRequest) {
    TaaVermittlerdatenResponse tResponse = new TaaVermittlerdatenResponse();
    tResponse.setStatus(new Status());

    Vermittler tVermittler = new Vermittler();

    if (pRequest.getVdAgt()
                .equals("04711")) {
      tResponse.getStatus()
               .setReturncode(ReturnCode.FACHLICHE_MELDUNGEN);
      GsMessage tFachFehler = new GsMessage(GsMessage.Type.ERROR,
                                            "PNC_ERROR_ALLISNR_VDAGT",
                                            "Ach nee, wat is dat den ...",
                                            "",
                                            GsMessage.Visibility.PERMANENT,
                                            GsMessage.Source.SERVER,
                                            WidgetAnnotationConstants.ALLGEMEIN_ALLISNR_VDAGT);
      tResponse.getStatus()
               .getMeldungenFachlich()
               .add(tFachFehler);
    } else {
      tResponse.getStatus()
               .setReturncode(ReturnCode.OK);

      tVermittler.setAllisnummer("0000071");
      tResponse.setVermittler(getVermittlerdaten(null).getVermittler());
    }

    try {
      // thread to sleep for 1000 milliseconds
      Thread.sleep(1000);
    } catch (Exception e) {
      System.out.println(e);
    }

    return tResponse;
  }

  @POST
  @Path("/getVermittlerdaten")
  @Produces("application/json")
  public TaaVermittlerdatenResponse getVermittlerdaten(TaaVermittlerdatenRequest pRequest) {

    TaaVermittlerdatenResponse tTaaVermittlerdatenResponse = new TaaVermittlerdatenResponse();
    tTaaVermittlerdatenResponse.setStatus(new Status());
    tTaaVermittlerdatenResponse.getStatus()
                               .setReturncode(ReturnCode.OK);

    GsMessage tFachFehler = new GsMessage(GsMessage.Type.ERROR,
                                          "PNC_ERROR_ALLISNR_VDAGT",
                                          "Ach nee, wat is dat den ...",
                                          "",
                                          GsMessage.Visibility.PERMANENT,
                                          GsMessage.Source.SERVER,
                                          WidgetAnnotationConstants.ALLGEMEIN_FREMDAKTENZEICHEN);// WidgetAnnotationConstants.ALLGEMEIN_ALLISNR_VDAGT);

    Vermittler tVermittler = new Vermittler();
    tVermittler.setAllisnummer("0000072");
    tVermittler.setVertriebsweg(Vermittler.VERTRIEBSWEG_AO);

    tVermittler.setInkassoArt("inkassoArt");
    tVermittler.setUrlToken("");
    tVermittler.setVdAgt("01023");
    tVermittler.setVertragspartner("Vertragspartner");

    if (pRequest.getAllisnummer()
                .equals("0000000")) {
      tTaaVermittlerdatenResponse.getStatus()
                                 .getMeldungenFachlich()
                                 .add(tFachFehler);
    }
    if (pRequest.getAllisnummer()
                .equals("0000001")) {
      tTaaVermittlerdatenResponse.getStatus()
                                 .getMeldungenFachlich()
                                 .add(tFachFehler);
      tVermittler.setVertriebsweg(Vermittler.VERTRIEBSWEG_MAKLER);
    }
    if (pRequest.getAllisnummer()
                .equals("0000002")) {
      tVermittler.setVertriebsweg(Vermittler.VERTRIEBSWEG_MAKLER);
    }

    tTaaVermittlerdatenResponse.setVermittler(tVermittler);

    return tTaaVermittlerdatenResponse;
  }

}
