package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsKeyValue;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.TAA;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaTaetigkeitListRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaTaetigkeitListResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("/taetigkeiten")
public class TaetigkeitenResource {

  @POST
  @Path("/getTaetigkeitenList")
  @Produces("application/json")
  public TaaTaetigkeitListResponse getTaetigkeitenList(TaaTaetigkeitListRequest request) {

    TaaTaetigkeitListResponse response = new TaaTaetigkeitListResponse();
    Status status = new Status();

    List<GsKeyValue> taetigkeiten = new ArrayList<>();
    if (request.getVerkaufsProdukt()
               .getKey()
               .contains("VKP_GEW_PROT")) {
      taetigkeiten.add(new GsKeyValue("1069",
                                      "Buchhandel"));
      taetigkeiten.add(new GsKeyValue("1165",
                                      "Catering"));
      taetigkeiten.add(new GsKeyValue("124",
                                      "Altenheim"));
      taetigkeiten.add(new GsKeyValue("1276",
                                      "Contactlinsenstudio"));
      taetigkeiten.add(new GsKeyValue("1591",
                                      "Elektroinstallationsbetrieb"));
      taetigkeiten.add(new GsKeyValue("169",
                                      "Ambulanter Pflegedienst"));
      taetigkeiten.add(new GsKeyValue("211",
                                      "Änderungsschneiderei"));
      taetigkeiten.add(new GsKeyValue("2599",
                                      "Gießerei"));
      taetigkeiten.add(new GsKeyValue("2896",
                                      "Hebamme (mit Geburtshilfe)"));
      taetigkeiten.add(new GsKeyValue("3076",
                                      "Hörgerätakustiker"));
      taetigkeiten.add(new GsKeyValue("4711",
                                      "Metzger"));
      taetigkeiten.add(new GsKeyValue("4712",
                                      "Bäckerei"));
      taetigkeiten.add(new GsKeyValue("3088",
                                      "Hotel (mit Restaurant) für die Besucher der Star Wars und Lego Convention in der schönsten Stadt der Welt (Alaaf, Alaaf, Alaaf) - Und das Hotel ist super klasse ..."));
      taetigkeiten.add(new GsKeyValue("3538",
                                      "Kfz-Handel"));
      taetigkeiten.add(new GsKeyValue("4018",
                                      "Küferei"));
      taetigkeiten.add(new GsKeyValue("43",
                                      "Airbagherstellung"));
      taetigkeiten.add(new GsKeyValue("4780",
                                      "Möbeltischlerei"));
      taetigkeiten.add(new GsKeyValue("484",
                                      "Bäckereibedarfshandel"));
      taetigkeiten.add(new GsKeyValue("5674",
                                      "Rechtsanwalt"));
      taetigkeiten.add(new GsKeyValue("7000",
                                      "Tiefbaubetrieb"));
      taetigkeiten.add(new GsKeyValue("907",
                                      "Blumenhandel (Einzelhandel)"));
    } else if (request.getVerkaufsProdukt()
                      .getKey()
                      .contains("VKP_VSH")) {
      taetigkeiten.add(new GsKeyValue(TAA.VSH_TAETIGKEIT_ANWALT,
                                      "Anwalt"));
      taetigkeiten.add(new GsKeyValue(TAA.VSH_TAETIGKEIT_STEUERBERATER,
                                      "Steuerberater"));
      taetigkeiten.add(new GsKeyValue(TAA.VSH_TAETIGKEIT_STEUERBEVOLLMAECHTIGTER,
                                      "Steuerbevollmüchtigter"));
      taetigkeiten.add(new GsKeyValue(TAA.VSH_TAETIGKEIT_WEG_BEIRAT,
                                      "WEG Beirat"));
      taetigkeiten.add(new GsKeyValue(TAA.VSH_TAETIGKEIT_UNTERNEHMENSBERATER,
                                      "Unternehmensberater"));
    }
    response.setTaetigkeiten(taetigkeiten);

    status.setReturncode(ReturnCode.OK);
    response.setStatus(status);
    return response;
  }

}
