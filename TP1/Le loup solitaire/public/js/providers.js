


app.provider("game", function (){
  this.$get = function ($http, $q){
    var game = {
      story: {},
      load: function (){
        var tmp = this;
        var deferred = $q.defer();

        $http.get('/api/page').then(
          function (rep){
            if (!rep.data.error) {
              tmp.story = rep.data;
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

        $http.get('/api/page/'+page).then(
          function (rep){
            if (!rep.data.error) {
              tmp.story = rep.data;
            } else {

            }

            deferred.resolve(tmp.story);
          },
          function (rep){
            console.log("Erreur lors du chargement de l'histoire !");
            deferred.reject();
          });

        return deferred.promise;
      }
    }

    return game;
  }

});

app.provider("player", function (){
  this.$get = function ($http, $q, state){
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
          
      },
      delete: function (){
          
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
        $cookies.put('gameId', id, { domain: '.localhost'});
        return $cookies.get('gameId');
      },
      gameId: function (){
        return ($cookies.get('gameId')) ? $cookies.get('gameId').match(/"(.*?)"/)[1] : "";
      }
    }

    return storage;
  }
});