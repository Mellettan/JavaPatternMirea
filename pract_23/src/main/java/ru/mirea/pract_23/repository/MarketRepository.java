package ru.mirea.pract_23.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.pract_23.model.Market;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
