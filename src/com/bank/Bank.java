package com.bank;

import org.apache.commons.lang.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Bank {

    protected Map<Integer,Integer> balanceChange = new HashMap<Integer, Integer>();
    public static final String CURRENCY_CHANGE_KEY = "currencychange";

    public void depositToTheBank()
    {
        SortedMap<Integer, Integer> currencyChangeMap = null;
        try {
                currencyChangeMap = getChangeInput();

        } catch (Exception e) {
            e.printStackTrace();
            currencyChangeMap = null;
        }
        if(currencyChangeMap == null || currencyChangeMap.size() == 0)
        {
            System.out.println("The Bank is out of money");
        }
    }

    private SortedMap<Integer,Integer> getChangeInput() throws Exception {
        String currencychange = readInput(CURRENCY_CHANGE_KEY);
        return convertTheInputToMap(currencychange);
    }

    public String readInput(String currencyChangeKey) throws Exception {
        Properties prop = new Properties();
        String propFileName = "config.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        prop.load(inputStream);
        if (inputStream == null) {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        String propertyValue = prop.getProperty(currencyChangeKey);
        if(propertyValue==null)
        {
            throw new Exception("The expected config Format should start with currencychange");
        }
        return propertyValue;
    }


    public SortedMap<Integer,Integer> convertTheInputToMap(String currencychange) throws NumberFormatException {
        SortedMap<Integer,Integer> currencyMap = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        try {
            String[] currencyCounts = StringUtils.split(currencychange, ',');
            for (String currencyCount : currencyCounts) {
                String[] currencyAndCountArr = StringUtils.split(currencyCount, '-');
                currencyMap.put(Integer.valueOf(currencyAndCountArr[0]), Integer.valueOf(currencyAndCountArr[1]));
            }
        }
        catch(NumberFormatException nfe)
        {
            throw new NumberFormatException("The Format Of the Input String is incorrect - " + currencychange);
        }
        return currencyMap;

    }

    public void generateDenominations(Integer inputCurrency,SortedMap<Integer,Integer> balanceMap)
    {
        Map<Integer,Integer> retMap = new HashMap<Integer, Integer>();
        Integer tempIpCurrency = Integer.valueOf(inputCurrency);
        Integer balanceCurrency = 0;
        for (Integer currencyKey : balanceMap.keySet()) {
            if(tempIpCurrency.intValue() >= currencyKey.intValue())
            {
                tempIpCurrency = tempIpCurrency.intValue() - currencyKey.intValue();
            }
        }
    }

    //public void gene

}
