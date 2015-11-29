/**
	Module regroupant les fonctions réutilisables
**/

module.exports = {
	/**
		Retourne un entier aléatoire en min et max inclut
		min : entier
		max : entier
	**/
	getRandom: function (min, max) {
		return Math.floor(Math.random() * (max - min + 1)) + min;
	},

	/**
		Retourne l'une des valeurs du tableau pages en fonction d'un entier aléatoire et des intervalles spécifiés par le tableau interval 
		(utilsé dans les versions futures pour abstraire les functions de choix aléatoire pour la page suivante)
		interval : tableau entier (interval.length = pages.length + 1) (entier deux à deux avec un décalage de 1 représente un interval)
		pages : tableau entier
	**/
	aleaChoice: function(interval, pages){
		var alea = getRandom(interval[0], interval[interval.length-1]);
		for(var i = 0; i < interval.length-1; i++) 
			if(alea >= interval[i] && alea <= interval[i-1])
				return pages[i].id;
	}
};
