/**
 * 
 */
package org.telstra.makearray.srvc.rest.serviceimpls;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telstra.makearray.srvc.rest.exceptions.MakeArrayException;
import org.telstra.makearray.srvc.rest.services.MakeArrayService;

/**
 * @author Sarin
 *
 */
@Service
public class MakeArrayServiceImpls implements MakeArrayService {

	private static final Logger LOG = LoggerFactory.getLogger(MakeArrayServiceImpls.class);

	/**
	 * This method used to used to generate and sort one array from multiple array
	 * 
 	 * @param requestData
	 * @return Map<String, Integer[]>
	 * @throws MakeArrayException
	 */
	@Override
	public Map<String, Integer[]> process(Map<String, Integer[]> requestData) throws MakeArrayException {
		LOG.info("STARTS - process");
		Map<String, Integer[]> resultMap = new HashMap<>();
		Set<Integer> result = new HashSet<>();
		Integer[] sortedResult = null;
		try {
			requestData.entrySet().forEach(entry -> Collections.addAll(result, entry.getValue()));
			sortedResult = result.parallelStream().sorted().toArray(Integer[]::new);
			resultMap.put("Array", (Integer[]) sortedResult);
		} catch (Exception exception) {
			LOG.error("Error Message", exception.getMessage());
			throw new MakeArrayException(exception.getMessage());
		}
		for (Integer integer : sortedResult) {
			LOG.info("Sorted Result: {}", integer);
		}
		LOG.info("STARTS - process");
		return resultMap;
	}

}
