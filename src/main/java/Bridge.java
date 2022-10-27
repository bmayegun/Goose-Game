public class Bridge extends Space{
    private int bridgeDestination = 0;

    public Bridge(int position, int bridgeDestination) {
        super(position, "The Bridge");
        this.bridgeDestination = bridgeDestination;
    }

    public int moveRolledValue(Space lastPosition, int dice1, int dice2) {
        return bridgeDestination;
    }
}
