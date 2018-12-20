package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.taa.gewerbe.ui.shared.s2.model.Absenderprofil;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.GetProfilListResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/absenderprofil")
public class AbsenderprofilResource {

  @GET
  @Path("/getProfilList")
  public GetProfilListResponse getProfilList() {
    GetProfilListResponse tResponse = new GetProfilListResponse();

    Status tStatus = new Status();
    tStatus.setReturncode(ReturnCode.OK);
    tResponse.setStatus(tStatus);

    tResponse.getAbsenderprofile()
             .add(new Absenderprofil("id1",
                                     "Absenderprofil1",
                                     false));
    tResponse.getAbsenderprofile()
             .add(new Absenderprofil("id2",
                                     "Absenderprofil2",
                                     true));
    tResponse.getAbsenderprofile()
             .add(new Absenderprofil("id3",
                                     "Absenderprofil3",
                                     false));

    //		try {
    //			Thread.sleep(6000); // 6 sekunden warten
    //		} catch (InterruptedException e) {
    //		}

    return tResponse;
  }

}
