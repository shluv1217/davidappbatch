package com.david.davidappbatch;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Person process(final Person person) throws Exception {
    	
    	ClassLoader classLoader = getClass().getClassLoader();
    	//File file = new File(classLoader.getResource("/images/jsonschema.json").getFile());
    	
    	File[] files = null;
    	
    	Enumeration<URL> en=classLoader.getResources("images");
    	if (en.hasMoreElements()) {
    	    URL metaInf=en.nextElement();
    	    File fileMetaInf=new File(metaInf.toURI());
    	    files = fileMetaInf.listFiles();

    	}
    	
	    for(File f:files){
			String fileName = f.getName();
			System.out.println(fileName);
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
