package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class CommonInfo {
    @Column(name = "name")
    private String name;

    @Column(name = "description",length = 10000)
    private String description;

    @Override
    public String toString() {
        return "CommonInfo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
