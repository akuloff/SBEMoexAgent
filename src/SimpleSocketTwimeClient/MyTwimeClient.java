package SimpleSocketTwimeClient;

import TradeEnvironment.TradeOrdersContainer;
import com.alfajavatrading.TradePrimitives.TradeOrder;
import sbe.ExecutionSingleReportDecoder;
import sbe.NewOrderRejectDecoder;
import sbe.NewOrderSingleResponseDecoder;
import sbe.OrderCancelResponseDecoder;

/**
 * Created by mpoke_000 on 31.03.2017.
 */
public class MyTwimeClient extends AbstractTwimeClient{
    private TradeOrdersContainer tradeOrdersContainer;

    @Override
    protected void onNewOrderReject(NewOrderRejectDecoder decoder) {
        super.onNewOrderReject(decoder);
        System.out.println("order reject: " + decoder.toString());
    }

    @Override
    protected void onNewOrderSingleResponse(NewOrderSingleResponseDecoder decoder) {
        super.onNewOrderSingleResponse(decoder);
        System.out.println("single order response: " + decoder.toString());
    }

    @Override
    protected void onOrderCancelResponse(OrderCancelResponseDecoder decoder) {
        super.onOrderCancelResponse(decoder);
        System.out.println("order cancel: " + decoder.toString());
    }

    @Override
    protected void onExecutionSingleReport(ExecutionSingleReportDecoder decoder) {
        super.onExecutionSingleReport(decoder);
        System.out.println("execution report: " + decoder.toString());
    }

    public TradeOrdersContainer getTradeOrdersContainer() {
        return tradeOrdersContainer;
    }

    public MyTwimeClient setTradeOrdersContainer(TradeOrdersContainer tradeOrdersContainer) {
        this.tradeOrdersContainer = tradeOrdersContainer;
        return this;
    }

    public void sendNewOrderSingle(){
        //TradeOrder order = new TradeOrder();
    }
}
