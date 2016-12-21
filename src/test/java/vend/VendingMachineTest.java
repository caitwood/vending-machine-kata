package vend;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

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

    @Test
    public void shouldAddPenniesToCoinReturnAndNotIncrementBalance() {
        vendingMachine.insert(PENNY);

        assertThat(vendingMachine.getCurrentBalance(), is("INSERT COIN"));
        assertThat(vendingMachine.getCoinReturnContents(), is(Collections.singletonList(PENNY)));
    }

    @Test
    public void shouldReturnThankYouMessageAndResetBalanceForCandy() {
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(DIME);
        vendingMachine.insert(NICKEL);

        assertThat(vendingMachine.vend(Snack.CANDY), is("THANK YOU"));
        assertThat(vendingMachine.getCurrentBalance(), is("INSERT COIN"));
    }

    @Test
    public void shouldReturnThankYouMessageAndResetBalanceForChips() {
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);

        assertThat(vendingMachine.vend(Snack.CHIPS), is("THANK YOU"));
        assertThat(vendingMachine.getCurrentBalance(), is("INSERT COIN"));
    }

    @Test
    public void shouldReturnThankYouMessageAndResetBalanceForCola() {
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);

        assertThat(vendingMachine.vend(Snack.COLA), is("THANK YOU"));
        assertThat(vendingMachine.getCurrentBalance(), is("INSERT COIN"));
    }

    @Test
    public void shouldAddCorrectChangeToCoinReturnIfMoneyIsLeftoverFromPurchase() {
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(DIME);

        assertThat(vendingMachine.vend(Snack.COLA), is("THANK YOU"));
        assertThat(vendingMachine.getCurrentBalance(), is("INSERT COIN"));
        assertThat(vendingMachine.getCoinReturnContents(), is(Collections.singletonList(DIME)));
    }
}