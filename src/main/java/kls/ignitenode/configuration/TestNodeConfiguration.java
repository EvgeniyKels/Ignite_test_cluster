package kls.ignitenode.configuration;
import kls.ignitenode.model.entities.Person;
import kls.ignitenode.model.entities.PersonKeyClass;
import org.apache.commons.io.FileUtils;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.store.cassandra.CassandraCacheStoreFactory;
import org.apache.ignite.cache.store.cassandra.datasource.DataSource;
import org.apache.ignite.cache.store.cassandra.persistence.KeyValuePersistenceSettings;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
//TODO partition node
@Configuration
public class TestNodeConfiguration {
    @Value("${cassandraContactPoint}")
    private String cassandraContactPoint;
    @Value("${cassandraPort}")
    private int cassandraPort;
    @Value("${pathToPersonSettingsXML}")
    private String pathToPersonSettingsXML;
    @Value("${enableFilePersistence}")
    private boolean enableFilePersistence;
    @Value("${storagePath}")
    private String storagePath;
    @Value("${personCacheName}")
    private String personCacheName;
    @Value("${personSchemaName}")
    private String personSchemaName;
    @Bean
    DataSource dataSourceConfiguration () {
        DataSource dataSource = new DataSource();
        dataSource.setContactPoints(cassandraContactPoint);
        dataSource.setPort(cassandraPort);
        return dataSource;
    }

    @Bean
    CassandraCacheStoreFactory cassandraCacheStoreFactory () {
        String persistenceSettingXml = null;
        try {
            persistenceSettingXml = FileUtils.readFileToString(new File(pathToPersonSettingsXML), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CustomCassandraCacheStoreFactory cassandraCacheStoreFactory = new CustomCassandraCacheStoreFactory(persistenceSettingXml);
        cassandraCacheStoreFactory.setDataSource(dataSourceConfiguration());
        return cassandraCacheStoreFactory;
    }

    @Bean
    public IgniteConfiguration igniteConfiguration () {
        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        igniteConfiguration.setClientMode(false);
        if (enableFilePersistence) {
            DataStorageConfiguration nativePersistence = new DataStorageConfiguration();
            nativePersistence.getDefaultDataRegionConfiguration().setPersistenceEnabled(enableFilePersistence);
            nativePersistence.setStoragePath(storagePath);
            igniteConfiguration.setDataStorageConfiguration(nativePersistence);
        }
        CacheConfiguration<PersonKeyClass, Person> personCacheConfiguration = new CacheConfiguration<>();
        personCacheConfiguration.setName(personCacheName);
        personCacheConfiguration.setReadThrough(true);
        personCacheConfiguration.setSqlSchema(personSchemaName);
        personCacheConfiguration.setIndexedTypes(PersonKeyClass.class, Person.class);
        personCacheConfiguration.setCacheStoreFactory(cassandraCacheStoreFactory());
//        personCacheConfiguration.setBackups(0); // 0-no backups, 1-every key has 1 backup
//        personCacheConfiguration.setCacheMode(CacheMode.PARTITIONED); // partitioned by default
//        personCacheConfiguration.setAtomicityMode(CacheAtomicityMode.ATOMIC); //atomic by default
        igniteConfiguration.setCacheConfiguration(personCacheConfiguration);
        return igniteConfiguration;
    }
}