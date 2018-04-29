package org.telstra.makearray.srvc.serviceimpls;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.telstra.makearray.srvc.rest.exceptions.MakeArrayException;
import org.telstra.makearray.srvc.rest.serviceimpls.MakeArrayServiceImpls;

/**
 * @author Sarin
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MakeArrayServiceImplsTest {

	@Autowired
	private MakeArrayServiceImpls makeArrayServiceImpls;

	@Test
	public void testProcess1() throws MakeArrayException {
		Map<String, Integer[]> requestData = new HashMap<>();
		requestData.put("Array1", new Integer[] {2,4,3,1});
		requestData.put("Array2", new Integer[] {6,3,7,9,5,8,2,4});
		requestData.put("Array3", new Integer[] {12,13,10,11});
		Map<String, Integer[]> resultMap = new HashMap<>();
		resultMap.put("Array", new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13});
		assertArrayEquals(makeArrayServiceImpls.process(requestData).get("Array"), resultMap.get("Array"));
	}

	@Test
	public void testProcess2() throws MakeArrayException {
		Map<String, Integer[]> requestData = new HashMap<>();
		requestData.put("Array1", new Integer[] {});
		requestData.put("Array2", new Integer[] {6,3,7,9,5,8,2,4});
		requestData.put("Array3", new Integer[] {12,13,10,11});
		Map<String, Integer[]> resultMap = new HashMap<>();
		resultMap.put("Array", new Integer[] {2,3,4,5,6,7,8,9,10,11,12,13});
		assertArrayEquals(makeArrayServiceImpls.process(requestData).get("Array"), resultMap.get("Array"));
	}

	@Test
	public void testProcess3() throws MakeArrayException {
		Map<String, Integer[]> requestData = new HashMap<>();
		requestData.put("Array1", new Integer[] {2,4,3,1});
		requestData.put("Array2", new Integer[] {});
		requestData.put("Array3", new Integer[] {});
		Map<String, Integer[]> resultMap = new HashMap<>();
		resultMap.put("Array", new Integer[] {1,2,3,4});
		assertArrayEquals(makeArrayServiceImpls.process(requestData).get("Array"), resultMap.get("Array"));
	}

	@Test(expected = MakeArrayException.class)
	public void testProcess4() throws MakeArrayException {
		Map<String, Integer[]> requestData = new HashMap<>();
		requestData.put("Array1", new Integer[] {2,4,3,1});
		requestData.put("Array2", null);
		requestData.put("Array3", new Integer[] {10,11,12,13});
		makeArrayServiceImpls.process(requestData);
	}

}
