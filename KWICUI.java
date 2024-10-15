package 软件体系结构222;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class KWICUI extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;
    private JComboBox<String> architectureComboBox;
    private JButton processButton;

    public KWICUI() {
        super("KWIC Processor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        inputField = new JTextField(20);
        outputArea = new JTextArea(10, 30);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setEditable(false);
        architectureComboBox = new JComboBox<>(new String[]{"主程序-子程序", "面向对象", "事件系统", "管道-过滤"});
        processButton = new JButton("Process");

        add(new JLabel("C:\\Users\\ASUS\\Desktop\\soft_test_class\\testYJ\\Ruan_Jian_She_Ji_Mo_Shi\\src\\input.txt"));
        add(inputField);
        add(new JLabel("Architecture:"));
        add(architectureComboBox);
        add(new JLabel("C:\\Users\\ASUS\\Desktop\\soft_test_class\\testYJ\\Ruan_Jian_She_Ji_Mo_Shi\\src\\output4.txt:"));
        add(new JScrollPane(outputArea));
        add(processButton);

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processFile();
            }
        });
    }

    private void processFile() {
        String inputPath = inputField.getText();
        List<String> outputLines = new ArrayList<>();

        switch ((String) architectureComboBox.getSelectedItem()) {
            case "主程序-子程序":
                KWICProcessor processor1 = new KWICProcessor();
                processor1.input(inputPath);
                processor1.shift();
                processor1.alphabetizer();
                outputLines.addAll(processor1.getKWICList());
                break;
            // 其他体系结构的处理逻辑可以在这里实现
            case "面向对象":
                 ObjectOrientedKWICProcessor processor2 = new ObjectOrientedKWICProcessor();
                 processor2.input(inputPath);
                 processor2.process();
                 outputLines.addAll(processor2.getKWICList());
                break;
            case "事件系统":
                 EventDrivenKWICProcessor processor3 = new EventDrivenKWICProcessor();
                 processor3.input(inputPath);
                 processor3.process();
                 outputLines.addAll(processor3.getKWICList());
                break;
            case "管道-过滤":
                 PipelineFilterKWICProcessor processor4 = new PipelineFilterKWICProcessor();
                 processor4.input(inputPath);
                 processor4.process();
                 outputLines.addAll(processor4.getKWICList());
                break;
        }

        outputArea.setText(String.join("\n", outputLines));
        JOptionPane.showMessageDialog(this, "Processing complete!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KWICUI frame = new KWICUI();
            frame.setVisible(true);
        });
    }
}