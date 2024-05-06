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
    String URL = config.getString("url");
    String USER_LOGIN = config.getString("usersParams.admin.login");
    String USER_PASSWORD = config.getString("usersParams.admin.password");
}
