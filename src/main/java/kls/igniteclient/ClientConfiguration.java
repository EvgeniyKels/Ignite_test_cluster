package kls.igniteclient;

import kls.ignitenode.model.entities.Person;
import kls.ignitenode.model.entities.PersonKeyClass;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.context.annotation.Bean;

public class ClientConfiguration {
    @Bean
    public IgniteConfiguration igniteConfiguration () {
        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        igniteConfiguration.setClientMode(true);
        return igniteConfiguration;
    }
}