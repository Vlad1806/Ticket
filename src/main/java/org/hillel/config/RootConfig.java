package org.hillel.config;

import org.hillel.service.InMemoryJourneyServiceImpl;
import org.hillel.service.JourneyService;
import org.hillel.service.StubJourneyServiceImpl;
import org.hillel.service.TicketClient;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("org.hillel")
@PropertySource({"application.properties", "database.properties"})
public class RootConfig {
//
//    @Bean
//    public TicketClient ticketClient(){
//        return new TicketClient();
//    }
//
//    @Bean("inMemoryJourneyService")
//    @Lazy
//    public JourneyService getInMemoryJourneyService(){
//        System.out.println("call getInMemoryJourneyService");
//        return new InMemoryJourneyServiceImpl();
//    }
//
//    @Bean
//    public JourneyService stubService(){
//        return new StubJourneyServiceImpl();
//    }
}
