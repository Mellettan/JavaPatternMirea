package ru.mirea.pract_15.service;

import org.springframework.stereotype.Service;
import ru.mirea.pract_15.model.Market;
import ru.mirea.pract_15.repository.MarketRepository;

import java.util.List;

@Service
public class MarketService implements MarketServiceInterface {
    private final MarketRepository marketRepository;

    public MarketService(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }


    @Override
    public void create(Market market) {
        marketRepository.save(market);
    }

    @Override
    public List<Market> readAll() {
        return marketRepository.findAll();
    }

    @Override
    public Market read(int id) {
        return marketRepository.getReferenceById(id);
    }

    @Override
    public boolean update(Market market, int id) {
        if (marketRepository.existsById(id)) {
            market.setId(id);  // noqa
            marketRepository.save(market);
            return  true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        if (marketRepository.existsById(id)) {
            marketRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
