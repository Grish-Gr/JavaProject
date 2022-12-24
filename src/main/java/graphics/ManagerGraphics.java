package graphics;

import database.FilmEntity;
import network.data.detail.CastMovie;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ManagerGraphics {

    public XYDataset getDatasetByNetwork(List<CastMovie> castMovieList){
        Map<String, List<CastMovie>> mapYearFilms = castMovieList.stream().collect(
                Collectors.groupingBy(CastMovie::getYear)
        );
        TimeSeries timeSeries = new TimeSeries("Year - Film");
        for (String keyYear: mapYearFilms.keySet()){
            if (keyYear.length() < 3) continue;
            int countFilm = mapYearFilms.get(keyYear).size();
            timeSeries.addOrUpdate(new Year(Integer.parseInt(keyYear.substring(0, 4))), countFilm);
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(timeSeries);
        return dataset;
    }

    public XYDataset getDatasetByDB(List<FilmEntity> filmEntityList){
        Map<String, List<FilmEntity>> mapYearFilms = filmEntityList.stream().collect(
                Collectors.groupingBy(FilmEntity::getYear)
        );
        TimeSeries timeSeries = new TimeSeries("Year - Film");
        for (String keyYear: mapYearFilms.keySet()){
            if (keyYear.length() < 3) continue;
            int countFilm = mapYearFilms.get(keyYear).size();
            timeSeries.addOrUpdate(new Year(Integer.parseInt(keyYear.substring(0, 4))), countFilm);
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(timeSeries);
        return dataset;
    }

    public JFreeChart createChart(XYDataset dataset)
    {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Year - Film",  // title
                "",                            // x-axis label
                "Count Film",                  // y-axis label
                dataset,                       // data
                true,                          // create legend
                true,                          // generate tooltips
                false                          // generate URLs
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint    (Color.lightGray);
        plot.setDomainGridlinePaint(Color.white    );
        plot.setRangeGridlinePaint (Color.white    );
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer renderer) {
            renderer.setDrawSeriesLineAsPath(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("YYYY"));

        return chart;
    }
}
