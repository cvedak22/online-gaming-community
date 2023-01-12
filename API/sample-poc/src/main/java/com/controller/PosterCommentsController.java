package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.PosterCommentsCountDTO;
import com.model.PosterCommentsDTO;
import com.service.PosterCommentsService;

@Controller
@CrossOrigin(origins = "*")
public class PosterCommentsController {

	@Autowired
	private PosterCommentsService service;

	@RequestMapping(value = "/getPosterCommentsCount", method = { RequestMethod.POST, RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<PosterCommentsCountDTO> getPosterCommentsCount() {
		return service.getPosterCommentsCount();
	}

	@RequestMapping(value = "/getFrequentPosterComments", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<PosterCommentsDTO> getFrequentPosterComments() {
		return service.getFrequentPosterComments();
	}
}
