import com.bethecoder.ascii_table.ASCIITable;
//Custom stack for the application
public class BestBuyStack<T> {
    BestBuyNode top;
    // Constructor
    BestBuyStack() { this.top = null; }

    // Utility function to add an element x in the stack
    public void push(T x) // insert at the beginning
    {
        // create new node temp and allocate memory
        BestBuyNode temp = new BestBuyNode();

        // check if stack (heap) is full. Then inserting an
        //  element would lead to stack overflow
        if (temp == null) {
            //System.out.print("\nHeap Overflow");
            return;
        }

        // initialize data into temp data field
        temp.data = x;

        // put top reference into temp link
        temp.next = top;

        // update top reference
        top = temp;
    }

    // Utility function to check if the stack is empty or
    // not
    public boolean isEmpty() { return top == null; }

    // Utility function to return top element in a stack
    public T peek()
    {
        // check for empty stack
        if (!isEmpty()) {
            return (T) top.data;
        }
        else {
            //System.out.println("Stack is empty");
            return null;
        }
    }

    // Utility function to pop top element from the stack
    public void pop() // remove at the beginning
    {
        // check for stack underflow
        if (top == null) {
            //stack overflow
            return;
        }

        // update the top pointer to point to the next node
        top = (top).next;
    }

    public void display() {
        if (top == null) {
            //stack underflow
            System.out.println("--------------------------------------------------------------YOUR CART IS EMPTY!-------------------------------------------------------------------");


        } else{
           String [] headers = {"PRODUCT_ID","PRODUCT_NAME","CATEGORY","QUANTITY","PRICE"};
            BestBuyNode temp = top;
            //
            int countOfElements = 0;
            while (temp != null) {

                countOfElements += 1;
                temp = temp.next;
            }

            String[][] productsData  = new String[countOfElements][5];
            int row = 0;
            temp = top;
            while (temp != null) {
                Order orderToIterate = (Order) temp.data;
                productsData[row][0] = orderToIterate.productID;
                productsData[row][1] = orderToIterate.productName;
                productsData[row][2] = orderToIterate.category;
                productsData[row][3] = orderToIterate.quantity+"";
                productsData[row][4] = orderToIterate.price+" $";

                row += 1;
                temp = temp.next;
            }
                ASCIITable.getInstance().printTable(headers, productsData);

        }


    }
}
