package ru.mirea.pract_15.service;

import ru.mirea.pract_15.model.Market;

import java.util.List;

public interface MarketServiceInterface {
    void create(Market market);
    List<Market> readAll();
    Market read(int id);
    boolean update(Market market, int id);
    boolean delete(int id);
}
