import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

public class MainIgnite {
    public static void main(String[] args) {
        Ignite ignite = Ignition.start("main_config.xml");
        ignite.cache("PersonCache").loadCache(null);
    }
}
