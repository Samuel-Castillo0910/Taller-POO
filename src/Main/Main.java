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

        // SoloDuo
        matchSoloDuo.getPlayerScore(1).getScore().setKills(10);
        matchSoloDuo.getPlayerScore(1).getScore().setDeaths(2);
        matchSoloDuo.getPlayerScore(1).getScore().setAssists(8);
        matchSoloDuo.getPlayerScore(1).getScore().setGold(15000);
        matchSoloDuo.getPlayerScore(1).getScore().setWon(true);

        matchSoloDuo.getPlayerScore(8).getScore().setKills(5);
        matchSoloDuo.getPlayerScore(8).getScore().setDeaths(5);
        matchSoloDuo.getPlayerScore(8).getScore().setAssists(7);
        matchSoloDuo.getPlayerScore(8).getScore().setGold(8000);
        matchSoloDuo.getPlayerScore(8).getScore().setWon(false);

        // Flex
        matchFlex.getPlayerScore(3).getScore().setKills(7);
        matchFlex.getPlayerScore(3).getScore().setDeaths(1);
        matchFlex.getPlayerScore(3).getScore().setAssists(10);
        matchFlex.getPlayerScore(3).getScore().setGold(13000);
        matchFlex.getPlayerScore(3).getScore().setWon(true);

        matchFlex.getPlayerScore(4).getScore().setKills(5);
        matchFlex.getPlayerScore(4).getScore().setDeaths(3);
        matchFlex.getPlayerScore(4).getScore().setAssists(6);
        matchFlex.getPlayerScore(4).getScore().setGold(11000);
        matchFlex.getPlayerScore(4).getScore().setWon(true);

        matchFlex.getPlayerScore(7).getScore().setKills(4);
        matchFlex.getPlayerScore(7).getScore().setDeaths(4);
        matchFlex.getPlayerScore(7).getScore().setAssists(9);
        matchFlex.getPlayerScore(7).getScore().setGold(10000);
        matchFlex.getPlayerScore(7).getScore().setWon(true);

        // Recruitment
        matchRecruitment.getPlayerScore(2).getScore().setKills(6);
        matchRecruitment.getPlayerScore(2).getScore().setDeaths(2);
        matchRecruitment.getPlayerScore(2).getScore().setAssists(4);
        matchRecruitment.getPlayerScore(2).getScore().setGold(9000);
        matchRecruitment.getPlayerScore(2).getScore().setWon(true);

        matchRecruitment.getPlayerScore(5).getScore().setKills(2);
        matchRecruitment.getPlayerScore(5).getScore().setDeaths(5);
        matchRecruitment.getPlayerScore(5).getScore().setAssists(3);
        matchRecruitment.getPlayerScore(5).getScore().setGold(7000);
        matchRecruitment.getPlayerScore(5).getScore().setWon(false);

        matchRecruitment.getPlayerScore(6).getScore().setKills(8);
        matchRecruitment.getPlayerScore(6).getScore().setDeaths(0);
        matchRecruitment.getPlayerScore(6).getScore().setAssists(12);
        matchRecruitment.getPlayerScore(6).getScore().setGold(14000);
        matchRecruitment.getPlayerScore(6).getScore().setWon(true);

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