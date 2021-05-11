package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hillel.persistence.entity.util.YesNoConvector;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractModifyEntity<ID extends Serializable> extends AbstractEntity<ID> {

    @Column(name = "create_date")
    @CreationTimestamp
    private Instant createDate;

    @Column(name = "active")
//  @Type(type = "numeric_boolean")
    @Convert(converter = YesNoConvector.class)
    private boolean active = true;


}
