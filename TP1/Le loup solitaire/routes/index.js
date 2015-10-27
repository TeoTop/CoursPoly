var express = require('express');
var Joueur = require('../Joueur.js');
var Battle = require('../Battle.js');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index');
});

router.get('/new_game', function(req, res, next) {
  res.render('new_game', { title: 'Nouvelle partie' });
});

router.get('/help', function(req, res, next) {
  res.render('help', { title: 'Guide aide' });
});

router.get('/combat/:playerHab/:ennemyHab', function (req, res, next) {
    var battle = new Battle();
    battle.playerHabilities = req.params.playerHab;
    battle.ennemyHabilities = req.params.ennemyHab;

    var result = battle.fight();
    
    res.json({ result })
});

router.post('/new_game', function(req, res, next) {
  var name = req.body.name;
  var kais = req.body.kais;
  var equipments = req.body.equipments;
  var master = req.body.master;
  var regex = /^\w+$/;
  var player;

  if(!name || !regex.test(name)){
  	res.render('new_game', { title: 'Nouvelle partie' });
  	return;
  }

  if(kais && kais.length == 5){
  	kais.forEach(function(kai){
  		if(!req.app.locals.kais.hasOwnProperty(kai)){
  			res.render('new_game', { title: 'Nouvelle partie' });
  			return;
  	  }
  	});
  } else {
  	res.render('new_game', { title: 'Nouvelle partie' });
  	return;
  }


  if(equipments && equipments.length == 2){
  	equipments.forEach(function(equip){
  		if(!req.app.locals.equipments.hasOwnProperty(equip)){
  			res.render('new_game', { title: 'Nouvelle partie' });
  			return;
  	  }
  	});
  } else {
  	res.render('new_game', { title: 'Nouvelle partie' });
  	return;
  }

  player = new Joueur();
  player.setUp(kais, equipments, master);
  req.session.player = player;
  
  res.redirect('/chap/1');

});

module.exports = router;
