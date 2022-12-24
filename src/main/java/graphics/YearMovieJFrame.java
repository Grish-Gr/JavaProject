package graphics;

import database.FilmEntity;
import network.data.detail.CastMovie;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class YearMovieJFrame extends JFrame {

    public YearMovieJFrame(ManagerGraphics managerGraphics, List<?> listFilm){
        if (listFilm.get(0) instanceof FilmEntity){
            JFreeChart chart = managerGraphics.createChart(managerGraphics.getDatasetByDB((List<FilmEntity>) listFilm));
            initUI(chart);
        } else if (listFilm.get(0) instanceof CastMovie){
            JFreeChart chart = managerGraphics.createChart(managerGraphics.getDatasetByNetwork((List<CastMovie>) listFilm));
            initUI(chart);
        }
    }

    private void initUI(JFreeChart chart){

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
