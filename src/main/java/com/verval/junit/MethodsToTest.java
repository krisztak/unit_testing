package com.verval.junit;

import com.verval.exception.UserChangeException;
import com.verval.exception.WrongTeamException;
import com.verval.model.BasketBallPlayer;
import com.verval.model.User;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;


public class MethodsToTest {


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
                } }
                else {
                    if(!verifyUserIsAdmin(callingUserId)){
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

    public BasketBallPlayer rookieJoinsTeam(BasketBallPlayer rookie){
        if("Tigers".equals(rookie.getTeam()) ) {
            rookie.setRookie(true);
            rookie.setTeamMates(createTigerTeam());
        }
        else throw new WrongTeamException();

        return rookie;
    }


    //dummy method to avoid mocking
    private User findApplicationUser(Long userId) {
        return new User().setEmail("test@gmail.com").setFirstName("User").setLastName("Test").setUsername("testUser");
    }

    //dummy method to avoid mocking
    private boolean verifyUserIsAdmin(Long userId) {
        if (userId < 10) {
            return true;
        } else return false;
    }

    private List<BasketBallPlayer> createTigerTeam(){
        List<BasketBallPlayer> team = new ArrayList<>();
        BasketBallPlayer player = new BasketBallPlayer();
        player.setAvgAssistsPerGame(10).setSize(196).setAvgPointsPerGame(20).setAvgReboundsPerGame(5).setTeam("Tigers").setWeight(130).setRookie(false).setName("Tom Savy");
        team.add(player);
        player.setAvgAssistsPerGame(1).setSize(190).setAvgPointsPerGame(30).setAvgReboundsPerGame(1).setTeam("Tigers").setWeight(100).setRookie(false).setName("John Doe");
        team.add(player);
        player.setAvgAssistsPerGame(1).setSize(190).setAvgPointsPerGame(30).setAvgReboundsPerGame(1).setTeam("Tigers").setWeight(100).setRookie(false).setName("Alex Smith");
        team.add(player);
        player.setAvgAssistsPerGame(1).setSize(190).setAvgPointsPerGame(30).setAvgReboundsPerGame(1).setTeam("Tigers").setWeight(100).setRookie(false).setName("Don White");
        team.add(player);

        return team;
    }
}
