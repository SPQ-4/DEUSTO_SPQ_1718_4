package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {

	private StringProperty name;
	private StringProperty position;
    private StringProperty club;
    private StringProperty points;
    private StringProperty salary;

	public Player(String name, String position, String club, String points, String salary) {
		this.name = new SimpleStringProperty(name);
		this.position = new SimpleStringProperty(position);
        this.club = new SimpleStringProperty(club);
        this.points = new SimpleStringProperty(points);
        this.salary = new SimpleStringProperty(salary);
    }

	public void setName(String name) {
		this.name.set(name);
	}

	public String getName() {
		return this.name.get();
	}

	public StringProperty nameProperty() {
		return this.name;
	}

    public void setPosition(String position) {
        this.position.set(position);
    }

    public String getPosition() {
        return this.position.get();
    }

    public StringProperty positionProperty() {
        return this.position;
    }

    public void setClub(String club) {
        this.club.set(club);
    }

    public String getClub() {
        return this.club.get();
    }

    public StringProperty clubProperty() {
        return this.club;
    }

    public void setPoints(String points) {
        this.points.set(points);
    }

    public String getPoints() {
        return this.points.get();
    }

    public StringProperty pointsProperty() {
        return this.points;
    }

    public void setSalary(String salary) {
        this.salary.set(salary);
    }

    public String getSalary() {
        return this.salary.get();
    }

    public StringProperty salaryProperty() {
        return this.salary;
    }

}
