package pkg220project3kdm;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Class that creates the GUI for this program and grabs text field input to do computations 
 * @author KDM
 */
public class CalcGUI extends JFrame implements ActionListener {

    //private instances variables    
    private JFrame frame;
    private JPanel jp;
    private JPanel jpLeft;

    private JLabel loan;
    private JLabel yrs;
    private JLabel rateOfReturn;
    private JLabel everyMonth;
    private JLabel duration1;
    private JLabel direct1;
    private JLabel direct2;
    private JLabel interest1;
    private JLabel values;
    //JButtons
    private JButton calc;
    private JButton go;

    //JTextFields
    private JTextField loanAmount;
    private JTextField interest2;
    private JTextField years;
    private JTextField rateReturn;
    private JTextField duration2;
    //TextAreas
    private JTextArea monthlyPay;
    private JTextArea button1;
    private JTextArea button2;
    private JTextArea totalValues;
    //JTable
    private JTable info;
    //data for table
    private final Object[] columnNames = {"Month(s):", "Principal:", "Interest Pocketed:", "Monthly Pay:", "Balance:"};
    //scroll pane
    private JScrollPane scrollPane;

    /**
     * Empty constructor with no parameters
     */
    public CalcGUI() {
    }

    /**
     * Method that constructs the GUI for this program and initializes JButton ActionListener event
     */
    public void display() {
        //Jframe
        frame = new JFrame("Financial Calculator");
        frame.setSize(325, 415);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Jpanels
        jp = new JPanel();
        jp.setBackground(Color.GRAY);
        jp.setLayout(new GridLayout(1, 2));
        jpLeft = new JPanel();
        jpLeft.setBackground(Color.GRAY);
        jpLeft.setLayout(new GridLayout(10, 2));

        //JTable
        info = new JTable(new DefaultTableModel(columnNames, 0));
        //scroll pane
        //adding table to scrollpane 
        scrollPane = new JScrollPane(info);
        jp.add(scrollPane);

        //add both to frame
        jp.add(jpLeft);
        frame.add(jp);
        //components for frame
        //label
        loan = new JLabel();
        loan.setText("Loan Amount:");
        jpLeft.add(loan);
        //field
        loanAmount = new JTextField(10);
        jpLeft.add(loanAmount);
        //label
        yrs = new JLabel();
        yrs.setText("Years(s)/Month(s)(in decimal form):");
        jpLeft.add(yrs);
        //field
        years = new JTextField(10);
        jpLeft.add(years);
        //label
        interest1 = new JLabel();
        interest1.setText("Interest:");
        jpLeft.add(interest1);
        //field
        interest2 = new JTextField(10);
        jpLeft.add(interest2);
       //JButton 

        //empty field for spacing
        button1 = new JTextArea();
        button1.setBackground(Color.GRAY);
        button1.setEditable(false);
        jpLeft.add(button1);

        //JButton
        calc = new JButton("calc");
        calc.addActionListener(this);
        calc.setForeground(Color.WHITE);
        calc.setBackground(Color.black);
        jpLeft.add(calc);

        //label
        everyMonth = new JLabel();
        everyMonth.setText("Monthly Payment:");
        jpLeft.add(everyMonth);
        //field
        monthlyPay = new JTextArea();
        monthlyPay.setEditable(false);
        jpLeft.add(monthlyPay);

        //direction label
        direct1 = new JLabel();
        direct1.setText("Type below & hit the " + "calc" + "button for amortization table:");
        direct1.setForeground(Color.BLACK);
        jpLeft.add(direct1);
        //direction label                                     
        direct2 = new JLabel();
        direct2.setForeground(Color.BLACK);
        jpLeft.add(direct2);

        //label
        rateOfReturn = new JLabel();
        rateOfReturn.setText("Rate of Return::");
        jpLeft.add(rateOfReturn);
        //field
        rateReturn = new JTextField(10);
        jpLeft.add(rateReturn);
        //label
        duration1 = new JLabel();
        duration1.setText("Month(s):");
        jpLeft.add(duration1);
        //field
        duration2 = new JTextField(10);
        jpLeft.add(duration2);

        //empty field for spacing
        button2 = new JTextArea();
        button2.setBackground(Color.GRAY);
        button2.setEditable(false);
        jpLeft.add(button2);

        //button
        go = new JButton("calc");
        go.addActionListener(this);
        go.setForeground(Color.WHITE);
        go.setBackground(Color.black);
        jpLeft.add(go);

        //total values
        //label
        values = new JLabel("Total saved(1), Total interest pocketed(2): ");
        jpLeft.add(values);
        //field 
        totalValues = new JTextArea();
        jpLeft.add(totalValues);

        frame.setVisible(true);
        frame.pack();

    }

    /**
     * ActionPerformed method that handles actionListener from JButtons and calls specified methods for a buttons designated purpose
     * @param e passes in reference to which JButton has been clicked to perform correct task
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int count = 1;
        if (e.getSource() == calc) {
            crunchNums();
        }
        if (e.getSource() == go) {

            save();
        }

    }

    /**
     * Method that takes in no parameters and grabs values of text fields so that calculations can be made. 
     * These calculations are to be considered as the "base case" and used in the following Save method to create an accurate amortization table
     */
    public void crunchNums() {
        //principle loan amount 
        double p;
        p = Double.parseDouble(loanAmount.getText());
        //interest rate variable
        double i;
        i = Double.parseDouble(interest2.getText());
        i = i / (12 * 100);
        //years //in doubles to acount for months? or no?
        double y;
        y = Double.parseDouble(years.getText());
        //conversion
        y *= 12;
        //monthly payments
        double m;
        //calculate
        m = p * (i / (1 - Math.pow((1 + i), -y)));
        //format into 2 decimals 
        DecimalFormat df1 = new DecimalFormat("#.##");
        monthlyPay.setText(df1.format(m));
        System.out.println(m);

    }

    /**
     * Method that is used to create data values to fill amortization JTable when specified rate of return, loan duration is entered, and second JButton is clicked.
     * All data values are pulled from text fields and base case values are used from crunchNums method above when needed
     */
    public void save() {

        //principle loan amount 
        double p;
        p = Double.parseDouble(loanAmount.getText());

        //rate of return- how much getting back every month
        double r;
        r = Double.parseDouble(rateReturn.getText());
        // r=r * 100 turns interest per month into cents but it must be kept in percent form before entering loop 
        //to show accurate dipiction of what is being taken out of the principle in chart each month
        r = r / (12 * 100);
        //interest cannot be two decimals because it is specific to user input 
        String interest = "" + r;

        //period of time 
        int d;
        d = Integer.parseInt(duration2.getText());

        //no need for converstion it will be typed in in months
        double moneySaved;
        moneySaved = p * (r / (1 - Math.pow((1 + r), -d)));
        //adding not subtracting bank account each month
        double balance = 0;

        //add last rows: how much saved and increase in balance
        //2 decimals
        DecimalFormat df2 = new DecimalFormat("#.##");
        String monthlySave = df2.format(moneySaved);
        //2 decimals
        DecimalFormat df3 = new DecimalFormat("#.##");
        String balIncrease = df3.format(balance);

        //add to model rows
        DefaultTableModel model = (DefaultTableModel) info.getModel();

        //turn interest into cents before entering loop to ensure accurate calculations 
        r = r * 100;
        //update values for every button press and run down balance
        for (int i = d; i > 0; i--) {

            //principal owed decrease
            //p = (p - l) + r; in theory wll yield the same end result, choosing to display loan amount 
            //decrease and balance increase intead of principal and interest make up monthly payment
            p = (p - moneySaved) + r;
            DecimalFormat df5 = new DecimalFormat("#.##");
            String principal = df5.format(p);

            //set text to subtracted value
            String newMonth = "" + i;
            //increase balance
            balance = balance + moneySaved;
            DecimalFormat df6 = new DecimalFormat("#.##");
            balIncrease = df6.format(balance);

            //total values calculated
            String totalSaved = balIncrease;

            //bring in original loan value
            double l;
            l = Double.parseDouble(loanAmount.getText());

            double calcTotalReturn = balance - l;
            DecimalFormat df7 = new DecimalFormat("#.##");
            String totalInterest = df7.format(calcTotalReturn);

            //pass them into textArea
            totalValues.setText("(1) " + totalSaved + " (2) " + totalInterest);

            Object[] dataValues = {newMonth, principal, interest, monthlySave, balIncrease};
            model.addRow(dataValues);
        }

    }

}
