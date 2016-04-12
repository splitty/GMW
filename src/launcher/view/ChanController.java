package launcher.view;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import launcher.MainApp;

public class ChanController {
	
	@FXML
	private TextField scapeChan;
	@FXML
	private TextField scapeFolder;
	@FXML
	private TextArea scapeCon;
	@FXML
	private TextArea scapeHow;
	@FXML
	private Button chanGrab;
	@FXML
	private Button folderSelectChan;
	@FXML
    private ImageView chanCheck;
	@FXML
	private ImageView chanDL;
	
	private MainApp mainApp;
	
	
	
	
	
	static String srcChan;  
	static String srcThread;
	static String srcComplete;
	static String srcRName;
	static String srcSName;
	public static String folderPath;
	
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp; 
    }
	
	 @FXML
	 private void initialize() {
		 Image img = new Image("resources/error.png"); 
         chanCheck.setImage(img);    
         scapeHow.setText("1.  Select your master folder using the 'Select Folder' button."
	          		+ "\n"
	          		+ "\n"
	          		+ "2.  Confirm your folder location in the folder bar, there will be a sub-directory created for each thread."
	          		+ "\n"
	          		+ "\n"
	          		+ "3.  If the name of the thread can't be determined, the media will be placed in the folder 4C."
	          		+ "\n"
	          		+ "\n"
	          		+ "4.  Paste the URL of the thread in the 'URL to Thread' text area. "
	          		+ "\n"
	          		+ "\n"
	          		+ "EX: http://boards.4chan.org/e/thread/##/eBoard"
	          		+ "\n"
	          		+ "\n"
	          		+ "5.  Click 'Grab!' and the files will be downloaded."
	          		+ "\n"
	          		+ "\n"
	          		+ "This application collects absolutely no data on your downloads, and will not monitor them either.  Therefore, it is entirely up to the user"
	          		+ "to maintain their downloads and the take responsibility for what they have on their machine as a result of this program."
	          		+ "\n"
	          		+ "\n"
	          		+ "My sincerest condolences to you, I am only helping to feed your media addictions."
	          		+ "\n"
	          		+ "\n"
	          		+ "https://twitter.com/SplitMane"
	          		+ "\n"
	          		+ "Email: splitmane@gmail.com"
	          		+ "\n"
	          		+ "\n"
	          		+ "Have a nice day.");
	 }
	
	 
	 @FXML
	 public void selectFoldersHandle() {
			selectFoldersChan();
	    }
	 
	 @FXML
	 public void grabHandle() {
            chanGrabTransfer();
	 }
	 
	 
	 public void chanGrabTransfer(){    	
	    	parseChan();
	    }
	 
	 private void selectFoldersChan(){
	    	DirectoryChooser directoryChooser = new DirectoryChooser();
	        File selectedDirectory = 
	                directoryChooser.showDialog(mainApp.primaryStage);
	        
	        if(selectedDirectory == null){        	
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText("No Directory Selected");
	            alert.setContentText("Please Choose a Valid Directory");
	            alert.showAndWait();
	            Image img = new Image("resources/error.png"); 
	            chanCheck.setImage(img);
	            scapeFolder.setText("No folder selected.");
	        }else{
	        	folderPath = selectedDirectory.getAbsolutePath();
	        	Alert alertinfo = new Alert(AlertType.INFORMATION);
	            alertinfo.setTitle("Success!");
	            alertinfo.setHeaderText("Good Choice!");
	            alertinfo.setContentText("Directory Valid");
	            alertinfo.showAndWait(); 
	            Image img = new Image("resources/check.png"); 
	            chanCheck.setImage(img);
	            scapeFolder.setText(folderPath);
	        }
	    }
	 
	@FXML
    private void parseChan(){	
    	final Task<Void> task;
        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
            	try{
            		Image chanLoad = new Image("resources/dance.gif");
            		chanDL.setImage(chanLoad);
            		String chanInput = scapeChan.getText();
            	   	Document doc = Jsoup.connect(chanInput).timeout(0).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();
            	   	Elements file = doc.select("a.fileThumb");
            	   	Element folder = doc.select("span.subject").first();
            	   	
            			srcThread = folder.text();
            			srcComplete = "4C" + srcThread;
        				System.out.println("Thread Name: " + srcThread);
        				String srcRWhite = srcComplete.replaceAll("\\s+", "");
        				String srcPass1 = srcRWhite.replaceAll("\\(.+?\\)", "");
        				String srcPass2 = srcPass1.replaceAll("\\.", "");
                        srcRName = srcPass2.replaceAll("[?:<>]", ""); 
                        srcSName = srcRName.replaceAll("\\/", "");
        				File dir = new File(folderPath + "//" + srcSName);
        				scapeCon.appendText("\n" + "New Thread" + "\n" + "Folder Name-  " + srcSName + "\n");
        				dir.mkdir();
            	
            	   	for (Element el : file) {
            	   		srcChan = el.absUrl("href");
            	   		System.out.println("File Found!");
            	   		System.out.println("src attribute is : " + srcChan);
            	   		scapeCon.appendText(srcChan + "\n");
            	   		grabMeChan(); 	                        				
            				}
            	   	
            			} catch (Exception ex) {
            				System.err.println("There was an error");
            				Logger.getLogger(ChanController.class.getName()).log(Level.SEVERE, null, ex);
                }
            		
            	Image chanDone = new Image("resources/done.png");
        		chanDL.setImage(chanDone);
        		scapeCon.appendText("\n" + "Folder Name-  " + srcSName + "\n");
        		scapeCon.appendText("\n" + "DONE");
                return null;
            }
        };
 	   Thread thread = new Thread(task);
 	   thread.setDaemon(true);
 	   thread.start(); 	   
    }
	
	 public void grabMeChan() throws Exception{
		 int indexname = srcChan.lastIndexOf("/");       
         String srcname = srcChan.substring(indexname, srcChan.length());
         String srcTWhite = srcRName.replaceAll("\\s+", "");
         String srcTName = srcTWhite.replaceAll("[?:<>]/", ""); 
         String srcNName = srcTName.replaceAll("\\/", "");
         String Path = folderPath + "//" + srcNName + "//";
         String srcname2 = srcname.replaceAll("\\s+[?:<>]", "");
         String myString = Path + srcname2;
         try {
         	URL url=new URL(srcChan);
         	HttpURLConnection connection =
         			(HttpURLConnection) url.openConnection();
         	float totalDataRead=0;
         	java.io.BufferedInputStream in = new java.io.BufferedInputStream(connection.getInputStream());
         	java.io.FileOutputStream fos = new java.io.FileOutputStream(myString);
         	java.io.BufferedOutputStream bout = new BufferedOutputStream(fos,1024);
         	//Declaring the buffer size
         	byte[] data = new byte[1024];
         	int i=0;
         	while((i=in.read(data,0,1024))>=0)
         	{
         		totalDataRead=totalDataRead+i;
         		bout.write(data,0,i);
 			
         	}	
         	
         	bout.close();
         	in.close();
                     
         }
        
         catch (FileNotFoundException e) {
         	e.printStackTrace();
         } catch (IOException e) {
         	e.printStackTrace();
         }       
		
}   

	
}
