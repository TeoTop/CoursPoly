module.exports = {
  getRandom: function (min, max) {
  	return Math.floor(Math.random() * (max - min + 1)) + min;
  },

  aleaChoice: function(interval, pages){
	var alea = getRandom(interval[0], interval[interval.length-1]);
	for(var i = 0; i < interval.length-1; i++) 
		if(alea > interval[i] && alea < interval[i-1])
			return pages[i].id;
	}
};
