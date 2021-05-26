package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stop")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class StopEntity extends AbstractModifyEntity<Long>{

    @Embedded
    private CommonInfo commonInfo;

    @OneToOne(mappedBy = "stop", cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY,orphanRemoval = true)
    private StopAdditionalInfoEntity additionalInfo;

    @ManyToMany(mappedBy = "stops",fetch = FetchType.LAZY)
    private List<JourneyEntity> journeys = new ArrayList<>();

    @Transient
    private boolean applyToJourneyBuild;


    public void addStopAdditionalInfo(StopAdditionalInfoEntity stopAdditionalInfo){
        if (stopAdditionalInfo == null) {
            this.additionalInfo = null;
            return;
        }
        stopAdditionalInfo.setStop(this);
        this.additionalInfo = stopAdditionalInfo;
    }

    public void addJourney(JourneyEntity journeyEntity) {
        if (journeyEntity == null)return;
        if (journeys == null) journeys = new ArrayList<>();
        journeys.add(journeyEntity);
    }

    public void removeAllJourneys(){
        if (CollectionUtils.isEmpty(journeys))return;
        journeys.forEach(item -> item.getStops().remove(this));
        this.journeys.clear();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StopEntity)) return false;
        StopEntity stopEntity = (StopEntity) o;
        return getId() != null && Objects.equals(getId(),stopEntity.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "StopEntity{" +
                "commonInfo=" + commonInfo +
                ", additionalInfo=" + additionalInfo +
                ", applyToJourneyBuild=" + applyToJourneyBuild +
                '}';
    }
}
