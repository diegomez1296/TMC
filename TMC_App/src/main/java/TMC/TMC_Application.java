/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMC;

import TMC.Classes.*;
import java.awt.*;
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
    JButton[] buttonsInTools = new JButton[4];
    JButton[] buttonsSpawnTanks = new JButton[8];
    JLabel[] labelsSpawnTanks = new JLabel[8];
    JLabel jLabel_DeleteBlock = new JLabel();
    JLabel jLabel_AutorsBlock = new JLabel();
    JLabel jLabel_SpawnTankTitle = new JLabel();
    JLabel jLabel_BonusTitle = new JLabel();
    JPanel jPanel_Settings = new JPanel();

    ColorSlider colorSliderR, colorSliderG, colorSliderB;
    ColorSlider[] colorSlider_tab;

    BonusSlider bonusSlider;

    TankSpawn tankSpawn_P1, tankSpawn_P2, tankSpawn_E1, tankSpawn_E2, tankSpawn_E3, tankSpawn_E4, tankSpawn_E5, tankSpawn_E6;
    TankSpawn[] tankSpawn_tab;

    int indexOfLastDestroyableBlock;
    int indexOfLastUnDestroyableBlock;
    int indexOfLastLiquidBlock;
    int indexOfLastGreenBlock;

    //showInfo 
    int pointX;
    int pointY;

    Boolean languagePL = false;
    Boolean mouseDragged = false;
    Block actualBlock;

    //Spawn Tanks
    // <editor-fold>
    boolean setSpawn = false;
    String actualSpawn;

    String SpawnTankTitle;
    String Player1;
    String Player2;

    String Enemy1;
    String Enemy2;
    String Enemy3;
    String Enemy4;
    String Enemy5;
    String Enemy6;

    String emptySpawn;
    String chooseSpawn;
    String okSpawn;
    // </editor-fold>

    String BonusTitle;

    String newFileTitle;
    String newFileInfo;
    String errorInfo;

    // </editor-fold>
    private void loadingDatabaseOfBlocks() {
        //Destoyable_Blocks
        allTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Default_Blocks\\RedBlock.gif", BlockTypes.DESTROYABLE));
        allTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Volcano_Blocks\\MagmaBlack.gif", BlockTypes.DESTROYABLE));
        allTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Volcano_Blocks\\MagmaRed.gif", BlockTypes.DESTROYABLE));

        indexOfLastDestroyableBlock = allTypesOfBlocks.size();

        //Undestoyable_Blocks
        allTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Default_Blocks\\MetalBlock3.gif", BlockTypes.UNDESTROYABLE));
        allTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Default_Blocks\\MetalBlock.gif", BlockTypes.UNDESTROYABLE));

        indexOfLastUnDestroyableBlock = allTypesOfBlocks.size();

        //Liquid_Blocks
        allTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Default_Blocks\\WaterBlock.gif", BlockTypes.LIQUID));
        allTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Volcano_Blocks\\Lawa.gif", BlockTypes.LIQUID));

        indexOfLastLiquidBlock = allTypesOfBlocks.size();

        //Green_Blocks
        allTypesOfBlocks.add(new Block("Assets\\Blocks_Tex\\Default_Blocks\\GreenBlock.gif", BlockTypes.GREEN));

        indexOfLastGreenBlock = allTypesOfBlocks.size();

    }

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

            newFileTitle = "Nowy plik";
            newFileInfo = "Czy napewno chcesz utworzyć nowy plik?";
            errorInfo = "Błąd";

            chooseSpawn = "Ustaw respawn";
            okSpawn = "Respawn ustawiony";

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

            newFileTitle = "New file";
            newFileInfo = "Do you want to create a new file?";
            errorInfo = "Error";

            chooseSpawn = "Set respawn";
            okSpawn = "Respawn created";

        }

        setLanguageInSettingsPanel();

    }

    private void setLanguageInSettingsPanel() {
        if (languagePL) {

            SpawnTankTitle = "Ustaw respawn czołgów";
            BonusTitle = "Szansa na bonus [%]";

            Player1 = "Gracz 1";
            Player2 = "Gracz 2";

            Enemy1 = "Wróg 1";
            Enemy2 = "Wróg 2";
            Enemy3 = "Wróg 3";
            Enemy4 = "Wróg 4";
            Enemy5 = "Wróg 5";
            Enemy6 = "Wróg 6";

            emptySpawn = "brak";
        } else {
            SpawnTankTitle = "Set respawn for tanks";
            BonusTitle = "Chance for a bonus [%]";

            Player1 = "Player 1";
            Player2 = "Player 2";

            Enemy1 = "Enemy 1";
            Enemy2 = "Enemy 2";
            Enemy3 = "Enemy 3";
            Enemy4 = "Enemy 4";
            Enemy5 = "Enemy 5";
            Enemy6 = "Enemy 6";

            emptySpawn = "null";
        }

        try {
            jLabel_BonusTitle.setText(BonusTitle);

            jLabel_SpawnTankTitle.setText(SpawnTankTitle);
            tankSpawn_tab[0].getJbutton_TankSpawn().setText(Player1);
            tankSpawn_tab[1].getJbutton_TankSpawn().setText(Player2);
            tankSpawn_tab[2].getJbutton_TankSpawn().setText(Enemy1);
            tankSpawn_tab[3].getJbutton_TankSpawn().setText(Enemy2);
            tankSpawn_tab[4].getJbutton_TankSpawn().setText(Enemy3);
            tankSpawn_tab[5].getJbutton_TankSpawn().setText(Enemy4);
            tankSpawn_tab[6].getJbutton_TankSpawn().setText(Enemy5);
            tankSpawn_tab[7].getJbutton_TankSpawn().setText(Enemy6);

            for (TankSpawn item : tankSpawn_tab) {
                if (item.getJlabel_TankSpawn().getText().equals("") || item.getJlabel_TankSpawn().getText().equals("null") || item.getJlabel_TankSpawn().getText().equals("brak")) {
                    item.getJlabel_TankSpawn().setText(emptySpawn);
                }
            }
        } catch (Exception e) {
        }
    }

    private void createComponentsInPanelGrid() {
        int LocationBlockX = 5;
        int LocationBlockY = 5;

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Block tmp = new Block("Assets\\Blocks_Tex\\DefaultBlock.gif", BlockTypes.DEFAULT, new Point(LocationBlockX, LocationBlockY), jPanel_Grid);

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

                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        showInfo("");
                    }
                });

                mapBlocks.add(tmp);
                LocationBlockX += 40;

            }
            LocationBlockY += 40;
            LocationBlockX = 5;
        }

    }

    private void createComponentsInPanelTools() {

        blockTypesButtonsInPanelTools();
        createBlocksInPanelItems();

        //actualBlock
        actualBlock = new Block("Assets\\Blocks_Tex\\DefaultBlock.gif", BlockTypes.DEFAULT, new Point(7, 565), jPanel_Tools);

        //jLabel_DeleteBlock
        setComponentSettings(jLabel_DeleteBlock, "", "Assets\\Blocks_Tex\\Delete_Block1.gif", 0, new Dimension(40, 40), new Point(50, 565), jPanel_Tools, true);

        jLabel_DeleteBlock.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actualBlock.getjLabel_Block().setIcon(new ImageIcon("Assets\\Blocks_Tex\\DefaultBlock.gif"));
                actualBlock.setBlockType(BlockTypes.DEFAULT);
            }
        });

        //jLabel_AutorsBlock
        setComponentSettings(jLabel_AutorsBlock, "", "Assets\\Icons\\Autors.png", 0, new Dimension(40, 40), new Point(120, 565), jPanel_Tools, true);

        jLabel_AutorsBlock.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_Authors.setVisible(true);
            }
        });
    }

    private void createPanelSettings() {
        setComponentSettings(jPanel_Settings, "", "", jPanel_Items.getBackground().getRGB(), new Dimension(160, 600), new Point(6, 5), jPanel_Tools, true);

        javax.swing.GroupLayout jPanel_SettingsLayout = new javax.swing.GroupLayout(jPanel_Settings);
        jPanel_Settings.setLayout(jPanel_SettingsLayout);
        jPanel_SettingsLayout.setHorizontalGroup(
                jPanel_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 610, Short.MAX_VALUE)
        );
        jPanel_SettingsLayout.setVerticalGroup(
                jPanel_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 610, Short.MAX_VALUE)
        );

        createComponentsInPanelSettings();

    }

    private void createComponentsInPanelSettings() {
        JButton jButton_ClosePanelSettings = new JButton();
        setComponentSettings(jButton_ClosePanelSettings, "", "Assets\\Blocks_Tex\\Delete_Block.gif", 0, new Dimension(20, 20), new Point(130, 10), jPanel_Settings, true);

        jButton_ClosePanelSettings.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                visibleSettingsPanel(false);
            }
        });

        spawnTanks();
        sliderColor();
        sliderBonus();
    }

    private void createComponentsInPanelAuthors() {

        JButton jButton_ClosePanelAuthors = new JButton();
        setComponentSettings(jButton_ClosePanelAuthors, "", "Assets\\Blocks_Tex\\Delete_Block.gif", 0, new Dimension(20, 20), new Point(460, 10), jPanel_Authors, true);

        jButton_ClosePanelAuthors.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_Authors.setVisible(false);
            }
        });

        JLabel jLabel_PawelMinda = new JLabel();
        setComponentSettings(jLabel_PawelMinda, "", "Assets\\Authors\\Pawel_Minda.jpg", 0, new Dimension(150, 150), new Point(20, 40), jPanel_Authors, true);

        JLabel jLabel_LukaszRydzinski = new JLabel();
        setComponentSettings(jLabel_LukaszRydzinski, "", "Assets\\Authors\\Lukasz_Rydzinski2.jpg", 0, new Dimension(150, 150), new Point(190, 40), jPanel_Authors, true);

        JLabel jLabel_PawelSokol = new JLabel();
        setComponentSettings(jLabel_PawelSokol, "", "Assets\\Authors\\Pawel_Sokol.jpg", 0, new Dimension(150, 150), new Point(360, 40), jPanel_Authors, true);

    }

    private void visibleSettingsPanel(boolean visible) {
        jPanel_Items.setVisible(!visible);
        if (!visible) {
            showBlocksInPanelItems(0, indexOfLastDestroyableBlock);
            jLabel_AutorsBlock.setVisible(true);
            jLabel_AutorsBlock.setLocation(120, 565);
            setSpawn = false;
            actualSpawn = "";
        } else {
            cleanBlocksInPanelItems();
            jLabel_AutorsBlock.setVisible(false);
            jLabel_AutorsBlock.setLocation(-1000, -1000);
        }
        jPanel_Settings.setVisible(visible);
        actualBlock.getjLabel_Block().setVisible(!visible);
        jLabel_DeleteBlock.setVisible(!visible);
        for (JButton item : buttonsInTools) {
            item.setVisible(!visible);
        }
    }

    private void spawnTanks() {

        setComponentSettings(jLabel_SpawnTankTitle, "", "", Color.WHITE.getRGB(), new Dimension(160, 20), new Point(20, 370), jPanel_Settings, true);

        tankSpawn_P1 = new TankSpawn("P1", new Point(10, 400), new Point(100, 400), jPanel_Settings);
        tankSpawn_P2 = new TankSpawn("P2", new Point(10, 425), new Point(100, 425), jPanel_Settings);

        tankSpawn_E1 = new TankSpawn("E1", new Point(10, 450), new Point(100, 450), jPanel_Settings);
        tankSpawn_E2 = new TankSpawn("E2", new Point(10, 475), new Point(100, 475), jPanel_Settings);
        tankSpawn_E3 = new TankSpawn("E3", new Point(10, 500), new Point(100, 500), jPanel_Settings);
        tankSpawn_E4 = new TankSpawn("E4", new Point(10, 525), new Point(100, 525), jPanel_Settings);
        tankSpawn_E5 = new TankSpawn("E5", new Point(10, 550), new Point(100, 550), jPanel_Settings);
        tankSpawn_E6 = new TankSpawn("E6", new Point(10, 575), new Point(100, 575), jPanel_Settings);

        tankSpawn_tab = new TankSpawn[]{
            tankSpawn_P1, tankSpawn_P2,
            tankSpawn_E1, tankSpawn_E2,
            tankSpawn_E3, tankSpawn_E4,
            tankSpawn_E5, tankSpawn_E6,};

        for (TankSpawn tankSpawn : tankSpawn_tab) {
            tankSpawn.getJbutton_TankSpawn().addMouseListener(new java.awt.event.MouseAdapter() {

                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    setSpawn = true;
                    actualSpawn = tankSpawn.getId();
                    showInfo(chooseSpawn);
                }
            });
        }
    }

    private void sliderColor() {
        //JPanel jButton_ActualColor = new JPanel();
        JButton jButton_ActualColor = new JButton();
        setComponentSettings(jButton_ActualColor, "", "", 0, new Dimension(60, 40), new Point(50, 165), jPanel_Settings, true);

        jButton_ActualColor.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_Grid.setBackground(new Color(colorSliderR.getJslider_Color().getValue(), colorSliderG.getJslider_Color().getValue(), colorSliderB.getJslider_Color().getValue()));
            }
        });

        colorSliderR = new ColorSlider("R", "R:", new Point(10, 40), new Point(22, 35), new Point(10, 55), jPanel_Settings);
        colorSliderG = new ColorSlider("G", "G:", new Point(10, 80), new Point(22, 75), new Point(10, 95), jPanel_Settings);
        colorSliderB = new ColorSlider("B", "B:", new Point(10, 120), new Point(22, 115), new Point(10, 135), jPanel_Settings);

        colorSlider_tab = new ColorSlider[]{
            colorSliderR,
            colorSliderB,
            colorSliderG,};
        for (ColorSlider item : colorSlider_tab) {
            sliderCreateListener(item.getJslider_Color(), item.getJtextfield_Color(), jButton_ActualColor);
        }

        setSliderColorValue();
    }

    private void setSliderColorValue() {

        int colorR = jPanel_Grid.getBackground().getRed();
        int colorG = jPanel_Grid.getBackground().getGreen();
        int colorB = jPanel_Grid.getBackground().getBlue();

        colorSliderR.getJtextfield_Color().setText(colorR + "");
        colorSliderG.getJtextfield_Color().setText(colorG + "");
        colorSliderB.getJtextfield_Color().setText(colorB + "");

        colorSliderR.getJslider_Color().setValue(colorR);
        colorSliderG.getJslider_Color().setValue(colorG);
        colorSliderB.getJslider_Color().setValue(colorB);
    }

    private void sliderBonus() {
        setComponentSettings(jLabel_BonusTitle, "", "", Color.WHITE.getRGB(), new Dimension(160, 20), new Point(20, 240), jPanel_Settings, true);

        bonusSlider = new BonusSlider(new Dimension(colorSliderR.getJslider_Color().getSize()), new Point(10, 255), new Dimension(colorSliderR.getJtextfield_Color().getSize()), new Point(63, 285), jPanel_Settings);
        sliderCreateListener(bonusSlider.getjSlider_Bonus(), bonusSlider.getjTextField_BonusValue(), null);
    }

    private void blockTypesButtonsInPanelTools() {

        int locationButtonsInToolsX = 10;
        int locationButtonsInToolsY = 10;

        for (int i = 0; i < buttonsInTools.length; i++) {
            buttonsInTools[i] = new JButton();
            setComponentSettings(buttonsInTools[i], "", "", 0, new Dimension(30, 30), new Point(locationButtonsInToolsX, locationButtonsInToolsY), jPanel_Tools, true);

            buttonsInTools[i].addMouseListener(new java.awt.event.MouseAdapter() {

                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonsInTools_Click(evt);
                }
            });

            locationButtonsInToolsX += 40;
        }

        buttonsInTools[0].setIcon(new ImageIcon("Assets\\Blocks_Tex\\Default_Blocks\\RedBlock.gif"));
        buttonsInTools[1].setIcon(new ImageIcon("Assets\\Blocks_Tex\\Default_Blocks\\MetalBlock3.gif"));
        buttonsInTools[2].setIcon(new ImageIcon("Assets\\Blocks_Tex\\Default_Blocks\\WaterBlock.gif"));
        buttonsInTools[3].setIcon(new ImageIcon("Assets\\Blocks_Tex\\Default_Blocks\\GreenBlock.gif"));

    }

    private void createBlocksInPanelItems() {

        for (int i = 0; i < allTypesOfBlocks.size(); i++) {

            jPanel_Items.add(allTypesOfBlocks.get(i).getjLabel_Block());
            allTypesOfBlocks.get(i).getjLabel_Block().setVisible(false);
            allTypesOfBlocks.get(i).getjLabel_Block().setLocation(-1000, -1000);

            allTypesOfBlocks.get(i).getjLabel_Block().addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    blocksInMenuTools_Click(evt);
                }
            });
        }
    }

    private void showBlocksInPanelItems(int startIndex, int endIndex) {

        cleanBlocksInPanelItems();

        if (endIndex > allTypesOfBlocks.size()) {
            endIndex = allTypesOfBlocks.size();
        }

        int LocationBlockX = 10;
        int LocationBlockY = 5;
        int counter_BlocksInRow = 0;

        for (int i = startIndex; i < endIndex; i++) {

            allTypesOfBlocks.get(i).getjLabel_Block().setVisible(true);
            allTypesOfBlocks.get(i).getjLabel_Block().setLocation(LocationBlockX, LocationBlockY);

            LocationBlockX += 50;
            counter_BlocksInRow++;

            if (counter_BlocksInRow == 3) {
                counter_BlocksInRow = 0;
                LocationBlockX = 10;
                LocationBlockY += 40;
            }

        }
    }

    private void cleanBlocksInPanelItems() {
        for (int i = 0; i < allTypesOfBlocks.size(); i++) {
            allTypesOfBlocks.get(i).getjLabel_Block().setVisible(false);
            allTypesOfBlocks.get(i).getjLabel_Block().setLocation(-1000, -1000);
        }
    }

    //Listeners Methods
    // <editor-fold> 
    private void blocks_Click(java.awt.event.MouseEvent evt) {
        if (!setSpawn) {
            for (int i = 0; i < mapBlocks.size(); i++) {
                if (mapBlocks.get(i).getjLabel_Block().getLocation().equals(evt.getComponent().getLocation())) {
                    mapBlocks.get(i).getjLabel_Block().setIcon(actualBlock.getjLabel_Block().getIcon());
                    mapBlocks.get(i).setBlockType(actualBlock.getBlockType());
                    break;
                }
            }
        } else {
            switch (actualSpawn) {
                case "P1":
                    setActualSpawn(tankSpawn_P1, evt);
                    break;
                case "P2":
                    setActualSpawn(tankSpawn_P2, evt);
                    break;

                case "E1":
                    setActualSpawn(tankSpawn_E1, evt);
                    break;
                case "E2":
                    setActualSpawn(tankSpawn_E2, evt);
                    break;
                case "E3":
                    setActualSpawn(tankSpawn_E3, evt);
                    break;
                case "E4":
                    setActualSpawn(tankSpawn_E4, evt);
                    break;
                case "E5":
                    setActualSpawn(tankSpawn_E5, evt);
                    break;
                case "E6":
                    setActualSpawn(tankSpawn_E6, evt);
                    break;
            }
            //setSpawn = false;
            showInfo(okSpawn);
        }

    }

    private void setActualSpawn(TankSpawn tankSpawn, java.awt.event.MouseEvent evt) {
        tankSpawn.setSpawnOnMap(evt.getComponent().getLocation());
        tankSpawn.getJlabel_TankSpawn().setText("[" + pointX + ";" + pointY + "]");
    }

    private void blocks_Entered(java.awt.event.MouseEvent evt) {
        if (mouseDragged) {
            for (int i = 0; i < mapBlocks.size(); i++) {
                if (evt.getComponent().equals(mapBlocks.get(i).getjLabel_Block())) {
                    mapBlocks.get(i).getjLabel_Block().setIcon(actualBlock.getjLabel_Block().getIcon());
                    mapBlocks.get(i).setBlockType(actualBlock.getBlockType());
                    break;
                }
            }
        }

        pointX = (int) ((evt.getComponent().getLocation().getX() - 5) / 40 + 1);
        pointY = (int) ((evt.getComponent().getLocation().getY() - 5) / 40 + 1);

        showInfo("[" + pointX + ";" + pointY + "]");
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

    private void buttonsInTools_Click(java.awt.event.MouseEvent evt) {
        switch (evt.getComponent().getX()) {
            case 10: //Dest
                showBlocksInPanelItems(0, indexOfLastDestroyableBlock);
                break;
            case 50: //UnDest
                showBlocksInPanelItems(indexOfLastDestroyableBlock, indexOfLastUnDestroyableBlock);
                break;
            case 90: //Liquid
                showBlocksInPanelItems(indexOfLastUnDestroyableBlock, indexOfLastLiquidBlock);
                break;
            case 130: //Green
                showBlocksInPanelItems(indexOfLastLiquidBlock, indexOfLastGreenBlock);
                break;
        }
    }

    private void sliderCreateListener(JSlider jSlider, JTextField jTextfield, JButton jButton) {
        jSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTextfield.setText(jSlider.getValue() + "");
                if (jButton != null) {
                    jButton.setBackground(new Color(colorSliderR.getJslider_Color().getValue(), colorSliderG.getJslider_Color().getValue(), colorSliderB.getJslider_Color().getValue()));
                }
            }
        });
        jTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == evt.VK_ENTER) {
                    try {
                        jSlider.setValue(Integer.parseInt(jTextfield.getText()));
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    // </editor-fold> 
    // Save/Load MapFile
    // <editor-fold> 
    private String getInfoAboutBlocks() {
        StringBuilder SaveFile = new StringBuilder();
        SaveFile.append(jPanel_Grid.getBackground().getRGB());
        SaveFile.append(System.getProperty("line.separator"));
        SaveFile.append(bonusSlider.getjSlider_Bonus().getValue());
        SaveFile.append(System.getProperty("line.separator"));
        for (TankSpawn item : tankSpawn_tab) {
            if (item.getSpawnOnMap() != null) {
                SaveFile.append(item.getSpawnOnMapToSave());
                SaveFile.append(System.getProperty("line.separator"));
            } else {
                SaveFile.append("-1000;-1000");
                SaveFile.append(System.getProperty("line.separator"));
            }
        }
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
                bonusSlider.getjSlider_Bonus().setValue(Integer.parseInt(in.readLine()));
                bonusSlider.getjTextField_BonusValue().setText(bonusSlider.getjSlider_Bonus().getValue() + "");
                for (int i = 0; i < tankSpawn_tab.length; i++) {
                    str = in.readLine();
                    String[] splittedSpawn = str.split(";");

                    int spawnX = Integer.parseInt(splittedSpawn[0]);
                    int spawnY = Integer.parseInt(splittedSpawn[1]);
                    if (spawnX != -1000) {
                        tankSpawn_tab[i].setSpawnOnMap(new Point(spawnX, spawnY));
                        spawnX = (int) ((spawnX - 5) / 40 + 1);
                        spawnY = (int) ((spawnY - 5) / 40 + 1);
                        tankSpawn_tab[i].getJlabel_TankSpawn().setText("[" + spawnX + ";" + spawnY + "]");
                    } else {
                        tankSpawn_tab[i].getJlabel_TankSpawn().setText(emptySpawn);
                    }
                }

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

                    setSliderColorValue();
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

        setSliderColorValue();
        bonusSlider.getjSlider_Bonus().setValue(50);
        bonusSlider.getjTextField_BonusValue().setText("50");

        for (TankSpawn item : tankSpawn_tab) {
            item.getJlabel_TankSpawn().setText(emptySpawn);
            item.setSpawnOnMap(null);
        }
    }

    private void showInfo(String info) {
        jLabel_Description.setText(info);
    }

    public void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    public void setComponentSettings(Component component, String text, String iconPath, int backgroundCode, Dimension size, Point location, JPanel panel, Boolean visible) {
        component.setSize(size);
        component.setVisible(visible);
        component.setLocation(location);
        if (backgroundCode != 0) {
            if (component instanceof JLabel || component instanceof JButton) {
                component.setForeground(Color.decode(backgroundCode + ""));
            } else {
                component.setBackground(Color.decode(backgroundCode + ""));
            }
        }
        if (panel != null) {
            panel.add(component);
        }
        if (component instanceof JLabel) {
            ((JLabel) component).setText(text);
            ((JLabel) component).setIcon(new ImageIcon(iconPath));
        }
        if (component instanceof JButton) {
            ((JButton) component).setText(text);
            ((JButton) component).setIcon(new ImageIcon(iconPath));
        }

    }

    private void startApplication() {

        centreWindow(this);

        jFileChooser_SaveFile.setCurrentDirectory(new File("Maps"));
        jFileChooser_OpenFile.setCurrentDirectory(new File("Maps"));

        loadingDatabaseOfBlocks();
        createComponentsInPanelGrid();
        createComponentsInPanelTools();
        createPanelSettings();
        createComponentsInPanelAuthors();
        showBlocksInPanelItems(0, indexOfLastDestroyableBlock);
        visibleSettingsPanel(false);
        setLanguage(languagePL);

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
        jPanel_Authors = new javax.swing.JPanel();
        jPanel_Tools = new javax.swing.JPanel();
        jPanel_Items = new javax.swing.JPanel();
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

        jPanel_Authors.setMaximumSize(new java.awt.Dimension(524, 284));
        jPanel_Authors.setMinimumSize(new java.awt.Dimension(524, 284));
        jPanel_Authors.setVisible(false);
        jPanel_Authors.setBackground(Color.CYAN);

        javax.swing.GroupLayout jPanel_AuthorsLayout = new javax.swing.GroupLayout(jPanel_Authors);
        jPanel_Authors.setLayout(jPanel_AuthorsLayout);
        jPanel_AuthorsLayout.setHorizontalGroup(
            jPanel_AuthorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );
        jPanel_AuthorsLayout.setVerticalGroup(
            jPanel_AuthorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_GridLayout = new javax.swing.GroupLayout(jPanel_Grid);
        jPanel_Grid.setLayout(jPanel_GridLayout);
        jPanel_GridLayout.setHorizontalGroup(
            jPanel_GridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_GridLayout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jPanel_Authors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel_GridLayout.setVerticalGroup(
            jPanel_GridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GridLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jPanel_Authors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );

        jPanel_Tools.setBackground(new java.awt.Color(127, 127, 127));
        jPanel_Tools.setMaximumSize(new java.awt.Dimension(200, 615));
        jPanel_Tools.setMinimumSize(new java.awt.Dimension(200, 615));
        jPanel_Tools.setPreferredSize(new java.awt.Dimension(200, 615));

        jPanel_Items.setBackground(new java.awt.Color(170, 170, 170));
        jPanel_Items.setMaximumSize(new java.awt.Dimension(160, 510));
        jPanel_Items.setMinimumSize(new java.awt.Dimension(160, 510));
        jPanel_Items.setPreferredSize(new java.awt.Dimension(160, 510));

        javax.swing.GroupLayout jPanel_ItemsLayout = new javax.swing.GroupLayout(jPanel_Items);
        jPanel_Items.setLayout(jPanel_ItemsLayout);
        jPanel_ItemsLayout.setHorizontalGroup(
            jPanel_ItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel_ItemsLayout.setVerticalGroup(
            jPanel_ItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_ToolsLayout = new javax.swing.GroupLayout(jPanel_Tools);
        jPanel_Tools.setLayout(jPanel_ToolsLayout);
        jPanel_ToolsLayout.setHorizontalGroup(
            jPanel_ToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ToolsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Items, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel_ToolsLayout.setVerticalGroup(
            jPanel_ToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ToolsLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jPanel_Items, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jLabel_Description.setBackground(new java.awt.Color(127, 127, 127));
        jLabel_Description.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel_Description.setForeground(new java.awt.Color(255, 255, 255));
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
        jMenuItem_Settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem_SettingsMouseClicked(evt);
            }
        });
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
        int imput = JOptionPane.showConfirmDialog(null, newFileInfo, newFileTitle, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon("Assets\\Icons\\NewFile_Warning.png"));
        switch (imput) {
            case 0:
                resetMap();
                break;
        }
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

    private void jMenuItem_SettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem_SettingsMouseClicked
        visibleSettingsPanel(true);
    }//GEN-LAST:event_jMenuItem_SettingsMouseClicked

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
    private javax.swing.JFileChooser jFileChooser_OpenFile;
    private javax.swing.JFileChooser jFileChooser_SaveFile;
    private javax.swing.JLabel jLabel_Description;
    private javax.swing.JMenuBar jMenuBar_Menu;
    private javax.swing.JMenu jMenuItem_Languages;
    private javax.swing.JMenu jMenuItem_NewFile;
    private javax.swing.JMenu jMenuItem_OpenFile;
    private javax.swing.JMenu jMenuItem_SaveFile;
    private javax.swing.JMenu jMenuItem_Settings;
    private javax.swing.JPanel jPanel_Authors;
    private javax.swing.JPanel jPanel_Grid;
    private javax.swing.JPanel jPanel_Items;
    private javax.swing.JPanel jPanel_Main;
    private javax.swing.JPanel jPanel_Tools;
    // End of variables declaration//GEN-END:variables
}
