package katas.exercises;

import katas.exercises.movieRental.Movie;
import katas.exercises.movieRental.Rental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieRentalCustomerTest {

    private MovieRentalCustomer customer;

    @BeforeEach
    void setUp() {
        customer = new MovieRentalCustomer("Martin");
    }

    @Test
    void testStatementWithRegularMovies() {
        // Arrange
        Movie regularMovie = new Movie("Ran", Movie.REGULAR);
        Rental rental1 = new Rental(regularMovie, 3);
        customer.addRental(rental1);

        // Act
        String result = customer.statement();

        // Assert
        String expected = "Rental Record for Martin\n\tRan\t3.5\nAmount owed is 3.5\nYou earned 1 frequent renter points";
        assertEquals(expected, result);
    }

    @Test
    void testStatementWithNewReleaseMovies() {
        // Arrange
        Movie newReleaseMovie = new Movie("Trois Couleurs: Bleu", Movie.NEW_RELEASE);
        Rental rental2 = new Rental(newReleaseMovie, 2);
        customer.addRental(rental2);

        // Act
        String result = customer.statement();

        // Assert
        String expected = "Rental Record for Martin\n\tTrois Couleurs: Bleu\t6.0\nAmount owed is 6.0\nYou earned 2 frequent renter points";
        assertEquals(expected, result);
    }

    @Test
    void testStatementWithChildrenMovies() {
        // Arrange
        Movie childrenMovie = new Movie("The Lion King", Movie.CHILDRENS);
        Rental rental3 = new Rental(childrenMovie, 4);
        customer.addRental(rental3);

        // Act
        String result = customer.statement();

        // Assert
        String expected = "Rental Record for Martin\n\tThe Lion King\t3.0\nAmount owed is 3.0\nYou earned 1 frequent renter points";
        assertEquals(expected, result);
    }


    @Test
    void testHtmlStatementWithChildrenAndRegularMovies() {
        // Arrange
        Movie childrenMovie = new Movie("The Lion King", Movie.CHILDRENS);
        Rental rental3 = new Rental(childrenMovie, 4);
        Movie regularMovie = new Movie("Ran", Movie.REGULAR);
        Rental rental1 = new Rental(regularMovie, 3);
        customer.addRental(rental3);
        customer.addRental(rental1);

        // Act
        String result = customer.htmlStatement();

        // Assert
        String expected = "<h1>Rental Record for <em>Martin</em></h1><table>" +
                          "<tr><td>The Lion King</td><td>3.0</td></tr>" +
                          "<tr><td>Ran</td><td>3.5</td></tr>" +
                          "</table><p>Amount owed is <em>6.5</em></p>" +
                          "<p>You earned <em>2</em> frequent renter points</p>";
        assertEquals(expected, result);
    }

    @Test
    void testHtmlStatementWithNoRentals() {
        // Act
        String result = customer.htmlStatement();

        // Assert
        String expected = "<h1>Rental Record for <em>Martin</em></h1><table></table>" +
                          "<p>Amount owed is <em>0.0</em></p>" +
                          "<p>You earned <em>0</em> frequent renter points</p>";
        assertEquals(expected, result);
    }
}
