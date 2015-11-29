var var_globals = require('../vars_global');
var common_functions = require('../commonFunctions');
var Battle = require('./Battle');

/**
	Objet JS Joueur: représente un joueur lors d'une partie.
	Contient les stats, les armes et objets du joueur tout au long de la partie
**/
function State() {
	this.currentPage = 1;
	this.pastPages = [];
	this.battles = [];
	this.alea_choices = [ {"page":999, "choices":[1,3]} ];
}

/**
	Modifie la page courante du joueur et ajoute l'ancienne page à pastPages
**/
State.prototype.changePage = function(page) {
	this.pastPages.push(this.currentPage);
	this.currentPage = page;
}

//Ajoute un nouveau combat (objet Battle) à l'historique des combats
State.prototype.addCombat = function(battle) {
	this.battles.push(battle);
}

//Ajoute un nouveau choix fait par le joueur ou un choix aléatoire ({"page": id de la page qui contient le choix, "choices":[checkbox1, checkbox3|val alea]})
State.prototype.addChoice = function(choice) {
	this.alea_choices.push(choice);
}

module.exports = State;