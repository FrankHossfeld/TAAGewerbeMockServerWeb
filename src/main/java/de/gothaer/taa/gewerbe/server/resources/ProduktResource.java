package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsKeyValue;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.Antrag;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.ProduktConfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.TAA;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaGetAlleProdukteRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaProduktListeRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaGetAlleProdukteResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaProduktListeResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.models.ProduktInfo;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/produkte")
public class ProduktResource {

  @POST
  @Path("/getProdukteList")
  @Produces("application/json")
  public TaaProduktListeResponse getProdukteList(TaaProduktListeRequest request) {
    return createResponse(request);
  }

  private TaaProduktListeResponse createResponse(TaaProduktListeRequest request) {

    TaaProduktListeResponse response = new TaaProduktListeResponse();
    Status status = new Status();

    try {
      response.setProduktList(getProdukte(request));
      status.setReturncode(ReturnCode.OK);

    } catch (Exception pException) {
      status.setMeldungTechnisch("Technischer Fehler beim Lesen der Produktliste!" + pException.getMessage(),
                                 null);
    }

    response.setStatus(status);
    return response;
  }

  //TODO Neues Produkt: hier das Produkt bekannt machen bzw. auf Server Seite
  private List<ProduktConfiguration> getProdukte(TaaProduktListeRequest request) {
    if (TAA.isVerkaufsProduktCYB(request.getAntrag()
                                        .getVerkaufsProdukt())) {
      return getProduktConfigurationCyber(request);
    }
    if (TAA.isVerkaufsProduktGU(request.getAntrag()
                                       .getVerkaufsProdukt())) {
      return getProduktConfigurationGu(request);
    } else {
      return getProduktConfigurationGgpAndVsh(request);
    }
  }

  private List<ProduktConfiguration> getProduktConfigurationGu(TaaProduktListeRequest request) {
    List<ProduktConfiguration> produktConfigurations = new ArrayList<>();
    ProduktConfiguration configuration = new ProduktConfiguration();
    configuration.setProdukt(new GsKeyValue("PR_GU",
                                            "Gruppenunfall"));
    produktConfigurations.add(new ProduktConfiguration(new GsKeyValue("PR_GU",
                                                                      "Gruppenunfall"),
                                                       request.getAntrag()
                                                              .getVersicherungsBeginn()));
    return produktConfigurations;
  }

  private List<ProduktConfiguration> getProduktConfigurationCyber(TaaProduktListeRequest request) {
    List<ProduktConfiguration> produktConfigurations = new ArrayList<>();
    ProduktConfiguration configuration = new ProduktConfiguration();
    configuration.setProdukt(new GsKeyValue("PR_CYB",
                                            "Cyber"));
    produktConfigurations.add(new ProduktConfiguration(new GsKeyValue("PR_CYB",
                                                                      "Cyber"),
                                                       request.getAntrag()
                                                              .getVersicherungsBeginn()));
    return produktConfigurations;
  }

  private List<ProduktConfiguration> getProduktConfigurationGgpAndVsh(TaaProduktListeRequest request) {
    List<ProduktConfiguration> configuration = new ArrayList<>();
    switch (request.getAntrag()
                   .getTaetigkeiten()
                   .getHauptTaetigkeit()
                   .getValue()) {
      case "Buchhandel":
        configuration.add(getProduktConfiguration(request,
                                                  TAA.BETRIEBSSCHLIESSUNG,
                                                  "Tango 1"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.ELEKTRONIK,
                                                  "Tango 1"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.GEBAEUDE,
                                                  "Tango 1"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.INHALT,
                                                  "Tango 1"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.MASCHINEN_FAHRBAR,
                                                  "Tango 1"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.MASCHINEN_STATIONAER,
                                                  "Tango 1"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.PHOTOVOLTAIK,
                                                  "Tango 1"));
        break;
      case "Tiefbaubetrieb":
        configuration.add(getProduktConfiguration(request,
                                                  TAA.BHV,
                                                  "42"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.ELEKTRONIK,
                                                  "42"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.GEBAEUDE,
                                                  "42"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.INHALT,
                                                  "42"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.PHOTOVOLTAIK,
                                                  "42"));
        break;
      case "Catering":
        configuration.add(getProduktConfiguration(request,
                                                  TAA.BHV,
                                                  "Marvin"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.BETRIEBSSCHLIESSUNG,
                                                  "Marvin"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.GEBAEUDE,
                                                  "Marvin"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.INHALT,
                                                  "Marvin"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.MASCHINEN_FAHRBAR,
                                                  "Marvin"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.MASCHINEN_STATIONAER,
                                                  "Marvin"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.PHOTOVOLTAIK,
                                                  "Marvin"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.PRIVATE_RISIKEN,
                                                  "Marvin"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.WERKVERKEHR,
                                                  "Marvin"));
        break;
      case "Contactlinsenstudio":
        configuration.add(getProduktConfiguration(request,
                                                  TAA.GEBAEUDE,
                                                  "Triton"));
        break;
      case "Altenheim":
        configuration.add(getProduktConfiguration(request,
                                                  TAA.BHV,
                                                  "Triton"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.GEBAEUDE,
                                                  "Triton"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.INHALT,
                                                  "Triton"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.PHOTOVOLTAIK,
                                                  "Triton"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.PRIVATE_RISIKEN,
                                                  "Triton"));
        break;
      case "Ambulanter Pflegedienst":
        configuration.add(getProduktConfiguration(request,
                                                  TAA.BHV,
                                                  "Neptune"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.INHALT,
                                                  "Neptune"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.PRIVATE_RISIKEN,
                                                  "Neptune"));
        break;
      case "Kfz-Handel":
        configuration.add(getProduktConfiguration(request,
                                                  TAA.GEBAEUDE,
                                                  "Pluto"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.INHALT,
                                                  "Pluto"));
        break;
      case "Anwalt":
        configuration.add(getProduktConfiguration(request,
                                                  TAA.VSH_RECHTSANWALT,
                                                  "Venus"));
        break;
      case "Steuerberater":
        configuration.add(getProduktConfiguration(request,
                                                  TAA.VSH_IDV,
                                                  "Merkur"));
        break;
      case "Unternehmensberater":
        configuration.add(getProduktConfiguration(request,
                                                  TAA.VSH,
                                                  "Jupiter"));
        break;
      default:
        configuration.add(getProduktConfiguration(request,
                                                  TAA.BHV,
                                                  "Top Seller"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.BETRIEBSSCHLIESSUNG,
                                                  "Top Seller"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.ELEKTRONIK,
                                                  "Top Seller"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.GEBAEUDE,
                                                  "Top Seller"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.INHALT,
                                                  "Top Seller"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.MASCHINEN_FAHRBAR,
                                                  "Top Seller"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.MASCHINEN_STATIONAER,
                                                  "Top Seller"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.PHOTOVOLTAIK,
                                                  "Top Seller"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.PRIVATE_RISIKEN,
                                                  "Top Seller"));
        configuration.add(getProduktConfiguration(request,
                                                  TAA.WERKVERKEHR,
                                                  "Top Seller"));
        break;
    }
    return configuration;
  }

  private ProduktConfiguration getProduktConfiguration(TaaProduktListeRequest request,
                                                       String produktKey,
                                                       String generationsName) {
    GsKeyValue produkt = new GsKeyValue(produktKey,
                                        getProduktValue(produktKey));
    return new ProduktConfiguration(produkt,
                                    this.getGenerationsBestimmungsDatum(request.getAntrag(),
                                                                        produkt),
                                    generationsName);
  }

  private String getProduktValue(String produktKey) {
    switch (produktKey) {
      case TAA.BETRIEBSSCHLIESSUNG:
        return "Betriebsschliessung";
      case TAA.BHV:
        return "Betriebshaftpflicht";
      case TAA.ELEKTRONIK:
        return "Elektronik";
      case TAA.GEBAEUDE:
        return "Gebäude";
      case TAA.GRUPPEN_UNFALL:
        return "Gruppenunfall";
      case TAA.INHALT:
        return "Inhalt";
      case TAA.MASCHINEN_FAHRBAR:
        return "Maschinen mobil";
      case TAA.MASCHINEN_STATIONAER:
        return "Maschinen stationär";
      case TAA.PHOTOVOLTAIK:
        return "Photovoltaik";
      case TAA.PRIVATE_RISIKEN:
        return "Private Risiken";
      case TAA.VSH:
        return "VSH Unternehmensberater";
      case TAA.VSH_IDV:
        return "VSH Steuerberater";
      case TAA.VSH_RECHTSANWALT:
        return "VSH Anwalt";
      case TAA.WERKVERKEHR:
        return "Werkverkehr";
    }
    return "Prouukt Value not defined!";
  }

  private Date getGenerationsBestimmungsDatum(Antrag antrag,
                                              GsKeyValue produkt) {
    if (TAA.isBetriebshaftflicht(produkt)) {
      if (antrag.isNeuAntrag()) {
        return antrag.getErstelldatum();
        //    } else {
        //      if (pGBD != null) {
        //        return pGBD;
        //      } else {
        //        if (pAntrag.getRaVeBeginndatum() != null) {
        //          return pAntrag.getRaVeBeginndatum();
        //        } else {
        //          return pAntrag.getErstelldatum();
        //        }
        //      }
      }
    }
    return antrag.getErstelldatum();
  }

  @POST
  @Path("/getAlleProdukte")
  @Produces("application/json")
  //TODO Neues Produkt: hier das Produkt bekannt machen bzw. auf Server Seite
  public TaaGetAlleProdukteResponse getAlleProdukte(TaaGetAlleProdukteRequest pRequest) {

    TaaGetAlleProdukteResponse tResponse = new TaaGetAlleProdukteResponse();

    String verkaufsproduktKey = pRequest.getVerkaufsProdukt()
                                        .getKey();
    if (TAA.VERKAUFSPRODUKT_GRUPPEN_UNFALL.equals(verkaufsproduktKey)) {
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_GU",
                                    "Gruppenunfall",
                                    null));
    }
    if (TAA.VERKAUFSPRODUKT_CYBER.equals(verkaufsproduktKey)) {
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_CYB",
                                    "Cyber",
                                    null));
    }
    if (TAA.VERKAUFSPRODUKT_VSH.equals(verkaufsproduktKey)) {
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_VSH",
                                    "Vermögensschadenhaftpflicht",
                                    null));
    } else {
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_INH_FEU",
                                    "Inhaltsversicherung - Feuer",
                                    "Inhalt"));
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_INH_ED",
                                    "Inhaltsversicherung - Einbruchdiebstahl",
                                    "Inhalt"));
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_INH_LTW",
                                    "Inhaltsversicherung - Leitungswasser",
                                    "Inhalt"));
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_INH_STU",
                                    "Inhaltsversicherung - Sturm",
                                    "Inhalt"));
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_INH_ELE",
                                    "Inhaltsversicherung - Elementar",
                                    "Inhalt"));
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_INH_ERW_DCK",
                                    "Inhaltsversicherung - Erweiterte Deckung",
                                    "Inhalt"));
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_INH_UNB_GEF",
                                    "Inhaltsversicherung - Unbenannte Gefahren",
                                    "Inhalt"));
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_INH_GLAS",
                                    "Inhaltsversicherung - Glas",
                                    "Inhalt"));
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_GEB_FEU",
                                    "Gebäudeversicherung - Feuer",
                                    "Gebäude"));
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_GEB_LTW",
                                    "Gebäudeversicherung - Leitungswasser",
                                    "Gebäude"));
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_GEB_ELE",
                                    "Gebäudeversicherung - Elementar",
                                    "Gebäude"));
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_GEB_ERW_DCK",
                                    "Gebäudeversicherung - Erweiterte Deckung",
                                    "Gebäude"));
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_GEB_UNB_GEF",
                                    "Gebäudeversicherung - Unbenannte Gefahren",
                                    "Gebäude"));
      tResponse.getProduktListe()
               .add(new ProduktInfo("PR_GEB_GLAS",
                                    "Gebäudeversicherung - Glas",
                                    "Gebäude"));
      tResponse.getProduktListe()
               .add(new ProduktInfo(TAA.BHV,
                                    "Haftpflichtversicherung für betriebliche oder berufliche Risiken",
                                    null));
      tResponse.getProduktListe()
               .add(new ProduktInfo(TAA.PRIVATE_RISIKEN,
                                    "Haftpflichtversicherung für private Risiken",
                                    null));
      tResponse.getProduktListe()
               .add(new ProduktInfo(TAA.ELEKTRONIK,
                                    "Elektronikversicherung",
                                    null));
      tResponse.getProduktListe()
               .add(new ProduktInfo(TAA.MASCHINEN_STATIONAER,
                                    "Versicherung für stationäre und transportable Maschinen",
                                    null));
      tResponse.getProduktListe()
               .add(new ProduktInfo(TAA.MASCHINEN_FAHRBAR,
                                    "Versicherung für selbstfahrende oder zulassungspflichtige Maschinen",
                                    null));
      tResponse.getProduktListe()
               .add(new ProduktInfo(TAA.WERKVERKEHR,
                                    "Werkverkehrsversicherung",
                                    null));
      tResponse.getProduktListe()
               .add(new ProduktInfo(TAA.BETRIEBSSCHLIESSUNG,
                                    "Betriebsschliessung",
                                    null));
    }

    tResponse.getStatus()
             .setReturncode(ReturnCode.OK);

    return tResponse;
  }

}