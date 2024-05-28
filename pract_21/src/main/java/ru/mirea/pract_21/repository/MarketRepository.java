package ru.mirea.pract_21.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.pract_21.model.Market;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
