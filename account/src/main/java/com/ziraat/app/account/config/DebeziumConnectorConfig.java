package com.ziraat.app.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DebeziumConnectorConfig {
    @Bean
    public io.debezium.config.Configuration mysqlConnector() {

        Map<String, String> configMap = new HashMap<>();

        configMap.put("name", "property-connector");
        configMap.put("connector.class", "io.debezium.connector.mysql.MySqlConnector");
        configMap.put("database.allowPublicKeyRetrieval", "true");
        configMap.put("database.hostname", "host.docker.internal");
        configMap.put("database.port", "3306");
        configMap.put("database.user", "ziraat-app-user1");
        configMap.put("database.password", "123456");
        configMap.put("database.include.list", "ziraat_app-db");
        configMap.put("table.include.list", "ziraat_app_db.accounts");
        configMap.put("topic-prefix", "ziraat-app-kafka");
        configMap.put("schema.history.internal.kafka.bootstrap.servers","kafka:9092");
        configMap.put("schema.history.internal.kafka.topic","schema-changes.db");
        configMap.put("database.server.id", "1");

        return io.debezium.config.Configuration.from(configMap);
    }
}