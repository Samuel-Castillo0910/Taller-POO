package Main;

import Exceptions.DuplicatePlayerException;
import Exceptions.InvalidMatchException;
import GameMode.RankedFlex;
import GameMode.RankedSoloDuo;
import GameMode.Recruitment;

public class Main {
    public static void main(String[] args) {

        GameManager manager = new GameManager();

        System.out.println("================================");
        System.out.println("|     REGISTRANDO JUGADORES    |");
        System.out.println("================================");

        try {
            manager.registerPlayer(new Player(1, 32, "Faker",    Rank.CHALLENGER, Role.MID));
            manager.registerPlayer(new Player(2, 34, "Caps",     Rank.CHALLENGER, Role.MID));
            manager.registerPlayer(new Player(3, 40, "Gumayusi", Rank.DIAMOND,    Role.ADC));
            manager.registerPlayer(new Player(4, 45, "Zeus",     Rank.MASTER,     Role.TOP));
            manager.registerPlayer(new Player(5, 42, "ShowMaker",Rank.CHALLENGER, Role.MID));
            manager.registerPlayer(new Player(6, 50, "Canyon",   Rank.GRANDMASTER,Role.JUNGLE));
            manager.registerPlayer(new Player(7, 60, "Ruler",    Rank.CHALLENGER, Role.ADC));
            manager.registerPlayer(new Player(8, 55, "Oner",     Rank.MASTER,     Role.JUNGLE));
            System.out.println("8 jugadores registrados correctamente\n");
        } catch (DuplicatePlayerException error) {
            System.out.println("Error: " + error.getMessage());
        }

        // Intentar registrar duplicado
        System.out.println("Intentando registrar jugador duplicado (id = 1): ");
        try {
            manager.registerPlayer(new Player(1, 32, "Fake", Rank.IRON, Role.TOP));
        } catch (DuplicatePlayerException error) {
            System.out.println("Error esperado capturado: " + error.getMessage() + "\n");
        }

        System.out.println("================================");
        System.out.println("|     CREANDO PARTIDAS         |");
        System.out.println("================================");

        Match matchSoloDuo    = manager.createMatch(new RankedSoloDuo(21));
        System.out.println("Partida 1 creada: Ranked Solo/Duo");
        Match matchFlex       = manager.createMatch(new RankedFlex(30));
        System.out.println("Partida 2 creada: Ranked Flex");
        Match matchRecruitment = manager.createMatch(new Recruitment());
        System.out.println("Partida 3 creada: Recruitment\n");

        System.out.println("================================");
        System.out.println("|     AGREGANDO JUGADORES      |");
        System.out.println("================================");

        matchSoloDuo.addPlayer(manager.findPlayer(1));
        matchSoloDuo.addPlayer(manager.findPlayer(8));
        System.out.println("SoloDuo: Faker y Oner agregados ("
                + matchSoloDuo.getPlayerCount() + " jugadores)");

        matchFlex.addPlayer(manager.findPlayer(3));
        matchFlex.addPlayer(manager.findPlayer(4));
        matchFlex.addPlayer(manager.findPlayer(7));
        System.out.println("Flex: Gumayusi, Zeus y Ruler agregados ("
                + matchFlex.getPlayerCount() + " jugadores)");

        matchRecruitment.addPlayer(manager.findPlayer(2));
        matchRecruitment.addPlayer(manager.findPlayer(5));
        matchRecruitment.addPlayer(manager.findPlayer(6));
        System.out.println("Recruitment: Caps, ShowMaker y Canyon agregados ("
                + matchRecruitment.getPlayerCount() + " jugadores)");

        // Estado inválido
        System.out.println("\nIntentando iniciar partida sin jugadores: ");
        Match matchVacia = manager.createMatch(new RankedSoloDuo(19));
        try {
            matchVacia.startMatch();
        } catch (InvalidMatchException error) {
            System.out.println("Error esperado capturado: " + error.getMessage() + "\n");
        }

        System.out.println("================================");
        System.out.println("|     INICIANDO PARTIDAS       |");
        System.out.println("================================");

        try {
            matchSoloDuo.startMatch();
            System.out.println("SoloDuo iniciada. Estado: " + matchSoloDuo.getStatus());
            matchFlex.startMatch();
            System.out.println("Flex iniciada. Estado: " + matchFlex.getStatus());
            matchRecruitment.startMatch();
            System.out.println("Recruitment iniciada. Estado: " + matchRecruitment.getStatus());
        } catch (InvalidMatchException error) {
            System.out.println("Error: " + error.getMessage());
        }

        System.out.println("\n================================");
        System.out.println("|     SIMULANDO ESTADISTICAS   |");
        System.out.println("================================");

        //SoloDuo
        matchSoloDuo.updatePlayerScores(1, 10, 2, 8, 15000, true);
        matchSoloDuo.updatePlayerScores(8, 5, 5, 7, 8000, false);

        //Flex
        matchFlex.updatePlayerScores(3, 7, 1, 10, 13000, true);
        matchFlex.updatePlayerScores(4, 5, 3, 6, 11000, true);
        matchFlex.updatePlayerScores(7, 4, 4, 9, 10000, true);

        //Recruitment
        matchRecruitment.updatePlayerScores(2, 6, 2, 4, 9000, true);
        matchRecruitment.updatePlayerScores(5, 2, 5, 3, 7000, false);
        matchRecruitment.updatePlayerScores(6, 8, 0, 12, 14000, true);


        // Terminar partidas
        matchSoloDuo.endMatch();
        matchFlex.endMatch();
        matchRecruitment.endMatch();
        System.out.println("Todas las partidas finalizadas\n");

        System.out.println("================================");
        System.out.println("|     RESUMEN ESTADISTICAS     |");
        System.out.println("================================");

        manager.removePlayer(5);
        System.out.println("Jugador 5 (ShowMaker) eliminado del sistema\n");

        Player[] players = manager.getPlayers();
        Match[] matches = {matchSoloDuo, matchFlex, matchRecruitment};

        for (int i = 0; i < manager.getPlayers().length; i++) {
            if (players[i] != null && players[i].isActive()) {
                // Muestra nombre, rol y rango del jugador
                System.out.println("Jugador: " + players[i].getNickname()
                        + " | Rol: "    + players[i].getRole()
                        + " | Rango: "  + players[i].getRank()
                        + " | Nivel: "  + players[i].getLevel());

                for (Match m : matches) {
                    if (m.getPlayerScore(players[i].getId()) != null) {
                        double kda = m.getPlayerScore(players[i].getId())
                                .getScore().calculateKDA();
                        int lp = m.getMode().calculateScore(
                                m.getPlayerScore(players[i].getId()).getScore());
                        double kdaRedondeado = (int)(kda * 100) / 100.0;

                        System.out.println("  KDA: " + kdaRedondeado
                                + " | Puntaje: " + lp
                                + " | Gano: " + m.getPlayerScore(players[i].getId())
                                .getScore().isWon());
                    }
                }
                System.out.println();
            }
        }
    }
}