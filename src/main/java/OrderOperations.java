import java.util.Iterator;
import java.util.LinkedList;

public class OrderOperations {
    BestBuyQueue  bestBuyQueue = new BestBuyQueue();

    BestBuyLinkedList orderLinkList = new BestBuyLinkedList();

    public static  OrderOperations orderOperations = new OrderOperations();

    public static OrderDAO orderDAO = new OrderDAO();

    public OrderOperations() {
        LinkedList<Order> ordersFromDB = new OrderDAO().listOrders();
        Iterator<Order> orderIterator = ordersFromDB.iterator();
        while (orderIterator.hasNext()){
            Order order = orderIterator.next();
            orderLinkList.insert(order);
            bestBuyQueue.enqueue(order);
        }

    }

    public void showOrderQueue() {
        bestBuyQueue.display();
    }

    public void updateOrder(int orderNO,int orderStatusNumber){
        BestBuyNode traversalNode = orderLinkList.head;
        boolean orderStatusUpdated = false;

        while(traversalNode!=null){
            Order order = (Order) traversalNode.data;
            if(order.orderNO==orderNO){
                    order.status=Order.orderStatusMap.get(orderStatusNumber);
                    orderStatusUpdated=true;
            }
            traversalNode = traversalNode.next;
        }
        if(orderStatusUpdated){
            orderDAO.updateOrderStatus(orderNO,Order.orderStatusMap.get(orderStatusNumber));
            System.out.println("--------------------------------------------SUCCESSFULLY UPDATED ORDER STATUS----------------------------------------------");
        }else{
            System.out.println("---------------------------------------------FAILED TO UPDATE ORDER STATUS, PLEASE RETRY-----------------------------------");
        }
    }


}
