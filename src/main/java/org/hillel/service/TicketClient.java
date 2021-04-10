package org.hillel.service;
import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class TicketClient {

    @Autowired
    @Qualifier("dbJourneyServiceImpl")
    private JourneyService journeyService;

    @Autowired
    private TransactionalJourneyService transactionalJourneyService;

    @Value("${system.message:default value}")
    private String systemMessage;

    public TicketClient() {
    }

    public Long createJourney(final JourneyEntity journeyEntity){
        return transactionalJourneyService.createJourney(journeyEntity);
    }

    @PostConstruct
    public void init() throws Exception {
        if (journeyService == null) throw new IllegalArgumentException("journeyService not init");
        else {
            System.out.println("@PostConstruct: " + systemMessage);
        }
    }

    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("destroy bean");
    }


    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        if (stationFrom == null) throw new IllegalArgumentException("station from must be set");
        if (stationTo == null) throw new IllegalArgumentException("station to must be set");
        if (dateFrom == null) throw new IllegalArgumentException("date from must be set");
        if (dateTo == null) throw new IllegalArgumentException("date to must be set");

        return journeyService.find(stationFrom, stationTo, dateFrom, dateTo);
    }
}
