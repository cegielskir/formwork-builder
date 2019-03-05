package com.cegielskir.formwork.builder.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @DecimalMin("1.0")
    @Column(name = "len")
    private float len;

    @NotNull
    @Column(name = "wid")
    @DecimalMin("1.0")
    private float wid;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "formwork_id")
    private Formwork formwork;

    public Room() {}

    public Room(@NotNull @DecimalMin("1.0") float len, @NotNull @DecimalMin("1.0") float wid) {
        this.len = len;
        this.wid = wid;
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

    public float getWid() {
        return wid;
    }

    public void setWid(float wid) {
        this.wid = wid;
    }

    public Formwork getFormwork() {
        return formwork;
    }

    public void setFormwork(Formwork formwork) {
        this.formwork = formwork;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", len=" + len +
                ", wid=" + wid +
                ", formwork=" + formwork +
                '}';
    }
}
