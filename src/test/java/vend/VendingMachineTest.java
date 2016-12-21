package vend;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static vend.Coin.*;

public class VendingMachineTest {
    private VendingMachine vendingMachine;

    @Before
    public void setup() {
        vendingMachine = new VendingMachine();
    }

    @Test
    public void shouldReturnInsertCoinMessageIfBalanceIsZero() {
        assertThat(vendingMachine.getCurrentBalance(), is("INSERT COIN"));
    }

    @Test
    public void shouldAcceptQuarters() {
        vendingMachine.insert(QUARTER);

        assertThat(vendingMachine.getCurrentBalance(), is("$0.25"));
    }

    @Test
    public void shouldAcceptDimes() {
        vendingMachine.insert(DIME);

        assertThat(vendingMachine.getCurrentBalance(), is("$0.10"));
    }

    @Test
    public void shouldAcceptNickels() {
        vendingMachine.insert(NICKEL);

        assertThat(vendingMachine.getCurrentBalance(), is("$0.05"));
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotAcceptPennies() {
        vendingMachine.insert(PENNY);
    }
}