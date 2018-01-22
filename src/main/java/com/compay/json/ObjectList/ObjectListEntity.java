package com.compay.json.ObjectList;

public class ObjectListEntity {
    //must be public, в другом случае джексон не сможет их считать
    public int id;
    public String name;
    public boolean objectDefault;

    public ObjectListEntity(){}

    public ObjectListEntity(int id, String name, boolean objectDefault) {
        this.id = id;
        this.name = name;
        this.objectDefault = objectDefault;
    }
}
