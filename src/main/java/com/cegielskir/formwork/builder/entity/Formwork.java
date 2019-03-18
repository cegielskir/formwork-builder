package com.cegielskir.formwork.builder.entity;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "formwork",
                cascade = {CascadeType.ALL})
    @Column(name = "rooms")
    private List<Room> rooms;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "formwork",
                cascade = {CascadeType.ALL})
    @Column(name = "girder_sets")
    private List<GirderSet> girderSets;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "formwork_project_id")
    private FormworkProject formworkProject;

    public Formwork() {
        this.createDate =  new Date(System.currentTimeMillis());
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

    public void deleteRoom(Room room){
        this.rooms.remove(room);
    }

    public void deleteGirderSet(GirderSet girderSet){
        this.girderSets.remove(girderSet);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<GirderSet> getGirderSets() {
        return girderSets;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setGirderSets(List<GirderSet> girderSets) {
        this.girderSets = girderSets;
    }

    public FormworkProject getFormworkProject() {
        return formworkProject;
    }

    public void setFormworkProject(FormworkProject formworkProject) {
        this.formworkProject = formworkProject;
        this.formworkProject.setFormwork(this);
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
                '}';
    }
}
