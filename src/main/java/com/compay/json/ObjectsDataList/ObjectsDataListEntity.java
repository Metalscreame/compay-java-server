package com.compay.json.ObjectsDataList;

import java.util.List;
import java.util.Map;

//есть два внешних листа
// accountObjects
// serviceList(фуловый перечень услуг)

public class ObjectsDataListEntity {

//    private List<Map> accountObjects;
//
//    private List<Map> serviceList;
//
//    public List<Map> getAccountObjects() {
//        return accountObjects;
//    }
//
//    public void setAccountObjects(List<Map> accountObjects) {
//        this.accountObjects = accountObjects;
//    }
//
//    public List<Map> getServiceList() {
//        return serviceList;
//    }
//
//    public void setServiceList(List<Map> serviceList) {
//        this.serviceList = serviceList;
//    }

    public int id;
    public String name;
    public boolean objectDefault;
    //public String services;
    public List<Map> services;

    public ObjectsDataListEntity(int id, String name, boolean objectDefault, List<Map> services) {
        this.id = id;
        this.name = name;
        this.objectDefault = objectDefault;
        this.services = services;
    }
}
