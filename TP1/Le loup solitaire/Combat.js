var var_globals = require('./vars_global');

function Battle() {
    this.playerEndurance = 0;
    this.playerHabilities = 0;
    this.ennemyEndurance = 0;
    this.ennemyHabilities = 0;
}

Battle.prototype.startBattle = function () {
    var attackRatio = playerHabilities - ennemyHabilities;
    var randomNumber = getRandom(0, 9);

    // En attendant d'avoir le tableau de correspondance.
    var playerLoss = 3;
    var ennemyLoss = 2;

    var result = new BattleResult();
    result.AttackRatio = attackRatio;
    result.randomNumber = randomNumber;
    result.playerEndurance = playerEndurance;
    result.playerEnduranceDiff = playerLoss;
    result.ennemyEndurance = ennemyEndurance;
    result.ennemyEnduranceDiff = ennemyLoss;
}

function BattleResult() {
    this.AttackRatio = 0;
    this.randomNumber = 0;
    this.playerEndurance = 0;
    this.playerEnduranceDiff = 0;
    this.ennemyEndurance = 0;
    this.ennemyEnduranceDiff = 0;
}
