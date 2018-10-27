package org.demis27.comics.comicbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.demis27.comics"})
public class ComicBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicBookApplication.class, args);
	}
}