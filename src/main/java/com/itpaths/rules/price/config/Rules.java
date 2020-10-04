package com.itpaths.rules.price.config;

import org.apache.logging.log4j.LogManager;
import org.drools.decisiontable.DecisionTableProviderImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel;
import org.kie.api.conf.EqualityBehaviorOption;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.internal.builder.*;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Configuration;

import org.apache.logging.log4j.Logger;

import java.io.File;

@Configuration("droolsImpl")
public class Rules {
    private final Logger log = LogManager.getLogger("pc-logger");
    private TrackAgendaEventListener trackAgendaEventListener;
    private KieContainer priceContainer;
    String baseUrl = new File("C:\\Users\\umesh\\Documents\\price-poc\\src\\main\\resources\\com\\rules\\").toURI().toASCIIString();
    String priceUrl = baseUrl + "amazonOffers.xls";

    public Rules(){
        priceContainer = loadContainer(priceUrl, "src/main/resource/model/price.drl", "com.itpaths.rules.price", "priceSession");
    }

    private KieContainer loadContainer(String priceUrl, String drl, String packageName, String sessionName) {
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();
        KieModuleModel kmm = ks.newKieModuleModel();
        modelSetup(kmm, packageName, sessionName);
        kfs.writeKModuleXML((kmm.toXML()));
        kfs.write(drl, getDrl(priceUrl));
        KieBuilder kb = ks.newKieBuilder(kfs);
        kb.buildAll();
        if(kb.getResults().hasMessages(Message.Level.ERROR)){
            throw new RuntimeException("Build error: \n" + kb.getResults().toString());
        }
        KieContainer kc = ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
        return kc;
    }

    private void modelSetup(KieModuleModel kmm, String packageName, String sessionName) {
        kmm.newKieBaseModel(packageName)
                .setDefault(true)
                .setEqualsBehavior(EqualityBehaviorOption.EQUALITY)
                .setEventProcessingMode(EventProcessingOption.STREAM)
                .newKieSessionModel(sessionName)
                .setType(KieSessionModel.KieSessionType.STATEFUL)
                .setClockType(ClockTypeOption.get("realtime"));
    }

    private String getDrl(String url) {
        DecisionTableConfiguration configuration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
        configuration.setInputType(DecisionTableInputType.XLS);
        DecisionTableProviderImpl decisionTableProvider = new DecisionTableProviderImpl();
        Resource dt = ResourceFactory.newUrlResource(url);
        String drl = decisionTableProvider.loadFromResource(dt, null);
        return drl;
    }

}
