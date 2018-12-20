package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsKeyValue;
import de.gothaer.gwt.gxt.config.shared.model.GsMessage;
import de.gothaer.gwt.gxt.config.shared.model.config.GsFieldConfiguration;
import de.gothaer.taa.gewerbe.config.shared.model.IDConstants;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaVerkaufsProduktListeRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaVerkaufsProduktListeResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/verkaufsProdukt")
public class VerkaufsProduktResource {

  @POST
  @Path("/getVerkaufsProduktList")
  @Produces("application/json")
  public TaaVerkaufsProduktListeResponse getVerkaufsProduktList(TaaVerkaufsProduktListeRequest pRequest) {
    return createResponse(pRequest);
  }

  private TaaVerkaufsProduktListeResponse createResponse(TaaVerkaufsProduktListeRequest request) {
    TaaVerkaufsProduktListeResponse response = new TaaVerkaufsProduktListeResponse();
    Status status = new Status();
    if ("1234567890".equals(request.getRave())) {
      status.setReturncode(ReturnCode.TECHNISCHER_FEHLER);
      status.setMeldungTechnisch("Ungültige Rave .... Finger wech .... ",
                                 null);
    } else {
      try {
        Map<String, GsFieldConfiguration> list = new HashMap<>();
        if ("0000004711".equals(request.getRave())) {
          status.add(new GsMessage(GsMessage.Type.ERROR,
                                   "0000004711",
                                   "Die Rave nix gut",
                                   "RAVE",
                                   GsMessage.Visibility.ONCE,
                                   GsMessage.Source.SERVER,
                                   new ArrayList<String>()));
        } else {
          GsFieldConfiguration productConfiguration = getVerkaufsProdukte();
          list.put(productConfiguration.getId(),
                   productConfiguration);
          status.setReturncode(ReturnCode.OK);
        }
        response.setVerkaufsProduktList(list);
      } catch (Exception pException) {
        status.setReturncode(ReturnCode.TECHNISCHER_FEHLER);
        status.setMeldungTechnisch("Technischer Fehler beim Lesen der Verkaufsproduktliste!" + pException.getMessage(),
                                   null);
      }
    }
    response.setStatus(status);
    return response;
  }

  private GsFieldConfiguration getVerkaufsProdukte() {
    GsFieldConfiguration configuration = new GsFieldConfiguration();
    configuration.setId(IDConstants.VERKAUFSPRODUKT_ID);
    List<GsKeyValue> listOfProducts = new ArrayList<>();
    listOfProducts.add(new GsKeyValue("VKP_GEW_PROT",
                                      "Gothaer GewerbeProtect"));
    listOfProducts.add(new GsKeyValue("VKP_VSH",
                                      "Gothaer GewerbeProtect VSH"));
    listOfProducts.add(new GsKeyValue("VKP_GU",
                                      "Gothaer GewerbeProtect Gruppenunfall"));
    configuration.setKeyValueList(listOfProducts);
    return configuration;
  }

}
