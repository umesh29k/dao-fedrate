package com.itpaths.rules.price.api;

import com.itpaths.rules.price.config.TrackAgendaEventListener;
import com.itpaths.rules.price.model.dto.Formula;
import org.apache.logging.log4j.LogManager;
import org.drools.decisiontable.DecisionTableProviderImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel;
import org.kie.api.conf.EqualityBehaviorOption;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.internal.builder.*;
import org.kie.internal.io.ResourceFactory;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class Rules {
    private final Logger log = LogManager.getLogger("pc-logger");
    private TrackAgendaEventListener trackAgendaEventListener;
    private KieContainer priceCodeContainer, formulaContainer;

    String baseUrl = new File("C:\\Users\\umesh\\Documents\\" +
            "price-poc\\src\\main\\resources\\" +
            "com\\itpaths\\rules\\").toURI().toASCIIString();

    String formulaUrl = baseUrl + "formula.xls";
    String priceCodeUrl = baseUrl + "price_code.xls";

    public Rules() {
        //formulaContainer = loadFormulaContainer(formulaUrl);
        priceCodeContainer = loadPriceCodeContainer(priceCodeUrl);
    }

    private KieContainer loadPriceCodeContainer(String url) {
        getDrl(url);
        Resource priceCode = ResourceFactory
                .newClassPathResource("com/itpaths/rules/price_code.xls", getClass());
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kieFileSystem = ks.newKieFileSystem()
                .write(priceCode);
        KieBuilder kb = ks.newKieBuilder(kieFileSystem)
                .buildAll();
        KieRepository kieRepository = ks.getRepository();
        ReleaseId krDefaultReleaseId = kieRepository.getDefaultReleaseId();
        KieContainer kc = ks.newKieContainer(krDefaultReleaseId);
        return kc;
    }

    private KieContainer loadFormulaContainer(String url) {
        getDrl(url);
        Resource formula = ResourceFactory
                .newClassPathResource("com/itpaths/rules/formula.xls", getClass());
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kieFileSystem = ks.newKieFileSystem()
                .write(formula);
        KieBuilder kb = ks.newKieBuilder(kieFileSystem)
                .buildAll();
        KieRepository kieRepository = ks.getRepository();
        ReleaseId krDefaultReleaseId = kieRepository.getDefaultReleaseId();
        KieContainer kc = ks.newKieContainer(krDefaultReleaseId);
        return kc;
    }

    private KieContainer getContainerByURI(String url, String drl, String packageName, String sessionName) {
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();
        KieModuleModel kmm = ks.newKieModuleModel();
        modelSetup(kmm, packageName, sessionName);
        kfs.writeKModuleXML((kmm.toXML()));
        kfs.write(drl, getDrl(url));
        KieBuilder kb = ks.newKieBuilder(kfs);
        kb.buildAll();
        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build error: \n" + kb.getResults().toString());
        }
        return ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
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
        System.out.println(drl);
        return drl;
    }

    public Formula getMethod(Formula formula) {
        formula.setApply(true);
        KieSession ks = priceCodeContainer.newKieSession();
        ks.insert(formula);
        ks.fireAllRules();
        ks.destroy();
        ks.dispose();
        return formula;
    }

    public Formula getFormula(Formula formula) {
        formula.setApply(true);
        KieSession ks = priceCodeContainer.newKieSession();
        ks.insert(formula);
        ks.fireAllRules();
        ks.destroy();
        ks.dispose();
        return formula;
    }
}
