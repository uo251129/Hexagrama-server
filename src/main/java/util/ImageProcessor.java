package util;


import java.io.FileOutputStream;
import java.io.IOException;

public class ImageProcessor {
	
	RandomString rs = new RandomString();
	
	private static byte[] decodeBase64(String input)
    {
        return Base64.decode(input);
    }
	
	private String downloadBitmap(byte[] file) {
		String name = rs.nextString();
		try {
			FileOutputStream fos = new FileOutputStream("./data/"+name+".png");
	        fos.write(file);  
	        fos.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		  return name;
    }
	
	public String downloadImage(String image) {
		return downloadBitmap(decodeBase64(image));
	}
}
