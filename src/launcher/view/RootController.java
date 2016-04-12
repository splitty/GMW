package launcher.view;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import launcher.MainApp;


public class RootController {
	
		
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
	
    
    @FXML
    private void handleChan() {
    	try {
            Desktop.getDesktop().browse(new URI("http://4chan.org"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        };
    }
    
    @FXML
    private void handleGMW() {
    	try {
            Desktop.getDesktop().browse(new URI("http://splitmane.com"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }
    
    @FXML
    private void handleClose() {
        System.exit(0);
    }
    
    
    
    
    @FXML
    private void handleImprove() {
    	try {
            Desktop.getDesktop().browse(new URI("http://splitmane.com/improve"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    private void handleTwitter() {
    	try {
            Desktop.getDesktop().browse(new URI("http://twitter.com/SplitMane"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }   
    
    @FXML
    private void handleAbout() {
    	try {
            Desktop.getDesktop().browse(new URI("http://splitmane.com/about"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }
    
}