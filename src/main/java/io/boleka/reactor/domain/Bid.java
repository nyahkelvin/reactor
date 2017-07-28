/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.boleka.reactor.domain;

import java.util.Date;

/**
 *
 * @author kelvinashu
 */
public class Bid {

    private String bidder;
    private String bidder_avatar;
    private Date bid_date;
    private Double bid_amount;
    private Double bid_interest;
    private String bid_rating;

    public String getBidder() {
        return bidder;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public String getBidder_avatar() {
        return bidder_avatar;
    }

    public void setBidder_avatar(String bidder_avatar) {
        this.bidder_avatar = bidder_avatar;
    }

    public Date getBid_date() {
        return bid_date;
    }

    public void setBid_date(Date bid_date) {
        this.bid_date = bid_date;
    }

    public Double getBid_amount() {
        return bid_amount;
    }

    public void setBid_amount(Double bid_amount) {
        this.bid_amount = bid_amount;
    }

    public Double getBid_interest() {
        return bid_interest;
    }

    public void setBid_interest(Double bid_interest) {
        this.bid_interest = bid_interest;
    }

    public String getBid_rating() {
        return bid_rating;
    }

    public void setBid_rating(String bid_rating) {
        this.bid_rating = bid_rating;
    }

    @Override
    public String toString() {
        return "Bid{" + "bidder=" + bidder + ", bidder_avatar=" + bidder_avatar + ", bid_date=" + bid_date + ", bid_amount=" + bid_amount + ", bid_interest=" + bid_interest + ", bid_rating=" + bid_rating + '}';
    }

}
