/*
 * WANDORA
 * Knowledge Extraction, Management, and Publishing Application
 * http://wandora.org
 * 
 * Copyright (C) 2004-2016 Wandora Team
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * 
 * SOMTopicVisualization.java
 *
 * Created on 30. heinäkuuta 2008, 13:43
 */

package org.wandora.application.tools.som;





import org.wandora.utils.*;
import static org.wandora.utils.Tuples.*;
import org.wandora.topicmap.*;
import org.wandora.application.*;
import org.wandora.application.gui.simple.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;


/**
 *
 * @author  akivela
 */
public class SOMTopicVisualization extends javax.swing.JDialog {

    SOMMap map = null;

    
    
    /** Creates new form SOMTopicVisualization */
    public SOMTopicVisualization(Wandora admin) {
        super(admin, true);
        initComponents();
        this.setSize(600,600);
        this.setTitle("Topic SOM Visualization");
        admin.centerWindow(this);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ((SOMTopicVisualizationPanel) mapPanel).shouldStop();
            }
        });
    }

    
    
    public void visualize(SOMMap map) {
        this.map = map;
        ((SOMTopicVisualizationPanel) mapPanel).initialize(this, map);
        setVisible(true);
    }
    
    
    public void copyMapVectors() {
        StringBuilder sb = new StringBuilder("");
        if(map != null) {
            int mapSize = map.getSize();
            SOMNeuron n = null;
            for(int i=0; i<mapSize; i++) {
                for(int j=0; j<mapSize; j++) {
                    sb.append(i).append("\t").append(j).append("\t");
                    n = map.getAt(i,j);
                    if(n != null) {
                        sb.append(n.toString());
                    }
                    else {
                        sb.append("null");
                    }
                    sb.append("\n");
                }
            }
        }
        ClipboardBox.setClipboard(sb.toString());
    }
    
    
    
    public void copySamples() {
        StringBuilder sb = new StringBuilder("");
        if(map != null) {
            HashMap<Topic,SOMVector> samples = map.getSamples();
            Set<Topic> set = samples.keySet();
            Topic t = null;
            SOMVector v = null;
            for(Iterator<Topic> iter = set.iterator(); iter.hasNext(); ) {
                try {
                    t = iter.next();
                    v = samples.get(t);

                    sb.append(t.getBaseName()).append("\t");
                    sb.append(v.toString());
                    sb.append("\n");
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ClipboardBox.setClipboard(sb.toString());
    }
    
    
    
    public void updateCellSize() {
        ((SOMTopicVisualizationPanel) mapPanel).setCellSize(cellSizeSlider.getValue());
        this.setVisible(true);
    }
    

    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mapScrollPane = new SimpleScrollPane();
        mapPanel = new SOMTopicVisualizationPanel();
        buttonPanel = new javax.swing.JPanel();
        sizeSliderPanel = new javax.swing.JPanel();
        cellSizeSlider = new javax.swing.JSlider();
        fillerPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        closeButton = new SimpleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        mapPanel.setFocusTraversalPolicyProvider(true);

        javax.swing.GroupLayout mapPanelLayout = new javax.swing.GroupLayout(mapPanel);
        mapPanel.setLayout(mapPanelLayout);
        mapPanelLayout.setHorizontalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 539, Short.MAX_VALUE)
        );
        mapPanelLayout.setVerticalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );

        mapScrollPane.setViewportView(mapPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(mapScrollPane, gridBagConstraints);

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        sizeSliderPanel.setLayout(new java.awt.GridBagLayout());

        cellSizeSlider.setMaximum(200);
        cellSizeSlider.setMinimum(5);
        cellSizeSlider.setToolTipText("SOM cell size slider");
        cellSizeSlider.setPreferredSize(new java.awt.Dimension(200, 21));
        cellSizeSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cellSizeSliderMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cellSizeSliderMouseReleased(evt);
            }
        });
        cellSizeSlider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                cellSizeSliderMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                cellSizeSliderMouseMoved(evt);
            }
        });
        sizeSliderPanel.add(cellSizeSlider, new java.awt.GridBagConstraints());

        buttonPanel.add(sizeSliderPanel, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout fillerPanelLayout = new javax.swing.GroupLayout(fillerPanel);
        fillerPanel.setLayout(fillerPanelLayout);
        fillerPanelLayout.setHorizontalGroup(
            fillerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        fillerPanelLayout.setVerticalGroup(
            fillerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        buttonPanel.add(fillerPanel, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        closeButton.setText("Close");
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeButtonMouseReleased(evt);
            }
        });
        jPanel2.add(closeButton, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        buttonPanel.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(buttonPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void closeButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseReleased
    ((SOMTopicVisualizationPanel) mapPanel).shouldStop();
    this.setVisible(false);
}//GEN-LAST:event_closeButtonMouseReleased


private void cellSizeSliderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cellSizeSliderMousePressed
    updateCellSize();
}//GEN-LAST:event_cellSizeSliderMousePressed

private void cellSizeSliderMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cellSizeSliderMouseMoved
    updateCellSize();
}//GEN-LAST:event_cellSizeSliderMouseMoved

private void cellSizeSliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cellSizeSliderMouseReleased
    updateCellSize();
}//GEN-LAST:event_cellSizeSliderMouseReleased

private void cellSizeSliderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cellSizeSliderMouseDragged
    updateCellSize();
}//GEN-LAST:event_cellSizeSliderMouseDragged

private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
    System.out.println("key pressed at FORM");
    if(mapPanel instanceof SOMTopicVisualizationPanel) {
        ((SOMTopicVisualizationPanel) mapPanel).keyPressed(evt);
    }
}//GEN-LAST:event_formKeyPressed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JSlider cellSizeSlider;
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel fillerPanel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel mapPanel;
    private javax.swing.JScrollPane mapScrollPane;
    private javax.swing.JPanel sizeSliderPanel;
    // End of variables declaration//GEN-END:variables

    
    
    
}
