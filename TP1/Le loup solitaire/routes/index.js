var express = require('express');
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

router.post('/new_game_post', function(req, res, next) {
  console.log(req.param)
  res.redirect('/chap/1');
});

module.exports = router;
