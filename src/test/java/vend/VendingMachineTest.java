package vend;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static vend.Coin.*;
import static vend.Snack.*;

public class VendingMachineTest {
    private VendingMachine vendingMachine;

    @Before
    public void setup() {
        vendingMachine = new VendingMachine(5, 5, 5);
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

        assertThat(vendingMachine.vend(CANDY), is("THANK YOU"));
        assertThat(vendingMachine.getCurrentBalance(), is("INSERT COIN"));
    }

    @Test
    public void shouldReturnThankYouMessageAndResetBalanceForChips() {
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);

        assertThat(vendingMachine.vend(CHIPS), is("THANK YOU"));
        assertThat(vendingMachine.getCurrentBalance(), is("INSERT COIN"));
    }

    @Test
    public void shouldReturnThankYouMessageAndResetBalanceForCola() {
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);

        assertThat(vendingMachine.vend(COLA), is("THANK YOU"));
        assertThat(vendingMachine.getCurrentBalance(), is("INSERT COIN"));
    }

    @Test
    public void shouldAddCorrectChangeToCoinReturnIfMoneyIsLeftoverFromPurchase() {
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(DIME);

        assertThat(vendingMachine.vend(COLA), is("THANK YOU"));
        assertThat(vendingMachine.getCurrentBalance(), is("INSERT COIN"));
        assertThat(vendingMachine.getCoinReturnContents(), is(Collections.singletonList(DIME)));
    }

    @Test
    public void shouldReturnCoinsIfReturnInvoked() {
        vendingMachine.insert(QUARTER);
        vendingMachine.returnCoins();

        assertThat(vendingMachine.getCurrentBalance(), is("INSERT COIN"));
        assertThat(vendingMachine.getCoinReturnContents(), is(Collections.singletonList(QUARTER)));
    }

    @Test
    public void shouldReturnSoldOutMessageIfProductIsOutOfStock() {
        vendingMachine.setInventory(CHIPS, 0);

        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);

        assertThat(vendingMachine.vend(CHIPS), is("SOLD OUT"));
        assertThat(vendingMachine.getCurrentBalance(), is("$0.50"));
    }

    @Test
    public void shouldReturnExactChangeOnlyMessageIfMakingChangeIsDisabled() {
        vendingMachine.setExactChangeOnly(true);

        assertThat(vendingMachine.getCurrentBalance(), is("EXACT CHANGE ONLY"));
    }

    @Test
    public void shouldNotMakeChangeIfMakingChangeIsDisabled() {
        vendingMachine.setExactChangeOnly(true);

        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);
        vendingMachine.insert(QUARTER);

        assertThat(vendingMachine.vend(CHIPS), is("THANK YOU"));
        assertThat(vendingMachine.getCurrentBalance(), is("EXACT CHANGE ONLY"));
        assertThat(vendingMachine.getCoinReturnContents(), is(Collections.<Coin>emptyList()));
    }
}