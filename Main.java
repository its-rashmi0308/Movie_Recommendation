import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.net.URI;

class MoodMovieRecommender {
    private JFrame frame;
    private JComboBox<String> moodBox;
    private JLabel resultLabel;
    private Map<String, String> moodToMovie;

    public MoodMovieRecommender() {
        // Mood-to-Movie Map (trailer links or clips)
        moodToMovie = new HashMap<>();
        moodToMovie.put("Happy", "https://youtu.be/P8mi8WhZpew?si=sfu5sYcHIPrDZXIH"); // Mujhse Shadi Krogi
        moodToMovie.put("Sad", "https://youtu.be/htwtV6qsSVo?si=RZBLF4u6Y4utsW7H"); // Sanam Teri Kasam
        moodToMovie.put("Motivational", "https://youtu.be/6q-GCCtgsDM?si=Z5Rb-CFjaAlZljIQ"); // 12th Fail
        moodToMovie.put("Funny", "https://youtu.be/TIQ5hrfermg?si=Tfo_HtVzEpwldgZl"); // Hera Pheri
        moodToMovie.put("Romantic", "https://youtu.be/cXIduSwk-rM?si=Gox4ZRPSgz5EHPyf"); // Hum Dil De Chuke Sanam

        frame = new JFrame("Mood-Based Movie Recommender");
        frame.setSize(500, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Select your Mood:");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        moodBox = new JComboBox<>(moodToMovie.keySet().toArray(new String[0]));
        moodBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton getMovieButton = new JButton("Get Movie");
        getMovieButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        resultLabel = new JLabel("🎬 Your recommended movie will appear here.");
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        getMovieButton.addActionListener(e -> recommendMovie());

        // Layout using BoxLayout for vertical centering
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(20)); // spacing
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(moodBox);
        panel.add(Box.createVerticalStrut(10));
        panel.add(getMovieButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(resultLabel);
        panel.add(Box.createVerticalGlue());

        frame.add(panel);
        frame.setLocationRelativeTo(null); // center the frame
        frame.setVisible(true);
    }

    private void recommendMovie() {
        String selectedMood = (String) moodBox.getSelectedItem();
        String movieURL = moodToMovie.get(selectedMood);

        try {
            Desktop.getDesktop().browse(new URI(movieURL));
            resultLabel.setText("🎥 Playing: " + selectedMood + " movie recommendation");
        } catch (Exception ex) {
            resultLabel.setText("❌ Unable to open movie link.");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MoodMovieRecommender::new);
    }
}