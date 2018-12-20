package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsKeyValue;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.PMAttribut;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.Selbstbehalt;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.TextKonserve;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaSelbstbeteiligungRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.TaaTextkonserveRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaSelbstbeteiligungResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaTextkonserveResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("/Zeichnung")
/**
 * über diesen Service wird der Antrag aus dem Requst mit Klauseln angereichert die im Rahmen des Zeichnungsprozesses
 * durch den Underwriter dem Antrag hinzugefügt werden künnen.
 *
 */ public class ZeichnungResource {

  public ZeichnungResource() {
    super();
  }

  @POST
  @Path("/textkonservenErmitteln")
  @Produces("application/json")
  public TaaTextkonserveResponse textkonservenErmitteln(TaaTextkonserveRequest request) {
    TaaTextkonserveResponse response = new TaaTextkonserveResponse();
    response.getStatus()
            .setReturncode(ReturnCode.OK);

    List<TextKonserve> result = new ArrayList<>();

    // Klausel 1
    TextKonserve textKonserve1 = new TextKonserve();
    textKonserve1.setTextkonserveId(new GsKeyValue("1",
                                                   "Anpasung AVB"));
    PMAttribut field1 = new PMAttribut();
    field1.setType(PMAttribut.Type.NONE);
    field1.setLabel("In der ünderung der AVB gilt eine Summe von ");
    textKonserve1.add(field1);

    field1 = new PMAttribut();
    field1.setType(PMAttribut.Type.NUMBER_FIELD);
    field1.setLabel("Summe");
    textKonserve1.add(field1);

    field1 = new PMAttribut();
    field1.setType(PMAttribut.Type.NONE);
    field1.setLabel(" ü bis zum ");
    textKonserve1.add(field1);

    field1 = new PMAttribut();
    field1.setType(PMAttribut.Type.DATE_FIELD);
    field1.setLabel("Datum");
    textKonserve1.add(field1);

    field1 = new PMAttribut();
    field1.setType(PMAttribut.Type.NONE);
    field1.setLabel(" versichert.");
    textKonserve1.add(field1);

    // Klausel 4
    TextKonserve textKonserve4 = new TextKonserve();
    textKonserve4.setTextkonserveId(new GsKeyValue("4",
                                                   "Ohne Attribute"));
    PMAttribut field4 = new PMAttribut();
    field4.setType(PMAttribut.Type.NONE);
    field4.setLabel("Heute ist ein schüner Tag!");
    textKonserve4.add(field4);

    // Klausel 3
    TextKonserve textKonserve3 = new TextKonserve();
    textKonserve3.setTextkonserveId(new GsKeyValue("3",
                                                   "ComboBox"));
    PMAttribut field3 = new PMAttribut();
    field3.setType(PMAttribut.Type.NONE);
    field3.setLabel("Folgendes Produkt wird eingeschlossen: ");
    textKonserve3.add(field3);

    field3 = new PMAttribut();
    field3.setType(PMAttribut.Type.COMBO_BOX);
    field3.setLabel("Produkt");
    List<GsKeyValue> l = new ArrayList<>();
    l.add(new GsKeyValue("1",
                         "Autoreifen"));
    l.add(new GsKeyValue("2",
                         "Lenkrad"));
    l.add(new GsKeyValue("3",
                         "Hupe"));

    field3.setStore(l);
    textKonserve3.add(field3);

    // Klausel 5
    TextKonserve textKonserve5 = new TextKonserve();
    textKonserve5.setTextkonserveId(new GsKeyValue("5",
                                                   "CheckBox"));
    PMAttribut field5 = new PMAttribut();
    field5.setType(PMAttribut.Type.NONE);
    field5.setLabel("Brauchen Sie Urlaub: ");
    textKonserve5.add(field5);

    field5 = new PMAttribut();
    field5.setType(PMAttribut.Type.CHECKBOX);
    field5.setLabel("Urlaubsreif");
    textKonserve5.add(field5);

    // Klausel 6
    TextKonserve textKonserve6 = new TextKonserve();
    textKonserve6.setTextkonserveId(new GsKeyValue("6",
                                                   "Langer Text"));
    PMAttribut field6 = new PMAttribut();
    field6.setType(PMAttribut.Type.NONE);
    field6.setLabel("Die internationalen Ratingagenturen S&P Global Ratings und Fitch Ratings haben die Rating-Ergebnisse der Versicherungsgesellschaften des Gothaer Konzerns im Oktober 2017 erneut bestütigt. S&P stufte die Gothaer Allgemeine Versicherung AG, die Gothaer Lebensversicherung AG und die Gothaer Krankenversicherung AG - wie in den letzten Jahren - mit A- und einem stabilen Ausblick ein. Fitch bewertete die Gothaer Allgemeine Versicherung AG und die Gothaer Lebensversicherung AG weiterhin mit A und einem stabilen Ausblick. Die starke und widerstandsfühige Kapitalausstattung sowie die stabile Marktposition der Gothaer Versicherungen werden hervorgehoben.");
    textKonserve6.add(field6);

    field6 = new PMAttribut();
    field6.setType(PMAttribut.Type.TEXT_FIELD);
    field6.setLabel("Ihr Text 1");
    textKonserve6.add(field6);
    field6 = new PMAttribut();
    field6.setType(PMAttribut.Type.TEXT_FIELD);
    field6.setLabel("Ihr Text 2");
    textKonserve6.add(field6);
    field6 = new PMAttribut();
    field6.setType(PMAttribut.Type.TEXT_FIELD);
    field6.setLabel("Ihr Text 3");
    textKonserve6.add(field6);
    field6 = new PMAttribut();
    field6.setType(PMAttribut.Type.TEXT_FIELD);
    field6.setLabel("Ihr Text 4");
    textKonserve6.add(field6);
    field6 = new PMAttribut();
    field6.setType(PMAttribut.Type.TEXT_FIELD);
    field6.setLabel("Ihr Text 5");
    textKonserve6.add(field6);
    field6 = new PMAttribut();
    field6.setType(PMAttribut.Type.TEXT_FIELD);
    field6.setLabel("Ihr Text 6");
    textKonserve6.add(field6);

    // Klausel 2
    TextKonserve textKonserve2 = new TextKonserve();
    textKonserve2.setTextkonserveId(new GsKeyValue("2",
                                                   "Test-Klausel"));
    PMAttribut field2 = new PMAttribut();
    field2.setLabel("Ab dem ");
    field2.setId("0");
    field2.setType(PMAttribut.Type.NONE);
    textKonserve2.add(field2);

    field2 = new PMAttribut();
    field2.setType(PMAttribut.Type.DATE_FIELD);
    field2.setLabel("Datum");
    field2.setId("1");
    textKonserve2.add(field2);

    field2 = new PMAttribut();
    field2.setType(PMAttribut.Type.NONE);
    field2.setLabel(" ist die Person '");
    field2.setId("2");
    textKonserve2.add(field2);

    field2 = new PMAttribut();
    field2.setType(PMAttribut.Type.TEXT_FIELD);
    field2.setLabel("Name");
    field2.setId("3");
    textKonserve2.add(field2);

    field2 = new PMAttribut();
    field2.setType(PMAttribut.Type.NONE);
    field2.setLabel("' für ");
    field2.setId("4");
    textKonserve2.add(field2);

    field2 = new PMAttribut();
    field2.setType(PMAttribut.Type.NUMBER_FIELD);
    field2.setLabel("Tage");
    field2.setId("5");
    textKonserve2.add(field2);

    field2 = new PMAttribut();
    field2.setType(PMAttribut.Type.NONE);
    field2.setLabel(" Tage mitversichert.");
    field2.setId("6");
    textKonserve2.add(field2);

    result.add(textKonserve1);
    result.add(textKonserve2);
    result.add(textKonserve3);
    result.add(textKonserve4);
    result.add(textKonserve5);
    result.add(textKonserve6);

    response.setTextKonserve(result);

    return response;
  }

  /**
   * @param request
   * @return
   */
  @POST
  @Path("/selbstbeteiligungErmitteln")
  @Produces("application/json")
  public TaaSelbstbeteiligungResponse selbstbeteiligungErmitteln(TaaSelbstbeteiligungRequest request) {
    TaaSelbstbeteiligungResponse response = new TaaSelbstbeteiligungResponse();
    response.getStatus()
            .setReturncode(ReturnCode.OK);

    Selbstbehalt s = new Selbstbehalt();
    s.setProdukt(request.getProdukt());
    s.setGefahr(request.getGefahr());
    s.setRisikoOrtId(request.getRisikoOrt()
                            .getId());

    PMAttribut pa1 = new PMAttribut();

    s.getDynamischeAttribute()
     .add(pa1);
    ;

    return response;
  }

}
