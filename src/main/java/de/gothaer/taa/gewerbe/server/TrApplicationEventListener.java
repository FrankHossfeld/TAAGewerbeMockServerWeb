package de.gothaer.taa.gewerbe.server;

import org.glassfish.jersey.server.ExtendedUriInfo;
import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;

/**
 * Created by hoss on 17.12.15.
 */
public class TrApplicationEventListener
    implements ApplicationEventListener {

  @Override
  public void onEvent(ApplicationEvent applicationEvent) {
    switch (applicationEvent.getType()) {
      case INITIALIZATION_FINISHED:
        System.out.println("TrApplication {} was initialized." +
                           applicationEvent.getResourceConfig()
                                           .getApplicationName());
        break;
      case DESTROY_FINISHED:
        System.out.println("TrApplication {} was detroyed." +
                           applicationEvent.getResourceConfig()
                                           .getApplicationName());
        break;
      default:
        //        System.out.println("--- WARN: TaaApplicationEventListener/onEvent-keine Aktion für dieses Event definiert! ---");
        break;
    }
  }

  @Override
  public RequestEventListener onRequest(RequestEvent requestEvent) {
    return new ResourceEventListener();
  }

  public static class ResourceEventListener
      implements RequestEventListener {
    private volatile long methodStartTime;

    @Override
    public void onEvent(RequestEvent requestEvent) {
      switch (requestEvent.getType()) {
        case RESOURCE_METHOD_START:
          methodStartTime = System.currentTimeMillis();
          break;
        case RESOURCE_METHOD_FINISHED:
          long methodExecution = System.currentTimeMillis() - methodStartTime;
          final String methodName = requestEvent.getUriInfo()
                                                .getMatchedResourceMethod()
                                                .getInvocable()
                                                .getHandlingMethod()
                                                .getName();
          final String resourceName = requestEvent.getUriInfo()
                                                  .getMatchedResourceMethod()
                                                  .getInvocable()
                                                  .getHandler()
                                                  .getHandlerClass()
                                                  .getName();
          System.out.println("TrApplication -> " + resourceName + ": Method '" + methodName + "' executed. Processing time: " + methodExecution + " ms");
          break;
        case ON_EXCEPTION:
          System.out.println("=====================================================================================================================================================");
          ExtendedUriInfo uriInfo = requestEvent.getUriInfo();
          if (uriInfo != null) {
            if (uriInfo.getMatchedResourceMethod() != null) {
              final String expeptionMethodName = requestEvent.getUriInfo()
                                                             .getMatchedResourceMethod()
                                                             .getInvocable()
                                                             .getHandlingMethod()
                                                             .getName();
              final String exceptionResourceName = requestEvent.getUriInfo()
                                                               .getMatchedResourceMethod()
                                                               .getInvocable()
                                                               .getHandler()
                                                               .getHandlerClass()
                                                               .getName();
              final String exceptionResourceCause = requestEvent.getException()
                                                                .getCause()
                                                                .getMessage();
              System.out.println("TrApplication -> " + exceptionResourceName + ": Method '" + expeptionMethodName + "' fails! ==> " + exceptionResourceCause);
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
          }
          requestEvent.getException()
                      .printStackTrace(System.out);
          System.out.println("=====================================================================================================================================================");
          break;
        default:
          //          System.out.println("--- WARN: TaaApplicationEventListener/ResourceEventListener/onEvent-keine Aktion für dieses Event definiert! ---");
          break;
      }
    }

  }

}
