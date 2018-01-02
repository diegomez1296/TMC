/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMC;

import TMC.Classes.Block;
import TMC.Classes.BlockTypes;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author diegomez
 */
public class TMC_Application extends javax.swing.JFrame {

    //Zwijanie kodu => CTRL, SHIFT, - 
    // Variables
    // <editor-fold> 
    ArrayList<Block> mapBlocks = new ArrayList<>();
    ArrayList<Block> allTypesOfBlocks = new ArrayList<>();

    Boolean languagePL = false;
    Boolean mouseDragged = false;
    Block actualBlock;

    String errorInfo;

    // </editor-fold>
    private void setLanguage(Boolean languagePL) {
        if (languagePL) {
            jMenuItem_NewFile.setText("Nowy");
            jMenuItem_SaveFile.setText("Zapisz");
            jMenuItem_OpenFile.setText("Otwórz");
            jMenuItem_Languages.setText("Języki");
            jMenuItem_Settings.setText("Ustawienia");
            this.setTitle("Tanks 2k17 - Kreator Map");

            jFileChooser_SaveFile.setApproveButtonText("Zapisz");
            jFileChooser_SaveFile.setDialogTitle(jFileChooser_SaveFile.getApproveButtonText());
            jFileChooser_OpenFile.setApproveButtonText("Otwórz");

            errorInfo = "Błąd";
        } else {
            jMenuItem_NewFile.setText("New");
            jMenuItem_SaveFile.setText("Save");
            jMenuItem_OpenFile.setText("Load");
            jMenuItem_Languages.setText("Languages");
            jMenuItem_Settings.setText("Settings");
            this.setTitle("Tanks 2k17 - Map Creator");

            jFileChooser_SaveFile.setApproveButtonText("Save");
            jFileChooser_SaveFile.setDialogTitle(jFileChooser_SaveFile.getApproveButtonText());
            jFileChooser_OpenFile.setApproveButtonText("Load");

            errorInfo = "Error";

        }

    }

    private void createDefaultBlocks() {
        int LocationBlockX = 5;
        int LocationBlockY = 5;

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Block tmp = new Block("Assets\\Blocks_Tex\\DefaultBlock.gif", BlockTypes.DEFAULT, LocationBlockX, LocationBlockY);

                jPanel_Grid.add(tmp.getjLabel_Block());
                tmp.getjLabel_Block().setVisible(true);
                tmp.getjLabel_Block().addMouseListener(new java.awt.event.MouseAdapter() {

                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        blocks_Click(evt);
                    }

                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        blocks_Entered(evt);
                    }

                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        mouseDragged = true;

                    }

                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        mouseDragged = false;

                    }
                });

                mapBlocks.add(tmp);
                LocationBlockX += 40;

            }
            LocationBlockY += 40;
            LocationBlockX = 5;
        }
        //actualBlock
        actualBlock = new Block("Assets\\Blocks_Tex\\DefaultBlock.gif", BlockTypes.DEFAULT, 7, 565);
        actualBlock.getjLabel_Block().setVisible(true);
        jPanel_Tools.add(actualBlock.getjLabel_Block());
        //deleteBlock
        JLabel deleteBlock = new JLabel();
        deleteBlock.setSize(40, 40);
        deleteBlock.setIcon(new ImageIcon("Assets\\Blocks_Tex\\Delete_Block1.gif"));
        deleteBlock.setVisible(true);
        deleteBlock.setText("deleteBlock_Tekst");
        jPanel_Tools.add(deleteBlock);
        deleteBlock.setLocation(50, 565);

    }

    private void toolsButtonsSettings() {
        
        System.out.println(jButton_ToolsDestroyable.getLocation());
        System.out.println(jButton_ToolsUnDestroyable.getLocation());
        System.out.println(jButton_ToolsLiquid.getLocation());
        System.out.println(jButton_ToolsGreen.getLocation());
        
        jButton_ToolsDestroyable.setLocation(5, 5);
        jButton_ToolsUnDestroyable.setLocation(45, 5);
        jButton_ToolsLiquid.setLocation(85, 5);
        jButton_ToolsGreen.setLocation(125, 5);
        
        jButton_ToolsDestroyable.setIcon(new ImageIcon("Assets\\Blocks_Tex\\Default_Blocks\\RedBlock.gif"));
        jButton_ToolsUnDestroyable.setIcon(new ImageIcon("Assets\\Blocks_Tex\\Default_Blocks\\MetalBlock.gif"));
        jButton_ToolsLiquid.setIcon(new ImageIcon("Assets\\Blocks_Tex\\Default_Blocks\\WaterBlock.gif"));
        jButton_ToolsGreen.setIcon(new ImageIcon("Assets\\Blocks_Tex\\Default_Blocks\\GreenBlock.gif"));
    }

    private void loadingDatabaseOfBlocks() {
        //Block(String ImageFromAddress, boolean isDestroyable, boolean isCollidingtanks, boolean isCollidingBullets boolean isWater)
        //Liquid_Blocks
        allTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Default_Blocks\\WaterBlock.gif", BlockTypes.LIQUID));
        allTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Volcano_Blocks\\Lawa.gif", BlockTypes.LIQUID));

        //Destoyable_Blocks
        allTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Default_Blocks\\RedBlock.gif", BlockTypes.DESTROYABLE));
        allTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Volcano_Blocks\\MagmaBlack.gif", BlockTypes.DESTROYABLE));
        allTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Volcano_Blocks\\MagmaRed.gif", BlockTypes.DESTROYABLE));

        //Undestoyable_Blocks
        allTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Default_Blocks\\MetalBlock.gif", BlockTypes.UNDESTROYABLE));

        //Green_Blocks
        allTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Default_Blocks\\GreenBlock.gif", BlockTypes.GREEN));

    }

    private void blocksInMenuTools(int startIndex, int endIndex) {
        if (endIndex > allTypesOfBlocks.size()) {
            endIndex = allTypesOfBlocks.size();
        }

        int LocationBlockX = 10;
        int LocationBlockY = 5;
        int counter_BlocksInRow = 0;

        for (int i = startIndex; i < endIndex; i++) {

            jPanel_Items.add(allTypesOfBlocks.get(i).getjLabel_Block());
            allTypesOfBlocks.get(i).getjLabel_Block().setVisible(true);
            allTypesOfBlocks.get(i).getjLabel_Block().setLocation(LocationBlockX, LocationBlockY);

            LocationBlockX += 50;
            counter_BlocksInRow++;

            if (counter_BlocksInRow == 3) {
                counter_BlocksInRow = 0;
                LocationBlockX = 10;
                LocationBlockY += 40;
            }

            allTypesOfBlocks.get(i).getjLabel_Block().addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    blocksInMenuTools_Click(evt);
                }
            });
        }
    }

    //Listeners Methods
    // <editor-fold> 
    private void blocks_Click(java.awt.event.MouseEvent evt) {
        for (int i = 0; i < mapBlocks.size(); i++) {
            if (mapBlocks.get(i).getjLabel_Block().getLocation().equals(evt.getComponent().getLocation())) {
                mapBlocks.get(i).getjLabel_Block().setIcon(actualBlock.getjLabel_Block().getIcon());
                mapBlocks.get(i).setBlockType(actualBlock.getBlockType());
                break;
            }
        }

    }

    private void blocks_Entered(java.awt.event.MouseEvent evt) {
        if (mouseDragged) {
            for (int i = 0; i < mapBlocks.size(); i++) {
                if (evt.getComponent().equals(mapBlocks.get(i).getjLabel_Block())) {
                    //System.out.println(mapBlocks.get(i).getjLabel_Block().getLocation());
                    //System.out.println(evt.getPoint());
                    mapBlocks.get(i).getjLabel_Block().setIcon(actualBlock.getjLabel_Block().getIcon());
                    mapBlocks.get(i).setBlockType(actualBlock.getBlockType());
                    break;
                }
            }
        }
    }

    private void blocksInMenuTools_Click(java.awt.event.MouseEvent evt) {
        for (int i = 0; i < allTypesOfBlocks.size(); i++) {
            if (allTypesOfBlocks.get(i).getjLabel_Block().getLocation().equals(evt.getComponent().getLocation())) {
                actualBlock.getjLabel_Block().setIcon(allTypesOfBlocks.get(i).getjLabel_Block().getIcon());
                actualBlock.setBlockType(allTypesOfBlocks.get(i).getBlockType());
                break;
            }
        }
    }
    // </editor-fold> 

    // Save/Load MapFile
    // <editor-fold> 
    private String getInfoAboutBlocks() {
        StringBuilder SaveFile = new StringBuilder();
        SaveFile.append(jPanel_Grid.getBackground().getRGB());
        SaveFile.append(System.getProperty("line.separator"));
        for (Block Block : mapBlocks) {
            SaveFile.append(Block.toString());
            SaveFile.append(System.getProperty("line.separator"));
        }
        return SaveFile.toString();
    }

    private void loadMapFromFile() {
        int answer = jFileChooser_OpenFile.showOpenDialog(this);
        if (answer == jFileChooser_OpenFile.APPROVE_OPTION) {
            File file = jFileChooser_OpenFile.getSelectedFile();
            try {
                BufferedReader in = new BufferedReader(new FileReader(file));
                String str;
                resetMap();
                jPanel_Grid.setBackground(Color.decode(in.readLine()));
                int i = 0;
                while ((str = in.readLine()) != null) {
                    String[] splittedTex = str.split(";");

                    switch (splittedTex[0]) {
                        case "DESTROYABLE":
                            mapBlocks.get(i).setBlockType(BlockTypes.DESTROYABLE);
                            break;

                        case "UNDESTROYABLE":
                            mapBlocks.get(i).setBlockType(BlockTypes.UNDESTROYABLE);
                            break;

                        case "LIQUID":
                            mapBlocks.get(i).setBlockType(BlockTypes.LIQUID);
                            break;

                        case "GREEN":
                            mapBlocks.get(i).setBlockType(BlockTypes.GREEN);
                            break;

                        case "DEFAULT":
                            mapBlocks.get(i).setBlockType(BlockTypes.DEFAULT);
                            break;
                    }
                    mapBlocks.get(i).getjLabel_Block().setIcon(new ImageIcon(splittedTex[1]));
                    mapBlocks.get(i).getjLabel_Block().setLocation(Integer.parseInt(splittedTex[2]), Integer.parseInt(splittedTex[3]));
                    i++;
                }

            } catch (IOException e) {
                System.out.println(errorInfo + file.getAbsolutePath());
                System.out.println(errorInfo + e);
            }

        }
    }

    private void saveMap() {
        int answer = jFileChooser_SaveFile.showSaveDialog(this);
        if (answer == jFileChooser_SaveFile.APPROVE_OPTION) {
            File file = jFileChooser_SaveFile.getSelectedFile();
            try {
                FileWriter out = new FileWriter(file);
                out.write(getInfoAboutBlocks());
                out.close();
            } catch (IOException e) {
                System.out.println(errorInfo + file.getAbsolutePath());
                System.out.println(errorInfo + e);
            }
        }
    }
    // </editor-fold> 

    private void resetMap() {

        jPanel_Grid.setBackground(Color.decode("-8421505"));
        for (Block MapBlock : mapBlocks) {
            MapBlock.getjLabel_Block().setIcon(new ImageIcon("Assets\\Blocks_Tex\\DefaultBlock.gif"));
            MapBlock.setBlockType(BlockTypes.DEFAULT);
        }
    }

    private void showInfo(String Info) {
        jLabel_Description.setText(Info);
    }

    private void startApplication() {

        jFileChooser_SaveFile.setCurrentDirectory(new File("Maps"));
        jFileChooser_OpenFile.setCurrentDirectory(new File("Maps"));
        System.out.println(jPanel_Grid.getBackground().getRGB());
        setLanguage(languagePL);
        createDefaultBlocks();
        toolsButtonsSettings();
        loadingDatabaseOfBlocks();
        blocksInMenuTools(0, allTypesOfBlocks.size());
               
        
    }

    public TMC_Application() {

        initComponents();
        startApplication();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser_SaveFile = new javax.swing.JFileChooser();
        jFileChooser_OpenFile = new javax.swing.JFileChooser();
        jPanel_Main = new javax.swing.JPanel();
        jPanel_Grid = new javax.swing.JPanel();
        jPanel_Tools = new javax.swing.JPanel();
        jPanel_Items = new javax.swing.JPanel();
        jButton_ToolsUnDestroyable = new javax.swing.JButton();
        jButton_ToolsLiquid = new javax.swing.JButton();
        jButton_ToolsGreen = new javax.swing.JButton();
        jButton_ToolsDestroyable = new javax.swing.JButton();
        jLabel_Description = new javax.swing.JLabel();
        jMenuBar_Menu = new javax.swing.JMenuBar();
        jMenuItem_NewFile = new javax.swing.JMenu();
        jMenuItem_OpenFile = new javax.swing.JMenu();
        jMenuItem_SaveFile = new javax.swing.JMenu();
        jMenuItem_Languages = new javax.swing.JMenu();
        jMenuItem_Settings = new javax.swing.JMenu();

        jFileChooser_SaveFile.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        jFileChooser_SaveFile.setApproveButtonText("");
        jFileChooser_SaveFile.setApproveButtonToolTipText("");
        jFileChooser_SaveFile.setDialogTitle("");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tank Map Creator");
        setResizable(false);
        setSize(new java.awt.Dimension(800, 700));

        jPanel_Main.setBackground(new java.awt.Color(127, 127, 127));
        jPanel_Main.setMaximumSize(new java.awt.Dimension(800, 636));
        jPanel_Main.setMinimumSize(new java.awt.Dimension(800, 636));
        jPanel_Main.setPreferredSize(new java.awt.Dimension(800, 636));
        jPanel_Main.setVerifyInputWhenFocusTarget(false);

        jPanel_Grid.setBackground(new java.awt.Color(127, 127, 127));
        jPanel_Grid.setAlignmentX(10.0F);
        jPanel_Grid.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel_Grid.setMaximumSize(new java.awt.Dimension(610, 610));
        jPanel_Grid.setMinimumSize(new java.awt.Dimension(610, 610));
        jPanel_Grid.setPreferredSize(new java.awt.Dimension(610, 610));

        javax.swing.GroupLayout jPanel_GridLayout = new javax.swing.GroupLayout(jPanel_Grid);
        jPanel_Grid.setLayout(jPanel_GridLayout);
        jPanel_GridLayout.setHorizontalGroup(
            jPanel_GridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        jPanel_GridLayout.setVerticalGroup(
            jPanel_GridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );

        jPanel_Tools.setBackground(new java.awt.Color(127, 127, 127));
        jPanel_Tools.setMaximumSize(new java.awt.Dimension(182, 350));
        jPanel_Tools.setMinimumSize(new java.awt.Dimension(182, 350));

        jPanel_Items.setBackground(new java.awt.Color(170, 170, 170));
        jPanel_Items.setMaximumSize(new java.awt.Dimension(160, 520));
        jPanel_Items.setMinimumSize(new java.awt.Dimension(160, 520));
        jPanel_Items.setPreferredSize(new java.awt.Dimension(160, 520));

        javax.swing.GroupLayout jPanel_ItemsLayout = new javax.swing.GroupLayout(jPanel_Items);
        jPanel_Items.setLayout(jPanel_ItemsLayout);
        jPanel_ItemsLayout.setHorizontalGroup(
            jPanel_ItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel_ItemsLayout.setVerticalGroup(
            jPanel_ItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        jButton_ToolsUnDestroyable.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton_ToolsUnDestroyable.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton_ToolsUnDestroyable.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton_ToolsUnDestroyable.setLocation(45, 5);

        jButton_ToolsLiquid.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton_ToolsLiquid.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton_ToolsLiquid.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton_ToolsLiquid.setLocation(85, 5);

        jButton_ToolsGreen.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton_ToolsGreen.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton_ToolsGreen.setPreferredSize(new java.awt.Dimension(30, 30));

        jButton_ToolsDestroyable.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton_ToolsDestroyable.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton_ToolsDestroyable.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton_ToolsDestroyable.setLocation(5, 5);

        javax.swing.GroupLayout jPanel_ToolsLayout = new javax.swing.GroupLayout(jPanel_Tools);
        jPanel_Tools.setLayout(jPanel_ToolsLayout);
        jPanel_ToolsLayout.setHorizontalGroup(
            jPanel_ToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ToolsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_ToolsLayout.createSequentialGroup()
                        .addComponent(jButton_ToolsDestroyable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jButton_ToolsUnDestroyable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_ToolsLiquid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jButton_ToolsGreen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel_Items, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 32, Short.MAX_VALUE))
        );
        jPanel_ToolsLayout.setVerticalGroup(
            jPanel_ToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ToolsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_ToolsLiquid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_ToolsGreen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton_ToolsDestroyable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_ToolsUnDestroyable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Items, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jLabel_Description.setBackground(new java.awt.Color(127, 127, 127));
        jLabel_Description.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Description.setText("Opis...");
        jLabel_Description.setMaximumSize(new java.awt.Dimension(30, 15));
        jLabel_Description.setMinimumSize(new java.awt.Dimension(30, 15));

        javax.swing.GroupLayout jPanel_MainLayout = new javax.swing.GroupLayout(jPanel_Main);
        jPanel_Main.setLayout(jPanel_MainLayout);
        jPanel_MainLayout.setHorizontalGroup(
            jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Description, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_MainLayout.createSequentialGroup()
                .addComponent(jPanel_Grid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Tools, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_MainLayout.setVerticalGroup(
            jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MainLayout.createSequentialGroup()
                .addGroup(jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_Tools, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel_Grid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_Description, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenuBar_Menu.setBackground(new java.awt.Color(170, 170, 170));
        jMenuBar_Menu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(170, 170, 170), 1, true));

        jMenuItem_NewFile.setText("New");
        jMenuItem_NewFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem_NewFileMouseClicked(evt);
            }
        });
        jMenuBar_Menu.add(jMenuItem_NewFile);

        jMenuItem_OpenFile.setText("Load");
        jMenuItem_OpenFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem_OpenFileMouseClicked(evt);
            }
        });
        jMenuBar_Menu.add(jMenuItem_OpenFile);

        jMenuItem_SaveFile.setText("Save");
        jMenuItem_SaveFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem_SaveFileMouseClicked(evt);
            }
        });
        jMenuBar_Menu.add(jMenuItem_SaveFile);

        jMenuItem_Languages.setText("Languages");
        jMenuItem_Languages.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem_LanguagesMouseClicked(evt);
            }
        });
        jMenuBar_Menu.add(jMenuItem_Languages);

        jMenuItem_Settings.setText("Settings");
        jMenuBar_Menu.add(jMenuItem_Settings);

        setJMenuBar(jMenuBar_Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_NewFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem_NewFileMouseClicked
        resetMap();
    }//GEN-LAST:event_jMenuItem_NewFileMouseClicked

    private void jMenuItem_OpenFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem_OpenFileMouseClicked
        loadMapFromFile();
    }//GEN-LAST:event_jMenuItem_OpenFileMouseClicked

    private void jMenuItem_SaveFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem_SaveFileMouseClicked
        saveMap();
    }//GEN-LAST:event_jMenuItem_SaveFileMouseClicked

    private void jMenuItem_LanguagesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem_LanguagesMouseClicked
        languagePL = !languagePL;
        setLanguage(languagePL);
    }//GEN-LAST:event_jMenuItem_LanguagesMouseClicked

    private void jComboBoxCreaner() {
        //jComboBox_SelectColor.setVisible(false);

        for (Block Blocks : allTypesOfBlocks) {
            Blocks.getjLabel_Block().setVisible(false);
        }

        actualBlock.getjLabel_Block().setVisible(false);
    }

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_ToolsDestroyable;
    private javax.swing.JButton jButton_ToolsGreen;
    private javax.swing.JButton jButton_ToolsLiquid;
    private javax.swing.JButton jButton_ToolsUnDestroyable;
    private javax.swing.JFileChooser jFileChooser_OpenFile;
    private javax.swing.JFileChooser jFileChooser_SaveFile;
    private javax.swing.JLabel jLabel_Description;
    private javax.swing.JMenuBar jMenuBar_Menu;
    private javax.swing.JMenu jMenuItem_Languages;
    private javax.swing.JMenu jMenuItem_NewFile;
    private javax.swing.JMenu jMenuItem_OpenFile;
    private javax.swing.JMenu jMenuItem_SaveFile;
    private javax.swing.JMenu jMenuItem_Settings;
    private javax.swing.JPanel jPanel_Grid;
    private javax.swing.JPanel jPanel_Items;
    private javax.swing.JPanel jPanel_Main;
    private javax.swing.JPanel jPanel_Tools;
    // End of variables declaration//GEN-END:variables
}
