import com.bethecoder.ascii_table.ASCIITable;
//Custom queue for the application
public class BestBuyQueue<T> {
    BestBuyNode front, rear;

    public BestBuyQueue() { this.front = this.rear = null; }

    // Method to add an key to the queue.
    public void enqueue(T key)
    {

        // Create a new LL node
        BestBuyNode temp = new BestBuyNode(key);

        // If queue is empty, then new node is front and
        // rear both
        if (this.rear == null) {
            this.front = this.rear = temp;
            return;
        }

        // Add the new node at the end of queue and change
        // rear
        this.rear.next = temp;
        this.rear = temp;
    }

    // Method to remove an key from queue.
    void dequeue()
    {
        // If queue is empty, return NULL.
        if (this.front == null)
            return;

        // Store previous front and move front one node
        // ahead
        BestBuyNode temp = this.front;
        this.front = this.front.next;

        // If front becomes NULL, then change rear also as
        // NULL
        if (this.front == null)
            this.rear = null;
    }

    public void display(){
        BestBuyNode traversalNode = front;
        int countOfElements = 0;
        while(traversalNode!=null){
            countOfElements+=1;
            traversalNode=traversalNode.next;
        }
        String[][] productsData  = new String[countOfElements][Order.headers.length];
        traversalNode=front;
        int row=0;
        boolean ordersPresent = false;
        while(traversalNode!=null){
            Order orderToIterate = (Order) traversalNode.data;

            productsData[row][0] = orderToIterate.orderNO+"";
            productsData[row][1] = orderToIterate.productID;
            productsData[row][2] = orderToIterate.productName;
            productsData[row][3] = orderToIterate.category;
            productsData[row][4] = orderToIterate.quantity+"";
            productsData[row][5] = orderToIterate.price+"$";
            productsData[row][6] = orderToIterate.status;
            traversalNode=traversalNode.next;
            row+=1;
            ordersPresent=true;
        }
        if(ordersPresent) {
            ASCIITable.getInstance().printTable(Order.headers, productsData);
        }else{
            System.out.println("--------------------------------------------------------------NO ORDERS FOUND!-------------------------------------------------------------------");
        }


    }
}