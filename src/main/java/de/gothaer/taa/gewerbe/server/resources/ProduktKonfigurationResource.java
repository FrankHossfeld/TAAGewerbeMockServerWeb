package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsKeyValue;
import de.gothaer.gwt.gxt.config.shared.model.GsToolTip;
import de.gothaer.taa.gewerbe.ui.shared.s2.GUID;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.Antrag;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.PMAttribut;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.PMAttribut.Type;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.alle.Deckungserweiterung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.alle.Deckungserweiterung.Typ;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.alle.PlusBaustein;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.alle.VersichertesGebaeudeElektronikMaschinenPhotovoltaik;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.gebaeude.GebaeudeVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.gebaeude.VersichertesGebaeudeGebaeude;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.gruppenunfall.GruppenunfallLeistungsart;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.gruppenunfall.GruppenunfallLeistungsblock;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.inhaltsversicherung.InhaltsVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.inhaltsversicherung.VersichertesGebaeudeInhalt;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.photovoltaik.PhotovoltaikVersicherung;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TAAProduktKonfigurationRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TAAProduktKonfigurationResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Path("/produktKonfiguration")
public class ProduktKonfigurationResource {

  private String[]    gruppenunfallLabel    = new String[] { "Invaliditätssumme",
                                                             "Invaliditätsrente",
                                                             "Tod",
                                                             "Krankenstagegeld",
                                                             "Genesungsgeld",
                                                             "Tagesgeld" };
  private Type[]      gruppenunfallType     = new Type[] { Type.COMBO_BOX,
                                                           Type.COMBO_BOX,
                                                           Type.TEXT_FIELD,
                                                           Type.TEXT_FIELD,
                                                           Type.TEXT_FIELD,
                                                           Type.COMBO_BOX, };
  private int[]       gruppenunfallMinValue = new int[] { 0,
                                                          0,
                                                          1000,
                                                          10000,
                                                          100000,
                                                          0 };
  private int[]       gruppenunfallMaxValue = new int[] { 100000,
                                                          30000,
                                                          10000,
                                                          20000,
                                                          200000,
                                                          15000 };
  private ArrayList[] gruppenunfallStore    = new ArrayList[] { new ArrayList<GsKeyValue>() {
    {
      add(new GsKeyValue("art101",
                         "Art 101"));
      add(new GsKeyValue("art102",
                         "Art 102"));
      add(new GsKeyValue("art103",
                         "Art 103"));
    }
  },
                                                                new ArrayList<GsKeyValue>() {
                                                                  {
                                                                    add(new GsKeyValue("art201",
                                                                                       "Art 201"));
                                                                    add(new GsKeyValue("art202",
                                                                                       "Art 202"));
                                                                  }
                                                                },
                                                                null,
                                                                null,
                                                                null,
                                                                new ArrayList<GsKeyValue>() {
                                                                  {
                                                                    add(new GsKeyValue("art601",
                                                                                       "Art 601"));
                                                                    add(new GsKeyValue("art602",
                                                                                       "Art 602"));
                                                                    add(new GsKeyValue("art603",
                                                                                       "Art 603"));
                                                                    add(new GsKeyValue("art604",
                                                                                       "Art 604"));
                                                                  }
                                                                }, };

  @POST
  @Path("/konfiguriereGULeistunsblock")
  @Produces("application/json")
  @Consumes("application/json")
  public TAAProduktKonfigurationResponse konfiguriereGULeistunsblock(TAAProduktKonfigurationRequest request) {

    TAAProduktKonfigurationResponse r = new TAAProduktKonfigurationResponse();
    r.setAntrag(request.getAntrag());

    for (GruppenunfallLeistungsblock lb : request.getAntrag()
                                                 .getGruppenunfallVersicherung()
                                                 .getLeistungsbloecke()) {
      GsKeyValue artDerTaetigkeit = lb.getArtDerTaetigkeit();
      for (GruppenunfallLeistungsart la : lb.getLeistungsarten()) {
        PMAttribut artDerDeckung = la.getArtDerDeckung();
        PMAttribut vsu = la.getVsu();
      }

    }
    return r;

  }

  @POST
  @Path("/konfiguriereProdukt")
  @Produces("application/json")
  public TAAProduktKonfigurationResponse konfiguriereProdukt(TAAProduktKonfigurationRequest request) {
    TAAProduktKonfigurationResponse response = new TAAProduktKonfigurationResponse();
    response.setAntrag(request.getAntrag());
    response.setProdukt(request.getProdukt());

    if (request.getRefId() != null &&
        !request.getRefId()
                .isEmpty()) {
      updateGruppenunfallLeistungsbloecke(request.getProdukt(),
                                          request.getRefId(),
                                          response);
    } else {
      updateDeckungserweiterungen(request.getProdukt(),
                                  response);
      updatePlusbaustein(request.getProdukt(),
                         response);
      updateDynamischeAttribute(request.getProdukt(),
                                response);
    }
    return response;
  }

  @POST
  @Path("/konfiguriereOrtsbezogeneDeckungserweiterungen")
  @Produces("application/json")
  public TAAProduktKonfigurationResponse konfiguriereOrtsbezogeneDeckungserweiterungen(TAAProduktKonfigurationRequest request) {
    TAAProduktKonfigurationResponse response = new TAAProduktKonfigurationResponse();
    response.setAntrag(request.getAntrag());
    response.setProdukt(request.getProdukt());

    if (request.getRefId() != null) {
      updateGruppenunfallLeistungsbloecke(request.getProdukt(),
                                          request.getRefId(),
                                          response);
    } else {
      updateDeckungserweiterungen(request.getProdukt(),
                                  response);
      updatePlusbaustein(request.getProdukt(),
                         response);
      updateDynamischeAttribute(request.getProdukt(),
                                response);
    }
    return response;
  }

  private void updateGruppenunfallLeistungsbloecke(GsKeyValue produkt,
                                                   String pLeistungsblockId,
                                                   TAAProduktKonfigurationResponse response) {
    if (produkt.getKey()
               .equals("PR_GU")) {
      //      for (int i = 0; i < response.getAntrag()
      //                                  .getGruppenunfallVersicherung()
      //                                  .getLeistungsbloecke()
      //                                  .size(); i++) {
      //        GruppenunfallLeistungsblock leistungsblock = response.getAntrag()
      //                                                             .getGruppenunfallVersicherung()
      //                                                             .getLeistungsbloecke()
      //                                                             .get(i);
      //        if (pLeistungsblockId
      //
      //                                       .equals(leistungsblock.getLeistungsblockId())) {
      //          response.getAntrag()
      //                  .getGruppenunfallVersicherung()
      //                  .getLeistungsbloecke()
      //                  .set(i,
      //                       updateGruppenunfallLeistungsblock(response.getAntrag(),
      //                                                         pLeistungsblockId));
      //          return;
      //        }
      //      }
      //
      //      response.getAntrag()
      //              .getGruppenunfallVersicherung()
      //              .getLeistungsbloecke()
      //              .add(updateGruppenunfallLeistungsblock(response.getAntrag(),
      //                                                     pLeistungsblockId));
      // Sortieren ...
      Collections.sort(response.getAntrag()
                               .getGruppenunfallVersicherung()
                               .getLeistungsbloecke());
    }
  }

  private void updateDeckungserweiterungen(GsKeyValue produkt,
                                           TAAProduktKonfigurationResponse response) {
    // Deckungserweiterung anlegen
    if (produkt.equals(new GsKeyValue("PR_BHV",
                                      "Betriebshaftpflicht"))) {
      Deckungserweiterung deckungserweiterungTyp1 = new Deckungserweiterung();
      deckungserweiterungTyp1.setId("1");
      deckungserweiterungTyp1.setTyp(Typ.OhneEingabe);
      deckungserweiterungTyp1.setLabel("Test Typ1");
      deckungserweiterungTyp1.setEinschluss(true);
      response.getAntrag()
              .getBetriebsHaftpflichtVersicherung()
              .getDeckungserweiterung()
              .add(deckungserweiterungTyp1);

      Deckungserweiterung deckungserweiterungTyp2 = new Deckungserweiterung();
      deckungserweiterungTyp2.setId("2");
      deckungserweiterungTyp2.setTyp(Typ.TextEingabe);
      deckungserweiterungTyp2.setLabel("Test Typ2");
      response.getAntrag()
              .getBetriebsHaftpflichtVersicherung()
              .getDeckungserweiterung()
              .add(deckungserweiterungTyp2);

      Deckungserweiterung deckungserweiterungTyp3 = new Deckungserweiterung();
      deckungserweiterungTyp3.setId("3");
      deckungserweiterungTyp3.setTyp(Typ.SelectEingabe);
      deckungserweiterungTyp3.setLabel("Test Typ3");
      response.getAntrag()
              .getBetriebsHaftpflichtVersicherung()
              .getDeckungserweiterung()
              .add(deckungserweiterungTyp3);

      Deckungserweiterung deckungserweiterungTyp4 = new Deckungserweiterung();
      deckungserweiterungTyp4.setId("4");
      deckungserweiterungTyp4.setTyp(Typ.SelectUndTextEingabe);
      deckungserweiterungTyp4.setLabel("Test Typ4");
      List<GsKeyValue> possibleListValues = new ArrayList<>();
      possibleListValues.add(new GsKeyValue("key1",
                                            "value1"));
      possibleListValues.add(new GsKeyValue("key2",
                                            "value2"));
      possibleListValues.add(new GsKeyValue("key3",
                                            "value3"));
      deckungserweiterungTyp4.setPossibleListValues(possibleListValues);
      response.getAntrag()
              .getBetriebsHaftpflichtVersicherung()
              .getDeckungserweiterung()
              .add(deckungserweiterungTyp4);
    } else if (produkt.equals(new GsKeyValue("PR_PHV",
                                             "Private Risiken"))) {
      Deckungserweiterung deckungserweiterungTyp1 = new Deckungserweiterung();
      deckungserweiterungTyp1.setId("1");
      deckungserweiterungTyp1.setTyp(Typ.OhneEingabe);
      deckungserweiterungTyp1.setLabel("Test Typ1 - Private Risiken");
      response.getAntrag()
              .getPrivateRisiken()
              .getDeckungserweiterung()
              .add(deckungserweiterungTyp1);

      Deckungserweiterung deckungserweiterungTyp2 = new Deckungserweiterung();
      deckungserweiterungTyp2.setId("2");
      deckungserweiterungTyp2.setTyp(Typ.TextEingabe);
      deckungserweiterungTyp2.setLabel("Test Typ2 - Private Risiken");
      response.getAntrag()
              .getPrivateRisiken()
              .getDeckungserweiterung()
              .add(deckungserweiterungTyp2);
    } else if (produkt.equals(new GsKeyValue("PRGR_INH",
                                             "Inhaltsversicherung"))) {
      InhaltsVersicherung inhaltsVersicherung = response.getAntrag()
                                                        .getInhaltsVersicherung();
      if (inhaltsVersicherung.getDeckungserweiterung() == null ||
          inhaltsVersicherung.getDeckungserweiterung()
                             .size() == 0) {
        Deckungserweiterung deckungserweiterungTyp1 = new Deckungserweiterung();
        deckungserweiterungTyp1.setId("1");
        deckungserweiterungTyp1.setTyp(Typ.OhneEingabe);
        deckungserweiterungTyp1.setLabel("Test Typ1 - Inhalt");
        inhaltsVersicherung.getDeckungserweiterung()
                           .add(deckungserweiterungTyp1);

        Deckungserweiterung deckungserweiterungTyp2 = new Deckungserweiterung();
        deckungserweiterungTyp2.setId("2");
        deckungserweiterungTyp2.setTyp(Typ.TextEingabe);
        deckungserweiterungTyp2.setLabel("Test Typ2 - Inhalt");
        inhaltsVersicherung.getDeckungserweiterung()
                           .add(deckungserweiterungTyp2);

        PMAttribut pmAttributTyp3 = new PMAttribut();
        pmAttributTyp3.setId("SumInsured");
        pmAttributTyp3.setLabel("VersicherteSumme");
        pmAttributTyp3.setReadOnly(false);
        pmAttributTyp3.setRequired(true);
        pmAttributTyp3.setSortNumber(0);
        pmAttributTyp3.setType(PMAttribut.Type.NUMBER_FIELD);
        pmAttributTyp3.setUuid("D38AA87B-C51B-49E9-843E-D07D70CF4CE6");
        pmAttributTyp3.setVisible(true);

        Deckungserweiterung deckungserweiterungTyp3 = new Deckungserweiterung();
        deckungserweiterungTyp3.setId("3");
        deckungserweiterungTyp3.setUuid("DD49F420-23C1-4432-B5E3-4DFF5A7E73E3");
        deckungserweiterungTyp3.setTyp(Typ.SelectUndTextEingabe);
        deckungserweiterungTyp3.setAttribut(pmAttributTyp3);
        deckungserweiterungTyp3.setLabel("Ertragsausfallversicherung individual");
        deckungserweiterungTyp3.setEinschluss(false);
        deckungserweiterungTyp3.setMaximaleAnzahl(2);
        deckungserweiterungTyp3.setElpGroup("Haftzeit");
        deckungserweiterungTyp3.setPossibleListValues(new ArrayList<GsKeyValue>() {{
          add(new GsKeyValue("12",
                             "12"));
          add(new GsKeyValue("24",
                             "24"));
        }});
        inhaltsVersicherung.getDeckungserweiterung()
                           .add(deckungserweiterungTyp3);
      }
      int ctr = 2;
      for (VersichertesGebaeudeInhalt gebaeudeInhalt : inhaltsVersicherung.getVersicherteGebaeude()) {
        if (gebaeudeInhalt.getOrtsbezogeneDeckungserweiterungen() == null ||
            gebaeudeInhalt.getOrtsbezogeneDeckungserweiterungen()
                          .size() == 0) {
          List<Deckungserweiterung> liste = new ArrayList<>();
          for (int i = 0; i < ctr; i++) {

            Deckungserweiterung deckungserweiterungTyp = new Deckungserweiterung();
            deckungserweiterungTyp.setId(Integer.toString(i + 1));
            deckungserweiterungTyp.setTyp(Typ.TextEingabe);
            deckungserweiterungTyp.setLabel("Test Typ TextEingabe - Inhalt #" + Integer.toString(i));
            switch (i % 4) {
              case 0:
                deckungserweiterungTyp.setTyp(Typ.TextEingabe);
                break;
              case 1:
                deckungserweiterungTyp.setTyp(Typ.SelectUndTextEingabe);
                deckungserweiterungTyp.setPossibleListValues(new ArrayList<GsKeyValue>() {
                  {
                    add(new GsKeyValue("key01",
                                       "value01"));
                    add(new GsKeyValue("key02",
                                       "value02"));
                    add(new GsKeyValue("key03",
                                       "value03"));
                  }
                });
                PMAttribut pmAttributTyp = new PMAttribut();
                pmAttributTyp.setId("SumInsured");
                pmAttributTyp.setLabel("VersicherteSumme");
                pmAttributTyp.setReadOnly(false);
                pmAttributTyp.setRequired(true);
                pmAttributTyp.setSortNumber(0);
                pmAttributTyp.setType(PMAttribut.Type.NUMBER_FIELD);
                pmAttributTyp.setUuid("D38AA87B-C51B-49E9-843E-D07D70CF4CE6");
                pmAttributTyp.setVisible(true);
                deckungserweiterungTyp.setAttribut(pmAttributTyp);

                deckungserweiterungTyp.setEinschluss(false);
                deckungserweiterungTyp.setElpGroup("Wertbehältnis mit Mindestgewicht von 300 kg oder eine Verankerung gemäß der Montageanleitung des Herstellers");
                deckungserweiterungTyp.setMaximaleAnzahl(2);
                deckungserweiterungTyp.setUuid("2D2DCCE1-CA06-46EE-B5C1-5634DF0095BB");

                break;
              case 2:
                deckungserweiterungTyp.setTyp(Typ.SelectEingabe);
                deckungserweiterungTyp.setPossibleListValues(new ArrayList<GsKeyValue>() {
                  {
                    add(new GsKeyValue("key01",
                                       "value01"));
                    add(new GsKeyValue("key02",
                                       "value02"));
                    add(new GsKeyValue("key03",
                                       "value03"));
                  }
                });
                break;
              case 3:
                deckungserweiterungTyp.setTyp(Typ.OhneEingabe);
                break;
            }
            liste.add(deckungserweiterungTyp);
          }
          ctr++;
          inhaltsVersicherung.getVersichertesGebaeude(gebaeudeInhalt.getIdVersichertesObjekt())
                             .setOrtsbezogeneDeckungserweiterungen(liste);
        }
      }
    } else if (produkt.equals(new GsKeyValue("PRGR_GEB",
                                             "Gebäudeversicherung"))) {
      GebaeudeVersicherung gebaeudeVersicherung = response.getAntrag()
                                                          .getGebaeudeVersicherung();
      if (gebaeudeVersicherung.getDeckungserweiterung() == null ||
          gebaeudeVersicherung.getDeckungserweiterung()
                              .size() == 0) {
        Deckungserweiterung deckungserweiterungTyp1 = new Deckungserweiterung();
        deckungserweiterungTyp1.setId("1");
        deckungserweiterungTyp1.setTyp(Typ.OhneEingabe);
        deckungserweiterungTyp1.setLabel("Test Typ1 - Gebäude");
        response.getAntrag()
                .getGebaeudeVersicherung()
                .getDeckungserweiterung()
                .add(deckungserweiterungTyp1);

        Deckungserweiterung deckungserweiterungTyp2 = new Deckungserweiterung();
        deckungserweiterungTyp2.setId("2");
        deckungserweiterungTyp2.setTyp(Typ.TextEingabe);
        deckungserweiterungTyp2.setLabel("Test Typ2 - Gebäude");
        response.getAntrag()
                .getGebaeudeVersicherung()
                .getDeckungserweiterung()
                .add(deckungserweiterungTyp2);

        Deckungserweiterung deckungserweiterungTyp3 = new Deckungserweiterung();
        deckungserweiterungTyp3.setId("3");
        deckungserweiterungTyp3.setTyp(Typ.SelectEingabe);
        deckungserweiterungTyp3.setLabel("Test Typ3 - Gebäude");
        response.getAntrag()
                .getGebaeudeVersicherung()
                .getDeckungserweiterung()
                .add(deckungserweiterungTyp3);
      }
      int ctr = 2;
      for (VersichertesGebaeudeGebaeude gebaeude : gebaeudeVersicherung.getVersicherteGebaeude()) {
        if (gebaeude.getOrtsbezogeneDeckungserweiterungen() == null ||
            gebaeude.getOrtsbezogeneDeckungserweiterungen()
                    .size() == 0) {
          List<Deckungserweiterung> liste = new ArrayList<>();
          for (int i = 0; i < ctr; i++) {
            Deckungserweiterung deckungserweiterungTyp = new Deckungserweiterung();
            deckungserweiterungTyp.setId(Integer.toString(i + 1));
            deckungserweiterungTyp.setTyp(Typ.TextEingabe);
            deckungserweiterungTyp.setLabel("Test Typ TextEingabe - Inhalt #" + Integer.toString(i));
            switch (i % 4) {
              case 0:
                deckungserweiterungTyp.setTyp(Typ.TextEingabe);
                break;
              case 1:
                deckungserweiterungTyp.setTyp(Typ.SelectUndTextEingabe);
                deckungserweiterungTyp.setPossibleListValues(new ArrayList<GsKeyValue>() {
                  {
                    add(new GsKeyValue("key01",
                                       "value01"));
                    add(new GsKeyValue("key02",
                                       "value02"));
                    add(new GsKeyValue("key03",
                                       "value03"));
                  }
                });
                break;
              case 2:
                deckungserweiterungTyp.setTyp(Typ.SelectEingabe);
                deckungserweiterungTyp.setPossibleListValues(new ArrayList<GsKeyValue>() {
                  {
                    add(new GsKeyValue("key01",
                                       "value01"));
                    add(new GsKeyValue("key02",
                                       "value02"));
                    add(new GsKeyValue("key03",
                                       "value03"));
                  }
                });
                break;
              case 3:
                deckungserweiterungTyp.setTyp(Typ.OhneEingabe);
                break;
            }
            liste.add(deckungserweiterungTyp);
          }
          ctr++;
          gebaeudeVersicherung.getVersichertesGebaeude(gebaeude.getIdVersichertesObjekt())
                              .setOrtsbezogeneDeckungserweiterungen(liste);
        }
      }
    } else if (produkt.equals(new GsKeyValue("PR_PHOTO",
                                             "Photovoltaik"))) {
      PhotovoltaikVersicherung photovoltaikVersicherung = response.getAntrag()
                                                                  .getPhotovoltaikVersicherung();
      if (photovoltaikVersicherung.getDeckungserweiterung() == null ||
          photovoltaikVersicherung.getDeckungserweiterung()
                                  .size() == 0) {
        Deckungserweiterung deckungserweiterungTyp1 = new Deckungserweiterung();
        deckungserweiterungTyp1.setId("1");
        deckungserweiterungTyp1.setTyp(Typ.OhneEingabe);
        deckungserweiterungTyp1.setLabel("Test Typ1 - Photovoltaik");
        response.getAntrag()
                .getPhotovoltaikVersicherung()
                .getDeckungserweiterung()
                .add(deckungserweiterungTyp1);

        Deckungserweiterung deckungserweiterungTyp2 = new Deckungserweiterung();
        deckungserweiterungTyp2.setId("2");
        deckungserweiterungTyp2.setTyp(Typ.TextEingabe);
        deckungserweiterungTyp2.setLabel("Test Typ2 - Photovoltaik");
        response.getAntrag()
                .getPhotovoltaikVersicherung()
                .getDeckungserweiterung()
                .add(deckungserweiterungTyp2);

        Deckungserweiterung deckungserweiterungTyp3 = new Deckungserweiterung();
        deckungserweiterungTyp3.setId("3");
        deckungserweiterungTyp3.setTyp(Typ.SelectEingabe);
        deckungserweiterungTyp3.setLabel("Test Typ3 - Photovoltaik");
        response.getAntrag()
                .getPhotovoltaikVersicherung()
                .getDeckungserweiterung()
                .add(deckungserweiterungTyp3);
      }
      int ctr = 2;
      for (VersichertesGebaeudeElektronikMaschinenPhotovoltaik gebaeude : photovoltaikVersicherung.getVersicherteGebaeude()) {
        if (gebaeude.getOrtsbezogeneDeckungserweiterungen() == null ||
            gebaeude.getOrtsbezogeneDeckungserweiterungen()
                    .size() == 0) {
          List<Deckungserweiterung> liste = new ArrayList<>();
          for (int i = 0; i < ctr; i++) {
            Deckungserweiterung deckungserweiterungTyp = new Deckungserweiterung();
            deckungserweiterungTyp.setId(Integer.toString(i + 1));
            deckungserweiterungTyp.setTyp(Typ.TextEingabe);
            deckungserweiterungTyp.setLabel("Test Typ TextEingabe - Inhalt #" + Integer.toString(i));
            switch (i % 4) {
              case 0:
                deckungserweiterungTyp.setTyp(Typ.TextEingabe);
                break;
              case 1:
                deckungserweiterungTyp.setTyp(Typ.SelectUndTextEingabe);
                deckungserweiterungTyp.setPossibleListValues(new ArrayList<GsKeyValue>() {
                  {
                    add(new GsKeyValue("key01",
                                       "value01"));
                    add(new GsKeyValue("key02",
                                       "value02"));
                    add(new GsKeyValue("key03",
                                       "value03"));
                  }
                });
                break;
              case 2:
                deckungserweiterungTyp.setTyp(Typ.SelectEingabe);
                deckungserweiterungTyp.setPossibleListValues(new ArrayList<GsKeyValue>() {
                  {
                    add(new GsKeyValue("key01",
                                       "value01"));
                    add(new GsKeyValue("key02",
                                       "value02"));
                    add(new GsKeyValue("key03",
                                       "value03"));
                  }
                });
                break;
              case 3:
                deckungserweiterungTyp.setTyp(Typ.OhneEingabe);
                break;
            }
            liste.add(deckungserweiterungTyp);
          }
          ctr++;
          photovoltaikVersicherung.getVersichertesGebaeude(gebaeude.getIdVersichertesObjekt())
                                  .setOrtsbezogeneDeckungserweiterungen(liste);
        }
      }
    }
  }

  private void updatePlusbaustein(GsKeyValue produkt,
                                  TAAProduktKonfigurationResponse response) {

    // Plusbaistein anlegen
    List<PlusBaustein> plusBausteine = new ArrayList<>();
    if (produkt.equals(new GsKeyValue("PR_BHV",
                                      "Betriebshaftpflicht"))) {
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
                                                       null),
                                         true));
      plusBausteine.add(new PlusBaustein(GUID.get(),
                                         3,
                                         "Plusbaustein III",
                                         false,
                                         new GsToolTip("PR_BHV_plusbaustein_tooltip3",
                                                       "PR_BHV_plusbaustein_tooltip Text",
                                                       "Lirum larum Löffelstiel. - Text 3",
                                                       null)));
      response.getAntrag()
              .getBetriebsHaftpflichtVersicherung()
              .getTarifKonfiguration1()
              .setPlusBausteine(plusBausteine);
      response.getAntrag()
              .getBetriebsHaftpflichtVersicherung()
              .getTarifKonfiguration2()
              .setPlusBausteine(plusBausteine);
    } else if (produkt.equals(new GsKeyValue("PR_PHV",
                                             "Private Risiken"))) {
      plusBausteine.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         true,
                                         null));
      plusBausteine.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         false,
                                         new GsToolTip("PR_PHV_plusbaustein_tooltip1",
                                                       "PR_PHV_plusbaustein_tooltip Text",
                                                       "Lirum larum Löffelstiel. - Text 1",
                                                       null)));
      response.getAntrag()
              .getPrivateRisiken()
              .getTarifKonfiguration1()
              .setPlusBausteine(plusBausteine);
      response.getAntrag()
              .getPrivateRisiken()
              .getTarifKonfiguration2()
              .setPlusBausteine(plusBausteine);
      // } else if (produkt
      // .equals(new GsKeyValue("PRGR_INH",
      // "Inhaltsversicherung"))) {
      // } else if (produkt
      // .equals(new GsKeyValue("PRGR_GEB",
      // "Gebäudeversicherung"))) {
    } else if (produkt.equals(new GsKeyValue("PR_PHOTO",
                                             "Photovoltaik"))) {
      plusBausteine.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         true,
                                         null));
      plusBausteine.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         false,
                                         new GsToolTip("PR_PHOTO_plusbaustein_tooltip1",
                                                       "PR_PHOTOV_plusbaustein_tooltip Text",
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
      response.getAntrag()
              .getPhotovoltaikVersicherung()
              .getTarifKonfiguration1()
              .setPlusBausteine(plusBausteine);
      response.getAntrag()
              .getPhotovoltaikVersicherung()
              .getTarifKonfiguration2()
              .setPlusBausteine(plusBausteine);
    } else if (produkt.equals(new GsKeyValue("PRGR_GEB",
                                             "Gebäudeversicherung"))) {
      plusBausteine.add(new PlusBaustein(GUID.get(),
                                         0,
                                         "Keine Auswahl",
                                         true,
                                         null));
      plusBausteine.add(new PlusBaustein(GUID.get(),
                                         1,
                                         "Plusbaustein I",
                                         false,
                                         new GsToolTip("PRGR_GEB_plusbaustein_tooltip1",
                                                       "PRGR_GEB_plusbaustein_tooltip Text",
                                                       "Lirum larum Löffelstiel. - Text 1",
                                                       null)));
      plusBausteine.add(new PlusBaustein(GUID.get(),
                                         2,
                                         "Plusbaustein II",
                                         false,
                                         new GsToolTip("PRGR_GEB_plusbaustein_tooltip2",
                                                       "PRGR_GEB_plusbaustein_tooltip Text",
                                                       "Lirum larum Löffelstiel. - Text 2",
                                                       null),
                                         true));
      response.getAntrag()
              .getGebaeudeVersicherung()
              .getTarifKonfiguration1()
              .setPlusBausteine(plusBausteine);
      response.getAntrag()
              .getGebaeudeVersicherung()
              .getTarifKonfiguration2()
              .setPlusBausteine(plusBausteine);
    }
  }

  private void updateDynamischeAttribute(GsKeyValue produkt,
                                         TAAProduktKonfigurationResponse response) {
    if (produkt.equals(new GsKeyValue("PRGR_INH",
                                      "Inhaltsversicherung"))) {
      InhaltsVersicherung inhaltsVersicherung = response.getAntrag()
                                                        .getInhaltsVersicherung();
      for (VersichertesGebaeudeInhalt gebaeudeInhalt : inhaltsVersicherung.getVersicherteGebaeude()) {
        if (gebaeudeInhalt.isEinbruchDiebstahl()) {
          if (gebaeudeInhalt.getDynamischeAttribute() == null ||
              gebaeudeInhalt.getDynamischeAttribute()
                            .size() == 0) {
            List<PMAttribut> liste = new ArrayList<>();
            liste.add(new PMAttribut(GUID.get(),
                                     "Aufbewahrungsort für Wertgegenstände",
                                     PMAttribut.Type.TEXT_FIELD,
                                     true));
            liste.add(new PMAttribut(GUID.get(),
                                     "Bugs Bunny bewacht Tresor?",
                                     Type.RADIO,
                                     true));
            response.getAntrag()
                    .getInhaltsVersicherung()
                    .getVersichertesGebaeude(gebaeudeInhalt.getIdVersichertesObjekt())
                    .setEinburchDiebstahlAttribute(liste);
          }
        }
      }
    }
  }

  private GruppenunfallLeistungsblock updateGruppenunfallLeistungsblock(Antrag antrag,
                                                                        GruppenunfallLeistungsblock gruppenunfallLeistungsblock) {
    List<GruppenunfallLeistungsart> newLeistungsArten = new ArrayList<>();
    if (gruppenunfallLeistungsblock.getArtDerTaetigkeit()
                                   .getKey()
                                   .equals("Geschäftsführer")) {
      for (int i = 0; i < 6; i++) {
        newLeistungsArten.add(new GruppenunfallLeistungsart(createGruppenunfallDynamischesAttributeArtDerDeckung(i),
                                                            createGruppenunfallDynamischesAttributeVsu(i)));

      }
    } else if (gruppenunfallLeistungsblock.getArtDerTaetigkeit()
                                          .getKey()
                                          .equals("kaufmännische Angestellte")) {

      for (int i = 0; i < 4; i++) {
        newLeistungsArten.add(new GruppenunfallLeistungsart(createGruppenunfallDynamischesAttributeArtDerDeckung(i),
                                                            createGruppenunfallDynamischesAttributeVsu(i)));

      }
    } else if (gruppenunfallLeistungsblock.getArtDerTaetigkeit()
                                          .getKey()
                                          .equals("gewerbliche Angestellte")) {
      for (int i = 0; i < 3; i++) {
        newLeistungsArten.add(new GruppenunfallLeistungsart(createGruppenunfallDynamischesAttributeArtDerDeckung(i),
                                                            createGruppenunfallDynamischesAttributeVsu(i)));

      }
    } else if (gruppenunfallLeistungsblock.getArtDerTaetigkeit()
                                          .getKey()
                                          .equals("Programmierer")) {

      for (int i = 2; i < 6; i++) {
        newLeistungsArten.add(new GruppenunfallLeistungsart(createGruppenunfallDynamischesAttributeArtDerDeckung(i),
                                                            createGruppenunfallDynamischesAttributeVsu(i)));

      }
    } else if (gruppenunfallLeistungsblock.getArtDerTaetigkeit()
                                          .getKey()
                                          .equals("Auszubildende")) {
      for (int i = 2; i < 6; i++) {
        newLeistungsArten.add(new GruppenunfallLeistungsart(createGruppenunfallDynamischesAttributeArtDerDeckung(i),
                                                            createGruppenunfallDynamischesAttributeVsu(i)));

      }
    }
    // mergen oder adden, das ist hier die Frage ....
    // Übertrage bereits eingegebene Daten ....
    for (GruppenunfallLeistungsart oldModel : gruppenunfallLeistungsblock.getLeistungsarten()) {
      for (GruppenunfallLeistungsart newModel : newLeistungsArten) {
        if (oldModel.getVsu()
                    .getLabel()
                    .equals(newModel.getVsu()
                                    .getLabel())) {
          newModel.setArtDerDeckung(oldModel.getArtDerDeckung());
          newModel.setVsu(oldModel.getVsu());
        }
      }
    }
    gruppenunfallLeistungsblock.setLeistungsarten(newLeistungsArten);
    //    gruppenunfallLeistungsblock.setNumberOfLeistungsblock(antrag.getGruppenunfallVersicherung()
    //                                                                .getLeistungsbloecke()
    //                                                                .size() + 1);
    return gruppenunfallLeistungsblock;
  }

  private PMAttribut createGruppenunfallDynamischesAttributeArtDerDeckung(int index) {
    if (index == 0 || index == 1 || index == 5) {
      PMAttribut attribut = new PMAttribut(GUID.get(),
                                           gruppenunfallLabel[index],
                                           gruppenunfallType[index],
                                           true);
      attribut.getStore()
              .addAll(gruppenunfallStore[index]);
      return attribut;
    } else {
      return null;
    }
  }

  private PMAttribut createGruppenunfallDynamischesAttributeVsu(int index) {
    PMAttribut attribut = new PMAttribut(GUID.get(),
                                         gruppenunfallLabel[index],
                                         gruppenunfallType[index],
                                         true);

    if (index == 2 || index == 3 || index == 4) {
      attribut.setMinvalue(gruppenunfallMinValue[index]);
      attribut.setMaxvalue(gruppenunfallMaxValue[index]);
    }
    return attribut;
  }

}
