var express = require('express');
var Joueur = require('../lib/objets/Joueur');
var database = require('../lib/database');
var router = express.Router();


/* Affiche la page d'accueil */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Accueil' });
});


/* Affiche la page d'aide */
router.get('/help', function(req, res, next) {
  res.render('help', { title: 'Guide aide' });
});


/* Affiche la page de nouvelle partie */
router.get('/new_game', function(req, res, next) {
  res.render('new_game', { title: 'Nouvelle partie' });
});


/* Récupère le formulaire de la page nouvelle partie, vérifie les valeurs fournis par le joueur, retourne vers la page nouvelle avec des erreurs si il y a des données
 invalides, ou redirige vers la page /chap/1/1 si les données sont valides. Créer un session pour le joueur.*/
router.post('/new_game', function(req, res, next) {
  //objet passé en parametre de l'affichage de nouvelle partie en cas d'erreur
  var option = {title: 'Nouvelle partie', err: false}

  //récupération des valeurs du formulaire
  var name = req.body.name;
  var kais = req.body.kais;
  var equipments = req.body.equipments;
  var master = req.body.master;

  //regex pour le nom
  var regex = /^\w+$/;

  //futur instanciation du joueur (stocker en session)
  var player;

  //vérification du nom : nom non null ou undefine et respecte le format (un mot => [caratère ou chiffre ou '_']{1..*})
  if(!name || !regex.test(name)){
  	option.err=true;
    option.msgName="Le nom est vide. Un nom ne peut contenir que des lettres, des chiffres et le caratère spécial '_'!";
  }

  //vérification des compétences de kai : 5 compétences sélectionnées et appartenant aux compétences de kai autorisées
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

  //vérification des armes ou objets : 2 armes ou objets sélectionnés et appartenant aux armes et objets autorisés
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

  // si une ou plusieurs données sont invalides, on retourne la page nouvelle partie avec les erreurs
  if(option.err){
    var data = {ok: false, option: option}
    res.json(data);
    return;
  }

  // on crée le joueur et on set ses capacités et objets qu'il possède
  player = new Joueur(name);
  player.setUp(kais, equipments, master);

  //on stock le joueur dans la session et la bd (stock avancement aussi)
  req.session.player = player;
  database.insertPlayer(player, function(){
    //redirige vers la page 1 du jeu
    var data = {ok: true}
    res.json(data);
  });

  //res.render('new_game', option);
});

module.exports = router;