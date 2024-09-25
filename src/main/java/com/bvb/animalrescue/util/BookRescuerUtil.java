package com.bvb.animalrescue.util;

import org.springframework.beans.BeanUtils;

import com.bvb.animalrescue.dto.BookRescuerDto;
import com.bvb.animalrescue.model.BookRescuer;

public class BookRescuerUtil {

    // Convert BookRescuer entity to DTO
    public static BookRescuerDto convertBookRescuerEntityToDto(BookRescuer bookRescuer) {
        BookRescuerDto dto = new BookRescuerDto();
        
        // Copy simple properties
        BeanUtils.copyProperties(bookRescuer, dto);
        
        return dto;
    }

    // Convert BookRescuerDto to BookRescuer entity
    public static BookRescuer convertRescuerDtoToEntity(BookRescuerDto dto) {
        BookRescuer bookRescuer = new BookRescuer();
        
        BeanUtils.copyProperties(dto, bookRescuer);

        return bookRescuer;
    }

}
