package GameMode;

import Score.Score;

public class Recruitment extends GameMode {

    public Recruitment() {
        super("Recruitment", 1, 10);
    }

    @Override
    public int calculateScore(Score score) {
        int kdaPoints = (int) (score.calculateKDA() * 10);
        int goldPoints = score.getGold() / 100;
        int winBonus = score.isWon() ? 50 : 0;
        return kdaPoints + goldPoints + winBonus;
    }

    @Override
    public String toString() {
        return "Recruitment{name='" + getName() + "'}";
    }
}
