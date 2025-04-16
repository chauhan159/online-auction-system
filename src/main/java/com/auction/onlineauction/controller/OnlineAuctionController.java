package com.auction.onlineauction.controller;

import com.auction.onlineauction.model.AuctionItem;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auctions")
public class OnlineAuctionController {

    private final Map<Long, AuctionItem> auctionDatabase = new HashMap<>();
    private long currentId = 1;

    @GetMapping
    public List<AuctionItem> getAllAuctions() {
        return new ArrayList<>(auctionDatabase.values());
    }

    @PostMapping
    public AuctionItem createAuction(@RequestBody AuctionItem item) {
        item.setId(currentId++);
        auctionDatabase.put(item.getId(), item);
        return item;
    }

    @PutMapping("/{id}")
    public AuctionItem updateAuction(@PathVariable Long id, @RequestBody AuctionItem updatedItem) {
        if (!auctionDatabase.containsKey(id)) {
            throw new NoSuchElementException("Auction item not found");
        }
        updatedItem.setId(id);
        auctionDatabase.put(id, updatedItem);
        return updatedItem;
    }

    @DeleteMapping("/{id}")
    public String deleteAuction(@PathVariable Long id) {
        if (!auctionDatabase.containsKey(id)) {
            throw new NoSuchElementException("Auction item not found");
        }
        auctionDatabase.remove(id);
        return "Auction item with ID " + id + " deleted.";
    }
}