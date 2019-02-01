var express = require('express')
var app = express()
const Influx = require('influx');
var Parse = require('parse/node');
var cors = require('cors')
var url = require('url')
var fs = require('fs');
var http = require('http');
//const Influx = require('../../')
const os = require('os')

//////////////////
//Begining
//////////////////

//creation de l'object

//*
const influx = new Influx.InfluxDB({
  host: 'localhost',
  database: 'express_response_db',
  schema: [
    {
      measurement: 'pression',
      fields: {
        valeur: Influx.FieldType.INTEGER,
        timey: Influx.FieldType.STRING,
      },
      tags: [
        'host'
      ]
    }
  ]
})


influx.getDatabaseNames()
.then(names => {
  if (!names.includes('express_response_db')) {
    return influx.createDatabase('express_response_db');
  }
})
.then(() => {
  http.createServer(app).listen(3004, function () {
    console.log('Listening on port 3001')
  })
})
.catch(err => {
  console.error(`Error creating Influx database!`);
})
//*/


//////////////////////////////////////////////////////////////////////
//fin d'init
//////////////////////////////////////////////////////////////////////


app.use(cors())
var injections = [];
var i =1;
var debut = new Date().getTime();


//////////////////////////////////////////////////////////////////////


app.get('/', function (req, res) {
  var query = url.parse(req.url,true);

  if  ( query.query['requestType'] == 'getdata' ) {

	  setTimeout(function () {
      //read the file of local data, only for testing
      //*
      fs.readFile('min.txt', {encoding: 'utf-8'}, function(err,data){
        if (!err) {
          var allLines = data.split('\n');
          Line = allLines[i].split('\t');

          var d  = Line[1]
          d  = d.replace(',', '.');

          var t  = Line[0]
          t  = t.replace(',', '.');

          //temps moche
          console.log(t)

          //décalag pour ce jeux de donnée uniquement
          t  =debut+((t-6000)*1000)

          var d2 = parseFloat(d)				
          var t2 = parseFloat(t)				

          /////////////////////////////////////////////////////////
          //ecriture dans la base de donnée, uniquement pour mes test

            //console.log("write :"+d2)
          
          //////////////////////////////////////////////////////
          //lecture dans le DB
         
         console.log("durée :");
         console.log(debut -new Date().getTime());
         //console.log(row, row.value),
          ///////////////////
          influx.query(` select * from pression order by time desc limit 1 `)
          .then(rows => {
            rows.forEach(row => {console.log("data: "+row),
            res.send({time: row.timey, int : row.value})})
          }).catch(function (err) {
            console.log("Promise Rejected: ", err);
        });
          //console.log(d3)
          //console.log(t3)
          //res.send({time: t2, int : d2})
          ///////////////////
        } else {
          console.log(err);
        }});
 
        //*/
      i++;

      }, 1);
  }
  /*else{
    console.log("Request for " + query.pathname + " received.");

    res.writeHead(200);

    if(query.pathname == "/h") {
        html = fs.readFileSync("affichage.html", "utf8");
        res.write(html);
    }if (query.pathname == "/graph.js") {
        script = fs.readFileSync("graph.js", "utf8");
        res.write(script);
    }

  res.end();
  }
  //*/
})

//////////////////////////////////////////////////////

  //istantiation si besoin 
	influx.getDatabaseNames()
  .then(names => {
    if (!names.includes('express_response_db')) {
      return influx.createDatabase('express_response_db');
    }
  })
  .catch(error => console.log({ error }))
.then(() => {
    http.createServer(app).listen(3001, function () {
      console.log('Listening on port 3001')
    })
  })

app.listen(3000)