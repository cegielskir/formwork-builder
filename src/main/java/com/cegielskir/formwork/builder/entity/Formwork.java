package com.cegielskir.formwork.builder.entity;

import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="formwork")
public class Formwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name="create_date")
    private Date createDate;

    @Column(name = "info")
    private String info;

    @OneToMany(mappedBy = "formwork",
                cascade = {CascadeType.ALL})
    @Column(name = "rooms")
    private List<Room> rooms;

    @OneToMany(mappedBy = "formwork",
                cascade = {CascadeType.ALL})
    @Column(name = "girder_sets")
    private List<GirderSet> girderSets;

    @Column(name = "solution")
    private String solution;

    @Column(name = "is_solved")
    private boolean isSolved;

    public Formwork() {
        this.createDate =  new Date(System.currentTimeMillis());
        this.solution = null;
        this.isSolved = false;
    }

    public Formwork(@NotNull String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date create_date) {
        this.createDate = create_date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public void addRoom(Room room){
        if(rooms == null){
            rooms = new ArrayList<>();
        }

        rooms.add(room);
        room.setFormwork(this);
    }

    public void addGirderSet(GirderSet girderSet){
        if(girderSets == null){
            girderSets = new ArrayList<>();
        }

        girderSets.add(girderSet);
        girderSet.setFormwork(this);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<GirderSet> getGirderSets() {
        return girderSets;
    }

    @Override
    public String toString() {
        return "Formwork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", create_date=" + createDate +
                ", info='" + info + '\'' +
                ", rooms=" + rooms +
                ", girderSets=" + girderSets +
                ", solution=" + solution +
                ", isSolved=" + isSolved +
                '}';
    }
}
