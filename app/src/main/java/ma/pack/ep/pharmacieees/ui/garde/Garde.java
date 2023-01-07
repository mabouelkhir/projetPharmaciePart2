package ma.pack.ep.pharmacieees.ui.garde;

public class Garde {
    private int id;
    private String type;

    public Garde(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Garde{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
