


app.provider("game", function (){
  this.$get = function ($http, $q, player, spe_object, backpack){
    var game = {
      story: {},
      load: function (){
        var tmp = this;
        var deferred = $q.defer();

        $http.get('/api/page').then(
          function (rep){
            if (!rep.data.error) {
              tmp.story = rep.data;
              tmp.checkPath();
              tmp.checkEndurance();
              tmp.checkAlea(tmp.story.info_page.id);
              tmp.fight();
            } else {

            }

            deferred.resolve(tmp.story);
          },
          function (rep){
            console.log("Erreur lors du chargement de l'histoire !");
            deferred.reject();
          });

        return deferred.promise;
      },
      loadPage: function (page){
        var tmp = this;
        var deferred = $q.defer();

        player.player.state.pastPages.push(player.player.state.currentPage);
        player.player.state.currentPage = page;

        player.save().then(function(rep) {
          $http.get('/api/page/'+page).then(
            function (rep){
              if (!rep.data.error) {
                tmp.story = rep.data;
                tmp.checkPath();
                tmp.checkEndurance();
                tmp.checkAlea(page);
                tmp.fight();
              } else {

              }

              deferred.resolve(tmp.story);
            },
            function (rep){
              console.log("Erreur lors du chargement de l'histoire !");
              deferred.reject();
            });
        });
        return deferred.promise;
      },
      checkPath: function (){
        this.story.next_page = this.story.next_page.filter(function( obj ) {
          if(!obj.cdt || player.player.kais.indexOf(obj.cdt) != -1 || player.player.spe_object.indexOf(obj.cdt) != -1)
            return obj;
        });
      },
      checkEndurance: function (){
          if(this.story.info_page.endurance && this.story.info_page.endurance.val){
            var i = player.player.backpack.indexOf(this.story.info_page.endurance.cdt);
            if(i != -1){
              player.player.backpack.splice(i,1);
              return;
            }

            player.player.life += this.story.info_page.endurance.val;
          }
      },
      checkAlea: function (page){
        var tmp = this;

        if(this.story.alea){
          $http.get('/api/choixAleatoire/'+page).then(
            function (rep){
              tmp.story.next_page[0].id = rep.data.choix.page;
            },
            function (rep){
              console.log("Erreur lors du choix aléatoire !");
              deferred.reject();
            });
        }
      },
      use: function (){
        if(this.story.info_page.use){
          for(u = 0 ; u < this.story.info_page.use.length ; u++){
            var i = player.player.backpack.indexOf(this.story.info_page.use[u]);
            if(i != -1){
              player.player.backpack.splice(i,1);
            }
          }
        }
      },
      addObject: function (objects){
        for(o = 0 ; o < objects.length ; o++){      
          var i = backpack[objects[o].nom];
          var j = spe_object[objects[o].nom];

          if(i){
            for(n = 0 ; n < objects[o].nb ; n++)
              player.player.backpack.push(objects[o].nom);
          }

          if(j){
            for(n = 0 ; n < objects[o].nb ; n++)
              player.player.spe_object.push(objects[o].nom);
          }
        }
      },
      fight: function (){
        var tmp = this;
        if(this.story.info_page.battle && this.story.info_page.battle.name){
          $http.get('api/combat/'+player.player.dexter+'/'+player.player.life+'/'+tmp.story.info_page.battle.hability+'/'+tmp.story.info_page.battle.endurance).then(
            function (rep){
              tmp.story.currentBattle = rep.data;
              player.player.state.battles.push({ennemy: tmp.story.info_page.battle.name, fight:rep.data});
            },
            function (rep){
              console.log("Erreur lors du combat !");
              deferred.reject();
            });
        }
      }
    }

    return game;
  }

});

app.provider("player", function (){
  this.$get = function ($http, $q, storage, state){
    var player = {
      player: {},
      load: function (){
        var tmp = this;
        var deferred = $q.defer();

        $http.get('/api/player').then(
          function (rep){
            if(!rep.data.error){
               tmp.player = rep.data;
            } else {
                
            }
            
            deferred.resolve(tmp.player);
          },
          function (rep){
            console.log("Erreur lors du chargement du joueur !");
            deferred.reject();
          });

        return deferred.promise;
      },
      save: function (){
        var tmp = this;
        var deferred = $q.defer();

        $http.post('/api/player/save', tmp.player, config).then(
          function (rep){
            if(!rep.data.error){
              
            } else {
                
            }
            
            deferred.resolve();
          },
          function (rep){
            console.log("Erreur lors de la sauvegarde du joueur !");
            deferred.reject();
          });

        return deferred.promise;
      },
      delete: function (){
        var tmp = this;
        var deferred = $q.defer();

        $http.delete('/api/players/'+tmp.player._id).then(
          function (rep){
            storage.deleteGame(tmp.player._id);
            deferred.resolve(tmp.saves);
          },
          function (rep){
            console.log("Une erreur c'est produite lors de la suppression de la partie !");
            deferred.reject();
          });

        return deferred.promise;
      }
    }
 
    return player;
  }
});

app.provider("state", function (){
  this.$get = function (){
    var state = {
        load: function (){
          
        },
        save: function (){
            
        },
        erase: function (){
            
        }
    }

    return state;
  }
});


app.provider("saves", function (){
  this.saves = [];

  this.$get = function ($http, $q, storage){
    var game = {
      saves : [],
      getAllSaves: function (){
        this.saves = [];
        var tmp = this;
        var ids = storage.gamesSave();
        var deferred = $q.defer();

        if(ids.length != 0)
          $http.get('/api/saves/'+JSON.stringify(ids)).then(
            function (rep){
              tmp.saves = rep.data;
              deferred.resolve(tmp.saves);
            },
            function (rep){
              console.log("Impossible de charger le partie sauvegardées !");
              deferred.reject();
            });

        return deferred.promise;
      },
      getSaveId: function (id){
        return this.saves[id]._id;
      },
      getSaveName: function (id){
        return this.saves[id].name;
      },
      getSaveChapter: function (id){
        return this.saves[id].state.currentPage;
      },
      createSave: function (){
        storage.addGame(storage.gameId());
      },
      loadSave: function (id){
        var tmp = this;
        var deferred = $q.defer();

        $http.get('/api/player/reset').then(
          function (rep){
            storage.loadGame(id);
            deferred.resolve();
          },
          function (rep){
            console.log("Une erreur c'est produite lors du chargement de la partie !");
            deferred.reject();
          });

        return deferred.promise;
      },
      deleteSave: function (id){
        var tmp = this;
        var deferred = $q.defer();

        $http.delete('/api/players/'+id).then(
          function (rep){
            storage.deleteGame(id);

            tmp.saves = tmp.saves.filter(function( obj ) {
                return obj._id != id;
            });

            deferred.resolve(tmp.saves);
          },
          function (rep){
            console.log("Une erreur c'est produite lors de la suppression de la sauvegarde !");
            deferred.reject();
          });

        return deferred.promise;
      }
    }

    return game;
  }

});


app.provider("storage", function (){
  this.$get = function ($cookies, $http){
    var storage = {
      gamesSave: function (){
        return JSON.parse(localStorage.getItem('gameSaveIds')) || [];
      },
      addGame: function (id){
        var tmp = this.gamesSave();
        tmp.push(id);
        localStorage.setItem('gameSaveIds', JSON.stringify(tmp));
        return;
      },
      deleteGame: function (id){
        var tmp = this;
        var localSaves = this.gamesSave();

        localSaves = localSaves.filter(function( str ) {
            if(str == id)
              if(tmp.gameId() == str)
                $cookies.remove('gameId');
            
            return str != id;
        });
        localStorage.setItem('gameSaveIds', JSON.stringify(localSaves));

        return;
      },
      loadGame: function (id){
        $cookies.put('gameId', 'j:"'+id+'"', { domain: '.localhost'});
        return $cookies.get('gameId');
      },
      gameId: function (){
        return ($cookies.get('gameId')) ? $cookies.get('gameId').match(/"(.*?)"/)[1] : "";
      }
    }

    return storage;
  }
});