package de.gothaer.taa.gewerbe.server.servlet;

import com.google.common.io.BaseEncoding;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class FileUploadServlet
    extends HttpServlet
    implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response) {
    doPost(request,
           response);
  }

  private void createReturnMess(HttpServletResponse response)
      throws IOException {
    String datei = ("Datei" +
                    UUID.randomUUID()
                        .toString()).substring(0,
                                               10);
    datei = BaseEncoding.base64()
                        .encode(datei.getBytes());
    String erfolgreichmeldung = "{\"id\":\"" +
                                UUID.randomUUID()
                                    .toString() +
                                "\",\"returnmessage\":\"\",\"mimeType\":\"application/pdf\",\"dit\":\"011680\",\"returncode\":\"200\",\"filename\":\"" +
                                datei +
                                "\"\r\n" +
                                ",\"uploadDate\":\"1476949326011\"}";
    response.getWriter()
            .printf(erfolgreichmeldung);
    response.setContentType("text/html");
  }

  @Override
  public void doPost(HttpServletRequest request,
                     HttpServletResponse response) {
    try {
      createReturnMess(response);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
