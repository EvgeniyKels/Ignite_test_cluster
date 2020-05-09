package kls.ignitenode;
import kls.ignitenode.configuration.TestNodeConfiguration;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteScheduler;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.AtomicConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MainIgnite {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainIgnite.class);
        Environment env = context.getBean(Environment.class);
        TestNodeConfiguration igniteConfiguration = context.getBean(TestNodeConfiguration.class);
        Ignite ignite = Ignition.start(igniteConfiguration.igniteConfiguration());
        ignite.cluster().active(true);
        IgniteCache <?, ?> personCacheName = ignite.cache(env.getProperty("personCacheName"));
//        personCacheName.clear(); //TODO in real action may be not need because will not load all old keys
        personCacheName.loadCache(null);
        ScheduledExecutorService executorService =
                Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(getReloadTask(ignite, env), 0, 59, TimeUnit.SECONDS);
    }

    private static Runnable getReloadTask(Ignite ignite, Environment env) {
        return () -> {
            IgniteCache <?, ?> personCacheName = ignite.cache(env.getProperty("personCacheName"));
//            personCacheName.clear();
            personCacheName.loadCache(null);
        };
    }
}
