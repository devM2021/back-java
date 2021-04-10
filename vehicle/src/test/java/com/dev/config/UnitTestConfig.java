package com.dev.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuration bean for unit tests.
 */

@Configuration
@ComponentScan(
        basePackages = {
                "com.dev"
        })
@EntityScan(
        basePackages = {
                "com.dev"
        })
@EnableJpaRepositories(
        basePackages = {
                "com.dev"
        })
@Import({
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
public class UnitTestConfig {

}
