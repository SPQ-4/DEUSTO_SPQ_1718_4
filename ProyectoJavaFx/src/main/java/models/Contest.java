package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contest {

  private int id;
  private StringProperty title;
  private StringProperty description;
  private StringProperty startingDate;
  private StringProperty endingDate;
  private StringProperty minimumParticipants;
  private StringProperty maximumParticipants;
  private StringProperty entryFee;

  public Contest(int id, String title, String description, String startingDate, String endingDate, String minimumParticipants, String maximumParticipants, String entryFee) {
    this.id = id;
    this.title = new SimpleStringProperty(title);
    this.description = new SimpleStringProperty(description);
    this.startingDate = new SimpleStringProperty(startingDate);
    this.endingDate = new SimpleStringProperty(endingDate);
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

  public void setDescription(String description) {
    this.description.set(description);
  }

  public String getDescription() {
    return this.description.get();
  }

  public StringProperty descriptionProperty() {
    return this.description;
  }

  public void setStartingDate(String startingDate) {
    this.startingDate.set(startingDate);
  }

  public String getStartingDate() {
    return this.startingDate.get();
  }

  public StringProperty startingDateProperty() {
    return this.startingDate;
  }

  public void setEndingDate(String endingDate) {
    this.endingDate.set(endingDate);
  }

  public String getEndingDate() {
    return this.endingDate.get();
  }

  public StringProperty endingDateProperty() {
    return this.endingDate;
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
