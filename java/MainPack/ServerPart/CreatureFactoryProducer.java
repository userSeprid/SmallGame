package MainPack.ServerPart;


class CreatureFactoryProducer {
    static CreatureSuperFactory getFactory(String name)
    {
        if (name.equalsIgnoreCase("hero"))return new HeroFactory();
        else if (name.equalsIgnoreCase("monster"))return new MonsterFactory();
        return null;
    }
}
