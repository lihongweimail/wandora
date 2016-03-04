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
 * TopicsOfTypeSelector.java
 *
 * Created on 9. heinäkuuta 2007, 13:56
 */

package org.wandora.application.gui;
import java.awt.Component;
import javax.swing.*;
import org.wandora.application.Wandora;
import org.wandora.topicmap.*;
import org.wandora.application.gui.simple.*;
import org.wandora.topicmap.TMBox;
import java.util.*;
import org.wandora.topicmap.SchemaBox;
/**
 *
 * @author  olli
 */
public class TopicsOfTypeSelector extends javax.swing.JPanel implements TopicSelector, Runnable {
    
    private DefaultListModel listModel;
    private Topic typeTopic;
    private Thread thread;
    private boolean running;
    private Collection<Topic> topicsOfType;
    private long lastTyped=0;
    private long lastPopulated=0;
    private String name;
    private boolean forcePopulate=false;
    
    private boolean useSchema;
    
    /** Creates new form TopicsOfTypeSelector */
    public TopicsOfTypeSelector(Topic typeTopic) {
        this(typeTopic,null,true);
    }
    public TopicsOfTypeSelector(Topic typeTopic,String name) {
        this(typeTopic,name,true);
    }
    public TopicsOfTypeSelector(Topic typeTopic,String name,boolean useSchema) {
        this.typeTopic=typeTopic;
        if(name==null) {
            try{
                this.name=typeTopic.getDisplayName("en");
            }catch(TopicMapException tme){tme.printStackTrace();}
        }
        else this.name=name;
        this.useSchema=useSchema;
        listModel=new DefaultListModel();
        initComponents();
        textField.setFocusTraversalKeysEnabled(false);
    }
    
    private void populateList(){
//        listModel.clear();
        DefaultListModel newModel=new DefaultListModel();
        String written=textField.getText().toLowerCase();
        try{
            for(Topic t : topicsOfType){
                String bn=t.getBaseName();
                if(bn==null) continue;
                if(bn.toLowerCase().startsWith(written)){
                    newModel.addElement(new ListWrapper(t));
                }
            }
        }
        catch(TopicMapException tme){tme.printStackTrace();}
        list.setModel(newModel);
    }

    public void init() {
        thread=new Thread(this);
        running=true;
        thread.start();
    }

    public String getSelectorName() {
        return name;
    }

    public Topic[] getSelectedTopics() {
        Topic t=getSelectedTopic();
        if(t==null) return new Topic[0];
        else return new Topic[]{t};
    }

    public Topic getSelectedTopic() {
        return ((ListWrapper)list.getSelectedValue()).t;
    }

    public Component getPanel() {
        return this;
    }

    public void cleanup() {
        running=false;
    }

    public void run() {
        try{
            Collection<Topic> temp;
            if(useSchema) temp=SchemaBox.getInstancesOf(typeTopic);
            else temp=typeTopic.getTopicMap().getTopicsOfType(typeTopic);
            topicsOfType=TMBox.sortTopics(temp,null);
            if(topicsOfType.size()<100) forcePopulate=true;
        }catch(TopicMapException tme){tme.printStackTrace();}
        while(running){
            long delay=0;
            long t=System.currentTimeMillis();
            if( (lastTyped>0 && t>=lastTyped+1000 && 
                    lastPopulated<lastTyped && textField.getText().length()>=3) || forcePopulate){
                forcePopulate=false;
                populateList();
                lastPopulated=t;
            }
            else if(lastTyped>0 && lastPopulated<lastTyped && textField.getText().length()>=3){
                delay=lastTyped+1000-t;
            }
            synchronized(this){
                try{
                    if(delay==0) this.wait();
                    else this.wait(delay);
                }catch(InterruptedException ie){}
            }
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        textField = new javax.swing.JTextField();
        scrollPane = new javax.swing.JScrollPane();
        list = new javax.swing.JList();
        findButton = new org.wandora.application.gui.simple.SimpleButton();

        setLayout(new java.awt.GridBagLayout());

        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 2);
        add(textField, gridBagConstraints);

        list.setFont(UIConstants.smallButtonLabelFont);
        list.setModel(listModel);
        list.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                listKeyReleased(evt);
            }
        });
        scrollPane.setViewportView(list);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 4);
        add(scrollPane, gridBagConstraints);

        findButton.setText("Find");
        findButton.setMargin(new java.awt.Insets(0, 2, 0, 2));
        findButton.setMaximumSize(new java.awt.Dimension(50, 19));
        findButton.setMinimumSize(new java.awt.Dimension(50, 19));
        findButton.setPreferredSize(new java.awt.Dimension(50, 19));
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 4);
        add(findButton, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void listKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listKeyReleased
        if(evt.getKeyCode()==evt.VK_ENTER){
            // select topic and close dialog
            // current topic selector doesn't have a mechanism to close the dialog
        }
    }//GEN-LAST:event_listKeyReleased

    private void textFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldKeyReleased
        if(evt.getKeyCode()==evt.VK_DOWN){
            if(listModel.size()>0){
                list.setSelectedIndex(0);
                list.requestFocus();
            }
        }
        else if(evt.getKeyCode()==evt.VK_UP){
            if(listModel.size()>0){
                list.setSelectedIndex(listModel.size()-1);
                list.requestFocus();
            }
        }
        else if(evt.getKeyCode()==evt.VK_TAB){
            forcePopulate=true;
            synchronized(this){
                this.notifyAll();
            }
        }
    }//GEN-LAST:event_textFieldKeyReleased

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed
        forcePopulate=true;
        synchronized(this){
            this.notifyAll();
        }
    }//GEN-LAST:event_findButtonActionPerformed

    private void textFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldKeyTyped
        lastTyped=System.currentTimeMillis();
        synchronized(this){
            this.notifyAll();
        }
    }//GEN-LAST:event_textFieldKeyTyped
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton findButton;
    private javax.swing.JList list;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextField textField;
    // End of variables declaration//GEN-END:variables
    
    private static class ListWrapper{
        public Topic t;
        public ListWrapper(Topic t){this.t=t;};
        public String toString(){
            try{
                String r=t.getBaseName();
                if(r!=null) return r;
                else return t.getOneSubjectIdentifier().toExternalForm();
          }catch(TopicMapException tme){tme.printStackTrace(); return "Exception";}    
        }
    }
}
