package MainPack.ServerPart;


import MainPack.BattleMode;
import MainPack.Profession;
import MainPack.RaceType;

class HeroFactory extends CreatureSuperFactory{

    public Hero getHero(Profession unitProf, String name, RaceType race)
    {
        if (unitProf == null)return null;

        if (unitProf == Profession.Dampir)return new Hero(Profession.Dampir, name, race);
        else if (unitProf == Profession.Paladin)return new Hero(Profession.Paladin, name, race);
        else return new Hero (Profession.Berserk, name, race);

    }
    public Hero getHero(int strength, int constitution, int agility, String _name, int _exp, int _lvl,
                        RaceType aRace, BattleMode aMode)
    {
        return new Hero(strength, constitution, agility, _name, _exp, _lvl, aRace, aMode);
    }

    public Monster getMonster(int strength, int constitution, int agility, String _name, RaceType aRace, BattleMode aMode) {
        return null;
    }

}
