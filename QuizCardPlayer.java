import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class QuizCardPlayer{

private JTextArea display;
private JTextArea answer;
private ArrayList<QuizCard> cardList;
private QuizCard currentCard;
private int currentCardIndex;
private JFrame frame;
private JButton nextButton;
private boolean isShowAnswer;

public static void main (String[] args){
QuizCardPlayer reader = new QuizCardPlayer();
reader.go();
}

public void go(){
// build gui

frame = new JFrame("Quiz Card Player");
JPanel mainPanel = new JPanel();
Font bigFont = new Font("sanserif", Font.BOLD, 24);

display = new JTextArea(10, 20);
display.setFont(bigFont);

display.setLiveWrap(true);
display.setEditable(false);

JScrollPane qScroller = new JScrollPane(display);
qScroller.setVerticalScrollBarPolicy(ScrollBarConstants.VERTICAL_SCROLLBAR_ALWAYS);
qScroller.setHorizontalScrollBarPolicy(ScrollBarConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
nextButton = new JButton("Show Question");
mainPanel.add(qScroller);
mainPanel.add(nextButton);
nextButton.addActionListener(new NextCardListener());

JMenuBar menuBar = new JMenuBar();
JMenu fileMenu = new JMenu("File");
JMenuItem loadMenuItem = new JMenuItem("Load card set");
loadMenuItem.addActionListener(new OpenMenuListener());
menuBar.add(loadMenuItem);
frame.setJMenuBar(menuBar);
frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
frame.setSize(640, 500);
frame.setVisible(true);

}

public class NextCardListener implements ActionListener{
public void actionPerformed(ActionEvent ev){
if(isShowAnswer){
// show the answer because they've seen the question
display.setText(currentCard.getAnswer());
