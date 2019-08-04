package com.kalypso.ascor.Kalender;

import com.kalypso.ascor.HTTPStuffs.KalenderDownloader;

public class Main {

    public static void main(String[] args){
        KalenderDownloader downloader = new KalenderDownloader();
        System.out.println(downloader.parseOutput(downloader.addRequestParameters(downloader.createRequest("https://www.wuerzburg.de/themen/umwelt-verkehr/vorsorge-entsorgung/abfallkalender/index.html"))));

    }

}
