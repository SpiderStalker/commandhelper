package com.laytonsmith.tools.docgen;

import com.laytonsmith.PureUtilities.UIUtils;
import com.laytonsmith.core.CHLog;
import com.laytonsmith.core.Installer;
import com.laytonsmith.core.Prefs;
import com.laytonsmith.tools.Interpreter;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteGlassLookAndFeel;

/**
 *
 * @author lsmith
 */
public class DocGenUI extends javax.swing.JFrame {

	private static final File jarLocation = new File(Interpreter.class.getProtectionDomain().getCodeSource().getLocation().getFile()).getParentFile();
	private static final File chDirectory = new File(jarLocation, "CommandHelper/");
	DocGenUIHandler handler;
	DocGenUIHandler.ProgressManager manager = new DocGenUIHandler.ProgressManager() {

					public void setProgress(final Integer i) {
						SwingUtilities.invokeLater(new Runnable() {

							public void run() {
								if(i == null){
									progress.setIndeterminate(true);
									progress.setValue(0);
								} else {
									progress.setIndeterminate(false);
									progress.setValue(i);
								}
							}
						});
					}
					
					public void setStatus(final String status){
						SwingUtilities.invokeLater(new Runnable() {

							public void run() {
								infoLabel.setText(status);								
							}
						});
					}
				};
	/**
	 * Creates new form DocGenUI
	 */
	public DocGenUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		UIUtils.centerWindow(this);	
		this.setTitle("Documentation Generator");
	}
	
	private void doUpload(){
		infoLabel.setText("Info:");
		manager.setProgress(null);
		try {
			try{
				handler = new DocGenUIHandler(manager, new URL("http://" + wikiURL.getText()), username.getText(), 
						new String(password.getPassword()), "CommandHelper/" + (staged.isSelected()?"Staged/":""),
						"CommandHelper/", staged.isSelected(),
						functions.isSelected(), examples.isSelected(), events.isSelected(), templates.isSelected());
				handler.go();
				manager.setStatus("Done.");
			} catch(DocGenUIHandler.APIException e){
				e.printStackTrace();
				manager.setStatus("Error: " + e.getMessage());
			} catch(DocGenUIHandler.QuickStop e){
				manager.setStatus("Info: Stopped");
			} catch(Exception e){
				e.printStackTrace();
				throw e;
			}
		} catch (MalformedURLException ex) {
			manager.setStatus("Info: Malformed URL");
		} catch(UnknownHostException e){
			manager.setStatus("Error: Unknown host: " + e.getMessage());
		} catch (Exception ex) {
			manager.setStatus(ex.getClass().getSimpleName() + ": " + ex.getMessage());
		} finally {
			handler = null;
			manager.setProgress(0);
			SwingUtilities.invokeLater(new Runnable() {

				public void run() {
					uploadButton.setText("Upload");
				}
			});
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        wikiURL = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        staged = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        functions = new javax.swing.JCheckBox();
        examples = new javax.swing.JCheckBox();
        events = new javax.swing.JCheckBox();
        templates = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        uploadButton = new javax.swing.JButton();
        progress = new javax.swing.JProgressBar();
        infoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Wiki hostname:");

        wikiURL.setText("wiki.sk89q.com");

        jLabel2.setText("Username:");

        jLabel3.setText("Password:");

        staged.setSelected(true);
        staged.setText("Staged?");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        functions.setSelected(true);
        functions.setText("Functions");

        examples.setSelected(true);
        examples.setText("Examples");

        events.setSelected(true);
        events.setText("Events");

        templates.setSelected(true);
        templates.setText("Templates");

        jLabel4.setText("Upload what?");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(functions)
                    .addComponent(examples)
                    .addComponent(events)
                    .addComponent(templates)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(functions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(examples)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(events)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(templates)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        uploadButton.setText("Upload");
        uploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadButtonActionPerformed(evt);
            }
        });

        infoLabel.setText("Info: Waiting");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(password)
                            .addComponent(wikiURL)
                            .addComponent(username)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(staged)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(uploadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                        .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(infoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(wikiURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(staged)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uploadButton))
                    .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadButtonActionPerformed
        uploadButton.setText("Stop");
		if(handler != null){
			handler.stop();
		} else {
			new Thread(new Runnable() {

				public void run() {
					Installer.Install(chDirectory);
					try {
						Prefs.init(new File(chDirectory, "preferences.txt"));
						CHLog.initialize(chDirectory);
					} catch (IOException ex) {
						Logger.getLogger(DocGenUI.class.getName()).log(Level.SEVERE, null, ex);
					}
					doUpload();
				}
			}, "UploadThread").start();
		}
    }//GEN-LAST:event_uploadButtonActionPerformed
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				public void run() {
					try {
						UIManager.setLookAndFeel(new SubstanceGraphiteGlassLookAndFeel());
						JFrame.setDefaultLookAndFeelDecorated(true);
					} catch (UnsupportedLookAndFeelException ex) {
						Logger.getLogger(DocGenUI.class.getName()).log(Level.SEVERE, null, ex);
					} 
				}
			});
		} catch (Exception ex) {
			Logger.getLogger(DocGenUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch(NoClassDefFoundError e){
			//This shouldn't be an error, they just won't have the awesome
			//theme
			System.err.println("NoClassDefFoundError occured while trying to install LaF. Do you have Substance installed?");
			if(Prefs.DebugMode()){
				e.printStackTrace(System.err);
			}
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new DocGenUI().setVisible(true);
			}
		});
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox events;
    private javax.swing.JCheckBox examples;
    private javax.swing.JCheckBox functions;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField password;
    private javax.swing.JProgressBar progress;
    private javax.swing.JCheckBox staged;
    private javax.swing.JCheckBox templates;
    private javax.swing.JButton uploadButton;
    private javax.swing.JTextField username;
    private javax.swing.JTextField wikiURL;
    // End of variables declaration//GEN-END:variables
}