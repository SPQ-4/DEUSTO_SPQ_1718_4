package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contest {

    private int id;
	private StringProperty title;
	private StringProperty minimumParticipants;
    private StringProperty maximumParticipants;
    private StringProperty entryFee;

	public Contest(int id, String title, String minimumParticipants, String maximumParticipants, String entryFee) {
		this.id = id;
	    this.title = new SimpleStringProperty(title);
		this.minimumParticipants = new SimpleStringProperty(minimumParticipants);
        this.maximumParticipants = new SimpleStringProperty(maximumParticipants);
        this.entryFee = new SimpleStringProperty(entryFee);
    }

    public void setId(int id) {
	    this.id = id;
    }

    public int getId() {
	    return this.id;
    }

	public void setTitle(String title) {
		this.title.set(title);
	}

	public String getTitle() {
		return this.title.get();
	}

	public StringProperty titleProperty() {
		return this.title;
	}

    public void setMinimumParticipants(String minimumParticipants) {
        this.minimumParticipants.set(minimumParticipants);
    }

    public String getMinimumParticipants() {
        return this.minimumParticipants.get();
    }

    public StringProperty minimumParticipantsProperty() {
        return this.minimumParticipants;
    }

    public void setMaximumParticipants(String maximumParticipants) {
        this.maximumParticipants.set(maximumParticipants);
    }

    public String getMaximumParticipants() {
        return this.maximumParticipants.get();
    }

    public StringProperty maximumParticipantsProperty() {
        return this.maximumParticipants;
    }

    public void setEntryFee(String entryFee) {
        this.entryFee.set(entryFee);
    }

    public String getEntryFee() {
        return this.entryFee.get();
    }

    public StringProperty entryFeeProperty() {
        return this.entryFee;
    }

}
