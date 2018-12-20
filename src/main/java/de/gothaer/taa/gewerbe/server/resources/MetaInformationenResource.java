package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsKeyValue;
import de.gothaer.gwt.gxt.config.shared.model.config.GsFieldConfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.Antrag;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.RisikoOrt;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.gebaeude.GebaeudeVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.staticEnums.WertermittlungsType;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaMetaInformationenRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaMetaInformationenResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.widgetconfig.WidgetAnnotationConstants;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

import static de.gothaer.taa.gewerbe.ui.shared.s2.widgetconfig.WidgetAnnotationConstants.*;

@Path("/MetaInformationen")
public class MetaInformationenResource {

  @POST
  @Path("/getMetaInformationen")
  @Produces("application/json")
  public TaaMetaInformationenResponse getMetaInformationen(TaaMetaInformationenRequest request) {

    TaaMetaInformationenResponse response = new TaaMetaInformationenResponse();

    response.setFieldConfig(createResponse(request.getWidget(),
                                           request.getAntrag(),
                                           request.getRisikoortId()));

    Status status = new Status();
    status.setReturncode(ReturnCode.OK);
    response.setStatus(status);

    return response;
  }

  private List<GsFieldConfiguration> createResponse(List<String> widgets,
                                                    Antrag antrag,
                                                    String risikoOrtId) {
    WertermittlungsType wertermittlungsType = risikoOrtId == null ? WertermittlungsType.KEINE_AUSWAHL : antrag.getGebaeudeVersicherung()
                                                                                                              .getVersichertesGebaeude(risikoOrtId)
                                                                                                              .getWertermittlungType();

    List<GsFieldConfiguration> responseList = new ArrayList<>();
    for (String key : widgets) {
      switch (key) {

        case WidgetAnnotationConstants.BHV_DECKUNGSSUMME:
          responseList.add(getDeckungssumme(WidgetAnnotationConstants.BHV_DECKUNGSSUMME));
          break;
        case WidgetAnnotationConstants.BETRIEBSSCHLIESSUNG_SELBSTBETEILIGUNG:
          responseList.add(getSBT(WidgetAnnotationConstants.BETRIEBSSCHLIESSUNG_SELBSTBETEILIGUNG));
          break;
        case WidgetAnnotationConstants.BETRIEBSSCHLIESSUNG_PLUS_BAUSTEIN:
          responseList.add(getMLP(WidgetAnnotationConstants.BETRIEBSSCHLIESSUNG_PLUS_BAUSTEIN));
          break;
        case WidgetAnnotationConstants.BHV_SELBSTBETEILIGUNG:
          responseList.add(getSBT(WidgetAnnotationConstants.BHV_SELBSTBETEILIGUNG));
          break;
        case WidgetAnnotationConstants.BHV_PLUS_BAUSTEIN:
          responseList.add(getMLP(WidgetAnnotationConstants.BHV_PLUS_BAUSTEIN));
          break;
        case WidgetAnnotationConstants.PRIVATE_RISIKEN_DECKUNGSSUMME:
          responseList.add(getDeckungssumme(WidgetAnnotationConstants.PRIVATE_RISIKEN_DECKUNGSSUMME));
          break;
        case WidgetAnnotationConstants.PRIVATE_RISIKEN_SELBSTBETEILIGUNG:
          responseList.add(getSBT(WidgetAnnotationConstants.PRIVATE_RISIKEN_SELBSTBETEILIGUNG));
          break;
        case WidgetAnnotationConstants.PRIVATE_RISIKEN_PLUS_BAUSTEIN:
          responseList.add(getMLP(WidgetAnnotationConstants.PRIVATE_RISIKEN_PLUS_BAUSTEIN));
          break;
        case WidgetAnnotationConstants.GEBAEUDE_SELBSTBETEILIGUNG:
          responseList.add(getSBT(WidgetAnnotationConstants.GEBAEUDE_SELBSTBETEILIGUNG));
          break;
        case WidgetAnnotationConstants.INHALT_SELBSTBETEILIGUNG:
          responseList.add(getSBT(WidgetAnnotationConstants.INHALT_SELBSTBETEILIGUNG));
          break;
        case WidgetAnnotationConstants.ELEKTRO_SELBSTBETEILIGUNG:
          responseList.add(getSBT(WidgetAnnotationConstants.ELEKTRO_SELBSTBETEILIGUNG));
          break;
        case WidgetAnnotationConstants.PHOTOVOLTAIK_SELBSTBETEILIGUNG:
          responseList.add(getSBT(WidgetAnnotationConstants.PHOTOVOLTAIK_SELBSTBETEILIGUNG));
          break;
        case WidgetAnnotationConstants.MASCHINEN_STATIONAER_SELBSTBETEILIGUNG:
          responseList.add(getSBT(WidgetAnnotationConstants.MASCHINEN_STATIONAER_SELBSTBETEILIGUNG));
          break;
        case WidgetAnnotationConstants.MASCHINEN_STATIONAER_PLUS_BAUSTEIN:
          responseList.add(getMLP(WidgetAnnotationConstants.MASCHINEN_STATIONAER_PLUS_BAUSTEIN));
          break;
        case WidgetAnnotationConstants.MASCHINEN_FAHRBAR_SELBSTBETEILIGUNG:
          responseList.add(getSBT(WidgetAnnotationConstants.MASCHINEN_FAHRBAR_SELBSTBETEILIGUNG));
          break;
        case WidgetAnnotationConstants.WERKVERKEHR_SELBSTBETEILIGUNG:
          responseList.add(getSBT(WidgetAnnotationConstants.WERKVERKEHR_SELBSTBETEILIGUNG));
          break;
        case WidgetAnnotationConstants.VSH_DECKUNGSSUMME:
          responseList.add(getDeckungssumme(WidgetAnnotationConstants.VSH_DECKUNGSSUMME));
          break;
        case WidgetAnnotationConstants.VSH_IMMOBILIENVERWALTER_DECKUNGSSUMME_PFLICHT:
          responseList.add(getDeckungssummeImmobilienVerwalterPflichtdeckung(WidgetAnnotationConstants.VSH_IMMOBILIENVERWALTER_DECKUNGSSUMME_PFLICHT));
          break;
        case WidgetAnnotationConstants.VSH_SELBSTBETEILIGUNG:
          responseList.add(getSBT(WidgetAnnotationConstants.VSH_SELBSTBETEILIGUNG));
          break;
        case WidgetAnnotationConstants.VSH_PLUS_BAUSTEIN:
          responseList.add(getMLP(WidgetAnnotationConstants.VSH_PLUS_BAUSTEIN));
          break;
        case PHOTOVOLTAIK_PLUS_BAUSTEIN:
          responseList.add(getMLP(PHOTOVOLTAIK_PLUS_BAUSTEIN));
          break;
        case WidgetAnnotationConstants.GEBAEUDE_VERSICHERUNG_ZUM:
          responseList.add(getGebDynVal(WidgetAnnotationConstants.GEBAEUDE_VERSICHERUNG_ZUM));
          break;
        case GEBAEUDE_PLUS_BAUSTEIN:
          responseList.add(getMLP(GEBAEUDE_PLUS_BAUSTEIN));
          break;
        case INHALT_PLUS_BAUSTEIN:
          responseList.add(getMLP(INHALT_PLUS_BAUSTEIN));
          break;
        case ELEKTRO_PLUS_BAUSTEIN:
          responseList.add(getMLP(ELEKTRO_PLUS_BAUSTEIN));
          break;
        case WERKVERKEHR_PLUS_BAUSTEIN:
          responseList.add(getMLP(WERKVERKEHR_PLUS_BAUSTEIN));
          break;
        case WidgetAnnotationConstants.WERKVERKEHR_GEFUERHRTE_GUETER:
          responseList.add(getGefuehrteGueter(WidgetAnnotationConstants.WERKVERKEHR_GEFUERHRTE_GUETER));
          break;
        case WidgetAnnotationConstants.WERKVERKEHR_LADUNGSMAXIMUM:
          responseList.add(getLadungsMaximum(WidgetAnnotationConstants.WERKVERKEHR_LADUNGSMAXIMUM));
          break;
        case WidgetAnnotationConstants.VSH_RECHTSANWALT_EINZELKANZLEI:
          responseList.add(getVshVisbleField(WidgetAnnotationConstants.VSH_RECHTSANWALT_EINZELKANZLEI,
                                             antrag));
          break;
        case WidgetAnnotationConstants.VSH_STEUERBERATER:
          responseList.add(getVshVisbleField(WidgetAnnotationConstants.VSH_STEUERBERATER,
                                             antrag));
          break;
        case WidgetAnnotationConstants.VSH_MB_NAMENTLICHE_NENNUNGEN: {
          GsFieldConfiguration config = new GsFieldConfiguration(key);
          config.setVisible(true);
          responseList.add(config);
          break;
        }
        case WidgetAnnotationConstants.VSH_VERSICHERUNGSBESTAETIGUNG_EMPFAENGER: {
          GsFieldConfiguration config = new GsFieldConfiguration(key);
          config.setVisible(true);
          config.getKeyValueList()
                .add(new GsKeyValue("2",
                                    "Typ2"));
          responseList.add(config);
          break;
        }
        case WidgetAnnotationConstants.VSH_RECHTSANWAELTE_NICHT_NACH_AUSSEN:
          responseList.add(getVshVisbleField(WidgetAnnotationConstants.VSH_RECHTSANWAELTE_NICHT_NACH_AUSSEN,
                                             antrag));
          break;
        case WidgetAnnotationConstants.VSH_NAMENTLICHE_NENNUNG_FUER:
          responseList.add(getVshVisbleField(WidgetAnnotationConstants.VSH_NAMENTLICHE_NENNUNG_FUER,
                                             antrag));
          break;
        case WidgetAnnotationConstants.VSH_UMSATZ_IMMOBILIENVERWALTER_PFLICHT: {
          GsFieldConfiguration config = new GsFieldConfiguration(key);
          config.setLabel("Umsatz Immoverwalter Pflicht");
          config.setReadOnly(false);
          config.setVisible(true);
          config.setRequired(false);
          responseList.add(config);
          break;
        }
        case WidgetAnnotationConstants.VSH_HEADLINE: {
          GsFieldConfiguration config = new GsFieldConfiguration(key);
          config.setLabel("Test zur VSH Headline");
          config.setVisible(true);
          responseList.add(config);
          break;
        }
        case WidgetAnnotationConstants.BHV_THV_PFERDE: {
          GsFieldConfiguration config = new GsFieldConfiguration(key);
          config.setLabel("Perddee ...");
          config.setReadOnly(false);
          config.setVisible(true);
          config.setRequired(false);
          responseList.add(config);
          break;
        }

        case WidgetAnnotationConstants.MASCHINEN_STATIONAER_ERWEITERTEDECKUNG_EINSCHLUSS: {
          GsFieldConfiguration config = new GsFieldConfiguration(key);
          config.setVisible(true);
          config.setLabel("Erweit.Deck. MaschStat. aus MetaInfo");
          config.setDefaultvalue("Ja");
          responseList.add(config);
          break;
        }

        case WidgetAnnotationConstants.UNTERSCHRIFT_ORT: {
          GsFieldConfiguration config = new GsFieldConfiguration(key);
          config.setVisible(true);
          config.setLabel("UnterschriftsOrt");
          config.setRegEx("^[a-zA-Z]*$");
          responseList.add(config);
          break;
        }
        case WidgetAnnotationConstants.GU_ART_DER_TAEITIGKEIT: {
          GsFieldConfiguration config = new GsFieldConfiguration(key);
          config.setVisible(true);
          config.setLabel("Art der Tätigkeit :-)");
          config.setRequired(true);
          config.setKeyValueList(new ArrayList<GsKeyValue>() {{
            add(new GsKeyValue("Geschäftsführer",
                               "Geschäftsführer"));
            add(new GsKeyValue("kaufmännische Angestellte",
                               "kaufmännische Angestellte"));
            add(new GsKeyValue("gewerbliche Angestellte",
                               "gewerbliche Angestellte"));
            add(new GsKeyValue("Programmierer",
                               "Programmierer"));
            add(new GsKeyValue("Auszubildende",
                               "Auszubildende"));
          }});
          responseList.add(config);
          break;
        }
        case WidgetAnnotationConstants.GU_GEFAHREN_GRUPPE: {
          GsFieldConfiguration config = new GsFieldConfiguration(key);
          config.setVisible(true);
          config.setLabel("Gefahrengruppe");
          config.setRequired(true);
          config.setKeyValueList(new ArrayList<GsKeyValue>() {{
            add(new GsKeyValue("A",
                               "A"));
            add(new GsKeyValue("B",
                               "B"));
          }});
          responseList.add(config);
          break;
        }
        case WidgetAnnotationConstants.GU_DECKUNGSUMFANG: {
          GsFieldConfiguration config = new GsFieldConfiguration(key);
          config.setVisible(true);
          config.setLabel("Deckungsumfang");
          config.setRequired(true);
          config.setKeyValueList(new ArrayList<GsKeyValue>() {{
            add(new GsKeyValue("Ja",
                               "Ja"));
            add(new GsKeyValue("Nein",
                               "Nein"));
          }});
          responseList.add(config);
          break;
        }
        case WidgetAnnotationConstants.GU_ANZAHL_PERSONEN: {
          GsFieldConfiguration config = new GsFieldConfiguration(key);
          config.setVisible(true);
          config.setLabel("Anzahl versicherter Personen!");
          config.setRequired(true);
          config.setMaxvalue(100d);
          responseList.add(config);
          break;
        }
      }

      switch (key) {
        case WidgetAnnotationConstants.RISIKOORT_BAUARTKLASSE:
          responseList.add(getLabel(antrag,
                                    wertermittlungsType,
                                    WidgetAnnotationConstants.RISIKOORT_BAUARTKLASSE));
          break;
        case WidgetAnnotationConstants.RISIKOORT_BAUJAHR:
          responseList.add(getLabel(antrag,
                                    wertermittlungsType,
                                    WidgetAnnotationConstants.RISIKOORT_BAUJAHR));
          break;
        case WidgetAnnotationConstants.RISIKOORT_PHOTOVOLTAIK:
          responseList.add(getLabel(antrag,
                                    wertermittlungsType,
                                    WidgetAnnotationConstants.RISIKOORT_PHOTOVOLTAIK));

          break;
        case WidgetAnnotationConstants.RISIKOORT_SOLARTHERMIE:
          responseList.add(getLabel(antrag,
                                    wertermittlungsType,
                                    WidgetAnnotationConstants.RISIKOORT_SOLARTHERMIE));

          break;
        case WidgetAnnotationConstants.RISIKOORT_TIEFGARAGE:
          responseList.add(getLabel(antrag,
                                    wertermittlungsType,
                                    WidgetAnnotationConstants.RISIKOORT_TIEFGARAGE));

          break;
        case WidgetAnnotationConstants.RISIKOORT_LAENGE:
          responseList.add(getLabel(antrag,
                                    wertermittlungsType,
                                    WidgetAnnotationConstants.RISIKOORT_LAENGE));
          break;
        case WidgetAnnotationConstants.RISIKOORT_BREITE:
          responseList.add(getLabel(antrag,
                                    wertermittlungsType,
                                    WidgetAnnotationConstants.RISIKOORT_BREITE));
          break;
        case WidgetAnnotationConstants.RISIKOORT_HOEHE:
          responseList.add(getLabel(antrag,
                                    wertermittlungsType,
                                    WidgetAnnotationConstants.RISIKOORT_HOEHE));
          break;
        case WidgetAnnotationConstants.RISIKOORT_ANZAHLSTOCKWERKE:
          responseList.add(getLabel(antrag,
                                    wertermittlungsType,
                                    WidgetAnnotationConstants.RISIKOORT_ANZAHLSTOCKWERKE));
          break;
        case WidgetAnnotationConstants.RISIKOORT_NUTZFLAECHE:
          responseList.add(getLabel(antrag,
                                    wertermittlungsType,
                                    WidgetAnnotationConstants.RISIKOORT_NUTZFLAECHE));
          break;
        case WidgetAnnotationConstants.RISIKOORT_WOHNFLAECHE:
          responseList.add(getLabel(antrag,
                                    wertermittlungsType,
                                    WidgetAnnotationConstants.RISIKOORT_WOHNFLAECHE));
          break;
        case WidgetAnnotationConstants.RISIKOORT_MANUELLEEINGABE:
          responseList.add(getLabel(antrag,
                                    wertermittlungsType,
                                    WidgetAnnotationConstants.RISIKOORT_MANUELLEEINGABE));
          break;
        case WidgetAnnotationConstants.RISIKOORT_VORSTEUERABZUG_BERECHTIGUNG:
          responseList.add(getLabel(antrag,
                                    wertermittlungsType,
                                    WidgetAnnotationConstants.RISIKOORT_VORSTEUERABZUG_BERECHTIGUNG));
          break;
        case WidgetAnnotationConstants.RISIKOORT_BAUAUSFUEHRUNG:
          responseList.add(getLabel(antrag,
                                    wertermittlungsType,
                                    WidgetAnnotationConstants.RISIKOORT_BAUAUSFUEHRUNG));
          break;
        case WidgetAnnotationConstants.RISIKOORT_GEBAEUDEART:
          responseList.add(getLabel(antrag,
                                    wertermittlungsType,
                                    WidgetAnnotationConstants.RISIKOORT_GEBAEUDEART));
          break;
      }
    }
    return responseList;
  }

  private GsFieldConfiguration getDeckungssumme(String id) {
    GsFieldConfiguration discription = new GsFieldConfiguration(id);
    discription.setDefaultvalue("Cov_CTPL5_GCOM");
    discription.setLabel("Deckungssumme");
    discription.setRequired(true);
    discription.setVisible(true);
    discription.getKeyValueList()
               .add(new GsKeyValue("Cov_CTPL5_GCOM",
                                   "5 Mio."));
    discription.getKeyValueList()
               .add(new GsKeyValue("Cov_CTPL10_GCOM",
                                   "10 Mio."));
    return discription;
  }

  private GsFieldConfiguration getDeckungssummeImmobilienVerwalterPflichtdeckung(String id) {
    GsFieldConfiguration discription = new GsFieldConfiguration(id);
    discription.setDefaultvalue("0");
    discription.setLabel("Deckungssumme Immobilien Verwalter Pflichtdeckung");
    discription.setRequired(true);
    discription.setVisible(true);
    discription.getKeyValueList()
               .add(new GsKeyValue("50",
                                   "50 Mio."));
    discription.getKeyValueList()
               .add(new GsKeyValue("100",
                                   "100 Mio."));
    discription.getKeyValueList()
               .add(new GsKeyValue("150",
                                   "150 Mio."));
    return discription;
  }

  private GsFieldConfiguration getSBT(String id) {
    GsFieldConfiguration discription = new GsFieldConfiguration(id);
    discription.setDefaultvalue("250");
    discription.setLabel("Selbstbeteiliigung für Tarifkonfiguraton");
    discription.setRequired(true);
    discription.setVisible(true);
    discription.getKeyValueList()
               .add(new GsKeyValue("0",
                                   "Keine Selbstbeteilung"));
    discription.getKeyValueList()
               .add(new GsKeyValue("250",
                                   "250 Euro"));
    discription.getKeyValueList()
               .add(new GsKeyValue("500",
                                   "500 Euro"));
    discription.getKeyValueList()
               .add(new GsKeyValue("1000",
                                   "1000 Euro"));
    if (id.equals(BHV_SELBSTBETEILIGUNG)) {
      discription.getKeyValueList()
                 .add(new GsKeyValue("2000",
                                     "2000 Euro"));
    }
    if (VSH_SELBSTBETEILIGUNG.equals(id)) {
      discription.getKeyValueList()
                 .add(new GsKeyValue("Relative Selbstbeteiligung",
                                     "Relative Selbstbeteiligung"));
    }
    //    if (id.equals(ELEKTRO_SELBSTBETEILIGUNG)) {
    //      discription.setVisible(false);
    //    }
    return discription;
  }

  private GsFieldConfiguration getMLP(String id) {
    GsFieldConfiguration discription = new GsFieldConfiguration(id);
    switch (id) {
      case INHALT_PLUS_BAUSTEIN: {
        discription.setDefaultvalue("true");
        discription.setLabel("Inhalt Mehrleistungspaket Vertragsteil");
        discription.setVisible(true);
        break;
      }
      case BHV_PLUS_BAUSTEIN: {
        discription.setDefaultvalue("0");
        discription.setLabel("BHV Mehrleistungspaket Vertragsteil");
        discription.setVisible(true);
        break;
      }
      case PRIVATE_RISIKEN_PLUS_BAUSTEIN: {
        discription.setDefaultvalue("true");
        discription.setLabel("Private Risiken Mehrleistungspaket Vertragsteil");
        discription.setVisible(true);
        break;
      }
      case PHOTOVOLTAIK_PLUS_BAUSTEIN:
        discription.setLabel("Photovoltaik Mehrleistungspaket Vertragsteil");
        discription.setVisible(true);
        break;
      case GEBAEUDE_PLUS_BAUSTEIN: {
        discription.setDefaultvalue("false");
        discription.setLabel("Gebäude Mehrleistungspaket Vertragsteil");
        break;
      }
      case ELEKTRO_PLUS_BAUSTEIN: {
        discription.setDefaultvalue("false");
        discription.setLabel("Elektro Mehrleistungspaket Vertragsteil");
        discription.setVisible(true);
        break;
      }
      case MASCHINEN_FAHRBAR_PLUS_BAUSTEIN: {
        discription.setDefaultvalue("false");
        discription.setLabel("Machinen Mobil Mehrleistungspaket Vertragsteil");
        discription.setVisible(true);
        break;
      }
      case MASCHINEN_STATIONAER_PLUS_BAUSTEIN: {
        discription.setDefaultvalue("false");
        discription.setLabel("Machinen Stationaer Mehrleistungspaket Vertragsteil");
        discription.setVisible(true);
        break;
      }
      case WERKVERKEHR_PLUS_BAUSTEIN: {
        discription.setDefaultvalue("false");
        discription.setLabel("Werkverkehr Mehrleistungspaket Vertragsteil");
        discription.setVisible(true);
        break;
      }
      case VSH_PLUS_BAUSTEIN: {
        discription.setDefaultvalue("false");
        discription.setLabel("VSH Mehrleistungspaket Vertragsteil");
        discription.setVisible(true);
        break;
      }
    }
    discription.setRequired(false);
    return discription;
  }

  private GsFieldConfiguration getGebDynVal(String id) {
    GsFieldConfiguration discription = new GsFieldConfiguration(id);
    //    discription.setDefaultvalue("form2");
    discription.setLabel("Versicherung zum");
    discription.setRequired(true);
    discription.setVisible(true);
    discription.setDefaultvalue(GebaeudeVersicherung.GEBAEUDE_VERSICHERUNG_ZUM_GLEITENDEN_NEUWERT_KEY);
    discription.getKeyValueList()
               .add(new GsKeyValue(GebaeudeVersicherung.GEBAEUDE_VERSICHERUNG_ZUM_NEUWERT_KEY,
                                   "Versicherung zum Neuwert"));
    discription.getKeyValueList()
               .add(new GsKeyValue(GebaeudeVersicherung.GEBAEUDE_VERSICHERUNG_ZUM_GLEITENDEN_NEUWERT_KEY,
                                   "Versicherung zum gleitenden Neuwert"));
    return discription;
  }

  private GsFieldConfiguration getGefuehrteGueter(String id) {
    GsFieldConfiguration discription = new GsFieldConfiguration(id);
    discription.setDefaultvalue("10000");
    discription.setLabel("Geführte Güter");
    discription.setRequired(true);
    discription.setVisible(true);
    discription.getKeyValueList()
               .add(new GsKeyValue("1",
                                   "Ladung Typ 01"));
    discription.getKeyValueList()
               .add(new GsKeyValue("2",
                                   "Ladung Typ 02"));
    discription.getKeyValueList()
               .add(new GsKeyValue("3",
                                   "Ladung Typ 03"));
    discription.getKeyValueList()
               .add(new GsKeyValue("4",
                                   "Ladung Typ 04"));
    discription.getKeyValueList()
               .add(new GsKeyValue("5",
                                   "Ladung Typ 05"));
    return discription;
  }

  private GsFieldConfiguration getLadungsMaximum(String id) {
    GsFieldConfiguration discription = new GsFieldConfiguration(id);
    discription.setLabel("Maximalladuing pro Fahrzeug");
    discription.setVisible(true);
    discription.setRequired(false);
    discription.setReadOnly(false);
    discription.setDefaultvalue("5000.0");
    for (int i = 1; i < 31; i++) {
      discription.getKeyValueList()
                 .add(new GsKeyValue(Integer.toString(i * 1000) + ".0",
                                     Integer.toString(i * 1000) + ".0"));
    }
    return discription;
  }

  private GsFieldConfiguration getVshVisbleField(String id,
                                                 Antrag antrag) {
    GsFieldConfiguration discription = new GsFieldConfiguration(id);
    String hauptTaetigkeitKey = antrag.getTaetigkeiten()
                                      .getHauptTaetigkeit()
                                      .getKey();
    if (WidgetAnnotationConstants.VSH_RECHTSANWALT_EINZELKANZLEI.equals(id)) {
      if ("Anwalt".equals(hauptTaetigkeitKey)) {
        discription.setVisible(true);
        discription.setRequired(true);
        discription.setReadOnly(true);
        discription.setDefaultvalue("2");
      } else {
        discription.setVisible(false);
      }
    } else if (WidgetAnnotationConstants.VSH_STEUERBERATER.equals(id)) {
      if ("Steuerberater".equals(hauptTaetigkeitKey)) {
        discription.setVisible(true);
        discription.setRequired(true);
        discription.setReadOnly(true);
        discription.setDefaultvalue("3");
      } else {
        discription.setVisible(false);
      }
    } else if (WidgetAnnotationConstants.VSH_RECHTSANWAELTE_NICHT_NACH_AUSSEN.equals(id)) {
      if ("Anwalt".equals(hauptTaetigkeitKey)) {
        discription.setVisible(true);
        discription.setRequired(true);
      } else {
        discription.setVisible(false);
        discription.setRequired(false);
      }
    } else if (WidgetAnnotationConstants.VSH_NAMENTLICHE_NENNUNG_FUER.equals(id)) {
      if ("Anwalt".equals(hauptTaetigkeitKey)) {
        ArrayList<GsKeyValue> keyValueList = new ArrayList<>();
        keyValueList.add(new GsKeyValue("1",
                                        "AnwaltTyp1"));
        keyValueList.add(new GsKeyValue("2",
                                        "AnwaltTyp2"));
        keyValueList.add(new GsKeyValue("3",
                                        "AnwaltTyp3"));
        discription.setKeyValueList(keyValueList);
      } else {
        ArrayList<GsKeyValue> keyValueList = new ArrayList<>();
        keyValueList.add(new GsKeyValue("1",
                                        "Typ1"));
        keyValueList.add(new GsKeyValue("2",
                                        "Typ2"));
        keyValueList.add(new GsKeyValue("3",
                                        "Typ3"));
        discription.setKeyValueList(keyValueList);
      }
    }
    return discription;
  }

  private GsFieldConfiguration getLabel(Antrag pAntrag,
                                        WertermittlungsType pWertermittlung,
                                        String pWidgetId) {

    GsFieldConfiguration discription = new GsFieldConfiguration(pWidgetId);
    discription.setVisible(true);

    if (WertermittlungsType.KEINE_AUSWAHL.equals(pWertermittlung)) {
      discription.setVisible(false);
    }

    if (pWertermittlung == null) {
      discription.setVisible(false);
      discription.setLabel("not visible");
    } else {
      switch (pWidgetId) {
        case WidgetAnnotationConstants.RISIKOORT_LAENGE:
          discription.setLabel("Länge des Gebäudes [in m]");
          switch (pWertermittlung) {
            case BRUTTO_FLAECHE:
              discription.setRequired(true);
              discription.setMaxLength(3);
              break;
            case BRUTTO_INHALT:
              discription.setVisible(true);
              break;
            default:
              discription.setVisible(false);
              break;
          }
          break;

        case WidgetAnnotationConstants.RISIKOORT_BREITE:
          discription.setLabel("Breite des Gebäudes [in m]");
          switch (pWertermittlung) {
            case BRUTTO_FLAECHE:
              discription.setVisible(true);
              break;
            case BRUTTO_INHALT:
              discription.setVisible(true);
              break;
            default:
              discription.setVisible(false);
              break;
          }
          break;

        case WidgetAnnotationConstants.RISIKOORT_HOEHE:
          discription.setLabel("Hähe des Geb. inkl. Keller u. Dachgeschoss [in m]");
          switch (pWertermittlung) {
            case BRUTTO_INHALT:
              discription.setVisible(true);
              break;
            default:
              discription.setVisible(false);
              break;
          }
          break;

        case WidgetAnnotationConstants.RISIKOORT_ANZAHLSTOCKWERKE:
          discription.setLabel("Anzahl der Stockwerke");
          switch (pWertermittlung) {
            case BRUTTO_FLAECHE:
              discription.setVisible(true);
              break;
            default:
              discription.setVisible(false);
              break;
          }
          break;

        case WidgetAnnotationConstants.RISIKOORT_NUTZFLAECHE:
          discription.setLabel("Summe der Nutzflächen (Nfl.) [in mä]");
          switch (pWertermittlung) {
            case NUTZFLAECHE:
              discription.setVisible(true);
              break;
            default:
              discription.setVisible(false);
              break;
          }
          break;

        case WidgetAnnotationConstants.RISIKOORT_WOHNFLAECHE:
          discription.setLabel("Summe der Wohnflächen (Nfl.) [in mä]");
          switch (pWertermittlung) {
            case WOHNRAUM:
              discription.setVisible(true);
              break;
            default:
              discription.setVisible(false);
              break;
          }
          break;

        case WidgetAnnotationConstants.RISIKOORT_MANUELLEEINGABE:
          discription.setLabel("manuelle Eingabe [in mä]");
          switch (pWertermittlung) {
            case NUTZFLAECHE:
            case BRUTTO_FLAECHE:
              discription.setVisible(false);
              break;
            default:
              discription.setVisible(true);
              break;
          }
          break;
        case WidgetAnnotationConstants.RISIKOORT_VORSTEUERABZUG_BERECHTIGUNG:
          discription.setLabel("Vorsteuerabzugsberechtigt");
          discription.setVisible(true);
          break;
      }
    }

    switch (pWidgetId) {
      case WidgetAnnotationConstants.RISIKOORT_BAUAUSFUEHRUNG:
        discription.setLabel("Bauausfährung");

        List<GsKeyValue> list2 = new ArrayList<>();
        list2.add(new GsKeyValue("1",
                                 "Einfache Bauausfährung / Ausstattungsqualität"));
        list2.add(new GsKeyValue("2",
                                 "Gute Bauausfährung / mittlere Ausstattungsqualität"));
        list2.add(new GsKeyValue("3",
                                 "Sehr gute Bauausfährung / gehobene Ausstattungsqualität"));
        list2.add(new GsKeyValue("4",
                                 "Sehr gute Bauausfährung / exklusive und aufwendige Ausstattungsqualität"));
        discription.setKeyValueList(list2);

        break;

      case WidgetAnnotationConstants.RISIKOORT_GEBAEUDEART:
        discription.setLabel("Gebäudeart");

        List<GsKeyValue> list3 = new ArrayList<>();
        list3.add(new GsKeyValue("1",
                                 "3.01 - Mehrparteien-Wohnhaus (3 - 6 Wohneinheiten)"));
        list3.add(new GsKeyValue("2",
                                 "3.02 - Mehrparteien-Wohnhaus (7 - 20 Wohneinheiten)"));
        list3.add(new GsKeyValue("3",
                                 "3.03 - Wohn- und Geschäftshäuser bis 20 WE/NE (Gewerbeanteil < 50%)"));
        list3.add(new GsKeyValue("4",
                                 "3.04 - Wohn- und Geschäftshäuser bis 20 WE/NE (Gewerbeanteil > 50%)"));
        list3.add(new GsKeyValue("5",
                                 "3.05 - Geschäftshäuser / Bank- und Sparkassengebäude (äberwiegend Gewerbenutzung)"));
        list3.add(new GsKeyValue("6",
                                 "3.06 - Verbrauchermärkte (Supermärkte, Discounter etc.)"));
        list3.add(new GsKeyValue("7",
                                 "3.07 - Pensionen/Hotels bis max. 40 Betten"));
        list3.add(new GsKeyValue("8",
                                 "3.08 - Gaststätte mit Beherbergung oder Wohneinheiten"));
        list3.add(new GsKeyValue("9",
                                 "3.09 - Seniorenwohnheime oder -Residenzen (ohne Pflegeheime)"));
        list3.add(new GsKeyValue("10",
                                 "3.10 - Fitnesscenter, Sporthallen"));
        list3.add(new GsKeyValue("11",
                                 "3.11 - Tennis-, Soccer- oder Squashhallen"));
        list3.add(new GsKeyValue("12",
                                 "3.12 - Bäro-/Verwaltungsgebäude"));
        list3.add(new GsKeyValue("13",
                                 "3.13 - Werkstatt- und Betriebsstätten (mehrgeschossig, geringer Hallenanteil)"));
        list3.add(new GsKeyValue("14",
                                 "3.14 - Werkstatt- und Produktionshallen (bis max. 15% Bäro-/Sozialtrakt-Anteil)"));
        list3.add(new GsKeyValue("15",
                                 "3.15 - Werkstatt- und Produktionshallen (mit mehr als 15% Bäro-/Sozialtrakt-Anteil)"));
        list3.add(new GsKeyValue("16",
                                 "3.16 - Lagerhallen (ohne Mischnutzung)"));
        list3.add(new GsKeyValue("17",
                                 "3.17 - Lagerhallen mit Mischnutzung bis 25%"));
        list3.add(new GsKeyValue("18",
                                 "3.18 - Garagengebäude / Fahrzeughallen (unbeheizt, incl. Wasser/Strom)"));
        list3.add(new GsKeyValue("19",
                                 "3.19 - Unterstände / einfachste, einseitig offene Hallen"));
        list3.add(new GsKeyValue("20",
                                 "3.20 - Autohäuser mit Werkstattbetrieb"));
        list3.add(new GsKeyValue("21",
                                 "3.21 - Schulen und Weiterbildungseinrichtungen"));
        list3.add(new GsKeyValue("22",
                                 "3.22 - Kindergärten und Betreuungseinrichtungen fär Kinder"));
        list3.add(new GsKeyValue("23",
                                 "3.23 - Bauernhäuser"));
        list3.add(new GsKeyValue("24",
                                 "3.24 - Guts- und Herrenhäuser"));
        list3.add(new GsKeyValue("25",
                                 "3.25 - Fachwerkshäuser (äberwiegend Wohnnutzung)"));
        list3.add(new GsKeyValue("26",
                                 "3.26 - Stallgebäude"));
        list3.add(new GsKeyValue("27",
                                 "3.27 - Scheunengebäude"));
        list3.add(new GsKeyValue("28",
                                 "3.28 - Reithallen"));
        list3.add(new GsKeyValue("29",
                                 "3.29 - Hochgaragen und Parkhäuser"));
        list3.add(new GsKeyValue("30",
                                 "3.30 - Tiefgaragen"));
        discription.setKeyValueList(list3);

        break;

      case WidgetAnnotationConstants.RISIKOORT_BAUJAHR:
        discription.setVisible(getVisibilityObjektDaten(pAntrag));
        discription.setLabel("Baujahr!");
        discription.setMinvalue(123d);
        discription.setVisible(true);
        break;

      case WidgetAnnotationConstants.RISIKOORT_BAUARTKLASSE:
        discription.setLabel("BAK");

        List<GsKeyValue> list = new ArrayList<>();
        list.add(new GsKeyValue("1",
                                "Bauartklasse 1"));
        list.add(new GsKeyValue("2",
                                "Bauartklasse 2"));
        list.add(new GsKeyValue("3",
                                "Bauartklasse 3"));

        discription.setKeyValueList(list);
        discription.setVisible(true);
        break;

      case WidgetAnnotationConstants.RISIKOORT_PHOTOVOLTAIK:
        discription.setVisible(getVisibilityObjektDaten(pAntrag));
        discription.setLabel("Photovoltaik");
        discription.setDefaultvalue("Ja");
        discription.setVisible(true);
        break;

      case WidgetAnnotationConstants.RISIKOORT_SOLARTHERMIE:
        discription.setVisible(getVisibilityObjektDaten(pAntrag));
        discription.setLabel("Solarthermie");
        discription.setDefaultvalue("Nein");
        discription.setVisible(true);
        break;

      case WidgetAnnotationConstants.RISIKOORT_TIEFGARAGE:
        discription.setVisible(getVisibilityObjektDaten(pAntrag));
        discription.setLabel("Tiefgarage");
        discription.setDefaultvalue("Nein");
        discription.setVisible(true);
        break;

      default:
        //    discription.setLabel("n.a.");
        break;
    }
    // discription.setLabel("");
    // discription.setLabel("Dyn. " + pWidgetId.substring(pWidgetId.length() - 1));
    // discription.setDefaultvalue("0");
    // discription.getKeyValueList()
    // .add(new GsKeyValue("0",
    // "00"));
    return discription;
  }

  public boolean getVisibilityObjektDaten(Antrag antrag) {
    if (antrag.hasInhaltsVersicherung()) {
      for (RisikoOrt risikoOrt : antrag.getRisikoOrte()) {
        if (antrag.getInhaltsVersicherung()
                  .getVersichertesGebaeude(risikoOrt.getId()) != null) {
          if (antrag.hasGebaeudeVersicherung()) {
            if (antrag.getGebaeudeVersicherung()
                      .getVersichertesGebaeude(risikoOrt.getId()) != null) {
              return true;
            }
          }
          if (antrag.hasPhotovoltaikVersicherung()) {
            if (antrag.getPhotovoltaikVersicherung()
                      .getVersichertesGebaeude(risikoOrt.getId()) != null) {
              return true;
            }
          }
          if (antrag.hasGebaeudeVersicherung()) {
            if (antrag.getGebaeudeVersicherung()
                      .getVersichertesGebaeude(risikoOrt.getId()) != null) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

}
