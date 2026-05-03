package com.tribyte.dialog;

import com.tribyte.swing.Glass;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Message extends javax.swing.JDialog {

    private final JFrame fram;
    private Animator animator;
    private Glass glass;
    private boolean show;
    private MessageType messageType = MessageType.CANCEL; 

    public Message(JFrame fram) {
        super(fram, true);
        this.fram = fram;
        initComponents();
        init();
    }
    
    private void init() {
        /*txt = new javax.swing.JTextPane();
        txtsub = new javax.swing.JTextPane();
        lbTitle = new javax.swing.JLabel();*/
        
        setBackground(new Color(0, 0, 0, 0));
        //javax.swing.text.StyledDocument doc = txt.getStyledDocument();
        //javax.swing.text.SimpleAttributeSet center = new javax.swing.text.SimpleAttributeSet();
        StyledDocument doc1 = txt.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        
        //javax.swing.text.StyleConstants.setAlignment(center, javax.swing.text.StyleConstants.ALIGN_CENTER);
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc1.setParagraphAttributes(0, doc1.getLength(), center, false);
        
        txt.setOpaque(false);
        txt.setBackground(new Color(0, 0, 0, 0));
        StyledDocument doc2 = txtsub.getStyledDocument();
        doc2.setParagraphAttributes(0, doc2.getLength(), center, false);
        txtsub.setOpaque(false);
        txtsub.setBackground(new Color(0, 0, 0, 0));
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeMessage();
            }
        });
        
        animator = new Animator(350, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float f = show ? fraction : 1f - fraction;
                glass.setAlpha(f - f * 0.3f);
                setOpacity(f);
            }

            @Override
            public void end() {
                if(show == false) {
                    dispose();
                    glass.setVisible(false);
                }
            }
        });
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        setOpacity(0f);
        glass = new Glass();
    }

    private void startAnimator (boolean show) {
        if(animator.isRunning()) {
            float f = animator.getTimingFraction();
            animator.stop();
            animator.setStartFraction(1f - f);
        } else {
            animator.setStartFraction(0f);
        }
        this.show = show;
        animator.start();
    }
    public void showMessage(/*MessageType type,*/ String title, String message, String submessage) {
        /*this.messageType = type;*/
        fram.setGlassPane(glass);
        glass.setVisible(true);
        
        /*if (type == MessageType.SUCCESS) {
            lbTitle.setForeground(new Color(4, 149, 22));
        } else {
            lbTitle.setForeground(new Color(255, 0, 0));
        }*/
        
        lbTitle.setText(title);
        txt.setText(message);
        txtsub.setText(submessage);
        setLocationRelativeTo(fram);
        startAnimator(true);
        setVisible(true);
    }
    
    public void closeMessage() {
        startAnimator(false);
    }
    
    public MessageType getMessageType() {
        return messageType;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        messageType = MessageType.CANCEL;
        closeMessage();
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void cmdYesDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdYesDeleteActionPerformed
        messageType = MessageType.YESDELETE;
        closeMessage();
    }//GEN-LAST:event_cmdYesDeleteActionPerformed

    public static enum MessageType {
        CANCEL, YESDELETE/*,SUCCESS*/
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextPane txt;
    private javax.swing.JTextPane txtsub;
    // End of variables declaration
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
