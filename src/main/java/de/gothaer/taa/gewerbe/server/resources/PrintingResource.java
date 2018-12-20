package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsMessage;
import de.gothaer.taa.gewerbe.ui.shared.s2.MessageWidgetIdUtils;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.Antrag;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.Risikofrage;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.VKdaten;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.gebaeude.VersichertesGebaeudeGebaeude;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.inhaltsversicherung.VersichertesGebaeudeInhalt;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.staticEnums.AntragStatus;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaPrintingRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaPrintingResponse;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Path("/printing")
public class PrintingResource {

  @POST
  @Path("/print/{clientSessionId}")
  @Consumes("application/json")
  @Produces("application/json")
  public TaaPrintingResponse print(@Context UriInfo uriInfo,
                                   TaaPrintingRequest pRequest,
                                   @PathParam("clientSessionId") String pClientSessionId) {
    System.out.println("=== Mock Printing ===");
    System.out.println("=== ClientSessionId:" + pClientSessionId);
    System.out.println("=== übergebener Antrag:\n" + pRequest.getAntrag());
    URI uri = uriInfo.getAbsolutePathBuilder()
                     .path("123")
                     .build();

    //    http://127.0.0.1:8080/TAAGewerbeServices/TaaService/printing/print/34DB27F2-3257-46F1-8652-22C0EE8214A0/123
    //    http://127.0.0.1:8080/TAAGewerbeServices/TaaService/printing/print/34DB27F2-3257-46F1-8652-22C0EE8214A0/123
    // http://127.0.0.1:8080/TAAGewerbe/taagewerbe/testpdf.pdf

    System.out.println("=== Unsere Uri: " + uri);
    System.out.println("======================");

    TaaPrintingResponse taaPrintingResponse = new TaaPrintingResponse();
    Status status = new Status();
    taaPrintingResponse.setStatus(status);
    if (!validateRisikoFragen(pRequest.getAntrag(),
                              status)) {
      status.setReturncode(ReturnCode.FACHLICHE_MELDUNGEN);
    } else {
      status.setReturncode(ReturnCode.OK);
    }

    //  taaPrintingResponse.getAveMeldungen().add(new Message(Message.Type.Info, "1", "1", "Test", Message.Visibility.PERMANENT));

    taaPrintingResponse.setVorgangskontextId("http://127.0.0.1:8080/TAAGewerbe/taagewerbe/testpdf.pdf");
    taaPrintingResponse.setVorlaeufigeDeckungszusagePdfId("http://127.0.0.1:8080/TAAGewerbe/taagewerbe/testpdf.pdf");

    VKdaten tVKdaten = new VKdaten();
    tVKdaten.setAntragStatus(AntragStatus.VORSCHLAG_GEDRUCKT_ANTRAG);
    tVKdaten.setAntragMinEinmalGedruckt(true);
    tVKdaten.setBeratungsProtMinEinmalGedruckt(true);
    taaPrintingResponse.setVkdaten(tVKdaten);

    taaPrintingResponse.setAntrag(pRequest.getAntrag());

    return taaPrintingResponse;
  }

  private boolean validateRisikoFragen(Antrag antrag,
                                       Status status) {
    List<GsMessage> messageList = new ArrayList<>();
    // Verkaufsprodukt
    validateRisikofragenVerkaufsProdukt(antrag,
                                        messageList);
    // BHV
    validateRisikofragenBhv(antrag,
                            messageList);
    // Gebaeude
    validateRisikofragenGebaeude(antrag,
                                 messageList);
    // Inhalt
    validateRisikofragenInhalt(antrag,
                               messageList);
    // Private Risiken
    validateRisikofragenPrivateRisiken(antrag,
                                       messageList);
    // ergebnisse auswerten
    if (messageList.size() > 0) {
      for (GsMessage message : messageList) {
        status.add(message);
      }
      return false;
    }
    return true;
  }

  private void validateRisikofragenVerkaufsProdukt(Antrag antrag,
                                                   List<GsMessage> messageList) {
    List<String> messageWidgetIds = new ArrayList<>();
    for (Risikofrage risikofrage : antrag.getRisikofragen()) {
      if (risikofrage.getAnswer() == null) {
        messageWidgetIds.add(MessageWidgetIdUtils.createRisikoFrageMessageId(antrag.getVerkaufsProdukt()
                                                                                   .getKey(),
                                                                             null,
                                                                             risikofrage.getSubId()));
      }
    }
    if (messageWidgetIds.size() > 0) {
      GsMessage message = new GsMessage();
      message.setType(GsMessage.Type.ERROR);
      message.setKey01(antrag.getVerkaufsProdukt()
                             .getKey());
      message.setText("Es muessen alle Risikofragen des Verkaufsprodukt beantwortet sein");
      message.setMessageId("UNBEANTWORTETE_FRAGEN_VERKAUFSPRODUKT");
      message.setVisibility(GsMessage.Visibility.PERMANENT);
      message.setSource(GsMessage.Source.SERVER);
      message.setMessageWidgetIds(messageWidgetIds);
      messageList.add(message);
    }
  }

  private void validateRisikofragenBhv(Antrag antrag,
                                       List<GsMessage> messageList) {
    if (antrag.hasBetriebshaftpflicht()) {
      List<String> messageWidgetIds = new ArrayList<>();
      if (antrag.hasBetriebshaftpflicht()) {
        for (Risikofrage risikofrage : antrag.getBetriebsHaftpflichtVersicherung()
                                             .getRisikofragen()) {
          if (risikofrage.getAnswer() == null) {
            messageWidgetIds.add(MessageWidgetIdUtils.createRisikoFrageMessageId(antrag.getBetriebsHaftpflichtVersicherung()
                                                                                       .getProdukt()
                                                                                       .getKey(),
                                                                                 null,
                                                                                 risikofrage.getSubId()));
          }
        }
        if (messageWidgetIds.size() > 0) {
          GsMessage message = new GsMessage();
          message.setType(GsMessage.Type.ERROR);
          message.setKey01(antrag.getBetriebsHaftpflichtVersicherung()
                                 .getProdukt()
                                 .getKey());
          message.setText("Es muessen alle Risikofragen der Sparte BHV beantwortet sein");
          message.setMessageId("UNBEANTWORTETE_FRAGEN_BHV");
          message.setVisibility(GsMessage.Visibility.PERMANENT);
          message.setSource(GsMessage.Source.SERVER);
          message.setMessageWidgetIds(messageWidgetIds);
          messageList.add(message);
        }
      }
    }
  }

  private void validateRisikofragenGebaeude(Antrag antrag,
                                            List<GsMessage> messageList) {
    if (antrag.hasGebaeudeVersicherung()) {
      List<String> messageWidgetIds = new ArrayList<>();
      for (VersichertesGebaeudeGebaeude versichertesGebaeude : antrag.getGebaeudeVersicherung()
                                                                     .getVersicherteGebaeude()) {
        messageWidgetIds.clear();
        for (Risikofrage risikofrage : versichertesGebaeude.getRisikofragen()) {
          if (risikofrage.getAnswer() == null) {
            messageWidgetIds.add(MessageWidgetIdUtils.createRisikoFrageMessageId(antrag.getGebaeudeVersicherung()
                                                                                       .getProdukt()
                                                                                       .getKey(),
                                                                                 versichertesGebaeude.getIdVersichertesObjekt(),
                                                                                 risikofrage.getSubId()));
          }
        }
        //        if (messageWidgetIds.size() > 0) {
        //          GsMessage message = new GsMessage();
        //          message.setType(GsMessage.Type.ERROR);
        //          message.setKey01(antrag.getGebaeudeVersicherung()
        //                                 .getProdukt()
        //                                 .getKey());
        //          message.setKey02(versichertesGebaeude.getIdVersichertesObjekt());
        //          message.setText("Es muessen alle Risikofragen der eingeschlossenen Bebaeude beantwortet sein: Risikoort -> " +
        //                          antrag.getRisikoort(versichertesGebaeude.getIdVersichertesObjekt())
        //                                .getAnschriftWithLeadingPlz());
        //          message.setMessageId("UNBEANTWORTETE_FRAGEN_GEBAEUDE");
        //          message.setVisibility(GsMessage.Visibility.PERMANENT);
        //          message.setSource(GsMessage.Source.SERVER);
        //          message.setMessageWidgetIds(messageWidgetIds);
        //          messageList.add(message);
        //        }
      }
    }
  }

  private void validateRisikofragenInhalt(Antrag antrag,
                                          List<GsMessage> messageList) {
    if (antrag.hasInhaltsVersicherung()) {
      List<String> messageWidgetIds = new ArrayList<>();
      for (VersichertesGebaeudeInhalt versichertesGebaeude : antrag.getInhaltsVersicherung()
                                                                   .getVersicherteGebaeude()) {
        messageWidgetIds.clear();
        for (Risikofrage risikofrage : versichertesGebaeude.getRisikofragen()) {
          if (risikofrage.getAnswer() == null) {
            messageWidgetIds.add(MessageWidgetIdUtils.createRisikoFrageMessageId(antrag.getInhaltsVersicherung()
                                                                                       .getProdukt()
                                                                                       .getKey(),
                                                                                 versichertesGebaeude.getIdVersichertesObjekt(),
                                                                                 risikofrage.getSubId()));
          }
        }
        if (messageWidgetIds.size() > 0) {
          GsMessage message = new GsMessage();
          message.setType(GsMessage.Type.ERROR);
          message.setKey01(antrag.getInhaltsVersicherung()
                                 .getProdukt()
                                 .getKey());
          message.setKey02(versichertesGebaeude.getIdVersichertesObjekt());
          message.setText("Es muessen alle Risikofragen der eingeschlossenen Inhalt beantwortet sein: Risikoort -> " +
                          antrag.getRisikoort(versichertesGebaeude.getIdVersichertesObjekt())
                                .getAnschriftWithLeadingPlz());
          message.setMessageId("UNBEANTWORTETE_FRAGEN_INHALT");
          message.setVisibility(GsMessage.Visibility.PERMANENT);
          message.setSource(GsMessage.Source.SERVER);
          message.setMessageWidgetIds(messageWidgetIds);
          messageList.add(message);
        }
      }
    }
  }

  private void validateRisikofragenPrivateRisiken(Antrag antrag,
                                                  List<GsMessage> messageList) {
    if (antrag.hasPrivateRisiken()) {
      List<String> messageWidgetIds = new ArrayList<>();
      if (antrag.hasPrivateRisiken()) {
        for (Risikofrage risikofrage : antrag.getPrivateRisiken()
                                             .getRisikofragen()) {
          if (risikofrage.getAnswer() == null) {
            messageWidgetIds.add(MessageWidgetIdUtils.createRisikoFrageMessageId(antrag.getPrivateRisiken()
                                                                                       .getProdukt()
                                                                                       .getKey(),
                                                                                 null,
                                                                                 risikofrage.getSubId()));
          }
        }
        if (messageWidgetIds.size() > 0) {
          GsMessage message = new GsMessage();
          message.setType(GsMessage.Type.ERROR);
          message.setKey01(antrag.getPrivateRisiken()
                                 .getProdukt()
                                 .getKey());
          message.setText("Es muessen alle Risikofragen der Sparte PHV beantwortet sein");
          message.setMessageId("UNBEANTWORTETE_FRAGEN_PHV");
          message.setVisibility(GsMessage.Visibility.PERMANENT);
          message.setSource(GsMessage.Source.SERVER);
          message.setMessageWidgetIds(messageWidgetIds);
          messageList.add(message);
        }
      }
    }
  }

}
