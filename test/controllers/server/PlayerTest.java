package controllers.server;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {
    @Test
    public void isMafia_returns_true_if_the_Player_is_mafia() {
        Player chethan = new Player("chethan");
        chethan.assignRole(Role.Mafia);
        Assert.assertEquals(true, chethan.isMafia());
    }

    @Test
    public void isMafia_returns_false_if_the_player_is_not_Mafia() {
        Player chethan = new Player("chethan");
        chethan.assignRole(Role.Villager);
        Assert.assertEquals(false, chethan.isMafia());
    }

}
