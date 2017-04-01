package TradeEnvironment;

import java.io.*;

/**
 * Created by mpoke_000 on 30.03.2017.
 */
public class Library {

    public static TradeOrdersContainer readOrdersContainerFromFile(String filename) {
        TradeOrdersContainer container = null;

        try {
            ObjectInput input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
            container = (TradeOrdersContainer) input.readObject();

            System.out.println("container readed from file ...");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return container;
    }

    public static void saveOrdersContainerToFile(TradeOrdersContainer container, String filename) {
        ObjectOutput output;
        if (container != null) {
            try {
                output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
                output.writeObject(container);
                output.flush();

                System.out.println("trades cointainer saved to file: " + filename);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
