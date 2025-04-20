package com.example.storehouse.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "storehouse_Product")
@Table(name = "STOREHOUSE_PRODUCT")
public class Product extends AbstractPersistable<UUID> {

    @Column(name = "name_", nullable = false)
    private String name;

    @Column(name = "count_", nullable = false)
    private int count = 0;

    @Column(name = "comment_", length = 4000)
    private String comment;

}
