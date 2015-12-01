var app = angular.module('monApp',['ngCookies']);

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


app.controller('NewGameCtrl', function($cookies, $http) {
  var ngc = this;
  ngc.name = "";
  ngc.kais = [];
  ngc.equipments = [];
  ngc.cookieId = [];

  var playerCookies = $cookies.get('playerId');

  if(playerCookies)
    ngc.cookieId = JSON.parse(playerCookies.match( /({.*:.*?})/ )[1]);

  $http.get('/api/equipments').then(
    function(rep){
      ngc.kais = rep.data.kais;
      ngc.equipments = rep.data.equipments;
    },
    function(rep){
      console.log("Failed to get data from web services api/equipments");
    });

  ngc.submitForm = function(){
    var send = true;
    var first_error = "";
    //regex pour le nom
    var regex = /^\w+$/;

    if($("input[name='equipments']:checked").length != 2) {
      $("#equipments_error").attr('class', 'error');
      first_error = "#equipments_error";
      send = false;
    } else {
      $("#equipments_error").attr('class', 'hide');
    }

    if($("input[name='kais']:checked").length != 5){
      $("#kais_error").attr('class', 'error');
      first_error = "#kais_error";
      send = false;
    } else {
      $("#kais_error").attr('class', 'hide');
    }

    if(!ngc.name || !regex.test(ngc.name)){
      $("#name_error").attr('class', 'error');
      first_error = "#name_error";
      send = false;
    } else {
      $("#name_error").attr('class', 'hide');
    }

    if(send){
      var kais_sel = $("input[name='kais']:checked").map(function(){return this.value}).get();
      var equipments_sel = $("input[name='equipments']:checked").map(function(){return this.value}).get();
      var parameter = JSON.stringify({name:ngc.name, kais:kais_sel, equipments:equipments_sel});
      
      $http.post('/new_game', parameter, config).then(
        function(rep){
          if(rep.data.ok)
            sessionStorage.setItem('currentPage', 1);
            window.location.assign(rep.data.url);
        }, 
        function(){
          alert("err");
        });
    } else {
      window.location.hash = first_error;
    }
  }
});

app.controller('CurrentPageCtrl', function($cookies, $http) {
  var cpc = this;
  cpc.info_page = {};
  cpc.next_page = [];

  $http.get('/api/page').then(
    function(rep){
      if(!rep.data.error){
        cpc.all = rep.data.all;
        cpc.info_page = rep.data.info_page;
        cpc.next_page = rep.data.next_page;    
      }
    },
    function(rep){
      console.log("Failed to get data from web services api/equipments");
    });
  
  cpc.nextPage = function(page){
    console.log(page);
    $http.get('/api/page/'+page).then(
    function(rep){
      if(!rep.data.error){
        cpc.all = rep.data.all;
        cpc.info_page = rep.data.info_page;
        cpc.next_page = rep.data.next_page;
      }
    },
    function(rep){
      console.log("Failed to get data from web services api/equipments");
    });
  }

  cpc.validation = function(){
    cpc.all = true;
  }
});


app.controller('PlayerCtrl', function($cookies, $http) {
  var pc = this;
  pc.player = {};
  $http.get('/api/player').then(
    function(rep){
      if(rep.data.error){
        alert("erreur");
      }
      pc.player = rep.data;
    },
    function(rep){
      console.log("Failed to get data from web services api/equipments");
    });
});