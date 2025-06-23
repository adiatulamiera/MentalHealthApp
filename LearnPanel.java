// Implemented by Amiera
// Tested by Mifdzal

import java.awt.*;
import java.net.URI;
import java.util.List;
import javax.swing.*;

public class LearnPanel extends JPanel {
    private LearningModule learningModule;
    private JPanel contentPanel;
    private JButton nextButton, backButton;
    private JFrame parentFrame;

    public LearnPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.learningModule = new LearningModule("Mental Health", 15);

        setLayout(new BorderLayout());

        // Main display area
        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Navigation bar
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backButton = new JButton("← Back");
        nextButton = new JButton("Next →");

        navPanel.add(backButton);
        navPanel.add(nextButton);
        add(navPanel, BorderLayout.SOUTH);

        // Button actions
        backButton.addActionListener(e -> {
            learningModule.previousPage();
            renderCurrentPage();
        });

        nextButton.addActionListener(e -> {
            boolean hasNext = learningModule.nextPage();
            if (!hasNext) {
                JOptionPane.showMessageDialog(this, "🎉 You’ve completed the module!");

                // Return to home screen
                if (parentFrame instanceof MainGUI gui) {
                    gui.switchTo("home");
                }
                return;
            }
            renderCurrentPage();
        });

        // Show first page
        renderCurrentPage();
    }

    private void renderCurrentPage() {
        contentPanel.removeAll();

        LearningContent content = learningModule.getCurrentPage();
        switch (content.getType()) {
            case IMAGE -> renderImagePage(content.getImagePaths());
            case TEXT -> renderTextPage(content.getTextContent(), content.getVideoLink());
        }

        revalidate();
        repaint();
    }

    private void renderTextPage(String rawText, String videoLink) {
    contentPanel.removeAll();

    // Convert \n to <br> and wrap in styled HTML
    String htmlText = "<html><body style='font-family:sans-serif; font-size:14px; line-height:1.6; padding:10px;'>"
            + rawText.replace("\n", "<br>")
            + "</body></html>";

    JEditorPane textPane = new JEditorPane("text/html", htmlText);
    textPane.setEditable(false);
    textPane.setOpaque(false);

    JScrollPane scrollPane = new JScrollPane(textPane);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);

    // If there's a video button (optional)
    if (videoLink != null && !videoLink.isEmpty()) {
        JButton videoButton = new JButton("▶ Watch Video");
        videoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        videoButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI(videoLink));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(scrollPane);
        panel.add(Box.createVerticalStrut(15));
        panel.add(videoButton);

        contentPanel.add(panel, BorderLayout.CENTER);
    } else {
        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }

    revalidate();
    repaint();
}

    private void renderImagePage(List<String> imagePaths) {
    JPanel imagePanel = new JPanel();
    imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
    imagePanel.setBackground(Color.WHITE);

    for (String path : imagePaths) {
        // Try loading image
        ImageIcon icon = new ImageIcon(path);
        if (icon.getIconWidth() <= 0 || icon.getIconHeight() <= 0) {
            JLabel errorLabel = new JLabel("⚠️ Image not found: " + path);
            errorLabel.setForeground(Color.RED);
            errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            imagePanel.add(errorLabel);
            continue;
        }

        Image originalImage = icon.getImage();
        Image scaledImage = originalImage.getScaledInstance(350, -1, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Pastel frame with rounded corners
        JPanel frame = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 250, 240)); // pastel cream
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }
        };

        frame.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        frame.setOpaque(false);
        frame.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));
        frame.add(imageLabel, BorderLayout.CENTER);
        frame.setAlignmentX(Component.CENTER_ALIGNMENT);

        imagePanel.add(Box.createVerticalStrut(20));
        imagePanel.add(frame);
    }

    JScrollPane scrollPane = new JScrollPane(imagePanel);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    contentPanel.add(scrollPane, BorderLayout.CENTER);
}
}
