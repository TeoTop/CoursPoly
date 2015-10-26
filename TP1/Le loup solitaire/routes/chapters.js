var express = require('express');
var router = express.Router();
var Joueur = require('../Joueur.js');

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.render('list_pages');
});

/* Dynamic route for chapters 
*  :chap parameter can complete file name
*  ex : view page_1, page_2 -> 'page_' + req.params.chap 
*/
router.get('/:chap', function(req, res, next) {
  if(!req.session.player) {
    player = new Joueur();
    req.session.player = player;
  }
  res.locals.session = req.session;
  res.render('pages/page_'+req.params.chap, { chap: req.params.chap });
});

router.get('/:chap/:section', function(req, res, next) {
  var page = 'pages/page_'+req.params.chap; 
  var option = { chap: req.params.chap };

  if(!req.session.player) {
    player = new Joueur();
    req.session.player = player;
  }

  if(req.params.section == 2) {
    page += '_2'
  } else if(req.params.section != 1){
  	var wrongPath = new Error('This path canno\'t be use');
  	return next(notFound);
  }

  res.locals.session = req.session;
  res.render(, option);
  
});

 

module.exports = router;
