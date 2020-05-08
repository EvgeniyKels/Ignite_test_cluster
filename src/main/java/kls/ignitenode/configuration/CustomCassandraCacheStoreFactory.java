package kls.ignitenode.configuration;

import org.apache.ignite.cache.store.cassandra.CassandraCacheStore;
import org.apache.ignite.cache.store.cassandra.CassandraCacheStoreFactory;
import org.apache.ignite.cache.store.cassandra.persistence.KeyValuePersistenceSettings;

public class CustomCassandraCacheStoreFactory extends CassandraCacheStoreFactory {
    private final String persistenceSettingsXml;

    public CustomCassandraCacheStoreFactory(String persistenceSettingsXml) {
        this.persistenceSettingsXml = persistenceSettingsXml;
    }

    @Override
    public CassandraCacheStore create() {
        setPersistenceSettings(new KeyValuePersistenceSettings(persistenceSettingsXml));
        return super.create();
    }
}
