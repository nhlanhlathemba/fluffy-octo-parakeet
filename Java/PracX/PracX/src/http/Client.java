package http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Base64;

import javafx.scene.image.Image;

public class Client  {
	public boolean isConn=false;
	Socket s = null;
	//inputStreams
	InputStream is = null; 
	BufferedReader br = null; 
	//outputStreams
	OutputStream os = null;
	BufferedOutputStream bos = null; 
	DataOutputStream dos = null;
	private String grayURL = "/api/GrayScale";
	private String rotateURL = "/api/Rotate";
	private String erosionURL = "/api/Erosion";
	private String dilationURL = "/api/Dilation";
	private String cropURL = "/api/Crop";
	private String cannyURL = "/api/Canny";
	private String fastURL = "/api/Fast";
	private String orbURL = "/api/ORB";
	
 
public Client() throws UnknownHostException, IOException {				
			connect();
			
  }

 public void connect() throws UnknownHostException, IOException {
	 s = new Socket("localhost",5000); 
		isConn = true;
		is = s.getInputStream(); 
		br = new BufferedReader(new InputStreamReader(is));
		os = s.getOutputStream(); 
		bos = new BufferedOutputStream(os); 
		dos = new DataOutputStream(bos); 
		//System.out.println("Client connected to the server\r\n"); 
		//bind streams 
 }
  
  public void disconnect() {
	  try {
		s.close();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
  }

 
public Image preprocessRequest(File file,String type) throws IOException {
	  String encodedFile = null;
	  String URL = "";
	  switch(type) {
	  case "GREYSCALE":
		  URL = grayURL;
		  break;
	  case "ROTATE":
		  URL = rotateURL;
		  break;
	  case "EROSION":
		  URL = erosionURL;
		  break;
	  case "DILATION":
		  URL = dilationURL;
		  break;
	  case "CROP":
		  URL = cropURL;
		  break;
			  
	  }
	   //DOS(BOS(OS)) 
		  //read the File into a FileInputStream
		  FileInputStream fileInputStreamReader=null;
		
			fileInputStreamReader = new FileInputStream(file);
			
		  byte[] bytes = new byte[(int)file.length()];
		  
			fileInputStreamReader.read(bytes);
	        
		  //Encode the bytes into a base64 format string
		  encodedFile = new String(Base64.getEncoder().encodeToString(bytes)); 
		  //get the bytes of this encoded  string
		  byte[] bytesToSend = encodedFile.getBytes();
		//Construct a POST HTTP REQUEST
		  dos.write(("POST " + URL +" HTTP/1.1\r\n").getBytes());
		  dos.write(("Content-Type: " +"application/text\r\n").getBytes());
		  dos.write(("Content-Length: " + encodedFile.length() +"\r\n").getBytes());
		  dos.write(("\r\n").getBytes());
		  dos.write(bytesToSend);
		  dos.write(("\r\n").getBytes());
		  dos.flush();
		
		  String response = "";
		  String line = "";
		  while(!(line = br.readLine()).equals("")) 
		  { response += line +"\n"; } 
		  System.out.println(response);
		  String imgData = "";
		  while((line = br.readLine())!=null) 
		  {
			  imgData += line; 
		  } 
		  String base64Str = imgData.substring(imgData.indexOf('\'')+1,imgData.lastIndexOf('}')-1);
		  byte[] decodedString = Base64.getDecoder().decode(base64Str);
		  Image grayImg = new Image(new ByteArrayInputStream(decodedString));
		  fileInputStreamReader.close();
		  return grayImg;

  }

  
  public Image featureExtraction(File file,String type) throws IOException {
	  String encodedFile = null;
	  String URL = "";
	  switch(type) {
	  case "CANNY":
		  URL = cannyURL;
		  break;
	  case "FAST":
		  URL = fastURL;
		  break;
	  case "ORB":
		  URL = orbURL;
		  break;
			  
	  }
	   //DOS(BOS(OS)) 
		  //read the File into a FileInputStream
		  FileInputStream fileInputStreamReader=null;
		
			fileInputStreamReader = new FileInputStream(file);
			
		  byte[] bytes = new byte[(int)file.length()];
		  
			fileInputStreamReader.read(bytes);
	
		  //Encode the bytes into a base64 format string
		  encodedFile = new String(Base64.getEncoder().encodeToString(bytes)); 
		  //get the bytes of this encoded  string
		  byte[] bytesToSend = encodedFile.getBytes();
		//Construct a POST HTTP REQUEST
		  dos.write(("POST " + URL +" HTTP/1.1\r\n").getBytes());
		  dos.write(("Content-Type: " +"application/text\r\n").getBytes());
		  dos.write(("Content-Length: " + encodedFile.length() +"\r\n").getBytes());
		  dos.write(("\r\n").getBytes());
		  dos.write(bytesToSend);
		  dos.write(("\r\n").getBytes());
		  dos.flush();
		
		  String response = "";
		  String line = "";
		  while(!(line = br.readLine()).equals("")) 
		  { response += line +"\n"; } 
		  System.out.println(response);
		  String imgData = "";
		  while((line = br.readLine())!=null) 
		  {
			  imgData += line; 
		  } 
		  String base64Str = imgData.substring(imgData.indexOf('\'')+1,imgData.lastIndexOf('}')-1);
		  byte[] decodedString = Base64.getDecoder().decode(base64Str);
		  Image grayImg = new Image(new ByteArrayInputStream(decodedString));
		  fileInputStreamReader.close();
		  return grayImg;
  }

  public String jsonExtraction() {
	  return null;
  }

}
