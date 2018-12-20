package de.gothaer.taa.gewerbe.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

//import de.gothaer.gwt.taa.pnc.shared.model.AttributeDescription;
//import de.gothaer.taa.gewerbe.server.models.PlainStructAttributeDescription;



public class Utils {

  public static String getXmlString(String path) {
    String content = null;
    URL url = Utils.class.getResource(path);
    File file = new File(url.getPath());
    try {
      try (FileReader reader = new FileReader(file)) {
        char[] chars = new char[(int) file.length()];
        reader.read(chars);
        content = new String(chars);
      }
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return content;
  }

  //  public static AttributeDescription getAttributeDescription(PlainStructAttributeDescription source,
  //                                                             AttributeDescription.SourceType sourceType) {
  //    AttributeDescription attribute = new AttributeDescription();
  //
  //    attribute.setChangeable(source.isChangeable());
  //    attribute.setConstraint(source.isConstraint());
  //    attribute.setDataType(source.getDataType());
  //    attribute.setDefaultValue(source.getDefaultValue());
  //    attribute.setLabel(source.getLabel());
  //    attribute.setNullable(source.isNullable());
  //    attribute.setPmName(source.getPmName());
  //    attribute.setSource(sourceType);
  //    attribute.setTechnicalName(source.getTechnicalName());
  //    attribute.setTypeClass(source.getTypeClass());
  //    attribute.setValue(source.getValue());
  //    attribute.setVisible(source.isVisible());
  //
  //    for (String validValues : source.getValidValues()) {
  //      attribute.getValidValues().add(validValues);
  //    }
  //
  //    return attribute;
  //  }

}
