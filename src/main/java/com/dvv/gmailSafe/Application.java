package com.dvv.gmailSafe;

import java.io.IOException;
import java.util.Map;

public class Application {
    public static ApplicationContext run(String packageToScan, String propertiesFilePath, Map<Class, Class> ifc2ImplClass) throws IOException {
        JavaConfig config = new JavaConfig(packageToScan, ifc2ImplClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context, propertiesFilePath);
        context.setFactory(objectFactory);
        return context;
    }
}
