/**
 * 
 */
package info.jsjackson.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author jsjackson
 *
 */
@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class Picture {

	private Long id;
	
	private String fileName;
	
	private String url;
	
	private Byte[] image;
	
	
}
