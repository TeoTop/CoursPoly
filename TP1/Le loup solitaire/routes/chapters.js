var express = require('express');
var router = express.Router();
var Joueur = require('../Joueur.js');
var fs = require('fs');

/* Affiche la liste des pages (liste à compléter) */
router.get('/', function(req, res, next) {
  res.render('list_pages', { title: 'Liste des chapitres' });
});


/* Route dynamique pour les chapitres 
*  :page : numéro de la page
*  Retourne le json de la page correspondante
*/
router.get('/:page', function(req, res, next) {
  var page = JSON.parse(fs.readFileSync('views/json/page_'+req.params.page+'.json', 'utf8'));
  res.json(page);
});


/* Route dynamique pour les chapitres 
*  :page : numéro de la page
*  :section : correspond à la partie de la page à afficher (1 -> première partie, 2-> toute la page)
*  Retourne le jade de la page correspondante
*/
router.get('/:chap/:section', function(req, res, next) {

  //variable pour le rendu de la bonne page jade (page -> jade correspondant, option -> paramètres passés au jade)
  var page = 'pages/page_'+req.params.chap; 
  var option = { title: 'Chapitre -- '+req.params.chap, chap: req.params.chap, next_page: false };

  // Pour l'instant (test notamment), on créer une intance de joueur aléatoire que l'on garde en session si il n'en existe pas. 
  if(!req.session.player) {
    player = new Joueur();
    req.session.player = player;
  }

  //section 1 ou 2 sinon retourne une erreur 404. 2 met next_page à true.
  if(req.params.section == 2) {
    option.next_page = true;
  } else if(req.params.section != 1){
  	var wrongPath = new Error('This path cannot be use');
    res.status(404);
    next(wrongPath);
  	return;
  }

  res.locals.session = req.session;
  res.render(page, option);
  
});

module.exports = router;