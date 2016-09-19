package MainPack;

class Monster extends Creature {
    Monster(int strength, int constitution, int agility, String _name, RaceType aRace, BattleMode aMode)
    {
        // TODO: 18.09.2016 Add implementation of races

        super(strength, constitution, agility, _name, aRace, aMode);
        transferStats();



    }



}
