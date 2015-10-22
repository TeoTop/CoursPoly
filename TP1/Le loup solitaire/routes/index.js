var express = require('express');
var methodes = require('../methodes');
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

router.post('/new_game', function(req, res, next) {
  var name = req.body.name;
  var kais = req.body.kais;
  var equipments = req.body.equipments;
  var regex = /^\w+$/;

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

  req.app.locals.life = methodes.getRandom(10,19);
  req.app.locals.dexter = methodes.getRandom(20,29);
  req.app.locals.gold = methodes.getRandom(10,19);

  if()

  res.redirect('/chap/1');

});

module.exports = router;
