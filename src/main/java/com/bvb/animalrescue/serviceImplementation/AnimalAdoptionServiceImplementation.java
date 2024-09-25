package com.bvb.animalrescue.serviceImplementation;

import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bvb.animalrescue.dao.AnimalAdoptionRepository;
import com.bvb.animalrescue.dao.AnimalRepository;
import com.bvb.animalrescue.dao.UserRepository;
import com.bvb.animalrescue.dto.AnimalAdoptionDto;
import com.bvb.animalrescue.model.Animal;
import com.bvb.animalrescue.model.AnimalAdoption;
import com.bvb.animalrescue.model.User;
import com.bvb.animalrescue.service.AnimalAdoptionService;
import com.bvb.animalrescue.util.AnimalAdoptionUtil;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class AnimalAdoptionServiceImplementation implements AnimalAdoptionService {

	@Autowired
	private AnimalAdoptionRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AnimalRepository animalRepository;

	@Value("${razorpay.key.id}")
	private String razorPayKey;

	@Value("${razorpay.secret.key}")
	private String razorPaySecret;

	private RazorpayClient client;

	@Override
	public AnimalAdoptionDto createOrder(AnimalAdoptionDto animalAdoptionDto) throws Exception {

		Optional<User> userOpt = userRepository.findById(animalAdoptionDto.getUserId());
		Optional<Animal> animalOpt = animalRepository.findById(animalAdoptionDto.getAnimalId());

		if (!userOpt.isPresent()) {
			throw new Exception("User not found with ID: " + animalAdoptionDto.getId());
		}

		if (!animalOpt.isPresent()) {
			throw new Exception("Rescuer not found with ID: " + animalAdoptionDto.getId());
		}

		// Proceed with user and rescuer if present
		User user = userOpt.get();
		Animal animal = animalOpt.get();

		JSONObject orderReq = new JSONObject();
		orderReq.put("amount", animalAdoptionDto.getAmount() * 100); // amount in paisa
		orderReq.put("currency", "INR");
		orderReq.put("receipt", user.getEmail());

		this.client = new RazorpayClient(razorPayKey, razorPaySecret);

		// create order in razorpay
		Order razorpayOrder = client.orders.create(orderReq);
		System.out.println(razorpayOrder);
		AnimalAdoption animalAdoption = AnimalAdoptionUtil.convertAnimalAdoptionDtoToEntity(animalAdoptionDto);

		animalAdoption.setUser(user);
		animalAdoption.setAnimal(animal);
		animalAdoption.setRazorPayOrderID(razorpayOrder.get("id"));
		animalAdoption.setOrderStatus(razorpayOrder.get("status"));

		orderRepository.save(animalAdoption);

		animalAdoptionDto = AnimalAdoptionUtil.convertAnimalAdoptionEntityToDto(animalAdoption);
		return animalAdoptionDto;

	}

	public String updateOrder(Map<String, String> responsePayLoad) {
		String razorPayOrderId = responsePayLoad.get("razorpay_order_id");
		AnimalAdoption order = orderRepository.findByRazorPayOrderID(razorPayOrderId);
		if (order == null) {
			System.err.println("Order not found for Razorpay Order ID: " + razorPayOrderId);
			throw new IllegalArgumentException("Order not found for Razorpay Order ID: " + razorPayOrderId);
		}

		order.setOrderStatus("PAYMENT_COMPLETED");
		orderRepository.save(order);
		return "Animal Adopted Successfully";
	}

}
