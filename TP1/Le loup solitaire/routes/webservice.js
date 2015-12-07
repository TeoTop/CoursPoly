var express = require('express');
var Joueur = require('../lib/objets/Joueur');
var Battle = require('../lib/objets/Battle');
var aleaChoice = require("../lib/aleaChoice");
var common_functions = require('../lib/CommonFunctions');
var database = require('../lib/database');
var var_global = require('../lib/vars_global');
var fs = require('fs');
var router = express.Router();

/* Retourne un json contenant le resultat de la fontion associé à la page éxécuté et les intervales de la page. Le numéro de la page est récupéré depuis l'URL */
router.get('/choixAleatoire/:page', function (req, res, next) {
  //json de retour, initialisé avec l'erreur si la page ne fait pas partie de page ayant un choix aléatoire
  var retour = {choix: "Cette page ne contient pas de choix aléatoire", intervales: []};

  //on boucle sur les objets du tableau contant les pages à choix aléatoire pour trouver l'objet correspondant à la page
  for(var i = 0; i < aleaChoice.length; i++){
    if(aleaChoice[i].id == req.params.page){
      //on applique la fonction associé à l'objet/page
      retour.choix = common_functions.aleaChoice(aleaChoice[i].interval, aleaChoice[i].next_page);

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





/* Retourne un json contenant tous les player de la bd dans un tableau*/
router.get('/player', function (req, res, next) {
  if(!req.cookies.gameId){
    rep = {"error":true, "msg":"Error detect"};
    res.json(rep);
    return;
  }

  if(!req.session.player){
    player = new Joueur("");

    player.load(req.cookies.gameId, function (){
      req.session.player = player;
      res.json(req.session.player);
    });
  } else {
    res.json(req.session.player);
  }
});



router.get('/player/reset', function (req, res, next) {
  delete req.session.player;
  res.json({});
});




/* Retourne un json contenant tous les player de la bd dans un tableau*/
router.get('/players', function (req, res, next) {
  database.getAllPlayer(function (rep){
    res.json(rep);
  });
});




router.route('/players/:id')
.get(function (req, res, next) {
  /* Retourne un json contenant l'objet player de la bd en fonction de l'id */
  database.getPlayer(req.params.id, function (rep){
    res.json(rep);
  });
})
.put(function (req, res, next) {
  /* Modifie un joueur en fonction de l'id et du body. Retourne un json contenant l'objet result de mongodb */
  database.updatePlayer(req.params.id, req.body, function (rep){
    res.json(rep);
  });
})
.delete(function (req, res, next) {
  /* Supprime un joueur et son avancement en fonction de l'id et du body. Retourne un json contenant l'objet result de mongodb */
  database.deletePlayer(req.params.id, function (rep){
    if(req.session.player && req.params.id == req.session.player._id)
      delete req.session.player;
    res.json(rep);
  });
});




router.route('/states/:id')
.get(function (req, res, next) {
  /* Retourne un json contenant l'objet player_state de la bd en fonction de l'id (du joueur associé)*/
  database.getState(req.params.id, function (rep){
    res.json(rep);
  });
})
.put(function (req, res, next) {
  /* Modifie un avancement en fonction de l'id (du joueur associé) et du body. Retourne un json contenant l'objet result de mongodb */
  database.updateState(req.params.id, req.body, function (rep){
    res.json(rep);
  });
});


router.get('/page', function (req, res, next) {
  var file = 'views/json/page_error.json';

  if(req.session.player && req.session.player.state && req.session.player.state.currentPage)
    file = 'views/json/page_'+req.session.player.state.currentPage+'.json';
  
  var page = JSON.parse(fs.readFileSync(file, 'utf8'));
  res.json(page);
});




/* Route dynamique pour les chapitres 
*  :page : numéro de la page
*  Retourne le json de la page correspondante
*/
router.get('/page/:page', function (req, res, next) {
  var page = JSON.parse(fs.readFileSync('views/json/page_'+req.params.page+'.json', 'utf8'));
  res.json(page);
});


router.post('/player/save', function (req, res, next) {
  player = new Joueur("");
  player.loadFromSession(req.body);

  player.save(player, function (rep){
    req.session.player = player;
    console.log(req.session.player);
    res.json(rep);
  });
});


router.get('/saves/:ids', function (req, res, next) {
  var savesId = JSON.parse(req.params.ids);

  database.getSaves(savesId, function (rep){
    res.json(rep);
  });
});

module.exports = router;