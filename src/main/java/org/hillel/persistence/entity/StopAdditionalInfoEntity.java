package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "stop_additional_info")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class StopAdditionalInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "longitude",nullable = false)
    private Double longitude;

    @Column(name = "latitude",nullable = false)
    private Double latitude;

    @Column(name = "date_build")
    private LocalDate dateBuild;

    @Column(name = "city")
    private String city;

    @OneToOne
    @MapsId
    @JoinColumn(name = "stop_id")
    private StopEntity stop;

    @Override
    public String toString() {
        return "StopAdditionalInfo{" +
                "id=" + id +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", dateBuild=" + dateBuild +
                ", city='" + city + '\'' +
                '}';
    }
}
