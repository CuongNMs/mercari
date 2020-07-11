package com.cuongnm.mercari.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cuongnm.mercari.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{

}
