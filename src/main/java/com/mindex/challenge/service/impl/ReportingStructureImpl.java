package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.*;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureImpl implements ReportingStructureService {
   
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureImpl.class);
 
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Pulling number of reports [{}]", id);

        if (id == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        ReportingStructure reportingStructure = new ReportingStructure();

        int tmpNbrReports = 0;
        int arraySz = 1;
        int loopIdx = 1;
        ArrayList<String> empIds = new ArrayList<>();
        empIds.add(id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(0);
        
        if(employee.getDirectReports() != null) {
            tmpNbrReports += employee.getDirectReports().size();
            for (Employee newEmpId : employee.getDirectReports()) {
                empIds.add(newEmpId.getEmployeeId());
                arraySz++;
            }
        }

        // While loop until no records return directs
        while (arraySz > loopIdx) {
            LOG.debug("Pulling employee record for [{}]", empIds.get(loopIdx));

            // Call employee service with id to get employee
            employee = employeeRepository.findByEmployeeId(empIds.get(loopIdx));

            // Count direct reports and call same service for each report (recursive)
            if(employee.getDirectReports() != null) {
                tmpNbrReports += employee.getDirectReports().size();
                for (Employee newEmpId : employee.getDirectReports()) {
                    empIds.add(newEmpId.getEmployeeId());
                    arraySz++;
                }
            }
            loopIdx++;
        }
        reportingStructure.setNumberOfReports(tmpNbrReports);

        return reportingStructure;
    }
    
}
