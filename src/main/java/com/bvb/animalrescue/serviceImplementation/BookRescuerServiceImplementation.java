package com.bvb.animalrescue.serviceImplementation;

import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bvb.animalrescue.dao.BookRescuerRepository;
import com.bvb.animalrescue.dao.RescuerRepository;
import com.bvb.animalrescue.dao.UserRepository;
import com.bvb.animalrescue.dto.BookRescuerDto;
import com.bvb.animalrescue.model.BookRescuer;
import com.bvb.animalrescue.model.Rescuer;
import com.bvb.animalrescue.model.User;
import com.bvb.animalrescue.service.BookRescuerService;
import com.bvb.animalrescue.util.BookRescuerUtil;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class BookRescuerServiceImplementation implements BookRescuerService {

	@Autowired
	private BookRescuerRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RescuerRepository rescuerRepository;

	@Value("${razorpay.key.id}")
	private String razorPayKey;

	@Value("${razorpay.secret.key}")
	private String razorPaySecret;

	// to communicate with razorPay- predefined

	private RazorpayClient client;

	@Override
	public BookRescuerDto createOrder(BookRescuerDto bookRescuerDto) throws Exception {

		Optional<User> userOpt = userRepository.findById(bookRescuerDto.getUserId());
		Optional<Rescuer> rescuerOpt = rescuerRepository.findById(bookRescuerDto.getRescuerId());

		if (!userOpt.isPresent()) {
			throw new Exception("User not found with ID: " + bookRescuerDto.getUserId());
		}

		if (!rescuerOpt.isPresent()) {
			throw new Exception("Rescuer not found with ID: " + bookRescuerDto.getRescuerId());
		}

		// Proceed with user and rescuer if present
		User user = userOpt.get();
		Rescuer rescuer = rescuerOpt.get();

		JSONObject orderReq = new JSONObject();
		orderReq.put("amount", bookRescuerDto.getAmount() * 100); // amount in paisa
		orderReq.put("currency", "INR");
		orderReq.put("receipt", user.getEmail());

		this.client = new RazorpayClient(razorPayKey, razorPaySecret);

		// create order in razorpay
		Order razorpayOrder = client.orders.create(orderReq);
		System.out.println(razorpayOrder);
		BookRescuer bookRescuer = BookRescuerUtil.convertRescuerDtoToEntity(bookRescuerDto);

		bookRescuer.setUser(user);
		bookRescuer.setRescuer(rescuer);
		bookRescuer.setRazorPayOrderID(razorpayOrder.get("id"));
		bookRescuer.setOrderStatus(razorpayOrder.get("status"));

		orderRepository.save(bookRescuer);

		bookRescuerDto = BookRescuerUtil.convertBookRescuerEntityToDto(bookRescuer);
		return bookRescuerDto;

	}

	public BookRescuer updateOrder(Map<String, String> responsePayLoad) {
		String razorPayOrderId = responsePayLoad.get("razorpay_order_id");
		BookRescuer order = orderRepository.findByRazorPayOrderID(razorPayOrderId);
		if (order == null) {
			System.err.println("Order not found for Razorpay Order ID: " + razorPayOrderId);
			throw new IllegalArgumentException("Order not found for Razorpay Order ID: " + razorPayOrderId);
		}

		order.setOrderStatus("PAYMENT_COMPLETED");
		BookRescuer updatedOrder = orderRepository.save(order);
		return updatedOrder;
	}

}
