package com.nogueiragabriel.crawler;

public class SpiderTest
{
    public static void main(String[] args)
    {
        Spider spider = new Spider();
        spider.search("http://www.ilocal.com.br/restaurante-e-churrascaria-cereja");
    }
}
