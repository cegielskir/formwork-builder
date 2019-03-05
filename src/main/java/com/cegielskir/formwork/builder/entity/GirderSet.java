package com.cegielskir.formwork.builder.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "girder_set")
public class GirderSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @DecimalMin("1.5")
    @Column(name = "len")
    private float len;

    @NotNull
    @Min(1)
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "formwork_id")
    private Formwork formwork;

    public GirderSet() {}

    public GirderSet(@NotNull @DecimalMin("1.5") float len, @NotNull @Min(1) int quantity) {
        this.len = len;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLen() {
        return len;
    }

    public void setLen(float len) {
        this.len = len;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Formwork getFormwork() {
        return formwork;
    }

    public void setFormwork(Formwork formwork) {
        this.formwork = formwork;
    }

    @Override
    public String toString() {
        return "GirderSet{" +
                "id=" + id +
                ", len=" + len +
                ", quantity=" + quantity +
                ", formwork=" + formwork +
                '}';
    }
}
