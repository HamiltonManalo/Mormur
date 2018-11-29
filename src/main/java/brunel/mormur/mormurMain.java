package brunel.mormur;

import brunel.mormur.controllers.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;


@EntityScan(basePackageClasses = { mormurMain.class, Jsr310JpaConverters.class })
@SpringBootApplication
public class mormurMain {

	public static void main(String[] args) {
		SpringApplication.run(mormurMain.class, args);
	}
}
