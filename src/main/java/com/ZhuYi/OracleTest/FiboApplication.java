package com.ZhuYi.OracleTest;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.jaxb.internal.XmlJaxbElementProvider;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class FiboApplication extends Application<ApplicationConfiguration> {
    public static void main(String[] args) throws Exception {
        new FiboApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {
    }

    @Override
    public void run(ApplicationConfiguration appConfigure, io.dropwizard.setup.Environment environment) throws Exception {
        final FiboResource resource = new FiboResource();
        environment.jersey().register(resource);
        environment.servlets().addFilter("CrossOriginFilter", new CrossOriginFilter())
                                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    }
}
