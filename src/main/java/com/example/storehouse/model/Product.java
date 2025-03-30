package com.example.storehouse.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Entity(name = "storehouse_Product")
@Table(name = "STOREHOUSE_PRODUCT")
public class Product extends AbstractPersistable<UUID> {

    @Column(name = "name_", nullable = false)
    private String name;

    @Column(name = "count_", nullable = false)
    private long actualCount = 0L;

    @Column(name = "comment_", length = 4000)
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getActualCount() {
        return actualCount;
    }

    public void setActualCount(long actualCount) {
        this.actualCount = actualCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
