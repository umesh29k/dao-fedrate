package com.jrstep.service;

import com.jrstep.dao.fedrate.repository.FCalndrRepository;
import com.jrstep.dao.local.model.Employees;
import com.jrstep.dao.local.model.Students;
import com.jrstep.dao.local.repository.EmployeesRepository;
import com.jrstep.dao.local.repository.StudentsRepository;
import com.jrstep.dao.remote.model.Calndr;
import com.jrstep.dao.remote.model.Orgnsm;
import com.jrstep.dao.remote.repository.CalndrRepository;
import com.jrstep.dao.fedrate.repository.FCalndrRepo;
import com.jrstep.dao.remote.repository.OrgnsmRepository;
import com.jrstep.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataRetrievalService {
    @Autowired
    private OrgnsmRepository orgnsmRepo;
    @Autowired
    private CalndrRepository calndrRepo;
    @Autowired
    private EmployeesRepository empRepo;
    @Autowired
    private StudentsRepository studRepo;
    //@Autowired
    //private FCalndrRepository fcalndrRepo;
    @Autowired
    private FCalndrRepo fc;
    public void calculate() throws ApiException {
        List<Orgnsm> orgnsms = orgnsmRepo.findAll();
        List<Calndr> calndrs = calndrRepo.findAll();
        
        List<Employees> employees = empRepo.findAll();
        List<Students> students = studRepo.findAll();


        for(Calndr calndr : calndrs){
            System.out.println(calndr.toString());
            com.jrstep.dao.fedrate.model.Calndr c = new com.jrstep.dao.fedrate.model.Calndr();
            c.setId(calndr.getId());
            //fcalndrRepo.insertCalndr(calndr.getId(),calndr.getCalndrDate());
            fc.insertWithQuery(c);
        }


    }
}
