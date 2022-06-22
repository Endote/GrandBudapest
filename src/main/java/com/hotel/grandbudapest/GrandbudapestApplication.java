package com.hotel.grandbudapest;

import com.hotel.grandbudapest.controller.UseCaseController;
import com.hotel.grandbudapest.model.Person;
import com.hotel.grandbudapest.model.Reservation;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
@EnableJpaRepositories("com.hotel.grandbudapest.repository")
public class GrandbudapestApplication {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(GrandbudapestApplication.class).headless(false).run();

//        ctx.getBean(UseCaseController.class).startApplication();
//        System.setProperty("spring.devtools.restart.enabled", "false");
//        SpringApplication.run(GrandbudapestApplication.class, args);

        SwingUtilities.invokeLater(
                ()->{
                    try {
                        ctx.getBean(UseCaseController.class).startApplication();
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}



