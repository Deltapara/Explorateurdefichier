package com.esiea.pootd2.models;

import java.util.Random;

public class FileInode extends Inode {
    public FileInode(String name) {
        // Taille aléatoire entre 1 et 100000
        super(name, new Random().nextInt(100_000) + 1);
    }
}