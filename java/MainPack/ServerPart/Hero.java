package MainPack.ServerPart;

import MainPack.BattleMode;
import MainPack.Profession;
import MainPack.RaceType;

class Hero extends CreatureImplementation {

    // TODO: 18.09.2016 Add implementation of races

    Hero(int strength, int constitution, int agility, String _name, int _exp, int _lvl,
         RaceType aRace, BattleMode aMode)
    {
        super(strength, constitution, agility, _name, aRace, aMode);
        transferStats();

        currentExp = _exp;
        expToNextLvl = lvl * 100;
        lvl = _lvl;
    }

    Hero(Profession unitProf, String _name, RaceType aRace)
    {
        super(unitProf, _name, aRace);
        transferStats();

        currentExp = 0;
        expToNextLvl = lvl * 100;
        lvl = 1;
    }

    //TODO: Implement level system;

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

    public void lvlUp()
    {
        lvl++;
        setStrength(getStrength() + 2);
        setConstitution(getConstitution() + 4);
        setAgility(getAgility() + 3);
        transferStats();
    }





    private int currentExp;
    private int expToNextLvl;
    private int lvl;

}
