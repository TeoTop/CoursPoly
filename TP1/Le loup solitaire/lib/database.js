/**
	Module regroupant les fonctions de la base de données
**/
var client = require('mongodb').MongoClient;
var ObjectId = require('mongodb').ObjectID;
var url = 'mongodb://group39:thegroup39@ds049864.mongolab.com:49864/lone_wolf_bd';

/**
	Auto-increment pour les ids simplifiés des joueurs et état de joueur. Trouve l'id dans count, augmente la sequence de 1 et retourne la séquence modifiée.
**/
function getNextSequence(db, name, callback) {
	db.collection('Count').findOneAndUpdate(
		{ _id: name },
		{ $inc: { seq: 1 } },
		{ 
			returnOriginal: false,
		  	upsert: true
		},
		function(err, ret){
			callback(ret.value.seq);
		}
	);
}

/**
	Créer un avancement (id passé en paramètre) et retourne l'objet result de mongoDB
**/
function insertStateDB(db, state, callback){
	db.collection('Player_State').insertOne(state, function(err,rep){ 
	    db.close();
	    if(err) callback(err); 
	    callback(rep);
	});
}

/**
	Supprime un avancement (id passé en paramètre) et retourne l'objet result de mongoDB
**/
function deleteStateDB(db, id, callback){
	db.collection('Player_State').deleteOne({playerId: id}, function(err,rep){ 
	    db.close();
	    if(err) callback(err); 
	    callback(rep);
	});
}


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
			
			getNextSequence(db, "playerId", function(seq){
				var tmpState = JSON.parse(JSON.stringify(player.state));
				tmpState.playerId = seq;
				
				var tmp = JSON.parse(JSON.stringify(player));
				delete tmp.state;
				tmp._id=seq;
				
				db.collection('Players').insertOne(tmp, function(err) {
					if(err) callback(err); 
					insertStateDB(db,tmpState,callback);
				});
			});
	    });
	},

	/**
		Récupérer tous les joueurs et renvoie un tableau d'objets player (json)
	**/
	getAllPlayer : function(callback) {
	    client.connect(url, function (err, db){
			if (err) return;
			db.collection('Players').find().toArray(function(err,players){ 
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
			db.collection('Players').find({_id: id}).limit(1).next(function(err,player){ 
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
			db.collection('Players').updateOne({_id: id}, {$set : updates}, function(err,rep){
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
			db.collection('Players').deleteOne({_id: id}, function(err,rep){ 
			    if(err) callback(err);
			    deleteStateDB(db, id, callback);
			});
	    });
	},

	/**
		Insérer un nouvel avancement sans joueur rattaché (auto increment _id, peut etre modifié par la suite pour utilisé ObjectID de mongoDB  
																								comme cookie -> index sur stateId(actuellement _id))
	**/
	insertState : function(state, callback) {
	    client.connect(url, function (err, db){
			if (err) return;
			insertStateDB(db, state, callback);			
	    });
	},

	/**
		Récupérer tous les avancements et renvoie un tableau d'objets state (json) (si on met en place un système de sauvegarde multiple pour chaque joueur)
	**/
	/*getAllState : function(callback) {
	    client.connect(url, function (err, db){
			if (err) return;
			db.collection('Players').find().toArray(function(err,players){ 
			    db.close();
			    if(err) callback(err); 
			    callback(players);
			});
	    });
	},*/

	/**
		Récupérer un avancement (id passé en paramètre) et retourne un objet state ({} si avancement non trouvé)
	**/
	getState : function(id,callback) {
	    client.connect(url, function (err, db){
			if (err) return;
			db.collection('Player_State').find({playerId: id}).limit(1).next(function(err,state){ 
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
			db.collection('Player_State').updateOne({playerId: id}, {$set : updates}, function(err,rep){
			    db.close();
			    if(err) callback(err); 
			    callback(rep);
			});
	    });
	},

	/**
		Supprime un avancement sans supprimer le joueur (id passé en paramètre) et retourne l'objet result de mongoDB 
		Peut etre transformé en reset plus tard
	**/
	/*deleteState : function(id,callback) {
	    client.connect(url, function (err, db){
			if (err) return;
			deleteStateDB(db, id, callback);
	    });
	}*/
};