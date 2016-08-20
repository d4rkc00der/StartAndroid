package com.soho.evgeny.customadapter;

/**
 * Created by evgeny on 11.08.16.
 */
public class Product {
    public String name;
    public int price;
    public int img;
    public boolean box;
    public Product(String _describe,int _price, int _image, boolean _box) {
        name = _describe;
        price = _price;
        img = _image;
        box = _box;
    }


}
