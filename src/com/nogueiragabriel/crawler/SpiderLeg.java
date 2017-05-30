package com.nogueiragabriel.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderLeg
{
    // We'll use a fake USER_AGENT so the web server thinks the robot is a normal web browser.
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links = new LinkedList<String>();
    private List<Company> companys = new ArrayList<Company>();
    private Document htmlDocument;

    public boolean crawl(String url)  {
        try  {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            
            this.htmlDocument = htmlDocument;
            if(connection.response().statusCode() == 200) {
                System.out.println("\n**Visiting** Received web page at " + url);
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
            for(Element link : linksOnPage) {
                if(!link.absUrl("href").contains("#") 
                	&& !link.absUrl("href").contains("$")
                	&& !link.absUrl("href").contains("/entrar")
                	&& link.absUrl("href").contains("ilocal"))
                	this.links.add(link.absUrl("href"));
            }

            System.out.println("Found (" + linksOnPage.size() + ") links on page");
            
	            
            String tel = htmlDocument.select("span[class=first-numbers]").first().text() 
            			+ htmlDocument.select("span[class=first-numbers]").first().attr("data-last");
            String adress = htmlDocument.select("div.footerMap>h3").text();
            String name = htmlDocument.select("div.centralizePage>h1").text();
            
            Company company = new Company (tel, adress, name);
            this.companys.add(company);
            
            System.out.println("Company name: " + name );
            System.out.println("Found tel number: " + tel);
            System.out.println("Company adress: " + adress);
            
            return true;
        } catch(Exception e) {
            return false;
        }
    }


    public List<String> getLinks() {
        return this.links;
    }
}