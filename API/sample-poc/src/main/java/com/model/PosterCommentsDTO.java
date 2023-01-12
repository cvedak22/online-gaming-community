/**
 * 
 */
package com.model;

import lombok.Data;

@Data
public class PosterCommentsDTO {

	private Long postId;
	private String frequentCommenter;
	private String posterName;
	private Double distanceFromPoster;	 
}
