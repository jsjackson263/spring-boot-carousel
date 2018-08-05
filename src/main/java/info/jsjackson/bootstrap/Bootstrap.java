/**
 * 
 */
package info.jsjackson.bootstrap;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import info.jsjackson.domain.Picture;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jsjackson
 *
 */
@Slf4j
@Component
public class Bootstrap implements CommandLineRunner, ServletContextAware {
	
	/*
	 * On start-up, load the images from specified location, 
	 * and store the list in a context attribute
	 */
	private ServletContext servletContext;
	public static final String PATH = "static/images/samples/";
	
	
	@Override
	public void run(String... args) throws Exception {
		loadFiles();
	}

	private void loadFiles() {

		List<Picture> fileList = new ArrayList<>();
		
		File[] directoryListing = getFilesUsingContextClassLoader(PATH);
		
		//loop through all the files in the directory, and load them into a list 
		for (File file : directoryListing) {
			if (!file.isDirectory()) {
				//add the file to the list
				fileList.add(Picture
						.builder()
						.fileName(file.getName())
						.url("/api/image/".concat(file.getName()))
						.build());
					
				log.info("File: '" + file.getName() + "' added to list");	
			}
				
		}
		
		log.info("File Count: " + fileList.size()); 
		//store file list in servlet context
		servletContext.setAttribute("fileList", fileList);
	}

	
	
	private File[] getFilesUsingContextClassLoader(String folder) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
	    URL url = loader.getResource(folder);
	    String path = url.getPath();
	    return new File(path).listFiles();
	}
	
	
	private List<String> getFilesUsingSystemClassLoader(String folder) {
		
		List<String> fileList = new ArrayList<>();
		
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		URL[] urls = ((URLClassLoader)cl).getURLs();
		for(URL url: urls){
			System.out.println(url.getFile());
			fileList.add(url.getFile());
		}
		
		return fileList;
	}
	
	
	 private static File[] getResourceFolderFiles (String folder) {
		    ClassLoader loader = Thread.currentThread().getContextClassLoader();
		    URL url = loader.getResource(folder);
		    String path = url.getPath();
		    return new File(path).listFiles();
	 }
	

	@Override
	public void setServletContext(ServletContext servletContext) {

		this.servletContext = servletContext;
		
	}
	
}
