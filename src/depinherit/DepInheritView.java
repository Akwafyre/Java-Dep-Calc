/*
 * DepCalcViewView.java
 */

package depinherit;
import business.*;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 * The application's main frame.
 */
public class DepInheritView extends FrameView {

    public DepInheritView(SingleFrameApplication app) {
        super(app);

        initComponents();
        jtblSched.setName(null); // ensures the table state errod does not appear
        
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = DepInheritApp.getApplication().getMainFrame();
            aboutBox = new DepInheritAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        DepInheritApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxtAssetNm = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxtCost = new javax.swing.JTextField();
        jtxtSalvage = new javax.swing.JTextField();
        jtxtLife = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jbtnClear = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jradSL = new javax.swing.JRadioButton();
        jradDDL = new javax.swing.JRadioButton();
        jbtnCalc = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblSched = new javax.swing.JTable();
        jrad1_5 = new javax.swing.JRadioButton();
        sumOfd = new javax.swing.JRadioButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jmnuSave = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        buttonGroup1 = new javax.swing.ButtonGroup();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(depinherit.DepInheritApp.class).getContext().getResourceMap(DepInheritView.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jtxtAssetNm.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtAssetNm.setText(resourceMap.getString("jtxtAssetNm.text")); // NOI18N
        jtxtAssetNm.setName("jtxtAssetNm"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jtxtCost.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtCost.setText(resourceMap.getString("jtxtCost.text")); // NOI18N
        jtxtCost.setName("jtxtCost"); // NOI18N

        jtxtSalvage.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtSalvage.setText(resourceMap.getString("jtxtSalvage.text")); // NOI18N
        jtxtSalvage.setName("jtxtSalvage"); // NOI18N

        jtxtLife.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtLife.setText(resourceMap.getString("jtxtLife.text")); // NOI18N
        jtxtLife.setName("jtxtLife"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jbtnClear.setText(resourceMap.getString("jbtnClear.text")); // NOI18N
        jbtnClear.setName("jbtnClear"); // NOI18N
        jbtnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClearActionPerformed(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        buttonGroup1.add(jradSL);
        jradSL.setText(resourceMap.getString("jradSL.text")); // NOI18N
        jradSL.setName("jradSL"); // NOI18N

        buttonGroup1.add(jradDDL);
        jradDDL.setText(resourceMap.getString("jradDDL.text")); // NOI18N
        jradDDL.setName("jradDDL"); // NOI18N

        jbtnCalc.setText(resourceMap.getString("jbtnCalc.text")); // NOI18N
        jbtnCalc.setName("jbtnCalc"); // NOI18N
        jbtnCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCalcActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jtblSched.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtblSched.setName("jtblSched"); // NOI18N
        jScrollPane1.setViewportView(jtblSched);

        buttonGroup1.add(jrad1_5);
        jrad1_5.setText(resourceMap.getString("jrad1_5.text")); // NOI18N
        jrad1_5.setName("jrad1_5"); // NOI18N

        buttonGroup1.add(sumOfd);
        sumOfd.setText(resourceMap.getString("sumOfd.text")); // NOI18N
        sumOfd.setName("sumOfd"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxtAssetNm, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jrad1_5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jradSL)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtCost, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jtxtSalvage, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtLife, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbtnClear))))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jbtnCalc))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jradDDL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sumOfd)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(151, 151, 151))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtAssetNm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtSalvage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtLife, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnClear))
                .addGap(37, 37, 37)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jradSL)
                    .addComponent(jradDDL)
                    .addComponent(jrad1_5)
                    .addComponent(sumOfd))
                .addGap(6, 6, 6)
                .addComponent(jbtnCalc)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        jmnuSave.setText(resourceMap.getString("jmnuSave.text")); // NOI18N
        jmnuSave.setName("jmnuSave"); // NOI18N
        fileMenu.add(jmnuSave);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(depinherit.DepInheritApp.class).getContext().getActionMap(DepInheritView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 372, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCalcActionPerformed
       double cost, salvage;
       int life;
       String assetName;
       statusMessageLabel.setText("");

       try {                 
          cost = Double.parseDouble(jtxtCost.getText());
          salvage = Double.parseDouble(jtxtSalvage.getText());
          assetName = jtxtAssetNm.getText();
          life = Integer.parseInt(jtxtLife.getText());
       }
       catch(NumberFormatException e){
         statusMessageLabel.setText("A Value is not numeric");
         return;
       }
       if (!jradSL.isSelected() && !jradDDL.isSelected() && !jrad1_5.isSelected() && !sumOfd.isSelected()){
          statusMessageLabel.setText("No Depreciation type selscted");
          return;
       }
         
    Asset a = null;
    if (jradSL.isSelected()){
      a = new AssetSL(jtxtAssetNm.getText(),cost,salvage,life);          
    }
    else if (jradDDL.isSelected()){
     a = new Asset2DL(jtxtAssetNm.getText(),cost,salvage,life);
    }
    else if (jrad1_5.isSelected()){
     a = new Asset1_5DL(jtxtAssetNm.getText(),cost,salvage,life);
    }
    else if (sumOfd.isSelected()){
      a = new AssetSYD(jtxtAssetNm.getText(),cost,salvage,life);
    }
    else {
        statusMessageLabel.setText("No Method Selected");
        return;
    }
    if (!a.GetErrorMessage().isEmpty()){
        statusMessageLabel.setText(a.GetErrorMessage());
        return;
    }
       
      String[] cols = sumOfd.isSelected() 
              ? new String[] {"Year", "Beg.bal", "Ann.dep", "End.dep", "Dep.rate"} 
              : new String[] {"Year","Beg.bal", "Ann.dep", "End.dep"};
      
       String [][] table = new String[life][4];
       DefaultTableModel mod = new DefaultTableModel(table, cols);
       jtblSched.setModel(mod);
       
       DefaultTableCellRenderer rend = new DefaultTableCellRenderer();
       rend.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
       jtblSched.setDefaultRenderer(Object.class, rend);
       
       NumberFormat curr = NumberFormat.getCurrencyInstance();
       for(int i=1; i <= life; i++){
           jtblSched.setValueAt(i,i-1,0);
           jtblSched.setValueAt( curr.format(a.getBegBal(i)), i-1, 1);
           jtblSched.setValueAt(curr.format(a.getAnnDep(i)), i-1, 2);
           jtblSched.setValueAt(curr.format(a.getEndBal(i)), i-1, 3);
           if (sumOfd.isSelected()){
              jtblSched.setValueAt(((AssetSYD) a).getAnnDepRate(i), i-1, 4);
           }
           
       }
    }//GEN-LAST:event_jbtnCalcActionPerformed

    private void jbtnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClearActionPerformed
        statusMessageLabel.setText("");
        jtxtSalvage.setText("");
        jtxtAssetNm.setText("");
        jtxtLife.setText("");
        jtxtCost.setText("");
        buttonGroup1.clearSelection();
        jtxtAssetNm.requestFocusInWindow();
        jtblSched.setModel(new DefaultTableModel());
    }//GEN-LAST:event_jbtnClearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnCalc;
    private javax.swing.JButton jbtnClear;
    private javax.swing.JMenuItem jmnuSave;
    private javax.swing.JRadioButton jrad1_5;
    private javax.swing.JRadioButton jradDDL;
    private javax.swing.JRadioButton jradSL;
    private javax.swing.JTable jtblSched;
    private javax.swing.JTextField jtxtAssetNm;
    private javax.swing.JTextField jtxtCost;
    private javax.swing.JTextField jtxtLife;
    private javax.swing.JTextField jtxtSalvage;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JRadioButton sumOfd;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
