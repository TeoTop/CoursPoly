var express = require('express');
var Joueur = require('../Joueur.js');
var Battle = require('../Battle.js');
var aleaChoice = require("../aleaChoice.js");
var router = express.Router();

/* Affiche la page d'accueil */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Accueil' });
});


/* Retourne un json contenant le resultat de la fontion associé à la page éxécuté et les intervales de la page. Le numéro de la page est récupéré depuis l'URL */
router.get('/choixAleatoire/:page', function(req, res, next) {
  //json de retour, initialisé avec l'erreur si la page ne fait pas partie de page ayant un choix aléatoire
  var retour = {choix: "Cette page ne contient pas de choix aléatoire", intervales: []};

  //on boucle sur les objets du tableau contant les pages à choix aléatoire pour trouver l'objet correspondant à la page
  for(var i = 0; i < aleaChoice.length; i++){
    if(aleaChoice[i].id == req.params.page){
      //on applique la fonction associé à l'objet/page
      retour.choix = aleaChoice[i].fun();

      //on copie les intervales de l'objet/page dans le json de retour
      retour.intervales = aleaChoice[i].intervals;
    }
  }

  // on retourne le resultat au format json
  res.json(retour);
});


/* Retourne un json contenant le résultat d'un round de combat. L'habilité du joueur et de l'ennemi sont récupéré depuis l'URL. */
router.get('/combat/:playerHab/:ennemyHab', function (req, res, next) {
    //création de l'objet Battle
    var battle = new Battle();

    //on set les habilités aux variables habilités de l'objet Battle
    battle.playerHabilities = req.params.playerHab;
    battle.ennemyHabilities = req.params.ennemyHab;

    //on fait tourner les combats (ici juste 1 round; à modifer au prochain TP) et on récupère l'objet BattleResult
    var result = battle.round();
    
    // on retourne le resultat au format json
    res.json(result);
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
    res.render('new_game', option);
    return;
  }

  // on crée le joueur et on set ses capacités et objets qu'il possède
  player = new Joueur();
  player.setUp(kais, equipments, master);

  //on stock le joueur dans la session
  req.session.player = player;
  
  //redirige vers la page 1 du jeu
  res.redirect('/chap/1/1');

});


/* Retourne le json correspondant à l'objet joueur stocker en session*/
router.get('/player', function(req, res, next) {
  res.json(req.session.player);
});


module.exports = router;