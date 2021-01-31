package simulateAttack;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		PlayerStatus newPlayer = new PlayerStatus();
		PlayerStatus newPlayer1 = new PlayerStatus();
		newPlayer.initPlayer("1P1", 5, 150000);
		newPlayer1.initPlayer("2P2", 4, 85000);
		System.out.println("Alege un numar norocos pentru jucatorul " + newPlayer.getNickName() + "!");
		newPlayer.findArtifact(scanner.nextInt());
		System.out.println("Alege un numar norocos pentru jucatorul " + newPlayer1.getNickName() + "!");
		newPlayer1.findArtifact(scanner.nextInt());
		System.out.println("Alege o arma pentru jucatorul " + newPlayer.getNickName() + "!");
		PlayerStatus.showWeapons();
		newPlayer.setWeaponInHand(scanner.next());
		System.out.println("Alege o arma pentru jucatorul " + newPlayer1.getNickName() + "!");
		PlayerStatus.showWeapons();
		newPlayer1.setWeaponInHand(scanner.next());
		System.out.println("Seteaza locatia jucatorului " + newPlayer.getNickName() + "!");
		newPlayer.movePlayerTo(scanner.nextDouble(), scanner.nextDouble());
		System.out.println("Seteaza locatia jucatorului " + newPlayer1.getNickName() + "!");
		newPlayer1.movePlayerTo(scanner.nextDouble(), scanner.nextDouble());
		newPlayer.getStats();
		newPlayer1.getStats();
		
		System.out.println("Distanta dintre jucatori este de: " + PlayerStatus.getDistance(newPlayer, newPlayer1));
		System.out.println("Jucatorul " + newPlayer.getNickName() + (newPlayer.shouldAttackOponent(newPlayer1) ? "" : " n-") + 
				" ar avea sanse sa castige impotriva jucatorului " + newPlayer1.getNickName());

		scanner.close();
	}

}
