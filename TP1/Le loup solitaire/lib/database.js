/**
	Module regroupant les fonctions de la base de données
**/
var client = require('mongodb').MongoClient;
var ObjectId = require('mongodb').ObjectID;
var url = 'mongodb://group39:thegroup39@ds049864.mongolab.com:49864/lone_wolf_bd';


/**
	Si une erreur se produit dans la base de données, l'objet MongoError est retourné
**/
module.exports = {
	/**
		Insérer un nouveau joueur (auto increment _id, peut etre modifié par la suite pour utilisé ObjectID de mongoDB comme cookie 
																									-> index sur playerId(actuellement _id))
	**/
	insertPlayer : function(player, callback) {
	    client.connect(url, function (err, db){
			if (err) return;

			db.collection('PlayersV2').insertOne(player, function(err, rep) {
				if(err) callback(err); 
				player._id = rep.insertedId;
				callback(rep);
			});
	    });
	},

	/**
		Récupérer tous les joueurs et renvoie un tableau d'objets player (json)
	**/
	getAllPlayer : function(callback) {
	    client.connect(url, function (err, db){
			if (err) return;
			db.collection('PlayersV2').find().toArray(function(err,players){ 
			    db.close();
			    if(err) callback(err); 
			    callback(players);
			});
	    });
	},

	/**
		Récupérer un joueur (id passé en paramètre) et retourne un objet player ({} si joueur non trouvé)
	**/
	getPlayer : function(id,callback) {
	    client.connect(url, function (err, db){
			if (err) return;
			
			var selId = ObjectId(id);

			db.collection('PlayersV2').find({_id: selId}).limit(1).next(function(err,player){ 
				db.close();
				if(err) callback(err); 
				(player) ? callback(player): callback({});
			});
	    });
	},

	/**
		Modifie les attributs d'un joueur (id passé en paramètre) selon les paramètres de l'objet updates et retourne l'objet result de mongoDB
	**/
	updatePlayer : function(id,updates,callback) {
	    client.connect(url, function (err, db){
			if (err) return;

			var selId = ObjectId(id);

			db.collection('PlayersV2').updateOne({_id: selId}, {$set : updates}, {upsert:true},function(err,rep){
			    db.close();
			    if(err) callback(err); 
			    callback(rep);
			});
	    });
	},

	/**
		Supprime un joueur et son avancement (id passé en paramètre) et retourne l'objet result de mongoDB
	**/
	deletePlayer : function(id,callback) {
	    client.connect(url, function (err, db){
			if (err) return;

			var selId = ObjectId(id);

			db.collection('PlayersV2').deleteOne({_id: selId}, function(err,rep){ 
			    if(err) callback(err);
			    callback(rep);
			});
	    });
	},


	/**
		Récupérer un avancement (id passé en paramètre) et retourne un objet state ({} si avancement non trouvé)
	**/
	getState : function(id,callback) {
	    client.connect(url, function (err, db){
			if (err) return;

			var selId = ObjectId(id);

			db.collection('PlayersV2').find({_id: selId}).project({ state: 1 }).limit(1).next(function(err,state){ 
				db.close();
				if(err) callback(err); 
				(state) ? callback(state): callback({});
			});
	    });
	},

	/**
		Modifie les attributs d'un avancement (id passé en paramètre) selon les paramètres de l'objet updates et retourne l'objet result de mongoDB
	**/
	updateState : function(id,updates,callback) {
	    client.connect(url, function (err, db){
			if (err) return;

			var selId = ObjectId(id);
			console.log(updates);
			db.collection('PlayersV2').updateOne({_id: selId}, {$set : updates}, {upsert:true}, function(err,rep){
			    db.close();
			    console.log(rep);
			    if(err) callback(err); 
			    callback(rep);
			});
	    });
	},


	/**
		Récupérer tous les parties passé en paramètre est retroune des info (nom, chapitre courrant et id de la partie)
	**/
	getSaves : function(savesId, callback) {
	    client.connect(url, function (err, db){
			if (err) return;

			var dbIds = savesId.map(function (id){
				return ObjectId(id);
			});

			db.collection('PlayersV2').find({_id: { $in: dbIds} }).project({name:1, "state.currentPage":1 }).toArray(function(err,players){ 
			    db.close();
			    if(err) callback(err); 
			    callback(players);
			});
	    });
	},
};