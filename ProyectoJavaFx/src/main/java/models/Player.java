package models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {
	private StringProperty playerShirt;
	private DoubleProperty player_value;
	private StringProperty player_position;
	private StringProperty player_team;
	private StringProperty player_nationality;
	private DoubleProperty player_points;
	public Player(String player_shirt,String position, String team, String nationality, double player_value) {
		this.playerShirt=new SimpleStringProperty(player_shirt);
		this.player_position=new SimpleStringProperty(position);
		this.player_team=new SimpleStringProperty(team);
		this.player_nationality=new SimpleStringProperty(nationality);
		this.player_value=new SimpleDoubleProperty(player_value);
		iniciarPoints();
	}
	public void iniciarPoints() {
		this.player_points=new SimpleDoubleProperty();
	}
	public String toString() {
		return this.playerShirt.get()+" "+this.player_team.get();
	}
	public void setPlayerShirt(String player_shirt) {
		this.playerShirt.set(player_shirt);
	}
	public String getPlayerShirt() {
		return this.playerShirt.get();
	}
	public StringProperty playerShirtProperty() {
		return this.playerShirt;
	}
	public void setPlayer_value(Double player_value) {
		this.player_value.set(player_value);
	}
	public Double getPlayer_value() {
		return this.player_value.get();
	}
	public DoubleProperty player_valueProperty() {
		return this.player_value;
	}
	public String getPlayer_position() {
		return this.player_position.get();
	}
	public StringProperty player_positionProperty() {
		return this.player_position;
	}
	public void setPlayer_position(String player_position) {
		this.player_position.set(player_position);
	}
	public String getPlayer_team() {
		return this.player_team.get();
	}
	public StringProperty player_teamProperty() {
		return this.player_team;
	}
	public void setPlayer_team(String player_team) {
		this.player_team.set(player_team);
	}
	public String getPlayer_nationality() {
		return this.player_nationality.get();
	}
	public StringProperty player_nationalityProperty() {
		return this.player_nationality;
	}
	public void setPlayer_nationality(String player_nationality) {
		this.player_nationality.set(player_nationality);
	}
	public Double getPlayer_points() {
		return this.player_points.get();
	}
	public DoubleProperty player_pointsProperty() {
		return this.player_points;
	}
	public void setPlayer_points(double i) {
		this.player_points.set(i);
	}
}
