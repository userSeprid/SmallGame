package MainPack.ServerPart;


import MainPack.BattleMode;
import MainPack.Profession;
import MainPack.RaceType;

class MonsterFactory extends CreatureSuperFactory{

    public Hero getHero(Profession unitProf, String name, RaceType race) {
        return null;
    }

    public Hero getHero(int strength, int constitution, int agility, String _name, int _exp, int _lvl, RaceType aRace, BattleMode aMode) {
        return null;
    }

    public Monster getMonster(int strength, int constitution, int agility, String _name, RaceType aRace, BattleMode aMode)
    {
        return new Monster(strength, constitution, agility, _name, aRace, aMode);
    }
}
