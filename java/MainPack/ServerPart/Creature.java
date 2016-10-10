package MainPack.ServerPart;


import MainPack.BattleMode;
import MainPack.RaceType;

interface Creature {

    void transferStats();
    void takeDamageFromAttack(int objectAttack);
    void takeDamageWhileDefending(int objectAttack);
    void attackPosition();
    void defencePosition();
    void setCurrentPosition(BattleMode currentMode);
    void setAgility(int agility);
    void setConstitution(int constitution);
    void setStrength(int strength);


    int getAgility();
    int getConstitution();
    int getStrength();


    int getAttack();
    String getName();
    int getDefence();
    int getMaxHp();
    int getHp();
    RaceType getUnitRace();
    BattleMode getBattleMode();


    boolean isDead();
}
