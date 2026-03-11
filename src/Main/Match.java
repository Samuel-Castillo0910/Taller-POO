package Main;

public class Match {

    private int id; //ID de la partida
    private PlayerScore[] playerScores = new PlayerScore[10]; //Jugadores
    private int playerCount;
    private GameMode mode;
    private MatchStatus status;

    public Match(int id, GameMode mode) {
        this.id = id;
        this.mode = mode;
        this.playerCount = 0;
        this.status = MatchStatus.WAITING;
    } //Constructor

    public void AddPlayer (Player player) {
        if (playerCount >= playerScores.length) {
            System.out.println("La partida está llena");
            return;
        }
        for (int i = 0; i < playerScores.length; i++) {
            if (playerScores[i] == null) {
                playerScores[i] = new PlayerScore(player);
                playerCount++;
                break;
            }
        }
    }

    public void RemovePlayer (int id){
        for (int i = 0; i < playerScores.length; i++){
            if (id == playerScores[i].getPlayer().getId()){
                playerScores[i]=null;
                playerCount--;
                break;
            }

        }
    }

    public void StartMatch (){
        status = MatchStatus.IN_PROGRESS;
    }

    public void EndMatch (){
        status = MatchStatus.FINISHED;
    }

    public Player FindPlayer(int id){
        for (int i = 0; i < playerScores.length; i++){
            if (id == playerScores[i].getPlayer().getId()){
                return playerScores[i].getPlayer();
            }
        }
        System.out.println("Jugador no esta en partida");
        return null;
    }

    public PlayerScore getPlayerScore(int id){
        for (int i = 0; i < playerScores.length; i++){
            if (id == playerScores[i].getPlayer().getId()){
                return playerScores[i];
            }
        }

        System.out.println("No tiene estadisticas");
        return null;
    }

    public int getId(){
        return id;
    }

    public MatchStatus getStatus(){
        return status;
    }

    public GameMode getMode(){
        return mode;
    }

    public int getPlayerCount(){
        return playerCount;
    }

}
