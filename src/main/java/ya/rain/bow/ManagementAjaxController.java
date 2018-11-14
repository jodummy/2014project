package ya.rain.bow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ya.rain.bow.model.service.ManagementService;

@Controller
public class ManagementAjaxController {
	
	private Logger logger = LoggerFactory.getLogger(ManagementAjaxController.class);
	
	@Autowired
	private ManagementService managementService;
	
	
	
}
