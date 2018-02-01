package com.compay.json.faq;

import java.util.ArrayList;
import java.util.List;

public class FaqEntityList {

    private List<Object> faqObjects = new ArrayList<>();

    public List<Object> getFaqObjects() {return faqObjects;}

   public FaqEntityList(List<Object> faqObjects) {
        this.faqObjects = faqObjects;
    }

    public FaqEntityList() {}
}
