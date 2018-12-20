package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsKeyValue;
import de.gothaer.gwt.gxt.config.shared.model.GsMessage;
import de.gothaer.gwt.gxt.config.shared.model.GsMessage.Source;
import de.gothaer.gwt.gxt.config.shared.model.GsMessage.Type;
import de.gothaer.gwt.gxt.config.shared.model.GsMessage.Visibility;
import de.gothaer.taa.gewerbe.ui.shared.s2.GUID;
import de.gothaer.taa.gewerbe.ui.shared.s2.MessageWidgetIdUtils;
import de.gothaer.taa.gewerbe.ui.shared.s2.errorMessage.ErrorMessageIDs;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.*;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.gebaeude.VersichertesGebaeudeGebaeude;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.inhaltsversicherung.InhaltsVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.inhaltsversicherung.VersichertesGebaeudeInhalt;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.photovoltaik.PhotovoltaikVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.vsh.Vermoegensschadenhaftpflicht;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.werkverkehr.WerkverkehrTarifkonfiguration;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.staticEnums.AntragStatus;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaTarifierungRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaTarifierungErgebnisResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaTarifierungVariantenResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.widgetconfig.WidgetAnnotationConstants;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

@Path("/tarifierung")
public class TarifierungResource {

  @POST
  @Path("/tarifiereAntrag")
  @Produces("application/json")
  public TaaTarifierungVariantenResponse tarifiereAntrag(final TaaTarifierungRequest request) {
    TaaTarifierungVariantenResponse response = new TaaTarifierungVariantenResponse();
    Antrag antrag = request.getAntrag();

    Random x = new Random();
    Status status = new Status();
    status.setReturncode(ReturnCode.OK);
    response.setStatus(status);
    response.setAntrag(antrag);

    List<EinzelTarifierungsergebnis> erg = new ArrayList<EinzelTarifierungsergebnis>();
    response.getAntrag()
            .setTarifierungsergebnisse(erg);
    for (int i = 0; i < 4; i++) {
      EinzelTarifierungsergebnis t = new EinzelTarifierungsergebnis();
      t.setId(i + "");
      t.setProductName("Produkt " + i);
      t.setElementarProdukt("Elementar " + i);
      t.setBeitragJaehrlich(new BigDecimal(x.nextInt(100)));
      erg.add(t);
    }

    tarifiere(response);

    return response;
  }

  private void tarifiere(TaaTarifierungVariantenResponse response) {
    if (response.getAntrag()
                .hasBetriebshaftpflicht()) {
      response.setAntrag(calculateBetriebsHafpflicht(response.getAntrag()));
    }
    if (response.getAntrag()
                .hasInhaltsVersicherung()) {
      if (validateInhalt(response.getAntrag(),
                         response.getStatus())) {

      }
    }
    if (response.getAntrag()
                .hasWerkverkehrVersicherung()) {
      calculateWerkverkehr(response);
    }
  }

  private Antrag calculateBetriebsHafpflicht(Antrag antrag) {
    return antrag;
  }

  private boolean validateInhalt(Antrag antrag,
                                 Status status) {
    InhaltsVersicherung versicherung = antrag.getInhaltsVersicherung();
    if (versicherung.getVersicherteGebaeude()
                    .size() > 0) {
      for (VersichertesGebaeudeInhalt model : versicherung.getVersicherteGebaeude()) {
        if (antrag.getRisikoort(model.getIdVersichertesObjekt())
                  .getBak() == null ||
            antrag.getRisikoort(model.getIdVersichertesObjekt())
                  .getBak()
                  .getKey() == null) {
          status.setReturncode(ReturnCode.FACHLICHE_MELDUNGEN);
          status.getMeldungenFachlich()
                .add(new GsMessage(Type.ERROR,
                                   ErrorMessageIDs.RISIKOORT_BAK_MISSING,
                                   versicherung.getProdukt()
                                               .getKey(),
                                   model.getIdVersichertesObjekt(),
                                   "Bauartklasse muss angegeben werden",
                                   "TARIFIERUNG",
                                   Visibility.PERMANENT,
                                   Source.SERVER,
                                   MessageWidgetIdUtils.createRisikoOrtWidgetMessageId(model.getIdVersichertesObjekt(),
                                                                                       WidgetAnnotationConstants.RISIKOORT_BAUARTKLASSE)));

        }
      }
    } else {
    }

    return true;
  }

  private void calculateWerkverkehr(TaaTarifierungVariantenResponse response) {
    WerkverkehrTarifkonfiguration werkverkehrTarifkonfiguration01 = new WerkverkehrTarifkonfiguration();
    werkverkehrTarifkonfiguration01.setSelected(false);
    werkverkehrTarifkonfiguration01.setBruttoBeitrag(new BigDecimal(120));
    werkverkehrTarifkonfiguration01.setPlusBausteine(response.getAntrag()
                                                             .getWerkverkehrVersicherung()
                                                             .getTarifKonfiguration1()
                                                             .getPlusBausteine());
    werkverkehrTarifkonfiguration01.setSelbstbeteiligung(response.getAntrag()
                                                                 .getWerkverkehrVersicherung()
                                                                 .getTarifKonfiguration1()
                                                                 .getSelbstbeteiligung());
    WerkverkehrTarifkonfiguration werkverkehrTarifkonfiguration02 = new WerkverkehrTarifkonfiguration();
    werkverkehrTarifkonfiguration02.setSelected(true);
    werkverkehrTarifkonfiguration02.setBruttoBeitrag(new BigDecimal(240));
    werkverkehrTarifkonfiguration02.setPlusBausteine(response.getAntrag()
                                                             .getWerkverkehrVersicherung()
                                                             .getTarifKonfiguration2()
                                                             .getPlusBausteine());
    werkverkehrTarifkonfiguration02.setSelbstbeteiligung(response.getAntrag()
                                                                 .getWerkverkehrVersicherung()
                                                                 .getTarifKonfiguration2()
                                                                 .getSelbstbeteiligung());

    if (response.getAntrag()
                .getTaetigkeiten() != null) {
      boolean hasTaetigkeitBaeckerei = checkIfTaetigkeiIsSelected(response.getAntrag()
                                                                          .getTaetigkeiten(),
                                                                  "4712");
      boolean hasTaetigkeitMetzgerei = checkIfTaetigkeiIsSelected(response.getAntrag()
                                                                          .getTaetigkeiten(),
                                                                  "4711");
      if (hasTaetigkeitBaeckerei && hasTaetigkeitMetzgerei) {
        werkverkehrTarifkonfiguration01.setBruttoBeitrag(new BigDecimal(360));
        werkverkehrTarifkonfiguration02.setBruttoBeitrag(new BigDecimal(720));
        response.getAntrag()
                .getWerkverkehrVersicherung()
                .setTarifKonfiguration1(new WerkverkehrTarifkonfiguration());
        response.getAntrag()
                .setBeitragJaehrlich(new BigDecimal(720));
        response.getAntrag()
                .setBeitragHalbJaehrlich(new BigDecimal(360));
        response.getAntrag()
                .setBeitragMonatlich(new BigDecimal(60));
      } else if (hasTaetigkeitBaeckerei) {
        werkverkehrTarifkonfiguration01.setBruttoBeitrag(new BigDecimal(120));
        werkverkehrTarifkonfiguration02.setBruttoBeitrag(new BigDecimal(240));
        response.getAntrag()
                .getWerkverkehrVersicherung()
                .setTarifKonfiguration1(new WerkverkehrTarifkonfiguration());
        response.getAntrag()
                .setBeitragJaehrlich(new BigDecimal(240));
        response.getAntrag()
                .setBeitragHalbJaehrlich(new BigDecimal(120));
        response.getAntrag()
                .setBeitragMonatlich(new BigDecimal(20));
      } else if (hasTaetigkeitMetzgerei) {
        werkverkehrTarifkonfiguration01.setBruttoBeitrag(new BigDecimal(240));
        werkverkehrTarifkonfiguration02.setBruttoBeitrag(new BigDecimal(480));
        response.getAntrag()
                .getWerkverkehrVersicherung()
                .setTarifKonfiguration1(new WerkverkehrTarifkonfiguration());
        response.getAntrag()
                .setBeitragJaehrlich(new BigDecimal(480));
        response.getAntrag()
                .setBeitragHalbJaehrlich(new BigDecimal(240));
        response.getAntrag()
                .setBeitragMonatlich(new BigDecimal(40));
      } else {
        werkverkehrTarifkonfiguration01.setBruttoBeitrag(new BigDecimal(100));
        werkverkehrTarifkonfiguration02.setBruttoBeitrag(new BigDecimal(200));
        response.getAntrag()
                .getWerkverkehrVersicherung()
                .setTarifKonfiguration1(new WerkverkehrTarifkonfiguration());
        response.getAntrag()
                .setBeitragJaehrlich(new BigDecimal(200));
        response.getAntrag()
                .setBeitragHalbJaehrlich(new BigDecimal(100));
        response.getAntrag()
                .setBeitragMonatlich(new BigDecimal(20));
      }
    } else {
      response.getAntrag()
              .getWerkverkehrVersicherung()
              .setTarifKonfiguration1(new WerkverkehrTarifkonfiguration());
      response.getAntrag()
              .setBeitragJaehrlich(new BigDecimal(240));
      response.getAntrag()
              .setBeitragHalbJaehrlich(new BigDecimal(120));
      response.getAntrag()
              .setBeitragMonatlich(new BigDecimal(20));
    }
  }

  private boolean checkIfTaetigkeiIsSelected(Taetigkeiten taetigkeiten,
                                             String keyRequestedTaetigkeit) {
    for (GsKeyValue taetigkeit : taetigkeiten.getAlleTaetigkeiten()) {
      if (keyRequestedTaetigkeit.equals(taetigkeit.getKey())) {
        return true;
      }
    }
    return false;
  }

  @POST
  @Path("/tarifiereVarianten")
  @Produces("application/json")
  public TaaTarifierungVariantenResponse tarifiereVarianten(final TaaTarifierungRequest request) {

    TaaTarifierungVariantenResponse response = new TaaTarifierungVariantenResponse();
    response.setAntrag(request.getAntrag());

    GregorianCalendar now = new GregorianCalendar();
    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT); // 14.04.12
    df = DateFormat.getTimeInstance(DateFormat.SHORT);

    Status status = new Status();
    status.setReturncode(ReturnCode.OK);
    response.setStatus(status);

    Random x = new Random();

    List<EinzelTarifierungsergebnis> erg = new ArrayList<EinzelTarifierungsergebnis>();
    response.getAntrag()
            .setTarifierungsergebnisse(erg);
    for (int i = 0; i < 4; i++) {
      EinzelTarifierungsergebnis t = new EinzelTarifierungsergebnis();
      t.setId(i + "");
      t.setProductName("Produkt " + i);
      t.setElementarProdukt("Elementar " + i);
      t.setBeitragJaehrlich(new BigDecimal(x.nextInt(100)));
      erg.add(t);
    }

    // *** Betriebshaftpflicht
    if (response.getAntrag()
                .hasBetriebshaftpflicht()) {
      response.getAntrag()
              .setBeitragJaehrlich(new BigDecimal(100));
      response.getAntrag()
              .setBeitragHalbJaehrlich(new BigDecimal(52));
      response.getAntrag()
              .setBeitragMonatlich(new BigDecimal(15));

      response.getAntrag()
              .getBetriebsHaftpflichtVersicherung()
              .getTarifKonfiguration1()
              .setBruttoBeitrag(new BigDecimal(119.00));
      response.getAntrag()
              .getBetriebsHaftpflichtVersicherung()
              .getTarifKonfiguration1()
              .setBruttoBeitrag(new BigDecimal(119.00));
      response.getAntrag()
              .getBetriebsHaftpflichtVersicherung()
              .getTarifKonfiguration2()
              .setBruttoBeitrag(new BigDecimal(238.00));

      GsMessage message = new GsMessage(Type.INFO,
                                        "4711",
                                        response.getAntrag()
                                                .getBetriebsHaftpflichtVersicherung()
                                                .getProdukt()
                                                .getKey(),
                                        null,
                                        "Taridierung Bhv erfolgreich abgeschlossen",
                                        "TARIFIERUNG",
                                        Visibility.PERMANENT,
                                        Source.SERVER,
                                        new ArrayList<String>());
      status.getMeldungenFachlich()
            .add(message);

      // tooltips.add(new GsToolTip(response.getAntrag()
      // .getBetriebsHaftpflichtVersicherung()
      // .getProduktConfiguration()
      // .getKey() + "_plusbaustein_tooltip",
      // "Tooltip",
      // "Spannender Text:<br>jetzt geht's los!<br><ul><li>JuppiDuppiDuuuuuuuhhhhhh ....</li><li>Erweiterte Produkthaftpflichtversicherung für Handel, Gewerbe, Handwerk (nicht gültig für Betriebe
      // des Bauhauptgewerbes, für Bauhandwerker sowie produzierende Betriebe)</li></ul> ",
      // null));
    }

    // *** Gebüude
    if (response.getAntrag()
                .hasGebaeudeVersicherung()) {
      response.getAntrag()
              .getGebaeudeVersicherung()
              .getTarifKonfiguration1()
              .setBruttoBeitrag(new BigDecimal(112212.00));
      response.getAntrag()
              .getGebaeudeVersicherung()
              .getTarifKonfiguration2()
              .setBruttoBeitrag(new BigDecimal(1856.22));
    }

    // *** Inhalt
    if (response.getAntrag()
                .hasInhaltsVersicherung()) {
      response.getAntrag()
              .getInhaltsVersicherung()
              .getTarifKonfiguration1()
              .setBruttoBeitrag(new BigDecimal(586));
      response.getAntrag()
              .getInhaltsVersicherung()
              .getTarifKonfiguration2()
              .setBruttoBeitrag(new BigDecimal(723.11));
    }

    // *** Gebüude
    if (request.getAntrag()
               .getGebaeudeVersicherung() != null) {
      for (final VersichertesGebaeudeGebaeude tVersGebaeude : request.getAntrag()
                                                                     .getGebaeudeVersicherung()
                                                                     .getVersicherteGebaeude()) {
        if (request.getAntrag()
                   .getRisikoort(tVersGebaeude.getIdVersichertesObjekt())
                   .getBak() == null ||
            request.getAntrag()
                   .getRisikoort(tVersGebaeude.getIdVersichertesObjekt())
                   .getBak()
                   .getKey() == null) {
          if (request.getAntrag()
                     .getRisikoort(tVersGebaeude.getIdVersichertesObjekt())
                     .getBaujahr() != null &&
              request.getAntrag()
                     .getRisikoort(tVersGebaeude.getIdVersichertesObjekt())
                     .getBaujahr() == 1999) {
            String key01 = request.getAntrag()
                                  .getGebaeudeVersicherung()
                                  .getProdukt()
                                  .getKey();
            // gibt es den Ort auch in Inhalt?
            if (request.getAntrag()
                       .hasInhaltsVersicherung()) {
              if (request.getAntrag()
                         .getInhaltsVersicherung()
                         .isUsingRisikoOrte(new ArrayList<RisikoOrt>() {
                           private static final long serialVersionUID = 6065267699777964238L;

                           {
                             add(request.getAntrag()
                                        .getRisikoort(tVersGebaeude.getIdVersichertesObjekt()));
                           }
                         })) {
                key01 = key01 +
                        "," +
                        request.getAntrag()
                               .getInhaltsVersicherung()
                               .getProdukt()
                               .getKey();
              }
            }

            status.setReturncode(ReturnCode.FACHLICHE_MELDUNGEN);
            status.getMeldungenFachlich()
                  .add(new GsMessage(Type.ERROR,
                                     ErrorMessageIDs.RISIKOORT_BAK_MISSING,
                                     key01,
                                     tVersGebaeude.getIdVersichertesObjekt(),
                                     "Bauartklasse muss angegeben werden",
                                     "TARIFIERUNG",
                                     Visibility.PERMANENT,
                                     Source.SERVER,
                                     MessageWidgetIdUtils.createRisikoOrtWidgetMessageId(tVersGebaeude.getIdVersichertesObjekt(),
                                                                                         WidgetAnnotationConstants.RISIKOORT_BAUARTKLASSE)));
          }
        }
      }
    }

    // *** Photovoltaik
    PhotovoltaikVersicherung photovoltaikVersicherung = request.getAntrag()
                                                               .getPhotovoltaikVersicherung();
    if (photovoltaikVersicherung != null) {
      if (photovoltaikVersicherung.getVersicherteGebaeude()
                                  .size() > 0) {
        GsMessage message = new GsMessage(Type.ERROR,
                                          ErrorMessageIDs.ERR_PHOTOVOLTAIK_RISIKOORT_VSU,
                                          photovoltaikVersicherung.getProdukt()
                                                                  .getKey(),
                                          photovoltaikVersicherung.getVersicherteGebaeude()
                                                                  .get(0)
                                                                  .getIdVersichertesObjekt(),
                                          "Test-Errormessage Photovoltaik",
                                          "TARIFIERUNG",
                                          Visibility.PERMANENT,
                                          Source.SERVER,
                                          MessageWidgetIdUtils.createPhotovoltaikVersichertesGebaeudeVersicherungssumme(photovoltaikVersicherung.getProdukt()
                                                                                                                                                .getKey(),
                                                                                                                        photovoltaikVersicherung.getVersicherteGebaeude()
                                                                                                                                                .get(0)
                                                                                                                                                .getIdVersichertesObjekt()));
        status.getMeldungenFachlich()
              .add(message);
      }
    }

    tarifiere(response);

    // *** VSH
    Vermoegensschadenhaftpflicht vsh = request.getAntrag()
                                              .getVermoegensschadenhaftpflichtVersicherung();
    if (vsh != null) {
      vsh.getTarifKonfiguration1()
         .setBruttoBeitrag(new BigDecimal(111.11));
      vsh.getTarifKonfiguration2()
         .setBruttoBeitrag(new BigDecimal(222.22));
    }

    // *** Metadaten
    VKdaten tVKdaten = new VKdaten();
    tVKdaten.setAntragMinEinmalGedruckt(false);
    tVKdaten.setBeratungsProtMinEinmalGedruckt(false);
    tVKdaten.setAntragStatus(AntragStatus.IN_ARBEIT_TARIFIERT);
    response.setVkdaten(tVKdaten);

    response.getAntrag()
            .getIndividualisierung()
            .setAntragIndividualisierbar(true);

    return response;
  }

  @POST
  @Path("/tarifiereErgebnis")
  @Produces("application/json")
  public TaaTarifierungErgebnisResponse tarifiereErgebnis(TaaTarifierungRequest request) {
    TaaTarifierungErgebnisResponse response = new TaaTarifierungErgebnisResponse();

    List<EinzelTarifierungsergebnis> list = new ArrayList<>();
    if (request.getAntrag()
               .hasBetriebshaftpflicht()) {
      list.addAll(loadBetriebsHaftpflichtData(request.getAntrag()));
    }
    if (request.getAntrag()
               .hasPrivateRisiken()) {
      list.addAll(loadPrivateRisiken(request.getAntrag()));
    }
    if (request.getAntrag()
               .hasGebaeudeVersicherung()) {
      list.addAll(loadGebaeudeData(request.getAntrag()));
    }
    if (request.getAntrag()
               .hasInhaltsVersicherung()) {
      list.addAll(loadInhaltData(request.getAntrag()));
    }
    if (request.getAntrag()
               .hasVermoegensschadenhaftpflichtVersicherung()) {
      list.addAll(loadVshData(request.getAntrag()));
    }

    Antrag antrag = request.getAntrag();
    antrag.getTarifierungsergebnisse()
          .clear();
    antrag.getTarifierungsergebnisse()
          .addAll(list);

    response.setAntrag(request.getAntrag());

    Status status = new Status();
    status.setReturncode(ReturnCode.OK);
    response.setStatus(status);

    return response;
  }

  private List<EinzelTarifierungsergebnis> loadVshData(Antrag antrag) {
    List<EinzelTarifierungsergebnis> list = new ArrayList<>();
    list.add(createNewTarifierungergebnis(antrag,
                                          antrag.getVermoegensschadenhaftpflichtVersicherung()
                                                .getProdukt(),
                                          "VSH",
                                          "Vermügensschadenhaftpflicht",
                                          new BigDecimal(15),
                                          new BigDecimal(10.45),
                                          new BigDecimal(120),
                                          new BigDecimal(350),
                                          new BigDecimal(680),
                                          new BigDecimal(1200)));

    return list;
  }

  private List<EinzelTarifierungsergebnis> loadBetriebsHaftpflichtData(Antrag antrag) {
    List<EinzelTarifierungsergebnis> list = new ArrayList<>();
    list.add(createNewTarifierungergebnis(antrag,
                                          antrag.getBetriebsHaftpflichtVersicherung()
                                                .getProdukt(),
                                          "Haftpflicht",
                                          "Betriebshaftpflicht",
                                          new BigDecimal(15),
                                          new BigDecimal(10.45),
                                          new BigDecimal(120),
                                          new BigDecimal(350),
                                          new BigDecimal(680),
                                          new BigDecimal(1200)));
    return list;
  }

  private List<EinzelTarifierungsergebnis> loadPrivateRisiken(Antrag antrag) {
    List<EinzelTarifierungsergebnis> list = new ArrayList<>();
    list.add(createNewTarifierungergebnis(antrag,
                                          antrag.getPrivateRisiken()
                                                .getProdukt(),
                                          "Haftpflicht",
                                          "Private Risiken",
                                          new BigDecimal(15),
                                          new BigDecimal(10),
                                          new BigDecimal(10),
                                          new BigDecimal(28),
                                          new BigDecimal(55),
                                          new BigDecimal(100)));
    return list;
  }

  private List<EinzelTarifierungsergebnis> loadGebaeudeData(Antrag antrag) {
    List<EinzelTarifierungsergebnis> list = new ArrayList<>();
    if (antrag.getGebaeudeVersicherung()
              .isElementar()) {
      list.add(new EinzelTarifierungsergebnis(GUID.get(),
                                              new GsKeyValue("PR_GEB_ELE",
                                                             "Gebäude - Elementar"),
                                              "Inhalt",
                                              "Elementar",
                                              new BigDecimal(0),
                                              new BigDecimal(10),
                                              new BigDecimal(10),
                                              new BigDecimal(2),
                                              new BigDecimal(100),
                                              new BigDecimal(280),
                                              new BigDecimal(560),
                                              new BigDecimal(990)));
    }
    if (antrag.getGebaeudeVersicherung()
              .isErweiterteDeckung()) {
      list.add(new EinzelTarifierungsergebnis(GUID.get(),
                                              new GsKeyValue("PR_GEB_ERW_DCK",
                                                             "Gebäude - Erweiterte Deckung"),
                                              "Gebäude",
                                              "Erweiterte Deckung",
                                              new BigDecimal(0),
                                              new BigDecimal(10),
                                              new BigDecimal(10),
                                              new BigDecimal(2),
                                              new BigDecimal(100),
                                              new BigDecimal(280),
                                              new BigDecimal(560),
                                              new BigDecimal(990)));
    }
    if (antrag.getGebaeudeVersicherung()
              .isFeuer()) {
      list.add(new EinzelTarifierungsergebnis(GUID.get(),
                                              new GsKeyValue("PR_GEB_FEU",
                                                             "Gebäude - Feuer"),
                                              "Gebäude",
                                              "Feuer",
                                              new BigDecimal(0),
                                              new BigDecimal(10),
                                              new BigDecimal(10),
                                              new BigDecimal(2),
                                              new BigDecimal(100),
                                              new BigDecimal(280),
                                              new BigDecimal(560),
                                              new BigDecimal(990)));
    }
    if (antrag.getGebaeudeVersicherung()
              .isGlas()) {
      list.add(new EinzelTarifierungsergebnis(GUID.get(),
                                              new GsKeyValue("PR_GEB_GLAS",
                                                             "Gebäude - Glas"),
                                              "Gebäude",
                                              "Glas",
                                              new BigDecimal(0),
                                              new BigDecimal(10),
                                              new BigDecimal(10),
                                              new BigDecimal(2),
                                              new BigDecimal(100),
                                              new BigDecimal(280),
                                              new BigDecimal(560),
                                              new BigDecimal(990)));
    }
    if (antrag.getGebaeudeVersicherung()
              .isLeitungswasser()) {
      list.add(new EinzelTarifierungsergebnis(GUID.get(),
                                              new GsKeyValue("PR_GEB_LTW",
                                                             "Gebäude - Leitungswasser"),
                                              "Gebäude",
                                              "Leitungswasser",
                                              new BigDecimal(0),
                                              new BigDecimal(10),
                                              new BigDecimal(10),
                                              new BigDecimal(2),
                                              new BigDecimal(100),
                                              new BigDecimal(280),
                                              new BigDecimal(560),
                                              new BigDecimal(990)));
    }
    if (antrag.getGebaeudeVersicherung()
              .isSturm()) {
      list.add(new EinzelTarifierungsergebnis(GUID.get(),
                                              new GsKeyValue("PR_GEB_STU",
                                                             "Gebäude - Sturm, Hagel"),
                                              "Gebäude",
                                              "Strum",
                                              new BigDecimal(0),
                                              new BigDecimal(10),
                                              new BigDecimal(10),
                                              new BigDecimal(2),
                                              new BigDecimal(100),
                                              new BigDecimal(280),
                                              new BigDecimal(560),
                                              new BigDecimal(990)));
    }
    if (antrag.getGebaeudeVersicherung()
              .isUnbenannteGefahren()) {
      list.add(new EinzelTarifierungsergebnis(GUID.get(),
                                              new GsKeyValue("PR_GEB_UNB_GEF",
                                                             "Gebäude - Unbenannte Gefahr"),
                                              "Gebäude",
                                              "Unbenannte Gefahr",
                                              new BigDecimal(0),
                                              new BigDecimal(10),
                                              new BigDecimal(10),
                                              new BigDecimal(2),
                                              new BigDecimal(100),
                                              new BigDecimal(280),
                                              new BigDecimal(560),
                                              new BigDecimal(990)));
    }
    return list;
  }

  private List<EinzelTarifierungsergebnis> loadInhaltData(Antrag antrag) {
    List<EinzelTarifierungsergebnis> list = new ArrayList<>();
    if (antrag.getInhaltsVersicherung()
              .isElementar()) {
      list.add(new EinzelTarifierungsergebnis(GUID.get(),
                                              new GsKeyValue("PR_INH_ELE",
                                                             "Inhalt - Elementar"),
                                              "Inhalt",
                                              "Elementar",
                                              new BigDecimal(0),
                                              new BigDecimal(10),
                                              new BigDecimal(10),
                                              new BigDecimal(2),
                                              new BigDecimal(100),
                                              new BigDecimal(280),
                                              new BigDecimal(560),
                                              new BigDecimal(990)));
    }
    if (antrag.getInhaltsVersicherung()
              .isErweiterteDeckung()) {
      list.add(new EinzelTarifierungsergebnis(GUID.get(),
                                              new GsKeyValue("PR_INH_ELE",
                                                             "Inhalt - Elementar"),
                                              "Inhalt",
                                              "Erweiterte Deckung",
                                              new BigDecimal(0),
                                              new BigDecimal(10),
                                              new BigDecimal(10),
                                              new BigDecimal(2),
                                              new BigDecimal(100),
                                              new BigDecimal(280),
                                              new BigDecimal(560),
                                              new BigDecimal(990)));
    }
    if (antrag.getInhaltsVersicherung()
              .isFeuer()) {
      list.add(new EinzelTarifierungsergebnis(GUID.get(),
                                              new GsKeyValue("PR_INH_FEU",
                                                             "Inhalt - Feuer"),
                                              "Inhalt",
                                              "Feuer",
                                              new BigDecimal(0),
                                              new BigDecimal(10),
                                              new BigDecimal(10),
                                              new BigDecimal(2),
                                              new BigDecimal(100),
                                              new BigDecimal(280),
                                              new BigDecimal(560),
                                              new BigDecimal(990)));
    }
    if (antrag.getInhaltsVersicherung()
              .isGlas()) {
      list.add(new EinzelTarifierungsergebnis(GUID.get(),
                                              new GsKeyValue("PR_INH_GLAS",
                                                             "Inhalt - Glas"),
                                              "Inhalt",
                                              "Glas",
                                              new BigDecimal(0),
                                              new BigDecimal(10),
                                              new BigDecimal(10),
                                              new BigDecimal(2),
                                              new BigDecimal(100),
                                              new BigDecimal(280),
                                              new BigDecimal(560),
                                              new BigDecimal(990)));
    }
    if (antrag.getInhaltsVersicherung()
              .isLeitungswasser()) {
      list.add(new EinzelTarifierungsergebnis(GUID.get(),
                                              new GsKeyValue("PR_INH_LTW",
                                                             "Inhalt - Leitungswasser"),
                                              "Inhalt",
                                              "Leitungswasser",
                                              new BigDecimal(0),
                                              new BigDecimal(10),
                                              new BigDecimal(10),
                                              new BigDecimal(2),
                                              new BigDecimal(100),
                                              new BigDecimal(280),
                                              new BigDecimal(560),
                                              new BigDecimal(990)));
    }
    if (antrag.getInhaltsVersicherung()
              .isSturm()) {
      list.add(new EinzelTarifierungsergebnis(GUID.get(),
                                              new GsKeyValue("PR_INH_STU",
                                                             "Inhalt - Sturm, Hagel"),
                                              "Inhalt",
                                              "Strum",
                                              new BigDecimal(0),
                                              new BigDecimal(10),
                                              new BigDecimal(10),
                                              new BigDecimal(2),
                                              new BigDecimal(100),
                                              new BigDecimal(280),
                                              new BigDecimal(560),
                                              new BigDecimal(990)));
    }
    if (antrag.getInhaltsVersicherung()
              .isUnbenannteGefahren()) {
      list.add(new EinzelTarifierungsergebnis(GUID.get(),
                                              new GsKeyValue("PR_INH_UNB_GEF",
                                                             "Inhalt - Unbenannte Gefahr"),
                                              "Inhalt",
                                              "Unbenannte Gefahr",
                                              new BigDecimal(0),
                                              new BigDecimal(10),
                                              new BigDecimal(10),
                                              new BigDecimal(2),
                                              new BigDecimal(100),
                                              new BigDecimal(280),
                                              new BigDecimal(560),
                                              new BigDecimal(990)));
    }
    if (antrag.getInhaltsVersicherung()
              .isEinbruchDiebstahl()) {
      list.add(new EinzelTarifierungsergebnis(GUID.get(),
                                              new GsKeyValue("PR_INH_ED",
                                                             "Inhalt - Einbruch Diebstahl"),
                                              "Inhalt",
                                              "Einbruch Diebstahl",
                                              new BigDecimal(0),
                                              new BigDecimal(10),
                                              new BigDecimal(10),
                                              new BigDecimal(2),
                                              new BigDecimal(100),
                                              new BigDecimal(280),
                                              new BigDecimal(560),
                                              new BigDecimal(990)));
    }
    return list;
  }

  private EinzelTarifierungsergebnis createNewTarifierungergebnis(Antrag antrag,
                                                                  GsKeyValue produkt,
                                                                  String productName,
                                                                  String elementarProdukt,
                                                                  BigDecimal quotenGrenzwert,
                                                                  BigDecimal aktuelleQuote,
                                                                  BigDecimal beitragMonatlich,
                                                                  BigDecimal beitragMVierteljaehrlich,
                                                                  BigDecimal beitragHalbJaehrlich,
                                                                  BigDecimal beitragJaehrlich) {
    EinzelTarifierungsergebnis oldErgebnis = getOldTarifierungsErgebnis(antrag,
                                                                        produkt);
    return new EinzelTarifierungsergebnis(GUID.get(),
                                          produkt,
                                          productName,
                                          elementarProdukt,
                                          oldErgebnis == null ? BigDecimal.ZERO : oldErgebnis.getZuschlag(),
                                          quotenGrenzwert,
                                          aktuelleQuote,
                                          oldErgebnis == null ? BigDecimal.ZERO : oldErgebnis.getQuote(),
                                          calculate(beitragMonatlich,
                                                    oldErgebnis == null ? BigDecimal.ZERO : oldErgebnis.getQuote()),
                                          calculate(beitragMVierteljaehrlich,
                                                    oldErgebnis == null ? BigDecimal.ZERO : oldErgebnis.getQuote()),
                                          calculate(beitragHalbJaehrlich,
                                                    oldErgebnis == null ? BigDecimal.ZERO : oldErgebnis.getQuote()),
                                          calculate(beitragJaehrlich,
                                                    oldErgebnis == null ? BigDecimal.ZERO : oldErgebnis.getQuote()));
  }

  private EinzelTarifierungsergebnis getOldTarifierungsErgebnis(Antrag antrag,
                                                                GsKeyValue produkt) {
    for (EinzelTarifierungsergebnis ergebnis : antrag.getTarifierungsergebnisse()) {
      if (produkt.getKey()
                 .equals(ergebnis.getProdukt()
                                 .getKey())) {
        return ergebnis;
      }
    }
    return null;
  }

  private BigDecimal calculate(BigDecimal beitrag,
                               BigDecimal quote) {
    return beitrag.subtract(beitrag.multiply(quote)
                                   .divide(new BigDecimal(100),
                                           2,
                                           BigDecimal.ROUND_HALF_UP));
  }

}
