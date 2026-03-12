package Main;

import Score.MatchStatus;
import Score.PlayerScore;
import Score.Score;

public class Match {


    //ATRIBUTOS
    private final int id;
    private final PlayerScore[] playerScores = new PlayerScore[10];
    private int playerCount;
    private final GameMode mode;
    private MatchStatus status;


    //CONSTRUCTOR
    public Match(int id, GameMode mode) {
        this.id = id;
        this.mode = mode;
        this.playerCount = 0;
        this.status = MatchStatus.WAITING;
    }



    //FUNCIÓN PARA AÑADIR JUGADORES A LA PARTIDA
    public void AddPlayer (Player player) {
        if (playerCount >= playerScores.length) {
            System.out.println("La partida está llena");
            return;
        }
        for (int i = 0; i < playerScores.length; i++) {
            if (playerScores[i] == null) {
                playerScores[i] = new PlayerScore(player, new Score(0, 0, 0, 0, false));
                playerCount++;
                break;
            } //FOR PARA QUE EL PRIMER ELEMENTO DEL ARREGLO QUE DETECTE COMO NULL, CREE UN OBJETO AHI
        }
    }


    //FUNCIÓN PARA QUITAR JUGADORES
    public void RemovePlayer (int id){
        for (int i = 0; i < playerScores.length; i++){
            if (playerScores[i] != null && id == playerScores[i].getPlayer().getId()){
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
            if (playerScores[i] != null && id == playerScores[i].getPlayer().getId()){
                return playerScores[i].getPlayer();
            }
        }
        System.out.println("Jugador no esta en partida");
        return null;
    }

    public PlayerScore getPlayerScore(int id){
        for (int i = 0; i < playerScores.length; i++){
            if (playerScores[i] != null && id == playerScores[i].getPlayer().getId()){
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
