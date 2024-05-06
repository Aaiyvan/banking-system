package dev.aaiyvan.loanservice.service.impl;

import dev.aaiyvan.loanservice.service.InterestRateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InterestRateServiceImpl implements InterestRateService {

    @Override
    public Double calculateInterestAccrued(
            final Double monthlyPayment,
            Double amount,
            final Double downPay,
            final Integer term
    ) {
        Integer termsInMonths = term * 12;
        amount -= downPay;
        Double totalCost = monthlyPayment * termsInMonths;
        Double totalInterestAccrued = totalCost - amount;

        return totalInterestAccrued;
    }

}
