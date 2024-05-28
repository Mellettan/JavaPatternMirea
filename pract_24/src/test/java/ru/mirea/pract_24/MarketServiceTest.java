package ru.mirea.pract_24;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.mirea.pract_24.model.Market;
import ru.mirea.pract_24.model.Product;
import ru.mirea.pract_24.repository.MarketRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import ru.mirea.pract_24.service.MarketService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class MarketServiceTest {

    @Mock
    private MarketRepository marketRepository;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private MarketService marketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMarket() {
        Market market = new Market();
        market.setName("Test Market");

        marketService.create(market);

        verify(marketRepository, times(1)).save(market);
    }

    @Test
    public void testReadAllMarkets() {
        List<Market> markets = new ArrayList<>();
        markets.add(new Market());
        when(marketRepository.findAll()).thenReturn(markets);

        List<Market> result = marketService.readAll();

        assertEquals(1, result.size());
        verify(marketRepository, times(1)).findAll();
    }

    @Test
    public void testReadMarketById() {
        Market market = new Market();
        when(marketRepository.getReferenceById(anyInt())).thenReturn(market);

        Market result = marketService.read(1);

        assertNotNull(result);
        verify(marketRepository, times(1)).getReferenceById(1);
    }

    @Test
    public void testUpdateMarket() {
        Market market = new Market();
        when(marketRepository.existsById(anyInt())).thenReturn(true);

        boolean result = marketService.update(market, 1);

        assertTrue(result);
        verify(marketRepository, times(1)).save(market);
    }

    @Test
    public void testUpdateMarketNotFound() {
        Market market = new Market();
        when(marketRepository.existsById(anyInt())).thenReturn(false);

        boolean result = marketService.update(market, 1);

        assertFalse(result);
        verify(marketRepository, times(0)).save(market);
    }

    @Test
    public void testDeleteMarket() {
        when(marketRepository.existsById(anyInt())).thenReturn(true);

        boolean result = marketService.delete(1);

        assertTrue(result);
        verify(marketRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteMarketNotFound() {
        when(marketRepository.existsById(anyInt())).thenReturn(false);

        boolean result = marketService.delete(1);

        assertFalse(result);
        verify(marketRepository, times(0)).deleteById(1);
    }

    @Test
    public void testFilterMarkets() {
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        CriteriaQuery<Market> query = mock(CriteriaQuery.class);
        Root<Market> root = mock(Root.class);
        TypedQuery<Market> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createQuery(Market.class)).thenReturn(query);
        when(query.from(Market.class)).thenReturn(root);
        when(entityManager.createQuery(query)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(new ArrayList<>());

        List<Market> result = marketService.filterMarkets("Test", "Address");

        assertNotNull(result);
        verify(entityManager, times(1)).createQuery(query);
        verify(typedQuery, times(1)).getResultList();
    }

    @Test
    public void testGetProductByMarketId() {
        Market market = new Market();
        Product product = new Product();
        market.setProduct(product);

        when(marketRepository.findById(anyInt())).thenReturn(Optional.of(market));

        Product result = marketService.getProductByMarketId(1);

        assertNotNull(result);
        assertEquals(product, result);
        verify(marketRepository, times(1)).findById(1);
    }

    @Test
    public void testGetProductByMarketIdNotFound() {
        when(marketRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> marketService.getProductByMarketId(1));
        verify(marketRepository, times(1)).findById(1);
    }
}
