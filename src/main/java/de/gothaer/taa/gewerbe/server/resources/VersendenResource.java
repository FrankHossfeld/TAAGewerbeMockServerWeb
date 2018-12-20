package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsKeyValue;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.VKdaten;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.staticEnums.AntragStatus;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.AveMeldung;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.AveMeldung.Typ;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaVersendenRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaVersendenResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("versenden")
public class VersendenResource {

  public VersendenResource() {
    super();

  }

  @POST
  @Path("/setOrder/{clientSessionId}")
  @Produces("application/json")
  public TaaVersendenResponse versenden(@Context UriInfo uriInfo,
                                        @PathParam("clientSessionId") String pClientSessionId,
                                        TaaVersendenRequest pRequest) {
    TaaVersendenResponse tResponse = new TaaVersendenResponse();
    Status tStatus = new Status();
    tStatus.setReturncode(ReturnCode.OK);
    tResponse.setStatus(tStatus);

    setAussteuerungsGruende(tResponse);
    tResponse.setVkdaten(new VKdaten());
    tResponse.getVkdaten()
             .setAntragStatus(AntragStatus.ANTRAG_VERSENDET_ANNAHME);

    // Faken der ADS Nummer
    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
    tResponse.setAdsAntragsnummer("ADS-" + sf.format(new Date()));

    return tResponse;
  }

  @POST
  @Path("/aveCheck/{clientSessionId}")
  @Produces("application/json")
  public TaaVersendenResponse aveCheck(@Context UriInfo uriInfo,
                                       @PathParam("clientSessionId") String pClientSessionId,
                                       TaaVersendenRequest pRequest) {
    TaaVersendenResponse tResponse = new TaaVersendenResponse();
    Status tStatus = new Status();
    tStatus.setReturncode(ReturnCode.OK);
    tResponse.setStatus(tStatus);
    setAussteuerungsGruende(tResponse);

    return tResponse;
  }

  private void setAussteuerungsGruende(TaaVersendenResponse response) {
    response.getAveMeldungen()
            .add(new AveMeldung("AG0001",
                                new GsKeyValue("PR_BHV",
                                               "Betriebshaftpflich"),
                                "AG0001",
                                "Vorschüden in Gebaude Leitungswasser - S-Quote = 33 %"));
    response.getAveMeldungen()
            .add(new AveMeldung("AG0002",
                                null,
                                "AG0002",
                                "Risikofragen nach Sicherungen in der Inhaltsversicherung negativ beantwortet"));
  }

  private void setAnwendungsstop(TaaVersendenResponse response) {
    AveMeldung tMeldung = new AveMeldung("AG0001",
                                         new GsKeyValue("PR_BHV",
                                                        "Betriebshaftpflich"),
                                         "AG0001",
                                         "Die Bearbeitung des Vertrages ist aktuell nicht müglich, da der Vertrag entweder storniert wurde oder im Betrieb eine Vertragsschwebe vorliegt.");
    tMeldung.setTyp(Typ.Stop);
    response.getAveMeldungen()
            .add(tMeldung);
  }

}
