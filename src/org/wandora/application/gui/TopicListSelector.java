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
 * TopicListSelector.java
 *
 * Created on 10. heinäkuuta 2007, 15:38
 */

package org.wandora.application.gui;


import java.awt.Component;
import javax.swing.*;
import org.wandora.topicmap.*;
import java.util.*;

/**
 *
 * @author  olli
 */
public class TopicListSelector extends javax.swing.JPanel implements TopicSelector {

    private DefaultListModel listModel;
    private String name;
    
    /** Creates new form TopicListSelector */
    public TopicListSelector(Vector<Topic> topics) {
        this(topics,"Suggested topics");
    }
    public TopicListSelector(Vector<Topic> topics,String name) {
        this.name=name;
        listModel=new DefaultListModel();
        for(Topic t : topics){
            listModel.addElement(new ListWrapper(t));
        }
        initComponents();
        if(listModel.size()>0) list.setSelectedIndex(0);
    }

    @Override
    public void init() {
    }

    @Override
    public String getSelectorName() {
        return name;
    }

    @Override
    public Topic[] getSelectedTopics() {
        Topic t=getSelectedTopic();
        if(t==null) return new Topic[0];
        else return new Topic[]{t};
    }

    @Override
    public Topic getSelectedTopic() {
        return ((ListWrapper)list.getSelectedValue()).t;
    }

    @Override
    public Component getPanel() {
        return this;
    }

    @Override
    public void cleanup() {
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        scrollPane = new javax.swing.JScrollPane();
        list = new javax.swing.JList();

        setLayout(new java.awt.GridBagLayout());

        list.setFont(UIConstants.plainFont);
        list.setModel(listModel);
        scrollPane.setViewportView(list);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(scrollPane, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList list;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables

    private static class ListWrapper{
        public Topic t;
        public ListWrapper(Topic t){this.t=t;};
        @Override
        public String toString(){
            try{
                String r=t.getBaseName();
                if(r!=null) return r;
                else return t.getOneSubjectIdentifier().toExternalForm();
          }catch(TopicMapException tme){tme.printStackTrace(); return "Exception";}    
        }
    }
    
}
