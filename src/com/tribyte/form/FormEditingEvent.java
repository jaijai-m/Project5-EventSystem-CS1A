package com.tribyte.form;

import com.tribyte.dialog.Notification;
import com.tribyte.model.ModelEventStorage;
import com.tribyte.model.ModelEvents;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

public class FormEditingEvent extends JPanel {
    
    private ActionListener backEvent;
    private ModelEvents editingData;

    public FormEditingEvent(ModelEvents data) {
        this.editingData = data;
        initComponents();
        setOpaque(false);

        ButtonGroup group = new ButtonGroup();
        group.add(chkYes);
        group.add(chkNo);

        ButtonGroup groupStatus = new ButtonGroup();
        groupStatus.add(chkOpen);
        groupStatus.add(chkLock);

        // Styling
        styleCheckBox(chkYes);
        styleCheckBox(chkNo);
        styleCheckBox(chkOpen);
        styleCheckBox(chkLock);

        styleTextField(jTextField1);
        styleTextField(jTextField2);
        styleTextField(jTextField3);
        styleTextField(jTextField5);
        styleTextField(jTextField6);
        styleTextField(jTextField7);
        styleTextField(jTextField8);

        chkYes.addActionListener(e -> txtInsertCode.setVisible(true));
        chkNo.addActionListener(e -> txtInsertCode.setVisible(false));

        btnBack.addActionListener(e -> {
            if (backEvent != null) {
                backEvent.actionPerformed(e);
            }
        });

        // Numeric Filters
        ((AbstractDocument) jTextField5.getDocument()).setDocumentFilter(new NumericFilter());
        ((AbstractDocument) jTextField8.getDocument()).setDocumentFilter(new NumericFilter());

        // LOAD DATA
        if (editingData != null) {
            loadData(editingData);
        }
    }

    public void loadData(ModelEvents data) {
        jTextField1.setText(data.getName());           // Event Name
        jTextField2.setText(data.getDate());           // Date
        jTextField6.setText(data.getProfessor());      // Organizer
        jTextField7.setText(data.getVenue());          // Venue
        jTextField8.setText(String.valueOf(data.getMaxSlots())); // Slots
        jTextField5.setText(String.valueOf(data.getOwnerID()));  // ID

        if (data.getAccessibility().equalsIgnoreCase("Private")) {
            chkYes.setSelected(true);
            txtInsertCode.setVisible(true);
            jTextField3.setText(data.getEventCode());
        } else {
            chkNo.setSelected(true);
            txtInsertCode.setVisible(false);
        }

        if (data.getStatus().equalsIgnoreCase("Open")) {
            chkOpen.setSelected(true);
        } else {
            chkLock.setSelected(true);
        }
    }

    public void addBackEvent(ActionListener event) {
        this.backEvent = event;
    }

    private void styleTextField(JTextField field) {
        field.setEditable(true);
        field.setText("");
        field.setOpaque(false);
        field.setBackground(new java.awt.Color(0, 0, 0, 0));

        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        field.setForeground(new Color(64, 64, 64)); 

        field.setBorder(createEmptyBorder(0, 15, 0, 10));
    }
    
    private void styleCheckBox(JCheckBox chk) {
        chk.setOpaque(false);
        chk.setFont(new Font("Segoe UI", Font.BOLD, 18));
        chk.setCursor(new Cursor(Cursor.HAND_CURSOR));

        chk.setIconTextGap(15);
        chk.setMargin(new java.awt.Insets(5, 5, 5, 5));
    }
    
    private void customizeCheckboxes() {
        for (JCheckBox chk : new JCheckBox[]{chkNo, chkYes}) {
            chk.setFont(new Font("Segoe UI", 1, 20));
            chk.setOpaque(false);
            chk.setCursor(new Cursor(Cursor.HAND_CURSOR));
            chk.setIconTextGap(15);
        }
    }
    
    class NumericFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (string.matches("\\d*")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text.matches("\\d*")) { 
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new javax.swing.JLabel();
        lbImage = new javax.swing.JLabel();
        btnBack = new com.tribyte.swing.ButtonCustomDBoard();
        panelRound1 = new com.tribyte.swing.PanelRound();
        txtNameEvent = new com.tribyte.swing.PanelRound();
        txt1 = new com.tribyte.swing.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        txtDateEvent = new com.tribyte.swing.PanelRound();
        txt2 = new com.tribyte.swing.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        txtNameEventOrganizer = new com.tribyte.swing.PanelRound();
        txt8 = new com.tribyte.swing.PanelRound();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        txtInsertCode = new com.tribyte.swing.PanelRound();
        txt3 = new com.tribyte.swing.PanelRound();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        txtEnterID = new com.tribyte.swing.PanelRound();
        txt7 = new com.tribyte.swing.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        txtRequireECode = new com.tribyte.swing.PanelRound();
        jLabel10 = new javax.swing.JLabel();
        chkNo = new javax.swing.JCheckBox();
        chkYes = new javax.swing.JCheckBox();
        txtStatus = new com.tribyte.swing.PanelRound();
        jLabel11 = new javax.swing.JLabel();
        chkLock = new javax.swing.JCheckBox();
        chkOpen = new javax.swing.JCheckBox();
        txtEventVenue = new com.tribyte.swing.PanelRound();
        txt9 = new com.tribyte.swing.PanelRound();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnUpload = new com.tribyte.swing.ButtonCustomDBoard();
        txtMaxSlots = new com.tribyte.swing.PanelRound();
        txt10 = new com.tribyte.swing.PanelRound();
        jLabel12 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();

        txt.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txt.setForeground(new java.awt.Color(4, 149, 22));
        txt.setText("EDITING EVENT");

        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tribyte/icon/eventSmall.png"))); // NOI18N

        btnBack.setBackground(new java.awt.Color(242, 242, 242));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tribyte/icon/back.png"))); // NOI18N
        btnBack.addActionListener(this::btnBackActionPerformed);

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));

        txtNameEvent.setBackground(new java.awt.Color(204, 204, 204));

        txt1.setBackground(new java.awt.Color(4, 149, 22));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Name of the Event:");

        javax.swing.GroupLayout txt1Layout = new javax.swing.GroupLayout(txt1);
        txt1.setLayout(txt1Layout);
        txt1Layout.setHorizontalGroup(
            txt1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txt1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addContainerGap())
        );
        txt1Layout.setVerticalGroup(
            txt1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        javax.swing.GroupLayout txtNameEventLayout = new javax.swing.GroupLayout(txtNameEvent);
        txtNameEvent.setLayout(txtNameEventLayout);
        txtNameEventLayout.setHorizontalGroup(
            txtNameEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtNameEventLayout.createSequentialGroup()
                .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        txtNameEventLayout.setVerticalGroup(
            txtNameEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtNameEventLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(txt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtDateEvent.setBackground(new java.awt.Color(204, 204, 204));

        txt2.setBackground(new java.awt.Color(4, 149, 22));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Date of the Event:");

        javax.swing.GroupLayout txt2Layout = new javax.swing.GroupLayout(txt2);
        txt2.setLayout(txt2Layout);
        txt2Layout.setHorizontalGroup(
            txt2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txt2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addContainerGap())
        );
        txt2Layout.setVerticalGroup(
            txt2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txt2Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(12, 12, 12))
        );

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
        jTextField2.addActionListener(this::jTextField2ActionPerformed);

        javax.swing.GroupLayout txtDateEventLayout = new javax.swing.GroupLayout(txtDateEvent);
        txtDateEvent.setLayout(txtDateEventLayout);
        txtDateEventLayout.setHorizontalGroup(
            txtDateEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtDateEventLayout.createSequentialGroup()
                .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2)
                .addContainerGap())
        );
        txtDateEventLayout.setVerticalGroup(
            txtDateEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtDateEventLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField2)
                .addContainerGap())
            .addComponent(txt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtNameEventOrganizer.setBackground(new java.awt.Color(204, 204, 204));

        txt8.setBackground(new java.awt.Color(4, 149, 22));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Name of Event Organizer");

        javax.swing.GroupLayout txt8Layout = new javax.swing.GroupLayout(txt8);
        txt8.setLayout(txt8Layout);
        txt8Layout.setHorizontalGroup(
            txt8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txt8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );
        txt8Layout.setVerticalGroup(
            txt8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txt8Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(12, 12, 12))
        );

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(204, 204, 204));
        jTextField6.addActionListener(this::jTextField6ActionPerformed);

        javax.swing.GroupLayout txtNameEventOrganizerLayout = new javax.swing.GroupLayout(txtNameEventOrganizer);
        txtNameEventOrganizer.setLayout(txtNameEventOrganizerLayout);
        txtNameEventOrganizerLayout.setHorizontalGroup(
            txtNameEventOrganizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtNameEventOrganizerLayout.createSequentialGroup()
                .addComponent(txt8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6)
                .addContainerGap())
        );
        txtNameEventOrganizerLayout.setVerticalGroup(
            txtNameEventOrganizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtNameEventOrganizerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField6)
                .addContainerGap())
            .addComponent(txt8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtInsertCode.setBackground(new java.awt.Color(204, 204, 204));

        txt3.setBackground(new java.awt.Color(4, 149, 22));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Insert Event Code:");

        javax.swing.GroupLayout txt3Layout = new javax.swing.GroupLayout(txt3);
        txt3.setLayout(txt3Layout);
        txt3Layout.setHorizontalGroup(
            txt3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txt3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );
        txt3Layout.setVerticalGroup(
            txt3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txt3Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(12, 12, 12))
        );

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(204, 204, 204));
        jTextField3.addActionListener(this::jTextField3ActionPerformed);

        javax.swing.GroupLayout txtInsertCodeLayout = new javax.swing.GroupLayout(txtInsertCode);
        txtInsertCode.setLayout(txtInsertCodeLayout);
        txtInsertCodeLayout.setHorizontalGroup(
            txtInsertCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtInsertCodeLayout.createSequentialGroup()
                .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3)
                .addContainerGap())
        );
        txtInsertCodeLayout.setVerticalGroup(
            txtInsertCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtInsertCodeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField3)
                .addContainerGap())
            .addComponent(txt3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtEnterID.setBackground(new java.awt.Color(204, 204, 204));

        txt7.setBackground(new java.awt.Color(4, 149, 22));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Enter ID (Admin/Staff)");

        javax.swing.GroupLayout txt7Layout = new javax.swing.GroupLayout(txt7);
        txt7.setLayout(txt7Layout);
        txt7Layout.setHorizontalGroup(
            txt7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txt7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );
        txt7Layout.setVerticalGroup(
            txt7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txt7Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(12, 12, 12))
        );

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(204, 204, 204));
        jTextField5.addActionListener(this::jTextField5ActionPerformed);

        javax.swing.GroupLayout txtEnterIDLayout = new javax.swing.GroupLayout(txtEnterID);
        txtEnterID.setLayout(txtEnterIDLayout);
        txtEnterIDLayout.setHorizontalGroup(
            txtEnterIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtEnterIDLayout.createSequentialGroup()
                .addComponent(txt7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5)
                .addContainerGap())
        );
        txtEnterIDLayout.setVerticalGroup(
            txtEnterIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtEnterIDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField5)
                .addContainerGap())
            .addComponent(txt7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtRequireECode.setBackground(new java.awt.Color(4, 149, 22));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Require Event Code?");

        javax.swing.GroupLayout txtRequireECodeLayout = new javax.swing.GroupLayout(txtRequireECode);
        txtRequireECode.setLayout(txtRequireECodeLayout);
        txtRequireECodeLayout.setHorizontalGroup(
            txtRequireECodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtRequireECodeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addContainerGap())
        );
        txtRequireECodeLayout.setVerticalGroup(
            txtRequireECodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtRequireECodeLayout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        chkNo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        chkNo.setText("No");

        chkYes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        chkYes.setText(" Yes");
        chkYes.addActionListener(this::chkYesActionPerformed);

        txtStatus.setBackground(new java.awt.Color(4, 149, 22));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Status:");

        javax.swing.GroupLayout txtStatusLayout = new javax.swing.GroupLayout(txtStatus);
        txtStatus.setLayout(txtStatusLayout);
        txtStatusLayout.setHorizontalGroup(
            txtStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        txtStatusLayout.setVerticalGroup(
            txtStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtStatusLayout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        chkLock.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        chkLock.setText("Lock");

        chkOpen.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        chkOpen.setText("Open");
        chkOpen.addActionListener(this::chkOpenActionPerformed);

        txtEventVenue.setBackground(new java.awt.Color(204, 204, 204));

        txt9.setBackground(new java.awt.Color(4, 149, 22));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Location of Event:");

        javax.swing.GroupLayout txt9Layout = new javax.swing.GroupLayout(txt9);
        txt9.setLayout(txt9Layout);
        txt9Layout.setHorizontalGroup(
            txt9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txt9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );
        txt9Layout.setVerticalGroup(
            txt9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txt9Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(12, 12, 12))
        );

        jTextField7.setEditable(false);
        jTextField7.setBackground(new java.awt.Color(204, 204, 204));
        jTextField7.addActionListener(this::jTextField7ActionPerformed);

        javax.swing.GroupLayout txtEventVenueLayout = new javax.swing.GroupLayout(txtEventVenue);
        txtEventVenue.setLayout(txtEventVenueLayout);
        txtEventVenueLayout.setHorizontalGroup(
            txtEventVenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtEventVenueLayout.createSequentialGroup()
                .addComponent(txt9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7)
                .addContainerGap())
        );
        txtEventVenueLayout.setVerticalGroup(
            txtEventVenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtEventVenueLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField7)
                .addContainerGap())
            .addComponent(txt9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnUpload.setBackground(new java.awt.Color(4, 149, 22));
        btnUpload.setForeground(new java.awt.Color(255, 255, 255));
        btnUpload.setText("Upload Event");
        btnUpload.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnUpload.setPreferredSize(new java.awt.Dimension(144, 50));
        btnUpload.addActionListener(this::btnUploadActionPerformed);
        jPanel1.add(btnUpload);

        txtMaxSlots.setBackground(new java.awt.Color(204, 204, 204));

        txt10.setBackground(new java.awt.Color(4, 149, 22));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Maximum Slots:");

        javax.swing.GroupLayout txt10Layout = new javax.swing.GroupLayout(txt10);
        txt10.setLayout(txt10Layout);
        txt10Layout.setHorizontalGroup(
            txt10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txt10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );
        txt10Layout.setVerticalGroup(
            txt10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txt10Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(12, 12, 12))
        );

        jTextField8.setEditable(false);
        jTextField8.setBackground(new java.awt.Color(204, 204, 204));
        jTextField8.addActionListener(this::jTextField8ActionPerformed);

        javax.swing.GroupLayout txtMaxSlotsLayout = new javax.swing.GroupLayout(txtMaxSlots);
        txtMaxSlots.setLayout(txtMaxSlotsLayout);
        txtMaxSlotsLayout.setHorizontalGroup(
            txtMaxSlotsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtMaxSlotsLayout.createSequentialGroup()
                .addComponent(txt10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField8)
                .addContainerGap())
        );
        txtMaxSlotsLayout.setVerticalGroup(
            txtMaxSlotsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtMaxSlotsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField8)
                .addContainerGap())
            .addComponent(txt10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(chkOpen)
                                .addGap(90, 90, 90)
                                .addComponent(chkLock))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(txtRequireECode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(chkYes)
                                .addGap(90, 90, 90)
                                .addComponent(chkNo)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEnterID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtInsertCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNameEventOrganizer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDateEvent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNameEvent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEventVenue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaxSlots, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(29, 29, 29))))
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txtNameEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(txtDateEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(txtNameEventOrganizer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(txtEventVenue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(txtMaxSlots, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRequireECode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkYes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(txtInsertCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(txtEnterID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(chkOpen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkLock, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbImage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        try { // <--- Added this 'try'
            String name = jTextField1.getText();
            String date = jTextField2.getText();
            String venue = jTextField7.getText();
            String eventCode = jTextField3.getText();
            String organizerName = jTextField6.getText();

            // Handle potential number format errors for Max Slots
            int max = Integer.parseInt(jTextField8.getText());
            String accessibility = chkYes.isSelected() ? "Private" : "Public";
            String status = chkOpen.isSelected() ? "Open" : "Closed";

            if (editingData != null) {
                // 2. Create the updated object keeping original IDs and slots
                ModelEvents updatedEvent = new ModelEvents(
                        editingData.getEventID(),
                        editingData.getOwnerID(),
                        name, date, venue,
                        editingData.getFilledSlots(),
                        max, status,
                        editingData.getJoinedTime(),
                        editingData.getLeftTime(),
                        organizerName, accessibility, eventCode
                );

                // 3. Find the index and swap the data in the Storage
                int index = ModelEventStorage.eventList.indexOf(editingData);
                if (index != -1) {
                    ModelEventStorage.eventList.set(index, updatedEvent);

                    // 4. Show the MODERN NOTIFICATION (Snackbar)
                    JFrame frame = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
                    Notification notif = new Notification(frame, "Event updated successfully!");
                    notif.showNotification();
                }
            }

            // 5. Go back to the list view immediately
            if (backEvent != null) {
                backEvent.actionPerformed(evt);
            }

        } catch (NumberFormatException e) { // <--- This now has a matching 'try'
            // Simple error handling if 'max slots' isn't a number
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter a valid number for slots.");
        }
    }//GEN-LAST:event_btnUploadActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void chkYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkYesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkYesActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void chkOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkOpenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkOpenActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.tribyte.swing.ButtonCustomDBoard btnBack;
    private com.tribyte.swing.ButtonCustomDBoard btnUpload;
    private javax.swing.JCheckBox chkLock;
    private javax.swing.JCheckBox chkNo;
    private javax.swing.JCheckBox chkOpen;
    private javax.swing.JCheckBox chkYes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel lbImage;
    private com.tribyte.swing.PanelRound panelRound1;
    private javax.swing.JLabel txt;
    private com.tribyte.swing.PanelRound txt1;
    private com.tribyte.swing.PanelRound txt10;
    private com.tribyte.swing.PanelRound txt2;
    private com.tribyte.swing.PanelRound txt3;
    private com.tribyte.swing.PanelRound txt7;
    private com.tribyte.swing.PanelRound txt8;
    private com.tribyte.swing.PanelRound txt9;
    private com.tribyte.swing.PanelRound txtDateEvent;
    private com.tribyte.swing.PanelRound txtEnterID;
    private com.tribyte.swing.PanelRound txtEventVenue;
    private com.tribyte.swing.PanelRound txtInsertCode;
    private com.tribyte.swing.PanelRound txtMaxSlots;
    private com.tribyte.swing.PanelRound txtNameEvent;
    private com.tribyte.swing.PanelRound txtNameEventOrganizer;
    private com.tribyte.swing.PanelRound txtRequireECode;
    private com.tribyte.swing.PanelRound txtStatus;
    // End of variables declaration//GEN-END:variables
}
