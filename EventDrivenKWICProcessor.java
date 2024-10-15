package 软件体系结构222;

import java.io.*;
import java.util.*;

public class EventDrivenKWICProcessor {
    private List<String> inputLines;
    private List<String> kwicList;

    public EventDrivenKWICProcessor() {
        inputLines = new ArrayList<>();
        kwicList = new ArrayList<>();
    }

    public void input(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                inputLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void process() {
        for (String line : inputLines) {
            fireEvent(new ProcessEvent(line));
        }
        Collections.sort(kwicList, String.CASE_INSENSITIVE_ORDER);
    }

    private void fireEvent(ProcessEvent event) {
        String[] words = event.getLine().split("\\s+");
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < words.length; j++) {
                sb.append(words[j]).append(" ");
            }
            for (int j = 0; j < i; j++) {
                sb.append(words[j]).append(" ");
            }
            kwicList.add(sb.toString().trim());
        }
    }

    public List<String> getKWICList() {
        return kwicList;
    }

    public static class ProcessEvent {
        private String line;

        public ProcessEvent(String line) {
            this.line = line;
        }

        public String getLine() {
            return line;
        }
    }
}