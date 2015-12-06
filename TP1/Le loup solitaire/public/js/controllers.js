app.controller('GlobalCtrl', function () {
  this.title = "";
  this.sayHello = function () {
    return 'Hello ' + this.title + '!';
  };
});


app.controller('NewGameCtrl', function ($cookies, $http, kais, equipments, saves) {
  var ngc = this;
  ngc.kais = kais;
  ngc.equipments = equipments;

  saves.getAllSaves().then(function(rep) {
    ngc.saves = rep;
  });


  ngc.loadGame = function (id){
    saves.loadSave(id).then(function() {
      window.location.assign('/chap');
    });
  };

  ngc.deleteSave = function (id){
    saves.deleteSave(id).then(function(rep) {
      ngc.saves = rep;
    });
  };

  ngc.submitForm = function (){
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
      var kais_sel = $("input[name='kais']:checked").map(function (){return this.value}).get();
      var equipments_sel = $("input[name='equipments']:checked").map(function (){return this.value}).get();
      var parameter = JSON.stringify({name:ngc.name, kais:kais_sel, equipments:equipments_sel});
      
      $http.post('/new_game', parameter, config).then(
        function (rep){
          if(rep.data.ok){
            saves.createSave();
            window.location.assign(rep.data.url);
          }
        }, 
        function (){
          alert("Une erreur c'est produite sur le serveur lors de la création du joueur!");
        });
    } else {
      window.location.hash = first_error;
    }
  }
});



app.controller('ChapterCtrl', function ($cookies, $http, player, game, kais, weapons) {
    var cc = this;
    cc.shownRounds = [];
    cc.battleOver = false;

    player.load().then(function (rep) {
        cc.player = rep;
        cc.displayKais = cc.customKais(cc.player.kais)
        console.log(cc.displayKais);
        game.load().then(function (rep) {
            cc.game = rep;
        });
    });

    cc.customKais = function (kais) {
        var tab = [];
        var tmp = [];
        console.log(kais);
        for (i = 0; i < kais.length; i++) {
            tmp.push(kais[i]);
            if (i % 4 == 3) {
                tab.push(tmp.slice());
                tmp = [];
            }
        }

        if (tmp.length != 0)
            tab.push(tmp.slice());

        return tab;
    };

    cc.colspan = function (l) {
        return 4 / l;
    };

    cc.kaiName = function (k) {
        return kais[k];
    };

    cc.weaponName = function (w) {
        return weapons[w];
    };

    cc.nextPage = function (page) {
        game.loadPage(page).then(function (rep) {
            cc.game = rep;
        });
    }

    cc.validation = function () {
        cc.all = true;
        cc.game.all = true;
    }

    cc.fuir = function () {
        cc.all = true;
        cc.battleOver = true;
    }

    cc.battle = function () {
        var battle = new Battle();
        battle.playerHabilities = 12; // A changer pour tester
        battle.ennemyHabilities = 10;
        battle.playerEndurance = 10;
        battle.ennemyEndurance = 10;

        battle.fight();
        cc.battleResult = battle;
        return battle;
    }

    cc.addRound = function () {

        if (cc.battleResult == null) {
            cc.battle();
        }

        if (cc.shownRounds.length < cc.battleResult.rounds.length) {
            cc.shownRounds.push(cc.battleResult.rounds[cc.shownRounds.length]);
        }
        else {
            cc.battleOver = true;
        }
    }
});