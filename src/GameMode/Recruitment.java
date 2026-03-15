package GameMode;

import Score.Score;

public class Recruitment extends GameMode {

    public Recruitment() {
        super("Recruitment", 1, 10);
    }

    @Override
    public int calculateScore(Score score) {
        if (score.isWon()) {
            return 50;  // puntaje fijo por victoria
        } else {
            return 0;   // sin penalización
        }
    }

    @Override
    public String toString() {
        return "Recruitment{name='" + getName() + "'}";
    }
}
