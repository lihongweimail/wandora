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
 * UClassifierConfiguration.java
 *
 * Created on 20.7.2011, 13:26:29
 */

package org.wandora.application.tools.extractors.uclassify;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JDialog;
import javax.swing.RowSorter;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.wandora.application.Wandora;
import org.wandora.application.gui.UIBox;
import org.wandora.application.gui.WandoraOptionPane;
import org.wandora.application.gui.simple.SimpleButton;
import org.wandora.application.gui.simple.SimpleScrollPane;
import org.wandora.utils.Options;

/**
 *
 * @author akivela
 */


public class UClassifierConfiguration extends javax.swing.JPanel {

    private JDialog confDialog = null;
    private boolean isAccepted = false;
    private Wandora wandora = null;
    private String[] classifiers = null;
    private AbstractUClassifier parent = null;
    
    
    /** Creates new form UClassifierConfiguration */
    public UClassifierConfiguration(Wandora w, Options options, AbstractUClassifier p) {
        wandora = w;
        parent = p;
        initComponents();
    }
    
    
    public void setClassifiers(String[] cs) {
        classifiers = cs;
        TableModel model = new ClassifierTableModel(cs);
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        classifierTable.setRowSorter(sorter);
        classifierTable.setModel(model);
    }
    
    
    
    public boolean wasAccepted() {
        return isAccepted;
    }
    
    
    public String[] getClassifiers() {
        return classifiers;
    }
    
    
    public void open() {
        confDialog = new JDialog(wandora, true);
        confDialog.setTitle("uClassifier configuration");
        confDialog.add(this);
        confDialog.setSize(730,350);
        wandora.centerWindow(confDialog);
        confDialog.setVisible(true);
    }
    
    
    
    private void removeSelectedClassifiers() {
        int[] rows = classifierTable.getSelectedRows();
        ArrayList<String> newClassifiers = new ArrayList<String>();
        for(int r=0; r<classifiers.length/2; r++) {
            boolean isSelected = false;
            for(int j=0; j<rows.length; j++) { 
                if(r == j) {
                    isSelected = true;
                    break;
                }
            }
            if(!isSelected) {
                newClassifiers.add(classifiers[r*2]);
                newClassifiers.add(classifiers[r*2+1]);
            }
        }
        setClassifiers( newClassifiers.toArray( new String[] {} ));
    }
    
    
    private void addClassifier() {
        UClassifierAddDialog d = new UClassifierAddDialog(wandora);
        d.setSize(400,180);
        UIBox.centerWindow(d, wandora);
        d.setVisible(true);
        
        // --- WAIT
        
        if(d.wasAccepted()) {
            String n = d.getName();
            String a = d.getAuthor();
            if(n == null || n.length() == 0) return;
            if(a == null || a.length() == 0) return;
            
            ArrayList<String> newClassifiers = new ArrayList<String>();
            newClassifiers.addAll(Arrays.asList(classifiers));
            newClassifiers.add(n);
            newClassifiers.add(a);
            
            setClassifiers( newClassifiers.toArray( new String[] {} ));
        }
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

        tablePanel = new javax.swing.JPanel();
        tableScrollPane = new SimpleScrollPane();
        classifierTable = new javax.swing.JTable();
        buttonPanel = new javax.swing.JPanel();
        addClassifierButton = new SimpleButton();
        removeClassifierButton = new SimpleButton();
        buttonFillerPanel2 = new javax.swing.JPanel();
        forgetApiKeyButton = new SimpleButton();
        buttonFillerPanel = new javax.swing.JPanel();
        okButton = new SimpleButton();
        cancelButton = new SimpleButton();

        setLayout(new java.awt.GridBagLayout());

        tablePanel.setLayout(new java.awt.GridBagLayout());

        classifierTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableScrollPane.setViewportView(classifierTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tablePanel.add(tableScrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        add(tablePanel, gridBagConstraints);

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        addClassifierButton.setText("Add");
        addClassifierButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        addClassifierButton.setPreferredSize(new java.awt.Dimension(75, 23));
        addClassifierButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addClassifierButtonMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        buttonPanel.add(addClassifierButton, gridBagConstraints);

        removeClassifierButton.setText("Remove");
        removeClassifierButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        removeClassifierButton.setPreferredSize(new java.awt.Dimension(75, 23));
        removeClassifierButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                removeClassifierButtonMouseReleased(evt);
            }
        });
        buttonPanel.add(removeClassifierButton, new java.awt.GridBagConstraints());

        buttonFillerPanel2.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        buttonPanel.add(buttonFillerPanel2, gridBagConstraints);

        forgetApiKeyButton.setText("Forget Api Key");
        forgetApiKeyButton.setMargin(new java.awt.Insets(1, 6, 1, 6));
        forgetApiKeyButton.setPreferredSize(new java.awt.Dimension(105, 23));
        forgetApiKeyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                forgetApiKeyButtonMouseReleased(evt);
            }
        });
        buttonPanel.add(forgetApiKeyButton, new java.awt.GridBagConstraints());

        buttonFillerPanel.setMinimumSize(new java.awt.Dimension(2, 2));
        buttonFillerPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        buttonPanel.add(buttonFillerPanel, gridBagConstraints);

        okButton.setText("OK");
        okButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        okButton.setPreferredSize(new java.awt.Dimension(75, 23));
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                okButtonMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        buttonPanel.add(okButton, gridBagConstraints);

        cancelButton.setText("Cancel");
        cancelButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        cancelButton.setPreferredSize(new java.awt.Dimension(75, 23));
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancelButtonMouseReleased(evt);
            }
        });
        buttonPanel.add(cancelButton, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 4);
        add(buttonPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseReleased
        isAccepted = false;
        if(confDialog != null) confDialog.setVisible(false);
    }//GEN-LAST:event_cancelButtonMouseReleased

    private void okButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseReleased
        isAccepted = true;
        if(confDialog != null) confDialog.setVisible(false);
    }//GEN-LAST:event_okButtonMouseReleased

    private void removeClassifierButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeClassifierButtonMouseReleased
        removeSelectedClassifiers();
    }//GEN-LAST:event_removeClassifierButtonMouseReleased

    private void addClassifierButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addClassifierButtonMouseReleased
        addClassifier();
    }//GEN-LAST:event_addClassifierButtonMouseReleased

    private void forgetApiKeyButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgetApiKeyButtonMouseReleased
        if(parent != null) {
            parent.forgetAuthorization();
            WandoraOptionPane.showMessageDialog(confDialog, "uClassify api key is forgot. Wandora asks new api key next time you access the uClassify web service.", "uClassify api key forgot");
        }
    }//GEN-LAST:event_forgetApiKeyButtonMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addClassifierButton;
    private javax.swing.JPanel buttonFillerPanel;
    private javax.swing.JPanel buttonFillerPanel2;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTable classifierTable;
    private javax.swing.JButton forgetApiKeyButton;
    private javax.swing.JButton okButton;
    private javax.swing.JButton removeClassifierButton;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables



    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------
    
    
    
    
    private class ClassifierTableModel implements TableModel {

        private String[] modelArray = null;
        
        public ClassifierTableModel(String[] m) {
            modelArray = m;
        }
        
        
        public int getRowCount() {
            return modelArray.length / 2;
        }

        public int getColumnCount() {
            return 2;
        }

        public String getColumnName(int columnIndex) {
            switch(columnIndex) {
                case 0: return "Classifier name";
                case 1: return "Classifier author"; 
            }
            return null;
        }

        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return modelArray[rowIndex*2+columnIndex];
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            modelArray[rowIndex*2+columnIndex] = aValue.toString();
        }

        public void addTableModelListener(TableModelListener l) {
            
        }

        public void removeTableModelListener(TableModelListener l) {

        }
        
    }

}
