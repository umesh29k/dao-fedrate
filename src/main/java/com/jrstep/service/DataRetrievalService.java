package com.jrstep.service;

import com.jrstep.dao.Repo;
import com.jrstep.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataRetrievalService {
    @Autowired
    private Repo repo;
    public void calculate() throws ApiException {
        /**
         * write custom repo, to query tables from both databases
         */
        repo.get();

    }
}
