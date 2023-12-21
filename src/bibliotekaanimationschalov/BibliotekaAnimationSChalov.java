package bibliotekaanimationschalov;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BibliotekaAnimationSChalov extends JFrame {
    private static BibliotekaAnimationSChalov library;
    private static Image logo;
    private static Image bookImage;
    private static Timer timer;
    private static int logoX = 50;
    private static int logoY = 50;
    private static int logoWidth = 500;
    private static int logoHeight = 500;
// Автор: MrZyablik
    public static void main(String[] args) throws IOException {
        logo = ImageIO.read(BibliotekaAnimationSChalov.class.getResourceAsStream("logo.png"));
        bookImage = ImageIO.read(BibliotekaAnimationSChalov.class.getResourceAsStream("Dostoevskiy.png"));

        library = new BibliotekaAnimationSChalov();
        library.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        library.setLocation(200, 50);
        library.setSize(900, 600);
        library.setResizable(true);

        LibraryField libraryField = new LibraryField();
        library.add(libraryField);

        timer = new Timer(30, new ActionListener() { // Увеличение интервала для более медленного падения
            @Override
            public void actionPerformed(ActionEvent e) {
                logoX += 5;
                if (logoX > library.getWidth()) {
                    logoX = -logoWidth;
                }
                library.repaint();
            }
        });
// Автор: MrZyblik
        timer.start();

        library.setVisible(true);
    }

    public static void onRepaint(Graphics g) {
        g.setColor(java.awt.Color.BLACK);
        g.fillRect(0, 0, library.getWidth(), library.getHeight());

        g.drawImage(logo, logoX, logoY, logoWidth, logoHeight, null);
    }

    public static class LibraryField extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            onRepaint(g);
            drawBooks(g);
        }

        private void drawBooks(Graphics g) {
            int bookWidth = 110;  // Новый размер книг
            int bookHeight = 110;

            for (int i = 0; i < 5; i++) {
                int bookX = (int) (Math.random() * (library.getWidth() - bookWidth));
                int bookY = (int) (Math.random() * (library.getHeight() - bookHeight));

                g.drawImage(bookImage, bookX, bookY, bookWidth, bookHeight, null);
            }
        }
    }
}