package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.*;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CompensationServiceImpl implements CompensationService{
    
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);

//        compensation.setEmployeeId(UUID.randomUUID().toString());
//        compensationRepository.insert(compensation);

        return compensation;
    }

    @Override
    public Compensation read(String id) {
        LOG.debug("Reading compensation for id [{}]", id);

        Compensation compensation = new Compensation();
//        compensationRepository.findByEmployeeId(id);

//        if (compensation == null) {
//            throw new RuntimeException("Invalid employeeId: " + id);
//        }

        return compensation;
    }

}
