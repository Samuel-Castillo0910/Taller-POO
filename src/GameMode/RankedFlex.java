package GameMode;

import Score.Score;

public class RankedFlex extends GameMode {

    private int lpGainPerWin;

    public RankedFlex(int lpGainPerWin) {
        super("Ranked Flex", 1, 5);
        this.lpGainPerWin = lpGainPerWin;
    }

    @Override
    public int calculateScore(Score score) {
        if (score.isWon()) {
            int kdaBonus = (int) (score.calculateKDA() * 3);
            return lpGainPerWin + kdaBonus;
        } else {
            return -(lpGainPerWin / 3);
        }
    }

    public int getLpGainPerWin() { return lpGainPerWin; }
    public void setLpGainPerWin(int lpGainPerWin) { this.lpGainPerWin = lpGainPerWin; }

    @Override
    public String toString() {
        return "RankedFlex{lpGainPerWin=" + lpGainPerWin + "}";
    }
}