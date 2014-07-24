package com.bank;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;

import static org.junit.Assert.assertEquals;


public class BankTest {

    Bank bank = new Bank();

    @Test
    public void shouldgetTheMapOfCurrencyAndItscounts() throws Exception
    {
        String currencychange = "100-10,10-30,50-20,5-50,1-100";
        SortedMap<Integer, Integer> inputMap = bank.convertTheInputToMap(currencychange);
        assertEquals(5, inputMap.size());
        assertEquals(10, inputMap.get(100).intValue());
        assertEquals(20, inputMap.get(50).intValue());
        assertEquals(30, inputMap.get(10).intValue());
        assertEquals(50, inputMap.get(5).intValue());
        assertEquals(100, inputMap.get(1).intValue());
    }

    @Test(expected = NumberFormatException.class)
    public void shouldFailIfTwoCurrencySeparatorIsDifferent() throws Exception
    {
        String currencychange = "100-10:50-20:10-30:5-50:1-100";
        SortedMap<Integer, Integer> inputMap = bank.convertTheInputToMap(currencychange);
    }

   @Test(expected = NumberFormatException.class)
    public void shouldFailIfTwoCurrencySeparatorIsDifferentOnlyForOne() throws Exception
    {
        String currencychange = "100-10:50-20,10-30,5-50,1-100";
        SortedMap<Integer, Integer> inputMap = bank.convertTheInputToMap(currencychange);
    }

    @Test(expected = NumberFormatException.class)
    public void shouldFailIfCurrencyAndChangeSeparatorIsDifferentOnlyForOne() throws Exception
    {
        String currencychange = "100:10,50-20,10-30,5-50,1-100";
        SortedMap<Integer, Integer> inputMap = bank.convertTheInputToMap(currencychange);
    }

    @Test
    public void shouldFailIfTheKeyInConfigurationIsCorrect() throws Exception {
        bank.readInput(Bank.CURRENCY_CHANGE_KEY);
    }

    @Test(expected = Exception.class)
    public void shouldFailIfTheKeyInConfigurationIsWrong() throws Exception {
        bank.readInput("currency");
    }

}
