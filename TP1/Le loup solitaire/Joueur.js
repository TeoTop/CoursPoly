var var_globals = require('./vars_global');

function getRandom(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

function Joueur() {
	this.lifeMax = getRandom(19,19);
	this.life = this.lifeMax;
		
	this.dexter = getRandom(10,19);
	this.dexterMaster = this.dexter + 2;
	this.master = -1;

	this.gold = getRandom(10,19);
	this.goldMax = 50;
	
	this.kais = [];
	this.weapons = [];
	this.backpack = [];
	this.spe_object = [];
}

Joueur.prototype.setUp = function(kais, equips, master) {
	if(equips.indexOf('WASTCOST') != -1) this.lifeMax+= 2;
	this.life = this.lifeMax

	this.master = master;

	this.kais = kais;

	for (var i = 0; i < equips.length; i++) {
		this.addEquip(equips[i]);
	}
}

Joueur.prototype.addEquip = function(equip) {
	if(var_globals.weapons.hasOwnProperty(equip) && !this.weaponsFull()){
		this.weapons.push(equip);
  	} else if(var_globals.spe_object.hasOwnProperty(equip)  && !this.weaponsFull()){
		this.spe_object.push(equip);
  	} else if(var_globals.backpack.hasOwnProperty(equip)  && !this.weaponsFull()){
  		this.backpack.push(equip);
  	}
}

Joueur.prototype.weaponsFull = function() {
	return (this.weapons.length >= 2) ? true : false;
}

Joueur.prototype.backPackFull = function() {
	return (this.backpack.length >= 8) ? true : false;
}

Joueur.prototype.objectFull = function() {
	return (this.spe_object.length >= 8) ? true : false;
}



module.exports = Joueur;
