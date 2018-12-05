package api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import audiveris.AudiverisAdapter;

@RestController
public class Controller {
	AudiverisAdapter audiveris = new AudiverisAdapter();

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return "{\"content\": \"Hello, "+name+"\"}";
    }
    
    @RequestMapping("/file")
    public String file(@RequestParam(value="name") String filename) {
    	if (filename==null) return "{\"error\": \"Filename must not be null\"}";
    	audiveris.convertImage(filename);
    	return "Imagen procesada satisfactoriamente";
    }
}
