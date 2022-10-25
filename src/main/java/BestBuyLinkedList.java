//Custom linkedlist for the application
public class BestBuyLinkedList<T> {

    public BestBuyNode head;

    //function to add an element to the list at the end if there are element already and at the start if no elements are there
    public void insert(T data) {
        BestBuyNode newNode = new BestBuyNode(data);
        if(head == null) {
            head = newNode;
        }else {
            BestBuyNode n = head;
            while(n.next != null) {
                n = n.next;
            }
            n.next = newNode;
        }
    }

    //function to add an element at the start of the list
    public void insertAtStart(T data) {
        BestBuyNode newNode = new BestBuyNode(data);

        newNode.next = head;
        head = newNode;
    }

    //function to add an element at a given index
    public void insertAt(int index, T data) {
        if(index == 0){
            insertAtStart(data); //We already have a function to do this.
        }else{
            BestBuyNode newNode = new BestBuyNode(data);

            BestBuyNode currentNode = head;
            for(int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }
    }

    //function to delete an element at the given index
    public void deleteAt(int index) {
        if(index == 0) {
            head = head.next;
        }else{
            BestBuyNode currentNode = head;
            for(int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            currentNode.next = currentNode.next.next;
        }
    }

    //function to show the list
    public void show() {
        BestBuyNode currentNode = head;
        if(currentNode == null){
            System.out.println("Linked list is empty");
        }
        else {
            while(currentNode != null) {
                System.out.print(currentNode.data + " ");
                currentNode = currentNode.next;
            }
        }
    }

    public void showList() {
        BestBuyNode traversalNode = head;
        while (traversalNode!=null){
            System.out.print(traversalNode.data+" ");
            traversalNode=traversalNode.next;
        }
    }
}