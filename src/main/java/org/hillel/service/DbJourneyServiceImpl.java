package org.hillel.service;

import org.hillel.JDBC.DbSource;
import org.hillel.Journey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class DbJourneyServiceImpl implements JourneyService{


    private DbSource dbSource;


    public DbJourneyServiceImpl(DbSource dbSource) {
        this.dbSource = dbSource;
    }


    private Collection<Journey> getStations(String stationFrom, String stationTo){
        List<Journey> journeys = new ArrayList<>();
        try (Connection connection = dbSource.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT station_from, station_to, departure, arrival, route " +
                            "FROM ticket.public.journey " +
                            "WHERE station_from  = ? AND station_to = ? ")){
                preparedStatement.setString(1, stationFrom);
                preparedStatement.setString(2, stationTo);

                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    while (resultSet.next()){
                        journeys.add(new Journey(
                                resultSet.getString("station_from"),
                                resultSet.getString("station_to"),
                                LocalDate.parse(resultSet.getString("departure")),
                                LocalDate.parse(resultSet.getString("arrival"))));
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        return Collections.unmodifiableList(journeys);
    }


    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        List<Journey> journeys = new ArrayList<>();
            for (Journey item: findByStations(stationFrom,stationTo)) {
                if (item.getDeparture().equals(dateFrom) && item.getArrival().equals(dateTo)) {
                    journeys.add(item);
                }
            }

        return Collections.unmodifiableList(journeys);
    }

    @Override
    public Collection<Journey> findByStations(String stationFrom, String stationTo) {
        List<Journey> journeys = new ArrayList<>();

        journeys.addAll(getStations(stationFrom,stationTo));

        return Collections.unmodifiableList(journeys);
    }


}
