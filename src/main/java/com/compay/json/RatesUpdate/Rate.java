package com.compay.json.RatesUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

public class Rate {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private ArrayList<Scale> scale;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private double mainRate;


    /*
                    value: 'livingArea * mainRate',
                    view: '[Жилая площадь] x [Тариф]',
                    attrs:{
                        mainRate:{
                            view:'Тариф',
                            value:6
                            },
                        livingArea:{
                            view:'Жилая площадь',
                            value:40
                            }
     */

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String value;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String view;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Attrs attrs;


    public double getMainRate() {
        return mainRate;
    }

    public String getValue() {
        return value;
    }

    public String getView() {
        return view;
    }

    public Attrs getAttrs() {
        return attrs;
    }

    public ArrayList<Scale> getScale() {
        return scale;
    }
}
