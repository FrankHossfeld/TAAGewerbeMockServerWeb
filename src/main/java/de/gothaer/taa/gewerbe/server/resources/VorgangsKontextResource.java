package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsKeyValue;
import de.gothaer.taa.gewerbe.ui.shared.s2.GUID;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.*;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.Risikofrage.AnswerType;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.Risikofrage.QuestionType;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.AbstractProdukt;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.AbstractProdukt.BearbeitungsStatus;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.TAA;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.alle.Deckungserweiterung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.alle.PlusBaustein;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.alle.VersichertesGebaeudeElektronikMaschinenPhotovoltaik;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.betriebsschliessung.BetriebsschliessungTarifkonfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.betriebsschliessung.BetriebsschliessungVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.betriebsschliessung.VersicherterRisikoortBetriebsschliessung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.bhv.BetriebsHaftpflichtVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.bhv.BhvTarifkonfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.elektronik.ElektronikTarifkonfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.elektronik.ElektronikVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.gebaeude.GebaeudeTarifkonfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.gebaeude.GebaeudeVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.gebaeude.VersichertesGebaeudeGebaeude;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.inhaltsversicherung.InhaltTarifkonfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.inhaltsversicherung.InhaltsVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.inhaltsversicherung.VersichertesGebaeudeInhalt;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.maschinen.fahrbar.MaschinenFahrbarTarifkonfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.maschinen.fahrbar.MaschinenFahrbarVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.maschinen.stationaer.MaschinenStationaerTarifkonfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.maschinen.stationaer.MaschinenStationaerVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.photovoltaik.PhotovoltaikTarifkonfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.photovoltaik.PhotovoltaikVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.privat.PrivateRisiken;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.privat.PrivateRisikenTarifkonfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.vsh.VermoegensschadenTarifkonfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.vsh.Vermoegensschadenhaftpflicht;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.werkverkehr.WerkverkehrTarifkonfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.werkverkehr.WerkverkehrVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.staticEnums.*;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.AveMeldung;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaVorgangskontextGetRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaVorgangskontextRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaVorgangskontextResponse;

import javax.inject.Singleton;
import javax.ws.rs.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Singleton
@Path("/vorgangsKontext")
public class VorgangsKontextResource {

  private static final GsKeyValue                 AUSWAHL_1 = new GsKeyValue("1",
                                                                             "Plusbaustein I");
  private static final GsKeyValue                 AUSWAHL_0 = new GsKeyValue("0",
                                                                             "kein Plusbaustein");
  private static int laufendeNummer = 0;
  private              TaaVorgangskontextResponse response01;
  /* Id des VorgangsKontext - zu Testzweckn */
  private              String                     vorgangsIdModel;
  private              String                     vorgangsIdPartner;
  /* historyName */
  private              String                     historyName01;
  /* Base64 encodierter Token der URL */
  private              String                     historyToken01;
  /* Antrag */
  private              Antrag                     antrag01;
  private              Antrag                     bestandsVertrag;
  /* Kenzeichen Auto-Save */
  private              boolean                    autoSave01;
  /* Kennezeichen, ob ToolTip of the Day schon gezeit wurde ... */
  private              boolean                    toolTipOfTheDayAlreadyShown;
  /* Datum an dem der ToolTip of the Day gezeigt wurde */
  private              Date                       toolTipOfTheDayShownAt;
  private TaaVorgangskontextResponse vorgangsKontextPartner;
  private ApplicationContext         applicationContext;
  private VKdaten      vkDaten;
  private AntragStatus antragsSatus = AntragStatus.TARIFIERT;

  @POST
  @Path("/get")
  @Produces("application/json")
  public TaaVorgangskontextResponse get(TaaVorgangskontextGetRequest request) {
    TaaVorgangskontextResponse response = new TaaVorgangskontextResponse(historyName01,
                                                                         historyToken01,
                                                                         vorgangsIdModel,
                                                                         antrag01,
                                                                         bestandsVertrag,
                                                                         autoSave01,
                                                                         toolTipOfTheDayAlreadyShown,
                                                                         toolTipOfTheDayShownAt,
                                                                         MODEL_TYP.CLIENT.name());
    Status status = new Status();
    status.setReturncode(ReturnCode.OK);
    VKdaten tVKdaten = new VKdaten();
    if (this.vkDaten == null) {
      tVKdaten.setAntragMinEinmalGedruckt(false);
      tVKdaten.setBeratungsProtMinEinmalGedruckt(false);
      tVKdaten.setAntragStatus(AntragStatus.OHNE);
      response.setVkdaten(tVKdaten);
    } else {
      response.setVkdaten(this.vkDaten);
    }

    Antrag antrag;
    if (antrag01 != null) {
      antrag = antrag01;
    } else {
      antrag = new Antrag();
    }

    Individualisierung individualisierung = new Individualisierung();
    individualisierung.setAntragIndividualisierbar(true);
    antrag.setIndividualisierung(individualisierung);

    String pattern = "yyyyMMdd";
    SimpleDateFormat tFormat = new SimpleDateFormat(pattern);
    String ret = tFormat.format(new Date()) +
                 "EL1" +
                 String.format("%06d",
                               ++laufendeNummer);

    antrag.setAdsAntragsnummer(ret);
    antrag.setGeVoType(GeVoType.NEU_ANTRAG);

    antrag.setZahlweise(new GsKeyValue("2",
                                       "Jährlich"));
    antrag.setKorrespondenzEmpfaengerType(new GsKeyValue("0",
                                                         "Versicherungsnehmer"));

    response.setAntrag(antrag);

    // createAenderungsgeschaeft();
    // response.setAntrag(antrag01);
    return response;
  }

  @GET
  @Path("/deleteUploadedFile/{vorgangskontextId}/{dit}/{uploadedFileId}")
  public Boolean deleteUploadedFile(@PathParam("vorgangskontextId") String pVkId,
                                    @PathParam("dit") String pDit,
                                    @PathParam("uploadedFileId") String pUploadedFileId) {
    System.out.println("=====");
    System.out.println("deleteUploadedFile");
    System.out.println(" ---> FileId: " + pUploadedFileId);
    System.out.println("=====");
    return true;
  }

  @GET
  @Path("/createNeugeschaeftTAA")
  @Produces("application/json")
  public void createNeugeschaeftTAA() {
    reset();
    // Daten setzen
    vorgangsIdModel = "VgId01Model";

    antrag01 = new Antrag();
    antrag01.setGeVoType(GeVoType.NEU_ANTRAG);
    antrag01.setZahlweise(new GsKeyValue("2",
                                         "Bährlich"));

    setAntragsSteller();
    setVersicherungsnehmer();
    setRisikoOrteNeuGeschaeft();

    this.vkDaten = new VKdaten();
    this.vkDaten.setAntragStatus(AntragStatus.OHNE);
  }

  private void reset() {
    vorgangsIdModel = "VgIdModel";
    vorgangsIdPartner = "vgIdPartner";
    historyToken01 = null;
    historyName01 = null;
    antrag01 = null;
    response01 = null;
    bestandsVertrag = null;
    autoSave01 = false;
    toolTipOfTheDayAlreadyShown = false;
    toolTipOfTheDayShownAt = null;
    vorgangsKontextPartner = null;
    applicationContext = null;
  }

  private void setAntragsSteller() {
    Versicherungsnehmer antragsteller = new Versicherungsnehmer();
    antragsteller.setNatuerlichePerson(true);
    antragsteller.setVorname("Donald");
    antragsteller.setName("Duck");
    antragsteller.getHausAdresse()
                 .setHausnummer("27");
    antragsteller.getHausAdresse()
                 .setStrasse("Kennedy Allee");
    antragsteller.getHausAdresse()
                 .setOrt("Bonn");
    antragsteller.getHausAdresse()
                 .setPlz("53175");
    antragsteller.setTelefon("0221 308115");
    antragsteller.setCrmExternalId("CrmKack01");
    Calendar gebDate = Calendar.getInstance();
    gebDate.set(1980,
                10,
                10);
    antragsteller.setGeburtsdatum(gebDate.getTime());
    antrag01.setAntragsteller(antragsteller);
  }

  private void setVersicherungsnehmer() {
    Versicherungsnehmer versicherungsnehmer = new Versicherungsnehmer();
    versicherungsnehmer.setNatuerlichePerson(true);
    versicherungsnehmer.setVorname("Donald");
    versicherungsnehmer.setName("Duck");
    versicherungsnehmer.getHausAdresse()
                       .setHausnummer("26");
    versicherungsnehmer.getHausAdresse()
                       .setStrasse("Kennedy Allee");
    versicherungsnehmer.getHausAdresse()
                       .setOrt("Bonn");
    versicherungsnehmer.getHausAdresse()
                       .setPlz("53175");
    versicherungsnehmer.setTelefon("0221 308115");
    versicherungsnehmer.setCrmExternalId("CrmKack01");
    Calendar gebDate = Calendar.getInstance();
    gebDate.set(1980,
                10,
                10);
    versicherungsnehmer.setGeburtsdatum(gebDate.getTime());
    antrag01.setAntragsteller(versicherungsnehmer);
  }

  private void setRisikoOrteNeuGeschaeft() {
    RisikoOrt risikoOrt01 = new RisikoOrt();
    risikoOrt01.setId("086892E9-799F-4765-BBA0-3F252FF3BCA2");
    risikoOrt01.setStrasse("Musterstrasse");
    risikoOrt01.setHausnummer("25");
    risikoOrt01.setPlz("42697");
    risikoOrt01.setOrt("Musterstadt");
    risikoOrt01.setGefaehrdungsklasseMaschinell("1");
    risikoOrt01.setLkz("D");
    antrag01.getRisikoOrte()
            .add(risikoOrt01);
  }

  @GET
  @Path("/createNeugeschaeftTR")
  @Produces("application/json")
  public void createNeugeschaeftTR() {
    reset();
    // Daten setzen
    Antrag antrag = new Antrag();
    antrag.setGeVoType(GeVoType.NEU_ANTRAG);

    this.vkDaten = new VKdaten();
    this.vkDaten.setAntragStatus(AntragStatus.OHNE);
  }

  // @GET
  // @Path("/createAenderungsgeschaeft")
  // @Produces("application/json")
  // public void createAenderungsgeschaeft() {
  // reset();
  //
  // vorgangsIdModel = "VgId01Neugeschaeft";
  //
  // antrag01 = new Antrag();
  // antrag01.setGeVoType(GeVoType.AENDERUNGS_ANTRAG);
  // }
  @GET
  @Path("/createAenderungsgeschaeft")
  @Produces("application/json")
  public void createAenderungsgeschaeft() {
    reset();

    vorgangsIdModel = "VgId01AenderugnsGeschaeft";

    antrag01 = new Antrag();
    antrag01.setGeVoType(GeVoType.AENDERUNGS_ANTRAG);
    // antrag01.setAntragStatus(AntragStatus.ANTRAG_IN_ARBEIT);
    antrag01.setAntragsnummer("20160629EL1000001");
    antrag01.setVermittler(new Vermittler("1099802"));

    antrag01.getVorversicherungen()
            .add(setVorversicherung());
    antrag01.getVorschaeden()
            .add(setVorschaden());
    antrag01.setVsnr("4711-12345");

    setAntragsSteller();
    setVersicherungsnehmer();
    setKorrespondenzPartner();

    setVerkaufsProduktGGP();
    setTaetigkeiten();
    setRisikoOrteAenderungsGeschaeft();
    setAllgemeineDaten();
    setBhv(antrag01,
           BearbeitungsStatus.BROWSE);
    setPrivateRisiken(BearbeitungsStatus.BROWSE);
    // setInhaltsVersicherung(BearbeitungsStatus.BROWSE,
    // false);
    // setGebaeudeVersicherung(BearbeitungsStatus.BROWSE,
    // false);
  }

  // private void setBhv(BearbeitungsStatus bearbeitungsStatus) {
  // BetriebsHaftpflichtVersicherung bhv = new BetriebsHaftpflichtVersicherung();
  // bhv.setProdukt(new GsKeyValue("PR_BHV",
  // "Betriebshaftpflicht"));
  // bhv.setBearbeitungsStatus(bearbeitungsStatus);
  // if (BearbeitungsStatus.BROWSE.equals(bearbeitungsStatus)) {
  // antrag01.getBrowsedProducts().add(new ProduktConfiguration(bhv.getProdukt(), new Date()));
  // } else {
  // antrag01.getAddedProducts().add(new ProduktConfiguration(bhv.getProdukt(), new Date()));
  // }
  // BhvTarifkonfiguration config01 = new BhvTarifkonfiguration();
  // config01.setSelbstbeteiligung(new GsKeyValue("0",
  // "Keine Selbstbeteilung"));
  // config01.setDeckungssumme(new GsKeyValue("Cov_CTPL5_GCOM",
  // "5 Mio."));
  // config01.setBruttoBeitrag(new BigDecimal(2586.99));
  //// config01.setPlusBaustein(true);
  //// config01.setPlusBausteinIncluded(true);
  // config01.setSelected(false);
  // bhv.setTarifKonfiguration1(config01);
  //
  // BhvTarifkonfiguration config02 = new BhvTarifkonfiguration();
  // config02.setSelbstbeteiligung(new GsKeyValue("2000",
  // "2000 Euro"));
  // config02.setDeckungssumme(new GsKeyValue("Cov_CTPL10_GCOM",
  // "10 Mio."));
  // config02.setBruttoBeitrag(new BigDecimal(4191.99));
  //// config02.setPlusBaustein(false);
  //// config02.setPlusBausteinIncluded(false);
  // config02.setSelected(true);
  // bhv.setTarifKonfiguration2(config02);
  //
  // antrag01.setBetriebsHaftpflichtVersicherung(bhv);
  // }

  // private void setVerkaufsProdukt() {
  // antrag01.setVerkaufsProdukt(new GsKeyValue("VKP_GEW_PROT",
  // "Gewerbe Police"));
  // }

  private void setKorrespondenzPartner() {
    KorrespondenzEmpfaenger ke = new KorrespondenzEmpfaenger();
    ke.setEmail("Fred.feuerstein@steinburch-ag.de");
    ke.setTelefon("0211 - 123 4711");
    ke.setTyp(PartnerType.BESTANDSKUNDE);
    antrag01.setKorrespondenzEmpfaenger(ke);
    antrag01.setKorrespondenzEmpfaengerType(new GsKeyValue("0",
                                                           "Korespondenzempfaenger VN"));
  }

  private void setTaetigkeiten() {
    List<GsKeyValue> list = new ArrayList<>();
    list.add(new GsKeyValue("4712",
                            "BÃ¤ckerei"));
    list.add(new GsKeyValue("484",
                            "BÃ¤ckereibedarfshandel"));
    list.add(new GsKeyValue("4711",
                            "Metzgerei"));
    antrag01.setTaetigkeiten(new Taetigkeiten(new GsKeyValue("4712",
                                                             "BÃ¤ckerei"),
                                              list));
  }

  @GET
  @Path("/createFreigabeVorbereitenVorgang")
  @Produces("application/json")
  public void createFreigabeVorbereitenVorgang() {
    vorgangsIdModel = "VgId01Neugeschaeft";

    antrag01 = new Antrag();
    antrag01.setGeVoType(GeVoType.NEU_ANTRAG);
    // antrag01.setAntragStatus(AntragStatus.AGENTUR_FREIGABE_ANGEFORDERT);
    antrag01.setAntragsnummer("20160629EL1000001");
    antrag01.setVermittler(new Vermittler("1099802"));

    antrag01.getVorversicherungen()
            .add(setVorversicherung());
    antrag01.getVorschaeden()
            .add(setVorschaden());

    setVerkaufsProduktGGP();
    setTaetigkeitenGGP();
    setRisikoOrteAenderungsGeschaeft();
    setAllgemeineDaten();
    setAussteuerungsGruende();
    setBhv(antrag01,
           BearbeitungsStatus.ADD);
    setPrivateRisiken(BearbeitungsStatus.BROWSE);
    setInhaltsVersicherung(antrag01,
                           BearbeitungsStatus.ADD,
                           false);
    setGebaeudeVersicherung(antrag01,
                            BearbeitungsStatus.ADD,
                            false);
    // setElektronikVersicherung(false);

    this.vkDaten = new VKdaten();
    this.vkDaten.setAntragStatus(AntragStatus.TARIFIERT);
  }

  private Vorversicherung setVorversicherung() {
    Vorversicherung vorversicherung = new Vorversicherung();
    vorversicherung.setUuid("25FB9A7D-8FA2-4B52-B29A-BFA6E7BF0FFE");
    vorversicherung.setProdukt(new GsKeyValue("PR_BHV",
                                              "Betriebshaftpflicht"));
    vorversicherung.setStatus(new GsKeyValue("4",
                                             "(wird) gekündigt durch VN"));
    vorversicherung.setVersicherer(new GsKeyValue("5591",
                                                  "Agrippina Versicherung AG"));
    vorversicherung.setVsnr("471147111256");
    return vorversicherung;
  }

  private Vorschaden setVorschaden() {
    Vorschaden vorschaden = new Vorschaden();
    vorschaden.setProdukt(new GsKeyValue("PR_BHV",
                                         "Betriebshaftpflicht"));
    vorschaden.setUuid("937EA4AD-CFC4-4B44-8257-895BA2D2B56F");
    vorschaden.setAnzahl(2);
    vorschaden.setHoehe(new BigDecimal(225000));
    vorschaden.setKalenderJahr(2014);
    vorschaden.setVorversicherung(setVorversicherung());
    return vorschaden;
  }

  private void setVerkaufsProduktGGP() {
    antrag01.setVerkaufsProdukt(new GsKeyValue("VKP_GEW_PROT",
                                               "Gewerbe Police"));
  }

  private void setTaetigkeitenGGP() {
    List<GsKeyValue> list = new ArrayList<>();
    list.add(new GsKeyValue("4712",
                            "Bäckerei"));
    list.add(new GsKeyValue("484",
                            "Bäckereibedarfshandel"));
    list.add(new GsKeyValue("4711",
                            "Metzgerei"));
    antrag01.setTaetigkeiten(new Taetigkeiten(new GsKeyValue("4712",
                                                             "Bäckerei"),
                                              list));
  }

  private void setRisikoOrteAenderungsGeschaeft() {
    RisikoOrt risikoOrt01 = new RisikoOrt();
    risikoOrt01.setId("086892E9-799F-4765-BBA0-3F252FF3BCA2");
    risikoOrt01.setStrasse("Musterstrasse");
    risikoOrt01.setHausnummer("25");
    risikoOrt01.setPlz("42697");
    risikoOrt01.setOrt("Musterstadt");
    risikoOrt01.setGefaehrdungsklasseMaschinell("1");
    risikoOrt01.setLkz("D");
    risikoOrt01.setEigeneTaetigkeiten(antrag01.getTaetigkeiten()
                                              .getAlleTaetigkeiten());
    antrag01.getRisikoOrte()
            .add(risikoOrt01);

    RisikoOrt risikoOrt02 = new RisikoOrt();
    risikoOrt02.setId("8FEFA1AB-D405-4D7B-84A2-38C30A7387F8");
    risikoOrt02.setStrasse("Teststrasse");
    risikoOrt02.setHausnummer("1");
    risikoOrt02.setPlz("50232");
    risikoOrt02.setOrt("Köln");
    risikoOrt02.setGefaehrdungsklasseMaschinell("1");
    risikoOrt02.setLkz("D");
    risikoOrt02.setEigeneTaetigkeiten(antrag01.getTaetigkeiten()
                                              .getAlleTaetigkeiten());
    risikoOrt02.setGefaehrdungsklasseMaschinell("0");
    antrag01.getRisikoOrte()
            .add(risikoOrt02);
  }

  private void setAllgemeineDaten() {
    Calendar beginn = Calendar.getInstance();
    beginn.set(2017,
               2,
               01);
    antrag01.setVersicherungsBeginn(beginn.getTime());

    Calendar ablauf = Calendar.getInstance();
    ablauf.set(2018,
               6,
               10);
    antrag01.setVersicherungsBeginn(ablauf.getTime());

  }

  private void setAussteuerungsGruende() {
    antrag01.getAveMeldungen()
            .add(new AveMeldung("AG0001",
                                new GsKeyValue("PR_BHV",
                                               "Betriebshaftpflich"),
                                "AG0001",
                                "Vorschäden in Gebaude Leitiungswasser - S-Quote = 33 %"));
    antrag01.getAveMeldungen()
            .add(new AveMeldung("AG0002",
                                null,
                                "AG0002",
                                "Risikofragen nach Sicherungen in der Inhaltsversicherung negativ beantwortet"));
  }

  private void setBhv(Antrag antrag,
                      BearbeitungsStatus bearbeitungsStatus) {
    BetriebsHaftpflichtVersicherung bhv = new BetriebsHaftpflichtVersicherung();
    bhv.setProdukt(new GsKeyValue("PR_BHV",
                                  "Betriebshaftpflicht"));
    bhv.setBearbeitungsStatus(bearbeitungsStatus);
    // bhv.setGenerationswechselMoeglich(true);
    if (BearbeitungsStatus.BROWSE.equals(bearbeitungsStatus)) {
      antrag01.getBrowsedProducts()
              .add(new ProduktConfiguration(bhv.getProdukt(),
                                            antrag.getVersicherungsBeginn()));
    } else {
      antrag01.getAddedProducts()
              .add(new ProduktConfiguration(bhv.getProdukt(),
                                            antrag.getVersicherungsBeginn()));
    }
    BhvTarifkonfiguration config01 = new BhvTarifkonfiguration();
    config01.setSelbstbeteiligung(new GsKeyValue("0",
                                                 "Keine Selbstbeteilung"));
    config01.setDeckungssumme(new GsKeyValue("Cov_CTPL5_GCOM",
                                             "5 Mio."));
    config01.setBruttoBeitrag(new BigDecimal(2586.99));
    List<PlusBaustein> plusBausteine01 = new ArrayList<>();
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         true,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config01.setPlusBausteine(plusBausteine01);
    config01.setPlusBausteinForProductAvailable(true);
    config01.setSelected(false);
    bhv.setTarifKonfiguration1(config01);

    BhvTarifkonfiguration config02 = new BhvTarifkonfiguration();
    config02.setSelbstbeteiligung(new GsKeyValue("2000",
                                                 "2000 Euro"));
    config02.setDeckungssumme(new GsKeyValue("Cov_CTPL10_GCOM",
                                             "10 Mio."));
    config02.setBruttoBeitrag(new BigDecimal(4191.99));
    List<PlusBaustein> plusBausteine02 = new ArrayList<>();
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         true,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config02.setPlusBausteine(plusBausteine02);
    config02.setPlusBausteinForProductAvailable(false);
    config02.setSelected(true);
    bhv.setTarifKonfiguration2(config02);

    antrag01.setBetriebsHaftpflichtVersicherung(bhv);
  }

  private void setPrivateRisiken(BearbeitungsStatus bearbeitungsStatus) {
    PrivateRisiken privateRisiken = new PrivateRisiken();
    privateRisiken.setBearbeitungsStatus(bearbeitungsStatus);
    privateRisiken.setProdukt(new GsKeyValue("PR_PHV",
                                             "Privathaftpflicht"));
    privateRisiken.setAnzahlGeschaeftsfuehrer(0);
    privateRisiken.setAnzahlHunde(1);
    privateRisiken.setAnzahlPferde(2);
    // privateRisiken.getNamenGeschaeftsfueher()
    // .add(new Geschaeftsfuehrer("Fred Feuerstein"));
    PrivateRisikenTarifkonfiguration config01 = new PrivateRisikenTarifkonfiguration();
    config01.setSelbstbeteiligung(new GsKeyValue("0",
                                                 "Keine Selbstbeteilung"));
    config01.setBruttoBeitrag(new BigDecimal(2586.99));
    List<PlusBaustein> plusBausteine01 = new ArrayList<>();
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         true,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    config01.setPlusBausteine(plusBausteine01);
    config01.setPlusBausteinForProductAvailable(true);
    config01.setSelected(false);
    privateRisiken.setTarifKonfiguration1(config01);

    PrivateRisikenTarifkonfiguration config02 = new PrivateRisikenTarifkonfiguration();
    config02.setSelbstbeteiligung(new GsKeyValue("2000",
                                                 "2000 Euro"));
    config02.setBruttoBeitrag(new BigDecimal(4191.99));
    List<PlusBaustein> plusBausteine02 = new ArrayList<>();
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         true,
                                         null));
    config02.setPlusBausteine(plusBausteine02);
    config02.setPlusBausteinForProductAvailable(false);
    config02.setSelected(true);
    privateRisiken.setTarifKonfiguration2(config02);

    antrag01.setPrivateRisiken(privateRisiken);
  }

  private void setInhaltsVersicherung(Antrag antrag,
                                      BearbeitungsStatus bearbeitungsStatus,
                                      boolean hasZeichnungsData) {
    InhaltsVersicherung inhalt = new InhaltsVersicherung(new GsKeyValue("PRGR_INH",
                                                                        "Inhalt"));
    inhalt.setBearbeitungsStatus(bearbeitungsStatus);
    if (BearbeitungsStatus.BROWSE.equals(bearbeitungsStatus)) {
      antrag01.getBrowsedProducts()
              .add(new ProduktConfiguration(inhalt.getProdukt(),
                                            antrag.getVersicherungsBeginn()));
    } else {
      antrag01.getAddedProducts()
              .add(new ProduktConfiguration(inhalt.getProdukt(),
                                            antrag.getVersicherungsBeginn()));
    }
    // inhalt.setNettoJahresBeitrag(new BigDecimal(1886.99));
    inhalt.setFeuer(true);
    inhalt.setSturm(true);
    inhalt.setGlas(false);
    inhalt.setLeitungswasser(true);
    inhalt.setEinbruchDiebstahl(true);
    inhalt.setAnpassungVssu("Nein");

    if (inhalt.getDeckungserweiterung() == null) {
      inhalt.setDeckungserweiterung(new ArrayList<Deckungserweiterung>());
    }
    if (inhalt.getDeckungserweiterung()
              .isEmpty()) {
      Deckungserweiterung tDeckungserweiterung = new Deckungserweiterung();
      inhalt.getDeckungserweiterung()
            .add(tDeckungserweiterung);
    }
    inhalt.getDeckungserweiterung()
          .get(0)
          .setEinschluss(false);
    // inhalt.getDeckungserweiterung()
    // .get(0)
    // .setVsuVorgegeben(true);
    // inhalt.getDeckungserweiterung()
    // .setEinschluss("Nein");
    // inhalt.getDeckungserweiterung()
    // .setVsuVorgegeben(true);

    InhaltTarifkonfiguration config01 = new InhaltTarifkonfiguration();
    config01.setSelbstbeteiligung(new GsKeyValue("0",
                                                 "Keine Selbstbeteilung"));
    config01.setBruttoBeitrag(new BigDecimal(2586.99));
    config01.setSelected(false);
    List<PlusBaustein> plusBausteine01 = new ArrayList<>();
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         true,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config01.setPlusBausteine(plusBausteine01);
    config01.setPlusBausteinForProductAvailable(true);
    inhalt.setTarifKonfiguration1(config01);

    InhaltTarifkonfiguration config02 = new InhaltTarifkonfiguration();
    config02.setSelbstbeteiligung(new GsKeyValue("2000",
                                                 "2000 Euro"));
    config02.setBruttoBeitrag(new BigDecimal(1886.99));
    config02.setSelected(true);
    List<PlusBaustein> plusBausteine02 = new ArrayList<>();
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         true,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config02.setPlusBausteine(plusBausteine02);
    config02.setPlusBausteinForProductAvailable(true);
    inhalt.setTarifKonfiguration2(config02);

    VersichertesGebaeudeInhalt versichertesGebaeudeInhalt01 = new VersichertesGebaeudeInhalt();
    versichertesGebaeudeInhalt01.setId("46DDEF02-6B7C-42F8-B950-C8FB068D6EB1");
    versichertesGebaeudeInhalt01.setIdRisikoort("086892E9-799F-4765-BBA0-3F252FF3BCA2");
    versichertesGebaeudeInhalt01.setVersicherungssumme(new BigDecimal(300000));
    if (hasZeichnungsData) {

    }
    inhalt.getVersicherteGebaeude()
          .add(versichertesGebaeudeInhalt01);

    VersichertesGebaeudeInhalt versichertesGebaeudeInhalt02 = new VersichertesGebaeudeInhalt();
    versichertesGebaeudeInhalt02.setId("2B226E27-C24C-4494-AFAE-A054EEDACCCC");
    versichertesGebaeudeInhalt02.setIdRisikoort("8FEFA1AB-D405-4D7B-84A2-38C30A7387F8");
    versichertesGebaeudeInhalt02.setVersicherungssumme(new BigDecimal(126000));
    if (hasZeichnungsData) {

    }
    inhalt.getVersicherteGebaeude()
          .add(versichertesGebaeudeInhalt02);

    antrag01.setInhaltsVersicherung(inhalt);
  }

  private void setGebaeudeVersicherung(Antrag antrag,
                                       BearbeitungsStatus bearbeitungsStatus,
                                       boolean hasZeichnungsData) {
    GebaeudeVersicherung gebaeude = new GebaeudeVersicherung(new GsKeyValue("PRGR_GEB",
                                                                            "Gebäude"));
    gebaeude.setBearbeitungsStatus(bearbeitungsStatus);
    if (BearbeitungsStatus.BROWSE.equals(bearbeitungsStatus)) {
      antrag01.getBrowsedProducts()
              .add(new ProduktConfiguration(gebaeude.getProdukt(),
                                            antrag.getVersicherungsBeginn()));
    } else {
      antrag01.getAddedProducts()
              .add(new ProduktConfiguration(gebaeude.getProdukt(),
                                            antrag.getVersicherungsBeginn()));
    }
    gebaeude.setFeuer(true);
    gebaeude.setSturm(true);
    gebaeude.setGlas(true);
    gebaeude.setLeitungswasser(true);

    GebaeudeTarifkonfiguration config01 = new GebaeudeTarifkonfiguration();
    config01.setSelbstbeteiligung(new GsKeyValue("0",
                                                 "Keine Selbstbeteilung"));
    config01.setBruttoBeitrag(new BigDecimal(4601.23));
    config01.setSelected(true);
    List<PlusBaustein> plusBausteine01 = new ArrayList<>();
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         true,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config01.setPlusBausteine(plusBausteine01);
    config01.setPlusBausteinForProductAvailable(true);
    gebaeude.setTarifKonfiguration1(config01);

    GebaeudeTarifkonfiguration config02 = new GebaeudeTarifkonfiguration();
    config02.setSelbstbeteiligung(new GsKeyValue("2000",
                                                 "2000 Euro"));
    config02.setBruttoBeitrag(new BigDecimal(3886.99));
    config02.setSelected(false);
    List<PlusBaustein> plusBausteine02 = new ArrayList<>();
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         true,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config02.setPlusBausteine(plusBausteine02);
    config02.setPlusBausteinForProductAvailable(true);
    gebaeude.setTarifKonfiguration2(config02);

    VersichertesGebaeudeGebaeude versichertesGebaeude01 = new VersichertesGebaeudeGebaeude();
    versichertesGebaeude01.setId("8ED4CF62-D264-4FBA-AFAD-42F4535495A8");
    versichertesGebaeude01.setIdRisikoort("086892E9-799F-4765-BBA0-3F252FF3BCA2");
    versichertesGebaeude01.setWertermittlungType(WertermittlungsType.AENDERUNGSANTRAGHINWEIS);

    versichertesGebaeude01.setVersicherungssummeBeantragt(new BigDecimal(9876));

    gebaeude.setVersicherungZum(new GsKeyValue(GebaeudeVersicherung.GEBAEUDE_VERSICHERUNG_ZUM_GLEITENDEN_NEUWERT_KEY,
                                               "gl. Neuwert"));
    if (hasZeichnungsData) {

    }

    gebaeude.getVersicherteGebaeude()
            .add(versichertesGebaeude01);

    antrag01.setGebaeudeVersicherung(gebaeude);
  }

  private List<Risikofrage> getRisikofragenInhaltFeuer(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();

    Risikofrage frage1 = new Risikofrage("RiskQuestion_Inhalt1_SCOM",
                                         "inhSubId1",
                                         produkt,
                                         "Erste _Inhalt-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         new ArrayList<>(Arrays.asList(new GsKeyValue("inhSubId1Msg0101",
                                                                                      "Ein TAA am Morgen vertreibt Kummer und Sorgen"),
                                                                       new GsKeyValue("inhSubId1Msg0102",
                                                                                      "Da fällt mir ja ein Stein von der Platine!"))),
                                         0);
    list.add(frage1);

    Risikofrage frage2 = new Risikofrage("RiskQuestion_Inhalt2_SCOM",
                                         "subid2",
                                         produkt,
                                         "Zweite Gebäude- und Inhalt-Risikofrage",
                                         null,
                                         createSimpsonsList(),
                                         AnswerType.INTEGER,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         1);
    list.add(frage2);

    return list;
  }

  private List<GsKeyValue> createYesNoList() {
    List<GsKeyValue> list = new ArrayList<>();
    list.add(new GsKeyValue(Boolean.TRUE.toString(),
                            "Ja"));
    list.add(new GsKeyValue(Boolean.FALSE.toString(),
                            "Nein"));
    return list;
  }

  private List<GsKeyValue> createSimpsonsList() {
    List<GsKeyValue> list = new ArrayList<>();
    list.add(new GsKeyValue("Ja",
                            "Nein"));
    list.add(new GsKeyValue("Ja",
                            "Ja"));
    list.add(new GsKeyValue("Vielleicht",
                            "Vielleicht"));
    return list;
  }

  private List<Risikofrage> getRisikofragenInhaltSturm(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();

    Risikofrage frage1 = new Risikofrage("RiskQuestion_Inhalt0201_SCOM",
                                         "inhSubId0201",
                                         produkt,
                                         "Erste Inhalt-Sturm-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         new ArrayList<GsKeyValue>(),
                                         0);
    list.add(frage1);

    return list;
  }

  private List<Risikofrage> getRisikofragenInhaltLeitungswasser(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();

    Risikofrage frage1 = new Risikofrage("RiskQuestion_Inhalt0301_SCOM",
                                         "inhSubId0301",
                                         produkt,
                                         "Erste Inhalt-Leitungswasser-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         new ArrayList<>(Arrays.asList(new GsKeyValue("inhSubId0301",
                                                                                      "Verloren einen Planeten Meister Obi-Wan hat. Wie peinlich. Wie peinlich!"))),
                                         0);
    list.add(frage1);

    Risikofrage frage2 = new Risikofrage("RiskQuestion_Inhalt0302_SCOM",
                                         "inhSubId0302",
                                         produkt,
                                         "Zweite Inhalt-Leitungswasser-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         new ArrayList<>(Arrays.asList(new GsKeyValue("inhSubId0302",
                                                                                      "Weisst Du noch, als wir das letzte Mal einen Drink zusammen hatten? Ich hatte einen Milchshake!"))),
                                         1);
    list.add(frage2);

    Risikofrage frage3 = new Risikofrage("RiskQuestion_Inhalt0303_SCOM",
                                         "inhSubId0303",
                                         produkt,
                                         "Dritte Inhalt-Leitungswasser-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         new ArrayList<>(Arrays.asList(new GsKeyValue("inhSubId0303",
                                                                                      "Idioten. Bürokratische Idioten. Die wissen überhaupt nicht was sie da haben."))),
                                         2);
    list.add(frage3);

    return list;
  }

  @GET
  @Path("/createFreigabenVorgangGGP")
  @Produces("application/json")
  public void createFreigabenVorgangGGP() {
    vorgangsIdModel = "VgId01Neugeschaeft";

    antrag01 = new Antrag();
    antrag01.setGeVoType(GeVoType.NEU_ANTRAG);
    antrag01.setAntragsnummer("20160629EL1000001");
    antrag01.setVermittler(new Vermittler("1099802"));
    antrag01.setZahlweise(new GsKeyValue("5",
                                         "monatlich"));

    antrag01.getVorversicherungen()
            .add(setVorversicherung());
    antrag01.getVorschaeden()
            .add(setVorschaden());

    antrag01.getZeichnung()
            .setBegruendung("Bli Bla Blub");

    setVerkaufsProduktGGP();
    setTaetigkeitenGGP();
    setRisikoOrteAenderungsGeschaeft();
    setAllgemeineDaten();
    setAussteuerungsGruende();
    setBhv(antrag01,
           BearbeitungsStatus.ADD);
    setPrivateRisiken(BearbeitungsStatus.BROWSE);
    setInhaltsVersicherung(antrag01,
                           BearbeitungsStatus.ADD,
                           false);
    setGebaeudeVersicherung(antrag01,
                            BearbeitungsStatus.ADD,
                            false);
    setWerkverkehrversicherung(antrag01,
                               BearbeitungsStatus.ADD);
    setBetriebsschliessungVersicherung(antrag01,
                                       BearbeitungsStatus.ADD);
    setElektronikVersicherung(false);
    setPhotovoltaikVersicherung(false);
    setMaschinenFahrbarVersicherung(antrag01,
                                    BearbeitungsStatus.ADD);
    setMaschinenStationaerVersicherung(false);

    this.vkDaten = new VKdaten();
    this.vkDaten.setAntragStatus(AntragStatus.FACHLICHE_FREIGABE_ANGEFORDERT);
  }

  private void setWerkverkehrversicherung(Antrag antrag,
                                          BearbeitungsStatus bearbeitungsStatus) {
    WerkverkehrVersicherung werkverkehrVersicherung = new WerkverkehrVersicherung();
    werkverkehrVersicherung.setProdukt(new GsKeyValue("PR_WERK_VERK",
                                                      "Werkverkehr"));
    werkverkehrVersicherung.setBearbeitungsStatus(bearbeitungsStatus);
    if (BearbeitungsStatus.BROWSE.equals(bearbeitungsStatus)) {
      this.antrag01.getBrowsedProducts()
                   .add(new ProduktConfiguration(werkverkehrVersicherung.getProdukt(),
                                                 antrag.getVersicherungsBeginn()));
    } else {
      this.antrag01.getAddedProducts()
                   .add(new ProduktConfiguration(werkverkehrVersicherung.getProdukt(),
                                                 antrag.getVersicherungsBeginn()));
    }
    WerkverkehrTarifkonfiguration config01 = new WerkverkehrTarifkonfiguration();
    config01.setSelbstbeteiligung(new GsKeyValue("0",
                                                 "Keine Selbstbeteilung"));
    config01.setBruttoBeitrag(new BigDecimal(2586.99));
    List<PlusBaustein> plusBausteine01 = new ArrayList<>();
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         true,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config01.setPlusBausteine(plusBausteine01);
    config01.setPlusBausteinForProductAvailable(true);
    config01.setSelected(false);
    werkverkehrVersicherung.setTarifKonfiguration1(config01);

    WerkverkehrTarifkonfiguration config02 = new WerkverkehrTarifkonfiguration();
    config02.setSelbstbeteiligung(new GsKeyValue("2000",
                                                 "2000 Euro"));
    config02.setBruttoBeitrag(new BigDecimal(4191.99));
    List<PlusBaustein> plusBausteine02 = new ArrayList<>();
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         true,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config02.setPlusBausteine(plusBausteine02);
    config02.setPlusBausteinForProductAvailable(false);
    config02.setSelected(true);
    werkverkehrVersicherung.setTarifKonfiguration2(config02);

    this.antrag01.setWerkverkehrVersicherung(werkverkehrVersicherung);
  }

  private void setBetriebsschliessungVersicherung(Antrag antrag,
                                                  BearbeitungsStatus bearbeitungsStatus) {
    BetriebsschliessungVersicherung betriebsschliessungVersicherung = new BetriebsschliessungVersicherung(new GsKeyValue("PR_BETRIEBSSCHLIESSUNG",
                                                                                                                         "Betriebsschliessung"));
    betriebsschliessungVersicherung.setBearbeitungsStatus(bearbeitungsStatus);
    if (BearbeitungsStatus.BROWSE.equals(bearbeitungsStatus)) {
      this.antrag01.getBrowsedProducts()
                   .add(new ProduktConfiguration(betriebsschliessungVersicherung.getProdukt(),
                                                 antrag.getVersicherungsBeginn()));
    } else {
      this.antrag01.getAddedProducts()
                   .add(new ProduktConfiguration(betriebsschliessungVersicherung.getProdukt(),
                                                 antrag.getVersicherungsBeginn()));
    }
    BetriebsschliessungTarifkonfiguration config01 = new BetriebsschliessungTarifkonfiguration();
    config01.setSelbstbeteiligung(new GsKeyValue("0",
                                                 "Keine Selbstbeteilung"));
    config01.setBruttoBeitrag(new BigDecimal(2586.99));
    config01.setSelected(false);
    List<PlusBaustein> plusBausteine01 = new ArrayList<>();
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         true,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config01.setPlusBausteine(plusBausteine01);
    config01.setPlusBausteinForProductAvailable(true);
    betriebsschliessungVersicherung.setTarifKonfiguration1(config01);

    BetriebsschliessungTarifkonfiguration config02 = new BetriebsschliessungTarifkonfiguration();
    config02.setSelbstbeteiligung(new GsKeyValue("2000",
                                                 "2000 Euro"));
    config02.setBruttoBeitrag(new BigDecimal(1886.99));
    config02.setSelected(true);
    List<PlusBaustein> plusBausteine02 = new ArrayList<>();
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         true,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config02.setPlusBausteine(plusBausteine02);
    config02.setPlusBausteinForProductAvailable(true);
    betriebsschliessungVersicherung.setTarifKonfiguration2(config02);

    VersicherterRisikoortBetriebsschliessung risikoortBetriebsschliessung01 = new VersicherterRisikoortBetriebsschliessung();
    risikoortBetriebsschliessung01.setId("46DDEF02-6B7C-42F8-B950-C8FB068D6EB1");
    risikoortBetriebsschliessung01.setIdRisikoort("086892E9-799F-4765-BBA0-3F252FF3BCA2");
    risikoortBetriebsschliessung01.setVersicherungssumme(new BigDecimal(300000));
    betriebsschliessungVersicherung.getRisikoorteList()
                                   .add(risikoortBetriebsschliessung01);

    VersicherterRisikoortBetriebsschliessung risikoortBetriebsschliessung02 = new VersicherterRisikoortBetriebsschliessung();
    risikoortBetriebsschliessung02.setId("2B226E27-C24C-4494-AFAE-A054EEDACCCC");
    risikoortBetriebsschliessung02.setIdRisikoort("8FEFA1AB-D405-4D7B-84A2-38C30A7387F8");
    risikoortBetriebsschliessung02.setVersicherungssumme(new BigDecimal(126000));
    betriebsschliessungVersicherung.getRisikoorteList()
                                   .add(risikoortBetriebsschliessung02);

    this.antrag01.setBetriebsschliessungVersicherung(betriebsschliessungVersicherung);
  }

  private void setElektronikVersicherung(boolean hasZeichnungsData) {
    ElektronikVersicherung tElektro = new ElektronikVersicherung(new GsKeyValue(TAA.ELEKTRONIK,
                                                                                "Elektronik"));
    tElektro.setBearbeitungsStatus(AbstractProdukt.BearbeitungsStatus.ADD);

    ElektronikTarifkonfiguration config01 = new ElektronikTarifkonfiguration();
    config01.setSelbstbeteiligung(new GsKeyValue("0",
                                                 "Keine Selbstbeteilung"));
    config01.setBruttoBeitrag(new BigDecimal(2586.99));
    config01.setSelected(false);
    List<PlusBaustein> plusBausteine01 = new ArrayList<>();
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         true,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config01.setPlusBausteine(plusBausteine01);
    config01.setPlusBausteinForProductAvailable(true);
    tElektro.setTarifKonfiguration1(config01);

    ElektronikTarifkonfiguration config02 = new ElektronikTarifkonfiguration();
    config02.setSelbstbeteiligung(new GsKeyValue("2000",
                                                 "2000 Euro"));
    config02.setBruttoBeitrag(new BigDecimal(1886.99));
    config02.setSelected(true);
    List<PlusBaustein> plusBausteine02 = new ArrayList<>();
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         true,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config02.setPlusBausteine(plusBausteine02);
    config02.setPlusBausteinForProductAvailable(true);
    tElektro.setTarifKonfiguration2(config02);

    VersichertesGebaeudeElektronikMaschinenPhotovoltaik versichertesGebaeude01 = new VersichertesGebaeudeElektronikMaschinenPhotovoltaik();
    versichertesGebaeude01.setId("46DDEF02-6B7C-42F8-B950-C8FB068D6EB1");
    versichertesGebaeude01.setIdRisikoort("086892E9-799F-4765-BBA0-3F252FF3BCA2");
    tElektro.getVersicherteGebaeude()
            .add(versichertesGebaeude01);

    VersichertesGebaeudeElektronikMaschinenPhotovoltaik versichertesGebaeude02 = new VersichertesGebaeudeElektronikMaschinenPhotovoltaik();
    versichertesGebaeude02.setId("2B226E27-C24C-4494-AFAE-A054EEDACCCC");
    versichertesGebaeude02.setIdRisikoort("8FEFA1AB-D405-4D7B-84A2-38C30A7387F8");
    if (hasZeichnungsData) {

    }
    tElektro.getVersicherteGebaeude()
            .add(versichertesGebaeude02);

    antrag01.setElektronikVersicherung(tElektro);
  }

  private void setPhotovoltaikVersicherung(boolean hasZeichnungsData) {
    PhotovoltaikVersicherung versicherung = new PhotovoltaikVersicherung(new GsKeyValue(TAA.PHOTOVOLTAIK,
                                                                                        "Photovoltaik"));
    versicherung.setBearbeitungsStatus(AbstractProdukt.BearbeitungsStatus.ADD);

    PhotovoltaikTarifkonfiguration config01 = new PhotovoltaikTarifkonfiguration();
    config01.setSelbstbeteiligung(new GsKeyValue("0",
                                                 "Keine Selbstbeteilung"));
    config01.setBruttoBeitrag(new BigDecimal(2586.99));
    config01.setSelected(false);
    List<PlusBaustein> plusBausteine01 = new ArrayList<>();
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         true,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config01.setPlusBausteine(plusBausteine01);
    config01.setPlusBausteinForProductAvailable(true);
    versicherung.setTarifKonfiguration1(config01);

    PhotovoltaikTarifkonfiguration config02 = new PhotovoltaikTarifkonfiguration();
    config02.setSelbstbeteiligung(new GsKeyValue("2000",
                                                 "2000 Euro"));
    config02.setBruttoBeitrag(new BigDecimal(1886.99));
    config02.setSelected(true);
    List<PlusBaustein> plusBausteine02 = new ArrayList<>();
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         true,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config02.setPlusBausteine(plusBausteine02);
    config02.setPlusBausteinForProductAvailable(true);
    versicherung.setTarifKonfiguration2(config02);

    VersichertesGebaeudeElektronikMaschinenPhotovoltaik versichertesGebaeude01 = new VersichertesGebaeudeElektronikMaschinenPhotovoltaik();
    versichertesGebaeude01.setId("46DDEF02-6B7C-42F8-B950-C8FB068D6EB1");
    versichertesGebaeude01.setIdRisikoort("086892E9-799F-4765-BBA0-3F252FF3BCA2");
    versicherung.getVersicherteGebaeude()
                .add(versichertesGebaeude01);

    VersichertesGebaeudeElektronikMaschinenPhotovoltaik versichertesGebaeude02 = new VersichertesGebaeudeElektronikMaschinenPhotovoltaik();
    versichertesGebaeude02.setId("2B226E27-C24C-4494-AFAE-A054EEDACCCC");
    versichertesGebaeude02.setIdRisikoort("8FEFA1AB-D405-4D7B-84A2-38C30A7387F8");
    if (hasZeichnungsData) {

    }
    versicherung.getVersicherteGebaeude()
                .add(versichertesGebaeude02);

    antrag01.setPhotovoltaikVersicherung(versicherung);
  }

  private void setMaschinenFahrbarVersicherung(Antrag antrag,
                                               BearbeitungsStatus bearbeitungsStatus) {
    MaschinenFahrbarVersicherung versicherung = new MaschinenFahrbarVersicherung();
    versicherung.setProdukt(new GsKeyValue(TAA.MASCHINEN_FAHRBAR,
                                           "Maschinen Fahrbar"));
    versicherung.setBearbeitungsStatus(bearbeitungsStatus);
    if (BearbeitungsStatus.BROWSE.equals(bearbeitungsStatus)) {
      this.antrag01.getBrowsedProducts()
                   .add(new ProduktConfiguration(versicherung.getProdukt(),
                                                 antrag.getVersicherungsBeginn()));
    } else {
      this.antrag01.getAddedProducts()
                   .add(new ProduktConfiguration(versicherung.getProdukt(),
                                                 antrag.getVersicherungsBeginn()));
    }
    MaschinenFahrbarTarifkonfiguration config01 = new MaschinenFahrbarTarifkonfiguration();
    config01.setSelbstbeteiligung(new GsKeyValue("0",
                                                 "Keine Selbstbeteilung"));
    config01.setBruttoBeitrag(new BigDecimal(2586.99));
    List<PlusBaustein> plusBausteine01 = new ArrayList<>();
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         true,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config01.setPlusBausteine(plusBausteine01);
    config01.setPlusBausteinForProductAvailable(true);
    config01.setSelected(false);
    versicherung.setTarifKonfiguration1(config01);

    MaschinenFahrbarTarifkonfiguration config02 = new MaschinenFahrbarTarifkonfiguration();
    config02.setSelbstbeteiligung(new GsKeyValue("2000",
                                                 "2000 Euro"));
    config02.setBruttoBeitrag(new BigDecimal(4191.99));
    List<PlusBaustein> plusBausteine02 = new ArrayList<>();
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         true,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config02.setPlusBausteine(plusBausteine02);
    config02.setPlusBausteinForProductAvailable(false);
    config02.setSelected(true);
    versicherung.setTarifKonfiguration2(config02);

    this.antrag01.setMaschinenFahrbarVersicherung(versicherung);
  }

  private void setMaschinenStationaerVersicherung(boolean hasZeichnungsData) {
    MaschinenStationaerVersicherung versicherung = new MaschinenStationaerVersicherung(new GsKeyValue(TAA.MASCHINEN_STATIONAER,
                                                                                                      "Maschinen Stationär"));
    versicherung.setBearbeitungsStatus(AbstractProdukt.BearbeitungsStatus.ADD);

    MaschinenStationaerTarifkonfiguration config01 = new MaschinenStationaerTarifkonfiguration();
    config01.setSelbstbeteiligung(new GsKeyValue("0",
                                                 "Keine Selbstbeteilung"));
    config01.setBruttoBeitrag(new BigDecimal(2586.99));
    config01.setSelected(false);
    List<PlusBaustein> plusBausteine01 = new ArrayList<>();
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         true,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config01.setPlusBausteine(plusBausteine01);
    config01.setPlusBausteinForProductAvailable(true);
    versicherung.setTarifKonfiguration1(config01);

    MaschinenStationaerTarifkonfiguration config02 = new MaschinenStationaerTarifkonfiguration();
    config02.setSelbstbeteiligung(new GsKeyValue("2000",
                                                 "2000 Euro"));
    config02.setBruttoBeitrag(new BigDecimal(1886.99));
    config02.setSelected(true);
    List<PlusBaustein> plusBausteine02 = new ArrayList<>();
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         true,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config02.setPlusBausteine(plusBausteine02);
    config02.setPlusBausteinForProductAvailable(true);
    versicherung.setTarifKonfiguration2(config02);

    VersichertesGebaeudeElektronikMaschinenPhotovoltaik versichertesGebaeude01 = new VersichertesGebaeudeElektronikMaschinenPhotovoltaik();
    versichertesGebaeude01.setId("46DDEF02-6B7C-42F8-B950-C8FB068D6EB1");
    versichertesGebaeude01.setIdRisikoort("086892E9-799F-4765-BBA0-3F252FF3BCA2");
    versicherung.getVersicherteGebaeude()
                .add(versichertesGebaeude01);

    VersichertesGebaeudeElektronikMaschinenPhotovoltaik versichertesGebaeude02 = new VersichertesGebaeudeElektronikMaschinenPhotovoltaik();
    versichertesGebaeude02.setId("2B226E27-C24C-4494-AFAE-A054EEDACCCC");
    versichertesGebaeude02.setIdRisikoort("8FEFA1AB-D405-4D7B-84A2-38C30A7387F8");
    if (hasZeichnungsData) {

    }
    versicherung.getVersicherteGebaeude()
                .add(versichertesGebaeude02);

    antrag01.setMaschinenStationaerVersicherung(versicherung);
  }

  @GET
  @Path("/createFreigabenVorgangVSH")
  @Produces("application/json")
  public void createFreigabenVorgangVSH() {
    vorgangsIdModel = "VgId01Neugeschaeft";

    antrag01 = new Antrag();
    antrag01.setGeVoType(GeVoType.NEU_ANTRAG);
    antrag01.setAntragsnummer("20160629EL1000001");
    antrag01.setVermittler(new Vermittler("1099802"));
    antrag01.setZahlweise(new GsKeyValue("5",
                                         "monatlich"));

    antrag01.getVorversicherungen()
            .add(setVorversicherung());
    antrag01.getVorschaeden()
            .add(setVorschaden());

    antrag01.getZeichnung()
            .setBegruendung("Bli Bla Blub");

    setVerkaufsProduktVSH();
    setTaetigkeitenVSH();
    setAllgemeineDaten();
    setAussteuerungsGruende();
    setVSH(antrag01,
           BearbeitungsStatus.ADD);

    this.vkDaten = new VKdaten();
    this.vkDaten.setAntragStatus(AntragStatus.FACHLICHE_FREIGABE_ANGEFORDERT);
  }

  private void setVerkaufsProduktVSH() {
    antrag01.setVerkaufsProdukt(new GsKeyValue("VKP_VSH",
                                               "Gewerbe VSH Police"));
  }

  private void setTaetigkeitenVSH() {
    List<GsKeyValue> list = new ArrayList<>();
    list.add(new GsKeyValue("4444",
                            "Steuerberater"));
    antrag01.setTaetigkeiten(new Taetigkeiten(new GsKeyValue("4444",
                                                             "Steuerberater"),
                                              list));
  }

  private void setVSH(Antrag antrag,
                      BearbeitungsStatus bearbeitungsStatus) {
    Vermoegensschadenhaftpflicht vsh = new Vermoegensschadenhaftpflicht();
    vsh.setProdukt(new GsKeyValue("PR_VSH_IDV",
                                  "VSH"));
    vsh.setBearbeitungsStatus(bearbeitungsStatus);
    if (BearbeitungsStatus.BROWSE.equals(bearbeitungsStatus)) {
      this.antrag01.getBrowsedProducts()
                   .add(new ProduktConfiguration(vsh.getProdukt(),
                                                 antrag.getVersicherungsBeginn()));
    } else {
      this.antrag01.getAddedProducts()
                   .add(new ProduktConfiguration(vsh.getProdukt(),
                                                 antrag.getVersicherungsBeginn()));
    }
    VermoegensschadenTarifkonfiguration config01 = new VermoegensschadenTarifkonfiguration();
    config01.setSelbstbeteiligung(new GsKeyValue("0",
                                                 "Keine Selbstbeteilung"));
    config01.setDeckungssumme(new GsKeyValue("Cov_CTPL5_GCOM",
                                             "5 Mio."));
    config01.setBruttoBeitrag(new BigDecimal(2586.99));
    List<PlusBaustein> plusBausteine01 = new ArrayList<>();
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         true,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine01.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config01.setPlusBausteine(plusBausteine01);
    config01.setPlusBausteinForProductAvailable(true);
    config01.setSelected(false);
    vsh.setTarifKonfiguration1(config01);

    VermoegensschadenTarifkonfiguration config02 = new VermoegensschadenTarifkonfiguration();
    config02.setSelbstbeteiligung(new GsKeyValue("2000",
                                                 "2000 Euro"));
    config02.setDeckungssumme(new GsKeyValue("Cov_CTPL10_GCOM",
                                             "10 Mio."));
    config02.setBruttoBeitrag(new BigDecimal(4191.99));
    List<PlusBaustein> plusBausteine02 = new ArrayList<>();
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         true,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         null));
    plusBausteine02.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         null));
    config02.setPlusBausteine(plusBausteine02);
    config02.setPlusBausteinForProductAvailable(false);
    config02.setSelected(true);
    vsh.setTarifKonfiguration2(config02);

    this.antrag01.setVermoegensschadenhaftpflichtVersicherung(vsh);
  }

  @GET
  @Path("/getVkdaten/{vorgangskontextId}")
  public TaaVorgangskontextResponse getVkdaten(@PathParam("vorgangskontextId") String pVkId) {
    TaaVorgangskontextResponse response = new TaaVorgangskontextResponse();
    response.setVkdaten(new VKdaten());
    response.getVkdaten()
            .setAntragStatus(this.antragsSatus);
    return response;
  }

  /**
   * Diese Methode wird im Falle des Tarifrechners aufgerufen.
   *
   * @return
   */
  @GET
  @Path("/create")
  // @Produces("application/text")
  public TaaVorgangskontextResponse create() {
    TaaVorgangskontextResponse neugeschaeftTR = createVorgangskontextNeugeschaeftTR();

    VKdaten tVKDaten = new VKdaten();
    tVKDaten.setAntragStatus(AntragStatus.OHNE);

    // TaaVorgangskontextResponse tRes = new TaaVorgangskontextResponse();
    Status tStatus = new Status();
    tStatus.setReturncode(ReturnCode.OK);

    neugeschaeftTR.setVkdaten(tVKDaten);
    neugeschaeftTR.setVorgangsIdAntrag("vgKo01Tarifrechner");

    neugeschaeftTR.setStatus(tStatus);
    Antrag antrag = new Antrag();

    String pattern = "yyyyMMdd";
    SimpleDateFormat tFormat = new SimpleDateFormat(pattern);
    String ret = tFormat.format(new Date()) +
                 "EL1" +
                 String.format("%06d",
                               ++laufendeNummer);
    antrag.setVersicherungsBeginn(new Date());
    antrag.setAdsAntragsnummer(ret);
    antrag.setGeVoType(GeVoType.NEU_ANTRAG);

    antrag.setZahlweise(new GsKeyValue("2",
                                       "Jährlich"));
    antrag.setKorrespondenzEmpfaengerType(new GsKeyValue("0",
                                                         "Versicherungsnehmer"));

    antrag.getIndividualisierung()
          .setAntragIndividualisierbar(true);

    neugeschaeftTR.setAntrag(antrag);

    return neugeschaeftTR;
  }

  private TaaVorgangskontextResponse createVorgangskontextNeugeschaeftTR() {
    response01 = new TaaVorgangskontextResponse();
    response01.setVorgangsIdAntrag("vgkoId01Tarfirechner");
    // bei einem Initeialen Aufruf aus dem CRM ist das nicht gefuellt
    // response01.setHistoryName("R2D2");
    // response01.setHistoryToken("dmdLbzAxKyEhK3ZnS28wMg==");
    response01.setAutoSave(false);
    Antrag antrag = new Antrag();

    Vermittler v = new Vermittler();
    v.setAllisnummer("0000072");
    v.setVdAgt("01023");
    v.setInkassoArt("inkassoArt");
    v.setVertragspartner("vertragspartner");

    antrag.setVermittler(v);

    antrag.setGeVoType(GeVoType.NEU_ANTRAG);
    response01.setAntrag(antrag);
    return response01;
  }

  @POST
  @Path("/save")
  @Consumes("application/json")
  @Produces("application/json")
  public TaaVorgangskontextResponse save(TaaVorgangskontextRequest model) {
    TaaVorgangskontextResponse tResponse = new TaaVorgangskontextResponse();
    tResponse.setStatus(new Status());
    tResponse.getStatus()
             .setReturncode(ReturnCode.OK);

    vorgangsIdModel = model.getVorgangsIdAntrag();
    historyName01 = model.getHistoryName();
    historyToken01 = model.getHistoryToken();
    antrag01 = model.getAntrag();
    bestandsVertrag = model.getBestandsAntrag();
    autoSave01 = model.isAutoSave();
    this.toolTipOfTheDayAlreadyShown = model.isToolTipOfTheDayAlreadyShown();
    this.toolTipOfTheDayShownAt = model.getToolTipOfTheDayShownAt();

    VKdaten tVKdaten = new VKdaten();
    // if (AntragStatus.OHNE.equals(tMetadaten.getAntragStatus())) {
    Versicherungsnehmer tVn = model.getAntrag()
                                   .getAntragsteller();

    // Wenn wir einen Partner haben, sind wir schon im Status ANTRAG_IN_ARBEIT
    if (tVn != null && !tVn.isEmpty()) {
      tVKdaten.setAntragStatus(AntragStatus.ANTRAG_IN_ARBEIT);
    } else {
      tVKdaten.setAntragStatus(AntragStatus.IN_ARBEIT_TARIFIERT);
    }
    // updateCrmStatus(tVKDaten, tMetadaten, false);
    // }

    System.out.println("=====");
    System.out.println("Persist Vorgangskontext");
    System.out.println(" ---> ApplicationContext: " + applicationContext);
    // System.out.println("Verkaufsprodukt -> " + model.getTaaModel().getVerkaufsProdukt() == null ? "n/a" : model.getTaaModel().getVerkaufsProdukt());
    // System.out.println("Keywords -> " + model.getTaaModel().getKeywords() == null || model.getTaaModel().getKeywords().getMainKeyword() == null ? "n/a" :
    // model.getTaaModel().getKeywords().getMainKeyword().getValue());
    // System.out.println("# of selected products -> " + response01.getTaaModel().getSelectedProdukts() == null ? "n/a" : response01.getTaaModel().getSelectedProdukts().size());
    System.out.println("=====");

    tResponse.setVkdaten(tVKdaten);
    tResponse.setAntrag(antrag01);
    tResponse.setTrLinkUrl("http://localhost:8080/app/TAAGewerbe/TRGewerbe.html" + "#" + historyName01 + "?" + historyToken01);

    return tResponse;
  }

  private TaaVorgangskontextResponse createVorgangskontextAenderungsgeschaeft(String vorgangsKontextId01,
                                                                              String vsnr) {
    response01 = new TaaVorgangskontextResponse();
    response01.setVorgangsIdAntrag(vorgangsKontextId01);
    // bei einem Initeialen Aufruf aus dem CRM ist das nicht gefuellt
    // response01.setHistoryName("R2D2");
    // response01.setHistoryToken("dmdLbzAxKyEhK3ZnS28wMg==");
    response01.setAutoSave(false);
    Antrag antrag = new Antrag();
    antrag.setGeVoType(GeVoType.AENDERUNGS_ANTRAG);
    antrag.setVsnr(vsnr);
    antrag.setBeitragJaehrlich(new BigDecimal(1200));
    antrag.setBeitragMonatlich(new BigDecimal(100));
    antrag.setBeitragHalbJaehrlich(new BigDecimal(600));
    antrag.setBeitragViertelJaehrlich(new BigDecimal(300));
    antrag.setZahlweise(new GsKeyValue("2",
                                       "Jährlich"));
    response01.setAntrag(antrag);
    return response01;
  }

  private TaaVorgangskontextResponse createVorgangskontextNeugeschaeftTAA(String vorgangsKontextId01,
                                                                          String vsnr) {
    response01 = new TaaVorgangskontextResponse();
    response01.setVorgangsIdAntrag(vorgangsKontextId01);
    // bei einem Initeialen Aufruf aus dem CRM ist das nicht gefuellt
    // response01.setHistoryName("R2D2");
    // response01.setHistoryToken("dmdLbzAxKyEhK3ZnS28wMg==");
    response01.setAutoSave(false);
    Antrag antrag = new Antrag();
    antrag.setGeVoType(GeVoType.NEU_ANTRAG);
    response01.setAntrag(antrag);
    return response01;
  }

  private void createVorgangsKontextPartner() {
    Antrag antrag = new Antrag();
    // taaModel.setBeitragsZahler(new Beitragszahler());
    // taaModel.setVersicherungsNehmer(new Versicherungsnehmer());

    vorgangsKontextPartner = new TaaVorgangskontextResponse();
    vorgangsKontextPartner.setVorgangsIdAntrag("vgKo02yEhK1ZnSWROb3R");
    vorgangsKontextPartner.setHistoryName("R2D2");
    vorgangsKontextPartner.setHistoryToken("dmdLbzAxKyEhK3ZnS28wMg==");
    response01.setAntrag(antrag);
  }

  enum MODEL_TYP {
    CLIENT,
    GSM
  }

}
