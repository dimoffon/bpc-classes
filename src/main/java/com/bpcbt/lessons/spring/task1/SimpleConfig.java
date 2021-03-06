package com.bpcbt.lessons.spring.task1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Random;

@Configuration
@ComponentScan("com.bpcbt.lessons.spring")
public class SimpleConfig {

    @Bean
    @Scope("prototype")
    public String textGenerator() {
        String[] split = "Привет,Hello,Bonjure,Konnichiwa".split(",");
        Random random = new Random();
        return split[random.nextInt(split.length)];
    }

    @Bean
    public SimpleHolder simpleHolder(SimpleBean simpleBean) {
        return new SimpleHolder(simpleBean);
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("db/ddl.sql", "db/dml.sql")
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

}
