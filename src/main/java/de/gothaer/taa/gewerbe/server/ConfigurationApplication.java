package de.gothaer.taa.gewerbe.server;

import de.gothaer.taa.gewerbe.server.errorhandling.TaaApplicationExceptionMapper;
import de.gothaer.taa.gewerbe.server.errorhandling.TaaGenericExceptionMapper;
import de.gothaer.taa.gewerbe.server.errorhandling.TaaNotFoundException;
import de.gothaer.taa.gewerbe.server.filters.CustomLoggingFilter;
import de.gothaer.taa.gewerbe.server.filters.LoggingRequestFilter;
import de.gothaer.taa.gewerbe.server.filters.LoggingResponseFilter;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConfigurationApplication
    extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    final Set<Class<?>> classes = new HashSet<Class<?>>();
    // register resources and features
    //      classes.add(MultiPartFeature.class);
    //      classes.add(MultiPartResource.class);

    classes.add(TaaApplicationExceptionMapper.class);
    classes.add(TaaGenericExceptionMapper.class);
    classes.add(TaaNotFoundException.class);

    classes.add(LoggingRequestFilter.class);
    classes.add(LoggingResponseFilter.class);
    classes.add(CustomLoggingFilter.class);
    //    classes.add(LoggingResponseFilter.class);

    classes.add(ConfigurationEventListener.class);
    return classes;
  }

  @Override
  public Map<String, Object> getProperties() {
    Map<String, Object> properties = new HashMap<String, Object>();
    properties.put(ServerProperties.MONITORING_STATISTICS_MBEANS_ENABLED,
                   true);
    return properties;
  }

}
