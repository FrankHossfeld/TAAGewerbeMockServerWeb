package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.gwt.gxt.config.shared.model.GsToolTip;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.ReturnCode;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.Status;
import de.gothaer.taa.gewerbe.ui.shared.s2.transport.response.TaaToolTipResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("/tooltips")
public class ToolTipResource {

  @POST
  @Path("/getTooltipList")
  @Produces("application/json")
  public TaaToolTipResponse getTooltipList() {
    List<GsToolTip> tooltTipList = new ArrayList<>();

    tooltTipList.add(new GsToolTip("allgSubId1",
                                   "RisikoFrage 01",
                                   "Ja, das ist tatsächlich eine Risikofrage ...",
                                   null));
    tooltTipList.add(new GsToolTip("RiskQuest_SpecialProfessions_GCOM",
                                   "Böse Berufe",
                                   "Programmierer, IT Spezialist und Versicherungskaufmann",
                                   null));
    tooltTipList.add(new GsToolTip("Elp_GAI_DirectClaim_ExtCov_GCOM",
                                   "Deckungserweiterung",
                                   "GU Deckungserweiterung auf Produkt-Ebene",
                                   null));
    tooltTipList.add(new GsToolTip("Elp_GAI_ExtPayHospital_ExtCov_GCOM",
                                   "Deckungserweiterung",
                                   "GU Deckungserweiterung auf LB-Ebene",
                                   null));
    tooltTipList.add(new GsToolTip("Cov_GAIConvalescence_GCOM",
                                   "Leistungsart",
                                   "GU Leistungsart auf LB-Ebene",
                                   null));
    tooltTipList.add(new GsToolTip("GUMaxAnzahlPersonen",
                                   "Maximale Anzahl VN",
                                   "Die maximale Anzahl versicherbarer Personen beträgt im Neugeschäft '100', im Änderungsgeschäft '120'",
                                   null));
    tooltTipList.add(new GsToolTip("GUGefahrengruppe",
                                   "Gefahrengruppe",
                                   "Bitte die Gefahrengruppe auswählen",
                                   null));
    tooltTipList.add(new GsToolTip("GUAnzahlPersonen",
                                   "Anzahl VN",
                                   "Bitte die Anzahl der zu versichernden Presonen angeben",
                                   null));
    tooltTipList.add(new GsToolTip("GUDeckungsumfang",
                                   "Deckungsumfang",
                                   "Bitte den Deckungsumfang angeben",
                                   null));
    tooltTipList.add(new GsToolTip("GUTaetigkeit",
                                   "Taetigkeit",
                                   "Bitte die Tätigkeit auswählen",
                                   null));
    tooltTipList.add(new GsToolTip("bhvSubId1",
                                   "RisikoFrage BHV 01",
                                   "Ja, das ist tatsächlich eine Risikofrage für das Produkt Betriebshafpflicht ... Yeah, Yeah, yeah ... :-)",
                                   null));
    tooltTipList.add(new GsToolTip("bhvSubId2",
                                   "RisikoFrage BHV 02",
                                   "Ja, das ist tatsächlich noch eine Risikofrage für das Produkt Betriebshafpflicht ... Yeah, Yeah, yeah ... :-)",
                                   null));

    tooltTipList.add(new GsToolTip("TIPOFTHEDAYTAA",
                                   "Hallo",
                                   "Wünsche einen angenehmen Tag",
                                   "media/images/serveimage.jpg"));

    tooltTipList.add(new GsToolTip("TIPGENERATIONSWECHSEL",
                                   "Hinweis",
        //                                   "Mehr zur man. Generationswechsel finden Sie <a href=\"http://www.google.de\" target=\"_blank\">hier</a>.",
                                   "<b>Weitergehende Informationen zum Generationswechsel finden Sie in der GGP Wissensdatenbank: </b><a href=\"http://www.aonet.gothaer.de/content/gosmart/de/sad/sad_detailseite_5253.html\" target=\"_blank\" >hier</a>.",
                                   ""));

    tooltTipList.add(new GsToolTip("TIPOFTHEDAYTR",
                                   "Hallo",
                                   "Wünsche einen angenehmen Tag",
                                   "media/images/serveimage.jpg"));

    tooltTipList.add(new GsToolTip("TIPNOTIZEN",
                                   "Notizen",
                                   "Hier können Sie Notizen eingeben. Diese bleiben bis zum Versenden des Antrages erhalten. Sie können dieses Fenster offen halten und auch verschieben während Sie im Hintergrund weiter arbeiten",
                                   ""));
    // Später zu verwenden ...
    tooltTipList.add(new GsToolTip("AuswahlSachElementar",
                                   "Sach Elementar",
                                   "Der Einschluss der Elementarschadendeckung ist nur zu Risikoorten möglich, die nicht in der Gefährdungsklasse 4 liegen.",
                                   null));
    tooltTipList.add(new GsToolTip("InhaltVSU",
                                   "Versicherungssumme",
                                   "Die Gesamt-Versicherungssumme über alle Risikoorte zusammen darf nicht größer als 250.000 Euro sein.",
                                   null));
    tooltTipList.add(new GsToolTip("InhaltSummenanpassung",
                                   "Summenanpassung",
                                   "Zur Vermeidung einer Unterversicherung kann vereinbart werden, dass die Versicherungssumme zur Hauptfälligkeit entsprechend der Änderung des vom statistischen Bundesamt ermittelten Index ‚Erzeugerpreise gewerblicher Produkte‘ angepasst wird.",
                                   null));
    tooltTipList.add(new GsToolTip("InhaltKBU",
                                   "KBU",
                                   "Die Mitversicherung von Schäden durch Betriebsunterbrechung (Klein-BU) infolge eines unter die Versicherung fallenden Sachschadens erfolgt in einer Versicherungssumme über alle Risikoorte. Entspricht die beantragte Versicherungssumme der Summe aller Inhaltswert wird Unterversicherungsver-zicht gewährt.",
                                   null));
    tooltTipList.add(new GsToolTip("AuswahlGebaeudeElementar",
                                   "Gebäude Elementar",
                                   "Der Einschluss der Elementarschadendeckung ist nur zu Risikoorten möglich, die nicht in der Gefährdungsklasse 4 liegen.",
                                   null));
    tooltTipList.add(new GsToolTip("GebaeudeVSU",
                                   "Versicherungssumme",
                                   "Die Gesamt-Versicherungssumme über alle Risikoorte zusammen darf nicht größer als 1 Mio. Euro sein.",
                                   null));
    tooltTipList.add(new GsToolTip("PHVDeckungssummen",
                                   "Deckungssummen",
                                   "Die Deckungssumme gilt …",
                                   null));
    tooltTipList.add(new GsToolTip("MaschinenZUERS",
                                   "ZÜRS",
                                   "Die Mitversicherung von Elementarschäden in den ZÜRS-Gefährdungsklassen 0 und N wird von Ihrer zuständigen Vertriebseinheit geprüft. Bitte prozessieren Sie den Antrag zu Ende und leiten den Vorgang dann an Ihre zuständige Vertriebsstelle weiter.\n" +
                                   "Risiken in der ZÜRS-Gefährdungsklasse 4 sind über GewerbeProtect leider nicht versicherbar.",
                                   null));
    tooltTipList.add(new GsToolTip("MaschinenStationaerVSU",
                                   "Versicherungssumme",
                                   "Die Gesamtversicherungssumme für die Maschinenversicherung stationär über alle Risikoorte und Tätigkeiten zusammen darf nicht größer als 250.000 Euro sein.",
                                   null));
    tooltTipList.add(new GsToolTip("PhotovoltaikZUERS",
                                   "ZÜRS",
                                   "Die Mitversicherung von Elementarschäden in den ZÜRS-Gefährdungsklassen 0 und N wird von Ihrer zuständigen Vertriebseinheit geprüft. Bitte prozessieren Sie den Antrag zu Ende und leiten den Vorgang dann an Ihre zuständige Vertriebsstelle weiter.\n" +
                                   "Risiken in der ZÜRS-Gefährdungsklasse 4 sind über GewerbeProtect leider nicht versicherbar.",
                                   null));
    tooltTipList.add(new GsToolTip("PhotovoltaikVSUGrunddeckung",
                                   "Versicherungssumme Grunddeckung",
                                   "Versichert sind alle Sachen, die unmittelbar zum Funktioniereneiner Photovoltaikanlage erforderlich sind, u.a. Solarmodule, Montagerahmen, Befestigungselemente, Mess-, Steuer- und Regeltechnik, Wechselrichter, Ver-kabelung.\n" +
                                   "Die Versicherungssumme ist zu bilden aus dem kompletten Wert der Anlage (heutiger Neuwert / Listenpreis) zuzüglich der Bezugskosten (z.B. für Verpa-ckung, Fracht, Montage, Zölle).",
                                   null));
    tooltTipList.add(new GsToolTip("PhotovoltaikLeistung",
                                   "Leistung",
                                   "Hier ist die Gesamtsumme der installierten Leistung in kWp anzugeben.",
                                   null));
    tooltTipList.add(new GsToolTip("PhotovoltaikVSUKBU",
                                   "Versicherungssumme KBU",
                                   "Versichert sind die entgangenen Einspeisevergütungen nach einem versicherten Sachschaden.",
                                   null));
    //    tooltTipList.add(new GsToolTip("WerkverkehrVSU",
    //                                   "Versicherungssumme",
    //                                   "Welche Positionen sind bei der Ermittlung der VSU zu berücksichtigen?",
    //                                   null));
    //    tooltTipList.add(new GsToolTip("Werkverkehr Ladungsmaximum",
    //                                   "Ladungsmaximum",
    //                                   "???",
    //                                   null));
    tooltTipList.add(new GsToolTip("BetriebsschliessungVSU",
                                   "Versicherungssumme",
                                   "Die Gesamt-Versicherungssumme über alle Risikoorte zusammen darf nicht größer als 1 Mio. Euro sein.",
                                   null));
    tooltTipList.add(new GsToolTip("AbweichendeSB",
                                   "Abweichende SB",
                                   "Zu den Produkten Elementar, Unbenannte Gefahren und Erweiterte Deckung sind spezielle Selbstbeteiligungen in den AVB definiert. Für diese Produkte führt die Auswahl der Selbstbeteiligung nicht zu einer Beitragsänderung.",
                                   null));

    //Aktuell verwendet
    tooltTipList.add(new GsToolTip("Versicherungsbeginn",
                                   "Versicherungsbeginn",
                                   "Der Versicherungsbeginn darf maximal 90 Tage in der Vergangenheit oder ein Jahr in der Zukunft liegen.",
                                   null));
    tooltTipList.add(new GsToolTip("Versicherungsablauf",
                                   "Versicherungsablauf",
                                   "Die Vertragslaufzeit muss mindestens eins Jahr betragen und muss geringer als vier Jahre sein.",
                                   null));
    tooltTipList.add(new GsToolTip("Fremdaktenkennzeichen",
                                   "Fremdaktenkennzeichen",
                                   "Zur Identifikation kann ein zusätzliches Kennzeichen für diesen Vorgang eingegeben werden.",
                                   "media/images/loading.gif"));
    tooltTipList.add(new GsToolTip("Rahmenvereinbarung",
                                   "Rahmenvereinbarung",
                                   "Liegt für die ausgewählten Produkte eine Rahmenvereinbarung vor, so kann diese für die Tarifierung verwendet werden.",
                                   null));
    tooltTipList.add(new GsToolTip("Haupttaetigkeit",
                                   "Haupttätigkeit",
                                   "Die Haupttätigkeit entspricht der führenden Tätigkeit des zu versichernden Gewerbebetriebes, z.B. der Tätigkeit mit dem höchsten Anteil am Gesamtumsatz" +
                                   "<ul><li> Verkaufspreis für verkaufte lieferungsfertige eigene Erzeugnisse </li><li> Verkaufspreis für lieferungsfertige eigene Erzeugnisse </li><li> Verkaufspreis bei Großhandelsbetrieben</li></ul>",
                                   null));
    tooltTipList.add(new GsToolTip("BHVUmsatz",
                                   "Umsatz",
                                   "Werden mehrere Tätigkeiten unter einer Wagniskennziffer versichert, so ist der Umsatz für diese Tätigkeiten in einer Summe zu erfassen. Der Gesamt-Umsatz über alle Tätigkeiten zusammen darf nicht größer als 2 Mio. Euro sein.",
                                   null));
    tooltTipList.add(new GsToolTip("ElektronikZUERS",
                                   "ZÜRS",
                                   "Die Deckungssumme gilt …",
                                   null));
    tooltTipList.add(new GsToolTip("ElektronikVSU",
                                   "Versicherungssumme",
                                   "Die Gesamtversicherungssumme für die Elektronikversicherung über alle Risikoorte und Tätigkeiten zusammen darf nicht größer als 250.000 Euro sein.",
                                   null));
    tooltTipList.add(new GsToolTip("BHVAnzahlGeschaeftsfueher",
                                   "Anzahl Geschäftsführer",
                                   "Die Anzahl der Geschaeftsfuehrer",
                                   null));
    tooltTipList.add(new GsToolTip("BHVAnzahlHunde",
                                   "Anzahl Hunde",
                                   "Die Anzahl der Hunde",
                                   null));
    tooltTipList.add(new GsToolTip("BHVAnzahlPerfde",
                                   "Anzahl Pferde",
                                   "Die Anzahl der Pferde",
                                   null));
    tooltTipList.add(new GsToolTip("BetriebsschliessungRisikoOrt",
                                   "Risikoort",
                                   "Das hier ist eine Risikoortadresse",
                                   null));
    tooltTipList.add(new GsToolTip("BetriebsschliessungRisikoOrtVSU",
                                   "Versicherungssumme",
                                   "Für diesen Risikoort dem ihm seine VSU halt.",
                                   null));

    tooltTipList.add(new GsToolTip("AuswahlElementar",
                                   "AuswahlElementar",
                                   "AuswahlElementar Text",
                                   null));

    tooltTipList.add(new GsToolTip("AuswahlGebauedeUnbenannteGefahr",
                                   "AuswahlUnbenannteGefahr",
                                   "AuswahlUnbenannteGefahr Text",
                                   null));

    tooltTipList.add(new GsToolTip("AuswahlGebaeudeErwDeckung",
                                   "AuswahlErwDeckung",
                                   "AuswahlErwDeckung Text",
                                   null));

    tooltTipList.add(new GsToolTip("GebaeudeRisikoOrt",
                                   "Risikoort",
                                   "Das hier ist eine Gebäude Risikoortadresse",
                                   null));
    tooltTipList.add(new GsToolTip("GebaeudeRisikoOrtZUERS",
                                   "ZUERS",
                                   "Die Mitversicherung von Elementarschäden in den ZÜRS-Gefährdungsklassen 0 und N wird von Ihrer zuständigen Vertriebseinheit geprüft. Bitte prozessieren Sie den Antrag zu Ende und leiten den Vorgang dann an Ihre zuständige Vertriebsstelle weiter.\n" +
                                   "Risiken in der ZÜRS-Gefährdungsklasse 4 sind über GewerbeProtect leider nicht versicherbar.",
                                   null));
    tooltTipList.add(new GsToolTip("InhaltRisikoOrt",
                                   "Risikoort",
                                   "Das hier ist eine Inhalt Risikoortadresse",
                                   null));
    tooltTipList.add(new GsToolTip("InhaltRisikoOrtZUERS",
                                   "ZUERS",
                                   "Die Mitversicherung von Elementarschäden in den ZÜRS-Gefährdungsklassen 0 und N wird von Ihrer zuständigen Vertriebseinheit geprüft. Bitte prozessieren Sie den Antrag zu Ende und leiten den Vorgang dann an Ihre zuständige Vertriebsstelle weiter.\n" +
                                   "Risiken in der ZÜRS-Gefährdungsklasse 4 sind über GewerbeProtect leider nicht versicherbar.",
                                   null));

    tooltTipList.add(new GsToolTip("GebaeudeSummeBeantragt",
                                   "Beantragte Summe",
                                   "Ihre Summe",
                                   null));
    tooltTipList.add(new GsToolTip("GebaeudeSummeBerechnet",
                                   "Berechnete Summe",
                                   "Ihre berechnete Summe",
                                   null));
    tooltTipList.add(new GsToolTip("GebaeudeWertermittlung",
                                   "Wertermittlung",
                                   "Ermitteln Sie den Wert.",
                                   null));
    tooltTipList.add(new GsToolTip("GebaeudeWertgutachtenUpload",
                                   "Dateiupload",
                                   "Lirum larum Löffelstiel.",
                                   null));
    tooltTipList.add(new GsToolTip("GebaeudeWertgutachtenInfo",
                                   "Info",
                                   "Lirum larum Löffelstiel.",
                                   null));
    tooltTipList.add(new GsToolTip("Unterversicherungsverzicht",
                                   "Verzicht",
                                   "Lirum larum Löffelstiel.",
                                   null));
    tooltTipList.add(new GsToolTip("GebaeudeVSUGutachten",
                                   "vsu gut",
                                   "Lirum larum Löffelstiel.",
                                   null));
    tooltTipList.add(new GsToolTip("MaschinenFahrbarVSU",
                                   "vsu",
                                   "Lirum larum Löffelstiel.",
                                   null));
    tooltTipList.add(new GsToolTip("MaschinenFahrbarTaetigkeitVSU",
                                   "vsu taet",
                                   "Lirum larum Löffelstiel.",
                                   null));
    tooltTipList.add(new GsToolTip("MaschinenStationaerVSU",
                                   "vsu",
                                   "Lirum larum Löffelstiel.",
                                   null));
    tooltTipList.add(new GsToolTip("MaschinenStationaerTaetigkeitVSU",
                                   "vsu taet",
                                   "Lirum larum Löffelstiel.",
                                   null));
    tooltTipList.add(new GsToolTip("MaschinenZUERS",
                                   "Zuers",
                                   "Lirum larum Löffelstiel.",
                                   null));
    tooltTipList.add(new GsToolTip("MaschinenStationaerKBU",
                                   "KBU",
                                   "Lirum larum Löffelstiel.",
                                   null));
    tooltTipList.add(new GsToolTip("MaschinenStationaerRisikoOrtZUERS",
                                   "Zuers risiko",
                                   "Lirum larum Löffelstiel.",
                                   null));
    tooltTipList.add(new GsToolTip("MaschinenStationaerRisikoOrt",
                                   "riskoort",
                                   "Lirum larum Löffelstiel.",
                                   null));
    tooltTipList.add(new GsToolTip("WerkverkehrVSU",
                                   "VSU",
                                   "Lirum larum Löffelstiel.",
                                   null));
    tooltTipList.add(new GsToolTip("WerkverkehrGefuehrteGueter",
                                   "Güter",
                                   "Lirum larum Löffelstiel.",
                                   null));
    tooltTipList.add(new GsToolTip("WerkverkehrLadungsmaximum",
                                   "Ladung",
                                   "Lirum larum Löffelstiel.",
                                   null));
    //    tooltTipList.add(new GsToolTip("inhaltRisikoOrte",
    //                                   "Versicherungssummen",
    //                                   "Die Gesamt-Versicherungssumme über alle Risikoorte zusammen darf nicht größer als 1 Mio. Euro sein.",
    //                                   null));
    //    tooltTipList.add(new GsToolTip("elektronikVsuMaschinen",
    //                                   "Versicherungssummen",
    //                                   "Hersteller: Siemens, Hercules, BMW oder Intel",
    //                                   null));
    //    tooltTipList.add(new GsToolTip("elektronikVsuPrototyp",
    //                                   "Versicherungssummen",
    //                                   "Zu den Prototypen zählen u.a. auch aus Lego selbst gebaute Modelle.",
    //                                   null));
    tooltTipList.add(new GsToolTip("ElektronikKBU",
                                   "Einschluss Mehrkosten",
                                   "???",
                                   null));

    //                                  BHV_PLUS_BAUSTEIN
    tooltTipList.add(new GsToolTip("PR_BHV_plusbaustein_tooltip",
                                   "PR_BHV_plusbaustein_tooltip Text",
                                   "Lirum larum Löffelstiel.",
                                   null));
    tooltTipList.add(new GsToolTip("PR_BHV_plusbaustein_tooltip1",
                                   "PR_BHV_plusbaustein_tooltip Text",
                                   "Lirum larum Löffelstiel. - Text 1",
                                   null));
    tooltTipList.add(new GsToolTip("PR_BHV_plusbaustein_tooltip2",
                                   "PR_BHV_plusbaustein_tooltip Text",
                                   "Lirum larum Löffelstiel. - Text 2",
                                   null));
    tooltTipList.add(new GsToolTip("PR_BHV_plusbaustein_tooltip3",
                                   "PR_BHV_plusbaustein_tooltip Text",
                                   "Lirum larum Löffelstiel. - Text 3",
                                   null));
    tooltTipList.add(new GsToolTip("PR_BHV_plusbaustein_tooltip4",
                                   "PR_BHV_plusbaustein_tooltip Text",
                                   "Lirum larum Löffelstiel. - Text 4",
                                   null));
    tooltTipList.add(new GsToolTip("PR_BHV_Deckungssummen",
                                   "PR_BHV_Deckungssummen Text",
                                   "Lirum larum Löffelstiel. - Deckungssummen haben wir hier ...,",
                                   null));
    tooltTipList.add(new GsToolTip("PR_BHV_Selbstbeteiligung",
                                   "PR_BHV_Selbstbeteiligung Text",
                                   "Lirum larum Löffelstiel. - Selbstbeteiligung schön und gut",
                                   null));
    tooltTipList.add(new GsToolTip("Deckungssummen",
                                   "Deckungssummen Text",
                                   "Lirum larum Löffelstiel.",
                                   null));
    tooltTipList.add(new GsToolTip("Selbstbeteiligung",
                                   "Selbstbeteiligung Text",
                                   "Lirum larum Löffelstiel.",
                                   null));

    // TODO Nicht verwendet!!! Für die einzelnen Produkte kann es (oben) noch spez. ZUERS geben!
    tooltTipList.add(new GsToolTip("Lastschrift",
                                   "Lastschrift",
                                   "Der Beitragseinzug per Lastschrift ist komfortabel für den Kunden und jederzeit möglich.",
                                   null));
    //    // TODO Nicht verwendet!!!
    //    tooltTipList.add(new GsToolTip("TT009",
    //                                   "Versicherungssumme gleitender Neuwert",
    //                                   "Sofern für das Risiko eine Wertermittlung vorgenommen wird um den Unterversicherungsverzicht zu erlangen lassen Sie dieses Feld bitte leer. Die Versicherungssumme wird in dem Fall nach erfolgreicher Wertermittlung automatisch in dieses Feld übernommen.",
    //                                   null));
    //    // TODO Nicht verwendet!!!
    //    tooltTipList.add(new GsToolTip("TT010",
    //                                   "Versicherungssumme Neuwert",
    //                                   "Bei einer Versicherung zum Neuwert kann kein Unterversicherungsverzicht gewährt werden.",
    //                                   null));

    //    tooltTipList.add(new GsToolTip("vertragsUnterlagen",
    //                                   "Vertragsunterlagen",
    //                                   "Wurden dem Kunden alle relevanten Vertragsunterlagen (Kundeninformationen, Produktinformationsblätter, Allgemeine Versicherungsbedingungen) ausgehändigt? Das entsprechende Dokument ist über den Link abrufbar.",
    //                                   null));
    // tooltTipList.add(new GsToolTip("TT012",
    // "Haupttaetigkeit",
    // "Die Haupttätigkeit entspricht der führenden Tätigkeit des zu versichernden Gewerbebetriebes, z.B. der Tätigkeit mit dem höchsten Anteil am Gesamtumsatz",
    // null));
    tooltTipList.add(new GsToolTip("InhaltVSU",
                                   "ToDo",
                                   "Platzhalter. Im FFK nicht definiert.",
                                   null));
    tooltTipList.add(new GsToolTip("ErhoehteVssuKbu",
                                   "ToDo",
                                   "Platzhalter. Im FFK nicht definiert.",
                                   null));
    tooltTipList.add(new GsToolTip("ErhoehteVssuKbuVariabel",
                                   "ToDo",
                                   "Platzhalter. Im FFK nicht definiert.",
                                   null));
    //    tooltTipList.add(new GsToolTip("BHV_PLUS_BAUSTEIN",
    //                                   "Plusbaustein BHV",
    //                                   "Spannender Text:<br>jetzt geht's los!<br><ul><li>JuppiDuppiDuuuuuuuhhhhhh ....</li><li>Erweiterte Produkthaftpflichtversicherung für Handel, Gewerbe, Handwerk (nicht gültig für Betriebe des Bauhauptgewerbes, für Bauhandwerker sowie produzierende Betriebe)</li></ul> ",
    //                                   null));
    tooltTipList.add(new GsToolTip("PR_VSH_plusbaustein_tooltip",
                                   "Plusbaustein VSH",
                                   "Tooltip für VSH Plusbaustein",
                                   null));
    tooltTipList.add(new GsToolTip("VSHNamentlicheNennungenRechtsanwalt",
                                   "VSH Namentliche Nennungen Rechtsanwalt",
                                   "Tooltip für VSH Namentliche Nennungen Rechtsanwalt",
                                   null));
    tooltTipList.add(new GsToolTip("VSHNamentlicheNennungenSteuerberater",
                                   "VSH Namentliche Nennungen Steuerberater",
                                   "Tooltip für VSH Namentliche Nennungen Steuerberater",
                                   null));
    tooltTipList.add(new GsToolTip("VshUmsatzImmobilienverwalterPflicht",
                                   "VSH Immobilieneverwalter Pflicht",
                                   "Tooltip für VSH VSH Immobilieneverwalter Pflicht",
                                   null));
    tooltTipList.add(new GsToolTip("PR_VSH_IMMOBILIENVERWALTER_DECKUNGSSUMME_PFLICHT",
                                   "Deckungssumme Immobilienverwalter Pflichtdeckung",
                                   "Tooltip für die Deckungssumme Immobilienverwalter Pflichtdeckung",
                                   null));

    TaaToolTipResponse response = new TaaToolTipResponse();
    response.setToolTips(tooltTipList);

    Status status = new Status();
    status.setReturncode(ReturnCode.OK);
    response.setStatus(status);
    return response;
  }

}
