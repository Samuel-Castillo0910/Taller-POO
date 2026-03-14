package Score;

public class Score {
    private int kills;
    private int deaths;
    private int assists;
    private int gold;
    private boolean won;

    // Constructor para inicializar los datos
    public Score(int kills, int deaths, int assists, int gold, boolean won) {
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.gold = gold;
        this.won = won;
    }

    // Lógica de negocio de el UML
    public double calculateKDA() {
        if (this.deaths == 0) {
            return (double) (this.kills + this.assists);
        }
        return (double) (this.kills + this.assists) / this.deaths;
    }

    // GETTERS
    public int getKills() { return kills; }
    public int getDeaths() { return deaths; }
    public int getAssists() { return assists; }
    public int getGold() { return gold; }
    public boolean isWon() { return won; }

    // SETTERS
    public void setKills(int kills) { this.kills = kills; }
    public void setDeaths(int deaths) { this.deaths = deaths; }
    public void setAssists(int assists) { this.assists = assists; }
    public void setGold(int gold) { this.gold = gold; }
    public void setWon(boolean won) { this.won = won; }

    @Override
    public String toString() {
        return "Score{" +
                "kills=" + kills +
                ", deaths=" + deaths +
                ", assists=" + assists +
                ", gold=" + gold +
                ", won=" + won +
                '}';
    }
}