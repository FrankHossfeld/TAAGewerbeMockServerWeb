package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.taa.gewerbe.ui.shared.s2.model.Vermittler;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.UserInformationen;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.UserInformationen.FreigabeRole;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.UserInformationen.SecurityRole;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaUserInfoResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Mockimplementierung für den User-Info Service. Gibt einfach immer die Rolle SAD zurück
 *
 * @author t0g
 */
@Path("/userInfo")
public class UserInfoResource {

  @GET
  @Path("/getUserInfos")
  @Produces("application/json")
  public TaaUserInfoResponse getUserInfos() {
    TaaUserInfoResponse tUserInfoResponse = new TaaUserInfoResponse();
    UserInformationen tUserInformationen = new UserInformationen();
    tUserInformationen.getRollen()
                      .add(SecurityRole.SAD);
    tUserInformationen.getAllisnrList()
                      .add(new Vermittler("1099801"));
    tUserInformationen.getAllisnrList()
                      .add(new Vermittler("1099802"));
    tUserInformationen.getAllisnrList()
                      .add(new Vermittler("1099803"));
    tUserInfoResponse.setUserInfos(tUserInformationen);

    tUserInfoResponse.getStatus()
                     .setReturncode(ReturnCode.OK);

    // Alles was wir haben vergeben .. :-)
    // tUserInformationen.getFreigabeRollen()
    // .add(FreigabeRole.VD_FREIGABE);
    tUserInformationen.getFreigabeRollen()
                      .add(FreigabeRole.VERSENDEN);
    tUserInformationen.getFreigabeRollen()
                      .add(FreigabeRole.VE_FREIGABE_GGP);
    tUserInformationen.getFreigabeRollen()
                      .add(FreigabeRole.VE_FREIGABE_VSH);
    tUserInformationen.getFreigabeRollen()
                      .add(FreigabeRole.DIREKTABSCHLUSS);

    return tUserInfoResponse;
  }

  //  @GET
  //  @Path("/getShowDeckungszusage")
  //  @Produces("application/json")
  //  public BooleanResponse getShowDeckungszusage() {
  //
  //    BooleanResponse response = new BooleanResponse();
  //    response.setResult(Boolean.TRUE);
  //
  //    Status status = new Status();
  //    response.setStatus(status);
  //    return response;
  //  }

}
