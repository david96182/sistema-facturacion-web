package cu.edu.cujae.pweb.bean;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.axes.radial.RadialScales;
import org.primefaces.model.charts.axes.radial.linear.RadialLinearAngleLines;
import org.primefaces.model.charts.axes.radial.linear.RadialLinearPointLabels;
import org.primefaces.model.charts.axes.radial.linear.RadialLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.bubble.BubbleChartDataSet;
import org.primefaces.model.charts.bubble.BubbleChartModel;
import org.primefaces.model.charts.data.BubblePoint;
import org.primefaces.model.charts.data.NumericPoint;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.elements.Elements;
import org.primefaces.model.charts.optionconfig.elements.ElementsLine;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import org.primefaces.model.charts.radar.RadarChartDataSet;
import org.primefaces.model.charts.radar.RadarChartModel;
import org.primefaces.model.charts.radar.RadarChartOptions;
import org.primefaces.model.charts.scatter.ScatterChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.CargaDto;
import cu.edu.cujae.pweb.dto.ServicioDto;
import cu.edu.cujae.pweb.service.CargaService;
import cu.edu.cujae.pweb.service.ServicioService;


@Component
@ManagedBean
@ViewScoped
public class ChartJsView{

	
	@Autowired
	private ServicioService servicioserv;
	
	@Autowired
	private CargaService cargaserv;
    
	
	private PieChartModel pieModel;

    private PolarAreaChartModel polarAreaModel;

    private LineChartModel lineModel;

    private LineChartModel cartesianLinerModel;

    private BarChartModel barModel;

    private BarChartModel barModel2;

    private HorizontalBarChartModel hbarModel;

    private BarChartModel stackedBarModel;

    private BarChartModel stackedGroupBarModel;

    private RadarChartModel radarModel;

    private RadarChartModel radarModel2;

    private BubbleChartModel bubbleModel;

    private BarChartModel mixedModel;

    private DonutChartModel donutModel;

    private ScatterChartModel scatterModel;

    /*@PostConstruct
    public void init() {
        createPieModel();
        createPolarAreaModel();
        createLineModel();
        createCartesianLinerModel();
        
        createHorizontalBarModel();
        
        createRadarModel();
        createRadarModel2();
        createBubbleModel();
       
        createScatterModel();
    }*/
    public void createBarModel2() {
    	List<ServicioDto> serv = servicioserv.getServicios();
    	List<CargaDto> cargs = cargaserv.getCargas();
    	float impor2020 = 0;
    	float export2020 = 0;
    	float impor2021 = 0;
    	float expor2021 = 0;
    	Date i2020 = new Date(120,00,01);
    	Date f2020 = new Date(120,11,31);
    	Date i2021 = new Date(121,00,01);
    	Date f2021 = new Date(121,11,31);
    	
    	// 2020 i
    	Instant in = i2020.toInstant();
		ZonedDateTime zft = in.atZone(ZoneId.systemDefault());
		LocalDate ii2020 = zft.toLocalDate();
		
		//2020 f
		Instant in1 = f2020.toInstant();
		ZonedDateTime zft1 = in1.atZone(ZoneId.systemDefault());
		LocalDate ff2020 = zft1.toLocalDate();
		
		//2021 i
		Instant in2 = i2021.toInstant();
		ZonedDateTime zft2 = in2.atZone(ZoneId.systemDefault());
		LocalDate ii2021 = zft2.toLocalDate();
		
		//2021 f
		Instant in3 = f2021.toInstant();
		ZonedDateTime zft3 = in3.atZone(ZoneId.systemDefault());
		LocalDate ff2021 = zft3.toLocalDate();
		
       /* System.out.println(ii2020);
        System.out.println(ff2020);
        System.out.println(ii2021);
        System.out.println(ff2021);*/
		
		Iterator<ServicioDto> it = serv.iterator();
		while(it.hasNext()) {
			ServicioDto s = it.next();
			CargaDto car = cargaserv.getCargaById(s.getIdCarga());
			
			Instant inn = car.getFechaIngreso().toInstant();
			ZonedDateTime zftt = inn.atZone(ZoneId.systemDefault());
			LocalDate asdf = zftt.toLocalDate();
			
			if(asdf.isAfter(ii2020) && asdf.isBefore(ff2020)) {
				if(s.getTipo_movimiento().equalsIgnoreCase("importacion")) {
					impor2020++;
				}
				else {
					export2020++;
				}
			}
			else if(asdf.isAfter(ii2021) && asdf.isBefore(ff2021)) {
				if(s.getTipo_movimiento().equalsIgnoreCase("importacion")) {
					impor2021++;
				}
				else {
					expor2021++;
				}
			}
		}
    	
    	
    	barModel2 = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Importaciones");
        barDataSet.setBackgroundColor("rgba(255, 99, 132, 0.2)");
        barDataSet.setBorderColor("rgb(255, 99, 132)");
        barDataSet.setBorderWidth(1);
        List<Number> values = new ArrayList<>();
        values.add(impor2020);
        values.add(impor2021);
       
        barDataSet.setData(values);

        
        BarChartDataSet barDataSet2 = new BarChartDataSet();
        barDataSet2.setLabel("Exportaciones");
        barDataSet2.setBackgroundColor("rgba(255, 159, 64, 0.2)");
        barDataSet2.setBorderColor("rgb(255, 159, 64)");
        barDataSet2.setBorderWidth(1);
        List<Number> values2 = new ArrayList<>();
        values2.add(export2020);
        values2.add(expor2021);
        
        barDataSet2.setData(values2);

        data.addChartDataSet(barDataSet);
        data.addChartDataSet(barDataSet2);

        List<String> labels = new ArrayList<>();
        labels.add("2020");
        labels.add("2021");
        
        data.setLabels(labels);
        barModel2.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);

        barModel2.setOptions(options);
    }

    private void createPieModel() {
    	List<ServicioDto> serv = servicioserv.getServicios();
    	List<CargaDto> cargs = cargaserv.getCargas();
    	float v2020 = 0;
    	float v2021 = 0;
    	Date i2020 = new Date(120,00,01);
    	Date f2020 = new Date(120,11,31);
    	Date i2021 = new Date(121,00,01);
    	Date f2021 = new Date(121,11,31);
    	
    	// 2020 i
    	Instant in = i2020.toInstant();
		ZonedDateTime zft = in.atZone(ZoneId.systemDefault());
		LocalDate ii2020 = zft.toLocalDate();
		
		//2020 f
		Instant in1 = f2020.toInstant();
		ZonedDateTime zft1 = in1.atZone(ZoneId.systemDefault());
		LocalDate ff2020 = zft1.toLocalDate();
		
		//2021 i
		Instant in2 = i2021.toInstant();
		ZonedDateTime zft2 = in2.atZone(ZoneId.systemDefault());
		LocalDate ii2021 = zft2.toLocalDate();
		
		//2021 f
		Instant in3 = f2021.toInstant();
		ZonedDateTime zft3 = in3.atZone(ZoneId.systemDefault());
		LocalDate ff2021 = zft3.toLocalDate();
		
       /* System.out.println(ii2020);
        System.out.println(ff2020);
        System.out.println(ii2021);
        System.out.println(ff2021);*/
		
		Iterator<ServicioDto> it = serv.iterator();
		while(it.hasNext()) {
			ServicioDto s = it.next();
			CargaDto car = cargaserv.getCargaById(s.getIdCarga());
			
			Instant inn = car.getFechaIngreso().toInstant();
			ZonedDateTime zftt = inn.atZone(ZoneId.systemDefault());
			LocalDate asdf = zftt.toLocalDate();
			
			if(asdf.isAfter(ii2020) && asdf.isBefore(ff2020)) {
				v2020 += s.getImporte_total();
			}
			else if(asdf.isAfter(ii2021) && asdf.isBefore(ff2021)) {
				v2021 += s.getImporte_total();
			}
		}
		pieModel = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(v2020);
        values.add(v2021);
        
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
       
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("2020");
        labels.add("2021");
        
        data.setLabels(labels);

        pieModel.setData(data);
    }

    private void createPolarAreaModel() {
        polarAreaModel = new PolarAreaChartModel();
        ChartData data = new ChartData();

        PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(11);
        values.add(16);
        values.add(7);
        values.add(3);
        values.add(14);
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(75, 192, 192)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(201, 203, 207)");
        bgColors.add("rgb(54, 162, 235)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Red");
        labels.add("Green");
        labels.add("Yellow");
        labels.add("Grey");
        labels.add("Blue");
        data.setLabels(labels);

        polarAreaModel.setData(data);
    }

    public void createLineModel() {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        values.add(65);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("My First Dataset");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setLineTension(0.1);
        data.addChartDataSet(dataSet);

        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Line Chart");
        options.setTitle(title);

        lineModel.setOptions(options);
        lineModel.setData(data);
    }

    public void createScatterModel() {
        scatterModel = new ScatterChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        values.add(new NumericPoint(-10, 0));
        values.add(new NumericPoint(0,10));
        values.add(new NumericPoint(10, 5));
        values.add(new NumericPoint(8, 14));
        values.add(new NumericPoint(12, 2));
        values.add(new NumericPoint(13, 7));
        values.add(new NumericPoint(6, 9));
        dataSet.setData(values);
        dataSet.setLabel("Red Dataset");
        dataSet.setBorderColor("rgb(249, 24, 24)");
        dataSet.setShowLine(false);
        data.addChartDataSet(dataSet);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Scatter Chart");
        options.setShowLines(false);
        options.setTitle(title);

        scatterModel.setOptions(options);
        scatterModel.setData(data);
    }

    public void createCartesianLinerModel() {
        cartesianLinerModel = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        values.add(20);
        values.add(50);
        values.add(100);
        values.add(75);
        values.add(25);
        values.add(0);
        dataSet.setData(values);
        dataSet.setLabel("Left Dataset");
        dataSet.setYaxisID("left-y-axis");

        LineChartDataSet dataSet2 = new LineChartDataSet();
        List<Object> values2 = new ArrayList<>();
        values2.add(0.1);
        values2.add(0.5);
        values2.add(1.0);
        values2.add(2.0);
        values2.add(1.5);
        values2.add(0);
        dataSet2.setData(values2);
        dataSet2.setLabel("Right Dataset");
        dataSet2.setYaxisID("right-y-axis");

        data.addChartDataSet(dataSet);
        data.addChartDataSet(dataSet2);

        List<String> labels = new ArrayList<>();
        labels.add("Jan");
        labels.add("Feb");
        labels.add("Mar");
        labels.add("Apr");
        labels.add("May");
        labels.add("Jun");
        data.setLabels(labels);
        cartesianLinerModel.setData(data);

        //Options
        LineChartOptions options = new LineChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setId("left-y-axis");
        linearAxes.setPosition("left");
        CartesianLinearAxes linearAxes2 = new CartesianLinearAxes();
        linearAxes2.setId("right-y-axis");
        linearAxes2.setPosition("right");

        cScales.addYAxesData(linearAxes);
        cScales.addYAxesData(linearAxes2);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Cartesian Linear Chart");
        options.setTitle(title);

        cartesianLinerModel.setOptions(options);
    }

   

    public void createHorizontalBarModel() {
        hbarModel = new HorizontalBarChartModel();
        ChartData data = new ChartData();

        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
        hbarDataSet.setLabel("My First Dataset");

        List<Number> values = new ArrayList<>();
        values.add(65);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        hbarDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        hbarDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        hbarDataSet.setBorderColor(borderColor);
        hbarDataSet.setBorderWidth(1);

        data.addChartDataSet(hbarDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);
        hbarModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addXAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Horizontal Bar Chart");
        options.setTitle(title);

        hbarModel.setOptions(options);
    }

    
    

    public void createRadarModel() {
        radarModel = new RadarChartModel();
        ChartData data = new ChartData();

        RadarChartDataSet radarDataSet = new RadarChartDataSet();
        radarDataSet.setLabel("My First Dataset");
        radarDataSet.setFill(true);
        radarDataSet.setBackgroundColor("rgba(255, 99, 132, 0.2)");
        radarDataSet.setBorderColor("rgb(255, 99, 132)");
        radarDataSet.setPointBackgroundColor("rgb(255, 99, 132)");
        radarDataSet.setPointBorderColor("#fff");
        radarDataSet.setPointHoverBackgroundColor("#fff");
        radarDataSet.setPointHoverBorderColor("rgb(255, 99, 132)");
        List<Number> dataVal = new ArrayList<>();
        dataVal.add(65);
        dataVal.add(59);
        dataVal.add(90);
        dataVal.add(81);
        dataVal.add(56);
        dataVal.add(55);
        dataVal.add(40);
        radarDataSet.setData(dataVal);

        RadarChartDataSet radarDataSet2 = new RadarChartDataSet();
        radarDataSet2.setLabel("My Second Dataset");
        radarDataSet2.setFill(true);
        radarDataSet2.setBackgroundColor("rgba(54, 162, 235, 0.2)");
        radarDataSet2.setBorderColor("rgb(54, 162, 235)");
        radarDataSet2.setPointBackgroundColor("rgb(54, 162, 235)");
        radarDataSet2.setPointBorderColor("#fff");
        radarDataSet2.setPointHoverBackgroundColor("#fff");
        radarDataSet2.setPointHoverBorderColor("rgb(54, 162, 235)");
        List<Number> dataVal2 = new ArrayList<>();
        dataVal2.add(28);
        dataVal2.add(48);
        dataVal2.add(40);
        dataVal2.add(19);
        dataVal2.add(96);
        dataVal2.add(27);
        dataVal2.add(100);
        radarDataSet2.setData(dataVal2);

        data.addChartDataSet(radarDataSet);
        data.addChartDataSet(radarDataSet2);

        List<String> labels = new ArrayList<>();
        labels.add("Eating");
        labels.add("Drinking");
        labels.add("Sleeping");
        labels.add("Designing");
        labels.add("Coding");
        labels.add("Cycling");
        labels.add("Running");
        data.setLabels(labels);

        /* Options */
        RadarChartOptions options = new RadarChartOptions();
        Elements elements = new Elements();
        ElementsLine elementsLine = new ElementsLine();
        elementsLine.setTension(0);
        elementsLine.setBorderWidth(3);
        elements.setLine(elementsLine);
        options.setElements(elements);

        radarModel.setOptions(options);
        radarModel.setData(data);
    }

    public void createRadarModel2() {
        radarModel2 = new RadarChartModel();
        ChartData data = new ChartData();

        RadarChartDataSet radarDataSet = new RadarChartDataSet();
        radarDataSet.setLabel("P.Practitioner");
        radarDataSet.setLineTension(0.1);
        radarDataSet.setBackgroundColor("rgba(102, 153, 204, 0.2)");
        radarDataSet.setBorderColor("rgba(102, 153, 204, 1)");
        radarDataSet.setPointBackgroundColor("rgba(102, 153, 204, 1)");
        radarDataSet.setPointBorderColor("#fff");
        radarDataSet.setPointHoverRadius(5);
        radarDataSet.setPointHoverBackgroundColor("#fff");
        radarDataSet.setPointHoverBorderColor("rgba(102, 153, 204, 1)");
        List<Number> dataVal = new ArrayList<>();
        dataVal.add(2);
        dataVal.add(3);
        dataVal.add(2);
        dataVal.add(1);
        dataVal.add(3);
        radarDataSet.setData(dataVal);

        RadarChartDataSet radarDataSet2 = new RadarChartDataSet();
        radarDataSet2.setLabel("P.Manager");
        radarDataSet2.setLineTension(0.1);
        radarDataSet2.setBackgroundColor("rgba(255, 204, 102, 0.2)");
        radarDataSet2.setBorderColor("rgba(255, 204, 102, 1)");
        radarDataSet2.setPointBackgroundColor("rgba(255, 204, 102, 1)");
        radarDataSet2.setPointBorderColor("#fff");
        radarDataSet2.setPointHoverRadius(5);
        radarDataSet2.setPointHoverBackgroundColor("#fff");
        radarDataSet2.setPointHoverBorderColor("rgba(255, 204, 102, 1)");
        List<Number> dataVal2 = new ArrayList<>();
        dataVal2.add(2);
        dataVal2.add(3);
        dataVal2.add(3);
        dataVal2.add(2);
        dataVal2.add(3);
        radarDataSet2.setData(dataVal2);

        data.addChartDataSet(radarDataSet);
        data.addChartDataSet(radarDataSet2);

        List<List<String>> labels = new ArrayList<>();
        labels.add(new ArrayList(Arrays.asList("Process","Excellence")));
        labels.add(new ArrayList(Arrays.asList("Problem","Solving")));
        labels.add(new ArrayList(Arrays.asList("Facilitation")));
        labels.add(new ArrayList(Arrays.asList("Project","Mgmt")));
        labels.add(new ArrayList(Arrays.asList("Change","Mgmt")));
        data.setLabels(labels);

        /* Options */
        RadarChartOptions options = new RadarChartOptions();
        RadialScales rScales = new RadialScales();

        RadialLinearAngleLines angelLines = new RadialLinearAngleLines();
        angelLines.setDisplay(true);
        angelLines.setLineWidth(0.5);
        angelLines.setColor("rgba(128, 128, 128, 0.2)");
        rScales.setAngelLines(angelLines);

        RadialLinearPointLabels pointLabels = new RadialLinearPointLabels();
        pointLabels.setFontSize(14);
        pointLabels.setFontStyle("300");
        pointLabels.setFontColor("rgba(204, 204, 204, 1)");
        pointLabels.setFontFamily("Lato, sans-serif");

        RadialLinearTicks ticks = new RadialLinearTicks();
        ticks.setBeginAtZero(true);
        ticks.setMaxTicksLimit(3);
        ticks.setMin(0);
        ticks.setMax(3);
        ticks.setDisplay(false);

        options.setScales(rScales);

        radarModel2.setOptions(options);
        radarModel2.setData(data);
        radarModel2.setExtender("skinRadarChart");
    }

    public void createBubbleModel() {
        bubbleModel = new BubbleChartModel();
        ChartData data = new ChartData();

        BubbleChartDataSet dataSet = new BubbleChartDataSet();
        List<BubblePoint> values = new ArrayList<>();
        values.add(new BubblePoint(20, 30, 15));
        values.add(new BubblePoint(40, 10, 10));
        dataSet.setData(values);
        dataSet.setBackgroundColor("rgb(255, 99, 132)");
        dataSet.setLabel("First Dataset");
        data.addChartDataSet(dataSet);
        bubbleModel.setData(data);
    }

    

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public PieChartModel getPieModel() {
    	createPieModel();
    	return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public PolarAreaChartModel getPolarAreaModel() {
        return polarAreaModel;
    }

    public void setPolarAreaModel(PolarAreaChartModel polarAreaModel) {
        this.polarAreaModel = polarAreaModel;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public LineChartModel getCartesianLinerModel() {
        return cartesianLinerModel;
    }

    public void setCartesianLinerModel(LineChartModel cartesianLinerModel) {
        this.cartesianLinerModel = cartesianLinerModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public BarChartModel getBarModel2() {
    	createBarModel2();
    	return barModel2;
    }

    public void setBarModel2(BarChartModel barModel2) {
        this.barModel2 = barModel2;
    }

    public HorizontalBarChartModel getHbarModel() {
        return hbarModel;
    }

    public void setHbarModel(HorizontalBarChartModel hbarModel) {
        this.hbarModel = hbarModel;
    }

    public BarChartModel getStackedBarModel() {
        return stackedBarModel;
    }

    public void setStackedBarModel(BarChartModel stackedBarModel) {
        this.stackedBarModel = stackedBarModel;
    }

    public BarChartModel getStackedGroupBarModel() {
        return stackedGroupBarModel;
    }

    public void setStackedGroupBarModel(BarChartModel stackedGroupBarModel) {
        this.stackedGroupBarModel = stackedGroupBarModel;
    }

    public RadarChartModel getRadarModel() {
        return radarModel;
    }

    public void setRadarModel(RadarChartModel radarModel) {
        this.radarModel = radarModel;
    }

    public RadarChartModel getRadarModel2() {
        return radarModel2;
    }

    public void setRadarModel2(RadarChartModel radarModel2) {
        this.radarModel2 = radarModel2;
    }

    public BubbleChartModel getBubbleModel() {
        return bubbleModel;
    }

    public void setBubbleModel(BubbleChartModel bubbleModel) {
        this.bubbleModel = bubbleModel;
    }

    public BarChartModel getMixedModel() {
        return mixedModel;
    }

    public void setMixedModel(BarChartModel mixedModel) {
        this.mixedModel = mixedModel;
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }

    public ScatterChartModel getScatterModel() {
        return scatterModel;
    }

    public void setScatterModel(ScatterChartModel scatterModel) {
        this.scatterModel = scatterModel;
    }

	public ServicioService getServicioserv() {
		return servicioserv;
	}

	public void setServicioserv(ServicioService servicioserv) {
		this.servicioserv = servicioserv;
	}

	public CargaService getCargaserv() {
		return cargaserv;
	}

	public void setCargaserv(CargaService cargaserv) {
		this.cargaserv = cargaserv;
	}
    
}
