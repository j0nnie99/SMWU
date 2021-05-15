import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class ChartBar extends JPanel
{
    private int histogramHeight = 200;
    private int barWidth = 50;
    private int barGap = 10;

    private JPanel barPanel;
    private JPanel labelPanel;

    private List<Bar> bars = new ArrayList<Bar>();

    public ChartBar()
    {
        setBorder( new EmptyBorder(10, 10, 10, 10) );
        setLayout( new BorderLayout() );

        barPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
        Border inner = new EmptyBorder(10, 10, 0, 10);
        Border compound = new CompoundBorder(outer, inner);
        barPanel.setBorder( compound );

        labelPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        labelPanel.setBorder( new EmptyBorder(5, 10, 0, 10) );

        add(barPanel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.PAGE_END);
    }

    public void addHistogramColumn(String label, int value, Color color)
    {
        Bar bar = new Bar(label, value, color);
        bars.add( bar );
    }

    public void layoutHistogram()
    {
        barPanel.removeAll();
        labelPanel.removeAll();

        int maxValue = 0;

        for (Bar bar: bars)
            maxValue = Math.max(maxValue, bar.getValue());

        for (Bar bar: bars)
        {
            JLabel label = new JLabel(bar.getValue() + "");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.TOP);
            label.setVerticalAlignment(JLabel.BOTTOM);
            int barHeight = (bar.getValue() * histogramHeight) / maxValue;
            Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight);
            label.setIcon( icon );
            barPanel.add( label );

            JLabel barLabel = new JLabel( bar.getLabel() );
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add( barLabel );
        }
    }

    private class Bar
    {
        private String label;
        private int value;
        private Color color;

        public Bar(String label, int value, Color color)
        {
            this.label = label;
            this.value = value;
            this.color = color;
        }

        public String getLabel()
        {
            return label;
        }

        public int getValue()
        {
            return value;
        }

        public Color getColor()
        {
            return color;
        }
    }

    private class ColorIcon implements Icon
    {
        private int shadow = 3;

        private Color color;
        private int width;
        private int height;

        public ColorIcon(Color color, int width, int height)
        {
            this.color = color;
            this.width = width;
            this.height = height;
        }

        public int getIconWidth()
        {
            return width;
        }

        public int getIconHeight()
        {
            return height;
        }

        public void paintIcon(Component c, Graphics g, int x, int y)
        {
            g.setColor(color);
            g.fillRect(x, y, width - shadow, height);
            g.setColor(Color.GRAY);
            g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
        }
    }

    private static void createAndShowGUI()
    {
        ChartBar panel = new ChartBar();
        panel.addHistogramColumn("2016", 350, Color.RED);
        panel.addHistogramColumn("2017", 690, Color.YELLOW);
        panel.addHistogramColumn("2018", 510, Color.BLUE);
        panel.addHistogramColumn("2019", 570, Color.ORANGE);
        panel.addHistogramColumn("2020", 180, Color.MAGENTA);
        panel.addHistogramColumn("2021", 504, Color.CYAN);
        panel.layoutHistogram();

        JFrame frame = new JFrame("Chart Bar: 윤정인");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add( panel );
        frame.setLocationByPlatform( true );
        frame.pack();
        frame.setVisible( true );
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });
    }
}