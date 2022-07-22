package com.mindex.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.controller.*;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.ReportingStructure;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeApplicationTests {

	@Autowired 
	private ReportingStructureController controller;
	
	@Autowired
	private CompensationController compController;

	@Test
	public void contextLoads() {
	}

	@Test
	public void ReportingStructureTests() {
		String[][] controlData =  new String[3][3];
		controlData[0][0] = "16a596ae-edd3-4847-99fe-c4518e82c86f";
		controlData[0][1] = "4";
		controlData[1][0] = "b7839309-3348-463b-a7e3-5de1c168beb3";
		controlData[1][1] = "0";
		controlData[2][0] = "03aa1462-ffa9-4978-901b-7c001562cf6f";
		controlData[2][1] = "2";
	
		ReportingStructure reportingStructure = new ReportingStructure();

		for (int idx = 0; idx < 3; idx++) {
			reportingStructure = this.controller.read(controlData[idx][0]);
			assertEquals(controlData[idx][1], reportingStructure.getNumberOfReports().toString());
		}
	}

	@Test
	public void CompensationTests() {

		Compensation compensation = new Compensation();
		compensation = this.compController.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
		
		Double salary = compensation.getSalary();
		assertNotEquals(salary, (Double)null);
	}
}
