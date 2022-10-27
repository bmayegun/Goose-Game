public class Space {
    private int position;
    private String name;

    public Space(int position) {
        this.position = position;
        this.name = null;
    }

    public Space(int position, String name) {
        this.position = position;
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int moveRolledValue(Space lastPosition, int dice1, int dice2) {
        return position;
    }

    @Override
    public String toString() {
        return position +
                (name != null ? ", " + name : "");
    }
}
