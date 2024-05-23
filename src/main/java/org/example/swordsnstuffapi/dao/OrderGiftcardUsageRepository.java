package org.example.swordsnstuffapi.dao;

import org.example.swordsnstuffapi.models.OrderGiftcardUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderGiftcardUsageRepository extends JpaRepository<OrderGiftcardUsage, Long> {
    @Query("SELECT o.order.id FROM OrderGiftcardUsage o WHERE o.giftcard.giftcard_id = :giftcard_id")
    List<Long> findOrderIdByGiftcardId(Long giftcard_id);
}
