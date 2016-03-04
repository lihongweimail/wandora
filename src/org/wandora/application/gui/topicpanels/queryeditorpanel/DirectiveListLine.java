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
 */
package org.wandora.application.gui.topicpanels.queryeditorpanel;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.TransferHandler;
import org.wandora.query2.DirectiveUIHints;

/**
 *
 * @author olli
 */


public class DirectiveListLine extends javax.swing.JPanel {

    protected DirectiveUIHints hints;
    
    /**
     * Creates new form DirectiveListLine
     */
    public DirectiveListLine() {
        initComponents();
        
        DnDTools.setDragSourceHandler(this, "directiveHints", DnDTools.directiveHintsDataFlavor, 
                new DnDTools.DragSourceCallback<DirectiveUIHints>() {
            @Override
            public DirectiveUIHints callback(JComponent component) {
                return hints;
            }
        });

        
    }
    
    public DirectiveListLine(DirectiveUIHints hints) {
        this();
        this.hints=hints;
        this.setLabel(hints.getLabel());
    }
    
    public void setLabel(String s){
        directiveLabel.setText(s);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        directiveLabel = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        directiveLabel.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        add(directiveLabel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel directiveLabel;
    // End of variables declaration//GEN-END:variables
}
