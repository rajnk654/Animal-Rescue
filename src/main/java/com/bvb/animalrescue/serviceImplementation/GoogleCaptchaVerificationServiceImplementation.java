package com.bvb.animalrescue.serviceImplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.bvb.animalrescue.dto.GoogleRecaptchaResponse;
import com.bvb.animalrescue.service.GoogleCaptchaVerificationService;

@Service
public class GoogleCaptchaVerificationServiceImplementation implements GoogleCaptchaVerificationService {

	Logger logger = LoggerFactory.getLogger(GoogleCaptchaVerificationServiceImplementation.class);

	@Override
	public boolean isvalidCaptcha(String token) {

		boolean valid = false;
		if (StringUtils.isNotBlank(token)) {

			try {
				String recaptchaUrl = "https://www.google.com/recaptcha/api/siteverify";
				String secret = "6LfuuD4qAAAAAGuEQVscvUXVO62EIbWWOUzvP6H7";
				String url = recaptchaUrl + "?secret=" + secret + "&response=" + token;

				RestTemplate restTemplate = new RestTemplate();

				GoogleRecaptchaResponse googleResponse = restTemplate.getForObject(url, GoogleRecaptchaResponse.class);
//				System.out.println(""+googleResponse);
				valid = googleResponse.isSuccess();
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage());
			}
		}
		return valid;
	}

}