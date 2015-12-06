var database = require('../database');
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
	this.alea_choices = [];
}

State.prototype.saveDB = function (id){
	var state = {
		state:{
			currentPage: this.currentPage,
			pastPages: this.pastPages,
			battles: this.battles,
			alea_choices: this.alea_choices
		}
	}

	database.updateState(id, state, function (rep){});
}

/**
	Modifie la page courante du joueur et ajoute l'ancienne page à pastPages
**/
State.prototype.changePage = function (id, page) {
	this.pastPages.push(this.currentPage);
	this.currentPage = page;
	this.saveDB(id);
}

//Ajoute un nouveau combat (objet Battle) à l'historique des combats
State.prototype.addCombat = function (battle) {
	this.battles.push(battle);
}

//Ajoute un nouveau choix fait par le joueur ou un choix aléatoire ({"page": id de la page qui contient le choix, "choices":[checkbox1, checkbox3|val alea]})
State.prototype.addChoice = function (choice) {
	this.alea_choices.push(choice);
}

State.prototype.load = function (state) {
	for(var k in state) 
		this[k]=state[k];
}

State.prototype.loadFromId = function (gameId, callback) {
	var tmp = this;

	database.getState(gameId, function (rep){
		for(var k in rep) 
			tmp[k]=rep[k];

		callback(rep);
    });
}




module.exports = State;