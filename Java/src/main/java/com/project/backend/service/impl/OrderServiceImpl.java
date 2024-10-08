package com.project.backend.service.impl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.backend.dto.OrderDto;
import com.project.backend.entity.KeyCode;
import com.project.backend.entity.Order;
import com.project.backend.entity.OrderDetail;
import com.project.backend.exception.ResourceNotFoundException;
import com.project.backend.mapper.OrderMapper;
import com.project.backend.repository.AccountRepository;
import com.project.backend.repository.GameRepository;
import com.project.backend.repository.KeycodeRepository;
import com.project.backend.repository.OrderDetailRepository;
import com.project.backend.repository.OrderRepository;
import com.project.backend.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private KeycodeRepository keyCodeRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public Order createOrder(Long accountId, Long gameId, Double price) {
        Order order = new Order();
        order.setAccount(accountRepository.findById(accountId).orElse(null));
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setPrice(price);
        orderDetail.setOrder(order);

        orderDetail.setGame(gameRepository.findById(gameId).orElse(null));

        KeyCode keyCode = new KeyCode();
        keyCode.generateKeyCode(); 
        keyCode.setGame(orderDetail.getGame());
        keyCode.setCreateDate(LocalDate.now());
        keyCode.setIsActive(true);

        keyCode.setOrderDetail(orderDetail);
        orderDetail.setKeycode(keyCode);
        keyCodeRepository.save(keyCode);
        orderDetailRepository.save(orderDetail);
        order.setOrderDetails(Collections.singletonList(orderDetail));
        return orderRepository.save(order);
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
        return OrderMapper.mapToOrderDto(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto UpdateOrder(Long orderId, OrderDto updatedOrderDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        // Cập nhật thông tin order
        order.setAccount(updatedOrderDto.getAccount());

        // Lưu lại và trả về order đã cập nhật
        Order updatedOrder = orderRepository.save(order);
        return OrderMapper.mapToOrderDto(updatedOrder);
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> GetAllOrderById(Long accountId) {
        // TODO Auto-generated method stub
        List<Order> orders = orderRepository.findListById(accountId);
        return orders.stream().map((order) -> OrderMapper.mapToOrderDto(order)).collect(Collectors.toList());
    }
    
//	@Override
//	public List<Object[]> getMonthlyStatistics(int year) {
//		return orderDetailRepository.getMonthlyStatistics(year);
//		 
//	}
	
    public Map<String, Double> getMonthlyStatistics(int year) {
        List<Object[]> results = orderDetailRepository.getMonthlyStatistics(year);
        Map<String, Double> statistics = new HashMap<>();

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        for (Object[] result : results) {
            int month = ((Number) result[0]).intValue();
            double totalPrice = ((Number) result[1]).doubleValue();
            statistics.put(months[month - 1], totalPrice);
        }

        return statistics;
    }
	
    

//	@Override
//	public List<Object[]> getMonthlyStatistics2024() {
//		  return orderDetailRepository.getMonthlyStatistics2024();
//	}
//    
	
}
