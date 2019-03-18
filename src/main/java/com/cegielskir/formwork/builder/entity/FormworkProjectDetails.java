package com.cegielskir.formwork.builder.entity;


import javax.persistence.*;

@Entity
@Table(name = "formwork_project_details")
public class FormworkProjectDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "upper_extra_distance")
    private float upperExtraDistance;

    @Column(name = "distance")
    private float distance;

    @Column(name = "overlap_distance")
    private float overlapDistance;

    @Column(name = "one_upper_girder_distance")
    private float oneUpperGirderDistance;

    @Column(name = "accuracy")
    private int accuracy;

    @OneToOne
    @JoinColumn(name = "formwork_project_id")
    private FormworkProject formworkProject;

    public FormworkProjectDetails() {
        this.upperExtraDistance = 0.3f;
        this.distance = 2.0f;
        this.overlapDistance = 0.2f;
        this.oneUpperGirderDistance = 0.15f;
        this.accuracy = 2;
    }

    public FormworkProjectDetails(float upperExtraDistance, float distance, float overlapDistance,
                                  float oneUpperGirderDistance, int accuracy) {
        this.upperExtraDistance = upperExtraDistance;
        this.distance = distance;
        this.overlapDistance = overlapDistance;
        this.oneUpperGirderDistance = oneUpperGirderDistance;
        this.accuracy = accuracy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getUpperExtraDistance() {
        return upperExtraDistance;
    }

    public void setUpperExtraDistance(float upperExtraDistance) {
        this.upperExtraDistance = upperExtraDistance;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getOverlapDistance() {
        return overlapDistance;
    }

    public void setOverlapDistance(float overlapDistance) {
        this.overlapDistance = overlapDistance;
    }

    public float getOneUpperGirderDistance() {
        return oneUpperGirderDistance;
    }

    public void setOneUpperGirderDistance(float oneUpperGirderDistance) {
        this.oneUpperGirderDistance = oneUpperGirderDistance;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public FormworkProject getFormworkProject() {
        return formworkProject;
    }

    public void setFormworkProject(FormworkProject formworkProject) {
        this.formworkProject = formworkProject;
    }

    @Override
    public String toString() {
        return "FormworkProjectDetails{" +
                "id=" + id +
                ", upperExtraDistance=" + upperExtraDistance +
                ", distance=" + distance +
                ", overlapDistance=" + overlapDistance +
                ", oneUpperGirderDistance=" + oneUpperGirderDistance +
                ", accuracy=" + accuracy +
                '}';
    }
}
