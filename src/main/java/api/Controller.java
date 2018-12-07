package api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import audiveris.AudiverisAdapter;
import util.ImageProcessor;

@RestController
public class Controller {
	AudiverisAdapter audiveris = new AudiverisAdapter();
	ImageProcessor btmProcessor = new ImageProcessor();

   
    @RequestMapping("/file")
    public String file(@RequestParam(value="name") String filename) {
    	if (filename==null) return "{\"error\": \"Filename must not be null\"}";
    	audiveris.convertImage(filename);
    	return "Imagen procesada satisfactoriamente";
    }
    
    @RequestMapping(
    		value = "/audiveris",
    		method = RequestMethod.POST)
    public String audiveris(@RequestBody String image) {
    	if (image==null) return "{\"error\": \"Image must not be null\"}";
    	return audiveris.convertImage(btmProcessor.downloadImage(image));
    }
    
}
