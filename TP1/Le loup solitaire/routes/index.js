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
    
    res.json(result);
});

router.post('/new_game', function(req, res, next) {
  var option = {title: 'Nouvelle partie', err: false}
  var name = req.body.name;
  var kais = req.body.kais;
  var equipments = req.body.equipments;
  var master = req.body.master;
  var regex = /^\w+$/;
  var player;

  if(!name || !regex.test(name)){
  	option.err=true;
    option.msgName="Le nom est vide. Un nom ne peut contenir que des lettres, des chiffres et le caratère spécial '_'!";
  }

  if(kais && kais.length == 5){
  	kais.forEach(function(kai){
  		if(!req.app.locals.kais.hasOwnProperty(kai)){
  			option.err=true;
        option.msgKai="Seul les compétences proposées dans le formulaire ne peuvent être sélectionnées!";
  	  }
  	});
  } else {
  	option.err=true;
    option.msgKai="Le nombre de compétences de Kai sélectionnées doit être exactement 5!";
  }


  if(equipments && equipments.length == 2){
  	equipments.forEach(function(equip){
  		if(!req.app.locals.equipments.hasOwnProperty(equip)){
  			option.err=true;
        option.msgWeapon="Seul les armes et objets proposés dans le formulaire ne peuvent être sélectionnés!";
  	  }
  	});
  } else {
  	option.err=true;
    option.msgWeapon="Le nombre d'armes ou objets sélectionnés doit être exactement 2!";
  }

  if(option.err){
    res.render('new_game', option);
    return;
  }

  player = new Joueur();
  player.setUp(kais, equipments, master);
  req.session.player = player;
  
  res.redirect('/chap/1/1');

});

router.get('/player', function(req, res, next) {
  res.json(req.session.player);
});

module.exports = router;
