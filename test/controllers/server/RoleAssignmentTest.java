package controllers.server;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoleAssignmentTest {
    @Test
    public void on_the_count_of_one_player_it_should_be_assigned_as_villager() {
        Player chanu = new Player("chanu");
        List<Player> players = new ArrayList<Player>();
        players.add(chanu);
        new RoleAssignment(players).assign();
        Assert.assertEquals(Role.Villager, chanu.getRole());
    }

    @Test
    public void on_the_count_of_two_players_there_should_be_two_villagers_and_zero_mafia() {
        List<Player> players = Arrays.asList(new Player("chanu"), new Player("deepthi"));
        RoleAssignment roleAssignment = new RoleAssignment(players);
        roleAssignment.assign();
        Assert.assertEquals(2, roleAssignment.getVillagerCount());
        Assert.assertEquals(0, roleAssignment.getMafiaCount());
    }

    @Test
    public void on_the_count_of_three_players_there_should_be_two_villagers_and_one_mafia() {
        List<Player> players = Arrays.asList(new Player("chanu"), new Player("deepthi"), new Player("chethu"));
        RoleAssignment roleAssignment = new RoleAssignment(players);
        roleAssignment.assign();
        Assert.assertEquals(2, roleAssignment.getVillagerCount());
        Assert.assertEquals(1, roleAssignment.getMafiaCount());
    }

    @Test
    public void on_the_count_of_four_players_there_should_be_three_villagers_and_one_mafia() {
        List<Player> players = Arrays.asList(new Player("chanu"), new Player("deepthi"), new Player("chethu"), new Player("raghu"));
        RoleAssignment roleAssignment = new RoleAssignment(players);
        roleAssignment.assign();
        Assert.assertEquals(3, roleAssignment.getVillagerCount());
        Assert.assertEquals(1, roleAssignment.getMafiaCount());
    }

    @Test
    public void on_the_count_of_five_players_there_should_be_three_villagers_and_two_mafia() {
        List<Player> players = Arrays.asList(new Player("chanu"), new Player("deepthi"), new Player("chethu"), new Player("raghu"), new Player("chanu1"));
        RoleAssignment roleAssignment = new RoleAssignment(players);
        roleAssignment.assign();
        Assert.assertEquals(3, roleAssignment.getVillagerCount());
        Assert.assertEquals(2, roleAssignment.getMafiaCount());
    }

    @Test
    public void on_the_count_of_six_players_there_should_be_four_villagers_and_two_mafia() {
        List<Player> players = Arrays.asList(new Player("chanu"), new Player("deepthi"), new Player("chethu"), new Player("raghu"), new Player("chanu1"), new Player("deepthi1"));
        RoleAssignment roleAssignment = new RoleAssignment(players);
        roleAssignment.assign();
        Assert.assertEquals(4, roleAssignment.getVillagerCount());
        Assert.assertEquals(2, roleAssignment.getMafiaCount());
    }

}
