package Main;

import Exceptions.DuplicatePlayerException;
import Exceptions.InvalidMatchException;
import GameMode.GameMode;
import Utils.GameUtils;

//atributos
public class GameManager {
    private Player[] players;
    private Match[] matches;
    private int playerCount;
    private int matchCount;
    //constructor
    public GameManager(){
        this.players = new Player[100];
        this.matches = new Match[100];
        this.playerCount = 0;
        this.matchCount = 0;
    }
    //resize partidas
    private void resizeMatchesArray() {
        matches = (Match[]) GameUtils.resizeArray(matches, matches.length * 2);
    }
    //resize jugadores
    private void resizePlayersArray() {
        players = (Player[]) GameUtils.resizeArray(players, players.length * 2);
    }
    //metodo registrar jugadores
    public void registerPlayer(Player player) throws DuplicatePlayerException{
        //buscar duplicado
        for (int i = 0; i < playerCount; i++) {
            if (players[i] != null && players[i].isActive()) {
                if(players[i].getId() == player.getId()) {
                    throw new DuplicatePlayerException("El jugador con id " + player.getId() + " ya existe");
                }
            }
        }
        //resize si está lleno
        if (playerCount == players.length){
            resizePlayersArray();
        }
        //agregar al contador
        players[playerCount++] = player;
    }
    //metodo remover jugadores
    public void removePlayer(int id){
        //recorrer el arreglo
        for (int i = 0; i < playerCount; i++) {
            //verificar que no sea null y que el id coincida
            if (players[i] != null && players[i].getId() == id) {
                //eliminación lógica
                players[i].setActive(false);
                return;
            }
        }
    }
    //getters
    public Player[] getPlayers() {
        return players;
    }

    public Match[] getMatches() {
        return matches;
    }
    //metodo encontrar jugadores
    public Player findPlayer(int id){
        //recorrer el arreglo
        for (int i = 0; i < playerCount; i++) {
            //verificar que no sea null y que el id coincida
            if (players[i] != null && players[i].getId() == id) {
                //retornar si se encontro el jugador
                return players[i];
            }
        }
        //si no se encontro
        return null; //no encontrado
    }
    //crear partida
    public Match createMatch(GameMode mode) throws InvalidMatchException {

        if (mode == null) {
            throw new InvalidMatchException("El modo de juego no puede ser nulo");
        }
        //si el arreglo se llena, resize
        if(matchCount ==  matches.length){
            resizeMatchesArray();
        }
        //crear una nueva partida con modo de juego
        Match match = new Match(matchCount + 1, mode);
        //anadir la partida al contador de partidas
        matches[matchCount++] = match;
        //retornar resultado
        return match;
    }
}
