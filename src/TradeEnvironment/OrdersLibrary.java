package TradeEnvironment;

import com.alfajavatrading.TradePrimitives.TradeOrder;

import java.util.HashMap;

/**
 * Created by mpoke_000 on 21.03.2017.
 */
public class OrdersLibrary {
    private HashMap<String, TradeOrder> activeOrders = new HashMap<>();

    public TradeOrder getActiveOrder(String clientOrderId){
        TradeOrder order = null;
        if (activeOrders.containsKey(clientOrderId)) {
            order = activeOrders.get(clientOrderId);
        }
        return order;
    }
}
