package woo.app.main;

import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;  

//import other classes
import woo.exceptions.MissingFileAssociationException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<Storefront> {

  private Input<String> _filename;
  
  /** @param receiver */
  public DoSave(Storefront receiver) {
    super(Label.SAVE, receiver);
    
    if(_receiver.emptyFileName()){
      _filename = _form.addStringInput(Message.newSaveAs());
      
    }
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() { 
    try{
      if(_receiver.emptyFileName()){
        _form.parse();
        _receiver.saveAs(_filename.value());
      } else {
        _receiver.save();
      }
    }catch(IOException e) {
      e.printStackTrace(); 
    }
    
  }
    
}
