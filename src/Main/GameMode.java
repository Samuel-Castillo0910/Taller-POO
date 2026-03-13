package Main;

import Score.Score;
import Score.ScoringSystem;

public abstract class GameMode implements ScoringSystem {

    private String name;
    private int minPlayers;
    private int maxPlayers;

    public GameMode(String name, int minPlayers, int maxPlayers) {
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

    public boolean validateQueueSize(int playerCount) {
        return playerCount >= minPlayers && playerCount <= maxPlayers;
    }

    @Override
    public abstract int calculateScore(Score score);

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getMinPlayers() { return minPlayers; }
    public void setMinPlayers(int minPlayers) { this.minPlayers = minPlayers; }

    public int getMaxPlayers() { return maxPlayers; }
    public void setMaxPlayers(int maxPlayers) { this.maxPlayers = maxPlayers; }

    @Override
    public String toString() {
        return "GameMode{name='" + name + "', minPlayers=" + minPlayers + ", maxPlayers=" + maxPlayers + "}";
    }
}