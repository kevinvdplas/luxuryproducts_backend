package org.example.swordsnstuffapi.dao;

import org.example.swordsnstuffapi.models.OrderGiftcardUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGiftcardUsageRepository extends JpaRepository<OrderGiftcardUsage, Long> {
}
