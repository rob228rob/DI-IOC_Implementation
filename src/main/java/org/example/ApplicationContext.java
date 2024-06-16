package org.example;

import org.example.MyAnnotation.Instance;
import org.reflections.Reflections;
import org.example.MyAnnotation.Config;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class ApplicationContext {

    private final Map<Class<?>, Object> instances = new HashMap<>();

    public ApplicationContext() {
        Set<Class<?>> types = getTypesAnnotatedWithConfig();
        for (Class<?> type : types) {
            registerInstances(type);
        }
    }

    private Set<Class<?>> getTypesAnnotatedWithConfig() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("org.example.Configuration"))
                .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner(), new MethodAnnotationsScanner()));
        return reflections.getTypesAnnotatedWith(Config.class);
    }

    private void registerInstances(Class<?> type) {
        Object instance = createInstance(type);
        Method[] methods = type.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Instance.class)) {
                try {
                    Object methodInstance = method.invoke(instance, getDependencies(method));
                    instances.put(method.getReturnType(), methodInstance);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public List<Object> getAllInstances()
    {
        return instances.values().stream().toList();
    }

    private Object createInstance(Class<?> type) {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private Object[] getDependencies(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        return Arrays.stream(parameterTypes)
                .map(this::getInstance)
                .toArray();
    }

    public <T> T getInstance(Class<T> typeClass) {
        return typeClass.cast(Optional.ofNullable(instances.get(typeClass))
                .orElseThrow(() -> new IllegalArgumentException("No such class " + typeClass.getName())));
    }
}