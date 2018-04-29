package org.telstra.makearray.srvc.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertArrayEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.telstra.makearray.srvc.rest.controllers.MakeArrayController;
import org.telstra.makearray.srvc.rest.exceptions.MakeArrayException;

/**
 * @author Sarin
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MakeArrayControllerTest {

	@Autowired
	private MakeArrayController makeArrayController;

	@Test
	public void testGenerateOneArray1() throws MakeArrayException {
		Map<String, Integer[]> requestData = new HashMap<>();
		requestData.put("Array1", new Integer[] {4,3,2,1});
		requestData.put("Array2", new Integer[] {9,8,7,6,5,3,2,4});
		requestData.put("Array3", new Integer[] {10,11,12,13});
		Map<String, Integer[]> resultMap = new HashMap<>();
		resultMap.put("Array", new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13});
		ResponseEntity<Map<String, Integer[]>> responseEntity = makeArrayController.generateOneArray(requestData);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertArrayEquals(responseEntity.getBody().get("Array"), resultMap.get("Array"));
	}
	
	@Test
	public void testGenerateOneArray2() throws MakeArrayException {
		Map<String, Integer[]> requestData = new HashMap<>();
		requestData.put("Array1", new Integer[] {4,3,2,1});
		requestData.put("Array2", new Integer[] {});
		requestData.put("Array3", new Integer[] {10,11,12,13});
		Map<String, Integer[]> resultMap = new HashMap<>();
		resultMap.put("Array", new Integer[] {1,2,3,4,10,11,12,13});
		ResponseEntity<Map<String, Integer[]>> responseEntity = makeArrayController.generateOneArray(requestData);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertArrayEquals(responseEntity.getBody().get("Array"), resultMap.get("Array"));
	}


	@Test(expected = MakeArrayException.class)
	public void testGenerateOneArray3() throws MakeArrayException {
		Map<String, Integer[]> requestData = new HashMap<>();
		requestData.put("Array1", null);
		requestData.put("Array2", new Integer[] {});
		requestData.put("Array3", new Integer[] {10,11,12,13});
		makeArrayController.generateOneArray(requestData);
	}

}
