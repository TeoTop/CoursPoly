var app = angular.module('monApp',[]);

var config = {
  headers : {
    'Content-Type': 'application/json'
  }
};

app.controller('GlobalCtrl', function() {
  this.title = "";
  this.sayHello = function() {
    return 'Hello ' + this.title + '!';
  };
});


app.controller('PlayerCtrl', function($http) {
  var pc = this;
  pc.name = "";
  pc.kais = [];
  pc.equipments = [];

  $http.get('/api/equipments').then(
    function(rep){
      pc.kais = rep.data.kais;
      pc.equipments = rep.data.equipments;
    },
    function(rep){
      console.log("Failed to get data from web services api/equipments");
    });

  pc.submitForm = function(){
    var send = true;

    //regex pour le nom
    var regex = /^\w+$/;

    if($("input[name='kais']:checked").length != 5){
      $("#kais_error").attr('class', 'error');
      send = false;
    } else {
      $("#kais_error").attr('class', 'hide');
    }

    if($("input[name='equipments']:checked").length != 2) {
      $("#equipments_error").attr('class', 'error');
      send = false;
    } else {
      $("#equipments_error").attr('class', 'hide');
    }

    if(!pc.name || !regex.test(pc.name)){
      $("#name_error").attr('class', 'error');
      send = false;
    } else {
      $("#name_error").attr('class', 'hide');
    }

    if(send){
      var kais_sel = $("input[name='kais']:checked").map(function(){return this.value}).get();
      var equipments_sel = $("input[name='equipments']:checked").map(function(){return this.value}).get();
      var parameter = JSON.stringify({name:pc.name, kais:kais_sel, equipments:equipments_sel});
      
      $http.post('/new_game', parameter, config).then(
        function(rep){
            if(rep.data.ok)
              window.location.replace("http://localhost:3000/chap/1/1");
        }, 
        function(){
          alert("err");
        });
    }
  }
});