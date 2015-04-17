package be.tribersoft.sigma.comment;

import javax.inject.Named;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import be.tribersoft.sigma.comment.rest.internal.CommentController;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Named
	public static class JerseyConfig extends ResourceConfig {

		public JerseyConfig() {
			this.register(CommentController.class);
			this.register(JacksonFeature.class);
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
