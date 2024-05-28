package ru.mirea.pract_24;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.mirea.pract_24.model.Market;
import ru.mirea.pract_24.model.Product;
import ru.mirea.pract_24.service.BackupService;
import ru.mirea.pract_24.service.MarketService;
import ru.mirea.pract_24.service.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class BackupServiceTest {

    @Mock
    private ProductService productService;

    @Mock
    private MarketService marketService;

    @InjectMocks
    private BackupService backupService;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        // Create a backup directory for testing
        Files.createDirectories(Paths.get(System.getProperty("user.dir"), "backup"));
    }

    @Test
    public void testMakeProductsBackup() throws IOException {
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Product1");
        product1.setPrice(10.0);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Product2");
        product2.setPrice(20.0);

        List<Product> products = Arrays.asList(product1, product2);

        when(productService.readAll()).thenReturn(products);

        backupService.makeBackup();

        Path productFilePath = Paths.get(System.getProperty("user.dir"), "backup").resolve("products.txt");
        assertTrue(Files.exists(productFilePath));

        List<String> productLines = Files.readAllLines(productFilePath);
        assertEquals("1 Product1 10,000000", productLines.get(0));
        assertEquals("2 Product2 20,000000", productLines.get(1));

        verify(productService, times(1)).readAll();
    }

    @Test
    public void testMakeMarketsBackup() throws IOException {
        Market market1 = new Market();
        market1.setId(1);
        market1.setName("Market1");
        market1.setAddress("Address1");

        Market market2 = new Market();
        market2.setId(2);
        market2.setName("Market2");
        market2.setAddress("Address2");

        List<Market> markets = Arrays.asList(market1, market2);

        when(marketService.readAll()).thenReturn(markets);

        backupService.makeBackup();

        Path marketFilePath = Paths.get(System.getProperty("user.dir"), "backup").resolve("markets.txt");
        assertTrue(Files.exists(marketFilePath));

        List<String> marketLines = Files.readAllLines(marketFilePath);
        assertEquals("1 Market1 Address1", marketLines.get(0));
        assertEquals("2 Market2 Address2", marketLines.get(1));

        verify(marketService, times(1)).readAll();
    }
}

