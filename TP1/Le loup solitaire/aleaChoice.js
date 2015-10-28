var fs = require('fs');
var common_functions = require('./CommonFunctions');
var page_167 = JSON.parse(fs.readFileSync('views/json/page_167.json', 'utf8'));

module.exports = [
	{ 
		id: page_167.id, 
	  	fun:function() {
			var alea = common_functions.getRandom(0,9);

			if(alea < 7) return 85;
			else return 300;
		},
	  	intervals:
	  	[
	  		{interval: [0,6], next_page: page_167.next_page[0].id},
	  		{interval: [7,9], next_page: page_167.next_page[1].id}
	  	]
	}
]