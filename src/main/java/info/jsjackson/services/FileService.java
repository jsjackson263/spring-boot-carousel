/**
 * 
 */
package info.jsjackson.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import info.jsjackson.domain.Picture;

/**
 * @author jsjackson
 *
 */
public interface FileService {

	void saveImageFile(Long recipeId, MultipartFile file);
	
	List<Picture> getPictureList();
	
	String[] getUrlArray();
	
	String getFileNameWithExtension(String fileName);
	
}
