package ru.mirea.pract_15;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.pract_15.service.MarketService;
import ru.mirea.pract_15.model.Market;

@Controller
public class MarketsController {
    @Autowired
    private MarketService marketService;

    @GetMapping(path = "/markets")
    public @ResponseBody ResponseEntity getMarkets() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(marketService.readAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(path = "/markets")
    public @ResponseBody ResponseEntity createMarket(Market market) {
        try {
            marketService.create(market);;
            return ResponseEntity.status(HttpStatus.OK).body("Market was created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/markets")
    public @ResponseBody ResponseEntity deleteOMarket(int id) {
        try {
            if (marketService.delete(id)) {
                return ResponseEntity.status(HttpStatus.OK).body("Market was deleted");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during deleting market");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
