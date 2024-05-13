package org.example.swordsnstuffapi.dao;

import org.example.swordsnstuffapi.models.Giftcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GiftcardRepository extends JpaRepository<Giftcard, Long> {
    public boolean existsByCode(String code);

    public Optional<Giftcard> findByCode(String code);
}
