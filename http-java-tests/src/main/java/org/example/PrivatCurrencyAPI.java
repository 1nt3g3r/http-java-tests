package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class PrivatCurrencyAPI {
    public static void main(String[] args) throws IOException {

        String jsonText = Jsoup.connect("https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5")
                .ignoreContentType(true)
                .get()
                .body()
                .text();

        Type listType = new TypeToken<List<CurrencyRate>>(){}.getType();
        List<CurrencyRate> currencyRates = new Gson().fromJson(jsonText, listType);

        for (CurrencyRate rate : currencyRates) {
            System.out.println(rate);
        }
    }
}
