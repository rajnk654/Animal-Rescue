package com.bvb.animalrescue.serviceImplementation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvb.animalrescue.dao.ContactRepository;
import com.bvb.animalrescue.dto.ContactUsDto;
import com.bvb.animalrescue.model.ContactUs;
import com.bvb.animalrescue.service.ContactService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ContactServiceImplementation implements ContactService {

	Logger logger = LoggerFactory.getLogger(ContactServiceImplementation.class);

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public void saveContactDetails(ContactUsDto contactUsDto) {
		try {
			ContactUs contactUs = new ContactUs();
			BeanUtils.copyProperties(contactUsDto, contactUs);

			contactRepository.save(contactUs);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
	}

}