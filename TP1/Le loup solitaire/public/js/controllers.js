angular.module('loneWolf').controller('GlobalCtrl', function() {
  this.title = "";
  this.sayHello = function() {
    return 'Hello ' + this.title + '!';
  };
});


angular.module('loneWolf').controller('PlayerCtrl', function() {
  this.name = "";
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

  this.submitForm = function(){
    
    /*if(kai.length != 5) 
      return;
    if(weapon.length != 2)
      return;*/

    if(!this.name)
      return;

    var parameter = JSON.stringify({name:this.name, kai:this.kai, weapon:this.weapon});
    $http.post("/new_game", parameter);
  }
});