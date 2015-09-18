var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.render('chapter', { chap: 0 });
});

/* Dynamic route for chapters 
*  :chap parameter can complete file name
*  ex : view chapter1, chapter2 -> 'chapter' + req.params.chap 
*/
router.get('/:chap', function(req, res, next) {

  res.render('pages/page_'+req.params.chap, { chap: req.params.chap });
});

 

module.exports = router;
