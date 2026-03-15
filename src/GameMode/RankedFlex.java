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
            return lpGainPerWin;        // ej: +30
        } else {
            return -(lpGainPerWin - 8); // ej: -22
        }
    }

    public int getLpGainPerWin() { return lpGainPerWin; }
    public void setLpGainPerWin(int lpGainPerWin) { this.lpGainPerWin = lpGainPerWin; }

    @Override
    public String toString() {
        return "RankedFlex{lpGainPerWin=" + lpGainPerWin + "}";
    }
}