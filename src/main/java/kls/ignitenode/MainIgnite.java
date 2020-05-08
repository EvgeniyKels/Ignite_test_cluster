package kls.ignitenode;
import kls.ignitenode.configuration.TestNodeConfiguration;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class MainIgnite {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainIgnite.class);
        Environment env = context.getBean(Environment.class);
        TestNodeConfiguration igniteConfiguration = context.getBean(TestNodeConfiguration.class);
        Ignite ignite = Ignition.start(igniteConfiguration.igniteConfiguration());
        ignite.cluster().active(true);
        ignite.cache(env.getProperty("personCacheName")).loadCache(null);
    }
}

