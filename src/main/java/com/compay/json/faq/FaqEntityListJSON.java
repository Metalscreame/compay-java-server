package com.compay.json.faq;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class FaqEntityListJSON {
    @JsonProperty("faqObjects")
    private List<FaqEntity> faqObjects = new ArrayList<>();

    public List<FaqEntity> getFaqObjects() {return faqObjects;}

    @JsonCreator
    public FaqEntityListJSON(@JsonProperty("faqObjects")List<FaqEntity> faqObjects) {
        this.faqObjects = faqObjects;
    }

    public FaqEntityListJSON() {}
}