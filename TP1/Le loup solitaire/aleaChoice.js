var fs = require('fs');
var common_functions = require('./CommonFunctions');

module.exports = [
	{ 
		id: 134, 
	  	fun:function() {
			var alea = common_functions.getRandom(0,9);

			if(alea < 4) return 57;
			else if(alea < 7) return 188;
			else return 331;
		},
	  	intervals:
	  	[
	  		{interval: [0,3], next_page: 57},
	  		{interval: [4,6], next_page: 188},
	  		{interval: [7,9], next_page: 331}
	  	]
	},
	{ 
		id: 155, 
	  	fun:function() {
			var alea = common_functions.getRandom(-2,10);

			if(alea < 3) return 248;
			else return 191;
		},
	  	intervals:
	  	[
	  		{interval: [-2,2], next_page: 248},
	  		{interval: [3,10], next_page: 191}
	  	]
	},
	{ 
		id: 167, 
	  	fun:function() {
			var alea = common_functions.getRandom(0,9);

			if(alea < 7) return 85;
			else return 300;
		},
	  	intervals:
	  	[
	  		{interval: [0,6], next_page: 85},
	  		{interval: [7,9], next_page: 300}
	  	]
	},
	{ 
		id: 331, 
	  	fun:function() {
			var alea = common_functions.getRandom(0,9);

			if(alea < 5) return 62;
			else return 288;
		},
	  	intervals:
	  	[
	  		{interval: [0,4], next_page: 62},
	  		{interval: [5,9], next_page: 288}
	  	]
	}
]