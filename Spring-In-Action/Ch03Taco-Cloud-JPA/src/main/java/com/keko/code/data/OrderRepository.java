package com.keko.code.data;

import org.springframework.data.repository.CrudRepository;

import com.keko.code.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
