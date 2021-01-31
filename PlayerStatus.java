package simulateAttack;

import java.util.HashMap;

public class PlayerStatus {

		int score;				//scorul jucatorului
		int lives;				//numarul de vieti al jucatorului
		int weaponPrice; 		//pretul armei in points, care se scad din score; pentru achizitie, scorul trebuie sa fie cel putin pretul armei
		int health = 100;		//intre 0 si 100, ce reprezinta procentul de viata ramas
		String nickName;		//nume jucator
		String weaponInHand;	//arma jucatorului
		double positionX;		//coordonata OX a pozitiei jucatorului, o valoare numerica reala
		double positionY;		//coordonata OY a pozitiei jucatorului, o valoare numerica reala
		double distance;		//distanta dintre jucatori
		private static final HashMap<String, Integer> weapons = new HashMap<String, Integer>();
		private static String GAMENAME = "JOC";

		public PlayerStatus() {
			setWeapons();
		}
		
//		private static void setGAMENAME(String gAMENAME) {
//			GAMENAME = gAMENAME;
//		}

		public static String getGameName() {
			return GAMENAME;
		}
		
		public String getNickName() {
			return nickName;
		}
		
		public void movePlayerTo(double positionX, double positionY) {
			this.positionX = positionX;
			this.positionY = positionY;
		}

		public double getPositionX() {
			return positionX;
		}

		public void setPositionX(double positionX) {
			this.positionX = positionX;
		}

		public double getPositionY() {
			return positionY;
		}

		public void setPositionY(double positionY) {
			this.positionY = positionY;
		}

		private static void setWeapons () {
			weapons.put("knife", 1000);
			weapons.put("sniper", 10000	);
			weapons.put("kalashnikov", 20000);
		}
		
		public static void showWeapons() {
			System.out.print("Armele disponibile sunt: ");
			for (String key : weapons.keySet()) {
				if (key.equals("knife")) {
					System.out.println(key);
				} else {
					System.out.println("\t\t\t " + key);
				}
			}
			System.out.println("Ce arma alegi?");
		}
		
		public String getWeaponInHand() {
			return this.weaponInHand;
		}
		
		public static double getDistance(PlayerStatus a, PlayerStatus b) {
			return Math.sqrt(Math.pow((a.positionX - b.positionX), 2) + Math.pow((a.positionY - b.positionY), 2));
		}
		
		void initPlayer(String nickname){
			this.nickName = nickname;
		}
		
		void initPlayer(String nickname, int lives) {
			this.nickName = nickname;
			this.lives = lives;
		}
		
		void initPlayer(String nickname, int lives, int score) {
			this.nickName = nickname;
			this.lives = lives;
			this.score = score;
		}
		
		public boolean shouldAttackOponent(PlayerStatus oponent) {
			boolean result = false;
			if (this.weaponInHand.equals(oponent.weaponInHand)) {
				if (getProbability(this.health, this.score) > getProbability(oponent.health, oponent.score)) {
					result = true;
				} else {
					result = false;
				}
			}
			else {
				if (isStronger(this, oponent)) {
					result = true;					
				} else {
					result = false;
				}
			}
			return result;
		}
		
		void findArtifact(int artifactCode) {
			if (estePerfect(artifactCode)) {
				score += 5000;
				lives++;
				health = 100;
			} else if (estePrim(artifactCode)) {
				score += 1000;
				lives += 2;
				health += 25;
				if (health > 100) {
					health = 100;
				}
			} else if(artifactCode % 2 == 0 && sumaCifrelor(artifactCode)) {
				score -= 3000;
				health -= 25;
				if (health < 1) {
					lives--;
					health = 100;
				}
			} else {
				score += artifactCode;
			}
		}

		int getProbability(int health, int score) {
			return (3 * health + score / 1000) / 4;
		}
		
		boolean isStronger(PlayerStatus player1, PlayerStatus player2) {
			boolean result = false;
			int n = 0;
			if (getDistance(player1, player2) > 1000) {
				n = getWeaponsPair(player1.weaponInHand, player2.weaponInHand);
			}
			if (getDistance(player1, player2) <= 1000) {
				n = getWeaponsPair1(player1.weaponInHand, player2.weaponInHand);
			}
			switch (n) {
				case 1:
					result = true;
					break;
				case 2:
					result = true;
					break;
				case 3:
					result = false;
					break;			
			}	
			return result;
		}
		
		private int getWeaponsPair(String weaponInHand1, String weaponInHand2) {
			int result = 0;
			if (weaponInHand1.equals("sniper") && (weaponInHand2.equals("kalashnikov") || weaponInHand2.equals("knife"))) {
				result = 1;				
			} else if (weaponInHand2.equals("kalashnikov") && weaponInHand2.equals("knife")) {
				result = 2;				
			} else {
				return 3;
			}
			return result;
		}
		
		private int getWeaponsPair1(String weaponInHand1, String weaponInHand2) {
			int result = 0;
			if (weaponInHand1.equals("kalashnikov") && (weaponInHand2.equals("sniper") || weaponInHand2.equals("knife"))) {
				result = 1;				
			} else if (weaponInHand1.equals("sniper") && weaponInHand2.equals("knife")) {
				result = 2;				
			} else {
				return 3;
			}
			return result;
		}

		private static boolean estePrim(int n) {
			boolean estePrim = false;			
			for (int i = 2; i * i < n; i++) {
				if (n % i == 0) {
					estePrim = false;
					break;
				}
			}			
			return estePrim;
		}

		private static boolean estePerfect(int n) {
			boolean estePerfect = false;
			int sumaDivizori = 0;			
			for (int i = 1; i < n; i++) {
				if (n % i == 0) {
					sumaDivizori += i;
				}
			}			
			if (sumaDivizori == n) {
				estePerfect = true;
			}			
			return estePerfect;
		}

		private static boolean sumaCifrelor(int n) {
			boolean divide3 = false;
			int sumaCifrelor = 0;
			while (n != 0) {
				sumaCifrelor += n % 10;
				n /= 10;
			}
			if (sumaCifrelor % 3 == 0) {
				divide3 = true;
			}			
			return divide3;
		}
		
		boolean setWeaponInHand(String weapon) {
			boolean result = false;
			for (int i = 0; i < weapons.size(); i++) {
				if (weapons.containsKey(weapon) && weapons.get(weapon) <= score) {
					this.weaponInHand = weapon;
					score -= weapons.get(weapon);
					result = true;
				}
			}
			return result;
		}

		public void getStats() {
			System.out.println("Jucatorul " + this.nickName + " are urmatoarele caracteristici:");
			System.out.println("\tNr vieti:  " + this.lives);
			System.out.println("\tViata: " + this.health);
			System.out.println("\tArma: " + this.weaponInHand);
			System.out.println("\tLocatie: " + this.positionX + ", " + this.positionY);			
		}
}
