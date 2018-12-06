package util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapProcessor {
	
	RandomString rs = new RandomString();
	
	private Bitmap StringToBitMap(String encodedString){
		System.out.println(encodedString);
		   try {
		      byte [] encodeByte=Base64.decode(encodedString);
		      System.out.println("XXXXXX");
		      System.out.println(encodeByte);
		      Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
		      return bitmap;
		   } catch(Exception e) {
			      System.out.println("YYYYY");
		      e.getMessage();
		      return null;
		   }
		}
	
	private static byte[] decodeBase64(String input)
    {
        return Base64.decode(input);
    }
	
	private String downloadBitmap(Bitmap bmp) {
		System.out.println(bmp);
		String file_path = "output";
		File dir = new File(file_path);
		if(!dir.exists())
			dir.mkdirs();
		String name = rs.nextString();
		File file = new File(dir, name + ".png");
		FileOutputStream fOut;
		try {
			System.out.println(file);
			fOut = new FileOutputStream(file);
			bmp.compress(Bitmap.CompressFormat.PNG, 85, fOut);
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
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
	
	public String downloadImage(String bitmap) {
		return downloadBitmap(decodeBase64(bitmap));
	}
}
