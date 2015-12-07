app.controller('GlobalCtrl', function () {
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
          console.log("Une erreur c'est produite sur le serveur lors de la création du joueur!");
        });
    } else {
      window.location.hash = first_error;
    }
  }
});



app.controller('ChapterCtrl', function ($cookies, $http, $location, player, game, kais, weapons) {
    var cc = this;

    cc.displayBackpack = false;
    cc.displayObject = false;
    cc.displayHeal = false;
    cc.counter = 0;
    cc.shownRounds = [];

    player.load().then(function(rep) {
      cc.player = rep;
      cc.displayKais = cc.customKais(cc.player.kais);

      game.load().then(function(rep) {
        $location.path(cc.player.state.currentPage).replace();
        cc.game = rep;
        cc.healing = (cc.player.kais.indexOf('HEALING') != -1 && cc.game.info_page.id != 1) ? true : false;
        cc.puissance = (cc.player.kais.indexOf('MINDBLAST') != -1) ? true : false;
        cc.next = cc.game.all;
      });
    });

    cc.customKais = function (kais){
      var tab = [];
      var tmp = [];

      for(i=0 ; i<kais.length ; i++){
        tmp.push(kais[i]);
        if(i%4==3){
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
      game.loadPage(page).then(function(rep) {
        $location.path(page).replace();
        cc.game = rep;

        $('html,body').animate({scrollTop: $("#storyBoard").offset().top},'slow');

        cc.puissance = (cc.player.kais.indexOf('MINDBLAST') != -1) ? true : false;

        if(cc.player.kais.indexOf('HEALING') != -1){
          cc.healing = (!cc.displayHeal) ? true : false;
          cc.player.life += 2;
          if(cc.player.life > cc.player.lifeMax + cc.player.lifeBonus)
            cc.player.life = cc.player.lifeMax + cc.player.lifeBonus;
        }

        cc.counter = 0;
        cc.shownRounds = [];
        cc.battleOver = false;
        cc.next = cc.game.all;
      });
    }

    cc.validation = function (action) {
      cc.next = true;

      if(action == ''){
        game.use();
      }

      if(action == 'CHOICE'){
        var sel_objet = $("input[name='sel_objet']:checked").map(function (){ 
          var tmp = {}; 
          tmp.nom = this.id;
          tmp.nb = this.value; 
          return tmp;
        }).get();
        console.log(sel_objet);
        game.addObject(sel_objet);
        game.use();
      }

      if(action == 'END'){
        player.delete().then(function (){
          window.location.assign('/');
        })
      }
    }

    cc.fuir = function () {
      cc.all = true;
      cc.next = true;
    }


    cc.battle = function () {
      cc.shownRounds = cc.game.currentBattle.rounds;
      cc.next = true;
      cc.player.life = cc.game.currentBattle.playerEndurance;

      if(!cc.game.currentBattle.result)
        cc.game.end = true;
    }

    cc.addRound = function () {
      if(cc.counter < cc.game.currentBattle.rounds.length){
        cc.shownRounds.push(cc.game.currentBattle.rounds[cc.counter++]);
      }

      if(cc.counter == cc.game.currentBattle.rounds.length){
        cc.next = true;
        cc.player.life = cc.game.currentBattle.playerEndurance;

        if(!cc.game.currentBattle.result)
          cc.game.end = true;
      }
        
    }
});