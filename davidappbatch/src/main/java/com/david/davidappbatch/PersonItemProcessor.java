package com.david.davidappbatch;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.util.IOUtils;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Person process(final Person person) throws Exception {
    	
    	ClassLoader classLoader = getClass().getClassLoader();
    	//File file = new File(classLoader.getResource("/images/jsonschema.json").getFile());
    	
    	File[] files = null;
    	ByteBuffer imageBytes;
    	
    	Enumeration<URL> en=classLoader.getResources("images");
    	if (en.hasMoreElements()) {
    	    URL metaInf=en.nextElement();
    	    File fileMetaInf=new File(metaInf.toURI());
    	    files = fileMetaInf.listFiles();
    	}

    	
	    for(File f:files){
			//InputStream inputStream = new FileInputStream(f);
			try (InputStream inputStream = new FileInputStream(f)){
	            imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
	        }
			
			
			
	        AWSCredentials credentials = null;
	        
	   	 
	        try {
	            credentials = new ProfileCredentialsProvider("default").getCredentials();
	        } catch (Exception e) {
	            throw new AmazonClientException(
	                    "Cannot load the credentials from the credential profiles file. " +
	                    "Please make sure that your credentials file is at the correct " +
	                    "location (/Users/davidshin/.aws/credentials), and is in valid format.",
	                    e);
	        }  
	        
	        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_1)
	      		  .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
	  	  
	  	

	        DetectLabelsRequest request = new DetectLabelsRequest()
	                .withImage(new Image()
	                        .withBytes(imageBytes))
	                .withMaxLabels(10)
	                .withMinConfidence(77F);

	        try {

	            DetectLabelsResult result = rekognitionClient.detectLabels(request);
	            List <Label> labels = result.getLabels();

	            //System.out.println("Detected labels for " + photo);
	            for (Label label: labels) {
	               System.out.println(label.getName() + ": " + label.getConfidence().toString());
	            }

	        } catch (AmazonRekognitionException e) {
	            e.printStackTrace();
	        }

    	}
	    
	    
	    
	    
    	
    	
    	// initialize File object
    	//File file = new File(classLoader.getResources("images"));

		// check if the specified pathname is directory first
//		if(file.isDirectory()){
//			//list all files on directory
//			File[] files = file.listFiles();
//			for(File f:files){
//				try {
//					System.out.println(f.getCanonicalPath());
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
    	
    	
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();

        final Person transformedPerson = new Person(firstName, lastName);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }
}
