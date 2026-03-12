package Score;

import Main.Player;

public class PlayerScore {
    private Player player;
    private Score score;

    public PlayerScore(Player player, Score score) {
        this.player = player;
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public Score getScore() {
        return score;
    }
}