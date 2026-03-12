package Main;

import java.util.Objects;
//Creacion de Atributos
public class Player {
    private final int id;
    private int level;
    private int wins;
    private int losses;
    private boolean active;
    private Rank rank;
    private String nickname;
//Constructor de la clase
    public Player(int id, int level, String nickname){
        this.id = id;
        this.nickname = nickname;
        setLevel(level);
        this.wins = 0;
        this.losses = 0;
        this.active = true;
        this.rank = Rank.IRON;
    }
//getters y setters
    public int getId() {
        return id;
    }


    public int getLevel() {
        return level;
    }
//el nivel no puede ser menor a 1, setter con condicional
    public void setLevel(int level) {
        if (level < 1) throw new IllegalArgumentException("El nivel no puede ser menor a 1");
        this.level = level;
    }

    public int getWins() {
        return wins;
    }
    public void addWin() { this.wins++; }

    public int getLosses() {
        return losses;
    }
    public void addLoss() { this.losses++; }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public String getNickname() {
        return nickname;
    }
    /**
     * Dos jugadores son iguales si tienen el mismo id,
     * independientemente de su nivel, rango o estadísticas.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id;
    }
    /**
     * El hashCode se basa únicamente en el id, consistente con equals().
     * Regla Java: si equals() compara por id, hashCode() debe usar el mismo atributo.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    /**
     * Representación textual del jugador para logs.
     * Muestra todos los atributos del estado actual.
     */
    @Override
    public String toString() {
        return "Main.Player{" +
                "id=" + id +
                ", level=" + level +
                ", wins=" + wins +
                ", losses=" + losses +
                ", active=" + active +
                ", rank=" + rank +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}