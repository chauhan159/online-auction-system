package com.auction.onlineauction.model;

import lombok.Data;

@Data
public class AuctionItem {
    private Long id;
    private String name;
    private String description;
    private double startingPrice;
}