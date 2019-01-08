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

const influx = new Influx.InfluxDB({
  host: 'localhost',
  database: 'express_response_db',
  schema: [
    {
      measurement: 'pression',
      fields: {
        path: Influx.FieldType.STRING,
        value: Influx.FieldType.INTEGER
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
  http.createServer(app).listen(3001, function () {
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



app.get('/', function (req, res) {
  var query = url.parse(req.url,true);

  if  ( query.query['requestType'] == 'getdata' ) {
	setTimeout(function () {
		fs.readFile('min.txt', {encoding: 'utf-8'}, function(err,data){
			if (!err) {
				var allLines = data.split('\n');
				//console.log(typeof(data))
				Line = allLines[i].split('\t');

				var d  = Line[1]
				d  = d.replace(',', '.');


				var t  = Line[0]
				t  = t.replace(',', '.');

				console.log(t)

				t  =debut+((t-5998)*1000)

				var d2 = parseFloat(d)				
				var t2 = parseFloat(t)				

				///////////////////
				//test db ecriture

				//const duration = Date.now() - debut
      //console.log(`Request to ${req.path} took ${duration}ms`)
	
			
				influx.writePoints([
					{
						measurement: 'response_times',
						tags: { host: os.hostname() },
						fields: { t2, path: req.path }
					}
				]).catch(err => {
					console.error(`Error saving data to InfluxDB! ${err.stack}`)
        })
        var v =influx.query(`
      select * from response_times
      where host = ${Influx.escape.stringLit(os.hostname())}
      order by time desc
      limit 10
    `)
    console.log(v)
				///////////////////


				console.log("send: ")
				console.log(d2)
				console.log(t2)
				res.send({time: t2, int : d2})
				//res.send({int:50})
			} else {
			console.log(err);
			}});
			i++;
    }, 50);
  }
})


//////////////////

/*  
  //istantiation si besoin 
	influx.getDatabaseNames()
  .then(names => {
    if (!names.includes('express_response_db')) {
      return influx.createDatabase('express_response_db');
    }
  })
  .catch(error => console.log({ error }));
/*.then(() => {
    http.createServer(app).listen(3001, function () {
      console.log('Listening on port 3001')
    })
  })*/

app.listen(3000)