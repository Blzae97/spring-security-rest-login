package blaze.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
public class RestLoginApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        String yml = getYml();
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(RestLoginApplication.class);
        springApplicationBuilder.properties(yml);

        SpringApplication springApplication = springApplicationBuilder.build();
        springApplication.run(args);
    }

    public static String getYml() {
        return "spring.config.location=" + getApplicationYml() + getDatasourceYml();
    }

    public static String getApplicationYml() {
        return "classpath:/application.yml;";
    }

    public static String getDatasourceYml() {
        return "classpath:/datasource.yml";
    }
}