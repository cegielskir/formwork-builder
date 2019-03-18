package com.cegielskir.formwork.builder.computing;
import com.cegielskir.formwork.builder.entity.FormworkProject;
import com.cegielskir.formwork.builder.service.FormworkProjectDetailsService;
import com.cegielskir.formwork.builder.service.FormworkProjectService;
import com.cegielskir.formwork.builder.service.FormworkService;
import org.springframework.beans.factory.annotation.Autowired;
import com.cegielskir.formwork.builder.entity.Formwork;
import org.springframework.stereotype.Component;


public class ComputingManager implements Runnable{


    private FormworkProjectService formworkProjectService;

    private FormworkService formworkService;

    //TODO rename the whole class
    private Formwork formwork;

    public ComputingManager() {}

    public ComputingManager(Formwork formwork, FormworkProjectService formworkProjectService,
                            FormworkService formworkService) {
        this.formwork = formwork;
        this.formworkProjectService = formworkProjectService;
        this.formworkService = formworkService;
    }


    @Override
    public void run() {
        FormworkBuilder formworkBuilder = new FormworkBuilder(formwork.getRooms(),
                formwork.getGirderSets(), formwork.getFormworkProject());
        FormworkProject formworkProject = formwork.getFormworkProject();
        FormworkProject calculatedFormworkProject = formworkBuilder.calculateSolution();
        formworkProject.setAllFields(calculatedFormworkProject);
        formworkProjectService.add(formworkProject);
        formworkService.add(formwork);


    }

}
