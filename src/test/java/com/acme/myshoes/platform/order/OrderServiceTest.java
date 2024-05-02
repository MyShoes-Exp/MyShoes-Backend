package com.acme.myshoes.platform.order;

import com.acme.myshoes.platform.authentication.domain.model.User;
import com.acme.myshoes.platform.authentication.resource.UserResource;
import com.acme.myshoes.platform.orders.api.rest.OrdersController;
import com.acme.myshoes.platform.orders.domain.model.Order;
import com.acme.myshoes.platform.orders.domain.persistence.OrderRepository;
import com.acme.myshoes.platform.orders.mapping.OrderMapper;
import com.acme.myshoes.platform.orders.resource.OrderResource;
import com.acme.myshoes.platform.orders.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class OrderServiceTest {

    @InjectMocks
    private OrdersController orderController;

    @Mock
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks antes de cada test
    }

    @Test
    public void testGetAllOrders() {
        // Simular datos
        List<Order> orders = Arrays.asList(new Order(), new Order());
        when(orderRepository.findAll()).thenReturn(orders);

        // Llamar al método y verificar el resultado
        List<Order> result = orderService.getAll();
        assertEquals(2, result.size());
    }
    @Test
    public void testGetOrderById() {
        // Mock data
        long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);
        order.setType_shoes("Test Type Shoes");

        // Simulate OrderService behavior
        when(orderService.getById(orderId)).thenReturn(order);

        OrderResource expectedOrderResource = new OrderResource();
        expectedOrderResource.setId(orderId);
        expectedOrderResource.setType_shoes(order.getType_shoes());

        // Simulate OrderMapper behavior
        when(orderMapper.toResource(order)).thenReturn(expectedOrderResource);

        // Test
        OrderResource actualOrderResource = orderController.getOrderById(orderId);

        // Assertions
        assertEquals(expectedOrderResource, actualOrderResource);
    }

}