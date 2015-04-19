package be.tribersoft.midas.comment;

import javax.inject.Named;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Named
	public static class JerseyConfig extends ResourceConfig {

		public JerseyConfig() {
			property(ServletProperties.FILTER_FORWARD_ON_404, true);
			packages("be.tribersoft.midas.comment.rest.internal");
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

}
