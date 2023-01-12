
package com.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.model.PosterCommentsCountDTO;
import com.model.PosterCommentsDTO;

@Repository
public class PosterCommentsDao {

	private static final Logger logger = LoggerFactory.getLogger(PosterCommentsDao.class);

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<PosterCommentsCountDTO> getPosterCommentsCount() {
		return jdbcTemplate.query("SELECT u.username AS poster, count(pc.email) as comments"
				+ " FROM post_comments pc"
				+ " JOIN posts p ON pc.postId = p.id"
				+ " JOIN users u ON u.id = p.userId WHERE pc.email IN (SELECT email FROM users)"
				+ " GROUP BY poster ORDER BY comments",
				new MapSqlParameterSource(), new BeanPropertyRowMapper<PosterCommentsCountDTO>(PosterCommentsCountDTO.class));		
	}
	
	public List<PosterCommentsDTO> getFrequentPosterComments() {
		return jdbcTemplate.query("SELECT c.frequent_commenter,"
				+ " u.NAME                                   AS poster_name,"
				+ " p.id                                     AS post_id,"
				+ " St_distance_sphere(Point(u.address__geo__lng, u.address__geo__lat),"
				+ " Point(c.commenter_lng, c.commenter_lat)) * .000621371192 AS distance_from_poster"
				+ " FROM   post_comments pc"
				+ "  JOIN (SELECT iq.email            AS commenter_email,"
				+ " u.username          AS frequent_commenter,"
				+ " u.address__geo__lat AS commenter_lat,"
				+ " u.address__geo__lng AS commenter_lng"
				+ " FROM   (SELECT email,"
				+ " Count(email) AS total_post"
				+ " FROM   post_comments"
				+ " GROUP  BY email"
				+ " HAVING Count(email) >= 3) iq"
				+ " LEFT JOIN users u"
				+ " ON iq.email = u.email"
				+ " WHERE  u.id IS NOT NULL) c"
				+ " ON pc.email = c.commenter_email"
				+ " JOIN posts p"
				+ " ON p.id = pc.postid"
				+ " JOIN programming_assignment_db.users u"
				+ " ON u.id = p.userid"
				+ " ORDER  BY 2",
				new MapSqlParameterSource(), new BeanPropertyRowMapper<PosterCommentsDTO>(PosterCommentsDTO.class));		
	}
}