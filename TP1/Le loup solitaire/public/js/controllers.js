var app = angular.module('monApp',['ngCookies']);
app.value('clientId', '');

var config = {
  headers : {
    'Content-Type': 'application/json'
  }
};

app.provider("player", function(){
  this.$get = function(){
    var player = {
        "load": function(){
            this.name = "test"
            alert(this);
        },
        "save": function(){
            
        },
        "erase": function(){
            
        }
    }
 
    return player;
  }
});

app.provider("state", function(){
  this.$get = function(){
    var state = {
        "load": function(){
            
        },
        "save": function(){
            
        },
        "erase": function(){
            
        }
    }

    return state;
  }
});

app.provider("storage", function(){
  this.$get = function($cookies, $http){
    var storage = {
      gamesSave: function(){
        return JSON.parse(localStorage.getItem('gameSaveIds')) || [];
      },
      addGame: function(id){
        this.gameId = id;
        var tmp = this.gamesSave();
        tmp.push(id);
        localStorage.setItem('gameSaveIds', JSON.stringify(tmp));
        return;
      },
      deleteGame: function(){
        var tmp = this.gamesSave();
        var index = tmp.indexOf(this.gameId);
        if(index != -1) {
          $cookies.remove('gameId');
          tmp.splice(index, 1);
          localStorage.setItem('gameSaveIds', JSON.stringify(tmp));
        }
        return;
      },
      loadGame: function(index){
        $cookies.put('gameId', localStorage.getItem('gameSaveIds')[index], { domain: '.localhost'});
        return $cookies.get('gameId');
      },
      gameId: function(){
        return $cookies.get('gameId').match(/"(.*?)"/)[1];
      }
    }

    return storage;
  }
});

app.provider("game", function(){
  this.$get = function($cookies, $http, storage){
    var game = {
      player : {},
      state : "state",
      getSave: function(){
        var tmp = this;

        //save provider à créer
        /*if(storage.gameId){
          $http.get('/api/players/'+storage.gameId()).then(
            function(rep){
              tmp.player.name = rep.data.name;
              //ngc.save.chapter = rep.data.state.currentPage;
            },
            function(rep){
              console.log("Failed to get data from web services api/equipments");
            });
        }*/
        return;
      },
      getKais: function(callback){
        $http.get('/api/kais').then(
          function(rep){
            callback(rep.data.kais);
          },
          function(rep){
            console.log("Failed to get data from web services api/equipments");
            callback({});
          });
      },
      getEquipement: function(callback){
        $http.get('/api/equipments').then(
          function(rep){
            callback(rep.data.equipments);
          },
          function(rep){
            console.log("Failed to get data from web services api/equipments");
            callback({});
          });
      }
    }

    return game;
  }

});

app.controller('GlobalCtrl', function() {
  this.title = "";
  this.sayHello = function() {
    return 'Hello ' + this.title + '!';
  };
});


app.controller('NewGameCtrl', function($cookies, $http, game, storage) {
  var ngc = this;
  ngc.game = game;
  
  ngc.saves = storage.gamesSave();
  if(ngc.saves.length != 0){
    ngc.saves.one = true;
  }

  game.getKais(function(data){
    ngc.kais = data;
  });

  game.getEquipement(function(data){
    ngc.equipments = data;
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
          if(rep.data.ok){
            console.log(rep);
            storage.addGame(storage.gameId());
            window.location.assign(rep.data.url);
          }
        }, 
        function(){
          alert("Une erreur c'est produite sur le serveur lors de la création du joueur!");
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
        
      }
      pc.player = rep.data;
    },
    function(rep){
      console.log("Failed to get data from web services api/equipments");
    });
});