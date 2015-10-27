var common_functions = require('./CommonFunctions');

function Battle() {
    this.playerEndurance = 0;
    this.playerHabilities = 0;
    this.ennemyEndurance = 0;
    this.ennemyHabilities = 0;
}

Battle.prototype.fight = function() {

    function BattleResult() {
        this.AttackRatio = 0;
        this.randomNumber = 0;
        this.playerEndurance = 0;
        this.playerEnduranceDiff = 0;
        this.ennemyEndurance = 0;
        this.ennemyEnduranceDiff = 0;
    }
    
    var attackRatio = this.playerHabilities - this.ennemyHabilities;
    var randomNumber = common_functions.getRandom(0, 9);

    // En attendant d'avoir le tableau de correspondance.
    var playerLoss = battleTable[0][0];
    var ennemyLoss = battleTable[2][1];

    var result = new BattleResult();
    result.AttackRatio = attackRatio;
    result.randomNumber = randomNumber;
    result.playerEndurance = this.playerEndurance;
    result.playerEnduranceDiff = playerLoss;
    result.ennemyEndurance = this.ennemyEndurance;
    result.ennemyEnduranceDiff = ennemyLoss;


    return result;
}

var battleTable = [[10,11], [12,13], [3, 4], [5,1]];

module.exports = Battle;