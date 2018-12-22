package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsMessage;
import de.gothaer.gwt.gxt.config.shared.model.GsMessage.Type;
import de.gothaer.gwt.gxt.config.shared.model.GsMessage.Visibility;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.Antrag;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.bhv.BhvWagnis;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaValidiereVermittlerAbhaengigeDatenRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaValidierungsRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaValidierungsResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.widgetconfig.WidgetAnnotationConstants;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.math.BigDecimal;

@Path("/validierung")
public class ValidierungsResource {

  @POST
  @Path("/validiere")
  @Produces("application/json")
  @Consumes("application/json")
  public TaaValidierungsResponse validiere(TaaValidierungsRequest pRequest) {

    TaaValidierungsResponse response = new TaaValidierungsResponse();
    Status status = new Status();
    status.setReturncode(ReturnCode.OK);
    response.setStatus(status);

    // Hier künnen noch 'echte' Prüfungen eingebaut werden!
    Antrag tAntrag = pRequest.getAntrag();

    if (tAntrag.getBetriebsHaftpflichtVersicherung() != null) {
      for (BhvWagnis tWagnis : tAntrag.getBetriebsHaftpflichtVersicherung()
                                      .getWagnisse()) {
        if (tWagnis.getJahresumsatz() != null && new BigDecimal("99").compareTo(tWagnis.getJahresumsatz()) == 0) {
          status.setReturncode(ReturnCode.FACHLICHE_MELDUNGEN);
          GsMessage tFachFehler = new GsMessage(Type.ERROR,
                                                "PNC_ERROR_TEST1",
                                                tAntrag.getBetriebsHaftpflichtVersicherung()
                                                       .getProdukt()
                                                       .getKey(),
                                                tWagnis.getWkz(),
                                                "Jahresumsatz nicht im Wertebereich",
                                                "CLIENT_VALIDATOR",
                                                Visibility.PERMANENT,
                                                GsMessage.Source.SERVER,
                                                WidgetAnnotationConstants.BHV_JAHRESUMSATZ);
          status.getMeldungenFachlich()
                .add(tFachFehler);
        }
      }
    }
    return response;
  }

  @POST
  @Path("/validiereVermittlerAbhaengigeDaten")
  @Produces("application/json")
  @Consumes("application/json")
  public TaaValidierungsResponse validiereVermittlerAbhaengigeDaten(TaaValidiereVermittlerAbhaengigeDatenRequest pRequest) {

    TaaValidierungsResponse response = new TaaValidierungsResponse();
    Status status = new Status();
    status.setReturncode(ReturnCode.OK);
    response.setStatus(status);

    // Hier künnen noch 'echte' Prüfungen eingebaut werden!
//    Antrag tAntrag = pRequest.getAntrag();

    //    if (tAntrag.getBetriebsHaftpflichtVersicherung() != null) {
    //      for (BhvWagnis tWagnis : tAntrag.getBetriebsHaftpflichtVersicherung()
    //                                      .getWagnisse()) {
    //        if (tWagnis.getJahresumsatz() != null && new BigDecimal("99").compareTo(tWagnis.getJahresumsatz()) == 0) {
    //          status.setReturncode(ReturnCode.FACHLICHE_MELDUNGEN);
    //          GsMessage tFachFehler = new GsMessage(Type.ERROR,
    //                                                "PNC_ERROR_TEST1",
    //                                                tAntrag.getBetriebsHaftpflichtVersicherung()
    //                                                       .getProduktConfiguration()
    //                                                       .getKey(),
    //                                                tWagnis.getWkz(),
    //                                                "Jahresumsatz nicht im Wertebereich",
    //                                                Visibility.PERMANENT,
    //                                                GsMessage.Source.SERVER,
    //                                                WidgetAnnotationConstants.BHV_JAHRESUMSATZ);
    //          status.getMeldungenFachlich()
    //                .add(tFachFehler);
    //        }
    //      }
    //    }
    return response;
  }

  //  @POST
  //  @Path("/validiereFremdaktenzeichen")
  //  @Produces("application/json")
  //  @Consumes("application/json")
  //  public TaaValidierungsResponse validiereFremdaktenzeichen(TaaValidiereFremdaktenzeichenRequest pRequest) {
  //
  //    TaaValidierungsResponse response = new TaaValidierungsResponse();
  //    Status                  status   = new Status();
  //    status.setReturncode(ReturnCode.OK);
  //    response.setStatus(status);
  //
  ////    // Hier künnen noch 'echte' Prüfungen eingebaut werden!
  ////    Antrag tAntrag = pRequest.getAntrag();
  ////
  ////    if (tAntrag.getBetriebsHaftpflichtVersicherung() != null) {
  ////      for (BhvWagnis tWagnis : tAntrag.getBetriebsHaftpflichtVersicherung()
  ////                                      .getWagnisse()) {
  ////        if (tWagnis.getJahresumsatz() != null && new BigDecimal("99").compareTo(tWagnis.getJahresumsatz()) == 0) {
  ////          status.setReturncode(ReturnCode.FACHLICHE_MELDUNGEN);
  ////          GsMessage tFachFehler = new GsMessage(Type.ERROR,
  ////                                                "PNC_ERROR_TEST1",
  ////                                                "Jahresumsatz nicht im Wertebereich",
  ////                                                Visibility.PERMANENT,
  ////                                                GsMessage.Source.SERVER,
  ////                                                new GsMessage.MessageWidgetIdentifier(WidgetAnnotationConstants.BHV_JAHRESUMSATZ,
  ////                                                                              tWagnis.getWkz(),
  ////                                                                              tAntrag.getBetriebsHaftpflichtVersicherung()
  ////                                                                                     .getProduktConfiguration()
  ////                                                                                     .getKey()));
  ////          status.getMeldungenFachlich()
  ////                .add(tFachFehler);
  ////        }
  ////      }
  ////    }
  //    return response;
  //  }

}
