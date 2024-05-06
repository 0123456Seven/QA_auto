package read_properties;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider{
    Config config = readConfig();
    static Config readConfig(){
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("application.conf");

    }
    String url = config.getString("url");
    Integer age = config.getInt("age");
    String login = config.getString("userParams.admin.login");
    String demoPassword = config.getString("userParams.admin.password");
}
