var var_globals = require('../vars_global');
var common_functions = require('../commonFunctions');

/**
    Objet JS Battle : permet d'exécuter le déroulement d'une bataille.
    Prend comme attributs l'endurance (à compléter) et l'habilté des combatants
**/
function Battle() {
    this.playerEndurance = 0;
    this.playerHabilities = 0;
    this.ennemyEndurance = 0;
    this.ennemyHabilities = 0;
    this.rounds = [];
    this.result = -1;
}

/**
    Méthode permettant de faire un combat au complet
**/
Battle.prototype.fight = function () {
    while (this.playerEndurance > 0 && this.ennemyEndurance > 0) {
        this.round();
    }
}

/**
    Méthode permettant de calculer les damages lors d'un round.
**/
Battle.prototype.round = function () {

    /**
    Obejt JS interne : Créer pour retourner le résultat d'un round (service web combat)
    **/
    function RoundResult() {
        this.combatRatio = 0;
        this.randomNumber = 0;
        this.playerEndurance = 0;
        this.playerEnduranceLose = 0;
        this.ennemyEndurance = 0;
        this.ennemyEnduranceLose = 0;
    }

    // On calcule le ratio pour déterminer qui a l'avantage dans le round (on bloque la différence à (-)11)
    var combatRatio = this.playerHabilities - this.ennemyHabilities;
    if (combatRatio < -11) combatRatio = -11;
    else if (combatRatio > 11) combatRatio = 11;

    // On détermine le chiffre aléatoire pour le round (entre 0 et 9 inclus)
    var randomNumber = common_functions.getRandom(0, 9);

    // On récupère les dégats de chaque combattant
    var tableResult;
    if (combatRatio > 0) {
        tableResult = combatRatioPos[randomNumber][Math.abs(combatRatio)];
    } else {
        tableResult = combatRatioNeg[randomNumber][Math.abs(combatRatio)];
    }

    // Initialisation du résultat du round
    var result = new RoundResult();

    result.combatRatio = combatRatio;
    result.randomNumber = randomNumber;
    result.ennemyEnduranceLose = tableResult[0];
    result.ennemyEndurance = this.ennemyEndurance - result.ennemyEnduranceLose;
    result.playerEnduranceLose = tableResult[1];
    result.playerEndurance = this.playerEndurance - result.playerEnduranceLose;

    this.rounds.push(result);

    return result;
}

/**
    Tableaux regroupants les damages à appliquer sur [ennemi, lone wolf] lors d'un round. (-1 signifie insta-kill)
**/
var combatRatioNeg = [
[[12,0],[11,0],[11,0],[10,0],[10,0],[9,0],[9,0],[8,0],[8,0],[7,0],[7,0],[6,0]],
[[3,5],[2,5],[2,5],[1,6],[1,6],[0,6],[0,6],[0,8],[0,8],[0,-1],[0,-1],[0,-1]],
[[4,4],[3,5],[3,5],[2,5],[2,5],[1,6],[1,6],[0,7],[0,7],[0,8],[0,8], [0,-1]],
[[5,4],[4,4],[4,4],[3,5],[3,5],[2,5],[2,5],[1,6],[1,6],[0,7],[0,7],[0,8]],
[[6,3],[5,4],[5,4],[4,4],[4,4],[3,5],[3,5],[2,6],[2,6],[1,7],[1,7],[0,8]],
[[7,2],[6,3],[6,3],[5,4],[5,4],[4,4],[4,4],[3,5],[3,5],[2,6],[2,6],[1,7]],
[[8,2],[7,2],[7,2],[6,3],[6,3],[5,4],[5,4],[4,5],[4,5],[3,6],[3,6],[2,6]],
[[9,1],[8,2],[8,2],[7,2],[7,2],[6,3],[6,3],[5,4],[5,4],[4,5],[4,5],[3,5]],
[[10,0],[9,1],[9,1],[8,1],[8,1],[7,2],[7,2],[6,3],[6,3],[5,4],[5,4],[4,4]],
[[11,0],[10,0],[10,0],[9,0],[9,0],[8,0],[8,0],[7,2],[7,2],[6,3],[6,3],[5,3]]
];

var combatRatioPos = [
[[12,0],[14,0],[14,0],[16,0],[16,0],[18,0],[18,0],[-1,0],[-1,0],[-1,0],[-1,0],[-1,0]],
[[3,5], [4,5], [4,5], [5,4] ,[5,4] ,[6,4] ,[6,4] ,[7,4] ,[7,4] ,[8,3] ,[8,3] ,[9,3] ],
[[4,4], [5,4], [5,4], [6,3] ,[6,3] ,[7,3] ,[7,3] ,[8,3] ,[8,3] ,[9,3] ,[9,3] ,[10,2]],
[[5,4], [6,3], [6,3], [7,3] ,[7,3] ,[8,3] ,[8,3] ,[9,2] ,[9,2] ,[10,2] ,[10,2] ,[11,2]],
[[6,3], [7,3], [7,3], [8,2] ,[8,2] ,[9,2] ,[9,2] ,[10,2],[10,2],[11,2] ,[11,2] ,[12,2]],
[[7,2], [8,2], [8,2], [9,2] ,[9,2] ,[10,2],[10,2],[11,2],[11,2],[12,2] ,[12,2] ,[14,1]],
[[8,2], [9,2], [9,2], [10,2],[10,2],[11,1],[11,1],[12,1],[12,1],[14,1] ,[14,1] ,[16,1]],
[[9,1], [10,1],[10,1],[11,1],[11,1],[12,0],[12,0],[14,0],[14,0],[16,0] ,[16,0] ,[18,0]],
[[10,0],[11,0],[11,0],[12,0],[12,0],[14,0],[14,0],[16,0],[16,0],[18,0] ,[18,0] ,[-1,0]],
[[11,0],[12,0],[12,0],[14,0],[14,0],[16,0],[16,0],[18,0],[18,0],[-1,0],[-1,0],[-1,0]]
];

module.exports = Battle;