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

    //
var d = 0;
function dataGenerator() {
            
    var timeout = Math.round(1);
    setTimeout(function() { // callback call every timeout miliseconds 

        // create new data item
        var now = new Date();

        function getdata(callback){
            var requestURL = 'http://localhost:3000/?requestType=getdata';
            var request = new XMLHttpRequest();
            request.open('GET', requestURL);
            request.responseType = 'json';
            request.send();
            var pressionA;
                    
            request.onload = function() {
                pressionA  = request.response.int;
                console.log("pressionA");
                console.log(pressionA);

                time1  = request.response.time;
                console.log("time");
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
            console.log(parseInt(time));                 
               
            chart.addData(
            { date:parseInt(time), Pression: valeur }); //now.getTime()
        }

        //ici il est necessaire de faire un modèle de callback car on ne connait pas 
        // les temps de réponses de la DB ni le pemps d'execution de l'affichage
        getdata(mainboucle);

        // do forever
        dataGenerator();

    }, timeout);
}

//la fonction du bouton, ne marche pas encore
function injectionA(){
    console.log("fct adrenaline");                 

    var http = new XMLHttpRequest();
    var url = 'localhost:8086';
    var params = 'write?db=eventA&event,nom=adrenaline timey=10';
    http.open('POST', url, true);

    //Send the proper header information along with the request
    //http.setRequestHeader('data-binary', 'application/x-www-form-urlencoded');//?
    
    http.onreadystatechange = function() {//Call a function when the state changes.
        console.log("adrenaline2");
        console.log(http.readyState);
        console.log(http.status);

        if(http.readyState == XMLHttpRequest.DONE && http.status == 204) {
            //alert(http.responseText);
            console.log("adrenaline");

        }
    }
    http.send(params);
}

// start the data generator
dataGenerator();