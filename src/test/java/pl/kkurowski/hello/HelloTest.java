package pl.kkurowski.hello;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;


public class HelloTest {
    @Test
    void helloTest() {
        //Arrange
        var a = 2;
        var b = 4;
        //Act
        var result = a + b;
        //Assert
        assert 6 == result;
    }

    @Test
    void itFail() {
        //Arrange
        var a = 2;
        var b = 4;
        //Act
        var result = a + b;
        //Assert
        assert 10 == result;
    }

    @Test
    void itGreetUsername() {
        //Arrange
        String name = "Jakub";
        //Act
        String message = String.format("Hello %s", name);
        //Assert
        assertEquals("Hello Jakub", message);
    }

    @Test
    void listExpectedToBeEmpty() {
        var list = new ArrayList<>();

        list.add("Jakub");
        list.remove(0);

        assert list.isEmpty();
    }
}