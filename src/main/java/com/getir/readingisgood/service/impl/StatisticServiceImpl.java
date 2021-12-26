package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.authentication.service.impl.UserDetailsImpl;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.model.dto.StatisticsDTO;
import com.getir.readingisgood.repository.OrderRepository;
import com.getir.readingisgood.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Service
@Transactional
public class StatisticServiceImpl implements StatisticService {

    private final OrderRepository orderRepository;

    public StatisticServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<StatisticsDTO> getMyStatistics() {
        List<StatisticsDTO> statisticsDTOList = new ArrayList<>();
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> orders = orderRepository.findAllByUserId(userDetails.getId());

        Map<Month, List<Order>> ordersByMonth = orders.stream().collect(groupingBy(order -> order.getOrderDate().getMonth()));

        ordersByMonth.forEach((key, value) -> {
            AtomicReference<BigDecimal> totalOrderAmount = new AtomicReference<>(BigDecimal.ZERO);
            AtomicReference<Long> totalBookCount = new AtomicReference<>(0L);
            value.forEach(order -> {
                totalOrderAmount.set(totalOrderAmount.get().add(order.getTotalPrice()));
                totalBookCount.set(totalBookCount.get() + order.getBookCount());
            });
            statisticsDTOList.add(StatisticsDTO.builder()
                    .month(key.name())
                    .totalOrderAmount(totalOrderAmount.get())
                    .totalBookCount(totalBookCount.get())
                    .totalOrderCount(value.size())
                    .userId(userDetails.getId())
                    .build());
        });

        return statisticsDTOList;
    }
}
