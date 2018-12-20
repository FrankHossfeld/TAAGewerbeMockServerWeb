package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsKeyValue;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.Kammer;
import de.gothaer.taa.gewerbe.ui.shared.s2.model.produkte.TAA;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.request.GetKammernRequest;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.GetKammernResponse;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaVersicherungsunternehmenResponse;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/versicherungsunternehmen")
public class VersicherungsunternehmenResource {

  @Path("/getVersicherungsunternehmen/{pVuNummer}")
  @Produces("application/json")
  public TaaVersicherungsunternehmenResponse getVersicherungsunternehmen(@PathParam("pVuNummer") String pVuNummer) {
    TaaVersicherungsunternehmenResponse tVersicherungsunternehmenResponse = leseVU(pVuNummer);
    return tVersicherungsunternehmenResponse;
  }

  @GET
  @Path("/getVersicherungsunternehmen")
  @Produces("application/json")
  public TaaVersicherungsunternehmenResponse getVersicherungsunternehmenOhneParameter() {
    TaaVersicherungsunternehmenResponse tVersicherungsunternehmenResponse = leseVU(null);
    return tVersicherungsunternehmenResponse;
  }

  private TaaVersicherungsunternehmenResponse leseVU(String pVuNummer) {
    TaaVersicherungsunternehmenResponse tVersicherungsunternehmenResponse = new TaaVersicherungsunternehmenResponse();
    List<GsKeyValue> versicherungsunternehmenList = new ArrayList<>();
    tVersicherungsunternehmenResponse.setVersicherungsunternehmen(versicherungsunternehmenList);

    GsKeyValue tVu = new GsKeyValue("5002",
                                    "Gerling Konzern");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5004",
                         "Schmitt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5007",
                         "Hoffmann GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5008",
                         "FVG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5011",
                         "Gottfried Schultz GmbH & Co.");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5012",
                         "D.A.S.Versicherungs-AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5013",
                         "Auto-Service");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5014",
                         "Gothaer VersicherungenSchadenbüro d.VVaG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5015",
                         "Freiwillige Feuerwehr");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5016",
                         "Garanta Versicherungs-AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5017",
                         "Schindler");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5018",
                         "Zulassungstelle");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5024",
                         "Landesgirokasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5025",
                         "Patria Versicherung AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5026",
                         "Koch GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5027",
                         "Herrmann");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5028",
                         "Landpolizei");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5029",
                         "Koch GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5030",
                         "Albrecht");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5032",
                         "Sparkasse Detmold");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5033",
                         "Generali Versicherungs-AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5035",
                         "Sixt AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5036",
                         "Feuerwehr");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5037",
                         "Deutsche Postgewerkschaft");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5038",
                         "Winter");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5039",
                         "Zimmermann GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5040",
                         "Telenorma");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5042",
                         "Bayerische Hypotheken- und Wechsel-Bank AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5043",
                         "Landeshauptkasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5044",
                         "Zulassungsstelle für KFZ");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5045",
                         "Sparkasse Bielefeld");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5047",
                         "Niederl. Oesterreich");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5048",
                         "Dekra AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5050",
                         "Winkler");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5051",
                         "Haus + Grund Bremen GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5053",
                         "Ambiente");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5054",
                         "Staedt.Krankenhaus");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5055",
                         "Meier");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5056",
                         "Landesarbeitsamt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5057",
                         "Landschaftliche BrandkasseHannover");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5058",
                         "Jet-Tankstelle");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5061",
                         "Koch GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5063",
                         "Schumacher GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5064",
                         "Staatshochbauamt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5065",
                         "Schaefer GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5066",
                         "Hypo Bank");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5067",
                         "Deutsche Bundespost Telekom");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5068",
                         "Innungskrankenkasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5069",
                         "Hessisches Strassenbauamt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5070",
                         "Schindler");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5072",
                         "Zimmermann");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5073",
                         "Bayerische Vereinsbank");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5074",
                         "Autohaus Wagner GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5077",
                         "Elektro Mueller");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5078",
                         "Interrent Autovermietung");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5079",
                         "Fischer GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5080",
                         "Grosse-Druck GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5082",
                         "Kkb Bank");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5083",
                         "Roth");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5084",
                         "EuropcarAutovermietung GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5085",
                         "Engel");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5086",
                         "Vereinte Versicherung AGDirektion Frankfurt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5088",
                         "Wohnungsbaugesellschaft");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5089",
                         "Kkb Bank");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5090",
                         "BEK Barmer Ersatzkasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5091",
                         "Stadt Duisburg");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5092",
                         "Autoverleih Buchbinder");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5093",
                         "Vereinte Versicherungen");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5094",
                         "Friedrich GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5095",
                         "Caritasverband e.V.");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5096",
                         "Kaufmünnische Krankenkasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5097",
                         "Citibank AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5098",
                         "Berger");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5099",
                         "Berufsbildungszentrum");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5115",
                         "DEVKSach- u.HUK-VVaG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5116",
                         "KKH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5117",
                         "Autohaus Meyer GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5118",
                         "Kaiser");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5119",
                         "Klein GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5120",
                         "Carglass GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5121",
                         "Bundeswehrkasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5122",
                         "Ford Bank AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5125",
                         "Strassenverkehrsamt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5126",
                         "Grund-u.Hauptschule");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5130",
                         "Autohaus Schmidt GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5138",
                         "Bay. Hypotheken-u.WechselbankAG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5141",
                         "Kolpinghaus");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5142",
                         "Schmidt & Bethge GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5151",
                         "Bayerische Vereinsbank AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5152",
                         "Schaefer GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5160",
                         "WürttembergischeVersicherung AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5163",
                         "Jaspers Industrie Assekuranz");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5165",
                         "Sparkasse Aachen");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5173",
                         "Autohaus Meyer GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5251",
                         "Zulassungstelle");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5264",
                         "Diakonisches Werk");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5310",
                         "TüV Rheinland");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5311",
                         "Bayrische Versicherungskammer");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5312",
                         "Dresdner Bank");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5316",
                         "Raab Karcher GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5317",
                         "Bundesknappschaft");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5318",
                         "Norddeutsche LandesbankGirozentrale");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5319",
                         "ALBINGIAVersicherungs-AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5324",
                         "Kreissparkasse Verden");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5325",
                         "Hausverw. Michelmann GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5326",
                         "Oberjustizkasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5328",
                         "Kreissparkasse Pinneberg");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5334",
                         "Colonia Versicherung");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5338",
                         "Schaefer GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5339",
                         "Autohaus Lang");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5340",
                         "Philips GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5342",
                         "Bundeskasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5343",
                         "Mercedes Benz AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5344",
                         "Herrmann");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5346",
                         "Deutsche Bahn AGGeschäftsbereich Netz");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5347",
                         "Taunus-Sparkasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5348",
                         "Kinderklinik");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5350",
                         "Kreispolizeibehoerde");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5356",
                         "Wohnbau GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5357",
                         "Hypo-Bank");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5362",
                         "Tierärztliche Klinik");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5364",
                         "Postbeamtenkrankenkasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5365",
                         "Deutsche Apotheker UndAerztebank");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5366",
                         "Kreiskasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5368",
                         "KKH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5370",
                         "Evangelisches Pfarramt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5372",
                         "Bosch");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5374",
                         "Kfz.-Zulassungsstelle");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5375",
                         "Berufsschule");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5377",
                         "Kinderklinik");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5384",
                         "DEKRA AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5390",
                         "Deutscher Kinderschutzbund eV");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5393",
                         "Oskar Schunck KG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5396",
                         "Eigent.Gem.");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5397",
                         "Woolworth");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5399",
                         "Koehler");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5400",
                         "DEVK Allgem. Versicherungs-AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5401",
                         "Hausverw. Michelmann GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5402",
                         "Bayerische Vereinsbank AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5403",
                         "Landschaftliche BrandkasseHannover");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5404",
                         "Polizeipraesidium");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5405",
                         "HL-Markt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5412",
                         "Massa Techno GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5413",
                         "Citibank");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5414",
                         "Horten Hauptverwaltung");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5419",
                         "Landeskrankenhaus");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5420",
                         "Polizeistation");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5423",
                         "Auto Bauer");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5424",
                         "Horn");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5426",
                         "HBS Voss GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5430",
                         "Einwohnermeldeamt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5432",
                         "Zulassungsstelle fuerKraftfahrzeuge");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5438",
                         "AVG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5441",
                         "Schulte");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5445",
                         "Schwabengarage AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5446",
                         "Gerichtszahlstelle");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5448",
                         "Buchholz");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5451",
                         "Raab Karcher GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5452",
                         "Bfg Bank AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5453",
                         "DVAG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5455",
                         "FinanzamtErbschaftssteuerstelle");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5456",
                         "Dresdner Bank AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5459",
                         "Bosch GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5461",
                         "Lindner GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5462",
                         "Winterthur Versicherungen");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5463",
                         "DEA-Tankstelle");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5464",
                         "Horten Hauptverwaltung");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5468",
                         "Arbeiter-Samariter-Bund");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5469",
                         "Autohaus Weiss");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5470",
                         "Schuster GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5472",
                         "Quelle Kundendienst");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5473",
                         "Manfred Schmidt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5476",
                         "Innungskrankenkasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5479",
                         "Autohaus Kraus");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5480",
                         "Hausverw. Michelmann GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5483",
                         "BKK");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5484",
                         "interRent");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5485",
                         "Orthopaedische Klinik");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5488",
                         "Versorgungsamt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5491",
                         "Kraftfahrzeugzulassungsstelle");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5492",
                         "Baumann GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5496",
                         "Creditreform");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5497",
                         "Barmer Ersatzkasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5498",
                         "Bundesanstalt THW");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5499",
                         "Autohaus Werner");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5501",
                         "Gemeindekasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5505",
                         "Deutsche Bank AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5508",
                         "Meier");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5509",
                         "Schumann");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5511",
                         "Realschule");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5512",
                         "Bosch");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5513",
                         "Müller GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5515",
                         "Helmut Schmidt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5516",
                         "Berger");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5518",
                         "interRent");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5519",
                         "WEG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5521",
                         "ASB");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5522",
                         "Straßenmeisterei");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5523",
                         "ARAG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5525",
                         "Huber GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5528",
                         "Katholische Kirchengemeinde");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5529",
                         "Schröder");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5530",
                         "Poliklinik");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5534",
                         "Stein GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5539",
                         "Adam Opel AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5541",
                         "Bürgermeisteramt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5544",
                         "Wohnungsbau GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5545",
                         "Gothaer Lebensvers.a.G.");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5546",
                         "Schneider GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5547",
                         "Staatliches Forstamt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5549",
                         "Kölner H.-u.G.-Verein GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5551",
                         "Vereinte Versicherung AGDirektion Aachen");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5552",
                         "Auto Schmid");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5556",
                         "Hanseatic Bank");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5557",
                         "InterRent Autovermietung");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5559",
                         "Wasserwirtschaftsamt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5560",
                         "Elisabeth-Krankenhaus");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5561",
                         "Ortsgemeinde");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5562",
                         "Vereins- und Westbank AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5565",
                         "Haustechnik GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5567",
                         "Edeka-Markt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5569",
                         "Autohaus Meyer GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5570",
                         "Raule GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5573",
                         "Landeshauptkasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5578",
                         "Eigent.Gem.");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5579",
                         "WEG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5581",
                         "Hermann");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5582",
                         "Oberpostkasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5583",
                         "Autohaus Koch");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5585",
                         "Bezirkskrankenhaus");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5586",
                         "Kölner H.-u.G.-Verein GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5589",
                         "Evangelisches Pfarramt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5590",
                         "GEK");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5591",
                         "Agrippina Versicherung AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5592",
                         "Diehl GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5593",
                         "Autohaus Schulz");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5595",
                         "Katholische Kirchenstiftung");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5596",
                         "Fa. Schneider");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5601",
                         "DRK Krankenhaus");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5609",
                         "Bayerische Hypotheken- und Wechsel-Bank AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5613",
                         "DAK");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5616",
                         "Auto Staiger GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5625",
                         "Philipp Holzmann AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5626",
                         "DEA Tankstelle");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5631",
                         "Gothaer Versicherungen");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5632",
                         "Citibank AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5633",
                         "Oberjustizkasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5636",
                         "Danzas GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5656",
                         "FVG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5662",
                         "Sixt Autovermietung");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5671",
                         "Postbank");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5673",
                         "Autohaus Meyer GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5680",
                         "Deutsche Lufthansa AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5686",
                         "Helvetia SchweizerischeVersicherungsgesellschaft");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5687",
                         "Autohaus Lehmann");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5690",
                         "Bender");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5698",
                         "Landespolizei");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5716",
                         "Interrent Autovermietung");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5728",
                         "Peters GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5751",
                         "Erste Allgemeine");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5752",
                         "Avis");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5754",
                         "Hoch- und Tiefbau GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5756",
                         "Wohnungsbau GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5765",
                         "Gemeinschaftshauptschule");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5767",
                         "Gemeindekasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5771",
                         "ARAG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5773",
                         "Staatskasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5780",
                         "Adler Versicherungen");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5781",
                         "Ott");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5782",
                         "Autohaus Neumann");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5783",
                         "Deutsche Versicherungs AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5785",
                         "Werner GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5786",
                         "Shell-Station");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5787",
                         "WürttembergischeVersicherung AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5788",
                         "Aachener und MünchenerVersicherung AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5789",
                         "Deutsche Postgewerkschaft");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5790",
                         "Rhenus-Weichelt AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5791",
                         "Bezirkskrankenhaus");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5792",
                         "Gothaer Lebensvers.a.G.");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5798",
                         "Gemeinde");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5799",
                         "Schule");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5800",
                         "Universitaetsklinik");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5801",
                         "Niederl. Oesterreich");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5802",
                         "Gesamtschule");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5803",
                         "Ott");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5805",
                         "Amt fuer Verteidigungslasten");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5806",
                         "Wohnbau GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5807",
                         "Fachhochschule");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5809",
                         "Autohaus Klein");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5812",
                         "Allbank");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5813",
                         "Ortsgemeinde");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5815",
                         "Rudolph");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5818",
                         "VUP Baden-Wuerttemberg GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5823",
                         "Autobahnpolizeistation");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5825",
                         "Stadt Neustadt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5826",
                         "Ernst GmbH");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5827",
                         "Straßenbauamt");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5828",
                         "Frankfurter Allianz");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5829",
                         "Vereinte Versicherung AGDirektion München");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5831",
                         "Helmut Mueller");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5834",
                         "Polizeiwache");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5836",
                         "Bayerische Hypotheken- und Wechselbank AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5838",
                         "Renz");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5852",
                         "Autohaus Mueller");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5854",
                         "Kaufmaennische Krankenkasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5856",
                         "Horn");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5858",
                         "Deutsche Bahn AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5859",
                         "Bay. Hypotheken-u.Wechselbank");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5861",
                         "Vereins-u.Westbank AG");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5862",
                         "Weber");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5900",
                         "AOKGesundheitskasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5901",
                         "Verbandsgemeindekasse");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("5902",
                         "Brinkmann");
    versicherungsunternehmenList.add(tVu);
    tVu = new GsKeyValue("9595",
                         "Unbekannt");
    versicherungsunternehmenList.add(tVu);

    tVersicherungsunternehmenResponse.getStatus()
                                     .setReturncode(ReturnCode.OK);
    return tVersicherungsunternehmenResponse;
  }

  @POST
  @Path("/getKammern")
  @Consumes("application/json")
  @Produces("application/json")
  public GetKammernResponse getKammern(GetKammernRequest request) {

    GetKammernResponse tResponse = new GetKammernResponse();

    if (TAA.VSH_TAETIGKEIT_ANWALT.equals(request.getTaetigkeit()
                                                .getKey())) {
      tResponse.getKammern()
               .add(new Kammer("pid1",
                               "adid1",
                               "Anwaltskammer 1"));
      tResponse.getKammern()
               .add(new Kammer("pid2",
                               "adid2",
                               "Anwaltskammer 2"));
      tResponse.getKammern()
               .add(new Kammer("pid3",
                               "adid3",
                               "Anwaltskammer 3"));
    } else if (TAA.VSH_TAETIGKEIT_STEUERBERATER.equals(request.getTaetigkeit()
                                                              .getKey())) {
      tResponse.getKammern()
               .add(new Kammer("pid1",
                               "adid1",
                               "Steuerberaterkammer 1"));
      tResponse.getKammern()
               .add(new Kammer("pid2",
                               "adid2",
                               "Steuerberaterkammer 2"));
      tResponse.getKammern()
               .add(new Kammer("pid3",
                               "adid3",
                               "Steuerberaterkammer 3"));
    } else if (TAA.VSH_TAETIGKEIT_STEUERBEVOLLMAECHTIGTER.equals(request.getTaetigkeit()
                                                                        .getKey())) {
      tResponse.getKammern()
               .add(new Kammer("pid1",
                               "adid1",
                               "Steuerbevollmächtigterkammer 1"));
      tResponse.getKammern()
               .add(new Kammer("pid2",
                               "adid2",
                               "Steuerbevollmächtigterkammer 2"));
      tResponse.getKammern()
               .add(new Kammer("pid3",
                               "adid3",
                               "Steuerbevollmächtigterkammer 3"));
    } else if (TAA.VSH_TAETIGKEIT_WEG_BEIRAT.equals(request.getTaetigkeit()
                                                           .getKey())) {
      tResponse.getKammern()
               .add(new Kammer("pid1",
                               "adid1",
                               "Beiratkammer 1"));
      tResponse.getKammern()
               .add(new Kammer("pid2",
                               "adid2",
                               "Beiratkammer 2"));
      tResponse.getKammern()
               .add(new Kammer("pid3",
                               "adid3",
                               "Beiratkammer 3"));
    }
    tResponse.getStatus()
             .setReturncode(ReturnCode.OK);

    return tResponse;
  }

}
