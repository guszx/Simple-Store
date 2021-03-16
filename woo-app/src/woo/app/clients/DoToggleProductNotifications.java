package woo.app.clients;

import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;                                                                                                                        
//import other classes
import woo.app.exceptions.UnknownClientKeyException;
import woo.exceptions.InvalidClientKeyException;
import woo.app.exceptions.UnknownProductKeyException;
import woo.exceptions.InvalidProductKeyException;

/**
 * Toggle product-related notifications.
 */
public class DoToggleProductNotifications extends Command<Storefront> {

  private Input<String> _clientKey;
  private Input<String> _productKey;

  public DoToggleProductNotifications(Storefront storefront) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, storefront);
    _clientKey = _form.addStringInput(Message.requestClientKey());
    _productKey = _form.addStringInput(Message.requestProductKey());
  }

  @Override
  public void execute() throws DialogException {
    try{
      _form.parse();
      if(_receiver.toggleProductNotifications(_clientKey.value(), _productKey.value()))
        _display.popup(Message.notificationsOn(_clientKey.value(), _productKey.value()));
      else
        _display.popup(Message.notificationsOff(_clientKey.value(), _productKey.value()));

    } catch(InvalidClientKeyException e){
      throw new UnknownClientKeyException(e.getInvalidKey());
    } catch(InvalidProductKeyException e){
      throw new UnknownProductKeyException(e.getInvalidKey());
    }
  }

}
