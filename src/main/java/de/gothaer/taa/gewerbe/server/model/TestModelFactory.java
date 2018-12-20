package de.gothaer.taa.gewerbe.server.model;

import de.gothaer.gwt.gxt.config.shared.model.GsKeyValue;
import de.gothaer.gwt.gxt.config.shared.model.GsToolTip;
import de.gothaer.taa.gewerbe.ui.shared.s2.GUID;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.*;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.AbstractProdukt;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.AbstractProdukt.BearbeitungsStatus;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.TAA;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.alle.Deckungserweiterung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.alle.PlusBaustein;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.alle.TaetigkeitenUndVsu;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.alle.VersichertesGebaeudeElektronikMaschinenPhotovoltaik;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.bhv.BetriebsHaftpflichtVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.bhv.BhvTarifkonfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.bhv.BhvWagnis;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.elektronik.ElektronikTarifkonfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.elektronik.ElektronikVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.gebaeude.GebaeudeTarifkonfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.gebaeude.GebaeudeVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.gebaeude.VersichertesGebaeudeGebaeude;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.inhaltsversicherung.InhaltTarifkonfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.inhaltsversicherung.InhaltsVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.inhaltsversicherung.VersichertesGebaeudeInhalt;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.privat.PrivateRisiken;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.vsh.Vermoegensschadenhaftpflicht;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.staticEnums.PartnerType;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.staticEnums.WertermittlungsType;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestModelFactory {

  private static final GsKeyValue KEINEAUSWAHL = new GsKeyValue("0",
                                                                "keine Auswahl");
  private static final GsKeyValue PLUS1        = new GsKeyValue("1",
                                                                "Plusbaustein 1");

  public static String GEFAHRENKONSTELLATION_FEUER                         = "1000000";
  public static String GEFAHRENKONSTELLATION_STURM                         = "0100000";
  public static String GEFAHRENKONSTELLATION_WASSER                        = "0010000";
  public static String GEFAHRENKONSTELLATION_GLAS                          = "0001000";
  public static String GEFAHRENKONSTELLATION_ERWEITERTE_DECKUNG            = "0000100";
  public static String GEFAHRENKONSTELLATION_ELEMENTAR                     = "1000010";
  public static String GEFAHRENKONSTELLATION_UNBENANNTE_GEFAHREN           = "0000001";
  public static String GEFAHRENKONSTELLATION_FEUER_UND_WASSER              = "1010000";
  public static String GEFAHRENKONSTELLATION_FEUER_UND_GLAS                = "1001000";
  public static String GEFAHRENKONSTELLATION_FEUER_UND_ERWEITERTE_DECKUNG  = "1000100";
  public static String GEFAHRENKONSTELLATION_FEUER_UND_ELEMENTAR           = "1000010";
  public static String GEFAHRENKONSTELLATION_FEUER_UND_UNBENANNTE_GEFAHREN = "1000001";

  public static Antrag createBHVAntragEinWagnis2(boolean pSample,
                                                 String pSelbstbeteiligung,
                                                 String pDeckungssumme,
                                                 String plusBaustein) {
    Antrag tAntrag = new Antrag();
    tAntrag = initAntrag(pSample,
                         tAntrag);

    BetriebsHaftpflichtVersicherung tBHV = new BetriebsHaftpflichtVersicherung(new GsKeyValue("PR_BHV",
                                                                                              "Betriebshaftpflicht"));

    BhvWagnis tWagnis = new BhvWagnis();
    Taetigkeiten tTaetigkeiten = new Taetigkeiten();
    if (pSample) {
      tTaetigkeiten.setHauptTaetigkeit(new GsKeyValue("1069",
                                                      "Buchhandel"));
    } else {
      tTaetigkeiten.setHauptTaetigkeit(new GsKeyValue("6400",
                                                      "Akustiker"));
    }

    tWagnis.setTaetigkeiten(tTaetigkeiten);
    tWagnis.setBbrGruppe("127");
    tWagnis.setJahresumsatz(new BigDecimal("500000"));
    tWagnis.setBbrVertraeglich(true);
    tWagnis.getDynamischeAttribute()
           .add(new PMAttribut(GUID.get(),
                               "Dynamisches Attribute 01",
                               PMAttribut.Type.TEXT_FIELD));
    tWagnis.getDynamischeAttribute()
           .add(new PMAttribut(GUID.get(),
                               "Dynamisches Attribute 02",
                               PMAttribut.Type.TEXT_FIELD));
    tWagnis.getDynamischeAttribute()
           .add(new PMAttribut(GUID.get(),
                               "Dynamisches Attribute 03",
                               PMAttribut.Type.TEXT_FIELD));
    tBHV.getWagnisse()
        .add(tWagnis);
    tBHV.setTarifKonfiguration1(createBHVTarifkonfiguration(pSelbstbeteiligung,
                                                            pDeckungssumme,
                                                            plusBaustein,
                                                            true));
    tBHV.setTarifKonfiguration2(createBHVTarifkonfiguration(pSelbstbeteiligung,
                                                            pDeckungssumme,
                                                            plusBaustein,
                                                            false));
    tAntrag.setBetriebsHaftpflichtVersicherung(tBHV);
    return tAntrag;
  }

  private static Antrag initAntrag(boolean pSample,
                                   Antrag tAntrag) {
    tAntrag.setAntragsnummer(createAntragsnummer());
    tAntrag.setVerkaufsProdukt(new GsKeyValue("VKP_GEW_PROT",
                                              "Gewerbe Police"));
    tAntrag.setVersicherungsBeginn(new Date());
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.YEAR,
                 2);
    tAntrag.setVersicherungsAblauf(calendar.getTime());
    if (pSample) {
      tAntrag.getTaetigkeiten()
             .setHauptTaetigkeit(new GsKeyValue("1069",
                                                "Buchhandel"));
      tAntrag.getTaetigkeiten()
             .getAlleTaetigkeiten()
             .add(new GsKeyValue("1069",
                                 "Buchhandel"));
      tAntrag.getTaetigkeiten()
             .getAlleTaetigkeiten()
             .add(new GsKeyValue("1165",
                                 "Catering"));
      tAntrag.getTaetigkeiten()
             .getAlleTaetigkeiten()
             .add(new GsKeyValue("211",
                                 "Änderungsschneiderei"));
      tAntrag.getTaetigkeiten()
             .getAlleTaetigkeiten()
             .add(new GsKeyValue("2599",
                                 "Gießerei"));
      tAntrag.getTaetigkeiten()
             .getAlleTaetigkeiten()
             .add(new GsKeyValue("2896",
                                 "Hebamme (mit Geburtshilfe)"));
      tAntrag.getTaetigkeiten()
             .getAlleTaetigkeiten()
             .add(new GsKeyValue("4711",
                                 "Metzger"));
      tAntrag.getTaetigkeiten()
             .getAlleTaetigkeiten()
             .add(new GsKeyValue("4712",
                                 "Bäckerei"));
    } else {
      tAntrag.getTaetigkeiten()
             .setHauptTaetigkeit(new GsKeyValue("6400",
                                                "Akustiker"));
      tAntrag.getTaetigkeiten()
             .getAlleTaetigkeiten()
             .add(new GsKeyValue("6400",
                                 "Akustiker"));
      tAntrag.getTaetigkeiten()
             .getAlleTaetigkeiten()
             .add(new GsKeyValue("1165",
                                 "Catering"));
      tAntrag.getTaetigkeiten()
             .getAlleTaetigkeiten()
             .add(new GsKeyValue("211",
                                 "Änderungsschneiderei"));
      tAntrag.getTaetigkeiten()
             .getAlleTaetigkeiten()
             .add(new GsKeyValue("2599",
                                 "Gießerei"));
      tAntrag.getTaetigkeiten()
             .getAlleTaetigkeiten()
             .add(new GsKeyValue("2896",
                                 "Hebamme (mit Geburtshilfe)"));
      tAntrag.getTaetigkeiten()
             .getAlleTaetigkeiten()
             .add(new GsKeyValue("4711",
                                 "Metzger"));
      tAntrag.getTaetigkeiten()
             .getAlleTaetigkeiten()
             .add(new GsKeyValue("4712",
                                 "Bäckerei"));
    }

    tAntrag.setVersicherungsBeginn(new Date());
    tAntrag.setVersicherungsAblauf(createAblauf());
    tAntrag.setZahlweise(new GsKeyValue("1",
                                        "jührlich"));

    tAntrag.setVermittler(new Vermittler("0000802"));

    tAntrag.setAntragsteller(createAntragsteller());
    return tAntrag;
  }

  private static BhvTarifkonfiguration createBHVTarifkonfiguration(String pSelbstbeteiligung,
                                                                   String pDeckungssumme,
                                                                   String plusBaustein,
                                                                   boolean pSelected) {
    BhvTarifkonfiguration tTarifkonfiguration = new BhvTarifkonfiguration();
    tTarifkonfiguration.setSelbstbeteiligung(new GsKeyValue(pSelbstbeteiligung,
                                                            pSelbstbeteiligung));
    tTarifkonfiguration.setDeckungssumme(new GsKeyValue(pDeckungssumme,
                                                        pDeckungssumme));
    List<PlusBaustein> plusBausteine = new ArrayList<>();
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       0,
                                       "Keine Auswahl",
                                       true,
                                       new GsToolTip("PR_BHV_plusbaustein_tooltip",
                                                     "PR_BHV_plusbaustein_tooltip Text",
                                                     "Lirum larum Löffelstiel.",
                                                     null)));
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       1,
                                       "Plusbaustein I",
                                       false,
                                       new GsToolTip("PR_BHV_plusbaustein_tooltip1",
                                                     "PR_BHV_plusbaustein_tooltip Text",
                                                     "Lirum larum Löffelstiel. - Text 1",
                                                     null)));
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       2,
                                       "Plusbaustein II",
                                       false,
                                       new GsToolTip("PR_BHV_plusbaustein_tooltip2",
                                                     "PR_BHV_plusbaustein_tooltip Text",
                                                     "Lirum larum Löffelstiel. - Text 2",
                                                     null)));
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       3,
                                       "Plusbaustein III",
                                       false,
                                       new GsToolTip("PR_BHV_plusbaustein_tooltip3",
                                                     "PR_BHV_plusbaustein_tooltip Text",
                                                     "Lirum larum Löffelstiel. - Text 3",
                                                     null)));

    tTarifkonfiguration.setPlusBausteine(plusBausteine);
    tTarifkonfiguration.setSelected(pSelected);
    return tTarifkonfiguration;
  }

  private static String createAntragsnummer() {
    Date tDatum = new Date();
    SimpleDateFormat tDateFormat = new SimpleDateFormat("yyyyMMdd");

    return (tDateFormat.format(tDatum) + "TA" + tDatum.getTime()).substring(0,
                                                                            20);
  }

  private static Date createAblauf() {
    Date tAblauf = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(tAblauf);
    cal.set(Calendar.YEAR,
            cal.get(Calendar.YEAR) + 5);
    tAblauf = cal.getTime();
    return tAblauf;
  }

  private static Versicherungsnehmer createAntragsteller() {
    Versicherungsnehmer tAntragsteller = new Versicherungsnehmer();
    tAntragsteller.setNatuerlichePerson(false);
    tAntragsteller.setName("Paul Arbeiter");
    tAntragsteller.getHausAdresse()
                  .setStrasse("Gothaer Allee");
    tAntragsteller.getHausAdresse()
                  .setHausnummer("1");
    tAntragsteller.getHausAdresse()
                  .setPlz("50969");
    tAntragsteller.getHausAdresse()
                  .setOrt("Köln");

    tAntragsteller.getPostfachAdresse()
                  .setPostfach("132456");
    tAntragsteller.getPostfachAdresse()
                  .setPlz("53842");
    tAntragsteller.getPostfachAdresse()
                  .setOrt("Troisdorf");

    tAntragsteller.getGrosskundenAdresse()
                  .setPlz("49809");
    tAntragsteller.getGrosskundenAdresse()
                  .setOrt("Lingen");

    return tAntragsteller;
  }

  public static Antrag createBHVAntragMehrereWagnisse2(boolean pSample,
                                                       String pSelbstbeteiligung,
                                                       String pDeckungssumme,
                                                       String plusBaustein) {
    Antrag tAntrag = new Antrag();
    tAntrag = initAntrag(pSample,
                         tAntrag);

    BetriebsHaftpflichtVersicherung tBHV = new BetriebsHaftpflichtVersicherung(new GsKeyValue("PR_BHV",
                                                                                              "Betriebshaftpflicht"));

    BhvWagnis tWagnis = new BhvWagnis();
    Taetigkeiten tTaetigkeiten = new Taetigkeiten();
    if (pSample) {
      tTaetigkeiten.setHauptTaetigkeit(new GsKeyValue("1069",
                                                      "Buchhandel"));
    } else {
      tTaetigkeiten.setHauptTaetigkeit(new GsKeyValue("6400",
                                                      "Akustiker"));
    }
    tWagnis.setTaetigkeiten(tTaetigkeiten);
    tWagnis.setBbrGruppe("111");
    tWagnis.setJahresumsatz(new BigDecimal("500000"));
    tWagnis.setBbrVertraeglich(true);
    tBHV.getWagnisse()
        .add(tWagnis);

    tWagnis = new BhvWagnis();
    tTaetigkeiten = new Taetigkeiten();
    if (pSample) {
      tTaetigkeiten.setHauptTaetigkeit(new GsKeyValue("1069",
                                                      "Buchhandel"));
      tTaetigkeiten.getNebenTaetigkeiten()
                   .add(new GsKeyValue("124",
                                       "Altenheim"));
    } else {
      tTaetigkeiten.setHauptTaetigkeit(new GsKeyValue("6400",
                                                      "Akustiker"));
      tTaetigkeiten.getNebenTaetigkeiten()
                   .add(new GsKeyValue("12400",
                                       "Akustiker"));
    }

    tWagnis.setTaetigkeiten(tTaetigkeiten);
    tWagnis.setBbrGruppe("987");
    tWagnis.setJahresumsatz(new BigDecimal("50000"));
    tWagnis.setBbrVertraeglich(true);
    tBHV.getWagnisse()
        .add(tWagnis);

    tWagnis = new BhvWagnis();
    tTaetigkeiten = new Taetigkeiten();
    if (pSample) {
      tTaetigkeiten.getNebenTaetigkeiten()
                   .add(new GsKeyValue("1165",
                                       "Catering"));
      tTaetigkeiten.getNebenTaetigkeiten()
                   .add(new GsKeyValue("1276",
                                       "Contactlinsenstudio"));
    } else {
      tTaetigkeiten.getNebenTaetigkeiten()
                   .add(new GsKeyValue("116500",
                                       "Catering"));
      tTaetigkeiten.getNebenTaetigkeiten()
                   .add(new GsKeyValue("127600",
                                       "Contactlinsenstudio"));
    }
    tWagnis.setJahresumsatz(new BigDecimal("1000"));
    tWagnis.setBbrGruppe("213");
    tWagnis.setTaetigkeiten(tTaetigkeiten);
    tWagnis.setBbrVertraeglich(true);
    tBHV.getWagnisse()
        .add(tWagnis);

    tBHV.setTarifKonfiguration1(createBHVTarifkonfiguration(pSelbstbeteiligung,
                                                            pDeckungssumme,
                                                            plusBaustein,
                                                            false));
    tBHV.setTarifKonfiguration2(createBHVTarifkonfiguration(pSelbstbeteiligung,
                                                            pDeckungssumme,
                                                            plusBaustein,
                                                            false));

    tAntrag.setBetriebsHaftpflichtVersicherung(tBHV);

    return tAntrag;
  }

  public static Antrag createVSHAntrag() {
    Antrag tAntrag = new Antrag();

    tAntrag.setAntragsnummer(createAntragsnummer());
    tAntrag.setVerkaufsProdukt(new GsKeyValue(TAA.VERKAUFSPRODUKT_VSH,
                                              "Vermügensschadenhaftpflicht"));

    tAntrag.setVersicherungsBeginn(new Date());
    tAntrag.setVersicherungsAblauf(createAblauf());
    tAntrag.setZahlweise(new GsKeyValue("1",
                                        "jührlich"));
    tAntrag.setVermittler(new Vermittler("0000802"));
    tAntrag.setAntragsteller(createAntragsteller());

    tAntrag.getTaetigkeiten()
           .setHauptTaetigkeit(new GsKeyValue(TAA.VSH_TAETIGKEIT_ANWALT,
                                              "Anwalt"));

    Vermoegensschadenhaftpflicht vsh = new Vermoegensschadenhaftpflicht(new GsKeyValue(TAA.VSH_RECHTSANWALT,
                                                                                       "VSH Kammer"));
    vsh.setBearbeitungsStatus(BearbeitungsStatus.BROWSE);
    tAntrag.setVermoegensschadenhaftpflichtVersicherung(vsh);
    tAntrag.addBrowsedProdukt(new ProduktConfiguration(vsh.getProdukt(),
                                                       tAntrag.getVersicherungsBeginn()));

    return tAntrag;
  }

  public static Antrag createBHVAntragEinWagnis(boolean pSample) {
    Antrag tAntrag = new Antrag();
    tAntrag = initAntrag(pSample,
                         tAntrag);
    setKorrespondenzPartner(tAntrag);

    BetriebsHaftpflichtVersicherung tBHV = new BetriebsHaftpflichtVersicherung(new GsKeyValue("PR_BHV",
                                                                                              "Betriebshaftpflicht"));

    BhvWagnis tWagnis = new BhvWagnis();
    Taetigkeiten tTaetigkeiten = new Taetigkeiten();
    if (pSample) {
      tTaetigkeiten.setHauptTaetigkeit(new GsKeyValue("1069",
                                                      "Buchhandel"));
      tTaetigkeiten.getAlleTaetigkeiten()
                   .add(new GsKeyValue("1069",
                                       "Buchhandel"));
    } else {
      tTaetigkeiten.setHauptTaetigkeit(new GsKeyValue("6400",
                                                      "Akustiker"));
      tTaetigkeiten.getAlleTaetigkeiten()
                   .add(new GsKeyValue("6400",
                                       "Akustiker"));
    }

    tWagnis.setTaetigkeiten(tTaetigkeiten);
    tWagnis.setBbrGruppe("127");
    tWagnis.setJahresumsatz(new BigDecimal("500000"));
    tWagnis.setBbrVertraeglich(true);
    tWagnis.setWkz("11470");
    tWagnis.getDynamischeAttribute()
           .add(new PMAttribut(GUID.get(),
                               "Dynamisches Attribute 01",
                               PMAttribut.Type.TEXT_FIELD));
    tWagnis.getDynamischeAttribute()
           .add(new PMAttribut(GUID.get(),
                               "Dynamisches Attribute 02",
                               PMAttribut.Type.TEXT_FIELD));
    tWagnis.getDynamischeAttribute()
           .add(new PMAttribut(GUID.get(),
                               "Dynamisches Attribute 03",
                               PMAttribut.Type.TEXT_FIELD));
    tBHV.getWagnisse()
        .add(tWagnis);

    BhvTarifkonfiguration tTarifkonfiguration = new BhvTarifkonfiguration();
    tTarifkonfiguration.setSelbstbeteiligung(new GsKeyValue("250",
                                                            "250 Euro"));
    tTarifkonfiguration.setDeckungssumme(new GsKeyValue("BHV_DECKUNG_5",
                                                        "5 Mio."));
    tTarifkonfiguration.setSelected(true);

    tBHV.setTarifKonfiguration1(tTarifkonfiguration);

    BhvTarifkonfiguration tTarifkonfiguration2 = new BhvTarifkonfiguration();
    tTarifkonfiguration2.setSelbstbeteiligung(new GsKeyValue("250",
                                                             "250 Euro"));
    tTarifkonfiguration2.setDeckungssumme(new GsKeyValue("BHV_DECKUNG_10",
                                                         "10 Mio."));
    tTarifkonfiguration2.setSelected(false);

    tBHV.setTarifKonfiguration2(tTarifkonfiguration2);

    tAntrag.setBetriebsHaftpflichtVersicherung(tBHV);

    // private Risiken
    PrivateRisiken privateRisiken = new PrivateRisiken();
    privateRisiken.setProdukt(new GsKeyValue("PR_PHV",
                                             "Privathaftpflicht"));
    privateRisiken.setBearbeitungsStatus(AbstractProdukt.BearbeitungsStatus.ADD);
    privateRisiken.setAnzahlGeschaeftsfuehrer(0);
    privateRisiken.setAnzahlHunde(0);
    privateRisiken.setAnzahlPferde(0);
    //		privateRisiken.getNamenGeschaeftsfueher()
    //									.add(new Geschaeftsfuehrer("Fred Feuerstein"));
    tAntrag.setPrivateRisiken(privateRisiken);

    RisikoOrt risikoOrt01 = createRisikoOrt1(false);
    tAntrag.getRisikoOrte()
           .add(risikoOrt01);
    RisikoOrt risikoOrt02 = createRisikoOrt2(false);
    tAntrag.getRisikoOrte()
           .add(risikoOrt02);

    InhaltsVersicherung inhalt = new InhaltsVersicherung(new GsKeyValue("PRGR_INH",
                                                                        "Inhalt"));
    inhalt.getVersicherteGebaeude()
          .add(createVersicherteGebaeudeInhalt1(pSample,
                                                risikoOrt01.getId()));
    inhalt.setBearbeitungsStatus(BearbeitungsStatus.BROWSE);

    inhalt.setTarifKonfiguration1(createInhaltTarifkonfiguration01());
    inhalt.setTarifKonfiguration2(createInhaltTarifkonfiguration02());
    inhalt.setFeuer(true);
    inhalt.setLeitungswasser(true);
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
    //    inhalt.getDeckungserweiterung().setEinschluss("Nein");

    tAntrag.setInhaltsVersicherung(inhalt);
    tAntrag.addBrowsedProdukt(new ProduktConfiguration(inhalt.getProdukt(),
                                                       tAntrag.getVersicherungsBeginn()));

    GebaeudeVersicherung gebaeude = new GebaeudeVersicherung();
    gebaeude.setBearbeitungsStatus(BearbeitungsStatus.ADD);
    gebaeude = setGefahrenkonstellation(gebaeude,
                                        "1111111");
    gebaeude.setProdukt(new GsKeyValue("PRGR_GEB",
                                       "Gebüude"));
    gebaeude.getVersicherteGebaeude()
            .add(createVersicherteGebaeude1(pSample,
                                            risikoOrt01.getId()));

    gebaeude.setVersicherungZum(new GsKeyValue(GebaeudeVersicherung.GEBAEUDE_VERSICHERUNG_ZUM_GLEITENDEN_NEUWERT_KEY,
                                               "gl. Neuwert"));

    gebaeude.setTarifKonfiguration1(createGebaeudeTarifkonfiguration());
    tAntrag.setGebaeudeVersicherung(gebaeude);
    tAntrag.addBrowsedProdukt(new ProduktConfiguration(gebaeude.getProdukt(),
                                                       tAntrag.getVersicherungsBeginn()));

    ElektronikVersicherung elektronikVersicherung = new ElektronikVersicherung();
    elektronikVersicherung.setBearbeitungsStatus(BearbeitungsStatus.BROWSE);
    elektronikVersicherung.setProdukt(new GsKeyValue("PR_ELEKTR",
                                                     "Elektronik"));
    elektronikVersicherung.getVersicherteGebaeude()
                          .add(createVersichertesGebaeudeElektronik(pSample,
                                                                    risikoOrt01.getId()));
    elektronikVersicherung.setTarifKonfiguration1(createElektronikTarifkonfiguration());
    tAntrag.setElektronikVersicherung(elektronikVersicherung);
    tAntrag.addBrowsedProdukt(new ProduktConfiguration(elektronikVersicherung.getProdukt(),
                                                       tAntrag.getVersicherungsBeginn()));

    return tAntrag;
  }

  private static void setKorrespondenzPartner(Antrag antrag01) {
    KorrespondenzEmpfaenger ke = new KorrespondenzEmpfaenger();
    ke.setEmail("Fred.feuerstein@steinburch-ag.de");
    ke.setTelefon("0211 - 123 4711");
    ke.setTyp(PartnerType.BESTANDSKUNDE);
    antrag01.setKorrespondenzEmpfaenger(ke);
    antrag01.setKorrespondenzEmpfaengerType(new GsKeyValue("0",
                                                           "Korespondenzempfaenger VN"));
  }

  private static RisikoOrt createRisikoOrt1(boolean pSample) {
    RisikoOrt tRisikoort = new RisikoOrt();
    tRisikoort.setPlz("50969");
    tRisikoort.setOrt("Köln");
    tRisikoort.setStrasse("Gothaer Allee");
    tRisikoort.setHausnummer("1");
    if (pSample) {
      tRisikoort.getEigeneTaetigkeiten()
                .add(new GsKeyValue("1069",
                                    "Catering"));
      tRisikoort.getFremdeTaetigkeiten()
                .add(new GsKeyValue("1069",
                                    "Catering"));
    } else {
      tRisikoort.getEigeneTaetigkeiten()
                .add(new GsKeyValue("106900",
                                    "Catering"));
      tRisikoort.getFremdeTaetigkeiten()
                .add(new GsKeyValue("106900",
                                    "Catering"));
    }
    return tRisikoort;
  }

  private static RisikoOrt createRisikoOrt2(boolean pSample) {
    RisikoOrt tRisikoort = new RisikoOrt();
    tRisikoort.setPlz("50969");
    tRisikoort.setOrt("Köln");
    tRisikoort.setStrasse("Gothaer Allee");
    tRisikoort.setHausnummer("2");
    if (pSample) {
      tRisikoort.getEigeneTaetigkeiten()
                .add(new GsKeyValue("1165",
                                    "Catering"));
      tRisikoort.getFremdeTaetigkeiten()
                .add(new GsKeyValue("1165",
                                    "Catering"));
    } else {
      tRisikoort.getEigeneTaetigkeiten()
                .add(new GsKeyValue("116500",
                                    "Catering"));
      tRisikoort.getFremdeTaetigkeiten()
                .add(new GsKeyValue("116500",
                                    "Catering"));
    }
    return tRisikoort;
  }

  private static VersichertesGebaeudeInhalt createVersicherteGebaeudeInhalt1(boolean pSample,
                                                                             String pRisikoOrtId1) {
    VersichertesGebaeudeInhalt tVersicherteGebaeude = new VersichertesGebaeudeInhalt();
    tVersicherteGebaeude.setIdRisikoort(pRisikoOrtId1);
    tVersicherteGebaeude.setVersicherungssumme(new BigDecimal("100000"));
    return tVersicherteGebaeude;
  }

  private static InhaltTarifkonfiguration createInhaltTarifkonfiguration01() {
    InhaltTarifkonfiguration tTarifkonfiguration = new InhaltTarifkonfiguration();
    tTarifkonfiguration.isSelected();
    tTarifkonfiguration.setSelbstbeteiligung(new GsKeyValue("250",
                                                            "250 Euro"));
    List<PlusBaustein> plusBausteine = new ArrayList<>();
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       0,
                                       "Keine Auswahl",
                                       true,
                                       null));
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       1,
                                       "Plusbaustein I",
                                       false,
                                       null));
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       2,
                                       "Plusbaustein II",
                                       false,
                                       null));
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       3,
                                       "Plusbaustein III",
                                       false,
                                       null));
    tTarifkonfiguration.setPlusBausteine(plusBausteine);
    tTarifkonfiguration.setBruttoBeitrag(new BigDecimal(500));
    return tTarifkonfiguration;
  }

  private static InhaltTarifkonfiguration createInhaltTarifkonfiguration02() {
    InhaltTarifkonfiguration tTarifkonfiguration = new InhaltTarifkonfiguration();
    tTarifkonfiguration.isSelected();
    tTarifkonfiguration.setSelbstbeteiligung(new GsKeyValue("500",
                                                            "500 Euro"));
    List<PlusBaustein> plusBausteine = new ArrayList<>();
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       0,
                                       "Keine Auswahl",
                                       true,
                                       null));
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       1,
                                       "Plusbaustein I",
                                       false,
                                       null));
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       2,
                                       "Plusbaustein II",
                                       false,
                                       null));
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       3,
                                       "Plusbaustein III",
                                       false,
                                       null));
    tTarifkonfiguration.setPlusBausteine(plusBausteine);
    tTarifkonfiguration.setBruttoBeitrag(new BigDecimal(400));
    return tTarifkonfiguration;
  }

  private static GebaeudeVersicherung setGefahrenkonstellation(GebaeudeVersicherung pGebaeude,
                                                               String pGefahrenKonstellation) {
    if (pGefahrenKonstellation.charAt(0) == '1') {
      pGebaeude.setFeuer(true);
    }
    if (pGefahrenKonstellation.charAt(1) == '1') {
      pGebaeude.setSturm(true);
    }
    if (pGefahrenKonstellation.charAt(2) == '1') {
      pGebaeude.setLeitungswasser(true);
    }
    if (pGefahrenKonstellation.charAt(3) == '1') {
      pGebaeude.setGlas(true);
    }
    if (pGefahrenKonstellation.charAt(4) == '1') {
      pGebaeude.setErweiterteDeckung(true);
    }
    if (pGefahrenKonstellation.charAt(5) == '1') {
      pGebaeude.setElementar(true);
    }
    if (pGefahrenKonstellation.charAt(6) == '1') {
      pGebaeude.setUnbenannteGefahren(true);
    }

    return pGebaeude;
  }

  private static VersichertesGebaeudeGebaeude createVersicherteGebaeude1(boolean pSample,
                                                                         String pRisikoOrtId1) {
    VersichertesGebaeudeGebaeude tVersicherteGebaeude = new VersichertesGebaeudeGebaeude();
    tVersicherteGebaeude.setIdRisikoort(pRisikoOrtId1);
    tVersicherteGebaeude.setVersicherungssummeBeantragt(new BigDecimal("100000"));
    tVersicherteGebaeude.setWertermittlungType(WertermittlungsType.AENDERUNGSANTRAGHINWEIS);

    return tVersicherteGebaeude;
  }

  private static GebaeudeTarifkonfiguration createGebaeudeTarifkonfiguration() {
    GebaeudeTarifkonfiguration tTarifkonfiguration = new GebaeudeTarifkonfiguration();
    tTarifkonfiguration.isSelected();
    tTarifkonfiguration.setSelbstbeteiligung(new GsKeyValue("500",
                                                            "150 Euro"));
    List<PlusBaustein> plusBausteine = new ArrayList<>();
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       0,
                                       "Keine Auswahl",
                                       true,
                                       null));
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       1,
                                       "Plusbaustein I",
                                       false,
                                       null));
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       2,
                                       "Plusbaustein II",
                                       false,
                                       null));
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       3,
                                       "Plusbaustein III",
                                       false,
                                       null));
    tTarifkonfiguration.setPlusBausteine(plusBausteine);
    return tTarifkonfiguration;
  }

  private static VersichertesGebaeudeElektronikMaschinenPhotovoltaik createVersichertesGebaeudeElektronik(boolean pSample,
                                                                                                          String pRisikoOrtId2) {
    VersichertesGebaeudeElektronikMaschinenPhotovoltaik tVersicherteGebaeude = new VersichertesGebaeudeElektronikMaschinenPhotovoltaik();
    tVersicherteGebaeude.setIdRisikoort(pRisikoOrtId2);
    Deckungserweiterung deckungserweiterung = new Deckungserweiterung();
    deckungserweiterung.setEinschluss(false);
    TaetigkeitenUndVsu taetigkeitenUndVsu01 = new TaetigkeitenUndVsu();
    Taetigkeiten taetigkeiten01 = new Taetigkeiten();
    taetigkeiten01.setHauptTaetigkeit(new GsKeyValue("211",
                                                     "Änderungsschneiderei"));
    taetigkeiten01.getAlleTaetigkeiten()
                  .add(new GsKeyValue("211",
                                      "Änderungsschneiderei"));
    taetigkeitenUndVsu01.setTaetigkeiten(taetigkeiten01);
    taetigkeitenUndVsu01.setTaetigkeitGruppe("211-Änderungsschneiderei");
    taetigkeitenUndVsu01.setVsu(new BigDecimal(100000));
    taetigkeitenUndVsu01.setTaetigkeitGruppeAusgewaehlt(true);
    tVersicherteGebaeude.getTaetigkeitenUndVsu()
                        .add(taetigkeitenUndVsu01);

    TaetigkeitenUndVsu taetigkeitenUndVsu02 = new TaetigkeitenUndVsu();
    Taetigkeiten taetigkeiten02 = new Taetigkeiten();
    taetigkeiten02.setHauptTaetigkeit(new GsKeyValue("1165",
                                                     "Catering"));
    taetigkeiten02.getAlleTaetigkeiten()
                  .add(new GsKeyValue("1165",
                                      "Catering"));
    taetigkeitenUndVsu02.setTaetigkeiten(taetigkeiten02);
    taetigkeitenUndVsu02.setTaetigkeitGruppe("1165-Catering");
    taetigkeitenUndVsu02.setVsu(new BigDecimal(200000));
    taetigkeitenUndVsu02.setTaetigkeitGruppeAusgewaehlt(true);
    tVersicherteGebaeude.getTaetigkeitenUndVsu()
                        .add(taetigkeitenUndVsu02);

    TaetigkeitenUndVsu taetigkeitenUndVsu03 = new TaetigkeitenUndVsu();
    Taetigkeiten taetigkeiten03 = new Taetigkeiten();
    taetigkeiten03.setHauptTaetigkeit(new GsKeyValue("2599",
                                                     "Gießerei"));
    taetigkeiten03.getAlleTaetigkeiten()
                  .add(new GsKeyValue("2599",
                                      "Gießerei"));
    taetigkeitenUndVsu03.setTaetigkeiten(taetigkeiten03);
    taetigkeitenUndVsu03.setTaetigkeiten(new Taetigkeiten(new GsKeyValue("2599",
                                                                         "Gießerei"),
                                                          new ArrayList()));
    taetigkeitenUndVsu03.setTaetigkeitGruppe("2599-Gießerei");
    taetigkeitenUndVsu03.setVsu(new BigDecimal(300000));
    taetigkeitenUndVsu03.setTaetigkeitGruppeAusgewaehlt(true);
    tVersicherteGebaeude.getTaetigkeitenUndVsu()
                        .add(taetigkeitenUndVsu03);

    TaetigkeitenUndVsu taetigkeitenUndVsu04 = new TaetigkeitenUndVsu();
    Taetigkeiten taetigkeiten04 = new Taetigkeiten();
    taetigkeiten04.setHauptTaetigkeit(new GsKeyValue("2896",
                                                     "Hebamme (mit Geburtshilfe)"));
    taetigkeiten04.getAlleTaetigkeiten()
                  .add(new GsKeyValue("2896",
                                      "Hebamme (mit Geburtshilfe)"));
    taetigkeitenUndVsu04.setTaetigkeiten(taetigkeiten04);
    taetigkeitenUndVsu04.setTaetigkeiten(new Taetigkeiten(new GsKeyValue("2896",
                                                                         "Hebamme (mit Geburtshilfe)"),
                                                          new ArrayList()));
    taetigkeitenUndVsu04.setTaetigkeitGruppe("2896-Hebamme (mit Geburtshilfe)");
    taetigkeitenUndVsu04.setVsu(new BigDecimal(400000));
    taetigkeitenUndVsu04.setTaetigkeitGruppeAusgewaehlt(true);
    tVersicherteGebaeude.getTaetigkeitenUndVsu()
                        .add(taetigkeitenUndVsu04);

    TaetigkeitenUndVsu taetigkeitenUndVsu05 = new TaetigkeitenUndVsu();
    Taetigkeiten taetigkeiten05 = new Taetigkeiten();
    taetigkeiten05.setHauptTaetigkeit(new GsKeyValue("4711",
                                                     "Metzger"));
    taetigkeiten05.getAlleTaetigkeiten()
                  .add(new GsKeyValue("4711",
                                      "Metzger"));
    taetigkeitenUndVsu05.setTaetigkeiten(taetigkeiten05);
    taetigkeitenUndVsu05.setTaetigkeiten(new Taetigkeiten(new GsKeyValue("4711",
                                                                         "Metzger"),
                                                          new ArrayList()));
    taetigkeitenUndVsu05.setTaetigkeitGruppe("4711-Metzger");
    taetigkeitenUndVsu05.setVsu(new BigDecimal(500000));
    taetigkeitenUndVsu05.setTaetigkeitGruppeAusgewaehlt(true);
    tVersicherteGebaeude.getTaetigkeitenUndVsu()
                        .add(taetigkeitenUndVsu05);

    TaetigkeitenUndVsu taetigkeitenUndVsu06 = new TaetigkeitenUndVsu();
    Taetigkeiten taetigkeiten06 = new Taetigkeiten();
    taetigkeiten06.setHauptTaetigkeit(new GsKeyValue("4712",
                                                     "Bäckerei"));
    taetigkeiten06.getAlleTaetigkeiten()
                  .add(new GsKeyValue("4712",
                                      "Bäckerei"));
    taetigkeitenUndVsu06.setTaetigkeiten(taetigkeiten06);
    taetigkeitenUndVsu06.setTaetigkeitGruppe("4712-Bäckerei");
    taetigkeitenUndVsu06.setVsu(new BigDecimal(600000));
    taetigkeitenUndVsu06.setTaetigkeitGruppeAusgewaehlt(true);
    tVersicherteGebaeude.getTaetigkeitenUndVsu()
                        .add(taetigkeitenUndVsu06);

    TaetigkeitenUndVsu taetigkeitenUndVsu07 = new TaetigkeitenUndVsu();
    Taetigkeiten taetigkeiten07 = new Taetigkeiten();
    taetigkeiten07.setHauptTaetigkeit(new GsKeyValue("6400",
                                                     "Akustiker"));
    taetigkeiten07.getAlleTaetigkeiten()
                  .add(new GsKeyValue("6400",
                                      "Akustiker"));
    taetigkeitenUndVsu07.setTaetigkeiten(taetigkeiten07);
    taetigkeitenUndVsu07.setTaetigkeitGruppe("6400-Akustiker");
    taetigkeitenUndVsu07.setVsu(new BigDecimal(700000));
    taetigkeitenUndVsu07.setTaetigkeitGruppeAusgewaehlt(true);
    tVersicherteGebaeude.getTaetigkeitenUndVsu()
                        .add(taetigkeitenUndVsu07);

    return tVersicherteGebaeude;
  }

  private static ElektronikTarifkonfiguration createElektronikTarifkonfiguration() {
    ElektronikTarifkonfiguration tTarifkonfiguration = new ElektronikTarifkonfiguration();
    tTarifkonfiguration.isSelected();
    tTarifkonfiguration.setSelbstbeteiligung(new GsKeyValue("500",
                                                            "150 Euro"));
    List<PlusBaustein> plusBausteine = new ArrayList<>();
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       0,
                                       "Keine Auswahl",
                                       true,
                                       new GsToolTip("PR_PHOTO_plusbaustein_tooltip",
                                                     "PR_PHOTO_plusbaustein_tooltip Text",
                                                     "Lirum larum Löffelstiel.",
                                                     null)));
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       1,
                                       "Plusbaustein I",
                                       false,
                                       new GsToolTip("PR_PHOTO_plusbaustein_tooltip1",
                                                     "PR_PHOTO_plusbaustein_tooltip Text",
                                                     "Lirum larum Löffelstiel. - Text 1",
                                                     null)));
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       2,
                                       "Plusbaustein II",
                                       false,
                                       new GsToolTip("PR_PHOTO_plusbaustein_tooltip2",
                                                     "PR_PHOTO_plusbaustein_tooltip Text",
                                                     "Lirum larum Löffelstiel. - Text 2",
                                                     null)));
    plusBausteine.add(new PlusBaustein(GUID.get(),
                                       3,
                                       "Plusbaustein III",
                                       false,
                                       new GsToolTip("PR_PHOTO_plusbaustein_tooltip3",
                                                     "PR_PHOTO_plusbaustein_tooltip Text",
                                                     "Lirum larum Löffelstiel. - Text 3",
                                                     null)));
    tTarifkonfiguration.setPlusBausteine(plusBausteine);
    return tTarifkonfiguration;
  }

  public static Antrag createBHVAntragMehrereWagnisse(boolean pSample) {
    Antrag tAntrag = new Antrag();
    tAntrag = initAntrag(pSample,
                         tAntrag);

    BetriebsHaftpflichtVersicherung tBHV = new BetriebsHaftpflichtVersicherung(new GsKeyValue("PR_BHV",
                                                                                              "Betriebshaftpflicht"));

    BhvWagnis tWagnis = new BhvWagnis();
    Taetigkeiten tTaetigkeiten = new Taetigkeiten();
    if (pSample) {
      tTaetigkeiten.setHauptTaetigkeit(new GsKeyValue("1069",
                                                      "Buchhandel"));
    } else {
      tTaetigkeiten.setHauptTaetigkeit(new GsKeyValue("6400",
                                                      "Akustiker"));
    }
    tWagnis.setTaetigkeiten(tTaetigkeiten);
    tWagnis.setBbrGruppe("111");
    tWagnis.setJahresumsatz(new BigDecimal("500000"));
    tWagnis.setWkz("11470");
    tWagnis.setBbrVertraeglich(true);
    tBHV.getWagnisse()
        .add(tWagnis);

    tWagnis = new BhvWagnis();
    tTaetigkeiten = new Taetigkeiten();
    if (pSample) {
      tTaetigkeiten.setHauptTaetigkeit(new GsKeyValue("1069",
                                                      "Buchhandel"));
      tTaetigkeiten.getNebenTaetigkeiten()
                   .add(new GsKeyValue("124",
                                       "Altenheim"));
    } else {
      tTaetigkeiten.setHauptTaetigkeit(new GsKeyValue("6400",
                                                      "Akustiker"));
      tTaetigkeiten.getNebenTaetigkeiten()
                   .add(new GsKeyValue("12400",
                                       "Altenheim"));
    }

    tWagnis.setTaetigkeiten(tTaetigkeiten);
    tWagnis.setBbrGruppe("987");
    tWagnis.setJahresumsatz(new BigDecimal("50000"));
    tWagnis.setWkz("11471");
    tWagnis.setBbrVertraeglich(true);
    tBHV.getWagnisse()
        .add(tWagnis);

    tWagnis = new BhvWagnis();
    tTaetigkeiten = new Taetigkeiten();
    if (pSample) {
      tTaetigkeiten.getNebenTaetigkeiten()
                   .add(new GsKeyValue("1165",
                                       "Catering"));
      tTaetigkeiten.getNebenTaetigkeiten()
                   .add(new GsKeyValue("1276",
                                       "Contactlinsenstudio"));
    } else {
      tTaetigkeiten.getNebenTaetigkeiten()
                   .add(new GsKeyValue("116500",
                                       "Catering"));
      tTaetigkeiten.getNebenTaetigkeiten()
                   .add(new GsKeyValue("127600",
                                       "Contactlinsenstudio"));
    }
    tWagnis.setJahresumsatz(new BigDecimal("1000"));
    tWagnis.setBbrGruppe("213");
    tWagnis.setWkz("11472");
    tWagnis.setTaetigkeiten(tTaetigkeiten);
    tWagnis.setBbrVertraeglich(true);
    tBHV.getWagnisse()
        .add(tWagnis);

    BhvTarifkonfiguration tTarifkonfiguration = new BhvTarifkonfiguration();
    tTarifkonfiguration.setSelbstbeteiligung(new GsKeyValue("150",
                                                            "150"));
    tTarifkonfiguration.setDeckungssumme(new GsKeyValue("10000000",
                                                        "10000000"));
    tTarifkonfiguration.setSelected(true);
    tBHV.setTarifKonfiguration1(tTarifkonfiguration);

    tAntrag.setBetriebsHaftpflichtVersicherung(tBHV);
    return tAntrag;
  }

  public static Antrag createGebaeudeAntragEinOrt(boolean pSample) {
    return createGebaeudeAntragEinOrt(pSample,
                                      "1111111");
  }

  public static Antrag createGebaeudeAntragEinOrt(boolean pSample,
                                                  String pGefahrenKonstellation) {
    Antrag tAntrag = new Antrag();
    tAntrag = initAntrag(pSample,
                         tAntrag);
    RisikoOrt tRisikoort1 = createRisikoOrt1(pSample);
    tAntrag.getRisikoOrte()
           .add(tRisikoort1);

    GebaeudeVersicherung tGebaeude = new GebaeudeVersicherung();

    tGebaeude = setGefahrenkonstellation(tGebaeude,
                                         pGefahrenKonstellation);

    tGebaeude.setProdukt(new GsKeyValue("PRGR_GEB",
                                        "Gebüude"));

    tGebaeude.getVersicherteGebaeude()
             .add(createVersicherteGebaeude1(pSample,
                                             tRisikoort1.getId()));

    tGebaeude.setTarifKonfiguration1(createGebaeudeTarifkonfiguration());

    tAntrag.setGebaeudeVersicherung(tGebaeude);

    return tAntrag;
  }

  public static Antrag createInhaltAntragEinOrt(boolean pSample,
                                                String pGefahrenKonstellation) {
    Antrag tAntrag = new Antrag();
    tAntrag = initAntrag(pSample,
                         tAntrag);
    RisikoOrt tRisikoort1 = createRisikoOrt1(pSample);
    tAntrag.getRisikoOrte()
           .add(tRisikoort1);

    InhaltsVersicherung tInhalt = new InhaltsVersicherung();

    tInhalt = setGefahrenkonstellation(tInhalt,
                                       pGefahrenKonstellation);

    tInhalt.setProdukt(new GsKeyValue("PRGR_INH",
                                      "Inhalt"));

    tInhalt.getVersicherteGebaeude()
           .add(createVersicherteGebaeudeInhalt1(pSample,
                                                 tRisikoort1.getId()));

    tInhalt.setTarifKonfiguration1(createInhaltTarifkonfiguration01());

    tAntrag.setInhaltsVersicherung(tInhalt);

    return tAntrag;
  }

  private static InhaltsVersicherung setGefahrenkonstellation(InhaltsVersicherung pInhalt,
                                                              String pGefahrenKonstellation) {
    if (pGefahrenKonstellation.charAt(0) == '1') {
      pInhalt.setFeuer(true);
    }
    if (pGefahrenKonstellation.charAt(1) == '1') {
      pInhalt.setSturm(true);
    }
    if (pGefahrenKonstellation.charAt(2) == '1') {
      pInhalt.setLeitungswasser(true);
    }
    if (pGefahrenKonstellation.charAt(3) == '1') {
      pInhalt.setGlas(true);
    }
    if (pGefahrenKonstellation.charAt(4) == '1') {
      pInhalt.setErweiterteDeckung(true);
    }
    if (pGefahrenKonstellation.charAt(5) == '1') {
      pInhalt.setElementar(true);
    }
    if (pGefahrenKonstellation.charAt(6) == '1') {
      pInhalt.setUnbenannteGefahren(true);
    }

    return pInhalt;
  }

  public static Antrag createInhaltundGebaeudeAntragEinOrt(boolean pSample,
                                                           String pGefahrenKonstellation) {
    Antrag tAntrag = new Antrag();
    tAntrag = initAntrag(pSample,
                         tAntrag);
    RisikoOrt tRisikoort1 = createRisikoOrt1(pSample);
    tAntrag.getRisikoOrte()
           .add(tRisikoort1);

    InhaltsVersicherung tInhalt = new InhaltsVersicherung();

    tInhalt = setGefahrenkonstellation(tInhalt,
                                       pGefahrenKonstellation);

    tInhalt.setProdukt(new GsKeyValue("PRGR_INH",
                                      "Inhalt"));

    tInhalt.getVersicherteGebaeude()
           .add(createVersicherteGebaeudeInhalt1(pSample,
                                                 tRisikoort1.getId()));

    tInhalt.setTarifKonfiguration1(createInhaltTarifkonfiguration01());

    tAntrag.setInhaltsVersicherung(tInhalt);

    GebaeudeVersicherung tGebaeude = new GebaeudeVersicherung();

    tGebaeude = setGefahrenkonstellation(tGebaeude,
                                         pGefahrenKonstellation);

    tGebaeude.setProdukt(new GsKeyValue("PRGR_GEB",
                                        "Gebüude"));

    tGebaeude.getVersicherteGebaeude()
             .add(createVersicherteGebaeude1(pSample,
                                             tRisikoort1.getId()));

    tGebaeude.setTarifKonfiguration1(createGebaeudeTarifkonfiguration());
    tAntrag.setGebaeudeVersicherung(tGebaeude);

    return tAntrag;
  }

  public static Antrag createGebaeudeAntragEinOrtSpezialfallFeuerwerkskoerperherstellung(boolean pSample,
                                                                                         String pGefahrenKonstellation) {
    Antrag tAntrag = new Antrag();
    tAntrag = initAntrag(pSample,
                         tAntrag);
    RisikoOrt tRisikoort1 = createRisikoOrt1(pSample);
    tAntrag.getRisikoOrte()
           .add(tRisikoort1);
    if (pSample) {
      tAntrag.getTaetigkeiten()
             .setHauptTaetigkeit(new GsKeyValue("20289",
                                                "Feuerwerkskürperherstellung"));
    } else {
      tAntrag.getTaetigkeiten()
             .setHauptTaetigkeit(new GsKeyValue("2028900",
                                                "Feuerwerkskürperherstellung"));
    }
    GebaeudeVersicherung tGebaeude = new GebaeudeVersicherung();

    tGebaeude.setProdukt(new GsKeyValue("PRGR_GEB",
                                        "Gebüude"));

    tGebaeude = setGefahrenkonstellation(tGebaeude,
                                         pGefahrenKonstellation);
    tGebaeude.getVersicherteGebaeude()
             .add(createVersicherteGebaeude1(pSample,
                                             tRisikoort1.getId()));

    tGebaeude.setTarifKonfiguration1(createGebaeudeTarifkonfiguration());
    tAntrag.setGebaeudeVersicherung(tGebaeude);

    return tAntrag;
  }

  public static Antrag createGebaeudeAntragZweiOrte(boolean pSample,
                                                    String pGefahrenKonstellation) {
    Antrag tAntrag = new Antrag();
    tAntrag = initAntrag(pSample,
                         tAntrag);

    GebaeudeVersicherung tGebaeude = new GebaeudeVersicherung();

    tGebaeude = setGefahrenkonstellation(tGebaeude,
                                         pGefahrenKonstellation);
    tGebaeude.setProdukt(new GsKeyValue("PRGR_GEB",
                                        "Gebüude"));

    RisikoOrt tRisikoOrt1 = createRisikoOrt1(pSample);
    tAntrag.getRisikoOrte()
           .add(tRisikoOrt1);

    tGebaeude.getVersicherteGebaeude()
             .add(createVersicherteGebaeude1(pSample,
                                             tRisikoOrt1.getId()));

    RisikoOrt tRisikoort2 = createRisikoOrt2(pSample);
    tAntrag.getRisikoOrte()
           .add(tRisikoort2);
    tGebaeude.getVersicherteGebaeude()
             .add(createVersicherteGebaeude2(pSample,
                                             tRisikoort2.getId()));

    tGebaeude.setTarifKonfiguration1(createGebaeudeTarifkonfiguration());

    tAntrag.setGebaeudeVersicherung(tGebaeude);
    return tAntrag;
  }

  private static VersichertesGebaeudeGebaeude createVersicherteGebaeude2(boolean pSample,
                                                                         String pRisikoOrtId2) {
    VersichertesGebaeudeGebaeude tVersicherteGebaeude = new VersichertesGebaeudeGebaeude();
    tVersicherteGebaeude.setIdRisikoort(pRisikoOrtId2);
    tVersicherteGebaeude.setVersicherungssummeBeantragt(new BigDecimal("200000"));
    return tVersicherteGebaeude;
  }

  public static Antrag createGebaeudeBHVAntrag(boolean pSample,
                                               String pGefahrenKonstellation,
                                               String pSelbstbeteiligung) {
    Antrag tAntrag = new Antrag();
    tAntrag = initAntrag(pSample,
                         tAntrag);

    BetriebsHaftpflichtVersicherung tBHV = new BetriebsHaftpflichtVersicherung(new GsKeyValue("PR_BHV",
                                                                                              "Betriebshaftpflicht"));

    BhvWagnis tWagnis = new BhvWagnis();
    Taetigkeiten tTaetigkeiten = new Taetigkeiten();
    if (pSample) {
      tTaetigkeiten.setHauptTaetigkeit(new GsKeyValue("1069",
                                                      "Buchhandel"));
    } else {
      tTaetigkeiten.setHauptTaetigkeit(new GsKeyValue("6400",
                                                      "Akustiker"));
    }

    tWagnis.setTaetigkeiten(tTaetigkeiten);
    tWagnis.setBbrGruppe("111");
    tWagnis.setJahresumsatz(new BigDecimal("500000"));
    tWagnis.setBbrVertraeglich(true);
    tBHV.getWagnisse()
        .add(tWagnis);

    BhvTarifkonfiguration tTarifkonfiguration = new BhvTarifkonfiguration();
    tTarifkonfiguration.setSelbstbeteiligung(new GsKeyValue(pSelbstbeteiligung,
                                                            pSelbstbeteiligung));
    tTarifkonfiguration.setDeckungssumme(new GsKeyValue("10000000",
                                                        "10000000"));

    tBHV.setTarifKonfiguration1(tTarifkonfiguration);

    tAntrag.setBetriebsHaftpflichtVersicherung(tBHV);

    GebaeudeVersicherung tGebaeude = new GebaeudeVersicherung();

    tGebaeude = setGefahrenkonstellation(tGebaeude,
                                         pGefahrenKonstellation);
    tGebaeude.setProdukt(new GsKeyValue("PRGR_GEB",
                                        "Gebüude"));

    RisikoOrt tRisikoort1 = createRisikoOrt1(pSample);
    tAntrag.getRisikoOrte()
           .add(tRisikoort1);

    tGebaeude.getVersicherteGebaeude()
             .add(createVersicherteGebaeude1(pSample,
                                             tRisikoort1.getId()));

    RisikoOrt tRisikoort2 = createRisikoOrt2(pSample);
    tAntrag.getRisikoOrte()
           .add(tRisikoort2);
    tGebaeude.getVersicherteGebaeude()
             .add(createVersicherteGebaeude2(pSample,
                                             tRisikoort2.getId()));

    tGebaeude.setTarifKonfiguration1(createGebaeudeTarifkonfiguration());

    tAntrag.setGebaeudeVersicherung(tGebaeude);
    return tAntrag;
  }

  public static Antrag createGebaeudeBHVAntrag2(boolean pSample,
                                                String pGefahrenKonstellation,
                                                String pSelbstbeteiligung,
                                                String pDeckungssumme,
                                                String plusBaustein) {
    Antrag tAntrag = new Antrag();
    tAntrag = initAntrag(pSample,
                         tAntrag);

    BetriebsHaftpflichtVersicherung tBHV = new BetriebsHaftpflichtVersicherung(new GsKeyValue("PR_BHV",
                                                                                              "Betriebshaftpflicht"));

    BhvWagnis tWagnis = new BhvWagnis();
    Taetigkeiten tTaetigkeiten = new Taetigkeiten();
    if (pSample) {
      tTaetigkeiten.setHauptTaetigkeit(new GsKeyValue("1069",
                                                      "Buchhandel"));
    } else {
      tTaetigkeiten.setHauptTaetigkeit(new GsKeyValue("6400",
                                                      "Akustiker"));
    }
    tWagnis.setTaetigkeiten(tTaetigkeiten);
    tWagnis.setBbrGruppe("127");
    tWagnis.setJahresumsatz(new BigDecimal("500000"));
    tWagnis.setBbrVertraeglich(true);
    tBHV.getWagnisse()
        .add(tWagnis);

    //		BhvTarifkonfiguration tTarifkonfiguration = new BhvTarifkonfiguration();
    //		tTarifkonfiguration.setSelbstbeteiligung(new GsKeyValue(pSelbstbeteiligung, pSelbstbeteiligung));
    //		tTarifkonfiguration.setDeckungssumme(new GsKeyValue("10000000", "10000000"));

    tBHV.setTarifKonfiguration1(createBHVTarifkonfiguration(pSelbstbeteiligung,
                                                            pDeckungssumme,
                                                            plusBaustein,
                                                            false));

    tAntrag.setBetriebsHaftpflichtVersicherung(tBHV);

    GebaeudeVersicherung tGebaeude = new GebaeudeVersicherung();

    tGebaeude = setGefahrenkonstellation(tGebaeude,
                                         pGefahrenKonstellation);
    tGebaeude.setProdukt(new GsKeyValue("PRGR_GEB",
                                        "Gebüude"));

    RisikoOrt tRisikoort1 = createRisikoOrt1(pSample);
    tAntrag.getRisikoOrte()
           .add(tRisikoort1);

    tGebaeude.getVersicherteGebaeude()
             .add(createVersicherteGebaeude1(pSample,
                                             tRisikoort1.getId()));

    RisikoOrt tRisikoort2 = createRisikoOrt2(pSample);
    tAntrag.getRisikoOrte()
           .add(tRisikoort2);
    tGebaeude.getVersicherteGebaeude()
             .add(createVersicherteGebaeude2(pSample,
                                             tRisikoort2.getId()));

    tGebaeude.setTarifKonfiguration1(createGebaeudeTarifkonfiguration());

    tAntrag.setGebaeudeVersicherung(tGebaeude);
    return tAntrag;
  }

  public static RisikoOrt getRisikoortGK1() {
    return new RisikoOrt("Gothaer Allee",
                         "1",
                         "50969",
                         "Köln");
  }

  public static RisikoOrt getRisikoortGK2() {
    return new RisikoOrt("Berliner Str.",
                         "1",
                         "01067",
                         "Dresden");
  }

  public static RisikoOrt getRisikoortGK3() {
    return new RisikoOrt("Odenthaler Str.",
                         "229",
                         "51069",
                         "Köln");
  }

  public static RisikoOrt getRisikoortGK4() {
    return new RisikoOrt("Fischmarkt",
                         "5",
                         "50667",
                         "Köln");
  }

  public static RisikoOrt getRisikoortBachlage() {
    return new RisikoOrt("Erdweg",
                         "1",
                         "50769",
                         "Köln");
  }

  public static RisikoOrt getRisikoortInsel() {
    return new RisikoOrt("Hauptstr.",
                         "1",
                         "17406",
                         "Rankwitz");
  }

}
