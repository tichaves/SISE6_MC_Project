package pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel;

public class Note {
    private String _title;
    private String _body;
    public Note(String title, String body) {
        this._title = title;
        this._body = body;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        this._title = title;
    }

    public String getBody() {
        return _body;
    }

    public void setBody(String body) {
        this._body = body;
    }

    @Override
    public String toString() {
        return _title;
    }
}
