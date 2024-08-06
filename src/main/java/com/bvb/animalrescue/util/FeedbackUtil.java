package com.bvb.animalrescue.util;

import org.springframework.beans.BeanUtils;

import com.bvb.animalrescue.dto.FeedbackDto;
import com.bvb.animalrescue.model.Feedback;

public class FeedbackUtil {
	public static FeedbackDto convertFeedbackEntityToDto(Feedback feedback) {
		FeedbackDto dto = new FeedbackDto();
		dto.setUserId(feedback.getUser().getId());
		BeanUtils.copyProperties(feedback, dto);
		return dto;
	}

	public static Feedback convertFeedbackDtoToEntity(FeedbackDto dto) {
		Feedback feedback = new Feedback();
		BeanUtils.copyProperties(dto, feedback);
		return feedback;
	}

}
