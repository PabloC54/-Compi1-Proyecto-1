/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Graphing.Reporter;
import static Main.Main.main_path;
import Structs.AFD;
import Structs.Tree;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDeepOceanContrastIJTheme;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import util.CreateChildNodes;
import util.FileNode;

/**
 *
 * @author pablo
 */
public class MainGUI extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    public static Map<String, ArrayList<ArrayList<String>>> Data = new HashMap<>();
    public Map<String, AFD> AFDs = new HashMap<>();

    private Analyzers.Parser parser;
    private Analyzers.Scanner scanner;

    private final String data_path = main_path + "/data";

    private Reporter Reporter = new Reporter(data_path);

    private String file_name = "";
    private Boolean saved = false;
    private File file = new File("");

    final UndoManager undo = new UndoManager();

    public MainGUI() {
        FlatMaterialDeepOceanContrastIJTheme.install();
        initComponents();
        setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/icon/appicon.png")).getImage());

        unsaved_label.setVisible(false);

        Document document = input_text.getDocument();
        document.addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent evt) {
                undo.addEdit(evt.getEdit());
            }
        });

        input_text.getActionMap().put("Undo", new AbstractAction("Undo") {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (undo.canUndo()) {
                        undo.undo();
                    }
                } catch (CannotUndoException e) {
                    Output("No se puede deshacer");
                }
            }
        });
        input_text.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");

        input_text.getActionMap().put("Redo", new AbstractAction("Redo") {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (undo.canRedo()) {
                        undo.redo();
                    }
                } catch (CannotRedoException e) {
                    Output("No se puede rehacer");
                }
            }
        });
        input_text.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
        input_text.getInputMap().put(KeyStroke.getKeyStroke("control shift Z"), "Redo");

        updateTree();
    }

    private void initDirectories() {
        file = new File(data_path);
        file.mkdir();
        file = new File(data_path + "/AFD");
        file.mkdir();
        file = new File(data_path + "/AFND");
        file.mkdir();
        file = new File(data_path + "/Arboles");
        file.mkdir();
        file = new File(data_path + "/Errores");
        file.mkdir();
        file = new File(data_path + "/Salidas");
        file.mkdir();
        file = new File(data_path + "/Siguientes");
        file.mkdir();
        file = new File(data_path + "/Transiciones");
        file.mkdir();
    }

    private void updateTree() {
        initDirectories();

        File fileRoot = new File(data_path);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new FileNode(fileRoot));

        new CreateChildNodes(fileRoot, root);

        DefaultTreeModel model = new DefaultTreeModel(root);
        data_tree.setModel(model);
//        expandNodes(0, data_tree.getRowCount());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        input_text = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        file_name_label = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        output_text = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        analize_button = new javax.swing.JToggleButton();
        generate_button = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        data_tree = new javax.swing.JTree();
        unsaved_label = new javax.swing.JLabel();
        clear_button = new javax.swing.JToggleButton();
        position_label = new javax.swing.JLabel();
        update_button = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        new_dialog = new javax.swing.JMenuItem();
        open_dialog = new javax.swing.JMenuItem();
        save_dialog = new javax.swing.JMenuItem();
        saveas_dialog = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        input_text.setColumns(20);
        input_text.setFont(new java.awt.Font("Fira Code", 0, 12)); // NOI18N
        input_text.setRows(5);
        input_text.setTabSize(4);
        input_text.setText("No hay archivo de entrada\nCarga un archivo OLC o crea uno nuevo en la pestaña 'Archivo'");
        input_text.setEnabled(false);
        input_text.setMinimumSize(new java.awt.Dimension(200, 36));
        input_text.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                input_textMouseClicked(evt);
            }
        });
        input_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                input_textKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                input_textKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(input_text);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel1.setText("Entrada:");

        file_name_label.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        file_name_label.setText("ninguno");

        output_text.setEditable(false);
        output_text.setBackground(new java.awt.Color(9, 11, 16));
        output_text.setFont(new java.awt.Font("Fira Code Medium", 0, 12)); // NOI18N
        output_text.setForeground(new java.awt.Color(204, 204, 204));
        output_text.setName("output_text"); // NOI18N
        jScrollPane4.setViewportView(output_text);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel2.setText("Salida:");

        analize_button.setText("Analizar Entrada");
        analize_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        analize_button.setEnabled(false);
        analize_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analize_buttonActionPerformed(evt);
            }
        });

        generate_button.setText("Generar Autómata");
        generate_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        generate_button.setEnabled(false);
        generate_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generate_buttonActionPerformed(evt);
            }
        });

        data_tree.setToolTipText("Selecciona un archivo para abrirlo");
        data_tree.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        data_tree.setSelectionRow(1);
        data_tree.setToggleClickCount(1);
        data_tree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                data_treeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(data_tree);

        unsaved_label.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        unsaved_label.setText("*");

        clear_button.setText("Limpiar");
        clear_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clear_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_buttonActionPerformed(evt);
            }
        });

        position_label.setBackground(new java.awt.Color(9, 11, 16));
        position_label.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 12)); // NOI18N
        position_label.setText(" Lín. 0, Col. 0 ");
        position_label.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(66, 122, 127), 2, true));
        position_label.setOpaque(true);

        update_button.setText("Actualizar");
        update_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        update_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(file_name_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unsaved_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(position_label)
                        .addGap(269, 269, 269))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(190, 190, 190)
                                .addComponent(generate_button, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101)
                                .addComponent(analize_button, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(clear_button, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(update_button, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(32, 32, 32))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(file_name_label)
                    .addComponent(unsaved_label, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(position_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generate_button)
                    .addComponent(analize_button)
                    .addComponent(clear_button)
                    .addComponent(update_button))
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jMenu1.setText("Archivo");

        new_dialog.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        new_dialog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new-file-icon.png"))); // NOI18N
        new_dialog.setText("Nuevo");
        new_dialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_dialogActionPerformed(evt);
            }
        });
        jMenu1.add(new_dialog);

        open_dialog.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        open_dialog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/open-file-icon.png"))); // NOI18N
        open_dialog.setText("Abrir");
        open_dialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                open_dialogActionPerformed(evt);
            }
        });
        jMenu1.add(open_dialog);

        save_dialog.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        save_dialog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save-file-icon.png"))); // NOI18N
        save_dialog.setText("Guardar");
        save_dialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_dialogActionPerformed(evt);
            }
        });
        jMenu1.add(save_dialog);

        saveas_dialog.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveas_dialog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save-as-icon.png"))); // NOI18N
        saveas_dialog.setText("Guardar como...");
        saveas_dialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveas_dialogActionPerformed(evt);
            }
        });
        jMenu1.add(saveas_dialog);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generate_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generate_buttonActionPerformed
        if (file_name.isEmpty()) {
            if (saveFileAs() == true) {
                Output_success("Se guardó exitosamente");
            } else {
                return;
            }
        }

        initDirectories();
        Data.clear();
        AFDs.clear();
//        try {
        scanner = new Analyzers.Scanner(new StringReader(input_text.getText()));
        parser = new Analyzers.Parser(scanner);
        try {
            parser.parse();
        } catch (Exception ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<ArrayList<String>> symbols = scanner.getSymbols();
        ArrayList<ArrayList<String>> lexical_errors = scanner.getErrors();

        ArrayList<ArrayList<String>> sets = parser.getSets();
        ArrayList<ArrayList<String>> expressions = parser.getExpressions();
        ArrayList<ArrayList<String>> comparations = parser.getComparations();
        ArrayList<ArrayList<String>> syntactic_errors = parser.getErrors();

        if (!lexical_errors.isEmpty() || !syntactic_errors.isEmpty()) {
            if (Reporter.reportErrors(lexical_errors, syntactic_errors, file_name)) {
                Output("Se encontraron errores en la entrada. Se generó un reporte de errores en data/Errores/" + file_name + ".png");
            } else {
                Output_error("Error al generar el reporte de errores");
            }
            return;
        }

        for (ArrayList<String> expression : expressions) {
            Tree tree = new Tree(expression);
            Output_highlight(tree.id);

            try {
                if (Reporter.graphAFND(tree, file_name)) {
                    Output("Se generó el AFND en data/AFND/" + file_name + "/" + tree.id + ".png");
                } else {
                    Output_error("Error al generar el AFND");
                }
                if (Reporter.graphTree(tree, file_name)) {
                    Output("Se generó el árbol en data/Arboles/" + file_name + "/" + tree.id + ".png");
                } else {
                    Output_error("Error al generar el árbol");
                }
                if (Reporter.graphFollowTable(tree, file_name)) {
                    Output("Se generó la tabla de siguientes en data/Siguientes/" + file_name + "/" + tree.id + ".png");
                } else {
                    Output_error("Error al generar la tabla de siguientes");
                }
                if (Reporter.graphTransitionTable(tree, file_name)) {
                    Output("Se generó la tabla de transiciones en data/Transiciones/" + file_name + "/" + tree.id + ".png");
                } else {
                    Output_error("Error al generar la tabla de transiciones");
                }

                AFD afd = Reporter.graphAFD(tree, file_name);

                if (afd != null) {
                    Output("Se generó el AFD en data/AFD/" + file_name + "/" + tree.id + ".png");
                } else {
                    Output_error("Error al generar el AFD");
                }

                AFDs.put(tree.id, afd);

            } catch (IOException ex) {
                Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Data.put("symbols", symbols);
        Data.put("sets", sets);
        Data.put("comparations", comparations);

//        } catch (Exception e) {
//            Output_error("Error fatal");
//            System.out.println(e.getCause());
//        }
//        updateTree();
    }//GEN-LAST:event_generate_buttonActionPerformed

    private void analize_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analize_buttonActionPerformed
        try {
            if (Data.isEmpty()) {

                int op = JOptionPane.showConfirmDialog(this, "No hay autómatas generados. ¿Deseas generarlos?", "No hay información", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (op == JOptionPane.NO_OPTION) {
                    return;
                }
                generate_button.doClick();
            }

            initDirectories();

            String name = data_path + "/Salidas/" + file_name;
            file = new File(name + ".json");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);

            String s = "[\n";

            for (ArrayList comparation : Data.get("comparations")) {

                s += "\t{\n";
                String lexema = (String) comparation.get(1);
                s += "\t\t\"Valor\":" + lexema + ",\n";

                String id = (String) comparation.get(0);
                s += "\t\t\"ExpresionRegular\":\"" + id + "\",\n";

                AFD afd = null;

                for (Map.Entry<String, AFD> entry : AFDs.entrySet()) {
                    if (entry.getKey().equals(id)) {
                        afd = entry.getValue();
                    }
                }

                if (afd == null) {
                    Output_error("No hay AFD para '" + lexema + "'");
                    continue;
                }

                if (afd.Evaluate(lexema.substring(1, lexema.length() - 1))) {
                    s += "\t\t\"Resultado\":\"Cadena Válida\"\n";
                } else {
                    s += "\t\t\"Resultado\":\"Cadena No Válida\"\n";
                    Output_error(afd.getMessage());
                }
                s += "\t}";

                if (!(Data.get("comparations").get(Data.get("comparations").size() - 1) == comparation)) {
                    s += ",";
                }
                s += "\n";

            }

            s += "]";

            writer.write(s);
            writer.close();

            Output("Se generó la salida en data/Salidas/" + file_name + ".png");

        } catch (IOException e) {
            Output_error("Error fatal");
            System.out.println(e.getCause());
        }

    }//GEN-LAST:event_analize_buttonActionPerformed

    private void clear_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_buttonActionPerformed
        output_text.setText("");
    }//GEN-LAST:event_clear_buttonActionPerformed

    private void data_treeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_data_treeMouseClicked
        TreePath sel = data_tree.getPathForLocation(evt.getX(), evt.getY());
        if (sel != null) {
            String path = "";

            for (Object o : sel.getPath()) {
                path += "/" + o;
            }

            File file_temp = new File(main_path + path);

            if (evt.getButton() == MouseEvent.BUTTON1) { // abrir archivo
                if (file_temp.getName().toLowerCase().endsWith(".png") || file_temp.getName().toLowerCase().endsWith(".jpg")) {
                    ImageGUI img_frame = new ImageGUI();
                    img_frame.setImage(file_temp);
                    img_frame.setVisible(true);

                } else if (file_temp.getName().toLowerCase().endsWith(".html")) {
                    try {
                        Desktop.getDesktop().browse(file_temp.toURI());
                    } catch (IOException ex) {
                        Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (file_temp.getName().toLowerCase().endsWith(".json")) {
                    FileInputStream inputStream;
                    try {
                        Output_highlight(file_temp.getName());
                        inputStream = new FileInputStream(file_temp);
                        Output(readFromInputStream(inputStream));
                        inputStream.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (evt.getButton() == MouseEvent.BUTTON3) { // eliminar archivo
                try {
                    int op;

                    if (file_temp.isDirectory()) {
                        op = JOptionPane.showConfirmDialog(this, "¿Quieres eliminar '" + file_temp.getName() + "'?", "Eliminar carpeta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                        if (op == JOptionPane.YES_OPTION) {
                            deleteFolder(file_temp);
                        }
                    } else {
                        op = JOptionPane.showConfirmDialog(this, "¿Quieres eliminar '" + file_temp.getName() + "'?", "Eliminar archivo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                        if (op == JOptionPane.YES_OPTION) {
                            Files.delete(file_temp.toPath());
                        }
                    }

                    updateTree();

                } catch (IOException ex) {
                    Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_data_treeMouseClicked

    private void input_textMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_input_textMouseClicked
        if (!input_text.isEnabled()) {
            return;
        }

        int line = 0, column = 0;
        int caretpos = input_text.getCaretPosition();
        try {
            line = input_text.getLineOfOffset(caretpos);
            column = caretpos - input_text.getLineStartOffset(line) + 1;
        } catch (BadLocationException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        line += 1;

        position_label.setText(" Lín. " + line + ", Col. " + column + " ");
    }//GEN-LAST:event_input_textMouseClicked

    private void update_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_buttonActionPerformed
        updateTree();
    }//GEN-LAST:event_update_buttonActionPerformed

    private void input_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_input_textKeyReleased
        if (!input_text.isEnabled()) {
            return;
        }

        int line = 0, column = 0;
        int caretpos = input_text.getCaretPosition();
        try {
            line = input_text.getLineOfOffset(caretpos);
            column = caretpos - input_text.getLineStartOffset(line) + 1;
        } catch (BadLocationException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        line += 1;

        position_label.setText(" Lín. " + line + ", Col. " + column + " ");
    }//GEN-LAST:event_input_textKeyReleased

    private static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }
    // ====================================================>>ACTIONS<<============================================================

    private void new_dialogActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_new_dialogActionPerformed

        if (saved == false && input_text.isEnabled()) {
            int op = JOptionPane.showConfirmDialog(this, "¿Quieres guardar el archivo actual?", "Cambios no guardados", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (op == JOptionPane.YES_OPTION) {
                saveFileAs();
            } else if (op == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }

        Data.clear();
        AFDs.clear();

        input_text.setText(
            "{\n\n\n"
            + "%%\n"
            + "%%"
            + "\n\n\n}");

        if (!input_text.isEnabled()) {
            input_text.setEnabled(true);
            analize_button.setEnabled(true);
            generate_button.setEnabled(true);
        }

        Font font = file_name_label.getFont();
        font = font.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR));
        file_name_label.setFont(font);

        unsaved_label.setVisible(false);
        saved = true;

        file_name = "";
        file_name_label.setText("unnamed.olc");
        position_label.setText(" (0, 0) ");
    }// GEN-LAST:event_new_dialogActionPerformed

    private void open_dialogActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_open_dialogActionPerformed

        if (saved == false && input_text.isEnabled()) {
            int op = JOptionPane.showConfirmDialog(this, "¿Quieres guardar el archivo actual?", "Cambios no guardados", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (op == JOptionPane.YES_OPTION) {
                saveFileAs();
            } else if (op == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }

        FileInputStream inputStream = null;

        try {
            file = openFile(this);

            if (file != null) {

                Data.clear();
                AFDs.clear();

                inputStream = new FileInputStream(file);
                input_text.setText(readFromInputStream(inputStream));

                if (!input_text.isEnabled()) {
                    input_text.setEnabled(true);
                    analize_button.setEnabled(true);
                    generate_button.setEnabled(true);
                }

                Font font = file_name_label.getFont();
                font = font.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR));
                file_name_label.setFont(font);
                setFileName(file.getName());

                unsaved_label.setVisible(false);
                saved = true;

                Output_success("Se cargó el archivo " + file.toString());
            }
        } catch (FileNotFoundException ex) {
            Output_error("No se encontró el archivo");
        } catch (IOException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }// GEN-LAST:event_open_dialogActionPerformed

    private void save_dialogActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_save_dialogActionPerformed
        if (!input_text.isEnabled()) {
            Output_error("No hay archivo para guardar");
            return;
        }

        try {
            if (file_name.isEmpty()) {
                if (saveFileAs() == true) {
                    Output_success("Se guardó exitosamente");
                    return;
                }

            } else {
                if (saveFile() == true) {

                    Font font = file_name_label.getFont();
                    font = font.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR));
                    file_name_label.setFont(font);
                    unsaved_label.setVisible(false);
                    Output_success("Se guardó exitosamente");
                    return;
                }
            }
        } catch (Exception ex) {
            Output_error("No se pudo guardar el archivo");
            Logger.getLogger(MainGUI.class
                .getName()).log(Level.SEVERE, null, ex);
        }

    }// GEN-LAST:event_save_dialogActionPerformed

    private void saveas_dialogActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveas_dialogActionPerformed      
        if (!input_text.isEnabled()) {
            Output_error("No hay archivo para guardar");
            return;
        }

        if (saveFileAs() == true) {
            Output_success("Se guardó exitosamente");
            return;
        }
    }// GEN-LAST:event_saveas_dialogActionPerformed

    private void input_textKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_input_textKeyTyped
        if (saved == true) {
            saved = false;
            Font font = file_name_label.getFont();
            font = font.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_ULTRABOLD));
            file_name_label.setFont(font);
            unsaved_label.setVisible(true);
        }
    }// GEN-LAST:event_input_textKeyTyped

    // ================================>>MISC<<=========================================
    public void Output(String str) {
        StyledDocument document = (StyledDocument) output_text.getDocument();
        try {
            document.insertString(document.getLength(), str + "\n", null);
        } catch (BadLocationException ex) {
            System.out.println(str);
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Output_highlight(String str) {
        StyledDocument document = (StyledDocument) output_text.getDocument();

        Style style = output_text.addStyle("highlight", null);
        StyleConstants.setForeground(style, Color.blue);
        StyleConstants.setItalic(style, true);
        StyleConstants.setFontSize(style, 14);

        str = ">>" + str;

        try {
            document.insertString(document.getLength(), str + "\n", style);
        } catch (BadLocationException ex) {
            System.out.println(str);
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Output_title(String str) {
        StyledDocument document = (StyledDocument) output_text.getDocument();

        Style style = output_text.addStyle("success", null);
        StyleConstants.setForeground(style, Color.cyan);
        StyleConstants.setFontSize(style, 15);

        str = " " + str + " ";

        int width = (108 - str.length()) / 2;

        for (int i = 0; i < width; i++) {
            str = "=" + str;
        }

        for (int i = 0; i < width; i++) {
            str = str + "=";
        }

        try {
            document.insertString(document.getLength(), str + "\n", style);
        } catch (BadLocationException ex) {
            System.out.println(str);
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Output_success(String str) {
        StyledDocument document = (StyledDocument) output_text.getDocument();

        Style style = output_text.addStyle("success", null);
        StyleConstants.setForeground(style, Color.green);

        try {
            document.insertString(document.getLength(), str + "\n", style);
        } catch (BadLocationException ex) {
            System.out.println(str);
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Output_error(String str) {
        StyledDocument document = (StyledDocument) output_text.getDocument();

        Style style = output_text.addStyle("error", null);
        StyleConstants.setForeground(style, Color.red);

        try {
            document.insertString(document.getLength(), str + "\n", style);
        } catch (BadLocationException ex) {
            System.out.println(str);
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setFileName(String name) {
        file_name_label.setText(name);
        if (name.toLowerCase().endsWith(".olc")) {
            file_name = name.substring(0, name.length() - 4);
        }
    }

    public JFileChooser fileChooser;

    private File openFile(final JFrame frame) {

        fileChooser = new JFileChooser(main_path);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter ff = new FileNameExtensionFilter("OLC Files", "olc");
        fileChooser.addChoosableFileFilter(ff);
        fileChooser.setFileFilter(ff);

        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }

        return file;
    }

    private Boolean saveFile() {

        if (saved == false) {

            BufferedWriter writer;

            try {
                writer = new BufferedWriter(new FileWriter(file, false));
                writer.flush();
                writer.write(input_text.getText());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Font font = file_name_label.getFont();
            font = font.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR));
            file_name_label.setFont(font);
            unsaved_label.setVisible(false);
            saved = true;
        }

        return saved;
    }

    private Boolean saveFileAs() {
        fileChooser = new JFileChooser(main_path);

        BufferedWriter writer;
        int out = fileChooser.showSaveDialog(null);

        if (out == JFileChooser.APPROVE_OPTION) {
            try {
                file = fileChooser.getSelectedFile();

                if (!file.getName().toLowerCase().endsWith(".olc")) {
                    file = new File(file.getAbsolutePath() + ".olc");
                }

                writer = new BufferedWriter(new FileWriter(file, false));
                writer.write(input_text.getText());
                writer.close();

            } catch (IOException e) {
                return false;
            }

            Font font = file_name_label.getFont();
            font = font.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR));
            file_name_label.setFont(font);
            unsaved_label.setVisible(false);

            setFileName(file.getName());
            saved = true;
        }

        return saved;
    }

    // LEER ARCHIVO
    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    // =================================>>MAIN<<===========================================
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class
                .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class
                .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class
                .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class
                .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton analize_button;
    private javax.swing.JToggleButton clear_button;
    private javax.swing.JTree data_tree;
    private javax.swing.JLabel file_name_label;
    private javax.swing.JToggleButton generate_button;
    private javax.swing.JTextArea input_text;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JMenuItem new_dialog;
    private javax.swing.JMenuItem open_dialog;
    private javax.swing.JTextPane output_text;
    private javax.swing.JLabel position_label;
    private javax.swing.JMenuItem save_dialog;
    private javax.swing.JMenuItem saveas_dialog;
    private javax.swing.JLabel unsaved_label;
    private javax.swing.JToggleButton update_button;
    // End of variables declaration//GEN-END:variables
}
