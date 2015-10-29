var express = require('express');
var router = express.Router();
var Joueur = require('../Joueur.js');
var fs = require('fs');

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.render('list_pages');
});

/* Dynamic route for chapters 
*  :chap parameter can complete file name
*  ex : view page_1, page_2 -> 'page_' + req.params.chap 
*/
router.get('/:chap', function(req, res, next) {
  var page = JSON.parse(fs.readFileSync('views/json/page_'+req.params.chap+'.json', 'utf8'));
  res.json(page);
});

router.get('/:chap/:section', function(req, res, next) {
  var page = 'pages/page_'+req.params.chap; 
  var option = { chap: req.params.chap, next_page: false };

  if(!req.session.player) {
    player = new Joueur();
    req.session.player = player;
  }

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
