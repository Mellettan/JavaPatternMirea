package ru.mirea.pract_17.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.mirea.pract_17.model.Market;
import ru.mirea.pract_17.model.Product;
import ru.mirea.pract_17.repository.MarketRepository;

import java.util.List;

@Service
public class MarketService implements MarketServiceInterface {
    private final MarketRepository marketRepository;
    private final EntityManager entityManager;

    public MarketService(MarketRepository marketRepository, EntityManager entityManager) {
        this.marketRepository = marketRepository;
        this.entityManager = entityManager;
    }

    public List<Market> filterMarkets(String name, String address) {
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

    @Transactional
    public Product getProductByMarketId(int marketId) {
        var market = marketRepository.findById(marketId).orElseThrow();
        return market.getProduct();
    }
}
