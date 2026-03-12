package Score;

public class player {
    private int id;
    private String name;

    public player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        player p = (player) o;
        return id == p.id;
    }

    @Override
    public int hashCode() {
        return id * 31;
    }

    // Polimorfismo
    @Override
    public String toString() {
        return "Player{id=" + id + ", name='" + name + "'}";
    }
}