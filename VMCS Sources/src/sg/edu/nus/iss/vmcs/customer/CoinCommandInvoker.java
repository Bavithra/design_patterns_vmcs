package sg.edu.nus.iss.vmcs.customer;
/**
 * Command interface for various coin operations.
 */
import java.util.HashMap;

public class CoinCommandInvoker {
    private HashMap <Integer,CoinCommand> coinCommands;

    public CoinCommandInvoker() {
        this.coinCommands = new HashMap<>();
    }
    
    public void addCommand(Integer coinAction, CoinCommand coinCommand)
    {
         coinCommands.put(coinAction, coinCommand);
    }
    
    public void dispatch(Integer coinAction, Object object)
    {
         CoinCommand coinCommand = coinCommands.get(coinAction);
         coinCommand.execute(object);
    }

}
