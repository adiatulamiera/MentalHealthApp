import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.List;

public class LearnPanel extends JPanel {
    private LearningModule learningModule;
    private JPanel contentPanel;
    private JButton nextButton, backButton;
    private JFrame parentFrame;

    public LearnPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.learningModule = new LearningModule("Mental Health", 15);

        setLayout(new BorderLayout());

        // Panel to dynamically hold learning content
        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Navigation Panel
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backButton = new JButton("← Back");
        nextButton = new JButton("Next →");

        navPanel.add(backButton);
        navPanel.add(nextButton);
        add(navPanel, BorderLayout.SOUTH);

        // Button listeners
        backButton.addActionListener(e -> {
            learningModule.previousPage();
            renderCurrentPage();
        });

        nextButton.addActionListener(e -> {
            boolean hasNext = learningModule.nextPage();
            if (!hasNext) {
                JOptionPane.showMessageDialog(this, "🎉 You’ve completed the module!");

                // 🏠 Return to home panel
                if (parentFrame instanceof MainGUI gui) {
                    gui.switchTo("home");
                }
                return; // No need to render again
            }
            renderCurrentPage();
        });

        // First render
        renderCurrentPage();
    }

    private void renderCurrentPage() {
        contentPanel.removeAll();

        LearningContent content = learningModule.getCurrentPage();
        switch (content.getType()) {
            case IMAGE:
                renderImagePage(content.getImagePaths());
                break;
            case VIDEO:
                renderVideoPage(content.getTextContent(), content.getVideoLink());
                break;
            case TEXT:
                renderTextPage(content.getTextContent());
                break;
        }

        revalidate();
        repaint();
    }

    private void renderTextPage(String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textArea.setMargin(new Insets(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(textArea);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void renderVideoPage(String title, String videoLink) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JButton openButton = new JButton("▶ Open Video");
        openButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        openButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI(videoLink));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(openButton);

        contentPanel.add(panel, BorderLayout.CENTER);
    }

    private void renderImagePage(List<String> imagePaths) {
    JPanel imagePanel = new JPanel();
    imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
    imagePanel.setBackground(Color.WHITE);

    for (String path : imagePaths) {
        ImageIcon icon = new ImageIcon(path);
        Image originalImage = icon.getImage();

        JLabel imageLabel = new JLabel(new ImageIcon(originalImage));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 🌼 Pastel rounded frame panel (no image resizing)
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
        frame.setMaximumSize(new Dimension(360, Integer.MAX_VALUE));
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
