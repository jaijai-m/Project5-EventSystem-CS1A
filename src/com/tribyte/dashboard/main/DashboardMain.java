package com.tribyte.dashboard.main;

import com.tribyte.component.Header;
import com.tribyte.component.Menu;
import com.tribyte.dialog.MessageLogout;
import com.tribyte.event.EventMenuSelected;
import com.tribyte.form.FormEvents;
import com.tribyte.form.FormCreateEvent;
import com.tribyte.form.FormEditExistingEvents;
import com.tribyte.form.FormEditingEvent;
import com.tribyte.form.FormHome;
import com.tribyte.form.MainForm;
import com.tribyte.model.ModelEvents;
import com.tribyte.model.ModelMenu;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class DashboardMain extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DashboardMain.class.getName());

    private String userRole = "Admin"; 
    private int userId = 101;
    private Menu menu = new Menu();
    private MigLayout layout;
    private Animator animator;  
    private boolean menuShow = true;
    private Header header;
    private MainForm main;
    
    public DashboardMain() {
        initComponents();
        init();
    }
    
    private void init() {
        layout = new MigLayout("fill, insets 0, gap 0", "0[]0[fill, grow]0", "0[fill]0");
        body.setLayout(layout);

        header = new Header();
        main = new MainForm();
        main.setOpaque(false);
        main.setLayout(new BorderLayout());

        menu.addEventLogout(e -> {
            MessageLogout msg = new MessageLogout(this);
            msg.showMessage("Logout Confirmation", "Are you sure you want to log out?");

            if (msg.getMessageType() == MessageLogout.MessageType.CONFIRM) {
                this.dispose();
            }
        });

        menu.setEvent(new EventMenuSelected() {
            @Override
            public void selected(int menuIndex) {
                if (menuIndex == 0) {
                    FormHome home = new FormHome(userRole, userId);

                    home.addEvent(e -> {
                        if ("EDIT_EVENT".equals(e.getActionCommand())) {
                            ModelEvents data = (ModelEvents) e.getSource();
                            FormEditingEvent editingForm = new FormEditingEvent(data);
                            editingForm.addBackEvent(back -> selected(0));
                            showForm(editingForm);
                        }
                    });

                    showForm(home);
                } // <--- Only one brace here!
                else if (menuIndex == 1) {
                    FormEvents fEvents = new FormEvents(userRole);

                    fEvents.addEvent(e -> {
                        String command = e.getActionCommand();

                        if ("Create New Event".equals(command)) {
                            FormCreateEvent createForm = new FormCreateEvent();
                            createForm.addBackEvent(ev -> selected(1));
                            showForm(createForm);
                        } else if ("Edit Existing Events".equals(command) || "Manage Events".equals(command)) {
                            FormEditExistingEvents editForm = new FormEditExistingEvents(userRole, userId);

                            editForm.addEvent(ev -> {
                                if ("EDIT_EVENT".equals(ev.getActionCommand())) {
                                    ModelEvents data = (ModelEvents) ev.getSource();
                                    FormEditingEvent editingForm = new FormEditingEvent(data);
                                    editingForm.addBackEvent(back -> selected(1));
                                    showForm(editingForm);
                                }
                            });

                            editForm.addBackEvent(back -> selected(1));
                            showForm(editForm);
                        }
                    });
                    showForm(fEvents);
                }
            } 
        }); 

        menu.addMenu(new ModelMenu("Dashboard", new ImageIcon(getClass().getResource("/com/tribyte/icon/dboard.png"))));
        menu.addMenu(new ModelMenu("Events", new ImageIcon(getClass().getResource("/com/tribyte/icon/event.png"))));
        menu.addMenu(new ModelMenu("Attendance", new ImageIcon(getClass().getResource("/com/tribyte/icon/attendance.png"))));

        body.add(menu, "w 230!, spany");
        body.add(header, "h 50!, wrap");
        body.add(main, "w 100%, h 100%");
        
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if(menuShow) {
                    width = 60 + (170 * (1f - fraction));
                    menu.setAlpha(1f - fraction);
                } else {
                    width = 60 + (170 * fraction);
                    menu.setAlpha(fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2 ");
                menu.revalidate();
            }

            @Override
            public void end() {
                menuShow = !menuShow;
            }
            
        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        header.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
        //Starting Form
        main.showForm(new FormHome(userRole, userId));
    }

    private void showForm(Component com) {
        main.removeAll();
        main.add(com);
        main.repaint();
        main.revalidate();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        body.setBackground(new java.awt.Color(245, 245, 245));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1032, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new DashboardMain().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    // End of variables declaration//GEN-END:variables
}
