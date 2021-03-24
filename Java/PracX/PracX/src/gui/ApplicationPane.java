package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

import file.FileHandler;
import http.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class ApplicationPane extends StackPane {
	Image image =null;
	Image original = null;
	File fileOriginal = null;
	ImageView imageView=null;
	 Client cl = null;
	 File selectedFile = null;
    public ApplicationPane() {
    	BackgroundFill fill = new BackgroundFill(Color.BLACK, null,null); 
    	this.setBackground(new Background(fill));
    	Text centerText = new Text("No Image Selected"); 
    	 Circle online = new Circle(40,40,10);
    	 Button btnConnect = new Button("Connect");
    	 Text isOnline =new Text( "You are NOT connected to the server!!");
    	centerText.setFill(Color.RED);
    	centerText.setFont(new Font(15));
        BorderPane Bpane = new BorderPane();
        Bpane.setStyle("-fx-background-color: #000011;");
        Bpane.setBottom(centerText);
        VBox vb = new VBox();
        vb.setFillWidth(true);
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setBackground(new Background(new BackgroundFill(null, null ,new Insets(5)))); 
        cancelBtn.setPrefWidth(150);
        cancelBtn.setPrefHeight(50);
        cancelBtn.setFont(new Font(20)); 
        cancelBtn.setTextFill(Color.WHITE);
        cancelBtn.setAlignment(Pos.BASELINE_LEFT);
        cancelBtn.setStyle("-fx-background-color: #000011;");
        cancelBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {				
				cancelBtn.setStyle("-fx-background-color:purple;"
						+ "-fx-border-radius: 50;");
			}       	
        });
        cancelBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {				
				cancelBtn.setStyle("-fx-background-color: #000011;");
			}       	
        });
        cancelBtn.setVisible(false);
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					cl.connect();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				image = original;
				cancelBtn.setVisible(false);
				imageView.setImage(image);
				selectedFile = fileOriginal;
				cl.disconnect();
				centerText.setText("Disconnect then connect again");
				
			}
        	
        });
        ContextMenu Menu = new ContextMenu();
        MenuItem greyScaleItem = new MenuItem("GreyScale");
        greyScaleItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					cl.connect();
					image = cl.preprocessRequest(selectedFile,"GREYSCALE");
					imageView.setImage(image);
					selectedFile = FileHandler.saveProgress(image);
					 cancelBtn.setVisible(true);
					 cl.disconnect();
					
				} catch (IOException e) {
					centerText.setText(e.getMessage()
							+"  ...make sure you are connected");
					System.out.println(selectedFile);
					e.printStackTrace();
					
				} catch (NullPointerException e) {
					centerText.setText(e.getMessage()
							+"  ...make sure you are connected");
					System.out.println(selectedFile);
					e.printStackTrace();
				}
				
			}
        	
        });
        MenuItem RotateItem = new MenuItem("Rotate");
        RotateItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					cl.connect();
					image = cl.preprocessRequest(selectedFile,"ROTATE");
					imageView.setImage(image);
					selectedFile =FileHandler.saveProgress(image);
					cancelBtn.setVisible(true);
					cl.disconnect();
					
				} catch (IOException e) {
					centerText.setText(e.getMessage()
							+"  ...make sure you are connected");					
				} catch (NullPointerException e) {
					centerText.setText(e.getMessage()
							+"  ...make sure you are connected");	
				}
				
			
			}
        	
        });
        MenuItem ErosionItem = new MenuItem("Erode");
        ErosionItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					cl.connect();
					image = cl.preprocessRequest(selectedFile,"EROSION");
					imageView.setImage(image);
					selectedFile =FileHandler.saveProgress(image);
					cancelBtn.setVisible(true);
					cl.disconnect();
					
				} catch (IOException e) {
					centerText.setText(e.getMessage()
							+"  ...make sure you are connected");					
				} catch (NullPointerException e) {
					centerText.setText(e.getMessage()
							+"  ...make sure you are connected");	
				}
			}
        	
        });
        MenuItem DilationItem = new MenuItem("Dilate");
        DilationItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					cl.connect();
					image = cl.preprocessRequest(selectedFile,"DILATION");
					imageView.setImage(image);
					selectedFile =FileHandler.saveProgress(image);
					cancelBtn.setVisible(true);
					cl.disconnect();
					
				} catch (IOException e) {
					centerText.setText(e.getMessage()
							+"  ...make sure you are connected");					
				} catch (NullPointerException e) {
					centerText.setText(e.getMessage()
							+"  ...make sure you are connected");	
				}
			}
        	
        });
        MenuItem CropItem = new MenuItem("Crop");
        CropItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					cl.connect();
					image = cl.preprocessRequest(selectedFile,"CROP");
					imageView.setImage(image);
					selectedFile =FileHandler.saveProgress(image);
					cancelBtn.setVisible(true);
					cl.disconnect();
					
				} catch (IOException e) {
					centerText.setText(e.getMessage()
							+"  ...make sure you are connected");					
				} catch (NullPointerException e) {
					centerText.setText(e.getMessage()
							+"  ...make sure you are connected");	
				}
			}
        	
        });
        
        SeparatorMenuItem smi = new SeparatorMenuItem();
        MenuItem CannyItem = new MenuItem("Canny ");
        CannyItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
						cl.connect();
						image = cl.featureExtraction(selectedFile,"CANNY");
						imageView.setImage(image);
						selectedFile =FileHandler.saveProgress(image);
						cancelBtn.setVisible(true);
					 cl.disconnect();
						
					} catch (IOException e) {
						centerText.setText(e.getMessage()
								+"  ...make sure you are connected");					
					} catch (NullPointerException e) {
						centerText.setText(e.getMessage()
								+"  ...make sure you are connected");	
					}
			}
        	
        });
        
        MenuItem FastItem = new MenuItem("Fast");
        FastItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
						cl.connect();
						image = cl.featureExtraction(selectedFile,"FAST");
						imageView.setImage(image);
						selectedFile =FileHandler.saveProgress(image);
						cancelBtn.setVisible(true);
						cl.disconnect();
						
					} catch (IOException e) {
						centerText.setText(e.getMessage()
								+"  ...make sure you are connected");					
					} catch (NullPointerException e) {
						centerText.setText(e.getMessage()
								+"  ...make sure you are connected");	
					}

			}
        	
        });
        MenuItem ORBItem = new MenuItem("ORB");
        ORBItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
						cl.connect();
						image = cl.featureExtraction(selectedFile,"ORB");
						imageView.setImage(image);
						selectedFile =FileHandler.saveProgress(image);
						cancelBtn.setVisible(true);
						cl.disconnect();
						
					} catch (IOException e) {
						centerText.setText(e.getMessage()
								+"  ...make sure you are connected");					
					} catch (NullPointerException e) {
						centerText.setText(e.getMessage()
								+"  ...make sure you are connected");	
					}

			}
        	
        });
        
        Menu.getItems().addAll(greyScaleItem,RotateItem,ErosionItem,DilationItem,CropItem,smi,CannyItem,FastItem,ORBItem);
        
        Button openBtn = new Button("Open File");
        openBtn.setBackground(new Background(new BackgroundFill(null, null ,new Insets(5)))); 
        openBtn.setPrefWidth(150);
        openBtn.setPrefHeight(50);
        openBtn.setFont(new Font(20)); 
        openBtn.setTextFill(Color.WHITE);
        openBtn.setAlignment(Pos.BASELINE_LEFT);
        openBtn.setStyle("-fx-background-color: #000011;");
        openBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {				
				openBtn.setStyle("-fx-background-color:purple;"
						+ "-fx-border-radius: 50;");
			}       	
        });
        openBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {				
				openBtn.setStyle("-fx-background-color: #000011;");
			}       	
        });
        
        openBtn.setOnAction(new EventHandler<ActionEvent>() {
        	
		@Override
		public void handle(ActionEvent arg0) {
			System.out.println("Hello");
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(new File("."));
			 selectedFile = fileChooser.showOpenDialog(null);
			 
			if (selectedFile != null) {
				FileInputStream input=null;
				fileOriginal = selectedFile;
				try {
					input = new FileInputStream(selectedFile);
				} catch (FileNotFoundException e) {
					centerText.setText(e.getMessage());
					
				}
		         image = new Image(input);
		         original = image;
		         imageView = new ImageView(image);
		         imageView.setPreserveRatio(true);
		         imageView.setFitWidth(600);
		         imageView.setFitHeight(500);
		         imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
		        
					@Override
					public void handle(MouseEvent event) {
						if(event.isSecondaryButtonDown()) {
							Menu.show(imageView,event.getScreenX(),event.getScreenY());
						}
						
					}
		        	 
		         });
		         
		         
		        Bpane.setCenter(imageView);

		        centerText.setText("File selected: " + selectedFile.getName() + "  Right click the image");
		        
			}
			else {
				centerText.setText("File selection cancelled.");
				
			}
			
		}
    	   
       });
        
        Button saveBtn = new Button("Save");
        saveBtn.setBackground(new Background(new BackgroundFill(null, null ,new Insets(5)))); 
        saveBtn.setPrefWidth(150);
        saveBtn.setPrefHeight(50);
        saveBtn.setFont(new Font(20)); 
        saveBtn.setTextFill(Color.WHITE);
        saveBtn.setAlignment(Pos.BASELINE_LEFT);
        saveBtn.setStyle("-fx-background-color: #000011;");
        saveBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {				
    				saveBtn.setStyle("-fx-background-color:purple;"
    						+ "-fx-border-radius: 50;");
    			}       	
            });
        saveBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {				
    				saveBtn.setStyle("-fx-background-color: #000011;");
    			}       	
            });
            
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
			if(image==null) {
				centerText.setText("No Image Selected");
			}else {
				try {
					FileHandler.saveFile(image);
					centerText.setText("Image Saved");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
    	   
       });
        
       
      //  vb.setBackground(new Background(new BackgroundFill(null, new CornerRadii(15) ,new Insets(5))));
        vb.setStyle("-fx-background-color: #000011;"
        		+ "-fx-border-radius: 20;"  
        		+ "-fx-border-color: green");
       vb.getChildren().addAll(openBtn, saveBtn,cancelBtn);
       
       HBox hb = new HBox(35);
      
       online.setFill(Color.RED);
   //    online.fillProperty().addListener();
      
       isOnline.setFill(Color.WHITE);
       isOnline.setFont(new Font(15));
      
       btnConnect.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(5) ,null)));
       btnConnect.setTextFill(Color.WHITE);
       btnConnect.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {				
				btnConnect.setStyle("-fx-background-color:purple;"
						+ "-fx-border-radius: 50;");
			}       	
       });
       btnConnect.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if(online.getFill().equals(Color.GREEN))
				btnConnect.setStyle("-fx-background-color: red;");
				else {
					btnConnect.setStyle("-fx-background-color: green;");
				}
			}       	
       });
       btnConnect.setOnAction(new EventHandler<ActionEvent>() {

   		@Override
   		public void handle(ActionEvent arg0) {
   		//	System.out.println("connect");
   			if(online.getFill().equals(Color.GREEN)) { // when user is already online(disconnect)
   			 cl.disconnect();
   			 online.setFill(Color.RED);
   			 btnConnect.setText("Connect");
   			 btnConnect.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(5) ,null)));
   			 isOnline.setText("You are NOT connected to the server ");
   			centerText.setText("Disconnected");
   			}else if(online.getFill().equals(Color.RED)) { // when user is logging in online(connect)
   			  try {
				cl = new Client();
				 online.setFill(Color.GREEN);
	   			 btnConnect.setText("Disconnect");
	   			 btnConnect.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5) ,null)));
	   			 isOnline.setText("You are connected to the server ");
	   			centerText.setText("You are connected");
			} catch (UnknownHostException e) {
				centerText.setText(e.getMessage());
			} catch (IOException e) {
				centerText.setText(e.getMessage() + "  ;make sure the server is running");
				
			}	
   			
   			}
   		  
   		}
       	   
       });
       hb.getChildren().addAll(online, isOnline,btnConnect);
       Bpane.setTop(hb);
       Bpane.setLeft(vb);
        
        this.getChildren().add(Bpane);
    }
}
