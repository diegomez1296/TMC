/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMC;

import TMC.Classes.Block;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Windows 8
 */
public class TMC_Application extends javax.swing.JFrame {

    ArrayList<Block> MapBlocks = new ArrayList<>();
    ArrayList<Block> AllTypesOfBlocks = new ArrayList<>();

    Boolean LanguagePL = false;
    Block ActualBlock;
    
    private void setLanguage (Boolean LanguagePL)
    {
        if(LanguagePL)
        {
            jComboBox_CreatingMapOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bloczki", "Spawn Tanków", "Tło" }));         
            selectColorJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wybierz kolor tła", "Żółty", "Zielony", "Czerwony", "Niebieski" }));

            jMenu_File.setText("Plik");
            jMenu_Edit.setText("Edycja");
            jMenu_Authors.setText("Autorzy");
            
            this.setTitle("Tanks 2k17 - Kreator Map");
            
            jLabel_LangPL.setIcon(new ImageIcon ("Assets\\Flags\\FlagaPL_Click.gif"));
            jLabel_LangENG.setIcon(new ImageIcon ("Assets\\Flags\\FlagaENG.gif"));
        }
        else
        {
            jComboBox_CreatingMapOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blocks", "Tank Spawn", "Change Background Color" }));
            selectColorJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Background Color", "Yellow", "Green", "Red", "Blue" }));
            
            jMenu_File.setText("File");
            jMenu_Edit.setText("Edit");
            jMenu_Authors.setText("Authors");
            
            this.setTitle("Tanks 2k17 - Map Creator");
            
            jLabel_LangPL.setIcon(new ImageIcon ("Assets\\Flags\\FlagaPL.gif"));
            jLabel_LangENG.setIcon(new ImageIcon ("Assets\\Flags\\FlagaENG_Click.gif"));
        }
        
    }
    
    private void getInfoAboutBlocks()
    {
        for (Block Block : MapBlocks) {
            System.out.println(Block.toString());
        }
    }
    private void createDefaultBlocks()
    {
        int LocationBlockX = 5;
        int LocationBlockY = 5;
                
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Block tmp = new Block("Assets\\Blocks_Tex\\DefaultBlock.gif", false, false, false, true, LocationBlockX, LocationBlockY);
                
                GridPanelJPanel.add(tmp.Bloczek);
                tmp.Bloczek.setVisible(true);
                tmp.Bloczek.addMouseListener(new java.awt.event.MouseAdapter() 
                {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    Blocks_Click(evt);
                    }
                });
                MapBlocks.add(tmp);
                LocationBlockX+=40;
                
            }
            LocationBlockY+=40;
            LocationBlockX =5;
        }
        //ActualBlock
        ActualBlock = new Block("Assets\\Blocks_Tex\\DefaultBlock.gif", false, false, false, true, 20, 380);
        ActualBlock.Bloczek.setVisible(true);
        jPanel_Tools.add(ActualBlock.Bloczek);
    }
    
    
    private void LoadingDatabaseOfBlocks()
    {
        //Block(String ImageFromAddress, boolean isDestroyable, boolean isCollidingtanks, boolean isCollidingBullets boolean isWater)
        //Liquid_Blocks
        AllTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Default_Blocks\\WaterBlock.gif", false, true, false, true));
        AllTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Volcano_Blocks\\Lawa.gif", false, true, false, true));
        
        //Destoyable_Blocks
        AllTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Default_Blocks\\RedBlock.gif", true, true, true, false));
        AllTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Volcano_Blocks\\MagmaBlack.gif", true, true, true, false));
        AllTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Volcano_Blocks\\MagmaRed.gif", true, true, true, false));
        
        //Undestoyable_Blocks
        AllTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Default_Blocks\\MetalBlock.gif", false, true, true, false));
        
        //Green_Blocks
        AllTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Default_Blocks\\GreenBlock.gif", false, false, false, false));
        
        
    }
        private void BlocksInMenuTools (int startIndex, int endIndex)
    {
        if (endIndex > AllTypesOfBlocks.size())
        {
            endIndex = AllTypesOfBlocks.size();
        }
        
        int LocationBlockX = 10;
        int LocationBlockY = 5;
        int counter_BlocksInRow = 0;
        
        for (int i = startIndex; i < endIndex; i++) {
                
            jPanel_Items.add(AllTypesOfBlocks.get(i).Bloczek);
            AllTypesOfBlocks.get(i).Bloczek.setVisible(true);
            AllTypesOfBlocks.get(i).Bloczek.setLocation(LocationBlockX, LocationBlockY);
            
            LocationBlockX += 50;
            counter_BlocksInRow++;
            
            
            if (counter_BlocksInRow == 3)
            {
            counter_BlocksInRow = 0;
            LocationBlockX = 10;
            LocationBlockY += 40;
            }
                
            AllTypesOfBlocks.get(i).Bloczek.addMouseListener(new java.awt.event.MouseAdapter() 
                {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    BlocksInMenuTools_Click(evt);
                    }
                });
        }
    }
    
    
    private void startApplication()
    {
        setLanguage(LanguagePL);
        createDefaultBlocks();
        LoadingDatabaseOfBlocks();
        BlocksInMenuTools (0, AllTypesOfBlocks.size());
        
        //jLabel_ActualBlock.setIcon(new ImageIcon ("Assets\\Blocks_Tex\\DefaultBlock.gif"));
        jLabel_DeleteBlock.setIcon(new ImageIcon ("Assets\\Blocks_Tex\\Delete_Block.gif"));
        
        
        
        //getInfoAboutBlocks();
        selectColorJComboBox.setVisible(false);
    }
    public TMC_Application() {
  
        initComponents();
        startApplication();
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainJPanel = new javax.swing.JPanel();
        GridPanelJPanel = new javax.swing.JPanel();
        jPanel_Tools = new javax.swing.JPanel();
        jComboBox_CreatingMapOptions = new javax.swing.JComboBox<>();
        jPanel_Items = new javax.swing.JPanel();
        selectColorJComboBox = new javax.swing.JComboBox<>();
        jLabel_DeleteBlock = new javax.swing.JLabel();
        jLabel_LangPL = new javax.swing.JLabel();
        jLabel_LangENG = new javax.swing.JLabel();
        jLabel_Description = new javax.swing.JLabel();
        MenuJMenu = new javax.swing.JMenuBar();
        jMenu_File = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu_Edit = new javax.swing.JMenu();
        jMenu_Authors = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tank Map Creator");
        setPreferredSize(new java.awt.Dimension(800, 700));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 700));

        MainJPanel.setBackground(new java.awt.Color(204, 204, 255));
        MainJPanel.setMaximumSize(new java.awt.Dimension(800, 636));
        MainJPanel.setMinimumSize(new java.awt.Dimension(800, 636));
        MainJPanel.setPreferredSize(new java.awt.Dimension(800, 636));
        MainJPanel.setVerifyInputWhenFocusTarget(false);

        GridPanelJPanel.setBackground(new java.awt.Color(204, 204, 204));
        GridPanelJPanel.setAlignmentX(10.0F);
        GridPanelJPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        GridPanelJPanel.setMaximumSize(new java.awt.Dimension(610, 610));
        GridPanelJPanel.setMinimumSize(new java.awt.Dimension(610, 610));
        GridPanelJPanel.setPreferredSize(new java.awt.Dimension(610, 610));

        javax.swing.GroupLayout GridPanelJPanelLayout = new javax.swing.GroupLayout(GridPanelJPanel);
        GridPanelJPanel.setLayout(GridPanelJPanelLayout);
        GridPanelJPanelLayout.setHorizontalGroup(
            GridPanelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        GridPanelJPanelLayout.setVerticalGroup(
            GridPanelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );

        jPanel_Tools.setBackground(new java.awt.Color(255, 102, 102));

        jComboBox_CreatingMapOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_CreatingMapOptionsActionPerformed(evt);
            }
        });

        jPanel_Items.setBackground(new java.awt.Color(51, 255, 51));

        selectColorJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Background Color", "Yellow", "Green", "Red", "Blue" }));
        selectColorJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectColorJComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_ItemsLayout = new javax.swing.GroupLayout(jPanel_Items);
        jPanel_Items.setLayout(jPanel_ItemsLayout);
        jPanel_ItemsLayout.setHorizontalGroup(
            jPanel_ItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ItemsLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(selectColorJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_ItemsLayout.setVerticalGroup(
            jPanel_ItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ItemsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectColorJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(278, Short.MAX_VALUE))
        );

        jLabel_DeleteBlock.setBackground(new java.awt.Color(51, 51, 255));
        jLabel_DeleteBlock.setForeground(new java.awt.Color(102, 102, 255));
        jLabel_DeleteBlock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_DeleteBlock.setLabelFor(jComboBox_CreatingMapOptions);
        jLabel_DeleteBlock.setMaximumSize(new java.awt.Dimension(40, 40));
        jLabel_DeleteBlock.setMinimumSize(new java.awt.Dimension(40, 40));
        jLabel_DeleteBlock.setPreferredSize(new java.awt.Dimension(40, 40));
        jLabel_DeleteBlock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_DeleteBlockMouseClicked(evt);
            }
        });

        jLabel_LangPL.setText("LangPL");
        jLabel_LangPL.setMaximumSize(new java.awt.Dimension(52, 30));
        jLabel_LangPL.setMinimumSize(new java.awt.Dimension(52, 30));
        jLabel_LangPL.setPreferredSize(new java.awt.Dimension(52, 30));
        jLabel_LangPL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_LangPLMouseClicked(evt);
            }
        });

        jLabel_LangENG.setText("LangENG");
        jLabel_LangENG.setMaximumSize(new java.awt.Dimension(52, 30));
        jLabel_LangENG.setMinimumSize(new java.awt.Dimension(52, 30));
        jLabel_LangENG.setPreferredSize(new java.awt.Dimension(52, 30));
        jLabel_LangENG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_LangENGMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel_ToolsLayout = new javax.swing.GroupLayout(jPanel_Tools);
        jPanel_Tools.setLayout(jPanel_ToolsLayout);
        jPanel_ToolsLayout.setHorizontalGroup(
            jPanel_ToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ToolsLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel_ToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_Items, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_ToolsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel_DeleteBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_ToolsLayout.createSequentialGroup()
                        .addComponent(jComboBox_CreatingMapOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel_ToolsLayout.createSequentialGroup()
                        .addComponent(jLabel_LangPL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_LangENG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel_ToolsLayout.setVerticalGroup(
            jPanel_ToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ToolsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox_CreatingMapOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_Items, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel_DeleteBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_ToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_LangPL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_LangENG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
        );

        jLabel_Description.setBackground(new java.awt.Color(153, 255, 255));
        jLabel_Description.setText("Opis...");
        jLabel_Description.setMaximumSize(new java.awt.Dimension(30, 15));
        jLabel_Description.setMinimumSize(new java.awt.Dimension(30, 15));

        javax.swing.GroupLayout MainJPanelLayout = new javax.swing.GroupLayout(MainJPanel);
        MainJPanel.setLayout(MainJPanelLayout);
        MainJPanelLayout.setHorizontalGroup(
            MainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainJPanelLayout.createSequentialGroup()
                .addGroup(MainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(GridPanelJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_Description, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Tools, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
        MainJPanelLayout.setVerticalGroup(
            MainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Tools, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainJPanelLayout.createSequentialGroup()
                .addComponent(GridPanelJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Description, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenu_File.setText("Plik");

        jMenuItem1.setText("Nowy");
        jMenu_File.add(jMenuItem1);

        jMenuItem3.setText("Zapisz");
        jMenu_File.add(jMenuItem3);

        MenuJMenu.add(jMenu_File);

        jMenu_Edit.setText("Edycja");
        MenuJMenu.add(jMenu_Edit);

        jMenu_Authors.setText("Autorzy");
        MenuJMenu.add(jMenu_Authors);

        setJMenuBar(MenuJMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxCreaner()
    {
        selectColorJComboBox.setVisible(false);
        
        for (Block Blocks : AllTypesOfBlocks) {
            Blocks.Bloczek.setVisible(false);
        }
        
        ActualBlock.Bloczek.setVisible(false);
    }
    
    private void jComboBox_CreatingMapOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_CreatingMapOptionsActionPerformed
        switch(jComboBox_CreatingMapOptions.getSelectedIndex())
        {
            case 0:
                jComboBoxCreaner();
                ActualBlock.Bloczek.setVisible(true);
                BlocksInMenuTools (0, AllTypesOfBlocks.size());
                jPanel_Items.setBackground(Color.GREEN);
                ShowInfo("Ustawiamy bloczki");
                break;
            case 1:
                jComboBoxCreaner();
                jPanel_Items.setBackground(Color.blue);
                ShowInfo("Ustawiamy Spawny Tanków");
                break;
            
            case 2:
                //GridPanelJPanel.setBackground(Color.red);
                jComboBoxCreaner();
                selectColorJComboBox.setVisible(true);
                jPanel_Items.setBackground(Color.red);
                ShowInfo("Ustawiamy BackGround");
                break;
        }
        
    }//GEN-LAST:event_jComboBox_CreatingMapOptionsActionPerformed

    private void selectColorJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectColorJComboBoxActionPerformed
        // TODO add your handling code here:
        //wybieranie kolorow
        
        switch(selectColorJComboBox.getSelectedIndex()){
            case 0:
                break;
                
            case 1:
                GridPanelJPanel.setBackground(Color.yellow);
                break;
            case 2:
                GridPanelJPanel.setBackground(Color.green);
                break;
            case 3:
                GridPanelJPanel.setBackground(Color.red);
                break;
            case 4:
                GridPanelJPanel.setBackground(Color.blue);
        }
    }//GEN-LAST:event_selectColorJComboBoxActionPerformed

    private void jLabel_DeleteBlockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_DeleteBlockMouseClicked
        ActualBlock.Bloczek.setIcon(new ImageIcon("Assets\\Blocks_Tex\\DefaultBlock.gif"));
        ActualBlock.IsDestroyable = false;
        ActualBlock.IsCollidingWithTanks = false;  
        ActualBlock.IsCollidingWithBullet = false; 
        ActualBlock.IsWater = true;
        
    }//GEN-LAST:event_jLabel_DeleteBlockMouseClicked

    private void jLabel_LangPLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_LangPLMouseClicked
        LanguagePL = true;
        setLanguage(LanguagePL);
    }//GEN-LAST:event_jLabel_LangPLMouseClicked

    private void jLabel_LangENGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_LangENGMouseClicked
        LanguagePL = false;
        setLanguage(LanguagePL);
    }//GEN-LAST:event_jLabel_LangENGMouseClicked
    
    private void Blocks_Click(java.awt.event.MouseEvent evt)
    {
       for (int i = 0; i < MapBlocks.size(); i++) {
            if(MapBlocks.get(i).Bloczek.getLocation().equals(evt.getComponent().getLocation()))
            {
                System.out.println(MapBlocks.get(i));
                MapBlocks.get(i).Bloczek.setIcon(ActualBlock.Bloczek.getIcon());
                MapBlocks.get(i).IsDestroyable = ActualBlock.IsDestroyable;
                MapBlocks.get(i).IsCollidingWithTanks = ActualBlock.IsCollidingWithTanks;    
                MapBlocks.get(i).IsCollidingWithBullet = ActualBlock.IsCollidingWithBullet;  
                MapBlocks.get(i).IsWater = ActualBlock.IsWater;  
                
            }
        }
    
    }
    
    private void BlocksInMenuTools_Click(java.awt.event.MouseEvent evt)
    {
        for (int i = 0; i < AllTypesOfBlocks.size(); i++) {
            if(AllTypesOfBlocks.get(i).Bloczek.getLocation().equals(evt.getComponent().getLocation()))
            {
                ActualBlock.Bloczek.setIcon(AllTypesOfBlocks.get(i).Bloczek.getIcon());
                ActualBlock.IsDestroyable = AllTypesOfBlocks.get(i).IsDestroyable;
                ActualBlock.IsCollidingWithTanks = AllTypesOfBlocks.get(i).IsCollidingWithTanks;    
                ActualBlock.IsCollidingWithBullet = AllTypesOfBlocks.get(i).IsCollidingWithBullet;  
                ActualBlock.IsWater = AllTypesOfBlocks.get(i).IsWater;  
                
            }
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TMC_Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TMC_Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TMC_Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TMC_Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TMC_Application().setVisible(true);
                
            }
        });
    }

    
    
    private void ShowInfo(String Info)
    {
        jLabel_Description.setText(Info);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GridPanelJPanel;
    private javax.swing.JPanel MainJPanel;
    private javax.swing.JMenuBar MenuJMenu;
    private javax.swing.JComboBox<String> jComboBox_CreatingMapOptions;
    private javax.swing.JLabel jLabel_DeleteBlock;
    private javax.swing.JLabel jLabel_Description;
    private javax.swing.JLabel jLabel_LangENG;
    private javax.swing.JLabel jLabel_LangPL;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenu jMenu_Authors;
    private javax.swing.JMenu jMenu_Edit;
    private javax.swing.JMenu jMenu_File;
    private javax.swing.JPanel jPanel_Items;
    private javax.swing.JPanel jPanel_Tools;
    private javax.swing.JComboBox<String> selectColorJComboBox;
    // End of variables declaration//GEN-END:variables
}
