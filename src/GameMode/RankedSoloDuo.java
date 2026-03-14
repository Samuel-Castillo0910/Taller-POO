package GameMode;

import Score.Score;

public class RankedSoloDuo extends GameMode {

    private int lpGainPerWin;

    public RankedSoloDuo(int lpGainPerWin) {
        super("Ranked Solo/Duo", 1, 2);
        this.lpGainPerWin = lpGainPerWin;
    }

    @Override
    public int calculateScore(Score score) {
        if (score.isWon()) {
            int kdaBonus = (int) (score.calculateKDA() * 5);
            return lpGainPerWin + kdaBonus;
        } else {
            return -(lpGainPerWin / 2);
        }
    }

    public int getLpGainPerWin() { return lpGainPerWin; }
    public void setLpGainPerWin(int lpGainPerWin) { this.lpGainPerWin = lpGainPerWin; }

    @Override
    public String toString() {
        return "RankedSoloDuo{lpGainPerWin=" + lpGainPerWin + "}";
    }
}