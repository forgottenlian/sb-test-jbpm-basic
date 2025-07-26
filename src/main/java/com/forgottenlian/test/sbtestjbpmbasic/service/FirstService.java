package com.forgottenlian.test.sbtestjbpmbasic.service;

import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.kie.api.KieServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FirstService {

    private final DeploymentService deploymentService;
    private final RuntimeDataService runtimeDataService;
    private final ProcessService processService;
    
    private KModuleDeploymentUnit deployedUnit;

    public FirstService(DeploymentService ds, RuntimeDataService rds, ProcessService ps) {
        this.deploymentService = ds;
        this.runtimeDataService = rds;
        this.processService = ps;
    }

    public void deployService() {
        System.out.println("deployService >>>");

        deployedUnit = new KModuleDeploymentUnit("com.forgottenlian.test.sb-test-jbpm-basic", "sb-test-jbpm-basic", "1.0.0");
        deployedUnit.setKieContainer(KieServices.Factory.get().getKieClasspathContainer());
        deploymentService.deploy(deployedUnit);
    }

    public void testSimple_startProcess() {
        processService.startProcess(deployedUnit.getIdentifier(), "test-simple");
    }
}
