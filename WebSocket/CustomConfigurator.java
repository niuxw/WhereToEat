package com.example.accessingdatamysql.WebSocket;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.socket.server.standard.ServerEndpointRegistration;

/**
 * The Class CustomConfigurator.
 */
public class CustomConfigurator extends ServerEndpointRegistration.Configurator
        implements ApplicationContextAware {

    /** The context. */
    private static volatile BeanFactory context;

    /**
     * Gets the endpoint instance.
     *
     * @param <T> the generic type
     * @param endpoint the endpoint
     * @return the endpoint instance
     * @throws InstantiationException the instantiation exception
     */
    @Override
    public <T> T getEndpointInstance(Class<T> endpoint) throws InstantiationException {
        return context.getBean(endpoint);
    }

    /**
     * Sets the application context.
     *
     * @param applicationContext the new application context
     * @throws BeansException the beans exception
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            CustomConfigurator.context = applicationContext;
    }
}
