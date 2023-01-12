package com.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PosterCommentsDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.PosterCommentsCountDTO;
import com.model.PosterCommentsDTO;

@Service
public class PosterCommentsService {

	private static final Logger logger = LoggerFactory.getLogger(PosterCommentsService.class);

	@Autowired
	private PosterCommentsDao posterCommentsDao;

	public List<PosterCommentsCountDTO> getPosterCommentsCount() {
		return posterCommentsDao.getPosterCommentsCount();
	}

	public JSONArray getPosterCommentsCountArr() {
		List<PosterCommentsCountDTO> list = posterCommentsDao.getPosterCommentsCount();
		if (!list.isEmpty()) {
			Gson gson = new Gson();
			String listString = gson.toJson(list, new TypeToken<ArrayList<PosterCommentsCountDTO>>() {
			}.getType());
			return new JSONArray(listString);
		}
		return null;
	}

	public List<PosterCommentsDTO> getFrequentPosterComments() {
		return posterCommentsDao.getFrequentPosterComments();
	}

	public JSONArray getFrequentPosterCommentsArr() {
		List<PosterCommentsDTO> list = posterCommentsDao.getFrequentPosterComments();
		if (!list.isEmpty()) {
			Gson gson = new Gson();
			String listString = gson.toJson(list, new TypeToken<ArrayList<PosterCommentsDTO>>() {
			}.getType());
			return new JSONArray(listString);
		}
		return null;
	}
}