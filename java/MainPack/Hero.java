package MainPack;

class Hero extends Creature{

    // TODO: 18.09.2016 Add implementation of races

    Hero(int strength, int constitution, int agility, String _name, int _exp, int _lvl,
         int aFlag, RaceType aRace)
    {
        super(strength, constitution, agility, _name, aRace);
        transferStats();

        currentExp = _exp;
        expToNextLvl = lvl * 100;
        lvl = _lvl;

        flag = aFlag;
    }

    public int getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(int currentExp) {
        this.currentExp = currentExp;
    }

    public int getExpToNextLvl() {
        return expToNextLvl;
    }

    public int getLvl() {
        return lvl;
    }

    int getFlag() {
        return flag;
    }

    public void lvlUp()
    {
        lvl++;
        setStrength(getStrength() + 2);
        setConstitution(getConstitution() + 4);
        setAgility(getAgility() + 3);
        transferStats();
    }

    void attackPosition()
    {
        if (flag != 2)
        {
            int bonus = super.getAttack() / 10;
            super.setAttack(bonus + super.getAttack());
            flag = 2;

            if (flag == 3)
            {
                int penalty = super.getDefence() / 105 * 5;
                super.setDefence(super.getDefence() - penalty);
            }
        }
    }

    void defencePosition()
    {
        if (flag != 3)
        {
            int bonus = super.getDefence() / 5;
            super.setDefence(super.getDefence() + bonus);
            flag = 3;

            if (flag == 2)
            {
                int penalty = super.getAttack() / 11;
                super.setAttack(super.getAttack() - penalty);
            }
        }
    }



    private int currentExp;
    private int expToNextLvl;
    private int lvl;
    private int flag;

}
