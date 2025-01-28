package katas.exercises;

import katas.exercises.wallet.RateProvider;
import katas.exercises.wallet.Stock;
import katas.exercises.wallet.Utils.*;
import java.util.List;

/**
 * Represents a wallet containing stocks, where each stock has a quantity and type.
 * The wallet's total value in a given currency can be calculated using an external rate provider.
 *
 * Implement the RateProvider interface to fetch REAL rates from external API:
 *     http://api.fixer.io/
 *
 * Use the Mockito package (https://site.mockito.org/) to test your wallet implementation without performing a real HTTP request to the API
 * during test execution.
 */
public class Wallet {

    private final List<Stock> stocks;

    public Wallet(List<Stock> stocks) {
        this.stocks = stocks;
    }

    /**
     * Computes the total value of the wallet in a specified currency using a rate provider.
     *
     * @param currency the target currency
     * @param rateProvider the rate provider
     * @return the total value of the wallet in the specified currency
     */
    public double value(Currency currency, RateProvider rateProvider) {
        double totalValue = 0.0;

        for (Stock stock : stocks) {
            double rate = rateProvider.rate(stock.getType(), currency);
            totalValue += stock.getQuantity() * rate;
        }

        return totalValue;
    }

    public static void main(String[] args) {

        // Implementing a static rate provider for testing
        class StaticRateProvider implements RateProvider {
            @Override
            public double rate(StockType from, Currency to) {
                switch (from) {
                    case PETROLEUM -> {
                        return to == Currency.EUR ? 80.0 : 85.0;
                    }
                    case BITCOIN -> {
                        return to == Currency.EUR ? 25000.0 : 26000.0;
                    }
                    case USD -> {
                        return to == Currency.EUR ? 0.9 : 1.0;
                    }
                    default -> throw new IllegalArgumentException("Unknown stock type: " + from);
                }
            }
        }

        RateProvider staticRateProvider = new StaticRateProvider();

        Wallet wallet = new Wallet(List.of(
                new Stock(5, StockType.PETROLEUM),
                new Stock(2, StockType.BITCOIN),
                new Stock(100, StockType.USD)
        ));

        double walletValueInEUR = wallet.value(Currency.EUR, staticRateProvider);
        System.out.println("Total Wallet Value in EUR: " + walletValueInEUR);

        double walletValueInUSD = wallet.value(Currency.USD, staticRateProvider);
        System.out.println("Total Wallet Value in USD: " + walletValueInUSD);
    }
}


