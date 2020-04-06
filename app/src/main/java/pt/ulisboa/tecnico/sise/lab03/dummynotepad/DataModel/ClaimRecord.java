package pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel;

public class ClaimRecord {
    private String title;
    private String plate;
    private String date;
    private String description;
    public ClaimRecord(String title, String plate, String date, String description) {
        this.title = title;
        this.plate = plate;
        this.date = date;
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlate() { return plate; }

    public void setPlate(String plate) { this.plate = plate; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public void setDescription(String description) { this.description = description; }

    public String getDescription() { return description; }

    @Override
    public String toString() {

        return this.title;
    }
}
