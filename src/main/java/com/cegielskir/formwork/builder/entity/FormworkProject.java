package com.cegielskir.formwork.builder.entity;

import javax.persistence.*;

@Entity
@Table(name = "formwork_project")
public class FormworkProject{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "max_left_girders")
    private int maxLeftGirders;

    @Column(name = "max_left_girders_value")
    private int maxLeftGirdersValue;

    @Column(name = "is_better_solution_available")
    private boolean isBetterSolutionAvailable;

    @Column(name = "is_better_solution_calculated")
    private boolean isBetterSolutionCalculated;

    @Column(name = "result_json")
    private String resultJSON;

    @OneToOne(mappedBy = "formworkProject", cascade = {CascadeType.ALL})
    private Formwork formwork;

    @OneToOne(mappedBy = "formworkProject", cascade = {CascadeType.ALL})
    private FormworkProjectDetails formworkProjectDetails;

    public FormworkProject() {
        this.maxLeftGirders = -1;
        this.maxLeftGirdersValue = -1;
        this.isBetterSolutionAvailable = true;
        this.isBetterSolutionCalculated = false;
        this.resultJSON = null;
    }

    public FormworkProject(int maxLeftGirders, int maxLeftGirdersValue, boolean isBetterSolutionAvailable,
                           boolean isBetterSolutionCalculated, String resultJSON) {
        this.maxLeftGirders = maxLeftGirders;
        this.maxLeftGirdersValue = maxLeftGirdersValue;
        this.isBetterSolutionAvailable = isBetterSolutionAvailable;
        this.isBetterSolutionCalculated = isBetterSolutionCalculated;
        this.resultJSON = resultJSON;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxLeftGirders() {
        return maxLeftGirders;
    }

    public void setMaxLeftGirders(int maxLeftGirders) {
        this.maxLeftGirders = maxLeftGirders;
    }

    public int getMaxLeftGirdersValue() {
        return maxLeftGirdersValue;
    }

    public void setMaxLeftGirdersValue(int maxLeftGirdersValue) {
        this.maxLeftGirdersValue = maxLeftGirdersValue;
    }

    public boolean getIsBetterSolutionAvailable() {
        return isBetterSolutionAvailable;
    }

    public void setIsBetterSolutionAvailable(boolean isBetterSolutionAvailable) {
        this.isBetterSolutionAvailable = isBetterSolutionAvailable;
    }

    public boolean getIsBetterSolutionCalculated() {
        return isBetterSolutionCalculated;
    }

    public void setIsBetterSolutionCalculated(boolean isBetterSolutionCalculated) {
        this.isBetterSolutionCalculated = isBetterSolutionCalculated;
    }

    public String getResultJSON() {
        return resultJSON;
    }

    public void setResultJSON(String resultJSON) {
        this.resultJSON = resultJSON;
    }

    public Formwork getFormwork() {
        return formwork;
    }

    public void setFormwork(Formwork formwork) {
        this.formwork = formwork;
    }

    public FormworkProjectDetails getFormworkProjectDetails() {
        return formworkProjectDetails;
    }

    public void setFormworkProjectDetails(FormworkProjectDetails formworkProjectDetails) {
        this.formworkProjectDetails = formworkProjectDetails;
        this.formworkProjectDetails.setFormworkProject(this);
    }



    @Override
    public String toString() {
        return "FormworkProject{" +
                "id=" + id +
                ", maxLeftGirders=" + maxLeftGirders +
                ", maxLeftGirdersValue=" + maxLeftGirdersValue +
                ", isBetterSolutionAvailable=" + isBetterSolutionAvailable +
                ", isBetterSolutionCalculated=" + isBetterSolutionCalculated +
                ", resultJSON='" + resultJSON + '\'' +
                '}';
    }


    public void setAllFields(FormworkProject formworkProject){
        this.setMaxLeftGirdersValue(formworkProject.getMaxLeftGirdersValue());
        this.setMaxLeftGirders(formworkProject.getMaxLeftGirders());
        this.setIsBetterSolutionCalculated(formworkProject.getIsBetterSolutionCalculated());
        this.setIsBetterSolutionAvailable(formworkProject.getIsBetterSolutionAvailable());
        this.setResultJSON(formworkProject.getResultJSON());
    }
}
