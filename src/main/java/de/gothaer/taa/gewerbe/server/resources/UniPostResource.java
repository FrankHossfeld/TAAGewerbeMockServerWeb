package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsMessage;
import de.gothaer.gwt.gxt.config.shared.model.GsMessage.Source;
import de.gothaer.taa.gewerbe.ui.shared.s2.errorMessage.ErrorMessageIDs;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.RisikoortUniPost;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.UniPostRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.UniPostResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;

@Path("/unipost")
public class UniPostResource {

  @POST
  @Path("/doUniPost")
  @Produces("application/json")
  public UniPostResponse doUniPost(UniPostRequest pRequest) {
    UniPostResponse tResponse = new UniPostResponse();
    Status tStatus = new Status();
    tStatus.setReturncode(ReturnCode.OK);
    tResponse.setStatus(tStatus);

    if (pRequest.getRisikoOrt()
                .getStrasse()
                .toLowerCase()
                .contains("unbekannt")) {
      tStatus.setReturncode(ReturnCode.FACHLICHE_MELDUNGEN);
      GsMessage tMeldung = new GsMessage();
      tMeldung.setId(ErrorMessageIDs.AL_RISIKOORT_UNBEKANNT);
      tMeldung.setText("Die erfasste Risikoanschrift ist im Datenbestand der Deutschen Post unbekannt. Bitte prüfen und ggfs. korrigieren. Mit Klick auf 'Ja' wird die Adresse für die weitere Bearbeitung übernommen.<br> <br>Mit Klick auf 'Nein' gelangen Sie zurück zur Adresserfassung.<br><br>Wollen Sie fortfahren?");
      tMeldung.setSource(Source.SERVER);

      tStatus.add(tMeldung);
      return tResponse;
    }

    if (pRequest.getRisikoOrt()
                .getPlz()
                .equalsIgnoreCase("11111")) {
      tResponse.setRisikoorte(new ArrayList<RisikoortUniPost>());
      RisikoortUniPost tRisikoort = new RisikoortUniPost();
      tRisikoort.setPlz("22222");
      tRisikoort.setOrt("Ort2");
      tRisikoort.setStrasse("Die Strasse");
      tRisikoort.setHausnummer("2");
      tResponse.getRisikoorte()
               .add(tRisikoort);
      RisikoortUniPost tRisikoort3 = new RisikoortUniPost();
      tRisikoort3.setPlz("33333");
      tRisikoort3.setOrt("Ort3");
      tRisikoort3.setStrasse("Die Strasse");
      tRisikoort3.setHausnummer("3");
      tResponse.getRisikoorte()
               .add(tRisikoort3);
      RisikoortUniPost tRisikoort4 = new RisikoortUniPost();
      tRisikoort4.setPlz("44444");
      tRisikoort4.setOrt("Ort4");
      tRisikoort4.setStrasse("Die Strasse");
      tRisikoort4.setHausnummer("4");
      tResponse.getRisikoorte()
               .add(tRisikoort4);
      return tResponse;
    }

    if (pRequest.getRisikoOrt()
                .getStrasse()
                .equalsIgnoreCase("Gothaer Alleee")) {
      RisikoortUniPost tRisikoort = new RisikoortUniPost(pRequest.getRisikoOrt());
      tRisikoort.setStrasse("Gothaer Allee");
      tResponse.setRisikoorte(new ArrayList<RisikoortUniPost>());
      tResponse.getRisikoorte()
               .add(tRisikoort);

      RisikoortUniPost tRisikoOrt2 = new RisikoortUniPost(tRisikoort);
      tRisikoOrt2.setStrasse("Gothaerstr.");
      tResponse.getRisikoorte()
               .add(tRisikoOrt2);

      RisikoortUniPost tRisikoOrt3 = new RisikoortUniPost(tRisikoort);
      tRisikoOrt3.setStrasse(pRequest.getRisikoOrt()
                                     .getStrasse());
      tRisikoOrt3.setHausnummer(pRequest.getRisikoOrt()
                                        .getHausnummer());
      tRisikoOrt3.setPlz(pRequest.getRisikoOrt()
                                 .getPlz());
      tRisikoOrt3.setOrt(pRequest.getRisikoOrt()
                                 .getOrt());
      tResponse.getRisikoorte()
               .add(tRisikoOrt3);
    }
    if (pRequest.getRisikoOrt()
                .getStrasse()
                .equalsIgnoreCase("Gothaer Alle")) {
      RisikoortUniPost tRisikoort = new RisikoortUniPost(pRequest.getRisikoOrt());
      tRisikoort.setStrasse("Gothaer Allee");
      tResponse.setRisikoorte(new ArrayList<RisikoortUniPost>());
      tResponse.getRisikoorte()
               .add(tRisikoort);
    }
    if (pRequest.getRisikoOrt()
                .getStrasse()
                .equalsIgnoreCase("Gothaer")) {
      RisikoortUniPost tRisikoort = new RisikoortUniPost(pRequest.getRisikoOrt());
      tRisikoort.setStrasse("Gothaer Allee");
      tResponse.setRisikoorte(new ArrayList<RisikoortUniPost>());
      tResponse.getRisikoorte()
               .add(tRisikoort);

      RisikoortUniPost tRisikoOrt2 = new RisikoortUniPost(tRisikoort);
      tRisikoOrt2.setStrasse("Gothaerstr.");
      tResponse.getRisikoorte()
               .add(tRisikoOrt2);
    }

    if (pRequest.getRisikoOrt()
                .getStrasse()
                .equalsIgnoreCase("Pohlig")) {
      RisikoortUniPost tRisikoort = new RisikoortUniPost(pRequest.getRisikoOrt());
      tRisikoort.setStrasse("Pohligweg");
      tResponse.setRisikoorte(new ArrayList<RisikoortUniPost>());
      tResponse.getRisikoorte()
               .add(tRisikoort);

      RisikoortUniPost tRisikoOrt2 = new RisikoortUniPost(tRisikoort);
      tRisikoOrt2.setStrasse("Pohligstr.");
      tResponse.getRisikoorte()
               .add(tRisikoOrt2);

      RisikoortUniPost tRisikoOrt3 = new RisikoortUniPost(tRisikoort);
      tRisikoOrt3.setStrasse("Pohlig Allee");
      tResponse.getRisikoorte()
               .add(tRisikoOrt3);

      RisikoortUniPost tRisikoOrt4 = new RisikoortUniPost(tRisikoort);
      tRisikoOrt4.setStrasse("Pohlig Pfad");
      tResponse.getRisikoorte()
               .add(tRisikoOrt4);
    }

    // ***********************************************************************************************************

    if (pRequest.getRisikoOrt()
                .getStrasse()
                .equalsIgnoreCase("0")) {
      RisikoortUniPost tRisikoort = new RisikoortUniPost(pRequest.getRisikoOrt());
      tRisikoort.setStrasse("Sonnenblumenweg");
      tRisikoort.setOrt("Hürth");
      tRisikoort.setPlz("50354");
      tRisikoort.setHausnummer("29");
      tResponse.setRisikoorte(new ArrayList<RisikoortUniPost>());
      tResponse.getRisikoorte()
               .add(tRisikoort);
    }
    if (pRequest.getRisikoOrt()
                .getStrasse()
                .equalsIgnoreCase("1")) {
      RisikoortUniPost tRisikoort = new RisikoortUniPost(pRequest.getRisikoOrt());
      tRisikoort.setStrasse("Pohligstr.");
      tRisikoort.setOrt("Köln");
      tRisikoort.setPlz("50969");
      tRisikoort.setHausnummer("1");
      tResponse.setRisikoorte(new ArrayList<RisikoortUniPost>());
      tResponse.getRisikoorte()
               .add(tRisikoort);
    }
    if (pRequest.getRisikoOrt()
                .getStrasse()
                .equalsIgnoreCase("2")) {

      tResponse.setRisikoorte(new ArrayList<RisikoortUniPost>());

      RisikoortUniPost tRisikoOrt1 = new RisikoortUniPost(pRequest.getRisikoOrt());
      tRisikoOrt1.setStrasse("Beethovenstr.");
      tRisikoOrt1.setOrt("Köln");
      tRisikoOrt1.setPlz("50674");
      tRisikoOrt1.setHausnummer("25");
      tResponse.getRisikoorte()
               .add(tRisikoOrt1);

      RisikoortUniPost tRisikoOrt2 = new RisikoortUniPost(pRequest.getRisikoOrt());
      tRisikoOrt2.setStrasse("Peter von Fliesteden Str.");
      tRisikoOrt2.setOrt("Köln");
      tRisikoOrt2.setPlz("50960");
      tRisikoOrt2.setHausnummer("41");
      tResponse.getRisikoorte()
               .add(tRisikoOrt2);

      tResponse.getRisikoorte()
               .add(pRequest.getRisikoOrt());
    }
    if (pRequest.getRisikoOrt()
                .getStrasse()
                .equalsIgnoreCase("4")) {
      RisikoortUniPost tRisikoort = new RisikoortUniPost(pRequest.getRisikoOrt());
      tRisikoort.setStrasse("Messering");
      tRisikoort.setOrt("Dresden");
      tRisikoort.setPlz("01067");
      tRisikoort.setHausnummer("1a");
      tResponse.setRisikoorte(new ArrayList<RisikoortUniPost>());
      tResponse.getRisikoorte()
               .add(tRisikoort);
    }
    if (pRequest.getRisikoOrt()
                .getStrasse()
                .equalsIgnoreCase("3")) {
      RisikoortUniPost tRisikoort = new RisikoortUniPost(pRequest.getRisikoOrt());
      tRisikoort.setStrasse("Messering");
      tRisikoort.setOrt("Dresden");
      tRisikoort.setPlz("01067");
      tRisikoort.setHausnummer("1");
      tResponse.setRisikoorte(new ArrayList<RisikoortUniPost>());
      tResponse.getRisikoorte()
               .add(tRisikoort);
    }
    if (pRequest.getRisikoOrt()
                .getStrasse()
                .equalsIgnoreCase("4i")) {
      RisikoortUniPost tRisikoort = new RisikoortUniPost(pRequest.getRisikoOrt());
      tRisikoort.setStrasse("Tannenweg");
      tRisikoort.setOrt(" Bergen auf Rügen");
      tRisikoort.setPlz("18528");
      tRisikoort.setHausnummer("13");
      tResponse.setRisikoorte(new ArrayList<RisikoortUniPost>());
      tResponse.getRisikoorte()
               .add(tRisikoort);
    }
    if (pRequest.getRisikoOrt()
                .getStrasse()
                .equalsIgnoreCase("n")) {
      RisikoortUniPost tRisikoort = new RisikoortUniPost(pRequest.getRisikoOrt());
      tRisikoort.setStrasse("Olgastr.");
      tRisikoort.setOrt("Lingen");
      tRisikoort.setPlz("49809");
      tRisikoort.setHausnummer("5");
      tResponse.setRisikoorte(new ArrayList<RisikoortUniPost>());
      tResponse.getRisikoorte()
               .add(tRisikoort);
    }
    return tResponse;
  }

}
