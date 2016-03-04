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
 */
package org.wandora.application.gui.topicpanels.queryeditorpanel;

import javax.swing.JPanel;

/**
 *
 * @author olli
 */


public class QueryEditorInspectorPanel extends javax.swing.JPanel {

    protected Object selectedObject;
    protected JPanel editor;
    
    /**
     * Creates new form QueryEditorInspectorPanel
     */
    public QueryEditorInspectorPanel() {
        initComponents();
        
        this.add(emptyPanel);
    }
    
    public void saveChanges(){
        if(this.selectedObject!=null && this.selectedObject instanceof DirectivePanel){
            if(editor!=null && editor instanceof DirectiveEditor) ((DirectiveEditor)editor).saveChanges();
        }
        
    }

    public void setSelection(Object o){
        saveChanges();
        
        this.selectedObject=o;
        this.removeAll();
        if(o==null) this.add(emptyPanel);
        else if(o instanceof DirectivePanel){
            DirectivePanel panel=(DirectivePanel)o;
            editor=panel.getEditorPanel();
            if(editor==null) this.add(new JPanel());
            else this.add(editor);
        }
        
        this.revalidate();
        this.repaint();
    }
    
    public Object getSelection(){
        return selectedObject;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        emptyPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        emptyPanel.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nothing selected");
        jLabel1.setEnabled(false);
        emptyPanel.add(jLabel1, java.awt.BorderLayout.CENTER);

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel emptyPanel;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
