package com.dden.pasbot.data;

import com.dden.pasbot.dto.DepartmentType;
import com.dden.pasbot.dto.PinState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "departments")
public class DepartmentsEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Id
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DepartmentType type;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "pin")
    private String pin;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private PinState state;

}
