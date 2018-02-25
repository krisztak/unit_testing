package com.verval.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class BasketBallPlayer {

        private String name;
        public double size;
        private float weight;
        private double avgPointsPerGame;
        private double avgAssistsPerGame;
        private double avgReboundsPerGame;
        private String team;
        private boolean rookie;
        private List<BasketBallPlayer> teamMates = new ArrayList<BasketBallPlayer>();

    }



