angular.module('loneWolf',[]).controller('JoueurCtrl', function() {
  this.name = "TEst";
  this.kai = [];
  this.weapon = [];

  this.addKai = function(l, e){
    if(e == "kai") 
      this.kai.push(e);
    else if (e == "weapon")
      this.weapon.push(e);

  }

  this.removeKai = function(l, e){
    if(e == "kai") 
      $.grep(this.kai, function (v){
        return v == e;
      })
    else if (e == "weapon")
      $.grep(this.weapon, function (v){
        return v == e;
      })
  }
});