package MainPack.ServerPart;


abstract class CreatureSuperFactory {

    public abstract Hero getHero(Profession unitProf, String name, RaceType race);
    public abstract Hero getHero(int strength, int constitution, int agility, String _name, int _exp, int _lvl,
                                 RaceType aRace, BattleMode aMode);

    public abstract Monster getMonster(int strength, int constitution, int agility, String _name, RaceType aRace, BattleMode aMode);

}
