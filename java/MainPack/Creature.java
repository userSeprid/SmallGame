package MainPack;

abstract class Creature {

    Creature(int aStrength, int aConstitution, int aAgility, String aName)


    {
        constitution = aConstitution;
        agility = aAgility;
        strength = aStrength;
        constitution = aConstitution;

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


    void setAgility(int agility) {
        this.agility = agility;
    }

    void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    void setStrength(int strength) {
        this.strength = strength;
    }

    void setAttack(int attack) {
        this.attack = attack;
    }

    void setDefence(int defence) {
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

    boolean isDead()
    {
        return hp <= 0;

    }

    private int hp;
    private int defence;
    private int attack;
    private String name;
    private int maxHp;
    private int constitution;
    private int strength;
    private int agility;


}
