package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hillel.persistence.entity.util.YesNoConvector;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractModifyEntity<ID>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    @Column(name = "create_date")
    @CreationTimestamp
    private Instant createDate;

    @Column(name = "active")
//    @Type(type = "numeric_boolean")
    @Convert(converter = YesNoConvector.class)
    private boolean active = true;


}
