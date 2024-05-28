package ru.mirea.pract_22.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.pract_22.model.Market;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
