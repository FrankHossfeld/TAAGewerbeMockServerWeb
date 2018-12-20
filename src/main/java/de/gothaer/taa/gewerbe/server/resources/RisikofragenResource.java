package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsKeyValue;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.Risikofrage;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.Risikofrage.AnswerType;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.Risikofrage.QuestionType;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.TAA;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaRisikofragenRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.models.RisikoFragenRequestModel;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaRisikofragenResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.models.RisikoFragenResponseModel;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/risikofragen")
public class RisikofragenResource {

  @POST
  @Path("/getRisikofragen")
  @Produces("application/json")
  public TaaRisikofragenResponse getRisikofragen(TaaRisikofragenRequest request) {

    TaaRisikofragenResponse response = new TaaRisikofragenResponse();
    Status status = new Status();

    for (RisikoFragenRequestModel model : request.getProdukte()) {
      RisikoFragenResponseModel responseModel = new RisikoFragenResponseModel(model.getProdukt(),
                                                                              model.getTaetigkeiten(),
                                                                              model.getRisikoort(),
                                                                              null);
      if (model.getProdukt() != null &&
          model.getProdukt()
               .getKey()
               .contains("PR_BHV")) {
        responseModel.getRisikofragen()
                     .addAll(getRisikofragenBhv(model.getProdukt()));
      }
      if (model.getProdukt() != null &&
          model.getProdukt()
               .getKey()
               .contains("PR_PHV")) {
        for (GsKeyValue taetigkeit : model.getTaetigkeiten()) {
          if ("Bäckerei".equals(taetigkeit.getValue())) {
            responseModel.getRisikofragen()
                         .add(new Risikofrage("PHV_BLOEDE_FRAGE_01",
                                              "PHV_SUB_BLOEDE_FRAGE_01",
                                              model.getProdukt(),
                                              "Soll die haftung auch auf Tolpatschigkeit, Dummheit und so ausgeweitet werden?",
                                              null,
                                              createYesNoList(),
                                              AnswerType.BOOLEAN,
                                              QuestionType.SINGLE,
                                              null,
                                              new ArrayList<GsKeyValue>(),
                                              1));
          }
        }
      }
      if (model.getProdukt() != null &&
          model.getProdukt()
               .getKey()
               .contains("ELP_GEB_FEU")) {
        responseModel.getRisikofragen()
                     .addAll(getRisikofragenGebaeudeFeuer(model.getProdukt()));
      }
      if (model.getProdukt() != null &&
          model.getProdukt()
               .getKey()
               .contains("ELP_GEB_STU")) {
        responseModel.getRisikofragen()
                     .addAll(getRisikofragenGebaeudeSturm(model.getProdukt()));
      }
      if (model.getProdukt() != null &&
          model.getProdukt()
               .getKey()
               .contains("ELP_GEB_LTW")) {
        responseModel.getRisikofragen()
                     .addAll(getRisikofragenGebaeudeLeitungswasser(model.getProdukt()));
      }
      if (model.getProdukt() != null &&
          model.getProdukt()
               .getKey()
               .contains(TAA.RISIKOFRAGEN_PRODUKT_ID_WERKVERKEHR)) {
        responseModel.getRisikofragen()
                     .addAll(getRisikofragenWerkverkehr(model.getProdukt()));
      }
      if (model.getProdukt() != null &&
          model.getProdukt()
               .getKey()
               .contains("ELP_INH_FEU")) {
        responseModel.getRisikofragen()
                     .addAll(getRisikofragenInhaltFeuer(model.getProdukt()));
      }
      if (model.getProdukt() != null &&
          model.getProdukt()
               .getKey()
               .contains("ELP_INH_STU")) {
        responseModel.getRisikofragen()
                     .addAll(getRisikofragenInhaltSturm(model.getProdukt()));
      }
      if (model.getProdukt() != null &&
          model.getProdukt()
               .getKey()
               .contains("ELP_INH_LTW")) {
        responseModel.getRisikofragen()
                     .addAll(getRisikofragenInhaltLeitungswasser(model.getProdukt()));
      }
      if (model.getProdukt() != null &&
          model.getProdukt()
               .getKey()
               .contains("ELP_INH_ED")) {
        responseModel.getRisikofragen()
                     .addAll(getRisikofragenInhaltEinbruch(model.getProdukt()));
      }
      if (model.getProdukt() != null &&
          model.getProdukt()
               .getKey()
               .contains(TAA.RISIKOFRAGEN_PRODUKT_ID_BETRIEBSSCHLIESSUNG)) {
        responseModel.getRisikofragen()
                     .addAll(getRisikofragenBetriebsschliessung(model.getProdukt()));
      }
      if (model.getProdukt() != null &&
          (model.getProdukt()
                .getKey()
                .contains("VKP_GEW_PROT") ||
           model.getProdukt()
                .getKey()
                .contains("VKP_VSH"))) {
        responseModel.getRisikofragen()
                     .addAll(getAllgemeineRisikofragen(model.getProdukt()));
      }
      if (model.getProdukt() != null &&
          model.getProdukt()
               .getKey()
               .contains("ELP_MASCHINEN_FAHRBAR")) {
        responseModel.getRisikofragen()
                     .addAll(getMaschinenFahrbarRisikofragen(model.getProdukt()));
      }
      if (model.getProdukt() != null &&
          model.getProdukt()
               .getKey()
               .contains("ELP_MASCHINEN_STATIONAER")) {
        responseModel.getRisikofragen()
                     .addAll(getMaschinenStationaerRisikofragen(model.getProdukt()));
      }
      if (model.getProdukt() != null &&
          model.getProdukt()
               .getKey()
               .contains("ELP_MASCHINEN_STATIONAER")) {
        responseModel.getRisikofragen()
                     .addAll(getMaschinenStationaerRisikofragen(model.getProdukt()));
      }
      if (model.getProdukt() != null &&
          model.getProdukt()
               .getKey()
               .contains("ELP_ELEKTRONIK")) {
        responseModel.getRisikofragen()
                     .addAll(getRisikofragenElektronik(model.getProdukt()));
        // } else {
        // status.add(new GsMessage(GsMessage.Type.ERROR,
        // "n/a",
        // "Unbekanntes Produkt: " + model.getProduktConfiguration()
        // .getKey() + " / " + model.getProduktConfiguration()
        // .getValue(),
        // GsMessage.Source.SERVER,
        // GsMessage.Visibility.ONCE));
      }
      if (model.getProdukt() != null &&
          "ELP_PHOTOVOLTAIK".contains(model.getProdukt()
                                           .getKey())) {
        responseModel.getRisikofragen()
                     .addAll(getRisikofragenPhotovoltaik(model.getProdukt()));
      }
      if (model.getProdukt() != null &&
          model.getProdukt()
               .getKey()
               .contains("PR_VSH")) {
        responseModel.getRisikofragen()
                     .addAll(getRisikofragenVSH(model.getProdukt()));
      }

      response.getRisikoFragenResponseModels()
              .add(responseModel);
    }
    response.setStatus(status);
    return response;
  }

  private List<Risikofrage> getRisikofragenBhv(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();

    Risikofrage frage1 = new Risikofrage("RiskQuestion_Bhv1_SCOM",
                                         "bhvSubId1",
                                         produkt,
                                         "Erste Bhv-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         0);
    list.add(frage1);

    Risikofrage frage2 = new Risikofrage("RiskQuestion_Bhv2_SCOM",
                                         "bhvSubId2",
                                         produkt,
                                         "Zweite Bhv-Risikofrage",
                                         null,
                                         createSimpsonsList(),
                                         AnswerType.INTEGER,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         1);
    list.add(frage2);

    Risikofrage frage3 = new Risikofrage("RiskQuestion_Bhv3_SCOM",
                                         "bhvSubId3",
                                         produkt,
                                         "Dritte Bhv-Risikofrage",
                                         null,
                                         createSimpsonsList(),
                                         AnswerType.INTEGER,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         2);
    list.add(frage3);

    Risikofrage frage4 = new Risikofrage("RiskQuestion_Bhv4_SCOM",
                                         "bhvSubId4",
                                         produkt,
                                         "Vierte Bhv-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         3);
    list.add(frage4);

    Risikofrage frage5 = new Risikofrage("RiskQuestion_Bhv5_SCOM",
                                         "bhvSubId5",
                                         produkt,
                                         "Dies ist eine dunkle Risikofrage und darf nicht angezeigt werden!!",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.AUTOMATIC,
                                         null,
                                         null,
                                         4);
    list.add(frage5);

    Risikofrage frage6 = new Risikofrage("RiskQuestion_Bhv6_SCOM",
                                         "bhvSubId6",
                                         produkt,
                                         "Sechste Bhv-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         5);
    List<Risikofrage> folgefragen = new ArrayList<>();
    folgefragen.add(new Risikofrage("RiskQuestion_Bhv61_SCOM",
                                    "bhvSubId61",
                                    produkt,
                                    "Erste Folgefrage zu Frage 6",
                                    null,
                                    createYesNoList(),
                                    AnswerType.BOOLEAN,
                                    QuestionType.SINGLE,
                                    null,
                                    null,
                                    0));
    folgefragen.add(new Risikofrage("RiskQuestion_Bhv62_SCOM",
                                    "bhvSubId62",
                                    produkt,
                                    "Zweite Folgefrage zu Frage 6",
                                    null,
                                    createYesNoList(),
                                    AnswerType.BOOLEAN,
                                    QuestionType.SINGLE,
                                    null,
                                    null,
                                    1));
    frage6.setFolgeFragen(folgefragen);
    list.add(frage6);

    Risikofrage frage7 = new Risikofrage("RiskQuestion_Bhv7_SCOM",
                                         "bhvSubId7",
                                         produkt,
                                         "Siebte Bhv-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         6);
    list.add(frage7);

    Risikofrage frage8 = new Risikofrage("RiskQuestion_Bhv8_SCOM",
                                         "bhvSubId8",
                                         produkt,
                                         "Achte Bhv-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         7);
    list.add(frage8);

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

  private List<Risikofrage> getRisikofragenGebaeudeFeuer(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();
    Risikofrage frage1 = new Risikofrage("RiskQuest_WallWoodRoofSoft_GCOM",
                                         "WallWoodRoofSoft",
                                         produkt,
                                         "Sind die Außenwände des Gebäudes / eines der Gebäude überwiegend aus Holz oder hat das Gebäude / eines der Gebäude eine weiche Bedachung (z.B. Holz, Ried, Schilf oder Stroh)?",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         new ArrayList<GsKeyValue>(),
                                         1);
    list.add(frage1);
    Risikofrage frage2 = new Risikofrage("RiskQuest_BuildingEmpty_GCOM",
                                         "BuildingEmpty",
                                         produkt,
                                         "Steht das Gebäude / eines der Gebäude leer oder teilweise leer (Leerstand ab 40%)?",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         2);
    list.add(frage2);
    Risikofrage frage3 = new Risikofrage("RiskQuest_BuildingOpenFlanks_GCOM",
                                         "BuildingOpenFlanks",
                                         produkt,
                                         "Hat das Gebäude /eines der Gebäude eine oder mehrere offene Seiten?",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         3);
    list.add(frage3);
    return list;
  }

  private List<Risikofrage> getRisikofragenGebaeudeSturm(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();

    Risikofrage frage1 = new Risikofrage("RiskQuestion_InhaltGebaeude0201_SCOM",
                                         "inhGebSubId0201",
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

  private List<Risikofrage> getRisikofragenGebaeudeLeitungswasser(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();
    Risikofrage frage1 = new Risikofrage("RiskQuest_BuildingEmpty_GCOM",
                                         "BuildingEmpty",
                                         produkt,
                                         "Steht das Gebäude / eines der Gebäude leer oder teilweise leer (Leerstand ab 40%)?",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         new ArrayList<GsKeyValue>(),
                                         2);
    list.add(frage1);
    Risikofrage frage2 = new Risikofrage("RiskQuest_BuildingOpenFlanks_GCOM",
                                         "BuildingOpenFlanks",
                                         produkt,
                                         "Hat das Gebäude /eines der Gebäude eine oder mehrere offene Seiten?",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         3);
    list.add(frage2);
    return list;
  }

  private List<Risikofrage> getRisikofragenWerkverkehr(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();

    Risikofrage frage1 = new Risikofrage("RiskQuestion_IWerkverkehr01_SCOM",
                                         "wvkSubId01",
                                         produkt,
                                         "Erste Werkverkehr-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         0);
    list.add(frage1);

    return list;
  }

  private List<Risikofrage> getRisikofragenInhaltFeuer(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();

    Risikofrage frage1 = new Risikofrage("RiskQuestion_Inhalt1_SCOM",
                                         "inhSubId1",
                                         produkt,
                                         "Erste Inhalt-Risikofrage",
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
                                         "inhSubid2",
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

    Risikofrage frage3 = new Risikofrage("RiskQuestion_Inhalt3_FEU_STURM_SCOM",
                                         "inhFeuSturmSubId1",
                                         produkt,
                                         "Erste Inhalt-Feuer-Sturm-Risikofrage",
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
    list.add(frage3);

    Risikofrage frage4 = new Risikofrage("RiskQuestion_Inhalt3_FEU_STURM_SCOM",
                                         "inhFeuSturmSubId2",
                                         produkt,
                                         "Zweite Inhalt-Feuer-Sturm-Risikofrage",
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
    list.add(frage4);

    return list;
  }

  private List<Risikofrage> getRisikofragenInhaltSturm(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();

    Risikofrage frage1 = new Risikofrage("RiskQuestion_InhaltGebaeude0201_SCOM",
                                         "inhGebSubId0201",
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

    Risikofrage frage2 = new Risikofrage("RiskQuestion_InhaltGebaeude0201_SCOM",
                                         "inhSturmSubId0202",
                                         produkt,
                                         "Reine Inhalt-Sturm-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         new ArrayList<GsKeyValue>(),
                                         0);
    list.add(frage2);

    Risikofrage frage3 = new Risikofrage("RiskQuestion_Inhalt3_FEU_STURM_SCOM",
                                         "inhFeuSturmSubId1",
                                         produkt,
                                         "Erste Inhalt-Feuer-Sturm-Risikofrage",
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
    list.add(frage3);

    Risikofrage frage4 = new Risikofrage("RiskQuestion_Inhalt3_FEU_STURM_SCOM",
                                         "inhFeuSturmSubId2",
                                         produkt,
                                         "Zweite Inhalt-Feuer-Sturm-Risikofrage",
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
    list.add(frage4);

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

  private List<Risikofrage> getRisikofragenInhaltEinbruch(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();

    Risikofrage frage1 = new Risikofrage("RiskQuestion_Inhalt1201_SCOM",
                                         "inhSubI1201",
                                         produkt,
                                         "Erste Einbruch-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         new ArrayList<>(Arrays.asList(new GsKeyValue("inhSubI1201",
                                                                                      "Tue es oder tue es nicht. Es gibt kein Versuchen, nur ein Eimbruch"))),
                                         0);
    list.add(frage1);

    Risikofrage frage2 = new Risikofrage("RiskQuestion_Inhalt1202_SCOM",
                                         "inhSubI1202",
                                         produkt,
                                         "Zweite Einbruch-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         1);
    List<Risikofrage> folgefragen = new ArrayList<>();
    folgefragen.add(new Risikofrage("RiskQuestion_Inhalt12021_SCOM",
                                    "inhSubI12021",
                                    produkt,
                                    "Erste Folgefrage zu Frage 2",
                                    null,
                                    createYesNoList(),
                                    AnswerType.BOOLEAN,
                                    QuestionType.SINGLE,
                                    null,
                                    null,
                                    0));
    folgefragen.add(new Risikofrage("RiskQuestion_Inhalt12022_SCOM",
                                    "inhSubI12022",
                                    produkt,
                                    "Zweite Folgefrage zu Frage 2",
                                    null,
                                    createYesNoList(),
                                    AnswerType.BOOLEAN,
                                    QuestionType.SINGLE,
                                    null,
                                    null,
                                    1));
    frage2.setFolgeFragen(folgefragen);
    list.add(frage2);

    Risikofrage frage3 = new Risikofrage("RiskQuestion_Inhalt1203_SCOM",
                                         "inhSubI1203",
                                         produkt,
                                         "Dritte Einbruch-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         2);
    list.add(frage3);

    return list;
  }

  private List<Risikofrage> getRisikofragenBetriebsschliessung(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();

    Risikofrage frage1 = new Risikofrage("RiskQuestion_Bsch1_SCOM",
                                         "BschSubId1",
                                         produkt,
                                         "Erste Bsch-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         0);
    list.add(frage1);

    Risikofrage frage2 = new Risikofrage("RiskQuestion_Bsch2_SCOM",
                                         "BschSubId2",
                                         produkt,
                                         "Zweite Bsch-Risikofrage",
                                         null,
                                         createSimpsonsList(),
                                         AnswerType.INTEGER,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         1);
    list.add(frage2);

    Risikofrage frage3 = new Risikofrage("RiskQuestion_Bsch3_SCOM",
                                         "BschSubId3",
                                         produkt,
                                         "Dritte Bsch-Risikofrage",
                                         null,
                                         createSimpsonsList(),
                                         AnswerType.INTEGER,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         2);
    list.add(frage3);

    return list;
  }

  private List<Risikofrage> getAllgemeineRisikofragen(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();

    Risikofrage frage1 = new Risikofrage("RiskQuestion_R1",
                                         "allgSubId1",
                                         produkt,
                                         "Prototypen vorhanden? Diese Fragen bekommt einen irre lange Text ohne Sonderzeichen. " +
                                         "Damit das klappt muessen wir jetzt eine Menge Blabla hier " +
                                         "reinschreiben. Das ist schon nicht unwichtig, da die Fragen vollstaendig zu lesen sind." +
                                         " Sonst weiss der User ja gar nicht, was er beantworten soll!" +
                                         " Gell?",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         2);
    // new ArrayList<>(Arrays.asList(new GsKeyValue("allgSubId101",
    // "Manchmal sind solche Meldungen schon ganz schoen nervig!"))));
    list.add(frage1);

    Risikofrage frage2 = new Risikofrage("RiskQuestion_R2",
                                         "allgSubId2",
                                         produkt,
                                         "Maschinen vom Hersteller Siemens vorhanden?",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         0);
    list.add(frage2);

    Risikofrage frage3 = new Risikofrage("RiskQuestion_R3",
                                         "allgSubId3",
                                         produkt,
                                         "Wurden ale wichtigen Angeben gemacht?",
                                         null,
                                         createSimpsonsList(),
                                         AnswerType.INTEGER,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         1);
    list.add(frage3);

    return list;
  }

  private List<Risikofrage> getMaschinenFahrbarRisikofragen(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();

    Risikofrage frage1 = new Risikofrage("RiskQuestion_MaschFahr1_SCOM",
                                         "gebSubId233",
                                         produkt,
                                         "Erste Maschinen-Fahrbar-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         new ArrayList<>(Arrays.asList(new GsKeyValue("gebSubId233",
                                                                                      "Hoffe, die Maschine tutsund fährt"))),
                                         15);
    list.add(frage1);

    Risikofrage frage2 = new Risikofrage("RiskQuestion_MaschFahr2_SCOM",
                                         "subid21201",
                                         produkt,
                                         "Zweite Maschinen-Fahrbar-Risikofrage",
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

  private List<Risikofrage> getMaschinenStationaerRisikofragen(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();

    Risikofrage frage1 = new Risikofrage("RiskQuestion_MaschStat1_SCOM",
                                         "gebSubId3",
                                         produkt,
                                         "Erste Maschinen-Stationaär-Risikofrage",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         new ArrayList<>(Arrays.asList(new GsKeyValue("gebSubId3",
                                                                                      "Hoffe, die Maschine tuts"))),
                                         15);
    list.add(frage1);

    Risikofrage frage2 = new Risikofrage("RiskQuestion_MaschStat2_SCOM",
                                         "subid21201",
                                         produkt,
                                         "Zweite Maschinen-Stationaär-Risikofrage",
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

  private List<Risikofrage> getRisikofragenElektronik(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();

    // FRAGE 1

    Risikofrage frage1 = new Risikofrage("RiskQuestion_R1_ele",
                                         "elesubid1",
                                         new GsKeyValue(TAA.RISIKOFRAGEN_PRODUKT_ID_ELEKTRONIK,
                                                        TAA.RISIKOFRAGEN_PRODUKT_ID_ELEKTRONIK),
                                         "FRAGE 1: Prototypen vorhanden?",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         0);
    list.add(frage1);

    // FRAGE 2

    Risikofrage frage2 = new Risikofrage("RiskQuestion_R2_ele",
                                         "elesubid2",
                                         new GsKeyValue(TAA.RISIKOFRAGEN_PRODUKT_ID_ELEKTRONIK,
                                                        TAA.RISIKOFRAGEN_PRODUKT_ID_ELEKTRONIK),
                                         "FRAGE 2: STEVE JOBS",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         1);

    List<Risikofrage> folgefragenZuFrage2 = new ArrayList<>();

    folgefragenZuFrage2.add(new Risikofrage("RiskQuestion_FolgeFragen_R2_ele",
                                            "elesubidFFR2",
                                            produkt,
                                            ">>> FOLGEFRAGE 1 ZU FRAGE 2 - STEVE JOBS (LEVEL 1)",
                                            null,
                                            createYesNoList(),
                                            AnswerType.BOOLEAN,
                                            QuestionType.SINGLE,
                                            null,
                                            null,
                                            6));

    Risikofrage frageLevel3 = new Risikofrage("RiskQuestion_Level3_R1_ele",
                                              "elesubidlevel3r1",
                                              produkt,
                                              "*** FOLGEFRAGE 1 VON FOLGEFRAGE 2 (LEVEL 2)",
                                              null,
                                              createYesNoList(),
                                              AnswerType.BOOLEAN,
                                              QuestionType.SINGLE,
                                              null,
                                              null,
                                              5);

    List<Risikofrage> folgefragenFrage2Level3 = new ArrayList<>();
    folgefragenFrage2Level3.add(new Risikofrage("RiskQuestion_Level3_R1_ele",
                                                "elesubidlevel31",
                                                produkt,
                                                " &&& FOLGEFRAGEN 1 VON FOlGEFRAGEN 2 (LEVEL 3)",
                                                null,
                                                createYesNoList(),
                                                AnswerType.BOOLEAN,
                                                QuestionType.SINGLE,
                                                null,
                                                null,
                                                4));

    frageLevel3.setFolgeFragen(folgefragenFrage2Level3);

    List<Risikofrage> folgefragenFrage2Level2 = new ArrayList<>();
    folgefragenFrage2Level2.add(frageLevel3);
    folgefragenFrage2Level2.add(new Risikofrage("RiskQuestion_R82_ele",
                                                "elesubid82",
                                                produkt,
                                                "*** FOLGEFRAGE 2 VON FOLGEFRAGE 2 (LEVEL 2)",
                                                null,
                                                createYesNoList(),
                                                AnswerType.BOOLEAN,
                                                QuestionType.SINGLE,
                                                null,
                                                null,
                                                3));

    Risikofrage folgefrage2Zu2 = new Risikofrage("RiskQuestion_R8_ele",
                                                 "elesubid8",
                                                 produkt,
                                                 ">>> FOLGEFRAGE 2 ZU FRAGE 2 - STEVE JOBS (LEVEL 1)",
                                                 null,
                                                 createYesNoList(),
                                                 AnswerType.BOOLEAN,
                                                 QuestionType.SINGLE,
                                                 null,
                                                 null,
                                                 2);

    folgefrage2Zu2.setFolgeFragen(folgefragenFrage2Level2);

    folgefragenZuFrage2.add(folgefrage2Zu2);

    frage2.setFolgeFragen(folgefragenZuFrage2);

    list.add(frage2);

    // FRAGE 3

    Risikofrage frage3 = new Risikofrage("RiskQuestion_R3_ele",
                                         "elesubid3",
                                         new GsKeyValue(TAA.RISIKOFRAGEN_PRODUKT_ID_ELEKTRONIK,
                                                        TAA.RISIKOFRAGEN_PRODUKT_ID_ELEKTRONIK),
                                         "FRAGE 3: Maschinen vom Hersteller Siemens vorhanden?",
                                         null,
                                         createYesNoList(),
                                         AnswerType.BOOLEAN,
                                         QuestionType.SINGLE,
                                         null,
                                         null,
                                         7);

    List<Risikofrage> folgefragenZu3 = new ArrayList<>();
    folgefragenZu3.add(new Risikofrage("RiskQuestion_R21_ele",
                                       "elesubid31",
                                       produkt,
                                       "Erste Folgefrage zu Frage 3",
                                       null,
                                       createYesNoList(),
                                       AnswerType.BOOLEAN,
                                       QuestionType.SINGLE,
                                       null,
                                       null,
                                       8));
    folgefragenZu3.add(new Risikofrage("RiskQuestion_R22_ele",
                                       "elesubid32",
                                       produkt,
                                       "Zweite Folgefrage zu Frage 3",
                                       null,
                                       createYesNoList(),
                                       AnswerType.BOOLEAN,
                                       QuestionType.SINGLE,
                                       null,
                                       null,
                                       9));
    frage3.setFolgeFragen(folgefragenZu3);

    list.add(frage3);

    return list;
  }

  private List<Risikofrage> getRisikofragenPhotovoltaik(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();

    list.add(new Risikofrage("photovoltaikId01",
                             "photovoltaikSubId01",
                             produkt,
                             "Erste Test-Risikofrage zu Photovoltaik",
                             null,
                             createYesNoList(),
                             AnswerType.BOOLEAN,
                             QuestionType.SINGLE,
                             null,
                             null,
                             0));

    list.add(new Risikofrage("photovoltaikId02",
                             "photovoltaikSubId02",
                             produkt,
                             "Zweite Test-Risikofrage zu Photovoltaik",
                             null,
                             createYesNoList(),
                             AnswerType.BOOLEAN,
                             QuestionType.SINGLE,
                             null,
                             null,
                             1));

    return list;
  }

  private List<Risikofrage> getRisikofragenVSH(GsKeyValue produkt) {
    List<Risikofrage> list = new ArrayList<>();

    list.add(new Risikofrage("vshId01",
                             "vshSubId01",
                             produkt,
                             "Erste Test-Risikofrage zu VSH",
                             null,
                             createYesNoList(),
                             AnswerType.BOOLEAN,
                             QuestionType.SINGLE,
                             null,
                             null,
                             0));
    list.add(new Risikofrage("vshId02",
                             "vshSubId02",
                             produkt,
                             "Zweite Test-Risikofrage zu VSH",
                             null,
                             createYesNoList(),
                             AnswerType.BOOLEAN,
                             QuestionType.SINGLE,
                             null,
                             null,
                             1));
    list.add(new Risikofrage("vshId03",
                             "vshSubId03",
                             produkt,
                             "Dritte Test-Risikofrage zu VSH",
                             null,
                             createYesNoList(),
                             AnswerType.BOOLEAN,
                             QuestionType.SINGLE,
                             null,
                             null,
                             2));
    list.add(new Risikofrage("vshId04",
                             "vshSubId04",
                             produkt,
                             "Vierte Test-Risikofrage zu VSH",
                             null,
                             createYesNoList(),
                             AnswerType.BOOLEAN,
                             QuestionType.SINGLE,
                             null,
                             null,
                             3));
    list.add(new Risikofrage("vshId05",
                             "vshSubId05",
                             produkt,
                             "Fünfte Test-Risikofrage zu VSH",
                             null,
                             createYesNoList(),
                             AnswerType.BOOLEAN,
                             QuestionType.SINGLE,
                             null,
                             null,
                             4));
    list.add(new Risikofrage("vshId06",
                             "vshSubId06",
                             produkt,
                             "Sechste Test-Risikofrage zu VSH",
                             null,
                             createYesNoList(),
                             AnswerType.BOOLEAN,
                             QuestionType.SINGLE,
                             null,
                             null,
                             5));
    list.add(new Risikofrage("vshId07",
                             "vshSubId07",
                             produkt,
                             "Siebte Test-Risikofrage zu VSH",
                             null,
                             createYesNoList(),
                             AnswerType.BOOLEAN,
                             QuestionType.SINGLE,
                             null,
                             null,
                             6));
    list.add(new Risikofrage("vshId08",
                             "vshSubId08",
                             produkt,
                             "Achte Test-Risikofrage zu VSH",
                             null,
                             createYesNoList(),
                             AnswerType.BOOLEAN,
                             QuestionType.SINGLE,
                             null,
                             null,
                             7));

    return list;
  }

  private List<GsKeyValue> createSimpsonsList() {
    List<GsKeyValue> list = new ArrayList<>();
    list.add(new GsKeyValue("Nein",
                            "Nein"));
    list.add(new GsKeyValue("Ja",
                            "Ja"));
    list.add(new GsKeyValue("Vielleicht",
                            "Vielleicht"));
    list.add(new GsKeyValue("Ich bin mir nicht so ganz sicher! Vielleicht doch, oder doch nicht?",
                            "Ich bin mir nicht so ganz sicher! Vielleicht doch, oder doch nicht?"));
    list.add(new GsKeyValue("1",
                            "Ohne Konzession"));
    list.add(new GsKeyValue("2",
                            "Andere Konzession"));
    list.add(new GsKeyValue("3",
                            "Restaurant Konzession"));
    list.add(new GsKeyValue("4",
                            "Gaststätte Konzession"));
    list.add(new GsKeyValue("5",
                            "Restaurant / Gaststätte Konzession"));
    list.add(new GsKeyValue("6",
                            "SB-Restaurant Konzession"));
    list.add(new GsKeyValue("7",
                            "Cafe Konzession"));
    list.add(new GsKeyValue("8",
                            "Eisdiele Konzession"));
    list.add(new GsKeyValue("9",
                            "Schankwirtschaft Konzession"));
    list.add(new GsKeyValue("10",
                            "Anzeigepflicht nach Landesgaststättengesetz mit Alkoholausschank (kein Imbiss)"));
    list.add(new GsKeyValue("11",
                            "Anzeigepflicht nach Landesgaststättengesetz ohne Alkohlausschank"));
    list.add(new GsKeyValue("12",
                            "erlaubnisfreier Weinausschank (Straußen-, Hecken-, Kranzwirtschaft)"));
    return list;
  }

  @POST
  @Path("/checkRisikofragen")
  public TaaRisikofragenResponse checkRisikofragen(TaaRisikofragenRequest request) {
    TaaRisikofragenResponse response = new TaaRisikofragenResponse();
    Status status = new Status();

    for (RisikoFragenRequestModel model : request.getProdukte()) {
      RisikoFragenResponseModel responseModel = new RisikoFragenResponseModel(model.getProdukt(),
                                                                              model.getTaetigkeiten(),
                                                                              model.getRisikoort(),
                                                                              model.getRisikofragen());
      for (Risikofrage risikofrage : responseModel.getRisikofragen()) {
        if ("allgSubId2".equals(risikofrage.getSubId())) {
          if (risikofrage.getAnswer() != null &&
              "true".equals(risikofrage.getAnswer()
                                       .getKey())) {
            if (risikofrage.getRejection() == null) {
              risikofrage.setRejection(new ArrayList<GsKeyValue>());
            }
            risikofrage.getRejection()
                       .add(new GsKeyValue("allgSubid2_ERROR",
                                           "nein, die maschienen wollen wir nicht!"));
          }
        }
        if ("bhvSubId1".equals(risikofrage.getSubId())) {
          if (risikofrage.getAnswer() != null &&
              "true".equals(risikofrage.getAnswer()
                                       .getKey())) {
            if (risikofrage.getRejection() == null) {
              risikofrage.setRejection(new ArrayList<GsKeyValue>());
            }
            risikofrage.getRejection()
                       .add(new GsKeyValue("bhvSubId1_ERROR",
                                           "nein, erste Frage falsch beantwortet!"));
          }
        }
        if ("WallWoodRoofSoft".equals(risikofrage.getSubId())) {
          if (risikofrage.getAnswer() != null &&
              "true".equals(risikofrage.getAnswer()
                                       .getKey())) {
            if (risikofrage.getRejection() == null) {
              risikofrage.setRejection(new ArrayList<GsKeyValue>());
            }
            risikofrage.getRejection()
                       .add(new GsKeyValue("WallWoodRoofSoft_ERROR",
                                           "nTest: Anzeige Fehler auf Antragsfragen"));
          }
        }

        //        RiskQuestion_Bhv8_SCOM
        //        bhvSubId8
        if ("bhvSubId8".equals(risikofrage.getSubId())) {
          if (risikofrage.getAnswer() != null &&
              "true".equals(risikofrage.getAnswer()
                                       .getKey())) {
            if (risikofrage.getFolgeFragen() == null ||
                risikofrage.getFolgeFragen()
                           .size() == 0) {
              List<Risikofrage> folgefragen = new ArrayList<>();
              folgefragen.add(new Risikofrage("RiskQuestion_Bhv81_SCOM",
                                              "bhvSubId81",
                                              model.getProdukt(),
                                              "Erste Folgefrage zu Frage 8",
                                              null,
                                              createYesNoList(),
                                              AnswerType.BOOLEAN,
                                              QuestionType.SINGLE,
                                              null,
                                              null,
                                              0));
              folgefragen.add(new Risikofrage("RiskQuestion_Bhv82_SCOM",
                                              "bhvSubId82",
                                              model.getProdukt(),
                                              "Zweite Folgefrage zu Frage 8",
                                              null,
                                              createYesNoList(),
                                              AnswerType.BOOLEAN,
                                              QuestionType.SINGLE,
                                              null,
                                              null,
                                              1));
              risikofrage.setFolgeFragen(folgefragen);
            }
          }
        }

        if ("inhSubI1203".equals(risikofrage.getSubId())) {
          if (risikofrage.getAnswer() != null &&
              "true".equals(risikofrage.getAnswer()
                                       .getKey())) {
            if (risikofrage.getFolgeFragen() == null ||
                risikofrage.getFolgeFragen()
                           .size() == 0) {
              List<Risikofrage> folgefragen = new ArrayList<>();
              folgefragen.add(new Risikofrage("RiskQuestion_Inhalt12031_SCOM",
                                              "inhSubI12031",
                                              model.getProdukt(),
                                              "Erste Folgefrage zu Frage 3",
                                              null,
                                              createYesNoList(),
                                              AnswerType.BOOLEAN,
                                              QuestionType.SINGLE,
                                              null,
                                              null,
                                              0));
              folgefragen.add(new Risikofrage("RiskQuestion_Inhalt12032_SCOM",
                                              "inhSubI12032",
                                              model.getProdukt(),
                                              "Zweite Folgefrage zu Frage 3",
                                              null,
                                              createYesNoList(),
                                              AnswerType.BOOLEAN,
                                              QuestionType.SINGLE,
                                              null,
                                              null,
                                              1));
              risikofrage.setFolgeFragen(folgefragen);
            }
          }
        }

        if ("vshSubId01".equals(risikofrage.getSubId())) {
          if (risikofrage.getAnswer() != null &&
              "true".equals(risikofrage.getAnswer()
                                       .getKey())) {
            if (risikofrage.getFolgeFragen() == null ||
                risikofrage.getFolgeFragen()
                           .size() == 0) {
              List<Risikofrage> folgefragen = new ArrayList<>();
              folgefragen.add(new Risikofrage("RiskQuestion_VSH12031_SCOM",
                                              "vshSubId0101",
                                              model.getProdukt(),
                                              "Erste Folgefrage zu Frage 1",
                                              null,
                                              createYesNoList(),
                                              AnswerType.BOOLEAN,
                                              QuestionType.SINGLE,
                                              null,
                                              null,
                                              0));
              folgefragen.add(new Risikofrage("RiskQuestion_VSH2032_SCOM",
                                              "vshSubId0102",
                                              model.getProdukt(),
                                              "Zweite Folgefrage zu Frage 1",
                                              null,
                                              createYesNoList(),
                                              AnswerType.BOOLEAN,
                                              QuestionType.SINGLE,
                                              null,
                                              null,
                                              1));
              risikofrage.setFolgeFragen(folgefragen);
            }
          }
        }
      }

      response.getRisikoFragenResponseModels()
              .add(responseModel);
    }
    response.setStatus(status);
    return response;
  }

  class Keys {
    private GsKeyValue       verkaufsProdukt;
    private List<GsKeyValue> produkte;

    public Keys() {
    }

    public GsKeyValue getVerkaufsProdukt() {
      return verkaufsProdukt;
    }

    public void setVerkaufsProdukt(GsKeyValue verkaufsProdukt) {
      this.verkaufsProdukt = verkaufsProdukt;
    }

    public List<GsKeyValue> getProdukte() {
      return produkte;
    }

    public void setProdukte(List<GsKeyValue> produkte) {
      this.produkte = produkte;
    }

  }

}
