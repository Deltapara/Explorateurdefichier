package com.esiea.pootd2.models;

import java.util.ArrayList;
import java.util.List;

public class FolderInode extends Inode {
    private List<Inode> children;

    public FolderInode(String name) {
        super(name, 0);
        this.children = new ArrayList<>();
    }

    public void addInode(Inode inode) {
        children.add(inode);
        updateSize();
    }

    public List<Inode> getChildren() {
        return children;
    }

    private void updateSize() {
        int totalSize = 0;
        for (Inode child : children) {
            totalSize += child.getSize();
        }
        this.size = totalSize;
    }

    @Override
    public String toString() {
        return name + " " + size + " (folder)";
    }
}