package org.hillel;
import org.hillel.config.RootConfig;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.service.TicketClient;
import org.springframework.beans.BeansException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.time.LocalDate;

public class Starter {
    public static void main(String[] args) throws BeansException {

        //final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("common-beans.xml");
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);

        System.out.println("after init");
        TicketClient ticketClient =  applicationContext.getBean(TicketClient.class);
        System.out.println("Journey from db: ");
        System.out.println(ticketClient.find("Odessa", "Kiev",
                LocalDate.parse("2021-04-02"),LocalDate.parse("2021-04-04")));
        System.out.println("Find journey by stations from DB: ");
        System.out.println(ticketClient.findByStations("Odessa","Lviv"));


        System.out.println("Journey from db with hibernate: ");
        JourneyEntity journeyEntity = new JourneyEntity("Odessa","Pripyat",
                LocalDate.parse("2021-04-10"),
                LocalDate.parse("2021-04-12"));
        System.out.println("Create journey with id = " + ticketClient.createJourney(journeyEntity));

        ticketClient =  applicationContext.getBean(TicketClient.class);

        System.out.println(ticketClient.find("Odessa", "Pripyat", LocalDate.parse("2021-04-10"),
                LocalDate.parse("2021-04-12")));
    }
}
