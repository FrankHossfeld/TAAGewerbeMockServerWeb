package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.TAA;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaInitApplicationResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaKeytableResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaToolTipResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/application")
public class InitApplicationResource {

  @GET
  @Path("/initApplication")
  public TaaInitApplicationResponse initApplication() {

    TaaInitApplicationResponse response = new TaaInitApplicationResponse();
    KeytableResource keyTableResource = new KeytableResource();
    TaaKeytableResponse tKeyTableResponse = keyTableResource.getKeyValues();

    ToolTipResource toolTipResource = new ToolTipResource();
    TaaToolTipResponse tTooltipResponse = toolTipResource.getTooltipList();

    response.setKeytableResponse(tKeyTableResponse);
    response.setToolTipResponse(tTooltipResponse);

    response.getStatus()
            .setReturncode(ReturnCode.OK);

    response.setClientSyncId(TAA.CLIENT_SYNC_ID);

    return response;

  }

}
