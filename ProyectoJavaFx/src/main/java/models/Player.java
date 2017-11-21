package models;

public class Player {
	private String player_shirt;
	private double player_value;
	private String position;
	private String team;
	private String nationality;
	private int points;
	public Player(String player_shirt,String position, String team, String nationality) {
		this.player_shirt=player_shirt;
		this.position=position;
		this.team=team;
		this.nationality=nationality;
	}
	public String toString() {
		return this.player_shirt+" "+this.team;
	}
}
