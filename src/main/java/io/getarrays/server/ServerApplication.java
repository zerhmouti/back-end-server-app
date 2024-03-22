package io.getarrays.server;

import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Server;
import io.getarrays.server.repo.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepository serverRepository){
		return args -> {
			serverRepository.save(
					Server.builder()
							.name("Ubuntu Linux")
							.ipAddress("192.168.1.160")
							.type("Personal PC")
							.memory("16 GB")
							.imageUrl("http://localhost:8080/server/image/image1.png")
							.status(Status.SERVER_UP)
							.build()
			);
			serverRepository.save(
					Server.builder()
							.name("Federo Linux")
							.ipAddress("192.168.1.89")
							.type("Dell Tower")
							.memory("16 GB")
							.imageUrl("http://localhost:8080/server/image/image2.png")
							.status(Status.SERVER_DOWN)
							.build()
			);
			serverRepository.save(
					Server.builder()
							.name("MS 2008")
							.ipAddress("192.168.1.53")
							.type("Web Server")
							.memory("16 GB")
							.imageUrl("http://localhost:8080/server/image/image3.png")
							.status(Status.SERVER_DOWN)
							.build()
			);
			serverRepository.save(
					Server.builder()
							.name("Red Hat Entriprise linux")
							.ipAddress("192.168.0.101")
							.type("Web Server")
							.memory("32 GB")
							.imageUrl("http://localhost:8080/server/image/image4.png")
							.status(Status.SERVER_DOWN)
							.build()
			);
		};
	}


	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();

		// Allow credentials
		config.setAllowCredentials(true);

		// Define allowed origins
		config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));

		// Define allowed headers
		config.setAllowedHeaders(Arrays.asList(
				"Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept",
				"Jwt-Token", "Authorization", "X-Requested-With", "Access-Control-Request-Method",
				"Access-Control-Request-Headers"
		));

		// Define exposed headers
		config.setExposedHeaders(Arrays.asList(
				"Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"
		));

		// Define allowed methods
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

		// Register CORS configuration for all paths
		source.registerCorsConfiguration("/**", config);

		// Return CorsFilter instance with the configured CorsConfiguration
		return new CorsFilter(source);
	}

}
