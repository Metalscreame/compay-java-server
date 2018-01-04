package com.compay.json.ObjectsDataList;

import java.util.List;

public class ObjectsDataListEntity {
    public List<Object> accountObjects;
    public List<Object> serviceList;

    public ObjectsDataListEntity(List<Object> accountObjects, List<Object> serviceList) {
        this.accountObjects = accountObjects;
        this.serviceList = serviceList;
    }
}
