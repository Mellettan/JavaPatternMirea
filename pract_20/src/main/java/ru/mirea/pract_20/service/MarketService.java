package ru.mirea.pract_20.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mirea.pract_20.model.Market;
import ru.mirea.pract_20.model.Product;
import ru.mirea.pract_20.repository.MarketRepository;

import java.util.List;

@Service
@Slf4j
public class MarketService implements MarketServiceInterface {
    private final MarketRepository marketRepository;
    private final EntityManager entityManager;

    public MarketService(MarketRepository marketRepository, EntityManager entityManager) {
        this.marketRepository = marketRepository;
        this.entityManager = entityManager;
    }

    public List<Market> filterMarkets(String name, String address) {
        log.info("Filtering markets: name={}, address={}", name, address);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Market> query = cb.createQuery(Market.class);
        Root<Market> market = query.from(Market.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
            predicates.add(cb.like(market.get("name"), "%" + name + "%"));
        }
        if (address != null) {
            predicates.add(cb.like(market.get("address"), "%" + address + "%"));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }


    @Override
    public void create(Market market) {
        log.info("Creating market: {}", market);
        marketRepository.save(market);
    }

    @Override
    public List<Market> readAll() {
        log.info("Reading all markets");
        return marketRepository.findAll();
    }

    @Override
    public Market read(int id) {
        log.info("Reading market with id={}", id);
        return marketRepository.getReferenceById(id);
    }

    @Override
    public boolean update(Market market, int id) {
        if (marketRepository.existsById(id)) {
            log.info("Updating market with id={}: {}", id, market);
            market.setId(id);  // noqa
            marketRepository.save(market);
            return  true;
        }
        log.info("Market with id={} not found", id);
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (marketRepository.existsById(id)) {
            log.info("Deleting market with id={}", id);
            marketRepository.deleteById(id);
            return true;
        }
        log.info("Market with id={} not found", id);
        return false;
    }

    @Transactional
    public Product getProductByMarketId(int marketId) {
        log.info("Getting product by market id={}", marketId);
        var market = marketRepository.findById(marketId).orElseThrow();
        return market.getProduct();
    }
}