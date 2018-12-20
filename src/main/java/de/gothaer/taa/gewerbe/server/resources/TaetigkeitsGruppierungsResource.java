package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsKeyValue;
import de.gothaer.taa.gewerbe.ui.shared.s2.GUID;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.PMAttribut;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.Taetigkeiten;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.alle.TaetigkeitenUndVsu;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.bhv.BhvWagnis;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaTaetigkeitsGruppierungsRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaTaetigkeitsGruppierungResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaTaetigkeitsGruppierungsTVResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * Mock für das Gruppieren der Taetigkeiten.
 * Die Implementierte Logik ist:
 * - werden 2 Taetigkeiten übergeben, werden Haupt- und Nebenwagnis getrennt
 * - in allen anderen Füllen bleiben die Taetigkeiten zusammen in einem Wagni
 */
@Path("/taetigkeitsGruppierungsService")
public class TaetigkeitsGruppierungsResource {

  @POST
  @Path("/getTaetigkeitsGruppenBhv")
  @Produces("application/json")
  public TaaTaetigkeitsGruppierungResponse getTaetigkeitsGruppenBhv(TaaTaetigkeitsGruppierungsRequest request) {

    TaaTaetigkeitsGruppierungResponse response = new TaaTaetigkeitsGruppierungResponse();
    List<BhvWagnis> wagnisse = new ArrayList<>();

    if (request.getTaetigkeiten()
               .getNebenTaetigkeiten() != null &&
        request.getTaetigkeiten()
               .getNebenTaetigkeiten()
               .size() > 1) {
      BhvWagnis wagnis01 = new BhvWagnis();
      wagnis01.setBbrGruppe("4711");
      Taetigkeiten tTaetigkeiten = new Taetigkeiten();
      tTaetigkeiten.setHauptTaetigkeit(request.getTaetigkeiten()
                                              .getHauptTaetigkeit());
      tTaetigkeiten.getAlleTaetigkeiten()
                   .add(request.getTaetigkeiten()
                               .getHauptTaetigkeit());
      wagnis01.setTaetigkeiten(tTaetigkeiten);
      wagnis01.setBbrVertraeglich(true);
      wagnis01.setWkz("4711");
      wagnis01.getDynamischeAttribute()
              .add(new PMAttribut(GUID.get(),
                                  "Dynamisches Attribute 0101 (Checkboy)",
                                  PMAttribut.Type.CHECKBOX));
      wagnis01.getDynamischeAttribute()
              .add(new PMAttribut(GUID.get(),
                                  "Dynamisches Attribute 0102 (TextField)",
                                  PMAttribut.Type.TEXT_FIELD));
      PMAttribut dynamischesAttribute03 = new PMAttribut(GUID.get(),
                                                         "Dynamisches Attribute 0103 (NumberField",
                                                         PMAttribut.Type.NUMBER_FIELD);
      dynamischesAttribute03.setMinvalue(1);
      dynamischesAttribute03.setMaxvalue(10);
      wagnis01.getDynamischeAttribute()
              .add(dynamischesAttribute03);
      PMAttribut dynamischesAttribute04 = new PMAttribut(GUID.get(),
                                                         "Dynamisches Attribute 0104 ComboBox",
                                                         PMAttribut.Type.COMBO_BOX);
      dynamischesAttribute04.getStore()
                            .add(new GsKeyValue("Bli",
                                                "Bli"));
      dynamischesAttribute04.getStore()
                            .add(new GsKeyValue("Bla",
                                                "Bla"));
      dynamischesAttribute04.getStore()
                            .add(new GsKeyValue("Blub",
                                                "Blub"));
      wagnis01.getDynamischeAttribute()
              .add(dynamischesAttribute04);
      wagnisse.add(wagnis01);

      BhvWagnis wagnis02 = new BhvWagnis();
      wagnis02.setBbrGruppe("4712");
      tTaetigkeiten = new Taetigkeiten();
      tTaetigkeiten.setHauptTaetigkeit(request.getTaetigkeiten()
                                              .getHauptTaetigkeit());
      tTaetigkeiten.getAlleTaetigkeiten()
                   .add(request.getTaetigkeiten()
                               .getNebenTaetigkeiten()
                               .get(0));
      wagnis02.setTaetigkeiten(tTaetigkeiten);
      wagnis02.setBbrVertraeglich(true);
      wagnis02.setWkz("4711");
      wagnis02.getDynamischeAttribute()
              .add(new PMAttribut(GUID.get(),
                                  "Dynamisches Attribute 0201",
                                  PMAttribut.Type.TEXT_FIELD));
      wagnisse.add(wagnis02);

      BhvWagnis wagnis03 = new BhvWagnis();
      wagnis03.setBbrGruppe("4713");
      tTaetigkeiten = new Taetigkeiten();
      tTaetigkeiten.setHauptTaetigkeit(request.getTaetigkeiten()
                                              .getHauptTaetigkeit());
      tTaetigkeiten.getAlleTaetigkeiten()
                   .add(request.getTaetigkeiten()
                               .getNebenTaetigkeiten()
                               .get(0));
      wagnis03.setTaetigkeiten(tTaetigkeiten);
      wagnis03.setBbrVertraeglich(true);
      wagnis03.getDynamischeAttribute()
              .add(new PMAttribut(GUID.get(),
                                  "Dynamisches Attribute 0301",
                                  PMAttribut.Type.TEXT_FIELD));
      wagnis03.getDynamischeAttribute()
              .add(new PMAttribut(GUID.get(),
                                  "Dynamisches Attribute 0302",
                                  PMAttribut.Type.TEXT_FIELD));
      wagnisse.add(wagnis03);
    } else {
      BhvWagnis tBhvWagnis = new BhvWagnis();
      tBhvWagnis.setBbrGruppe("4711");
      tBhvWagnis.setTaetigkeiten(request.getTaetigkeiten());
      wagnisse.add(tBhvWagnis);
    }

    response.setBhvWagnisse(wagnisse);
    response.getStatus()
            .setReturncode(ReturnCode.OK);
    return response;
  }

  @POST
  @Path("/getTaetigkeitsGruppenElektronik")
  @Produces("application/json")
  public TaaTaetigkeitsGruppierungsTVResponse getTaetigkeitsGruppenElektronik(TaaTaetigkeitsGruppierungsRequest pRequest) {

    TaaTaetigkeitsGruppierungsTVResponse tResponse = new TaaTaetigkeitsGruppierungsTVResponse();
    List<TaetigkeitenUndVsu> tElektronikgruppen = new ArrayList<>();

    for (GsKeyValue tStichwort : pRequest.getTaetigkeiten()
                                         .getAlleTaetigkeiten()) {
      TaetigkeitenUndVsu tElektronikgruppe = new TaetigkeitenUndVsu();
      tElektronikgruppe.setTaetigkeitGruppe(tStichwort.getKey() + "-" + tStichwort.getValue());
      Taetigkeiten tTaetigkeiten = new Taetigkeiten();
      tTaetigkeiten.setHauptTaetigkeit(tStichwort);
      tTaetigkeiten.getAlleTaetigkeiten()
                   .add(tStichwort);
      tElektronikgruppe.setTaetigkeiten(tTaetigkeiten);
      tElektronikgruppen.add(tElektronikgruppe);
    }

    tResponse.setGruppen(tElektronikgruppen);
    tResponse.getStatus()
             .setReturncode(ReturnCode.OK);
    return tResponse;
  }

  @POST
  @Path("/getTaetigkeitsGruppenFahrbar")
  @Produces("application/json")
  public TaaTaetigkeitsGruppierungsTVResponse getTaetigkeitsGruppenFahrbar(TaaTaetigkeitsGruppierungsRequest pRequest) {

    TaaTaetigkeitsGruppierungsTVResponse tResponse = new TaaTaetigkeitsGruppierungsTVResponse();
    List<TaetigkeitenUndVsu> tTaetigkeitenVsuList = new ArrayList<>();

    int tGruppe = 7;
    GsKeyValue tHauptTaet = null;
    for (GsKeyValue tStichwort : pRequest.getTaetigkeiten()
                                         .getAlleTaetigkeiten()) {
      TaetigkeitenUndVsu tTaetigkeitenVsu = new TaetigkeitenUndVsu();
      tTaetigkeitenVsu.setTaetigkeitGruppe("" + tGruppe++);
      Taetigkeiten tTaetigkeiten = new Taetigkeiten();
      if (tHauptTaet == null) {
        tHauptTaet = tStichwort;
      } else {
      }
      tTaetigkeiten.setHauptTaetigkeit(tHauptTaet);
      tTaetigkeiten.getAlleTaetigkeiten()
                   .add(tStichwort);
      tTaetigkeitenVsu.setTaetigkeiten(tTaetigkeiten);
      tTaetigkeitenVsuList.add(tTaetigkeitenVsu);
    }

    tResponse.setGruppen(tTaetigkeitenVsuList);
    tResponse.getStatus()
             .setReturncode(ReturnCode.OK);
    return tResponse;
  }

  @POST
  @Path("/getTaetigkeitsGruppenMaschinen")
  @Produces("application/json")
  public TaaTaetigkeitsGruppierungsTVResponse getTaetigkeitsGruppenMaschinen(TaaTaetigkeitsGruppierungsRequest pRequest) {

    TaaTaetigkeitsGruppierungsTVResponse tResponse = new TaaTaetigkeitsGruppierungsTVResponse();
    List<TaetigkeitenUndVsu> tTaetigkeitenVsuList = new ArrayList<>();

    int tGruppe = 6;
    for (GsKeyValue tStichwort : pRequest.getTaetigkeiten()
                                         .getAlleTaetigkeiten()) {
      TaetigkeitenUndVsu tTaetigkeitenVsu = new TaetigkeitenUndVsu();
      tTaetigkeitenVsu.setTaetigkeitGruppe(tStichwort.getKey() + "-" + tStichwort.getValue());
      Taetigkeiten tTaetigkeiten = new Taetigkeiten();
      tTaetigkeiten.setHauptTaetigkeit(tStichwort);
      tTaetigkeiten.getAlleTaetigkeiten()
                   .add(tStichwort);
      tTaetigkeitenVsu.setTaetigkeiten(tTaetigkeiten);
      tTaetigkeitenVsuList.add(tTaetigkeitenVsu);
    }

    tResponse.setGruppen(tTaetigkeitenVsuList);
    tResponse.getStatus()
             .setReturncode(ReturnCode.OK);
    return tResponse;
  }

}
