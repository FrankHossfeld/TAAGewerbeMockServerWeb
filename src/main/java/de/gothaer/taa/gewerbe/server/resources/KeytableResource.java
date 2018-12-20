package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsKeyValue;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaKeytableResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("/keytable")
public class KeytableResource {

  @GET
  @Path("/getKeyValues")
  @Produces("application/json")
  public TaaKeytableResponse getKeyValues() {
    TaaKeytableResponse tResponse = new TaaKeytableResponse();

    // Anreden für natürliche Personen holen
    tResponse.setAnredenNatuerlichePerson(lese_PTR_ANREDE_NAT());
    // Anreden für juristische Personen holen
    tResponse.setAnredenJuristischePerson(lese_PTR_ANREDE_ORGA());

    // Anrede holen
    tResponse.setTitel(lese_PTR_TITEL());

    // KommTyp holen
    tResponse.setKommTyp(lese_PTR_KOMM_TYP());

    // SACH_ZAHLWEISE holen
    tResponse.setZahlweise(lese_CRM_GRA_ZAHLWEISE());

    // GSM_ZAHLUNGSART holen
    tResponse.setZahlart(lese_GSM_ZAHLUNGSART());

    // DIT
    tResponse.setDit(lese_DIT());
    // DIT Wertgutachten
    tResponse.setDitWertgutachten(lese_DITWert());
    // DIT Gk4
    tResponse.setDitGk4(lese_DITGK4());

    // SEPA_ABLAGE
    tResponse.setAblageOrt(lese_AblageortSepa());

    // Status Vorversicherungen
    tResponse.setStatusVorversicherung(lese_StatusVorverischerung());

    //Abweichender Korrespondenzempfänger
    tResponse.setKorrespondenzEmpfaengerType(lese_AbweichenderKorrEmpfaenger());

    //Anpassung Preis Zeichnung
    tResponse.setAnpassungPreis(lese_AnpassungPreis());

    tResponse.getStatus()
             .setReturncode(ReturnCode.OK);
    return tResponse;
  }

  private List<GsKeyValue> lese_StatusVorverischerung() {
    List<GsKeyValue> tList = new ArrayList<>();

    tList.add(new GsKeyValue("BEANTRAGT",
                             "beantragt"));
    tList.add(new GsKeyValue("BESTEHT_WEITER",
                             " besteht beim VR weiter"));
    tList.add(new GsKeyValue("ABGELEHNT",
                             "abgelehnt"));
    tList.add(new GsKeyValue("WIRD_GEKUENDIGT_VN",
                             "(wird) gekündigt durch VN"));
    tList.add(new GsKeyValue("GEKUENDIGT_VR",
                             "gekündigt durch VR"));

    return tList;
  }

  private List<GsKeyValue> lese_AblageortSepa() {
    List<GsKeyValue> list = new ArrayList<>();

    GsKeyValue tKV = new GsKeyValue("1",
                                    "   Zentrale ");
    list.add(tKV);
    tKV = new GsKeyValue("2",
                         "Vermittler ");
    list.add(tKV);
    tKV = new GsKeyValue("3",
                         "Kunde ");
    list.add(tKV);
    tKV = new GsKeyValue("4",
                         "Kunde (Kundenanfrage) ");
    list.add(tKV);

    return list;
  }

  private List<GsKeyValue> lese_DITWert() {
    List<GsKeyValue> list = new ArrayList<>();

    GsKeyValue tKV = new GsKeyValue("011680",
                                    "Wert- /Summenermittlungen");
    list.add(tKV);

    return list;
  }

  private List<GsKeyValue> lese_GSM_ZAHLUNGSART() {
    List<GsKeyValue> list = new ArrayList<>();

    GsKeyValue tKV = new GsKeyValue("1",
                                    "Lastschrift");
    list.add(tKV);
    tKV = new GsKeyValue("2",
                         "Rechnung (Überweisung)");
    list.add(tKV);

    return list;
  }

  private List<GsKeyValue> lese_DIT() {
    List<GsKeyValue> list = new ArrayList<>();

    GsKeyValue tKV = new GsKeyValue("011910",
                                    "Schriftwechsel Agentur/Makler");
    list.add(tKV);
    tKV = new GsKeyValue("011685",
                         "Bescheinigungen, Sonstige");
    list.add(tKV);
    tKV = new GsKeyValue("011206",
                         "Sanierungsnachweise");
    list.add(tKV);
    tKV = new GsKeyValue("011207",
                         "Objektbeschreibung");
    list.add(tKV);
    tKV = new GsKeyValue("011521",
                         "Fragebogen");
    list.add(tKV);
    tKV = new GsKeyValue("013108",
                         "Fotos, Schadenbeleg, Ermittlungsdoku");
    list.add(tKV);

    return list;
  }

  private List<GsKeyValue> lese_DITGK4() {
    List<GsKeyValue> list = new ArrayList<>();

    GsKeyValue tKV = new GsKeyValue("011696",
                                    "HQ 100-Auskunft/Elementarfragebogen");
    list.add(tKV);

    return list;
  }

  private List<GsKeyValue> lese_PTR_ANREDE_ORGA() {

    List<GsKeyValue> list = new ArrayList<>();

    GsKeyValue tKV = new GsKeyValue("10",
                                    "Behörde");
    list.add(tKV);
    tKV = new GsKeyValue("11",
                         "Damen");
    list.add(tKV);
    tKV = new GsKeyValue("05",
                         "Eheleute");
    list.add(tKV);
    tKV = new GsKeyValue("09",
                         "Eigentümergemeinschaft");
    list.add(tKV);
    tKV = new GsKeyValue("08",
                         "Erbengemeinschaft");
    list.add(tKV);
    tKV = new GsKeyValue("07",
                         "Firma");
    list.add(tKV);
    tKV = new GsKeyValue("04",
                         "Herren");
    list.add(tKV);
    tKV = new GsKeyValue("03",
                         "Herrn und Frau");
    list.add(tKV);
    tKV = new GsKeyValue("06",
                         "Rechtsanwaltsbüro");
    list.add(tKV);

    return list;
  }

  private List<GsKeyValue> lese_PTR_ANREDE_NAT() {

    List<GsKeyValue> list = new ArrayList<>();

    GsKeyValue tKV = new GsKeyValue("02",
                                    "Frau");
    list.add(tKV);
    tKV = new GsKeyValue("01",
                         "Herr");
    list.add(tKV);

    return list;
  }

  private List<GsKeyValue> lese_PTR_TITEL() {

    List<GsKeyValue> list = new ArrayList<>();

    GsKeyValue tKV = new GsKeyValue("0",
                                    "Dr.");
    list.add(tKV);
    tKV = new GsKeyValue("1",
                         "Professor");
    list.add(tKV);

    return list;
  }

  private List<GsKeyValue> lese_AbweichenderKorrEmpfaenger() {

    List<GsKeyValue> list = new ArrayList<>();

    list.add(new GsKeyValue("0",
                            "Versicherungsnehmer"));
    list.add(new GsKeyValue("1",
                            "abweichender Korrespondenzempfänger"));

    return list;
  }

  private List<GsKeyValue> lese_AnpassungPreis() {

    List<GsKeyValue> list = new ArrayList<>();
    list.add(new GsKeyValue("0",
                            "Nachlass"));
    list.add(new GsKeyValue("1",
                            "Zuschlag"));

    return list;
  }

  private List<GsKeyValue> lese_PTR_KOMM_TYP() {

    List<GsKeyValue> list = new ArrayList<>();

    GsKeyValue tKV = new GsKeyValue("09",
                                    "eMail");
    list.add(tKV);
    tKV = new GsKeyValue("07",
                         "Handy");
    list.add(tKV);
    tKV = new GsKeyValue("12",
                         "Homepage");
    list.add(tKV);
    tKV = new GsKeyValue("01",
                         "Telefax");
    list.add(tKV);
    tKV = new GsKeyValue("02",
                         "Telefon");
    list.add(tKV);

    return list;
  }

  private List<GsKeyValue> lese_CRM_GRA_ZAHLWEISE() {

    List<GsKeyValue> list = new ArrayList<>();

    GsKeyValue tKV = new GsKeyValue("1",
                                    "jährlich");
    list.add(tKV);
    tKV = new GsKeyValue("2",
                         "halbjährlich");
    list.add(tKV);
    tKV = new GsKeyValue("3",
                         "monatlich");
    list.add(tKV);
    tKV = new GsKeyValue("4",
                         "vierteljährlich");
    list.add(tKV);

    return list;
  }

}
