/**
 * 
 */
package org.telstra.makearray.srvc.rest.controllers;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telstra.makearray.srvc.rest.exceptions.MakeArrayException;
import org.telstra.makearray.srvc.rest.services.MakeArrayService;

/**
 * @author Sarin
 *
 */
@RestController
public class MakeArrayController {

	private static final Logger LOG = LoggerFactory.getLogger(MakeArrayController.class);

	@Autowired
	private MakeArrayService makeArrayService;

	/**
	 * This method used to used to generate and sort one array from multiple arrays
	 * 
	 * @param requestData
	 * @return ResponseEntity<Map<String, List<Integer>>>
	 * @throws MakeArrayException
	 */
	@PostMapping("/api/makeonearray")
	public ResponseEntity<Map<String, Integer[]>> generateOneArray(@RequestBody Map<String, Integer[]> requestData)
			throws MakeArrayException {
		LOG.debug("STARTS - generateOneArray");
		LOG.info("Request Data");
		for (Entry<String, Integer[]> entry : requestData.entrySet()) {
			if(null!= entry.getKey() && null!= entry.getValue()) {
				LOG.info("key: {}", entry.getKey());
				for (Integer value : entry.getValue()) {
					LOG.info("value: {}", value);
				}
			}
		}
		Map<String, Integer[]> resultMap = makeArrayService.process(requestData);
		LOG.debug("ENDS - generateOneArray");
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(resultMap);

	}

}
