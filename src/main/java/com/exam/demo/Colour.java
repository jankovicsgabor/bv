package com.exam.demo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Colour implements Serializable {
    @Id
    public String id;

    public String colourName;

    public Colour() {}

    public Colour(String colourName) {
        this.colourName = colourName;
    }

    public Colour(String id, String colourName) {
        this.colourName = colourName;
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%s, firstName='%s']", id, colourName);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColourName() {
        return colourName;
    }

    public void setColourName(String colourName) {
        this.colourName = colourName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Colour colour = (Colour) o;

        return new EqualsBuilder().append(id, colour.id).append(colourName, colour.colourName).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(colourName).toHashCode();
    }
}
