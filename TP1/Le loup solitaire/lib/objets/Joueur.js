var database = require('../database');
var var_globals = require('../vars_global');
var common_functions = require('../commonFunctions');
var State = require('./State');

/**
	Objet JS Joueur: représente un joueur lors d'une partie.
	Contient les stats, les armes et objets du joueur tout au long de la partie
**/
function Joueur(name) {
	this.name = name;
	this.state = new State();

	this.lifeMax = common_functions.getRandom(19,19);
	this.life = this.lifeMax;
		
	this.dexter = common_functions.getRandom(10,19);
	this.dexterMaster = this.dexter + 2;
	this.master = -1;

	this.gold = common_functions.getRandom(10,19);
	this.goldMax = 50;
	
	this.kais = [];
	this.weapons = [];
	this.backpack = [];
	this.spe_object = [];
}

/**
	Initialise les stats du joueur lors d'une nouvelle partie
**/
Joueur.prototype.setUp = function(kais, equips, master) {
	if(equips.indexOf('WASTCOST') != -1) this.lifeMax+= 2;
	this.life = this.lifeMax

	//arme maitrisée
	this.master = master;

	//compétence de kai
	this.kais = kais;

	//équipement de départ
	for (var i = 0; i < equips.length; i++) {
		this.addEquip(equips[i]);
	}
}

/**
	Ajoute une arme, un objet spécial ou un objet à la liste correspondante. Vérifie le nombre arme/objet(spé) que possède déjà le joueur et 
	si il existe bien dans le jeu
**/
Joueur.prototype.addEquip = function(equip) {
	if(var_globals.weapons.hasOwnProperty(equip) && !this.weaponsFull()){
		this.weapons.push(equip);
  	} else if(var_globals.spe_object.hasOwnProperty(equip)  && !this.objectFull()){
		this.spe_object.push(equip);
  	} else if(var_globals.backpack.hasOwnProperty(equip)  && !this.backPackFull()){
  		this.backpack.push(equip);
  	}
}

//Définie la taille max des armes que peut posséder le joueur (2)
Joueur.prototype.weaponsFull = function() {
	return (this.weapons.length >= 2) ? true : false;
}

//Définie la taille max des objets que peut posséder le joueur (8)
Joueur.prototype.backPackFull = function() {
	return (this.backpack.length >= 8) ? true : false;
}

//Définie la taille max des objets spéciaux que peut posséder le joueur (8)
Joueur.prototype.objectFull = function() {
	return (this.spe_object.length >= 8) ? true : false;
}

Joueur.prototype.load = function(gameId, callback) {
	var tmp = this;
	database.getPlayer(gameId, function(rep){
		for(var k in rep) tmp[k]=rep[k];
		tmp.state.load(gameId, callback);
    });
}

module.exports = Joueur;