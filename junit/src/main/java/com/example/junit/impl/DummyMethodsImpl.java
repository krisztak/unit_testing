package com.example.junit.impl;

import com.example.junit.exception.UserChangeException;
import com.example.junit.exception.WrongTeamException;
import com.example.junit.model.BasketBallPlayer;
import com.example.junit.model.User;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;


public class DummyMethodsImpl {


    public double calculateAnnualCommusion(int salesClass, double salesTotal) {
        double commissionRate;
        if (salesClass == 1)
            if (salesTotal < 10000.0)
                commissionRate = 0.02;
            else
                commissionRate = 0.04;
        else if (salesTotal < 10000.0)
            commissionRate = 0.025;
        else
            commissionRate = 0.05;
        return salesTotal * commissionRate;
    }


    public User editUser(Boolean editEnabled, Long userId,
                         User editingDto,
                         Long callingUserId) {

        User user = findApplicationUser(userId);

        if (callingUserId != null) {
            if (callingUserId == userId) {
                if (!editEnabled && !verifyUserIsAdmin(callingUserId)) {
                    throw new UserChangeException();
                }
            } else {
                if (!verifyUserIsAdmin(callingUserId)) {
                    throw new UserChangeException();
                }
            }
        }

        String newEmail = editingDto.getEmail();
        if (StringUtils.isNotEmpty(newEmail)) {
            user.setEmail(newEmail);
        }

        String newUsername = editingDto.getUsername();
        if (StringUtils.isNotEmpty(newUsername)) {
            user.setUsername(newUsername);
        }

        String newFirstName = editingDto.getFirstName();
        if (StringUtils.isNotEmpty(newFirstName)) {
            user.setFirstName(newFirstName);
        }

        String newLastName = editingDto.getLastName();
        if (StringUtils.isNotEmpty(newLastName)) {
            user.setLastName(newLastName);
        }

        return user;
    }

    public BasketBallPlayer rookieJoinsTeam(BasketBallPlayer rookie) {
        if ("Tigers".equals(rookie.getTeam())) {
            rookie.setRookie(true);
            rookie.setTeamMates(createTigerTeam());
        } else throw new WrongTeamException();
        return rookie;
    }

    //dummy method to avoid mocking
    private User findApplicationUser(Long userId) {
        User dummy = new User();
        dummy.setEmail("test@gmail.com");
        dummy.setFirstName("User");
        dummy.setLastName("Test");
        dummy.setUsername("testUser");
        return dummy;
    }

    //dummy method to avoid mocking
    private boolean verifyUserIsAdmin(Long userId) {
        if (userId < 10) {
            return true;
        } else return false;
    }

    private List<BasketBallPlayer> createTigerTeam() {
        List<BasketBallPlayer> team = new ArrayList<>();
        BasketBallPlayer player = new BasketBallPlayer();
        player.setAvgAssistsPerGame(10);
        player.setSize(196);
        player.setAvgPointsPerGame(20);
        player.setAvgReboundsPerGame(5);
        player.setTeam("Tigers");
        player.setWeight(130);
        player.setRookie(false);
        player.setName("Tom Savy");
        team.add(player);
        BasketBallPlayer player2= new BasketBallPlayer();
        player2.setAvgAssistsPerGame(1);
        player2.setSize(190);
        player2.setAvgPointsPerGame(30);
        player2.setAvgReboundsPerGame(1);
        player2.setTeam("Tigers");
        player2.setWeight(100);
        player2.setRookie(false);
        player2.setName("John Doe");
        team.add(player2);
        BasketBallPlayer player3 = new BasketBallPlayer();
        player3.setAvgAssistsPerGame(1);
        player3.setSize(190);
        player3.setAvgPointsPerGame(30);
        player3.setAvgReboundsPerGame(1);
        player3.setTeam("Tigers");
        player3.setWeight(100);
        player3.setRookie(false);
        player3.setName("Alex Smith");
        team.add(player3);
        BasketBallPlayer player4 = new BasketBallPlayer();
        player4.setAvgAssistsPerGame(1);
        player4.setSize(190);
        player4.setAvgPointsPerGame(30);
        player4.setAvgReboundsPerGame(1);
        player4.setTeam("Tigers");
        player4.setWeight(100);
        player4.setRookie(false);
        player4.setName("Don White");
        team.add(player4);

        return team;
    }
}
