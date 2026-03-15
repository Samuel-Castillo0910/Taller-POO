package Main;

import Exceptions.InvalidMatchException;
import GameMode.GameMode;
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
    public void addPlayer (Player player) {
        if (playerCount >= playerScores.length) {
            System.out.println("La partida está llena");
            return;
        }
        //FOR PARA QUE EL PRIMER ELEMENTO DEL ARREGLO QUE DETECTE COMO NULL, CREE UN OBJETO AHI
        for (int i = 0; i < playerScores.length; i++) {
            if (playerScores[i] == null) {
                playerScores[i] = new PlayerScore(player, new Score(0, 0, 0, 0, false));
                playerCount++;
                break;
            }
        }
    }


    //FUNCIÓN PARA QUITAR JUGADORES
    public void removePlayer (int id){
        /*
        FOR PARA QUE BUSQUE EN EL ARREGLO UN PLAYER CON EL ID INDICADO
        (EL != NULL ES PARA QUE NO BUSQUE EN LOS ELEMENTOS NULOS)
        */
        for (int i = 0; i < playerScores.length; i++){
            if (playerScores[i] != null && id == playerScores[i].getPlayer().getId()){
                playerScores[i]=null;
                playerCount--;
                break;
            }

        }
    }

    //
    public void startMatch() throws InvalidMatchException {
    if (!mode.validateQueueSize(playerCount)) {
        throw new InvalidMatchException("Jugadores insuficientes para iniciar la partida");
    }
    status = MatchStatus.IN_PROGRESS;
}

    public void endMatch (){
        status = MatchStatus.FINISHED;
    }

    public Player findPlayer(int id){
        for (int i = 0; i < playerScores.length; i++){
            if (playerScores[i] != null && id == playerScores[i].getPlayer().getId()){
                return playerScores[i].getPlayer();
            }
        }
        System.out.println("Jugador no esta en partida");
        return null;
    }

    public PlayerScore getPlayerScore(int id) {
        for (int i = 0; i < playerScores.length; i++) {
            if (playerScores[i] != null && id == playerScores[i].getPlayer().getId()) {
                return playerScores[i];
            }
        }
        return null;
    }

    public PlayerScore updatePlayerScores(int id, int kills, int deaths, int assists, int gold, boolean won) {

        for (int i = 0; i < playerScores.length; i++){
            if (playerScores[i] != null && id == playerScores[i].getPlayer().getId()){
                playerScores[i].getScore().setKills(kills);
                playerScores[i].getScore().setDeaths(deaths);
                playerScores[i].getScore().setAssists(assists);
                playerScores[i].getScore().setGold(gold);
                playerScores[i].getScore().setWon(won);

                return playerScores[i];
            }
        }
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
