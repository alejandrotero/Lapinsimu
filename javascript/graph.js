var chartData = [];

var debut = new Date().getTime();
    // Themes begin
    am4core.useTheme(am4themes_animated);    
    
    // Create chart instance
    var chart = am4core.create("chartdiv1", am4charts.XYChart);
    //chart.initialData(data);
 
    chart.data = chartData;

    // Create axes
    var dateAxis = chart.xAxes.push(new am4charts.DateAxis());
    dateAxis.renderer.minGridDistance = 50;

    var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
            
    // Create series
    var series = chart.series.push(new am4charts.LineSeries());
        series.dataFields.valueY = "Pression";
        series.dataFields.dateX = "date";
        series.strokeWidth = 2;
        series.ITTimeInteral = { timeUnit: "miliseconde", count: 5 }
        series.tooltipText = "Pression, temps: [bold]{valueY, valueX}[/]";
            

    // Add scrollbar
    chart.scrollbarX = new am4charts.XYChartScrollbar();
    chart.scrollbarX.series.push(series);
            
    // Add cursor
    chart.cursor = new am4charts.XYCursor();
            
    // Pre-zoom the chart
    chart.events.on("inited", function(ev) {
    //dateAxis.zoomToDates(chart.data[chart.data.length - 40].date, chart.data[chart.data.length - 1].date);
    });
    //*/

    var now = new Date();

    // get db object
    influxdb = new InfluxDB({
        "host" :"localhost",
        "port" :"8086",
        "username" :"root",
        "password" :"root",
        "database" :"test"
      });
    

    //
var d = 0;
function dataGenerator() {
            
    var timeout = Math.round(50);
    setTimeout(function() {

        // create new data item
        var now = new Date();

        function getdata(callback){
        var requestURL = 'http://localhost:3000/?requestType=getdata';
        var request = new XMLHttpRequest();
        request.open('GET', requestURL);
        request.responseType = 'json';
        request.send();
        var pressionA;

        var PressionA2 = influxdb.readPoint('value', 'pression')
        console.log("pressionA2");
        console.log(PressionA2);
                
                
        request.onload = function() {
            pressionA  = request.response.int;
            console.log("pressionA");
            console.log(pressionA);

            time1  = request.response.time;
            console.log("time");
            //console.log(time1*1000);

            var time2 = new Date(time1);
            console.log(time1) 

            //console.log(pressionA.int);

            callback(time1, pressionA)
        }
    }

        //pressionA
        function mainboucle(time,valeur){   
            console.log("valeur");                 
            console.log(valeur);  
            console.log(time);                 
               
            chart.addData(
            { date:time, Pression: valeur }); //now.getTime()
        }

        getdata(mainboucle);
        // do forever
        dataGenerator();

    }, timeout);
}

        // start the data generator
        dataGenerator();