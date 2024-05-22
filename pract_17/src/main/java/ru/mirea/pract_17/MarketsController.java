package ru.mirea.pract_17;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.pract_17.service.MarketService;
import ru.mirea.pract_17.model.Market;

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

    @GetMapping("/markets/{id}/products")
    public @ResponseBody ResponseEntity getProductByMarket(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(marketService.getProductByMarketId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/markets/filter")
    public @ResponseBody ResponseEntity filterMarkets(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(marketService.filterMarkets(name, address));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
