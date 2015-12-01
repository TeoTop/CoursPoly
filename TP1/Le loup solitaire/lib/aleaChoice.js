/**
	Objet js qui stock la liste des pages qui doivent appliquer une méthode élatoire pour déterminer la suite de l'histoire
	id : numero de la page
	fun : fonction à appliquer -> retourne le numéro de la page suivante en fonction d'un nombre aléatoire
	intervals : liste des intervales et de la page associée
**/
module.exports = [
	{ 
		id: 134, 
		interval: [0,3,6,9],
	  	next_page: [57, 188, 331],
	  	intervals:
	  	[
	  		{interval: [0,3], next_page: 57},
	  		{interval: [4,6], next_page: 188},
	  		{interval: [7,9], next_page: 331}
	  	]
	},
	{ 
		id: 155, 
	  	interval: [-2,2,10],
	  	next_page: [248, 191],
	  	intervals:
	  	[
	  		{interval: [-2,2], next_page: 248},
	  		{interval: [3,10], next_page: 191}
	  	]
	},
	{ 
		id: 167, 
	  	interval: [0,6,9],
	  	next_page: [85, 300],
	  	intervals:
	  	[
	  		{interval: [0,6], next_page: 85},
	  		{interval: [7,9], next_page: 300}
	  	]
	},
	{ 
		id: 331, 
	  	interval: [0,4,9],
	  	next_page: [62, 288],
	  	intervals:
	  	[
	  		{interval: [0,4], next_page: 62},
	  		{interval: [5,9], next_page: 288}
	  	]
	}
]