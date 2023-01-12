/**
 * 
 */
package com;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {

	@Bean
	public RestTemplate restTemplate() throws GeneralSecurityException, IOException {
		RestTemplate template = new RestTemplate();
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
		template.setMessageConverters(Arrays.asList(mappingJackson2HttpMessageConverter,
				new StringHttpMessageConverter(StandardCharsets.UTF_8)));
		return template;
	}
}
