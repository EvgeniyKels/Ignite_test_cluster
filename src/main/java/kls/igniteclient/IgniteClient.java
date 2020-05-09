package kls.igniteclient;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteScheduler;
import org.apache.ignite.Ignition;
import org.apache.ignite.lang.IgniteRunnable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class IgniteClient {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IgniteClient.class);
        ClientConfiguration clientConfiguration = context.getBean(ClientConfiguration.class);
        Environment env = context.getBean(Environment.class);
        String personCacheName = env.getProperty("personCacheName");
        Ignite ignite = Ignition.start(clientConfiguration.igniteConfiguration());
        ignite.compute().broadcast(new IgniteRunnable() {
            @Override
            public void run() {
                ignite.cache(personCacheName).loadCache(null);
            }
        });
    }
}
