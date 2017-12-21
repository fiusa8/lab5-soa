package soa.web;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.HashMap;


@Controller
public class SearchController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping(value="/search")
    @ResponseBody
    public Object search(@RequestParam("q") String q) {


	Map<String, Object> headers = new HashMap<>(); 
	String key;

	if (q.contains("max:")){
		String[] q2 = q.split("max:");
		String max = q2[1];
		key = q2[0];
		headers.put("CamelTwitterCount", max);	
	}
	else{
		key = q;
	}

	headers.put("CamelTwitterKeywords", key);
	
	
        return producerTemplate.requestBodyAndHeader("direct:search", "", "CamelTwitterKeywords", h);
    }
}
