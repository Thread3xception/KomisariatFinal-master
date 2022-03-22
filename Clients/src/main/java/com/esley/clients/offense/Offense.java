package com.esley.clients.offense;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Offense {
    private int id;
    private double priceFrom;
    private double priceTo;
    private String description;
    private int scoreFrom;
    private int scoreTo;
}
