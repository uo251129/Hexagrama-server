package api;

import java.util.Map;

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

   
    /**
    * Deprecated method. It will be removed in a near version.
    * Use /audiveris/raw or /audiveris/json instead.
    */
    @Deprecated
	@RequestMapping("/file")
    public String file(@RequestParam(value="name") String filename) {
    	if (filename==null) return "{\"error\": \"Filename must not be null\"}";
    	audiveris.convertImage(filename);
    	return "Imagen procesada satisfactoriamente";
    }
    
    @RequestMapping(
    		value = "/audiveris/raw",
    		method = RequestMethod.POST)
    public String audiveris(@RequestBody String image) {
    	if (image==null) return "{\"error\": \"Image must not be null\"}";
    	return audiveris.convertImage(btmProcessor.downloadImage(image));
    }
    
    @RequestMapping(
    		value = "/audiveris/json",
    		method = RequestMethod.POST)
    public String audiveris(@RequestBody Map<String, String> jsonInput) {
    	if (jsonInput==null) return "{\"error\": \"Image must not be null\"}";
    	String image = jsonInput.get("image");
    	if (image==null) return "{\"error\": \"Image must not be null\"}";
    	return audiveris.convertImage(btmProcessor.downloadImage(image));
    }
    
}
