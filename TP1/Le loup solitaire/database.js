/**
	Module regroupant les fonctions de la base de données
**/
var client = require('mongodb').MongoClient;
var ObjectId = require('mongodb').ObjectID;
var url = 'mongodb://group39:thegroup39@ds049864.mongolab.com:49864/lone_wolf_bd';

function getNextSequence(db, name, callback) {
	db.collection('Count').find({_id: name}).toArray(function(err,player){ 
		console.log(player);
	});

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



module.exports = {
	/**
		Insérer un joueur nouveau
	**/
	insert : function(player, callback) {
	    client.connect(url, function (err, db){
			if (err) return;
			
			getNextSequence(db, "playerId", function(seq){
				var tmp = player;
				tmp._id=seq;
				console.log(tmp);
				db.collection('Players').insertOne(tmp, function() {
					db.close();
					callback();
				});
			});
	    });
	},

	/**
		Récupérer tous les joueurs
	**/
	getAll : function(callback) {
	    client.connect(url, function (err, db){
			if (err) return;
			db.collection('Players').find().toArray(function(err,players){ 
			    db.close();
			    callback(players);
			});
	    });
	},

	/**
		Récupérer un joueur (id passé en paramètre)
	**/
	getPlayer : function(id,callback) {
	    client.connect(url, function (err, db){
			if (err) return;
			db.collection('Players').find({_id: id}).limit(1).next(function(err,player){ 
				db.close();
				(player) ? callback(player): callback({});
			});
	    });
	},

	/**
		Récupérer un joueur (id passé en paramètre)
	**/
	updatePlayer : function(id,updates,callback) {
	    client.connect(url, function (err, db){
			if (err) return;
			db.collection('Players').updateOne({_id: id}, {$set : updates}, function(err,rep){ 
			    db.close();
			    callback(rep);
			});
	    });
	},

	deletePlayer : function(id,callback) {
	    client.connect(url, function (err, db){
			if (err) return;
			db.collection('Players').deleteOne({_id: id}, function(err,rep){ 
			    db.close();
			    callback(rep);
			});
	    });
	}
};