package TradeEnvironment;

import com.alfajavatrading.TradePrimitives.OrderState;
import com.alfajavatrading.TradePrimitives.TradeOrder;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by mpoke_000 on 21.03.2017.
 */
public class OrdersLibrary implements Serializable{
    private HashMap<String, TradeOrder> activeOrders = new HashMap<>();
    private HashMap<String, TradeOrder> processedOrders = new HashMap<>();

    public TradeOrder getActiveOrder(String clientOrderId){
        TradeOrder order = null;
        if (activeOrders.containsKey(clientOrderId)) {
            order = activeOrders.get(clientOrderId);
        }
        return order;
    }

    public void addActiveOrder(TradeOrder order) {
        activeOrders.put(order.getClientOrderId(), order);
    }

    public void addProcessedOrder(TradeOrder order) {
        processedOrders.put(order.getClientOrderId(), order);
    }

    public void addOrder(TradeOrder order){
       OrderState state = order.getOrderState();
       if(OrderState.Partial.equals(state) || OrderState.Placed.equals(state) || OrderState.Processing.equals(state)) {
           addActiveOrder(order);
       } else {
           addProcessedOrder(order);
       }
    }

}
