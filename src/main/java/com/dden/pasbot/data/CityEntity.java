package com.dden.pasbot.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "cities")
public class CityEntity {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    public String name;
}
