package com.cegielskir.formwork.builder.formworkbuilder.entity;

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
    @Column(name="departure_date")
    private Date create_date;

    @Column(name = "info")
    private String info;

    @OneToMany(mappedBy = "formwork_id",
                cascade = {CascadeType.ALL})
    private List<Room> rooms;

    @OneToMany(mappedBy = "formwork_id",
                cascade = {CascadeType.ALL})
    private List<GirderSet> girderSets;

    public Formwork() {}

    public Formwork(@NotNull String name) {
        this.name = name;
        this.create_date =  new Date(System.currentTimeMillis());
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

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
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

    public List<Room> getRooms() {
        return rooms;
    }

    public List<GirderSet> getGirderSets() {
        return girderSets;
    }
}
