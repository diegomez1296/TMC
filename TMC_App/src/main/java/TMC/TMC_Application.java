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
            jComboBox_CreatingMapOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Bloczki", "Spawn Tanków", "Tło"}));
            jComboBox_SelectColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Wybierz kolor tła", "Żółty", "Zielony", "Czerwony", "Niebieski"}));

            jMenu_File.setText("Plik");
            jMenuItem_NewFile.setText("Nowy");
            jMenuItem_SaveFile.setText("Zapisz");
            jMenuItem_OpenFile.setText("Otwórz");
            jMenu_Edit.setText("Edycja");
            jMenu_Authors.setText("Autorzy");

            this.setTitle("Tanks 2k17 - Kreator Map");

            jLabel_LangPL.setIcon(new ImageIcon("Assets\\Flags\\FlagaPL_Click.gif"));
            jLabel_LangENG.setIcon(new ImageIcon("Assets\\Flags\\FlagaENG.gif"));

            jFileChooser_SaveFile.setApproveButtonText("Zapisz");
            jFileChooser_SaveFile.setDialogTitle(jFileChooser_SaveFile.getApproveButtonText());
            jFileChooser_OpenFile.setApproveButtonText("Otwórz");

            errorInfo = "Błąd";
        } else {
            jComboBox_CreatingMapOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Blocks", "Tank Spawn", "Change Background Color"}));
            jComboBox_SelectColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Background Color", "Yellow", "Green", "Red", "Blue"}));

            jMenu_File.setText("File");
            jMenuItem_NewFile.setText("New");
            jMenuItem_SaveFile.setText("Save");
            jMenuItem_OpenFile.setText("Load");
            jMenu_Edit.setText("Edit");
            jMenu_Authors.setText("Authors");

            this.setTitle("Tanks 2k17 - Map Creator");

            jLabel_LangPL.setIcon(new ImageIcon("Assets\\Flags\\FlagaPL.gif"));
            jLabel_LangENG.setIcon(new ImageIcon("Assets\\Flags\\FlagaENG_Click.gif"));

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
        actualBlock = new Block("Assets\\Blocks_Tex\\DefaultBlock.gif", BlockTypes.DEFAULT, 20, 380);
        actualBlock.getjLabel_Block().setVisible(true);
        jPanel_Tools.add(actualBlock.getjLabel_Block());
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
        jPanel_Grid.setBackground(Color.decode("-3355444"));

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

        setLanguage(languagePL);
        createDefaultBlocks();
        loadingDatabaseOfBlocks();
        blocksInMenuTools(0, allTypesOfBlocks.size());

        jLabel_DeleteBlock.setIcon(new ImageIcon("Assets\\Blocks_Tex\\Delete_Block.gif"));

        jComboBox_SelectColor.setVisible(false);
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
        jComboBox_CreatingMapOptions = new javax.swing.JComboBox<>();
        jPanel_Items = new javax.swing.JPanel();
        jComboBox_SelectColor = new javax.swing.JComboBox<>();
        jLabel_DeleteBlock = new javax.swing.JLabel();
        jLabel_LangPL = new javax.swing.JLabel();
        jLabel_LangENG = new javax.swing.JLabel();
        jButton_ResetPanel = new javax.swing.JButton();
        jLabel_Description = new javax.swing.JLabel();
        jMenuBar_Menu = new javax.swing.JMenuBar();
        jMenu_File = new javax.swing.JMenu();
        jMenuItem_NewFile = new javax.swing.JMenuItem();
        jMenuItem_OpenFile = new javax.swing.JMenuItem();
        jMenuItem_SaveFile = new javax.swing.JMenuItem();
        jMenu_Edit = new javax.swing.JMenu();
        jMenu_Authors = new javax.swing.JMenu();

        jFileChooser_SaveFile.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        jFileChooser_SaveFile.setApproveButtonText("");
        jFileChooser_SaveFile.setApproveButtonToolTipText("");
        jFileChooser_SaveFile.setDialogTitle("");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tank Map Creator");
        setResizable(false);
        setSize(new java.awt.Dimension(800, 700));

        jPanel_Main.setBackground(new java.awt.Color(204, 204, 255));
        jPanel_Main.setMaximumSize(new java.awt.Dimension(800, 636));
        jPanel_Main.setMinimumSize(new java.awt.Dimension(800, 636));
        jPanel_Main.setPreferredSize(new java.awt.Dimension(800, 636));
        jPanel_Main.setVerifyInputWhenFocusTarget(false);

        jPanel_Grid.setBackground(new java.awt.Color(204, 204, 204));
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

        jPanel_Tools.setBackground(new java.awt.Color(255, 102, 102));

        jComboBox_CreatingMapOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_CreatingMapOptionsActionPerformed(evt);
            }
        });

        jPanel_Items.setBackground(new java.awt.Color(51, 255, 51));

        jComboBox_SelectColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Background Color", "Yellow", "Green", "Red", "Blue" }));
        jComboBox_SelectColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_SelectColorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_ItemsLayout = new javax.swing.GroupLayout(jPanel_Items);
        jPanel_Items.setLayout(jPanel_ItemsLayout);
        jPanel_ItemsLayout.setHorizontalGroup(
            jPanel_ItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ItemsLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jComboBox_SelectColor, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_ItemsLayout.setVerticalGroup(
            jPanel_ItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ItemsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox_SelectColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jButton_ResetPanel.setText("Reset");
        jButton_ResetPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ResetPanelActionPerformed(evt);
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
                        .addComponent(jLabel_LangPL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_LangENG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_ToolsLayout.createSequentialGroup()
                        .addGroup(jPanel_ToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_CreatingMapOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_ResetPanel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_ToolsLayout.setVerticalGroup(
            jPanel_ToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ToolsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox_CreatingMapOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_Items, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel_DeleteBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_ResetPanel)
                .addGap(31, 31, 31)
                .addGroup(jPanel_ToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_LangPL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_LangENG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
        );

        jLabel_Description.setBackground(new java.awt.Color(153, 255, 255));
        jLabel_Description.setText("Opis...");
        jLabel_Description.setMaximumSize(new java.awt.Dimension(30, 15));
        jLabel_Description.setMinimumSize(new java.awt.Dimension(30, 15));

        javax.swing.GroupLayout jPanel_MainLayout = new javax.swing.GroupLayout(jPanel_Main);
        jPanel_Main.setLayout(jPanel_MainLayout);
        jPanel_MainLayout.setHorizontalGroup(
            jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MainLayout.createSequentialGroup()
                .addGroup(jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel_Grid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_Description, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Tools, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
        jPanel_MainLayout.setVerticalGroup(
            jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Tools, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_MainLayout.createSequentialGroup()
                .addComponent(jPanel_Grid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Description, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenu_File.setText("Plik");

        jMenuItem_NewFile.setText("Nowy");
        jMenuItem_NewFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_NewFileActionPerformed(evt);
            }
        });
        jMenu_File.add(jMenuItem_NewFile);

        jMenuItem_OpenFile.setText("Otwórz");
        jMenuItem_OpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_OpenFileActionPerformed(evt);
            }
        });
        jMenu_File.add(jMenuItem_OpenFile);

        jMenuItem_SaveFile.setText("Zapisz");
        jMenuItem_SaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_SaveFileActionPerformed(evt);
            }
        });
        jMenu_File.add(jMenuItem_SaveFile);

        jMenuBar_Menu.add(jMenu_File);

        jMenu_Edit.setText("Edycja");
        jMenuBar_Menu.add(jMenu_Edit);

        jMenu_Authors.setText("Autorzy");
        jMenuBar_Menu.add(jMenu_Authors);

        setJMenuBar(jMenuBar_Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxCreaner() {
        jComboBox_SelectColor.setVisible(false);

        for (Block Blocks : allTypesOfBlocks) {
            Blocks.getjLabel_Block().setVisible(false);
        }

        actualBlock.getjLabel_Block().setVisible(false);
    }

    private void jComboBox_CreatingMapOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_CreatingMapOptionsActionPerformed
        switch (jComboBox_CreatingMapOptions.getSelectedIndex()) {
            case 0:
                jComboBoxCreaner();
                actualBlock.getjLabel_Block().setVisible(true);
                blocksInMenuTools(0, allTypesOfBlocks.size());
                jPanel_Items.setBackground(Color.GREEN);
                showInfo("Ustawiamy bloczki");
                break;
            case 1:
                jComboBoxCreaner();
                jPanel_Items.setBackground(Color.blue);
                showInfo("Ustawiamy Spawny Tanków");
                break;

            case 2:
                //GridPanelJPanel.setBackground(Color.red);
                jComboBoxCreaner();
                jComboBox_SelectColor.setVisible(true);
                jPanel_Items.setBackground(Color.red);
                showInfo("Ustawiamy BackGround");
                break;
        }

    }//GEN-LAST:event_jComboBox_CreatingMapOptionsActionPerformed

    private void jComboBox_SelectColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_SelectColorActionPerformed
        //wybieranie kolorow

        switch (jComboBox_SelectColor.getSelectedIndex()) {
            case 0:
                break;

            case 1:
                jPanel_Grid.setBackground(Color.yellow);
                break;
            case 2:
                jPanel_Grid.setBackground(Color.green);
                break;
            case 3:
                jPanel_Grid.setBackground(Color.red);
                break;
            case 4:
                jPanel_Grid.setBackground(Color.blue);
        }
    }//GEN-LAST:event_jComboBox_SelectColorActionPerformed

    private void jLabel_DeleteBlockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_DeleteBlockMouseClicked
        actualBlock.getjLabel_Block().setIcon(new ImageIcon("Assets\\Blocks_Tex\\DefaultBlock.gif"));
        actualBlock.setBlockType(BlockTypes.DEFAULT);

    }//GEN-LAST:event_jLabel_DeleteBlockMouseClicked

    private void jLabel_LangPLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_LangPLMouseClicked
        languagePL = true;
        setLanguage(languagePL);
    }//GEN-LAST:event_jLabel_LangPLMouseClicked

    private void jLabel_LangENGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_LangENGMouseClicked
        languagePL = false;
        setLanguage(languagePL);
    }//GEN-LAST:event_jLabel_LangENGMouseClicked

    private void jButton_ResetPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ResetPanelActionPerformed
        resetMap();
    }//GEN-LAST:event_jButton_ResetPanelActionPerformed

    private void jMenuItem_NewFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_NewFileActionPerformed
        resetMap();
    }//GEN-LAST:event_jMenuItem_NewFileActionPerformed

    private void jMenuItem_OpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_OpenFileActionPerformed
        loadMapFromFile();
    }//GEN-LAST:event_jMenuItem_OpenFileActionPerformed

    private void jMenuItem_SaveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_SaveFileActionPerformed
        saveMap();
    }//GEN-LAST:event_jMenuItem_SaveFileActionPerformed

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
    private javax.swing.JButton jButton_ResetPanel;
    private javax.swing.JComboBox<String> jComboBox_CreatingMapOptions;
    private javax.swing.JComboBox<String> jComboBox_SelectColor;
    private javax.swing.JFileChooser jFileChooser_OpenFile;
    private javax.swing.JFileChooser jFileChooser_SaveFile;
    private javax.swing.JLabel jLabel_DeleteBlock;
    private javax.swing.JLabel jLabel_Description;
    private javax.swing.JLabel jLabel_LangENG;
    private javax.swing.JLabel jLabel_LangPL;
    private javax.swing.JMenuBar jMenuBar_Menu;
    private javax.swing.JMenuItem jMenuItem_NewFile;
    private javax.swing.JMenuItem jMenuItem_OpenFile;
    private javax.swing.JMenuItem jMenuItem_SaveFile;
    private javax.swing.JMenu jMenu_Authors;
    private javax.swing.JMenu jMenu_Edit;
    private javax.swing.JMenu jMenu_File;
    private javax.swing.JPanel jPanel_Grid;
    private javax.swing.JPanel jPanel_Items;
    private javax.swing.JPanel jPanel_Main;
    private javax.swing.JPanel jPanel_Tools;
    // End of variables declaration//GEN-END:variables
}
