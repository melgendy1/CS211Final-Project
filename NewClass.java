import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import java.awt.Color;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import java.awt.Dimension;
import java.util.*;
import java.awt.print.*;
/**
 * Represents the class we will be creating
 * NOTE: This class is similar to the LoadedClass
 * So the comments may seem repeated.
 */

public class NewClass {
	/**
	 * The New class has many attributes.
	 * We initialize them being public and static
	 * Meaning they are accessible within other classes.
	 */
    public String[] columnNames;
    public String[][] data;
    int[] columnsWidth;
    public JTable table ;
    public String name;
    static int numStudents;
    static int numMeetings;
    static int partWeight;
    static int assWeight;
    static int quizWeight;
    static int examWeight;
    /**
     * The constructor for the new Class
     * @param name is the name of the student.
     * @param numStudents is the number of students.
     * @param numMeetings is the number of meetings.
     * @param partWeight is the participation weight.
     * @param assWeight is the assignment weight.
     * @param quizWeight is the quiz weight.
     * @param examWeight is the exam weight.
     */
    public NewClass(String name, int numStudents, int numMeetings, int partWeight, int assWeight, int quizWeight, int examWeight) {
        this.name=name;
        NewClass.numStudents=numStudents;
        NewClass.numMeetings=numMeetings;
        NewClass.partWeight = partWeight;
        NewClass.assWeight= assWeight;
        NewClass.quizWeight= quizWeight;
        NewClass.examWeight=examWeight;
        /**
         * This is where we create number of columns
         */
        columnNames = new String[numMeetings*3+10];

        int partNum=1;
        int quizNum=1;
        int hwNum=1;
        columnNames[0]="Name";

        for(int i=1;i<numMeetings+1;i++)
        {
            columnNames[i]= "Participation "+ partNum;
            partNum++;
        }
        for (int i=numMeetings+1;i<numMeetings*2+1;i++)
        {
            columnNames[i]="Assignment "+hwNum;
            hwNum++;
        }
        for(int i=numMeetings*2+1;i<columnNames.length-9;i++)
        {
            columnNames[i]="Quiz "+quizNum;
            quizNum++;
        }
        /**
         * The different column names with their respective
         * positions on the excel
         */    
        columnNames[columnNames.length-9]="Final Exam";
        columnNames[columnNames.length-8]="Participation Average";
        columnNames[columnNames.length-7]="Assignment Average";
        columnNames[columnNames.length-6]="Quiz Average";
        columnNames[columnNames.length-5]="Participation Total";
        columnNames[columnNames.length-4]="Assignment Total";
        columnNames[columnNames.length-3]="Quiz Total";
        columnNames[columnNames.length-2]="Overall";
        columnNames[columnNames.length-1]="Letter Grade";
        

        columnsWidth=new int[columnNames.length];
        columnsWidth[0]=200;
        for(int i =1;i<columnsWidth.length;i++)
            columnsWidth[i]=120;

        int scrollPaneWidth = 200+(100*columnsWidth.length-2);
        data = new String[numStudents][numMeetings*3+10];

        table = new JTable(new DefaultTableModel(data,columnNames));

        int i = 0;
        for (int width : columnsWidth) {
            TableColumn column = table.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        /**
         * The "Print" Button 
         * Along with all its functionalities.
         */
        JFrame frame = new JFrame();
        JButton print = new JButton();
        print.setText("Print");
        print.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
                    if(table.isEditing())
                        table.getCellEditor().stopCellEditing();
                    boolean checkpoint=false;
                    try{
                        for (int i = 0;i<NewClass.numStudents;i++)
                            for (int j=0;j<columnNames.length;j++)
                            {
                                if(  table.getValueAt(i,j)==null||table.getValueAt(i,j).equals(""))
                                    table.setValueAt("N/A",i,j);
                            }
                        for (int i=0;i<NewClass.numStudents;i++){
                            int quizTotal=0;
                            int assTotal=0;
                            int partTotal=0;
                            int quizAveNum=0;
                            int assAveNum=0;
                            int partAveNum=0;
                            for(int j=1;j<numMeetings+1;j++)
                            {
                                String value;
                                value = table.getValueAt(i,j).toString();

                                if (!value.equals("N/A")||value.equals(""))
                                {                            
                                    partTotal+=Integer.parseInt(value);
                                    table.setValueAt(value,i,j);
                                    partAveNum++;
                                }
                                else
                                    table.setValueAt(value,i,j);
                            }
                            for (int j=numMeetings+1;j<numMeetings*2+1;j++)
                            {
                                String value;

                                value = table.getValueAt(i,j).toString();
                                if (!value.equals("N/A")||value.equals(""))
                                {                            
                                    assTotal+=Integer.parseInt(value);
                                    table.setValueAt(value,i,j);
                                    assAveNum++;
                                }
                                else
                                    table.setValueAt(value,i,j);
                            }
                            for(int j=numMeetings*2+1;j<columnNames.length-9;j++)
                            {
                                String value;

                                value = table.getValueAt(i,j).toString();
                                if (!value.equals("N/A")||value.equals(""))
                                {                            
                                    quizTotal+=Integer.parseInt(value);
                                    table.setValueAt(value,i,j);
                                    quizAveNum++;
                                }
                                else
                                    table.setValueAt(value,i,j);
                            }

                            String exam =  table.getValueAt(i,columnNames.length-9).toString();
                            int finalExam=0;
                            if (exam.equals("N/A")||exam.equals(""))
                            {
                                table.setValueAt(exam,i,columnNames.length-9);}
                            else
                            {
                                finalExam= Integer.parseInt(exam);
                                table.setValueAt(exam,i,columnNames.length-9);
                            }

                            int partAve=0;
                            int assAve=0;
                            int quizAve=0;
                            if(partAveNum!=0)
                                partAve=Math.round(partTotal/partAveNum);
                            if(assAveNum!=0)
                                assAve=Math.round(assTotal/assAveNum);
                            if(quizAveNum!=0)
                                quizAve=Math.round(quizTotal/quizAveNum);

                            table.setValueAt(partAve,i,columnNames.length-8);
                            table.setValueAt(assAve,i,columnNames.length-7);
                            table.setValueAt(quizAve,i,columnNames.length-6);
                            /**
                             * The calculations for the average along the given weights
                             */
                            double partTot=partAve*((double)partWeight/100);
                            double assTot=assAve*((double)assWeight/100);
                            double quizTot=quizAve*((double)quizWeight/100);
                            double examTot=finalExam*((double)examWeight/100);
                            
                            
                            table.setValueAt(Math.round(partTot),i,columnNames.length-5);
                            table.setValueAt(Math.round(assTot),i,columnNames.length-4);
                            table.setValueAt(Math.round(quizTot),i,columnNames.length-3);
                            
                            int total=partWeight+assWeight+quizWeight+examWeight;
                            
                            table.setValueAt(Math.round(((partTot+assTot+quizTot+examTot)/total)*100),i,columnNames.length-2);
                            
                            for(int j=columnNames.length-8;j<columnNames.length-1;j++)
                            {
                                String value=  table.getValueAt(i,j).toString();
                                table.setValueAt(value,i,j);
                            }
                            /**
                             * The Calculations for the letter grades and their
                             * respective percentile ranges.
                             */
                            int value = Integer.parseInt(table.getValueAt(i, columnNames.length-2).toString());
                            if (value<=59) {
                            table.setValueAt("F", i,columnNames.length-1);
    

                        }
                            if (value >= 60 && value <= 62 ) {
                                table.setValueAt("D-", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 63 && value <= 66 ) {
                                table.setValueAt("D", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 67 && value <= 69 ) {
                                table.setValueAt("D+", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 70 && value <= 72 ) {
                                table.setValueAt("C-", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 73 && value <= 76 ) {
                                table.setValueAt("C", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 77 && value <= 79 ) {
                                table.setValueAt("C+", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 80 && value <= 82 ) {
                                table.setValueAt("B-", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 83 && value <= 86 ) {
                                table.setValueAt("B", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 87 && value <= 89 ) {
                                table.setValueAt("B+", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 90 && value <= 92 ) {
                                table.setValueAt("A-", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 93 && value <= 96 ) {
                                table.setValueAt("A", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 97  ) {
                                table.setValueAt("A+", i,columnNames.length-1);
        

                            }

                        }
                    }
                    catch (Exception t) {
                    	/**
                    	 * Exception for if the user enters a string or something else.
                    	 */
                        new Exceptions("Please enter whole numbers.");
                        checkpoint=true;
                    }

                    if(checkpoint==false)
                        try {

                            File parentDir = new File("./txtFiles");
                            parentDir.mkdir();
                            File file = new File(parentDir, name + ".txt");
                            if(!file.exists())
                                file.createNewFile();
                            /**
                             * This is to write for printing purposes.
                             */
                            PrintWriter writer = new PrintWriter(new FileWriter("./txtFiles/" + name + ".txt",false));
                            data = new String[NewClass.numStudents][columnNames.length];

                            for (int i = 0;i<NewClass.numStudents;i++)
                                for (int j=0;j<columnNames.length;j++)
                                    data[i][j] = (String) table.getValueAt(i, j);

                            writer.println(NewClass.numStudents);
                            writer.println(NewClass.numMeetings);
                            writer.println(NewClass.partWeight);
                            writer.println(NewClass.assWeight);
                            writer.println(NewClass.quizWeight);
                            writer.println(NewClass.examWeight);
                            
                            for(int i=0;i<data.length;i++) {
                                for (int j = 0; j < data[i].length; j++) {
                                    writer.println(data[i][j]);
                                }

                            }
                            writer.close();
                        }
                        catch(IOException t){JOptionPane.showMessageDialog(null, "This File does not exist.");
                            checkpoint=true;
                        }
                    if(checkpoint == false)
                        try {
                            PrintWriter printFile = new PrintWriter(new FileWriter("./printFiles/"+name+".txt",false));
                            printFile.println(name);
                            printFile.println();
                            printFile.println("Weights:");
                            printFile.println("Participation: "+NewClass.partWeight+"%       Assignments: "+NewClass.assWeight+"%        Quizzes: "+NewClass.quizWeight+"%     Exam: "+NewClass.examWeight+"%");
                            printFile.println();
                            for (int i=0;i<NewClass.numStudents;i++){
                                printFile.println("Student: "+ (String) table.getValueAt(i, 0));
                                printFile.println();
                                int num=1;
                                for (int j=1;j<=NewClass.numMeetings;j++){
                                    printFile.println("Participation "+num +": "+(String) table.getValueAt(i,j) +"      "+"Assignment "+num+": "+(String) table.getValueAt(i,j+NewClass.numMeetings)+"      "+"Quiz "+num+": "+(String) table.getValueAt(i,j+(NewClass.numMeetings*2)));
                                    num++;
                                }
                                printFile.println();
                                printFile.println("Participation average: "+(String) table.getValueAt(i, columnNames.length-8) +"     "+ "Assignment average: "+(String)table.getValueAt(i, columnNames.length-7) + "       "+"Quiz average: "+(String) table.getValueAt(i, columnNames.length-6));
                                printFile.println();
                                printFile.println("Participation total: "+(String) table.getValueAt(i, columnNames.length-5) +"     "+ "Assignment total: "+(String)table.getValueAt(i, columnNames.length-4) + "       "+"Quiz total: "+(String) table.getValueAt(i, columnNames.length-3));
                                printFile.println();
                                printFile.println("Final Exam: " + (String) table.getValueAt(i, columnNames.length-9));
                                printFile.println("Overall: " + (String) table.getValueAt(i, columnNames.length-2));
                                printFile.println("Letter Grade: "+(String)table.getValueAt(i, columnNames.length-1));
                                printFile.println();
                                printFile.println();

                            }

                            printFile.close();
                            ArrayList<String> print= new ArrayList<>();;
                            try {
                                File file = new File("./printFiles/"+name+".txt");
                                Scanner read = new Scanner(new FileReader(file));
                                while(read.hasNextLine()){
                                    print.add(read.nextLine());
                                }
                                read.close();
                            }

                            catch (Exception t){JOptionPane.showMessageDialog(null,"Error happened");}
                            MyPrintableTable mpt = new MyPrintableTable(print);
                            PrinterJob job = PrinterJob.getPrinterJob();
                            job.setPrintable(mpt);
                            job.printDialog();
                            try {
                                job.print();
                            } catch (PrinterException t) {
                                new Exceptions("Printing failed");
                            }

                        }
                        catch(Exception t) { new Exceptions("This File does not exist.");}

                }});

        frame.setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());

        frame.setAlwaysOnTop(false);

        frame.setVisible(true);
        frame.setResizable(true);

        DefaultTableModel model = (DefaultTableModel)table.getModel();

        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel topBtnPnl = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        /**
         * The "Add Row" Button
         * Along with all its functionalities.
         */
        JButton addRow= new JButton();
        addRow.setText("Add Row");
        addRow.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
                    NewClass.numStudents++;
                    model.addRow(new Object[]{"", "","",""});
                    table.setRowHeight(16);
                    Dimension p = table.getPreferredSize();
                    int v = frame.getHeight()-100;
                    if (v > p.height)
                    {
                        int available = v -
                            table.getRowCount() * table.getRowMargin();
                        int perRow = available / table.getRowCount();
                        table.setRowHeight(perRow);
                    }
                }});
        bottombtnPnl.add(addRow);
        /**
         * The "Remove Row" Button 
         * Along with all its functionalities.
         */
        JButton removeRow=new JButton();

        removeRow.setText("Remove Row");
        removeRow.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){

                    try{
                        if(NewClass.numStudents==1)
                            throw new Exceptions("Can't have less than one student in a class");
                        else
                            NewClass.numStudents--;
                        model.removeRow(model.getRowCount()-1);
                        table.setRowHeight(16);
                        Dimension p = table.getPreferredSize();
                        int v = frame.getHeight()-100;
                        if (v > p.height)
                        {
                            int available = v -
                                table.getRowCount() * table.getRowMargin();
                            int perRow = available / table.getRowCount();
                            table.setRowHeight(perRow);
                        }
                    }
                    catch(Exception t){}

                }});
        bottombtnPnl.add(removeRow);
        /**
         * The "Back" Button 
         * Along with all its functionalities.
         */
        JButton back=new JButton();
        bottombtnPnl.add(back);
        back.setText("Back");
        back.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
                    new SetNewClass();
                    frame.dispose();
                }});
        bottombtnPnl.add(back);
        /**
         * The "Save" Button 
         * Along with all its functionalities.
         */
        JButton save=new JButton();
        bottombtnPnl.add(save);
        save.setText("Save");
        save.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
                    if(table.isEditing())
                        table.getCellEditor().stopCellEditing();
                    boolean checkpoint=false;
                    try{
                        for (int i = 0;i<NewClass.numStudents;i++)
                            for (int j=0;j<columnNames.length;j++)
                            {
                                if(  table.getValueAt(i,j)==null||table.getValueAt(i,j).equals(""))
                                    table.setValueAt("N/A",i,j);
                            }
                        for (int i=0;i<NewClass.numStudents;i++){
                            int quizTotal=0;
                            int assTotal=0;
                            int partTotal=0;
                            int quizAveNum=0;
                            int assAveNum=0;
                            int partAveNum=0;
                            for(int j=1;j<numMeetings+1;j++)
                            {
                                String value;
                                value = table.getValueAt(i,j).toString();

                                if (!value.equals("N/A")||value.equals(""))
                                {                            
                                    partTotal+=Integer.parseInt(value);
                                    table.setValueAt(value,i,j);
                                    partAveNum++;
                                }
                                else
                                    table.setValueAt(value,i,j);
                            }
                            for (int j=numMeetings+1;j<numMeetings*2+1;j++)
                            {
                                String value;

                                value = table.getValueAt(i,j).toString();
                                if (!value.equals("N/A")||value.equals(""))
                                {                            
                                    assTotal+=Integer.parseInt(value);
                                    table.setValueAt(value,i,j);
                                    assAveNum++;
                                }
                                else
                                    table.setValueAt(value,i,j);
                            }
                            for(int j=numMeetings*2+1;j<columnNames.length-9;j++)
                            {
                                String value;

                                value = table.getValueAt(i,j).toString();
                                if (!value.equals("N/A")||value.equals(""))
                                {                            
                                    quizTotal+=Integer.parseInt(value);
                                    table.setValueAt(value,i,j);
                                    quizAveNum++;
                                }
                                else
                                    table.setValueAt(value,i,j);
                            }

                            String exam =  table.getValueAt(i,columnNames.length-9).toString();
                            int finalExam=0;
                            if (exam.equals("N/A")||exam.equals(""))
                            {
                                table.setValueAt(exam,i,columnNames.length-9);}
                            else
                            {
                                finalExam= Integer.parseInt(exam);
                                table.setValueAt(exam,i,columnNames.length-9);
                            }

                            int partAve=0;
                            int assAve=0;
                            int quizAve=0;
                            if(partAveNum!=0)
                                partAve=Math.round(partTotal/partAveNum);
                            if(assAveNum!=0)
                                assAve=Math.round(assTotal/assAveNum);
                            if(quizAveNum!=0)
                                quizAve=Math.round(quizTotal/quizAveNum);

                            table.setValueAt(partAve,i,columnNames.length-8);
                            table.setValueAt(assAve,i,columnNames.length-7);
                            table.setValueAt(quizAve,i,columnNames.length-6);
                            
                            double partTot=partAve*((double)partWeight/100);
                            double assTot=assAve*((double)assWeight/100);
                            double quizTot=quizAve*((double)quizWeight/100);
                            double examTot=finalExam*((double)examWeight/100);
                            
                            
                            table.setValueAt(Math.round(partTot),i,columnNames.length-5);
                            table.setValueAt(Math.round(assTot),i,columnNames.length-4);
                            table.setValueAt(Math.round(quizTot),i,columnNames.length-3);
                            
                            int total=partWeight+assWeight+quizWeight+examWeight;
                            
                            table.setValueAt(Math.round(((partTot+assTot+quizTot+examTot)/total)*100),i,columnNames.length-2);
                            for(int j=columnNames.length-8;j<columnNames.length-1;j++)
                            {
                                String value=  table.getValueAt(i,j).toString();
                                table.setValueAt(value,i,j);
                            }
                            
                            int value = Integer.parseInt(table.getValueAt(i, columnNames.length-2).toString());
                            if (value<=59) {
                            table.setValueAt("F", i,columnNames.length-1);


                        }
                            if (value >= 60 && value <= 62 ) {
                                table.setValueAt("D-", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 63 && value <= 66 ) {
                                table.setValueAt("D", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 67 && value <= 69 ) {
                                table.setValueAt("D+", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 70 && value <= 72 ) {
                                table.setValueAt("C-", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 73 && value <= 76 ) {
                                table.setValueAt("C", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 77 && value <= 79 ) {
                                table.setValueAt("C+", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 80 && value <= 82 ) {
                                table.setValueAt("B-", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 83 && value <= 86 ) {
                                table.setValueAt("B", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 87 && value <= 89 ) {
                                table.setValueAt("B+", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 90 && value <= 92 ) {
                                table.setValueAt("A-", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 93 && value <= 96 ) {
                                table.setValueAt("A", i,columnNames.length-1);
        

                            }
                            
                            if (value >= 97 ) {
                                table.setValueAt("A+", i,columnNames.length-1);
        

                            }

                        }
                    }
                    catch (Exception t) {
                    	/**
                    	 * Exception for if the user enters a string or something else.
                    	 */
                        new Exceptions("Please enter whole numbers.");
                        checkpoint=true;
                    }
                    if(checkpoint==false)
                        try {

                            File parentDir = new File("./txtFiles");
                            parentDir.mkdir();
                            File file = new File(parentDir, name + ".txt");
                            if(!file.exists())
                                file.createNewFile();

                            PrintWriter writer = new PrintWriter(new FileWriter("./txtFiles/" + name + ".txt",false));
                            data = new String[NewClass.numStudents][columnNames.length];

                            for (int i = 0;i<NewClass.numStudents;i++)
                                for (int j=0;j<columnNames.length;j++)
                                {
                                    data[i][j] =  table.getValueAt(i,j).toString();
                                }
                            writer.println(NewClass.numStudents);
                            writer.println(NewClass.numMeetings);
                            writer.println(NewClass.partWeight);
                            writer.println(NewClass.assWeight);
                            writer.println(NewClass.quizWeight);
                            writer.println(NewClass.examWeight);

                            for(int i=0;i<data.length;i++) {
                                for (int j = 0; j < data[i].length; j++) {
                                    writer.println(data[i][j]);
                                }

                            }
                            writer.close();
                        }
                        catch(IOException t){JOptionPane.showMessageDialog(null, "This File does not exist.");}

                }});
        bottombtnPnl.add(save);
        bottombtnPnl.add(print);

        btnPnl.add(topBtnPnl, BorderLayout.NORTH);
        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);

        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(false);

        table.setRowHeight(16);
        Dimension p = table.getPreferredSize();
        Dimension v =Toolkit.getDefaultToolkit().getScreenSize();
        if (v.height > p.height)
        {
            int available = (v.height-100) -
                table.getRowCount() * table.getRowMargin();
            int perRow = available / table.getRowCount();
            table.setRowHeight(perRow);
        }

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setShowGrid(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(String.class, centerRenderer);

        table.setGridColor(Color.RED);
        table.setBackground(Color.lightGray);
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        JScrollBar bar = scrollPane.getHorizontalScrollBar();
        bar.setPreferredSize(new Dimension(scrollPaneWidth,20));
        frame.add(table.getTableHeader(), BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(btnPnl, BorderLayout.SOUTH);

        frame.setTitle(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public boolean isCellEditable(int row, int col)
    { return false; }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
        	/**
        	 * The Default for the New Class
        	 */
                public void run() {
                    new NewClass("Default name",0,0,0,0,0,0);
                }
            });
    }
}
