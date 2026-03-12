package Score;

public class PlayerScore {
    private player player;
    private Score score;

    public PlayerScore(player player, Score score) {
        this.player = player;
        this.score = score;
    }

    public player getPlayer() {
        return player;
    }

    public Score getScore() {
        return score;
    }
}