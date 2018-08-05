/**
 * 
 */
package info.jsjackson.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import info.jsjackson.domain.Picture;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jsjackson
 *
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

	@Autowired
    private ServletContext servletContext;
	
	@Override
	public void saveImageFile(Long recipeId, MultipartFile file) {
		//future implementation

	}


	@Override
	public String[] getUrlArray() {

		List<Picture> fileList = (List<Picture>)servletContext.getAttribute("fileList");
		if (fileList != null) {
			log.info("*** In FileServiceImpl: File List: " + fileList.toString());
		}
		
		List<String> urlList = new ArrayList<>();
		for (Picture pic : fileList) {
			urlList.add(pic.getUrl());
		}
		
		String[] urls = new String[urlList.size()];
		urls = urlList.toArray(urls);

		for(String s : urls)
		    System.out.println("Item in url array: " + s);
		
		
 		return urls;
	}
	

	@Override
	public List<Picture> getPictureList() {
		
		List<Picture> fileList = (List<Picture>)servletContext.getAttribute("fileList");
		
		if (fileList != null) {
			log.info("*** In FileServiceImpl: File List: " + fileList.toString());
		}
		
		return fileList;
	}


	/*@Override
	public String getFileNameWithExtension(String fileName) {
		
		List<Picture> fileList = (List<Picture>)servletContext.getAttribute("fileList");
		fileList.forEach(picture -> {
			
			String fileNameWithoutExtension = StringUtils.substringBefore(picture.getFileName(), "."); 
			log.info("fileNameWithoutExtension : " + fileNameWithoutExtension);
			if (fileNameWithoutExtension.equals(fileName)) {
			
				return picture.getFileName();
			}
		});
		return null;
	}
	 */
	
	
	/*
	 * This method tries to get around the fact that in the controller, the endpoint "/api/image/{imageUrl}" 
	 * does not have the file extension, even though the full file name was part of the url passed in the model.
	 * This is causing the carousel photos not to load as expected - as the url won't match the expected resource.  
	 */
	@Override
	public String getFileNameWithExtension(String fileName) {
		
		List<Picture> pictureList = (List<Picture>)servletContext.getAttribute("fileList");
		for (Picture picture : pictureList) {
			
			// given "abc.jpg" returns "abc"
			String fileNameWithoutExtension = StringUtils.substringBefore(picture.getFileName(), ".");
			log.info("fileNameWithoutExtension : " + fileNameWithoutExtension);
			
			//compare the extension-less parts
			if (fileNameWithoutExtension.equals(fileName)) {
				//if equal, return the full file name with extension
				return picture.getFileName(); 
			}
		}
		return null;
	}
	
}
