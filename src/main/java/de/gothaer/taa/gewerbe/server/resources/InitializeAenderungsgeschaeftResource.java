package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsKeyValue;
import de.gothaer.gwt.gxt.config.shared.model.GsMessage;
import de.gothaer.gwt.gxt.config.shared.model.GsMessage.Source;
import de.gothaer.taa.gewerbe.server.model.TestModelFactory;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.Antrag;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.staticEnums.GeVoType;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.ChangeRaveRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.GetAenderungsantragRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.GetTarifgenerationenRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.InitAenderungsgeschaeftRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.ChangeRaveResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.GetAenderungsantragResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.GetTarifgenerationenResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.InitAenderungsgeschaeftResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.models.AnhebbaresProdukt;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.models.PnCGenerationenUpdateInfos;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Path("initializeAenderungsgeschaeft")
public class InitializeAenderungsgeschaeftResource {

  //  private static final Logger LOGGER = Logger.getLogger(InitializeAenderungsgeschaeftResource.class);

  @POST
  @Path("/initialize")
  public InitAenderungsgeschaeftResponse initialize(InitAenderungsgeschaeftRequest request) {
    InitAenderungsgeschaeftResponse response = new InitAenderungsgeschaeftResponse();

    response.setRave("rave_test");

    Status status = new Status();
    status.setReturncode(ReturnCode.OK);
    response.setStatus(status);

    return response;
  }

  @POST
  @Path("/changeRave")
  public ChangeRaveResponse changeRave(ChangeRaveRequest request) {
    ChangeRaveResponse response = new ChangeRaveResponse();

    Status status = new Status();

    if ("4711".equals(request.getRave())) {
      status.setReturncode(ReturnCode.FACHLICHE_MELDUNGEN);
      GsMessage tMessage = new GsMessage();
      tMessage.setSource(Source.SERVER);
      tMessage.setText("Ungültige RaVe");
      status.add(tMessage);
    } else {
      status.setReturncode(ReturnCode.OK);
    }
    response.setStatus(status);

    return response;
  }

  @POST
  @Path("/getTarifgenerationen")
  public GetTarifgenerationenResponse getTarifgenerationen(GetTarifgenerationenRequest request) {

    GetTarifgenerationenResponse response = new GetTarifgenerationenResponse();

    List<AnhebbaresProdukt> anhebbareProdukte = new ArrayList<>();

    // BHV
    AnhebbaresProdukt anhebbaresProduktBhv = new AnhebbaresProdukt();

    PnCGenerationenUpdateInfos pnCGenerationenUpdateInfosBhv = new PnCGenerationenUpdateInfos();
    pnCGenerationenUpdateInfosBhv.setGenerationsName("Generations Y");

    List<PnCGenerationenUpdateInfos> pnCGenerationenUpdateInfosBhvList = new ArrayList<>();
    pnCGenerationenUpdateInfosBhvList.add(pnCGenerationenUpdateInfosBhv);
    anhebbaresProduktBhv.setPnCGenerationenUpdateInfos(pnCGenerationenUpdateInfosBhvList);

    anhebbaresProduktBhv.setProduktId(new GsKeyValue("PR_BHV",
                                                     "Betriebshaftpflicht"));
    // Inhalt
    AnhebbaresProdukt anhebbaresProduktInhalt = new AnhebbaresProdukt();
    PnCGenerationenUpdateInfos pnCGenerationenUpdateInfosInhalt = new PnCGenerationenUpdateInfos();
    pnCGenerationenUpdateInfosInhalt.setGenerationsName("Generations Y");
    PnCGenerationenUpdateInfos pnCGenerationenUpdateInfosInhalt2 = new PnCGenerationenUpdateInfos();
    pnCGenerationenUpdateInfosInhalt2.setGenerationsName("Generations X");

    List<PnCGenerationenUpdateInfos> pnCGenerationenUpdateInfosInhaltList = new ArrayList<>();
    pnCGenerationenUpdateInfosInhaltList.add(pnCGenerationenUpdateInfosInhalt);
    pnCGenerationenUpdateInfosInhaltList.add(pnCGenerationenUpdateInfosInhalt2);
    anhebbaresProduktInhalt.setPnCGenerationenUpdateInfos(pnCGenerationenUpdateInfosInhaltList);

    anhebbaresProduktInhalt.setProduktId(new GsKeyValue("PRGR_INH",
                                                        "Inhaltsversicherung"));
    // Gebaeude
    AnhebbaresProdukt anhebbaresProduktGebaeude = new AnhebbaresProdukt();
    anhebbaresProduktGebaeude.setProduktId(new GsKeyValue("PRGR_GEB",
                                                          "Gebäude"));

    anhebbareProdukte.add(anhebbaresProduktBhv);
    anhebbareProdukte.add(anhebbaresProduktInhalt);
    anhebbareProdukte.add(anhebbaresProduktGebaeude);

    response.setAnhebbareProdukte(anhebbareProdukte);

    return response;
  }

  @POST
  @Path("/getAenderunsgantrag")
  public GetAenderungsantragResponse getAenderunsgantrag(GetAenderungsantragRequest request) {
    GetAenderungsantragResponse response = new GetAenderungsantragResponse();

    //		Antrag tBestandsvertrag ;
    //		if (pRequest.getAntrag().getVsnr() != null && pRequest.getAntrag().getVsnr().equals("01010123456")) {
    //			tBestandsvertrag = TestModelFactory.createVSHAntrag();
    //		} else {
    Antrag tBestandsvertrag = TestModelFactory.createBHVAntragEinWagnis(false);
    //		}

    //		tBestandsvertrag.setVsnr(pRequest.getAntrag().getVsnr());
    //		tBestandsvertrag.setAntragsnummer(pRequest.getAntrag().getAntragsnummer());
    tBestandsvertrag.setGeVoType(GeVoType.AENDERUNGS_ANTRAG);
    tBestandsvertrag.setBeitragJaehrlich(new BigDecimal(1200));
    tBestandsvertrag.setBeitragMonatlich(new BigDecimal(100));
    tBestandsvertrag.setBeitragHalbJaehrlich(new BigDecimal(600));
    tBestandsvertrag.setBeitragViertelJaehrlich(new BigDecimal(300));
    tBestandsvertrag.setZahlweise(new GsKeyValue("2",
                                                 "Jährlich"));
    response.setBestandsvertrag(tBestandsvertrag);
    // addRueckwirkendeAenderungen(tBestandsvertrag);

    // vorbereiteten Antrag zurück an den Client geben
    response.setAntrag(tBestandsvertrag);

    Status status = new Status();
    status.setReturncode(ReturnCode.OK);
    response.setStatus(status);

    return response;
  }

}
