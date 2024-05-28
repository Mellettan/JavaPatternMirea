package ru.mirea.pract_23.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.mirea.pract_23.model.Market;
import ru.mirea.pract_23.model.Product;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BackupService {
    @Autowired
    private ProductService productService;
    @Autowired
    private MarketService marketService;

    private static final Logger log = LoggerFactory.getLogger(BackupService.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static final Path BACKUP_DIRECTORY = Paths.get(System.getProperty("user.dir"), "backup");

    private void createBackupDirectory() throws IOException {
        if (!Files.exists(BACKUP_DIRECTORY)) {
            Files.createDirectories(BACKUP_DIRECTORY);
        }
    }

    private void makeProductsBackup() {
        List<Product> products = productService.readAll();
        try {
            createBackupDirectory();
            RandomAccessFile writer = new RandomAccessFile(BACKUP_DIRECTORY.resolve("products.txt").toFile(), "rw");
            writer.setLength(0);
            for (Product product : products) {
                String productStr = String.format("%d %s %f\n",
                        product.getId(),
                        product.getName(),
                        product.getPrice());
                writer.write(productStr.getBytes());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void makeMarketsBackup() {
        List<Market> markets = marketService.readAll();
        try {
            createBackupDirectory();
            RandomAccessFile writer = new RandomAccessFile(BACKUP_DIRECTORY.resolve("markets.txt").toFile(), "rw");
            writer.setLength(0);
            for (Market market : markets) {
                String marketStr = String.format("%d %s %s\n",
                        market.getId(),
                        market.getName(),
                        market.getAddress());
                writer.write(marketStr.getBytes());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(fixedRate = 10000000)  // the interval was set to 10000 seconds
    public void makeBackup() {
        makeProductsBackup();
        makeMarketsBackup();
        log.info("Backups are made: {}", dateFormat.format(new Date()));
    }
}
