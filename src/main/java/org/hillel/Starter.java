package org.hillel;
import org.hillel.config.RootConfig;
import org.hillel.service.TicketClient;
import org.springframework.beans.BeansException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.time.LocalDate;

public class Starter {
    public static void main(String[] args) throws BeansException {
        //AppContext.load("application.properties");

        //final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("common-beans.xml");
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);

        System.out.println("after init");
        TicketClient ticketClient =  applicationContext.getBean(TicketClient.class);
        System.out.println("Journey from db: ");
        System.out.println(ticketClient.find("Odessa", "Kiev",
                LocalDate.parse("2021-04-02"),LocalDate.parse("2021-04-04")));

       //( (ClassPathXmlApplicationContext)applicationContext).close();
//        JourneyEntity journeyEntity = new JourneyEntity();
//        journeyEntity.setStationFrom("UGANDA");
//        System.out.println("Create journey with id = " + ticketClient.createJourney(journeyEntity));

//        ticketClient =  applicationContext.getBean(TicketClient.class);
//
//        System.out.println(ticketClient.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));

        //System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(3).plusDays(4)));
    }
}
