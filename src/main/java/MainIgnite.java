import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

import javax.cache.Cache;
import java.util.Collection;

public class MainIgnite {
    public static void main(String[] args) {
        Ignite ignite = Ignition.start("main_config.xml");
        ignite.active(true);
        ignite.cache("PersonCache").loadCache(null);

//        Collection <String> strings = ignite.cacheNames();
//        for (String s:
//             strings) {
//            System.out.println(s);
//        }

        IgniteCache <Object, Object> personCache = ignite.getOrCreateCache("PersonCache");
        for (Cache.Entry<Object, Object> e:
             personCache) {
            System.out.println(e.getKey() + "   " + e.getValue());
        }
    }
}
