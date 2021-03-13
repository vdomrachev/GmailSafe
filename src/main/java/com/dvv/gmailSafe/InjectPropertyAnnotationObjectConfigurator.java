package com.dvv.gmailSafe;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class InjectPropertyAnnotationObjectConfigurator implements ObjectConfigurator {

    private Map<String, String> propertiesMap;

    public InjectPropertyAnnotationObjectConfigurator(String propertiesFilePath) throws IOException {
    	if (propertiesFilePath != null && new File(propertiesFilePath).exists()) {
    		try (BufferedReader reader = new BufferedReader(new FileReader(propertiesFilePath))) {
    			Stream<String> lines = reader.lines();
    			propertiesMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
    		}
    	} else {
    		propertiesMap = new HashMap<>();
    	}
    }

    @Override
    @SneakyThrows
    public void configure(Object t,ApplicationContext context) {
        Class<?> implClass = t.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);
           if (annotation != null) {
                String value = annotation.value().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(annotation.value());
                field.setAccessible(true);
                field.set(t,value);
            }
        }
    }
}
