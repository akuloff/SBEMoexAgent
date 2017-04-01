package TradeEnvironment;

import com.alfajavatrading.TradePrimitives.OrderState;
import com.alfajavatrading.TradePrimitives.TradeOrder;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by mpoke_000 on 21.03.2017.
 */
public class TradeOrdersContainer implements Serializable{
    private HashMap<String, TradeOrder> activeOrdersClientId = new HashMap<>();
    private HashMap<String, TradeOrder> activeOrdersSystemId = new HashMap<>();
    private HashMap<String, TradeOrder> processedOrders = new HashMap<>();

    public TradeOrder getActiveOrderByClientId(String clientOrderId){
        TradeOrder order = null;
        if (activeOrdersClientId.containsKey(clientOrderId)) {
            order = activeOrdersClientId.get(clientOrderId);
        }
        return order;
    }

    public void addActiveOrder(TradeOrder order) {
        activeOrdersClientId.put(order.getClientOrderId(), order);
        activeOrdersSystemId.put(order.getOrderId(), order);
    }

    public void addProcessedOrder(TradeOrder order) {
        processedOrders.put(order.getOrderId(), order);
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
