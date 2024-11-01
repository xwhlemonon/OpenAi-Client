package com.chatgpt.openaiclient.app.ui;

import com.chatgpt.openaiclient.OpenAiClientApplication;
import com.chatgpt.openaiclient.app.pojo.VO.node.Messages;
import com.chatgpt.openaiclient.app.service.SendService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MessageWindow {
    private final JTextArea textArea;
    private final JTextArea messageArea;
    private final JButton sendButton;
    private final JButton resetButton;
    private final JButton setPromptButton;
    private final List<Messages> messageList;
    private final JFrame frame;

    public MessageWindow() {
        messageList = OpenAiClientApplication.HISTORY_LIST;

        // 创建窗口
        frame = new JFrame("OpenAI"); // 窗口名
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 默认关闭策略
        frame.setLayout(new BorderLayout()); // 布局

        // 消息显示框
        messageArea = new JTextArea();
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setEditable(false); // 禁用
        JScrollPane messageScrollPane = new JScrollPane(messageArea); // 加入组件
        frame.add(messageScrollPane, BorderLayout.CENTER); // 加入窗口

        // 创建组件
        JPanel inputPanel = new JPanel(new BorderLayout());

        // 输入框组件
        textArea = new JTextArea(3, 20);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane inputScrollPane = new JScrollPane(textArea);
        inputPanel.add(inputScrollPane, BorderLayout.CENTER);

        // 按钮组件
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // 按钮
        sendButton = new JButton("Send");
        sendButton.setEnabled(false);
        resetButton = new JButton("Reset");
        setPromptButton = new JButton("Set Prompt");

        // 按钮大小
        Dimension buttonSize = new Dimension(100, 25);

        // 设置按钮大小
        sendButton.setMinimumSize(buttonSize);
        resetButton.setMinimumSize(buttonSize);
        setPromptButton.setMinimumSize(buttonSize);

        sendButton.setPreferredSize(buttonSize);
        resetButton.setPreferredSize(buttonSize);
        setPromptButton.setPreferredSize(buttonSize);

        sendButton.setMaximumSize(buttonSize);
        resetButton.setMaximumSize(buttonSize);
        setPromptButton.setMaximumSize(buttonSize);

        // 添加点击事件
        sendButton.addActionListener(new SendButtonListener());
        resetButton.addActionListener(new ResetButtonListener());
        setPromptButton.addActionListener(new SetPromptButtonListener());

        // 加入组件
        buttonPanel.add(sendButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add spacing between buttons
        buttonPanel.add(resetButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(setPromptButton);

        inputPanel.add(buttonPanel, BorderLayout.EAST);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // 输入框事件
        textArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                manageButtonStates();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                manageButtonStates();
            }

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                manageButtonStates();
            }
        });

        // 设置窗口大小显示
        frame.pack();
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void manageButtonStates() {
        String text = textArea.getText().trim();
        sendButton.setEnabled(!text.isEmpty() && !messageList.isEmpty());
        setPromptButton.setEnabled(messageList.isEmpty());
    }

    private void send() {
        // Simulate sending, e.g., through an API call.
        SendService.getAssistant();
        // For demonstration assume it takes some time and is successful
    }

    private void updateMessageArea() {
        StringBuilder sb = new StringBuilder();
        for (Messages message : messageList) {
            sb.append(message.getRole()).append(":\n").append(message.getContent().get(0).getText()).append("\n\n");
        }
        messageArea.setText(sb.toString());
    }

    class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = textArea.getText().trim();
            if (!text.isEmpty()) {
                messageList.add(Messages.user(text));
                textArea.setText("");
                sendButton.setEnabled(false);
                resetButton.setEnabled(false);
                setPromptButton.setEnabled(false);
                textArea.setEnabled(false);
                updateMessageArea();
                new Thread(() -> {
                    send();
                    SwingUtilities.invokeLater(() -> {
                        textArea.setEnabled(true);
                        resetButton.setEnabled(true);
                        manageButtonStates();
                        updateMessageArea();
                    });
                }).start();
            }
        }
    }

    class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            messageList.clear();
            textArea.setText("");
            updateMessageArea();
            manageButtonStates();
        }
    }

    class SetPromptButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = textArea.getText().trim();
            if (!text.isEmpty()) {
                messageList.add(Messages.system(text));
                textArea.setText("");
                updateMessageArea();
                manageButtonStates();
            }
        }
    }
}