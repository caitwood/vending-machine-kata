package vend;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

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

    @Test
    public void shouldConvertTwentyFiveCentsToCoins() {
        assertThat(Coin.convertToCoins(.25), is(Collections.singletonList(QUARTER)));
    }

    @Test
    public void shouldConvertTenCentsToCoins() {
        assertThat(Coin.convertToCoins(.10), is(Collections.singletonList(DIME)));
    }

    @Test
    public void shouldConvertFiveCentsToCoins() {
        assertThat(Coin.convertToCoins(.05), is(Collections.singletonList(NICKEL)));
    }

    @Test
    public void shouldConvertOneCentToCoins() {
        assertThat(Coin.convertToCoins(.01), is(Collections.singletonList(PENNY)));
    }

    @Test
    public void shouldConvertSixtySixCentsToCoins() {
        assertThat(Coin.convertToCoins(.66), is(Arrays.asList(QUARTER, QUARTER, DIME, NICKEL, PENNY)));
    }
}