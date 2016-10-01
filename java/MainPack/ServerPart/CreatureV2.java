package MainPack.ServerPart;


interface CreatureV2 {

    void transferStats();
    void takeDamageFromAttack(int objectAttack);
    void takeDamageWhileDefending(int objectAttack);
    void attackPosition();
    void defencePosition();
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
