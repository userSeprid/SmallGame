package MainPack;

abstract class Creature {

    Creature(int aStrength, int aConstitution, int aAgility, String aName, RaceType aRace, BattleMode mode)


    {
        unitRace = aRace;
        battleMode = mode;

        constitution = aConstitution +unitRace.bonusToCon;
        agility = aAgility +unitRace.bonusToAgi;
        strength = aStrength +unitRace.bonusToStr;

        name = aName;
  }
    void transferStats()
    {
        hp = constitution * 5;
        defence = agility * 3;
        attack = strength * 5;
        maxHp = constitution * 5;

    }


    void takeDamageFromAttack(int objectAttack)
    {

        int damage = objectAttack - defence;

        if (damage < 2)
        {
            hp--;
        }else
        {
            hp = hp - damage;
        }


    }
    void takeDamageWhileDefending(int objectAttack)
    {
        int bonus = defence / 4;
        int damage = objectAttack - defence + bonus;

        if (damage < 2)
        {
            hp--;
        }else
        {
            hp = hp - damage;
        }

    }

    void attackPosition()
    {
        if (battleMode == BattleMode.defencePosition)
        {
            int penalty = getDefence() / 105 * 5;
            setDefence(getDefence() - penalty);

            int bonus = getAttack() / 10;
            setAttack(bonus + getAttack());
            battleMode = BattleMode.attackPosition;
        }
    }

    void defencePosition()
    {
        if (battleMode == BattleMode.attackPosition)
        {
            int bonus = getDefence() / 5;
            setDefence(getDefence() + bonus);

            int penalty = getAttack() / 11;
            setAttack(getAttack() - penalty);
            battleMode = BattleMode.defencePosition;
        }
    }



    void setAgility(int agility) {
        this.agility = agility;
    }

    void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    void setStrength(int strength) {
        this.strength = strength;
    }

    private void setAttack(int attack) {
        this.attack = attack;
    }

    private void setDefence(int defence) {
        this.defence = defence;
    }



    int getAgility() {
        return agility;
    }

    int getConstitution() {
        return constitution;
    }

    int getStrength() {
        return strength;
    }


    int getAttack() {
        return attack;
    }

    String getName() {
        return name;
    }

    int getDefence() {
        return defence;
    }


    int getMaxHp() {
        return maxHp;
    }

    int getHp() {
        return hp;
    }

    RaceType getUnitRace() {
        return unitRace;
    }

    BattleMode getBattleMode() {
        return battleMode;
    }

    boolean isDead() {return hp <= 0;}



    private int hp;
    private int defence;
    private int attack;
    private String name;
    private int maxHp;
    private int constitution;
    private int strength;
    private int agility;
    private RaceType unitRace;
    private BattleMode battleMode;


}
