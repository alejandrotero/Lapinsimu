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
  database: 'PressionA',
  schema: [
    {
      measurement: 'pression',
      fields: {
        valeur: Influx.FieldType.INTEGER,
        timey: Influx.FieldType.STRING,
        frequenceR=Influx.FieldType.INTEGER,
        urine=Influx.FieldType.INTEGER
      },
      tags: [
        'host'
      ]
    }
  ],
})
const influx2 = new Influx.InfluxDB({
  host: 'localhost',
  database: 'event',
  schema: [
    {
      measurement: 'event',
      fields: {
        valeur: Influx.FieldType.STRING,
        timey: Influx.FieldType.STRING,
      },
      tags: [
        'host'
      ]
    }
  ],
})

/*
database: 'event',
  schema: [
    {
      measurement: 'event',
      fields: {
        valeur: Influx.FieldType.INTEGER,
        timey: Influx.FieldType.STRING,
      },
      tags: [
        'host'
      ]
    }
  ]
*/

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
      /*
        fs.readFile('min.txt', {encoding: 'utf-8'}, function(err,data){
        if (!err) {
          var allLines = data.split('\n');
          Line = allLines[i].split('\t');

          var d  = Line[1]
          d  = d.replace(',', '.');

          var t  = Line[0]
          t  = t.replace(',', '.');

          //temps moche
          console.log("time 1 ")

          console.log(t)

          //décalag pour ce jeux de donnée uniquement
          t  =debut+((t-6000)*1000)

          var d2 = parseFloat(d)				
          var t2 = parseFloat(t)	

          influx.writePoints([
            {
              measurement: 'pression',
              fields: { valeur: d2, timey: t2 },
            }
          ]);

        
          //console.log(d3)
          //console.log(t3)
          //res.send({time: t2, int : d2})
          ///////////////////
        } else {
          console.log(err);
        }});
        //*/
        //////////////////////////////////////////////////////
        //lecture dans la DB
        console.log("durée :");
        console.log(debut -new Date().getTime());
        //console.log(row, row.value),
         ///////////////////
         influx.query(` select valeur timey from pression order by time desc limit 1 `)
         .then(rows => {
           rows.forEach(row => {console.log("data: "+row.valeur+","+row.timey),
           res.send({time: row.timey, int : row.valeur})})
         }).catch(function (err) {
           console.log("Promise Rejected: ", err);
       });
        //////////////////////////////

        influx2.writePoints([
          {
            measurement: 'event',
            fields: { valeur: "isAlived", timey: "0" },
          }
        ]);
        /*
        console.log("et donc?")
        influx2.query(` select * from event order by time desc limit 1 `)
         .then(rows => {
           rows.forEach(row => {console.log("data: "+row.valeur+","+row.timey)//,
           //res.send({time: row.timey, int : row.valeur})
          })
         }).catch(function (err) {
           console.log("Promise Rejected: ", err);
       });
       */
      i++;
    }, 5);
  }

})

//////////////////////////////////////////////////////


app.listen(3000)