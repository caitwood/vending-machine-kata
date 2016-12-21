package vend;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static vend.Coin.*;

public class CoinTest {
    @Test
    public void shouldNotConsiderPennyValidCoin() {
        assertThat(isValidVendingMachineCoin(PENNY), is(false));
    }

    @Test
    public void shouldConsiderQuarterValidCoin() {
        assertThat(isValidVendingMachineCoin(QUARTER), is(true));
    }

    @Test
    public void shouldConsiderDimeValidCoin() {
        assertThat(isValidVendingMachineCoin(DIME), is(true));
    }

    @Test
    public void shouldConsiderNickelValidCoin() {
        assertThat(isValidVendingMachineCoin(NICKEL), is(true));
    }
}