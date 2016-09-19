package MainPack;


import java.util.Random;

class GameLogic {

    // TODO: 18.09.2016 Add to method parameters "race" field

    void makeAHero(int profession, String name, RaceType race)
        {
            // TODO: 18.09.2016 Make possible to get name from player.

            if (profession == 1) {player = new Hero(5, 22, 8, name, 0, 1, 1, race);
            } else if (profession == 2) {player = new Hero(6, 17, 12, name, 0, 1, 1, race);
            } else if (profession == 3) {player = new Hero(10, 500, 6, name, 0, 1, 1, race);
            }

        }

    void makeAMonster()
    {
        Random rand = new Random();

        enemy = new Monster(8 + rand.nextInt(3), 15 + rand.nextInt(4), 2 + rand.nextInt(2), "Bakemono", RaceType.Orc);
    }

    Hero getPlayer() {
        return player;
    }

    Monster getEnemy() {
        return enemy;
    }

    private Hero player;
    private Monster enemy;


}
