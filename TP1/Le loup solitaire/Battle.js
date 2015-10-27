var common_functions = require('./CommonFunctions');

function Battle() {
    this.playerEndurance = 0;
    this.playerHabilities = 0;
    this.ennemyEndurance = 0;
    this.ennemyHabilities = 0;
}

Battle.prototype.fight = function () {

    function BattleResult() {
        this.combatRatio = 0;
        this.randomNumber = 0;
        this.playerEndurance = 0;
        this.playerEnduranceDiff = 0;
        this.ennemyEndurance = 0;
        this.ennemyEnduranceDiff = 0;
    }

    var combatRatio = this.playerHabilities - this.ennemyHabilities;
    var randomNumber = common_functions.getRandom(0, 9);

    var tableResult;

    if(combatRatio > 0){
        tableResult = combatRatioPos[randomNumber][Math.abs(combatRatio)];
  	} else{
        tableResult = combatRatioNeg[randomNumber][Math.abs(combatRatio)];
    }

    var result = new BattleResult();
    result.combatRatio = combatRatio;
    result.randomNumber = randomNumber;
    result.playerEndurance = this.playerEndurance;
    result.playerEnduranceDiff = tableResult[0];
    result.ennemyEndurance = this.ennemyEndurance;
    result.ennemyEnduranceDiff = tableResult[1];


    return result;
}

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
[[12,0],[14,0],[14,0],[15,0],[15,0],[18,0],[18,0],[-1,0],[-1,0],[-1,0],[-1,0],[-1,0]],
[[3,5], [4,5], [4,5], [5,4] ,[5,4] ,[6,4] ,[6,4] ,[7,4] ,[7,4] ,[8,3] ,[8,3] ,[9,3] ],
[[4,4], [5,4], [5,4], [6,3] ,[6,3] ,[7,3] ,[7,3] ,[8,3] ,[8,3] ,[9,3] ,[9,3] ,[10,2]],
[[5,4], [6,3], [6,3], [7,3] ,[7,3] ,[8,3] ,[8,3] ,[9,2] ,[9,2] ,[10,2] ,[10,2] ,[11,2]],
[[6,3], [7,3], [7,3], [8,2] ,[8,2] ,[9,2] ,[9,2] ,[10,2],[10,2],[11,2] ,[11,2] ,[12,2]],
[[7,2], [8,2], [8,2], [9,2] ,[9,2] ,[10,2],[10,2],[11,2],[11,2],[12,2] ,[12,2] ,[14,1]],
[[8,2], [9,2], [9,2], [10,2],[10,2],[11,1],[11,1],[12,1],[12,1],[14,1] ,[14,1] ,[16,1]],
[[9,1], [10,1],[10,1],[11,1],[11,1],[12,0],[12,0],[12,0],[12,0],[16,0] ,[16,0] ,[18,0]],
[[10,0],[11,0],[11,0],[12,0],[12,0],[14,0],[14,0],[14,0],[14,0],[18,0] ,[18,0] ,[-1,0]],
[[11,0],[12,0],[12,0],[14,0],[14,0],[16,0],[16,0],[16,0],[16,0],[-1,0],[-1,0],[-1,0]]
];

module.exports = Battle;