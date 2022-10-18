package bestbuy.util;

import bestbuy.model.BestBuyLinkedList;
import bestbuy.model.BestBuyNode;
import bestbuy.model.BestBuyQueue;
import bestbuy.model.Order;

public class OrderOperations {
    BestBuyQueue  bestBuyQueue = new BestBuyQueue();

    BestBuyLinkedList orderLinkList = new BestBuyLinkedList();

    public static  OrderOperations orderOperations = new OrderOperations();

    public OrderOperations() {
        Order order1 = new Order();
        order1.status=Order.ORDER_PLACED;
        order1.orderNO=1;
        order1.category="office";
        order1.productID="A1597";
        order1.price=300;
        order1.productName="twin size Desk";
        order1.quantity=1;
        Order order2 = new Order();
        order2.status=Order.ORDER_PLACED;
        order2.orderNO=2;
        order2.category="Boots";
        order2.productID="A3479";
        order2.price=400;
        order2.productName="Canvas shoes";
        order2.quantity=1;
        Order order3 = new Order();
        order3.status=Order.ORDER_PLACED;
        order3.orderNO=3;
        order3.category="clothing";
        order3.productName="Denim jacket";
        order3.productID="B5496";
        order3.price=800;
        order3.quantity=1;

        orderLinkList.insert(order1);
        orderLinkList.insert(order2);
        orderLinkList.insert(order3);
        bestBuyQueue.enqueue(order1);
        bestBuyQueue.enqueue(order2);
        bestBuyQueue.enqueue(order3);

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
            System.out.println("--------------------------------------------SUCCESSFULLY UPDATED ORDER STATUS----------------------------------------------");
        }else{
            System.out.println("---------------------------------------------FAILED TO UPDATE ORDER STATUS, PLEASE RETRY-----------------------------------");
        }
    }


}
