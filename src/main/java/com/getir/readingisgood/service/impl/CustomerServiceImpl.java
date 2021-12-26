package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.authentication.service.impl.UserDetailsImpl;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.model.dto.OrderDTO;
import com.getir.readingisgood.repository.OrderRepository;
import com.getir.readingisgood.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final OrderRepository orderRepository;

    public CustomerServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public PageImpl<OrderDTO> myOrders(int page, int pageSize) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<Order> orders = orderRepository.findAllByUserId(userDetails.getId(), PageRequest.of(page, pageSize));
            return new PageImpl<OrderDTO>(orders.get().map(Order::toDTO).collect(Collectors.toList()),
                    orders.getPageable(),
                    orders.getTotalElements());
    }
}
