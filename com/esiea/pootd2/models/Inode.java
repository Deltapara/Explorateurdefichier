package com.esiea.pootd2.models;

public abstract class Inode {
    protected String name;
    protected int size;

    public Inode(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return name + " " + size;
    }
}