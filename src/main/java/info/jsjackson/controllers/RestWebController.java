/**
 * 
 */
package info.jsjackson.controllers;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import info.jsjackson.bootstrap.Bootstrap;
import info.jsjackson.services.FileService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jsjackson
 *
 */
@Slf4j
@RestController
public class RestWebController {

	private FileService fileService;
	
	public RestWebController(FileService fileService) {
		this.fileService = fileService;
	}


	@GetMapping(value="/api/image/{imageUrl}")
	public ResponseEntity<InputStreamResource> getImage(@PathVariable("imageUrl") String imageUrl, Model model) throws IOException {
		
		ClassPathResource imageFile;
		
		String fileNameWithExtension = fileService.getFileNameWithExtension(imageUrl);
		imageFile = new ClassPathResource(Bootstrap.PATH.concat(fileNameWithExtension));
				
		return ResponseEntity
				.ok()
				.contentType(getMediaType(fileNameWithExtension))
				.body(new InputStreamResource(imageFile.getInputStream()));
		
		
	}
	
	private MediaType getMediaType(String fileNameWithExtension) {
		//infer media type from file extension
		MediaType mediaType = null;
		String extension = StringUtils.substringAfter(fileNameWithExtension, ".");
		log.info("Inferred File Extension: " + extension);
		switch (extension) {
		case "png":
			mediaType = MediaType.IMAGE_PNG;
			break;
		
		case "jpg":
			mediaType = MediaType.IMAGE_JPEG;
			break;
			
		default:
			mediaType = MediaType.IMAGE_JPEG;
		}
		
		return mediaType;
		
	}
	
}
