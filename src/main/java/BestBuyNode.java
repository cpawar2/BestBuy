public class BestBuyNode<T> {
    public T data; //Data in the current node
    public BestBuyNode next; //Reference for the next node

    //Constructor which takes an T value which is stored as the data in this Node object.
    public BestBuyNode(T data) {
        this.data = data;
    }

    public BestBuyNode() {
    }
}
