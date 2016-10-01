package MainPack.ServerPart;


public class CreatureV2Imp implements CreatureV2{


    public CreatureV2Imp(Profession unitProf, String aName, RaceType aRace)
    {
        unitRace = aRace;
        battleMode = BattleMode.attackPosition;

        constitution = unitProf.con +unitRace.bonusToCon;
        agility = unitProf.agi +unitRace.bonusToAgi;
        strength = unitProf.str +unitRace.bonusToStr;

        name = aName;
    }

    public void transferStats()
    {
        hp = constitution * 5;
        defence = agility * 3;
        attack = strength * 5;
        maxHp = constitution * 5;

    }


    public void takeDamageFromAttack(int objectAttack)
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
    public void takeDamageWhileDefending(int objectAttack)
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

    public void attackPosition()
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

    public void defencePosition()
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



    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    private void setAttack(int attack) {
        this.attack = attack;
    }

    private void setDefence(int defence) {
        this.defence = defence;
    }



    public int getAgility() {
        return agility;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getStrength() {
        return strength;
    }


    public int getAttack() {
        return attack;
    }

    public String getName() {
        return name;
    }

    public int getDefence() {
        return defence;
    }


    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public RaceType getUnitRace() {
        return unitRace;
    }

    public BattleMode getBattleMode() {
        return battleMode;
    }

    public boolean isDead() {return hp <= 0;}



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
