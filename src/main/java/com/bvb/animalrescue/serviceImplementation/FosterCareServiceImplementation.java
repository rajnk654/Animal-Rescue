package com.bvb.animalrescue.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvb.animalrescue.dao.AnimalRepository;
import com.bvb.animalrescue.dao.FosterCareRepository;
import com.bvb.animalrescue.dto.AnimalDto;
import com.bvb.animalrescue.dto.FosterCareDto;
import com.bvb.animalrescue.exception.AnimalRescueException;
import com.bvb.animalrescue.model.Animal;
import com.bvb.animalrescue.model.FosterCare;
import com.bvb.animalrescue.service.FosterCareService;
import com.bvb.animalrescue.util.AnimalUtil;
import com.bvb.animalrescue.util.FosterCareUtil;

import jakarta.transaction.Transactional;

@Service
public class FosterCareServiceImplementation implements FosterCareService {

	@Autowired
	private FosterCareRepository fosterCareRepository;
	
	@Autowired
	private AnimalRepository animalRepository;

	@Override

	// Adding New FosterCare
	public String addNewFosterCare(FosterCareDto fosterCareDto) throws AnimalRescueException {
		try {
			FosterCare fosterCare = FosterCareUtil.convertFosterCareDtoToEntity(fosterCareDto);
			if (fosterCare == null) {
				throw new Exception("Empty Data IS Not Allowed");
			}
			fosterCareRepository.save(fosterCare);
			fosterCareDto = FosterCareUtil.convertFosterCareEntityToDto(fosterCare);
			return "FosterCare' " + fosterCareDto.getId() + "' has Successfully inserted.";
		} catch (Exception exception) {
			throw new AnimalRescueException("Internal Server Error" + exception.getMessage());
		}
	}

	// Fetching The FosterCare

	public List<FosterCareDto> getFosterCare() throws AnimalRescueException {
		try {
			List<FosterCareDto> listOfDtos = fosterCareRepository.findAll().stream()
					.map(FosterCareUtil::convertFosterCareEntityToDto).collect(Collectors.toList());

			return listOfDtos;
		} catch (Exception exception) {
			throw new AnimalRescueException(exception.getMessage());
		}
	}

	// Updating The FosterCare

	@Transactional
	public String updateFosterCare(Integer fosterCareId, FosterCareDto dto) throws AnimalRescueException {
		FosterCare fosterCare = FosterCareUtil.convertFosterCareDtoToEntity(dto);
		FosterCare existingFosterCare = fosterCareRepository.findById(fosterCareId).get();

		if (existingFosterCare == null) {
			throw new AnimalRescueException("User not Found");
		}
		// Update PhoneNumber
		if (fosterCare.getPhoneNumber() != null && fosterCare.getPhoneNumber().length() > 0
				&& !Objects.equals(fosterCare.getPhoneNumber(), existingFosterCare.getPhoneNumber())) {
			existingFosterCare.setPhoneNumber(fosterCare.getPhoneNumber());
		}
		return "FosterCare updated Successfully";
	}
	
	@Override
    public List<AnimalDto> getAnimalsInFosterCare(Integer fosterCareId) throws AnimalRescueException {
        List<Animal> animals = animalRepository.findByFosterCareId(fosterCareId); // Custom query method
        if (animals == null || animals.isEmpty()) {
            throw new AnimalRescueException("No animals found for this foster care.");
        }

        List<AnimalDto> animalDtoList = new ArrayList<>();
        for (Animal animal : animals) {
            AnimalDto animalDto = AnimalUtil.convertAnimalsEntityToDto(animal);
            animalDtoList.add(animalDto);
        }

        return animalDtoList;
    }

	// Delete The FosterCare
	public String deleteFosterCare(Integer fosterCareId) throws AnimalRescueException {
		Optional<FosterCare> fosterCare = fosterCareRepository.findById(fosterCareId);
		try {
			if (fosterCare.isPresent()) {
				fosterCareRepository.deleteById(fosterCareId);
				String name = fosterCare.get().getFosterCareName();
				return "fosterCare " + name + " Has deleted successfully.";
			} else {
				throw new AnimalRescueException("Foster Care doesn't exist.");
			}
		} catch (Exception e) {
			throw new AnimalRescueException("Foster Care doesn't exist." + e.getLocalizedMessage());
		}
	}
}
