var express = require('express');
var Battle = require('../lib/objets/Battle');
var aleaChoice = require("../lib/aleaChoice");
var database = require('../lib/database');
var router = express.Router();

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


/* Retourne un json contenant tous les player de la bd dans un tableau*/
router.get('/players', function(req, res, next) {
  database.getAllPlayer(function(rep){
    res.json(rep);
  });
});


router.route('/players/:id')
.get(function(req, res, next) {
  /* Retourne un json contenant l'objet player de la bd en fonction de l'id */
  database.getPlayer(parseInt(req.params.id), function(rep){
    res.json(rep);
  });
})
.put(function(req, res, next) {
  /* Modifie un joueur en fonction de l'id et du body. Retourne un json contenant l'objet result de mongodb */
  database.updatePlayer(parseInt(req.params.id), req.body, function(rep){
    res.json(rep);
  });
})
.delete(function(req, res, next) {
  /* Supprime un joueur et son avancement en fonction de l'id et du body. Retourne un json contenant l'objet result de mongodb */
  database.deletePlayer(parseInt(req.params.id), function(rep){
    res.json(rep);
  });
});

router.route('/states/:id')
.get(function(req, res, next) {
  /* Retourne un json contenant l'objet palyer_state de la bd en fonction de l'id (du joueur associé)*/
  database.getState(parseInt(req.params.id), function(rep){
    res.json(rep);
  });
})
.put(function(req, res, next) {
  /* Modifie un avancement en fonction de l'id (du joueur associé) et du body. Retourne un json contenant l'objet result de mongodb */
  database.updateState(parseInt(req.params.id), req.body, function(rep){
    res.json(rep);
  });
})
.delete(function(req, res, next) {
  /* Supprime un joueur et son avancement en fonction de l'id (du joueur associé) et du body. Retourne un json contenant l'objet result de mongodb */
  database.deletePlayer(parseInt(req.params.id), function(rep){
    res.json(rep);
  });
});


module.exports = router;