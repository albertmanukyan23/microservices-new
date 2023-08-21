package com.example.orderservice.endpoint;

import com.example.orderservice.dto.OrderRequestDto;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Slf4j
public class OrderEndpoint {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
     log.info("placeorder worked");
        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequestDto));

    }

    public CompletableFuture<String> fallbackMethod(OrderRequestDto orderRequestDto, RuntimeException runtimeException) {
        return  CompletableFuture.supplyAsync(() -> "Oops something went wrong , try again") ;

    }
}
